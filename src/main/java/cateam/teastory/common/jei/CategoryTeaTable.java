package cateam.teastory.common.jei;

import java.util.ArrayList;
import java.util.List;

import cateam.teastory.TeaStory;
import cateam.teastory.item.ItemLoader;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CategoryTeaTable extends BlankRecipeCategory<IRecipeWrapper>
{
	protected final IDrawable background;
	protected final IDrawableAnimated progressBar;
	
	public CategoryTeaTable(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_tea_table.png");
		background = helper.createDrawable(backgroundTexture, 3, 5, 169, 70, 23, 88, 0, 0);
		IDrawableStatic progressBarOverlay = helper.createDrawable(backgroundTexture, 176, 0, 24, 17);
		progressBar = helper.createAnimatedDrawable(progressBarOverlay, 300, StartDirection.LEFT, false);
	}

	@Override
	public String getUid()
	{
		return "teastory.teatable";
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.category.teatable");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft)
	{
		progressBar.draw(minecraft, 82, 51);
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		items.init(0, true, 58, 59);
		items.set(0, ((RecipeTeaTable)recipeWrapper).getTeaLeafInputs());
		items.init(1, true, 58, 41);
		items.set(1, ((RecipeTeaTable)recipeWrapper).getSugarInputs());
		items.init(2, true, 112, 37);
		items.set(2, ((RecipeTeaTable)recipeWrapper).getCupInputs());
		items.init(3, true, 40, 41);
		items.set(3, ((RecipeTeaTable)recipeWrapper).getToolInputs());
		items.init(4, true, 40, 59);
		items.set(4, ((RecipeTeaTable)recipeWrapper).getPotInputs());
		items.init(5, false, 112, 65);
		items.set(5, recipeWrapper.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		setRecipe(recipeLayout, recipeWrapper);
	}
	
}
