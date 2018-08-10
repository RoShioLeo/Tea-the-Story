package roito.teastory.recipe;

import java.util.Collections;

import javax.annotation.Nonnull;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.block.BlockLoader;
import roito.teastory.item.ItemLoader;

public class RecipeLoader
{
	@Nonnull
	public static IRecipeManager<ITeaStoveRecipe> managerTeaStove;
	public static IRecipeManager<ITeaTableRecipe> managerTeaTable;
	public static IRecipeManager<ITeaMakingRecipe> managerDryingPan;
	public static IRecipeManager<ITeaMakingRecipe> managerCookingPan;
	public static IRecipeManager<ITeaMakingRecipe> managerTeapan;
	public static IRecipeManager<ITeaMakingRecipe> managerBarrel;
	
	public RecipeLoader()
	{
		managerTeaStove = new TeaStoveRecipeManager();
		managerTeaTable = new TeaTableRecipeManager();
		managerDryingPan =new DryingPanRecipeManager();
		managerCookingPan = new CookingPanRecipeManager();
		managerTeapan = new TeapanRecipeManager();
		managerBarrel = new BarrelRecipeManager();
		
		addTeaStoveRecipe();
		addTeaTableRecipe();
		addDryingPanRecipe();
		addCookingPanRecipe();
		addTeapanRecipe();
		addBarrelRecipe();
	}

	private static void addTeaStoveRecipe()
	{
		managerTeaStove.add(new TeaStoveRecipe(new ItemStack(ItemLoader.tea_leaf), new ItemStack(ItemLoader.matcha_leaf), true));
		managerTeaStove.add(new TeaStoveRecipe(new ItemStack(ItemLoader.half_dried_tea), new ItemStack(ItemLoader.white_tea_leaf), false));
	}
	
	private static void addTeapanRecipe()
	{
		managerTeapan.add(new TeaMakingRecipe(new ItemStack(ItemLoader.wet_tea), new ItemStack(ItemLoader.tea_leaf), new ItemStack(ItemLoader.half_dried_tea), new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.yellow_tea_leaf)));
	}
	
	private static void addBarrelRecipe()
	{
		managerBarrel.add(new TeaMakingRecipe(new ItemStack(ItemLoader.half_dried_tea), new ItemStack(ItemLoader.black_tea_leaf), null, null, null));
	}
	
	private static void addCookingPanRecipe()
	{
		managerCookingPan.add(new TeaMakingRecipe(new ItemStack(ItemLoader.washed_rice, 8), new ItemStack(Items.WATER_BUCKET), new ItemStack(ItemLoader.wooden_lid), new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(ItemLoader.rice_ball, 2)));
	}
	
	private static void addDryingPanRecipe()
	{
		managerDryingPan.add(new TeaMakingRecipe(new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(ItemLoader.tea_leaf, 8), new ItemStack(ItemLoader.dried_tea, 8), null, null));
		managerDryingPan.add(new TeaMakingRecipe(new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(ItemLoader.half_dried_tea, 8), new ItemStack(ItemLoader.oolong_tea_leaf, 8), null, null));
	}
	
	private static void addTeaTableRecipe()
	{
		for (int i = 0; i <= 5; i++)
		{
			if(i != 1)
			{
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.black_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.black_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.green_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.green_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.dried_tea, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.green_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.milk_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 8), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.milk_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag), OreDictionary.getOres("cropLemon"), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.lemon_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 8), OreDictionary.getOres("cropLemon"), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.lemon_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.yellow_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.yellow_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.white_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.white_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.oolong_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.oolong_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.puer_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.puer_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.matcha_powder, 8), Collections.singletonList(new ItemStack(ItemLoader.tea_whisk)), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.matcha_drink, 1, i)));
			}
		}
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.black_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.black_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.green_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.green_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.dried_tea, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.green_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 4), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.milk_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 32), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.milk_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 4), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.lemon_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 32), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.lemon_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.yellow_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.yellow_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.white_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.white_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.oolong_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.oolong_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.puer_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.puer_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.matcha_powder, 32), Collections.singletonList(new ItemStack(ItemLoader.tea_whisk)), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.matcha_drink_porcelain_kettle)));
		
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.black_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.black_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.green_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.green_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.dried_tea, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.green_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 8), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.milk_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 64), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.milk_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 8), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.lemon_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 64), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.lemon_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.yellow_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.yellow_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.white_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.white_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.oolong_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.oolong_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.puer_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.puer_tea_zisha_kettle2)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemLoader.matcha_powder, 64), Collections.singletonList(new ItemStack(ItemLoader.tea_whisk)), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.matcha_drink_zisha_kettle2)));
	}
}
