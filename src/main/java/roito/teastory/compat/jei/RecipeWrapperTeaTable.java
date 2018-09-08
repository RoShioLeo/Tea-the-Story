package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import roito.teastory.recipe.ITeaTableRecipe;

public class RecipeWrapperTeaTable implements IRecipeWrapperFactory<ITeaTableRecipe>
{
    @Override
    public IRecipeWrapper getRecipeWrapper(ITeaTableRecipe recipe)
    {
        return new RecipeTeaTable(recipe);
    }
}

