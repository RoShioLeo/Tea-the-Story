package roito.teastory.compat.jei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import roito.teastory.item.ItemLoader;
import roito.teastory.recipe.IRecipeManager;
import roito.teastory.recipe.ITeaMakingRecipe;
import roito.teastory.recipe.RecipeLoader;

public class RecipeTeapan implements IRecipeWrapper
{
	
	public static List<RecipeTeapan> getWrappedRecipeList()
	{
		List<RecipeTeapan> recipesToReturn = new ArrayList<>();
		for (ITeaMakingRecipe recipe : RecipeLoader.managerTeapan.getRecipes())
		{
			recipesToReturn.add(new RecipeTeapan(recipe));
		}
		return recipesToReturn;
	}
	
	private final ITeaMakingRecipe recipe;
	
	public RecipeTeapan(ITeaMakingRecipe recipe)
	{
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInputs(ItemStack.class, getInputs());
		ingredients.setOutputs(ItemStack.class, getOutputs());
	}

	@Override
	public List<ItemStack> getInputs()
	{
		List<ItemStack> list = new ArrayList<>();
		list.add(recipe.getStep1());
		list.add(recipe.getStep2());
		list.add(recipe.getStep3());
		list.add(recipe.getStep4());
		return list;
	}

	@Override
	public List<ItemStack> getOutputs()
	{
		List<ItemStack> list = new ArrayList<>();
		list.add(recipe.getStep3());
		list.add(recipe.getStep4());
		list.add(recipe.getStep5());
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
	
	public List<ItemStack> getStep4()
	{
		return Collections.singletonList(recipe.getStep4());
	}
	
	public List<ItemStack> getStep5()
	{
		return Collections.singletonList(recipe.getStep5());
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
