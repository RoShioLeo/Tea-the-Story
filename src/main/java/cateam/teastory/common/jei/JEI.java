package cateam.teastory.common.jei;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.client.gui.GuiContainerTeaStove;
import cateam.teastory.client.gui.GuiContainerTeaTable;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEI implements IModPlugin
{

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
	{
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry)
	{
		
	}

	@Override
	public void register(IModRegistry registry)
	{
		registry.addRecipeHandlers(new HandlerTeaStove());
		registry.addRecipeCategories(new CategoryTeaStove(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategoryCraftingItem(new ItemStack(BlockLoader.tea_stove), "teastory.teastove");
		registry.addRecipes(RecipeTeaStove.getWrappedRecipeList());
		registry.addRecipeClickArea(GuiContainerTeaStove.class, 78, 38, 24, 17, "teastory.teastove");
		
		registry.addRecipeHandlers(new HandlerTeaTable());
		registry.addRecipeCategories(new CategoryTeaTable(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategoryCraftingItem(new ItemStack(BlockLoader.tea_table), "teastory.teatable");
		registry.addRecipes(RecipeTeaTable.getWrappedRecipeList());
		registry.addRecipeClickArea(GuiContainerTeaTable.class, 85, 33, 24, 17, "teastory.teatable");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
		
	}
}
