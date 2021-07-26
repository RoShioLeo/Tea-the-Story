package cloud.lemonslice.teastory.common.recipe.serializer;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.recipe.drink.DrinkRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class DrinkRecipeSerializer<T extends DrinkRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>
{
    private final IFactory<T> factory;

    public DrinkRecipeSerializer(IFactory<T> factory)
    {
        this.factory = factory;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json)
    {
        String group = JSONUtils.getString(json, "group", "");
        NonNullList<Ingredient> ingredients = readIngredients(JSONUtils.getJsonArray(json, "item_ingredients"));
        if (ingredients.isEmpty())
        {
            throw new JsonParseException("No ingredients for drink recipe");
        }
        else
        {
            FluidIngredient fluidIngredient = FluidIngredient.deserialize(JSONUtils.getJsonObject(json, "fluid_ingredient"));
            Fluid result = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(JSONUtils.getString(json, "drink_result")));
            if (result == null || result == Fluids.EMPTY)
            {
                throw new JsonSyntaxException("Result cannot be null");
            }
            return this.factory.create(recipeId, group, ingredients, fluidIngredient, result);
        }
    }

    private static NonNullList<Ingredient> readIngredients(JsonArray array)
    {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        for (int i = 0; i < array.size(); ++i)
        {
            Ingredient ingredient = Ingredient.deserialize(array.get(i));
            if (!ingredient.hasNoMatchingItems())
            {
                nonnulllist.add(ingredient);
            }
        }
        return nonnulllist;
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer)
    {
        String group = buffer.readString(32767);

        int i = buffer.readVarInt();
        NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);
        for (int j = 0; j < ingredients.size(); ++j)
        {
            ingredients.set(j, Ingredient.read(buffer));
        }

        FluidIngredient fluidIngredient = FluidIngredient.read(buffer);
        Fluid result = buffer.readFluidStack().getFluid();
        return this.factory.create(recipeId, group, ingredients, fluidIngredient, result);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe)
    {
        buffer.writeString(recipe.getGroup());
        buffer.writeVarInt(recipe.getIngredients().size());

        for (Ingredient ingredient : recipe.getIngredients())
        {
            ingredient.write(buffer);
        }

        recipe.getFluidIngredient().write(buffer);
        buffer.writeFluidStack(new FluidStack(recipe.getFluidResult(), 1));
    }

    interface IFactory<T extends DrinkRecipe>
    {
        T create(ResourceLocation resourceLocation, String group, NonNullList<Ingredient> ingredients, FluidIngredient fluidIngredient, Fluid result);
    }
}
