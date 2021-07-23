package cloud.lemonslice.teastory.common.recipe.bamboo_tray;

import cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry;
import cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class BambooTrayIndoorsRecipe extends BambooTraySingleInRecipe
{
    public BambooTrayIndoorsRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, int workTime)
    {
        super(idIn, groupIn, ingredientIn, resultIn, workTime);
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return RecipeSerializerRegistry.BAMBOO_TRAY_INDOORS.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return NormalRecipeTypes.BT_INDOORS;
    }
}
