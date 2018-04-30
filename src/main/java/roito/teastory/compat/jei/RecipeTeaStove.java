package roito.teastory.compat.jei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import roito.teastory.recipe.ITeaStoveRecipe;
import roito.teastory.recipe.RecipeLoader;

public class RecipeTeaStove implements IRecipeWrapper
{
	
	public static List<RecipeTeaStove> getWrappedRecipeList()
	{
		List<RecipeTeaStove> recipesToReturn = new ArrayList<>();
		for (ITeaStoveRecipe recipe : RecipeLoader.managerTeaStove.getRecipes())
		{
			recipesToReturn.add(new RecipeTeaStove(recipe));
		}
		return recipesToReturn;
	}
	
	private final ITeaStoveRecipe recipe;
	
	public RecipeTeaStove(ITeaStoveRecipe recipe)
	{
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInput(ItemStack.class, recipe.getInput());
		ingredients.setOutput(ItemStack.class, recipe.getOutput());
	}

	public List<ItemStack> getInputs()
	{
		return Collections.singletonList(recipe.getInput());
	}

	public List<ItemStack> getOutputs()
	{
		return Collections.singletonList(recipe.getOutput());
	}
}
