package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.helper.NonNullListHelper;

import java.util.ArrayList;
import java.util.Collection;

public class TeaTableRecipeManager implements ITeaTableRecipeManager<ITeaTableRecipe>
{
    @Override
    public boolean equal(ITeaTableRecipe recipe1, ITeaTableRecipe recipe2)
    {
        return recipe1.equals(recipe2);
    }

    @Override
    public void add(ITeaTableRecipe recipe)
    {
        recipes.add(recipe);
    }

    @Override
    public void remove(ITeaTableRecipe recipe)
    {
        java.util.Iterator<ITeaTableRecipe> iter = recipes.iterator();
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
    public void remove(NonNullList<ItemStack> teaLeaves, NonNullList<ItemStack> tools, ItemStack cup, NonNullList<ItemStack> sugars, ItemStack output)
    {
        ITeaTableRecipe recipe = getRecipe(teaLeaves, tools, cup, sugars);
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
    public Collection<ITeaTableRecipe> getRecipes()
    {
        return recipes;
    }

    @Override
    public ITeaTableRecipe getRecipe(ItemStack teaLeaf, ItemStack tool, ItemStack cup, ItemStack sugar)
    {
        for (ITeaTableRecipe recipe : recipes)
        {
            if (recipe.isTheSameInput(teaLeaf, tool, cup, sugar))
            {
                return recipe;
            }
        }
        return new TeaTableRecipe(NonNullListHelper.createNonNullList(teaLeaf), NonNullListHelper.createNonNullList(tool), cup, NonNullListHelper.createNonNullList(sugar), ItemStack.EMPTY);
    }

    @Override
    public ITeaTableRecipe getRecipe(NonNullList<ItemStack> teaLeaves, NonNullList<ItemStack> tools, ItemStack cup, NonNullList<ItemStack> sugars)
    {
        for (ItemStack teaLeaf : teaLeaves)
        {
            for (ItemStack tool : tools)
            {
                for (ItemStack sugar : sugars)
                {
                    ITeaTableRecipe recipe = getRecipe(teaLeaf, tool, cup, sugar);
                    if (!recipe.getOutput().isEmpty())
                    {
                        return recipe;
                    }
                }
            }
        }
        return new TeaTableRecipe(teaLeaves, tools, cup, sugars, ItemStack.EMPTY);
    }

    private static ArrayList<ITeaTableRecipe> recipes = new ArrayList<>();

}
