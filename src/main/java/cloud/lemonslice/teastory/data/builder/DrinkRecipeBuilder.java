package cloud.lemonslice.teastory.data.builder;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import cloud.lemonslice.teastory.common.recipe.serializer.DrinkRecipeSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry.DRINK_MAKER;

public class DrinkRecipeBuilder
{
    private final Fluid result;
    private final FluidIngredient fluidIngredient;
    private final NonNullList<Ingredient> ingredients;

    private DrinkRecipeBuilder(Fluid resultIn, FluidIngredient fluidIngredientIn, NonNullList<Ingredient> ingredientsIn)
    {
        this.result = resultIn;
        this.fluidIngredient = fluidIngredientIn;
        this.ingredients = ingredientsIn;
    }

    public static DrinkRecipeBuilder drinkRecipe(Fluid resultIn, FluidIngredient fluidIngredientIn, Ingredient... ingredientsIn)
    {
        return new DrinkRecipeBuilder(resultIn, fluidIngredientIn, NonNullList.from(Ingredient.EMPTY, ingredientsIn));
    }

    public static DrinkRecipeBuilder boilingRecipe(Fluid resultIn, Ingredient... ingredientsIn)
    {
        return new DrinkRecipeBuilder(resultIn, FluidIngredient.fromFluid(500, FluidRegistry.BOILING_WATER_STILL.get()), NonNullList.from(Ingredient.EMPTY, ingredientsIn));
    }

    public void build(Consumer<IFinishedRecipe> consumerIn)
    {
        this.build(consumerIn, ForgeRegistries.FLUIDS.getKey(this.result));
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, String save)
    {
        ResourceLocation originRes = ForgeRegistries.FLUIDS.getKey(this.result);
        ResourceLocation saveRes = new ResourceLocation(save);
        if (saveRes.equals(originRes))
        {
            throw new IllegalStateException("Recipe " + saveRes + " should remove its 'save' argument");
        }
        else
        {
            this.build(consumerIn, saveRes);
        }
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id)
    {
        consumerIn.accept(new DrinkRecipeBuilder.Result(id, this.result, this.fluidIngredient, this.ingredients));
    }

    public static class Result implements IFinishedRecipe
    {
        private final ResourceLocation id;
        private final FluidIngredient fluidIngredient;
        private final NonNullList<Ingredient> ingredients;
        private final Fluid result;
        private final DrinkRecipeSerializer<?> serializer = DRINK_MAKER.get();

        public Result(ResourceLocation idIn, Fluid resultIn, FluidIngredient fluidIngredientIn, NonNullList<Ingredient> ingredientsIn)
        {
            this.id = new ResourceLocation(idIn.getNamespace(), "drink_maker/" + idIn.getPath());
            this.fluidIngredient = fluidIngredientIn;
            this.result = resultIn;
            this.ingredients = ingredientsIn;
        }

        public void serialize(JsonObject json)
        {
            JsonArray jsonarray = new JsonArray();
            for (Ingredient ingredient : this.ingredients)
            {
                jsonarray.add(ingredient.serialize());
            }
            json.add("item_ingredients", jsonarray);
            json.add("fluid_ingredient", fluidIngredient.serialize());

            json.addProperty("drink_result", ForgeRegistries.FLUIDS.getKey(this.result).toString());
        }

        public IRecipeSerializer<?> getSerializer()
        {
            return this.serializer;
        }

        public ResourceLocation getID()
        {
            return this.id;
        }

        @Nullable
        public JsonObject getAdvancementJson()
        {
            return null;
        }

        @Nullable
        public ResourceLocation getAdvancementID()
        {
            return null;
        }
    }
}
