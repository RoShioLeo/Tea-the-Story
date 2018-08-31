package roito.teastory.recipe;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ITeaTableRecipe
{
    ItemStack getTeaLeafInput();

    List<ItemStack> getToolInput();

    List<ItemStack> getSugarInput();

    ItemStack getCupInput();

    ItemStack getOutput();
}
