package cloud.lemonslice.teastory.data.builder;

import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTraySingleInRecipe;
import cloud.lemonslice.teastory.common.recipe.serializer.BambooTraySingleInRecipeSerializer;
import cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry;
import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BambooTrayRecipeBuilder
{
    private final Item result;
    private final Ingredient ingredient;
    private final int workTime;
    private final BambooTraySingleInRecipeSerializer<?> recipeSerializer;

    private BambooTrayRecipeBuilder(IItemProvider resultIn, Ingredient ingredientIn, int workTimeIn, BambooTraySingleInRecipeSerializer<?> serializer)
    {
        this.result = resultIn.asItem();
        this.ingredient = ingredientIn;
        this.workTime = workTimeIn;
        this.recipeSerializer = serializer;
    }

    public static BambooTrayRecipeBuilder workingRecipe(Ingredient ingredientIn, IItemProvider resultIn, int workingTimeIn, BambooTraySingleInRecipeSerializer<?> serializer)
    {
        return new BambooTrayRecipeBuilder(resultIn, ingredientIn, workingTimeIn, serializer);
    }

    public static BambooTrayRecipeBuilder outdoorsRecipe(Ingredient ingredientIn, IItemProvider resultIn, int workingTimeIn)
    {
        return workingRecipe(ingredientIn, resultIn, workingTimeIn, RecipeSerializerRegistry.BAMBOO_TRAY_OUTDOORS.get());
    }

    public static BambooTrayRecipeBuilder indoorsRecipe(Ingredient ingredientIn, IItemProvider resultIn, int workingTimeIn)
    {
        return workingRecipe(ingredientIn, resultIn, workingTimeIn, RecipeSerializerRegistry.BAMBOO_TRAY_INDOORS.get());
    }

    public static BambooTrayRecipeBuilder wetRecipe(Ingredient ingredientIn, IItemProvider resultIn, int workingTimeIn)
    {
        return workingRecipe(ingredientIn, resultIn, workingTimeIn, RecipeSerializerRegistry.BAMBOO_TRAY_IN_RAIN.get());
    }

    public static BambooTrayRecipeBuilder bakeRecipe(Ingredient ingredientIn, IItemProvider resultIn, int workingTimeIn)
    {
        return workingRecipe(ingredientIn, resultIn, workingTimeIn, RecipeSerializerRegistry.BAMBOO_TRAY_BAKE.get());
    }

    public void build(Consumer<IFinishedRecipe> consumerIn)
    {
        this.build(consumerIn, ForgeRegistries.ITEMS.getKey(this.result));
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, String save)
    {
        ResourceLocation originRes = ForgeRegistries.ITEMS.getKey(this.result);
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
        consumerIn.accept(new BambooTrayRecipeBuilder.Result(id, this.ingredient, this.result, this.workTime, this.recipeSerializer));
    }

    public static class Result implements IFinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final Item result;
        private final int workingTime;
        private final IRecipeSerializer<? extends BambooTraySingleInRecipe> serializer;

        public Result(ResourceLocation idIn, Ingredient ingredientIn, Item resultIn, int workingTimeIn, IRecipeSerializer<? extends BambooTraySingleInRecipe> serializerIn)
        {
            this.id = new ResourceLocation(idIn.getNamespace(), "bamboo_tray/" + idIn.getPath());
            this.ingredient = ingredientIn;
            this.result = resultIn;
            this.workingTime = workingTimeIn;
            this.serializer = serializerIn;
        }

        public void serialize(JsonObject json)
        {
            json.add("ingredient", this.ingredient.serialize());
            json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
            json.addProperty("work_time", this.workingTime);
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
