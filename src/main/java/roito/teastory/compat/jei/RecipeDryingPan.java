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

public class RecipeDryingPan implements IRecipeWrapper
{

    public static List<RecipeDryingPan> getWrappedRecipeList()
    {
        List<RecipeDryingPan> recipesToReturn = new ArrayList<>();
        for (ITeaMakingRecipe recipe : RecipeRegister.managerDryingPan.getRecipes())
        {
            recipesToReturn.add(new RecipeDryingPan(recipe));
        }
        return recipesToReturn;
    }

    private final ITeaMakingRecipe recipe;

    public RecipeDryingPan(ITeaMakingRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(VanillaTypes.ITEM, getInputs());
        ingredients.setOutput(VanillaTypes.ITEM, getOutput());
    }

    public NonNullList<ItemStack> getInputs()
    {
        return NonNullListHelper.createNonNullList(recipe.getInputs());
    }

    public ItemStack getOutput()
    {
        return recipe.getOutput();
    }
}
