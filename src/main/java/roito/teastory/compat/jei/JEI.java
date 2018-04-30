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
import roito.teastory.block.BlockLoader;
import roito.teastory.client.gui.GuiContainerTeaStove;
import roito.teastory.client.gui.GuiContainerTeaTable;
import roito.teastory.item.ItemLoader;
import roito.teastory.recipe.*;

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
		
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.tea_stove), "teastory.teastove");
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.tea_table), "teastory.teatable");
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.tea_drying_pan), "teastory.dryingpan");
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.teapan), "teastory.teapan");
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.barrel), "teastory.barrel");
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.tea_drying_pan), "teastory.cookingpan");
		
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
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lit_cooking_pan));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lit_tea_stove));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lit_tea_drying_pan));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.black_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.green_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matcha_drink_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milk_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemon_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellow_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.white_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolong_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puer_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.field));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.xian_rice_plant));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.xian_rice_seedling));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.teaplant));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.straw_blanket));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemLoader.cup, 1, 1));
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
		
	}
}
