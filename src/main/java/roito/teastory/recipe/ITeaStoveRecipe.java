package roito.teastory.recipe;

import net.minecraft.item.ItemStack;

public interface ITeaStoveRecipe
{
    ItemStack getInput();

    ItemStack getOutput();

    Boolean NeedWater();
}
