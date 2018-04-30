package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerCookingPan implements IRecipeHandler<RecipeCookingPan>
{
	@Override
	public Class<RecipeCookingPan> getRecipeClass()
	{
		return RecipeCookingPan.class;
	}

	public String getRecipeCategoryUid()
	{
		return "teastory.cookingpan";
	}

	@Override
	public String getRecipeCategoryUid(RecipeCookingPan recipe)
	{
		return getRecipeCategoryUid();
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeCookingPan recipe)
	{
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeCookingPan recipe)
	{
		return recipe.getInputs() != null && recipe.getOutputs() != null;
	}
}

