package roito.teastory.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import roito.teastory.recipe.ITeaMakingRecipe;
import roito.teastory.recipe.RecipeRegister;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeBarrel implements IRecipeWrapper
{

    public static List<RecipeBarrel> getWrappedRecipeList()
    {
        List<RecipeBarrel> recipesToReturn = new ArrayList<>();
        for (ITeaMakingRecipe recipe : RecipeRegister.managerBarrel.getRecipes())
        {
            recipesToReturn.add(new RecipeBarrel(recipe));
        }
        return recipesToReturn;
    }

    private final ITeaMakingRecipe recipe;

    public RecipeBarrel(ITeaMakingRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutputs(ItemStack.class, getOutputs());
    }

    public List<ItemStack> getInputs()
    {
        List<ItemStack> list = new ArrayList<>();
        list.add(recipe.getStep1());
        list.add(recipe.getStep2());
        return list;
    }

    public List<ItemStack> getOutputs()
    {
        List<ItemStack> list = new ArrayList<>();
        list.add(recipe.getStep2());
        list.add(recipe.getStep3());
        return list;
    }

    public List<ItemStack> getStep1()
    {
        return Collections.singletonList(recipe.getStep1());
    }

    public List<ItemStack> getStep2()
    {
        return Collections.singletonList(recipe.getStep2());
    }

    public List<ItemStack> getStep3()
    {
        return Collections.singletonList(recipe.getStep3());
    }
}
