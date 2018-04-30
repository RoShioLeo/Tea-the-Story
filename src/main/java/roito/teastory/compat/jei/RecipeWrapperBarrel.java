package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import roito.teastory.recipe.ITeaMakingRecipe;

public class RecipeWrapperBarrel implements IRecipeWrapperFactory<ITeaMakingRecipe>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(ITeaMakingRecipe recipe)
	{
		return new RecipeBarrel(recipe);
	}
}

