package roito.teastory.recipe;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;

public class TeaTableRecipeManager implements IRecipeManager<ITeaTableRecipe>
{
	@Override
	public boolean equal(ITeaTableRecipe recipe1, ITeaTableRecipe recipe2)
	{
		return (recipe1.getOutput() == recipe2.getOutput() && recipe1.getTeaLeafInput() == recipe2.getTeaLeafInput());
	}

	@Override
	public void add(ITeaTableRecipe recipe)
	{
		recipes.add(recipe);
	}

	@Override
	public void remove(ITeaTableRecipe recipe)
	{
		java.util.Iterator<ITeaTableRecipe> iter = recipes.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(recipe))
			{
				iter.remove();
				return;
			}
		}
	}
	
	@Override
	public Collection<ITeaTableRecipe> getRecipes()
	{
		return recipes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> ITeaTableRecipe getRecipe(T... inputs)
	{
		if (inputs[0] == null || !(inputs[0] instanceof ItemStack))
			return null;
		for (ITeaTableRecipe r : recipes)
		{
			if (r.getOutput().isItemEqual((ItemStack)inputs[0]) && r.getOutput().stackSize <= ((ItemStack)inputs[0]).stackSize)
				return r;
		}
		return null;
	}
	
	private static ArrayList<ITeaTableRecipe> recipes = new ArrayList<>();

}
