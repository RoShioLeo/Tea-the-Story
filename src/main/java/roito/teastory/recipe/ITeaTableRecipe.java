package roito.teastory.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ITeaTableRecipe
{
	ItemStack getTeaLeafInput();
	
	List<ItemStack> getToolInput();
	
	List<ItemStack> getSugarInput();
	
	ItemStack getCupInput();

	ItemStack getOutput();
}
