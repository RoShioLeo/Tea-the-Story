package cateam.teastory.common.jei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import cateam.teastory.item.ItemLoader;
import cateam.teastory.recipe.IRecipeManager;
import cateam.teastory.recipe.ITeaStoveRecipe;
import cateam.teastory.recipe.RecipeLoader;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeTeaStove implements IRecipeWrapper
{
	
	public static List<RecipeTeaStove> getWrappedRecipeList()
	{
		List<RecipeTeaStove> recipesToReturn = new ArrayList<>();
		for (ITeaStoveRecipe recipe : RecipeLoader.managerTS.getRecipes()) {
			recipesToReturn.add(new RecipeTeaStove(recipe));
		}
		return recipesToReturn;
	}
	
	private final ITeaStoveRecipe recipe;
	
	public RecipeTeaStove(ITeaStoveRecipe recipe)
	{
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInput(ItemStack.class, recipe.getInput());
		ingredients.setOutput(ItemStack.class, recipe.getOutput());
	}

	@Override
	public List<ItemStack> getInputs()
	{
		return Collections.singletonList(recipe.getInput());
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
