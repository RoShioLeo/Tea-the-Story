package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.helper.NonNullListHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

public class TeaStoveDryingRecipeManager implements IRecipeManager<ITeaMakingRecipe>
{
    @Override
    public boolean equal(ITeaMakingRecipe recipe1, ITeaMakingRecipe recipe2)
    {
        return recipe1.equals(recipe2);
    }

    @Override
    public void add(ITeaMakingRecipe recipe)
    {
        recipes.add(recipe);
    }

    @Override
    public void remove(ITeaMakingRecipe recipe)
    {
        java.util.Iterator<ITeaMakingRecipe> iter = recipes.iterator();
        while (iter.hasNext())
        {
            if (iter.next().equals(recipe))
            {
                iter.remove();
                return;
            }
        }
    }

    @Override
    public void remove(NonNullList<ItemStack> listIn, ItemStack output)
    {
        ITeaMakingRecipe recipe = getRecipe(listIn);
        if (OreDictionary.itemMatches(output, recipe.getOutput(), false))
        {
            remove(recipe);
        }
    }

    @Override
    public void removeAll()
    {
        recipes.clear();
    }

    @Override
    public Collection getRecipes()
    {
        return recipes;
    }

    @Nonnull
    @Override
    public ITeaMakingRecipe getRecipe(@Nonnull ItemStack input)
    {
        for (ITeaMakingRecipe recipe : recipes)
        {
            if (recipe.isTheSameInput(input))
            {
                return recipe;
            }
        }
        return new TeaMakingRecipe(NonNullListHelper.createNonNullList(input), ItemStack.EMPTY);
    }

    @Nonnull
    @Override
    public ITeaMakingRecipe getRecipe(@Nonnull NonNullList<ItemStack> inputs)
    {
        for (ItemStack input : inputs)
        {
            ITeaMakingRecipe recipe = getRecipe(input);
            if (!recipe.getOutput().isEmpty())
            {
                return recipe;
            }
        }
        return new TeaMakingRecipe(inputs, ItemStack.EMPTY);
    }

    private static ArrayList<ITeaMakingRecipe> recipes = new ArrayList<>();
}
