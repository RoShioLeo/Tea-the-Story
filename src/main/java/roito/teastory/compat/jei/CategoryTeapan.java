package roito.teastory.compat.jei;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class CategoryTeapan extends BlankRecipeCategory<IRecipeWrapper>
{

	protected final IDrawable background;
	
	public CategoryTeapan(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_recipe.png");
		background = helper.createDrawable(backgroundTexture, 3, 5, 169, 70, 23, 88, 0, 0);
	}
	
	@Override
	public String getUid()
	{
		return "teastory.teapan";
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.teastory.category.teapan");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		items.init(0, true, 4, 52);
		items.set(0, ((RecipeTeapan)recipeWrapper).getStep1());
		items.init(1, true, 40, 52);
		items.set(1, ((RecipeTeapan)recipeWrapper).getStep2());
		items.init(2, true, 76, 52);
		items.set(2, ((RecipeTeapan)recipeWrapper).getStep3());
		items.init(3, true, 112, 52);
		items.set(3, ((RecipeTeapan)recipeWrapper).getStep4());
		items.init(4, false, 148, 52);
		items.set(4, ((RecipeTeapan)recipeWrapper).getStep5());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		setRecipe(recipeLayout, recipeWrapper);
	}
}
