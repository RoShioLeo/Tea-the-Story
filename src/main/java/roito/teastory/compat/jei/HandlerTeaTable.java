package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerTeaTable implements IRecipeHandler<RecipeTeaTable>
{
	@Override
	public Class<RecipeTeaTable> getRecipeClass()
	{
		return RecipeTeaTable.class;
	}

	@Override
	public String getRecipeCategoryUid()
	{
		return "teastory.teatable";
	}

	@Override
	public String getRecipeCategoryUid(RecipeTeaTable recipe)
	{
		return getRecipeCategoryUid();
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeTeaTable recipe)
	{
		return recipe;
	}

	@Override
	public boolean isRecipeValid(RecipeTeaTable recipe)
	{
		return recipe.getInputs() != null && recipe.getOutputs() != null;
	}
}

