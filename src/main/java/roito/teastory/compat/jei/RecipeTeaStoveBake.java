package roito.teastory.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import roito.teastory.api.recipe.ITeaMakingRecipe;
import roito.teastory.common.RecipeRegister;
import roito.teastory.helper.NonNullListHelper;

import java.util.ArrayList;
import java.util.List;

public class RecipeTeaStoveBake implements IRecipeWrapper
{

    public static List<RecipeTeaStoveBake> getWrappedRecipeList()
    {
        List<RecipeTeaStoveBake> recipesToReturn = new ArrayList<>();
        for (ITeaMakingRecipe recipe : RecipeRegister.managerStoveDrying.getRecipes())
        {
            recipesToReturn.add(new RecipeTeaStoveBake(recipe));
        }
        return recipesToReturn;
    }

    private final ITeaMakingRecipe recipe;

    public RecipeTeaStoveBake(ITeaMakingRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
    }

    public NonNullList<ItemStack> getInputs()
    {
        return NonNullListHelper.createNonNullList(recipe.getInputs());
    }

    public NonNullList<ItemStack> getOutputs()
    {
        return NonNullListHelper.createNonNullList(recipe.getOutput());
    }
}
