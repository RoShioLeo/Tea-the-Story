package roito.teastory.recipe;

import net.minecraft.item.ItemStack;

public interface ITeaMakingRecipe
{
	ItemStack getStep1();
	
	ItemStack getStep2();
	
	ItemStack getStep3();
	
	ItemStack getStep4();

	ItemStack getStep5();
}
