package roito.teastory.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ITeaMakingRecipe
{
	ItemStack getStep1();
	
	ItemStack getStep2();
	
	ItemStack getStep3();
	
	ItemStack getStep4();

	ItemStack getStep5();
}
