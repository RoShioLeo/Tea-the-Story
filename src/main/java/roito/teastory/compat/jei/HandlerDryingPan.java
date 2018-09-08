package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HandlerDryingPan implements IRecipeHandler<RecipeDryingPan>
{
    @Override
    public Class<RecipeDryingPan> getRecipeClass()
    {
        return RecipeDryingPan.class;
    }

    public String getRecipeCategoryUid()
    {
        return "teastory.dryingpan";
    }

    @Override
    public String getRecipeCategoryUid(RecipeDryingPan recipe)
    {
        return getRecipeCategoryUid();
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(RecipeDryingPan recipe)
    {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(RecipeDryingPan recipe)
    {
        return recipe.getInputs() != null && recipe.getOutputs() != null;
    }
}

