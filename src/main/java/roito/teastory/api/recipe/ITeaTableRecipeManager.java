package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Collection;

public interface ITeaTableRecipeManager<R>
{
    boolean equal(R recipe1, R recipe2);

    void add(R recipe);

    void remove(R recipe);

    void remove(NonNullList<ItemStack> teaLeaves, NonNullList<ItemStack> tools, ItemStack cup, NonNullList<ItemStack> sugars, ItemStack output);

    void removeAll();

    Collection<R> getRecipes();

    R getRecipe(ItemStack teaLeaf, ItemStack tool, ItemStack cup, ItemStack sugar);

    R getRecipe(NonNullList<ItemStack> teaLeaf, NonNullList<ItemStack> tool, ItemStack cup, NonNullList<ItemStack> sugar);
}
