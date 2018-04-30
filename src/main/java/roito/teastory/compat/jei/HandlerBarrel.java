package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerBarrel implements IRecipeHandler<RecipeBarrel>
{
	@Override
	public Class<RecipeBarrel> getRecipeClass()
	{
		return RecipeBarrel.class;
	}

	public String getRecipeCategoryUid()
	{
		return "teastory.barrel";
	}

	@Override
	public String getRecipeCategoryUid(RecipeBarrel recipe)
	{
		return getRecipeCategoryUid();
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeBarrel recipe)
	{
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeBarrel recipe)
	{
		return recipe.getInputs() != null && recipe.getOutputs() != null;
	}
}

