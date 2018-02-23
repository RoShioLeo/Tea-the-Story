package cateam.teastory.recipe;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;

public class TeaStoveRecipeManger implements IRecipeManager<ITeaStoveRecipe>
{
	@Override
	public boolean equal(ITeaStoveRecipe recipe1, ITeaStoveRecipe recipe2)
	{
		return (recipe1.getInput() == recipe2.getInput());
	}

	@Override
	public void add(ITeaStoveRecipe recipe)
	{
		recipes.add(recipe);
	}

	@Override
	public void remove(ITeaStoveRecipe recipe)
	{
		java.util.Iterator<ITeaStoveRecipe> iter = recipes.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(recipe))
			{
				iter.remove();
				return;
			}
		}
	}
	
	@Override
	public Collection<ITeaStoveRecipe> getRecipes()
	{
		return recipes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> ITeaStoveRecipe getRecipe(T... inputs)
	{
		if (inputs[0] == null || !(inputs[0] instanceof ItemStack))
			return null;
		for (ITeaStoveRecipe r : recipes)
		{
			if (r.getInput().isItemEqual((ItemStack)inputs[0]) && r.getInput().stackSize <= ((ItemStack)inputs[0]).stackSize)
				return r;
		}
		return null;
	}
	
	private static ArrayList<ITeaStoveRecipe> recipes = new ArrayList<>();

}
