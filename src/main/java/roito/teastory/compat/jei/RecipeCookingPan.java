package roito.teastory.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import roito.teastory.recipe.ITeaMakingRecipe;
import roito.teastory.recipe.RecipeRegister;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeCookingPan implements IRecipeWrapper
{

    public static List<RecipeCookingPan> getWrappedRecipeList()
    {
        List<RecipeCookingPan> recipesToReturn = new ArrayList<>();
        for (ITeaMakingRecipe recipe : RecipeRegister.managerCookingPan.getRecipes())
        {
            recipesToReturn.add(new RecipeCookingPan(recipe));
        }
        return recipesToReturn;
    }

    private final ITeaMakingRecipe recipe;

    public RecipeCookingPan(ITeaMakingRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutput(ItemStack.class, recipe.getStep5());
    }

    public List<ItemStack> getInputs()
    {
        List<ItemStack> list = new ArrayList<>();
        list.add(recipe.getStep1());
        list.add(recipe.getStep2());
        list.add(recipe.getStep3());
        list.add(recipe.getStep4());
        return list;
    }

    public List<ItemStack> getOutputs()
    {
        return Collections.singletonList(recipe.getStep5());
    }

    public List<ItemStack> getStep1Inputs()
    {
        return Collections.singletonList(recipe.getStep1());
    }

    public List<ItemStack> getStep2Inputs()
    {
        return Collections.singletonList(recipe.getStep2());
    }

    public List<ItemStack> getStep3Inputs()
    {
        return Collections.singletonList(recipe.getStep3());
    }

    public List<ItemStack> getStep4Inputs()
    {
        return Collections.singletonList(recipe.getStep4());
    }
}
