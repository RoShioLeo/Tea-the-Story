package roito.teastory.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class CategoryTeapanInRain implements IRecipeCategory<IRecipeWrapper>
{

	protected final IDrawable background;

	public CategoryTeapanInRain(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_tea_recipe.png");
		background = helper.createDrawable(backgroundTexture, 50, 28, 76, 24);
	}

	@Override
	public String getUid()
	{
		return "teastory.teapan.in_rain";
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.teastory.category.teapan.in_rain");
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
		items.set(0, ((RecipeTeapanInRain) recipeWrapper).getInputs());
		items.init(1, false, 56, 2);
		items.set(1, ((RecipeTeapanInRain) recipeWrapper).getOutputs());
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
