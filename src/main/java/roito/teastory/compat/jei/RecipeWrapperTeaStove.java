package roito.teastory.compat.jei;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import roito.teastory.recipe.ITeaStoveRecipe;

public class RecipeWrapperTeaStove implements IRecipeWrapperFactory<ITeaStoveRecipe>
{

    @Override
    public IRecipeWrapper getRecipeWrapper(ITeaStoveRecipe recipe)
    {
        return new RecipeTeaStove(recipe);
    }

}
