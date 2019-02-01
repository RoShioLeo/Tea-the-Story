package roito.teastory.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import roito.teastory.api.recipe.ITeaTableRecipe;
import roito.teastory.common.RecipeRegister;
import roito.teastory.helper.NonNullListHelper;
import roito.teastory.item.ItemRegister;

import java.util.ArrayList;
import java.util.List;

public class RecipeTeaTable implements IRecipeWrapper
{

	public static List<RecipeTeaTable> getWrappedRecipeList()
	{
		List<RecipeTeaTable> recipesToReturn = new ArrayList<>();
		for (ITeaTableRecipe recipe : RecipeRegister.managerTeaTable.getRecipes())
		{
			recipesToReturn.add(new RecipeTeaTable(recipe));
		}
		return recipesToReturn;
	}

	private final ITeaTableRecipe recipe;

	public RecipeTeaTable(ITeaTableRecipe recipe)
	{
		this.recipe = recipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInputs(VanillaTypes.ITEM, getInputs());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
	}

	public NonNullList<ItemStack> getSugarInputs()
	{
		NonNullList<ItemStack> list = NonNullListHelper.createNonNullList(recipe.getSugarInput());
		return list;
	}

	public NonNullList<ItemStack> getCupInputs()
	{
		return NonNullListHelper.createNonNullList(recipe.getCupInput());
	}

	public NonNullList<ItemStack> getTeaLeafInputs()
	{
		return NonNullListHelper.createNonNullList(recipe.getTeaLeafInput());
	}

	public NonNullList<ItemStack> getToolInputs()
	{
		NonNullList<ItemStack> list = NonNullListHelper.createNonNullList(recipe.getToolInput());
		return list;
	}

	public static NonNullList<ItemStack> getPotInputs()
	{
		NonNullList<ItemStack> potList = NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.hw_pot_stone));
		potList.add(new ItemStack(ItemRegister.hw_pot_stone));
		potList.add(new ItemStack(ItemRegister.hw_pot_iron));
		potList.add(new ItemStack(ItemRegister.hw_pot_porcelain));
		potList.add(new ItemStack(ItemRegister.hw_pot_zisha));
		return potList;
	}

	public NonNullList<ItemStack> getInputs()
	{
		NonNullList<ItemStack> list = NonNullListHelper.createNonNullList(recipe.getTeaLeafInput());
		list.add(recipe.getCupInput());
		list.addAll(recipe.getSugarInput());
		list.addAll(recipe.getToolInput());
		list.addAll(getPotInputs());
		return list;
	}

	public NonNullList<ItemStack> getOutputs()
	{
		return NonNullListHelper.createNonNullList(recipe.getOutput());
	}
}
