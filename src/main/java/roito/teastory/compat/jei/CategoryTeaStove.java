package roito.teastory.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class CategoryTeaStove extends BlankRecipeCategory<IRecipeWrapper>
{

	protected final IDrawable background;
	protected final IDrawableAnimated fireOverlay;
	protected final IDrawable steamBar;
	protected final IDrawable water;
	protected final IDrawableAnimated progressBar;
	
	public CategoryTeaStove(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_tea_stove.png");
		background = helper.createDrawable(backgroundTexture, 3, 5, 169, 70, 23, 88, 0, 0);
		IDrawableStatic fire = helper.createDrawable(backgroundTexture, 176, 0, 14, 14);
		fireOverlay = helper.createAnimatedDrawable(fire, 300, StartDirection.TOP, true);
		IDrawableStatic progressBarOverlay = helper.createDrawable(backgroundTexture, 176, 14, 24, 17);
		progressBar = helper.createAnimatedDrawable(progressBarOverlay, 300, StartDirection.LEFT, false);
		steamBar = helper.createDrawable(backgroundTexture, 176, 31, 12, 29);
		water = helper.createDrawable(backgroundTexture, 192, 60, 16, 16);
	}
	
	@Override
	public String getUid()
	{
		return "teastory.teastove";
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.teastory.category.teastove");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft)
	{
		fireOverlay.draw(minecraft, 50, 57);
		progressBar.draw(minecraft, 75, 56);
		steamBar.draw(minecraft, 32, 41);
		water.draw(minecraft, 31, 71);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		items.init(0, true, 49, 37);
		items.set(0, recipeWrapper.getInputs());
		items.init(1, false, 112, 56);
		items.set(1, recipeWrapper.getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		setRecipe(recipeLayout, recipeWrapper);
	}
}
