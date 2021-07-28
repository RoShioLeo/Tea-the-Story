package cloud.lemonslice.teastory.data.builder;

import cloud.lemonslice.teastory.common.recipe.serializer.StoneRollerRecipeSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry.STONE_ROLLER;

public class StoneRollerRecipeBuilder
{
    private final Ingredient inputItem;
    private final NonNullList<ItemStack> outputItems;
    private final int workTime;

    private StoneRollerRecipeBuilder(Ingredient inputItem, NonNullList<ItemStack> outputItems, int workTime)
    {
        this.inputItem = inputItem;
        this.outputItems = outputItems;
        this.workTime = workTime;
    }

    public static StoneRollerRecipeBuilder recipe(int workTime, Ingredient inputItem, ItemStack... outputItems)
    {
        return new StoneRollerRecipeBuilder(inputItem, NonNullList.from(ItemStack.EMPTY, outputItems), workTime);
    }

    public static StoneRollerRecipeBuilder recipeWithDefaultTime(Ingredient inputItem, ItemStack... outputItems)
    {
        return recipe(200, inputItem, outputItems);
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, String save)
    {
        ResourceLocation saveRes = new ResourceLocation(save);
        this.build(consumerIn, saveRes);
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id)
    {
        consumerIn.accept(new StoneRollerRecipeBuilder.Result(id, this.inputItem, this.outputItems, this.workTime));
    }

    public static class Result implements IFinishedRecipe
    {
        private final ResourceLocation id;
        protected final Ingredient inputItem;
        protected final NonNullList<ItemStack> outputItems;
        protected final int workTime;
        private final StoneRollerRecipeSerializer<?> serializer = STONE_ROLLER.get();

        public Result(ResourceLocation idIn, Ingredient inputItem, NonNullList<ItemStack> outputItems, int workTime)
        {
            this.id = new ResourceLocation(idIn.getNamespace(), "stone_roller/" + idIn.getPath());
            this.inputItem = inputItem;
            this.outputItems = outputItems;
            this.workTime = workTime;
        }

        public void serialize(JsonObject json)
        {
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
