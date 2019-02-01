package roito.teastory.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class CategoryTeaTable implements IRecipeCategory<IRecipeWrapper>
{
	protected final IDrawable background;
	protected final IDrawableAnimated progressBar;

	public CategoryTeaTable(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_tea_table.png");
		background = helper.createDrawable(backgroundTexture, 41, 17, 94, 50);
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
		return I18n.format("jei.teastory.category.teatable");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft)
	{
		progressBar.draw(minecraft, 44, 16);
	}

	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		items.init(0, true, 20, 24);
		items.set(0, ((RecipeTeaTable) recipeWrapper).getTeaLeafInputs());
		items.init(1, true, 20, 6);
		items.set(1, ((RecipeTeaTable) recipeWrapper).getSugarInputs());
		items.init(2, true, 74, 2);
		items.set(2, ((RecipeTeaTable) recipeWrapper).getCupInputs());
		items.init(3, true, 2, 6);
		items.set(3, ((RecipeTeaTable) recipeWrapper).getToolInputs());
		items.init(4, true, 2, 24);
		items.set(4, RecipeTeaTable.getPotInputs());
		items.init(5, false, 74, 30);
		items.set(5, ((RecipeTeaTable) recipeWrapper).getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		setRecipe(recipeLayout, recipeWrapper);
	}

	@Override
	public String getModName()
	{
		return "TeaStory";
	}

}
