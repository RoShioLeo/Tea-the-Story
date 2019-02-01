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

public class CategoryTeaStoveBake implements IRecipeCategory<IRecipeWrapper>
{

	protected final IDrawable background;
	protected final IDrawableAnimated fireOverlay;
	protected final IDrawableAnimated progressBar;

	public CategoryTeaStoveBake(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_tea_stove.png");
		background = helper.createDrawable(backgroundTexture, 31, 17, 108, 59);
		IDrawableStatic fire = helper.createDrawable(backgroundTexture, 176, 0, 14, 14);
		fireOverlay = helper.createAnimatedDrawable(fire, 300, StartDirection.TOP, true);
		IDrawableStatic progressBarOverlay = helper.createDrawable(backgroundTexture, 176, 14, 24, 17);
		progressBar = helper.createAnimatedDrawable(progressBarOverlay, 300, StartDirection.LEFT, false);
	}

	@Override
	public String getUid()
	{
		return "teastory.teastove.bake";
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.teastory.category.teastove.bake");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft)
	{
		fireOverlay.draw(minecraft, 22, 22);
		progressBar.draw(minecraft, 47, 21);
	}

	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		items.init(0, true, 21, 2);
		items.set(0, ((RecipeTeaStoveBake) recipeWrapper).getInputs());
		items.init(1, false, 84, 21);
		items.set(1, ((RecipeTeaStoveBake) recipeWrapper).getOutputs());
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
