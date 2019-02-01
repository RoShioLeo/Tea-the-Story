package roito.teastory.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class CategoryCookingPan implements IRecipeCategory<IRecipeWrapper>
{

	protected final IDrawable background;
	protected final IDrawableAnimated progressBar;

	public CategoryCookingPan(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_tea_recipe.png");
		background = helper.createDrawable(backgroundTexture, 50, 28, 76, 24);
		IDrawableStatic progressBarOverlay = helper.createDrawable(backgroundTexture, 176, 0, 24, 17);
		progressBar = helper.createAnimatedDrawable(progressBarOverlay, 300, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public String getUid()
	{
		return "teastory.cookingpan";
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.teastory.category.cookingpan");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		items.init(0, true, 2, 2);
		items.set(0, ((RecipeCookingPan) recipeWrapper).getInputs());
		items.init(1, false, 56, 2);
		items.set(1, ((RecipeCookingPan) recipeWrapper).getOutput());
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
