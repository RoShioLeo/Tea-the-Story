package cloud.lemonslice.teastory.common.recipe.serializer;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneMillRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class StoneMillRecipeSerializer<T extends StoneMillRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>
{
    private final int workTime;
    private final StoneMillRecipeSerializer.IFactory<T> factory;

    public StoneMillRecipeSerializer(StoneMillRecipeSerializer.IFactory<T> factory, int timeIn)
    {
        this.factory = factory;
        this.workTime = timeIn;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json)
    {
        String group = JSONUtils.getString(json, "group", "");

        if (!json.has("item_ingredient"))
            throw new JsonSyntaxException("Missing input ingredient, expected to find a string or object");

        JsonElement jsonelement = JSONUtils.isJsonArray(json, "item_ingredient") ? JSONUtils.getJsonArray(json, "item_ingredient") : JSONUtils.getJsonObject(json, "item_ingredient");
        Ingredient inputItem = Ingredient.deserialize(jsonelement);

        FluidIngredient inputFluid;
        if (json.has("fluid_ingredient"))
        {
            inputFluid = FluidIngredient.deserialize(JSONUtils.getJsonObject(json, "fluid_ingredient"));
        }
        else
        {
            inputFluid = FluidIngredient.EMPTY;
        }

        if (!json.has("output_fluid") && !json.has("output_items"))
            throw new JsonSyntaxException("Missing output, expected to find a string or object");

        FluidStack outputFluid;
        if (json.has("output_fluid"))
        {
            JsonObject jsonOutputFluid = JSONUtils.getJsonObject(json, "output_fluid");
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(JSONUtils.getString(jsonOutputFluid, "fluid")));
            if (fluid == null)
            {
                outputFluid = FluidStack.EMPTY;
            }
            else
            {
                int amount = JSONUtils.getInt(jsonOutputFluid, "amount");
                outputFluid = new FluidStack(fluid, amount);
            }
        }
        else
        {
            outputFluid = FluidStack.EMPTY;
        }

        NonNullList<ItemStack> outputItems;
        if (json.has("output_items"))
        {
            outputItems = readItems(JSONUtils.getJsonArray(json, "output_items"));
        }
        else
        {
            outputItems = NonNullList.create();
        }


        int i = JSONUtils.getInt(json, "work_time", this.workTime);
        return this.factory.create(recipeId, group, inputItem, inputFluid, outputItems, outputFluid, i);
    }

    private static NonNullList<ItemStack> readItems(JsonArray array)
    {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        for (int i = 0; i < array.size(); ++i)
        {
            JsonElement item = array.get(i);
            ItemStack itemStack;
            if (item.isJsonObject())
            {
                itemStack = ShapedRecipe.deserializeItem((JsonObject) item);
            }
            else
            {
                itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getString(item, "output_items"))));
            }
            nonnulllist.add(itemStack);
        }
        return nonnulllist;
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer)
    {
        String groupIn = buffer.readString(32767);
        Ingredient inputItem = Ingredient.read(buffer);
        FluidIngredient inputFluid = FluidIngredient.read(buffer);

        int i = buffer.readVarInt();
        NonNullList<ItemStack> outputItems = NonNullList.withSize(i, ItemStack.EMPTY);
        for (int j = 0; j < i; ++j)
        {
            outputItems.set(j, buffer.readItemStack());
        }

        FluidStack outputFluid = buffer.readFluidStack();

        int workTime = buffer.readVarInt();

        return this.factory.create(recipeId, groupIn, inputItem, inputFluid, outputItems, outputFluid, workTime);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe)
    {
        buffer.writeString(recipe.getGroup());
        recipe.getInputItem().write(buffer);
        recipe.getInputFluid().write(buffer);

        buffer.writeVarInt(recipe.getOutputItems().size());
        for (ItemStack ingredient : recipe.getOutputItems())
        {
            buffer.writeItemStack(ingredient);
        }

        buffer.writeFluidStack(recipe.getOutputFluid());

        buffer.writeVarInt(recipe.getWorkTime());
    }

    interface IFactory<T extends StoneMillRecipe>
    {
        T create(ResourceLocation idIn, String groupIn, Ingredient inputItem, FluidIngredient inputFluid, NonNullList<ItemStack> outputItems, FluidStack outputFluid, int workTime);
    }
}
