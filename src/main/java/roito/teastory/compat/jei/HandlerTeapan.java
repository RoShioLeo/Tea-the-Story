package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerTeapan implements IRecipeHandler<RecipeTeapan>
{
	@Override
	public Class<RecipeTeapan> getRecipeClass()
	{
		return RecipeTeapan.class;
	}

	public String getRecipeCategoryUid()
	{
		return "teastory.teapan";
	}

	@Override
	public String getRecipeCategoryUid(RecipeTeapan recipe)
	{
		return getRecipeCategoryUid();
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeTeapan recipe)
	{
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeTeapan recipe)
	{
		return recipe.getInputs() != null && recipe.getOutputs() != null;
	}
}

