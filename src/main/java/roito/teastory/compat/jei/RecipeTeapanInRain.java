package roito.teastory.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import roito.teastory.api.recipe.ITeaMakingRecipe;
import roito.teastory.common.RecipeRegister;
import roito.teastory.helper.NonNullListHelper;

import java.util.ArrayList;
import java.util.List;

public class RecipeTeapanInRain implements IRecipeWrapper
{
	public static List<RecipeTeapanInRain> getWrappedRecipeList()
	{
		List<RecipeTeapanInRain> recipesToReturn = new ArrayList<>();
		for (ITeaMakingRecipe recipe : RecipeRegister.managerWet.getRecipes())
		{
			recipesToReturn.add(new RecipeTeapanInRain(recipe));
		}
		return recipesToReturn;
	}

	private final ITeaMakingRecipe recipe;

	public RecipeTeapanInRain(ITeaMakingRecipe recipe)
	{
		this.recipe = recipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInputs(VanillaTypes.ITEM, getInputs());
		ingredients.setOutputs(VanillaTypes.ITEM, getOutputs());
	}

	public NonNullList<ItemStack> getInputs()
	{
		return NonNullListHelper.createNonNullList(recipe.getInputs());
	}

	public NonNullList<ItemStack> getOutputs()
	{
		return NonNullListHelper.createNonNullList(recipe.getOutput());
	}
}
