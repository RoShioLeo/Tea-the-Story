package roito.teastory.compat.jei;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import roito.teastory.block.BlockRegister;
import roito.teastory.client.gui.GuiContainerTeaStove;
import roito.teastory.client.gui.GuiContainerTeaTable;
import roito.teastory.item.ItemRegister;
import roito.teastory.recipe.*;

@JEIPlugin
public class JEICompatRegister implements IModPlugin
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
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(
				new CategoryTeaStove(registry.getJeiHelpers().getGuiHelper()),
				new CategoryTeaTable(registry.getJeiHelpers().getGuiHelper()),
				new CategoryDryingPan(registry.getJeiHelpers().getGuiHelper()),
				new CategoryTeapan(registry.getJeiHelpers().getGuiHelper()),
				new CategoryBarrel(registry.getJeiHelpers().getGuiHelper()),
				new CategoryCookingPan(registry.getJeiHelpers().getGuiHelper())
		);
	}

	@Override
	public void register(IModRegistry registry)
	{
		addIngredientToBlacklist(registry);
		
		registry.handleRecipes(ITeaStoveRecipe.class, new RecipeWrapperTeaStove(), "teastory.teastove");
		registry.handleRecipes(ITeaTableRecipe.class, new RecipeWrapperTeaTable(), "teastory.teatable");
		registry.handleRecipes(ITeaMakingRecipe.class, new RecipeWrapperDryingPan(), "teastory.dryingpan");
		registry.handleRecipes(ITeaMakingRecipe.class, new RecipeWrapperTeapan(), "teastory.teapan");
		registry.handleRecipes(ITeaMakingRecipe.class, new RecipeWrapperBarrel(), "teastory.barrel");
		registry.handleRecipes(ITeaMakingRecipe.class, new RecipeWrapperCookingPan(), "teastory.cookingpan");
		
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.tea_stove), "teastory.teastove");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.tea_table), "teastory.teatable");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.tea_drying_pan), "teastory.dryingpan");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.teapan), "teastory.teapan");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.barrel), "teastory.barrel");
		registry.addRecipeCatalyst(new ItemStack(BlockRegister.tea_drying_pan), "teastory.cookingpan");
		
		registry.addRecipes(RecipeTeaStove.getWrappedRecipeList(), "teastory.teastove");
		registry.addRecipes(RecipeTeaTable.getWrappedRecipeList(), "teastory.teatable");
		registry.addRecipes(RecipeDryingPan.getWrappedRecipeList(), "teastory.dryingpan");
		registry.addRecipes(RecipeTeapan.getWrappedRecipeList(), "teastory.teapan");
		registry.addRecipes(RecipeBarrel.getWrappedRecipeList(), "teastory.barrel");
		registry.addRecipes(RecipeCookingPan.getWrappedRecipeList(), "teastory.cookingpan");
		
		registry.addRecipeClickArea(GuiContainerTeaStove.class, 78, 38, 24, 17, "teastory.teastove");
		registry.addRecipeClickArea(GuiContainerTeaTable.class, 85, 33, 24, 17, "teastory.teatable");
	}
	
	public static void addIngredientToBlacklist(IModRegistry registry)
	{
		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.blacktea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.blacktea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.blacktea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.blacktea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.blacktea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.greentea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.greentea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.greentea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.greentea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.greentea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.matchadrink_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.matchadrink_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.matchadrink_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.matchadrink_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.matchadrink_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.milktea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.milktea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.milktea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.milktea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.milktea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lemontea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lemontea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lemontea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lemontea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lemontea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.yellowtea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.yellowtea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.yellowtea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.yellowtea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.yellowtea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.whitetea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.whitetea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.whitetea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.whitetea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.whitetea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.oolongtea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.oolongtea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.oolongtea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.oolongtea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.oolongtea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.puertea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.puertea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.puertea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.puertea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.puertea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lit_cooking_pan));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lit_tea_stove));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.lit_tea_drying_pan));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.field));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.paddy_field));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.xian_rice_plant));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.xian_rice_seedling));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.teaplant));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockRegister.straw_blanket));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemRegister.cup, 1, 1));
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
		
	}
}
