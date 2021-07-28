package cloud.lemonslice.teastory.common.recipe.serializer;

import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneRollerRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class StoneRollerRecipeSerializer<T extends StoneRollerRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>
{
    private final int workTime;
    private final StoneRollerRecipeSerializer.IFactory<T> factory;

    public StoneRollerRecipeSerializer(StoneRollerRecipeSerializer.IFactory<T> factory, int timeIn)
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

        if (!json.has("output_items"))
            throw new JsonSyntaxException("Missing output, expected to find a string or object");

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
        return this.factory.create(recipeId, group, inputItem, outputItems, i);
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

        int i = buffer.readVarInt();
        NonNullList<ItemStack> outputItems = NonNullList.withSize(i, ItemStack.EMPTY);
        for (int j = 0; j < i; ++j)
        {
            outputItems.set(j, buffer.readItemStack());
        }

        int workTime = buffer.readVarInt();

        return this.factory.create(recipeId, groupIn, inputItem, outputItems, workTime);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe)
    {
        buffer.writeString(recipe.getGroup());
        recipe.getInputItem().write(buffer);

        buffer.writeVarInt(recipe.getOutputItems().size());
        for (ItemStack ingredient : recipe.getOutputItems())
        {
            buffer.writeItemStack(ingredient);
        }

        buffer.writeVarInt(recipe.getWorkTime());
    }

    interface IFactory<T extends StoneRollerRecipe>
    {
        T create(ResourceLocation idIn, String groupIn, Ingredient inputItem, NonNullList<ItemStack> outputItems, int workTime);
    }
}
