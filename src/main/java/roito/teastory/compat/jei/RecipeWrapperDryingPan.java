package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import roito.teastory.api.recipe.ITeaMakingRecipe;

public class RecipeWrapperDryingPan implements IRecipeWrapperFactory<ITeaMakingRecipe>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(ITeaMakingRecipe recipe)
	{
		return new RecipeDryingPan(recipe);
	}
}

