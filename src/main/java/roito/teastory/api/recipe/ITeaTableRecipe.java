package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ITeaTableRecipe
{
    NonNullList<ItemStack> getTeaLeafInput();

    NonNullList<ItemStack> getToolInput();

    NonNullList<ItemStack> getSugarInput();

    ItemStack getCupInput();

    ItemStack getOutput();

    boolean isTheSameInput(ItemStack leaf, ItemStack tool, ItemStack sugar, ItemStack cup);
}
