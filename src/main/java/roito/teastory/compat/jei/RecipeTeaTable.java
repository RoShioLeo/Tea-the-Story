package roito.teastory.compat.jei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import roito.teastory.item.ItemLoader;
import roito.teastory.recipe.ITeaTableRecipe;
import roito.teastory.recipe.RecipeLoader;

public class RecipeTeaTable implements IRecipeWrapper
{
	
	public static List<RecipeTeaTable> getWrappedRecipeList()
	{
		List<RecipeTeaTable> recipesToReturn = new ArrayList<>();
		for (ITeaTableRecipe recipe : RecipeLoader.managerTeaTable.getRecipes())
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
		ingredients.setInputs(ItemStack.class, getInputs());
		ingredients.setOutput(ItemStack.class, recipe.getOutput());
	}
	
	public List<ItemStack> getSugarInputs()
	{
		if (recipe.getSugarInput() != null)
		{
			return Collections.singletonList(recipe.getSugarInput());
		}
		return Collections.EMPTY_LIST;
	}
	
	public List<ItemStack> getCupInputs()
	{
		return Collections.singletonList(recipe.getCupInput());
	}
	
	public List<ItemStack> getTeaLeafInputs()
	{
		return Collections.singletonList(recipe.getTeaLeafInput());
	}
	
	public List<ItemStack> getToolInputs()
	{
		if (recipe.getToolInput() != null)
		{
			List<ItemStack> list = new ArrayList<>();
			list.addAll(recipe.getToolInput());
			return list;
		}
		return Collections.EMPTY_LIST;
	}
	
	public static List<ItemStack> getPotInputs()
	{
		List<ItemStack> potList = new ArrayList<>();
		potList.add(new ItemStack(ItemLoader.hw_pot_stone));
		potList.add(new ItemStack(ItemLoader.hw_pot_iron));
		potList.add(new ItemStack(ItemLoader.hw_pot_porcelain));
		potList.add(new ItemStack(ItemLoader.hw_pot_zisha));
		return potList;
	}

	@Override
	public List<ItemStack> getInputs()
	{
		List<ItemStack> list = new ArrayList<>();
		list.add(recipe.getTeaLeafInput());
		list.add(recipe.getCupInput());
		if (recipe.getSugarInput() != null)
		{
			list.add(recipe.getSugarInput());
		}
		if (recipe.getToolInput() != null)
		{
			list.addAll(recipe.getToolInput());
		}
		list.addAll(getPotInputs());
		return list;
	}

	@Override
	public List<ItemStack> getOutputs()
	{
		return Collections.singletonList(recipe.getOutput());
	}

	@Override
	public List<FluidStack> getFluidInputs()
	{
		return null;
	}

	@Override
	public List<FluidStack> getFluidOutputs()
	{
		return null;
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
	{
		
	}

	@Override
	public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight)
	{
		
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY)
	{
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton)
	{
		return false;
	}

}
