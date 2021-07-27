package cloud.lemonslice.teastory.data.builder;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.recipe.serializer.StoneMillRecipeSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry.STONE_MILL;

public class StoneMillRecipeBuilder
{
    private final FluidIngredient inputFluid;
    private final Ingredient inputItem;
    private final NonNullList<ItemStack> outputItems;
    private final FluidStack outputFluid;
    private final int workTime;

    private StoneMillRecipeBuilder(Ingredient inputItem, FluidIngredient inputFluid, NonNullList<ItemStack> outputItems, FluidStack outputFluid, int workTime)
    {
        this.inputItem = inputItem;
        this.inputFluid = inputFluid;
        this.outputItems = outputItems;
        this.outputFluid = outputFluid;
        this.workTime = workTime;
    }

    public static StoneMillRecipeBuilder recipe(int workTime, Ingredient inputItem, FluidIngredient inputFluid, FluidStack outputFluid, ItemStack... outputItems)
    {
        return new StoneMillRecipeBuilder(inputItem, inputFluid, NonNullList.from(ItemStack.EMPTY, outputItems), outputFluid, workTime);
    }

    public static StoneMillRecipeBuilder recipeWithDefaultTime(Ingredient inputItem, FluidIngredient inputFluid, FluidStack outputFluid, ItemStack... outputItems)
    {
        return recipe(200, inputItem, inputFluid, outputFluid, outputItems);
    }

    public static StoneMillRecipeBuilder recipeWithoutFluid(int workTime, Ingredient inputItem, ItemStack... outputItems)
    {
        return recipe(workTime, inputItem, FluidIngredient.EMPTY, FluidStack.EMPTY, outputItems);
    }

    public static StoneMillRecipeBuilder recipeWithDefaultTimeWithoutFluid(Ingredient inputItem, ItemStack... outputItems)
    {
        return recipeWithoutFluid(200, inputItem, outputItems);
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, String save)
    {
        ResourceLocation saveRes = new ResourceLocation(save);
        this.build(consumerIn, saveRes);
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id)
    {
        consumerIn.accept(new StoneMillRecipeBuilder.Result(id, this.inputItem, this.inputFluid, this.outputItems, this.outputFluid, this.workTime));
    }

    public static class Result implements IFinishedRecipe
    {
        private final ResourceLocation id;
        protected final FluidIngredient inputFluid;
        protected final Ingredient inputItem;
        protected final NonNullList<ItemStack> outputItems;
        protected final FluidStack outputFluid;
        protected final int workTime;
        private final StoneMillRecipeSerializer<?> serializer = STONE_MILL.get();

        public Result(ResourceLocation idIn, Ingredient inputItem, FluidIngredient inputFluid, NonNullList<ItemStack> outputItems, FluidStack outputFluid, int workTime)
        {
            this.id = new ResourceLocation(idIn.getNamespace(), "stone_mill/" + idIn.getPath());
            this.inputItem = inputItem;
            this.inputFluid = inputFluid;
            this.outputItems = outputItems;
            this.outputFluid = outputFluid;
            this.workTime = workTime;
        }

        public void serialize(JsonObject json)
        {
            if (!inputFluid.hasNoMatchingFluids())
            {
                json.add("fluid_ingredient", inputFluid.serialize());
            }
            if (!inputItem.hasNoMatchingItems())
            {
                json.add("item_ingredient", inputItem.serialize());
            }

            JsonArray jsonarray = new JsonArray();
            for (ItemStack itemStack : this.outputItems)
            {
                JsonObject jsonItem = new JsonObject();
                jsonItem.addProperty("item", ForgeRegistries.ITEMS.getKey(itemStack.getItem()).toString());
                if (itemStack.getCount() > 1)
                {
                    jsonItem.addProperty("count", itemStack.getCount());
                }
                jsonarray.add(jsonItem);
            }
            json.add("output_items", jsonarray);

            if (!outputFluid.isEmpty())
            {
                JsonObject jsonItem = new JsonObject();
                jsonItem.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(this.outputFluid.getFluid()).toString());
                jsonItem.addProperty("amount", this.outputFluid.getAmount());
                json.add("output_fluid", jsonItem);
            }

            json.addProperty("work_time", this.workTime);
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
