package roito.teastory.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class CategoryBarrel implements IRecipeCategory<IRecipeWrapper>
{

	protected final IDrawable background;
	
	public CategoryBarrel(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_recipe.png");
		background = helper.createDrawable(backgroundTexture, 5, 32, 166, 22);
	}
	
	@Override
	public String getUid()
	{
		return "teastory.barrel";
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.teastory.category.barrel");
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
		items.set(0, ((RecipeBarrel)recipeWrapper).getStep1());
		items.init(1, true, 38, 2);
		items.set(1, ((RecipeBarrel)recipeWrapper).getStep2());
		items.init(2, true, 74, 2);
		items.set(2, ((RecipeBarrel)recipeWrapper).getStep3());
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
