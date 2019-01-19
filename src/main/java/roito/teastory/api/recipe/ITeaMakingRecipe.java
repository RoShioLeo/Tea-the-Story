package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ITeaMakingRecipe
{
	NonNullList<ItemStack> getInputs();

	ItemStack getOutput();

	boolean isTheSameInput(ItemStack input);
}
