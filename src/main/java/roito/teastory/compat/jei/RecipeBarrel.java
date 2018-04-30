package roito.teastory.compat.jei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import roito.teastory.recipe.ITeaMakingRecipe;
import roito.teastory.recipe.RecipeLoader;

public class RecipeBarrel implements IRecipeWrapper
{
	
	public static List<RecipeBarrel> getWrappedRecipeList()
	{
		List<RecipeBarrel> recipesToReturn = new ArrayList<>();
		for (ITeaMakingRecipe recipe : RecipeLoader.managerBarrel.getRecipes())
		{
			recipesToReturn.add(new RecipeBarrel(recipe));
		}
		return recipesToReturn;
	}
	
	private final ITeaMakingRecipe recipe;
	
	public RecipeBarrel(ITeaMakingRecipe recipe)
	{
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInput(ItemStack.class, recipe.getStep1());
		ingredients.setOutput(ItemStack.class, recipe.getStep2());
	}

	public List<ItemStack> getInputs()
	{
		return Collections.singletonList(recipe.getStep1());
	}

	public List<ItemStack> getOutputs()
	{
		return Collections.singletonList(recipe.getStep2());
	}
}
