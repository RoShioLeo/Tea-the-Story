package roito.teastory.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class CategoryBarrel extends BlankRecipeCategory<IRecipeWrapper>
{

	protected final IDrawable background;
	
	public CategoryBarrel(IGuiHelper helper)
	{
		ResourceLocation backgroundTexture = new ResourceLocation(TeaStory.MODID, "textures/gui/container/gui_recipe.png");
		background = helper.createDrawable(backgroundTexture, 3, 5, 169, 70, 23, 88, 0, 0);
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

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup items = recipeLayout.getItemStacks();
		items.init(0, true, 4, 52);
		items.set(0, ((RecipeBarrel)recipeWrapper).getInputs());
		items.init(1, false, 40, 52);
		items.set(1, ((RecipeBarrel)recipeWrapper).getOutputs());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		setRecipe(recipeLayout, recipeWrapper);
	}
}
