package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerTeaStove implements IRecipeHandler<RecipeTeaStove>
{
	@Override
	public Class<RecipeTeaStove> getRecipeClass()
	{
		return RecipeTeaStove.class;
	}

	public String getRecipeCategoryUid()
	{
		return "teastory.teastove";
	}

	@Override
	public String getRecipeCategoryUid(RecipeTeaStove recipe)
	{
		return getRecipeCategoryUid();
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeTeaStove recipe)
	{
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeTeaStove recipe)
	{
		return recipe.getInputs() != null && recipe.getOutputs() != null;
	}
}
