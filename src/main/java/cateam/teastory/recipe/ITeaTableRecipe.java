package cateam.teastory.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ITeaTableRecipe
{
	ItemStack getTeaLeafInput();
	
	List<ItemStack> getToolInput();
	
	ItemStack getSugarInput();
	
	ItemStack getCupInput();

	ItemStack getOutput();
}
