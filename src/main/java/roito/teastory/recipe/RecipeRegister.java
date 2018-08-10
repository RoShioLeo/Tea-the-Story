package roito.teastory.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.block.BlockRegister;
import roito.teastory.helper.NonNullListHelper;
import roito.teastory.item.ItemRegister;

public class RecipeRegister
{
	@Nonnull
	public static IRecipeManager<ITeaStoveRecipe> managerTeaStove;
	public static IRecipeManager<ITeaTableRecipe> managerTeaTable;
	public static IRecipeManager<ITeaMakingRecipe> managerDryingPan;
	public static IRecipeManager<ITeaMakingRecipe> managerCookingPan;
	public static IRecipeManager<ITeaMakingRecipe> managerTeapan;
	public static IRecipeManager<ITeaMakingRecipe> managerBarrel;
	
	public RecipeRegister()
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
		managerTeaStove.add(new TeaStoveRecipe(new ItemStack(ItemRegister.tea_leaf), new ItemStack(ItemRegister.matcha_leaf), true));
		managerTeaStove.add(new TeaStoveRecipe(new ItemStack(ItemRegister.half_dried_tea), new ItemStack(ItemRegister.white_tea_leaf), false));
	}
	
	private static void addTeapanRecipe()
	{
		managerTeapan.add(new TeaMakingRecipe(new ItemStack(ItemRegister.wet_tea, 8), new ItemStack(ItemRegister.tea_leaf, 8), new ItemStack(ItemRegister.half_dried_tea, 8), new ItemStack(ItemRegister.dried_tea, 8), new ItemStack(ItemRegister.yellow_tea_leaf, 8)));
	}
	
	private static void addBarrelRecipe()
	{
		managerBarrel.add(new TeaMakingRecipe(new ItemStack(ItemRegister.half_dried_tea, 8), new ItemStack(ItemRegister.black_tea_leaf, 8), new ItemStack(ItemRegister.puer_tea_leaf, 8), ItemStack.EMPTY, ItemStack.EMPTY));
	}
	
	private static void addCookingPanRecipe()
	{
		managerCookingPan.add(new TeaMakingRecipe(new ItemStack(ItemRegister.washed_rice, 8), new ItemStack(Items.WATER_BUCKET), new ItemStack(ItemRegister.wooden_lid), new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(ItemRegister.rice_ball, 2)));
	}
	
	private static void addDryingPanRecipe()
	{
		managerDryingPan.add(new TeaMakingRecipe(new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(ItemRegister.tea_leaf, 8), new ItemStack(ItemRegister.dried_tea, 8), ItemStack.EMPTY, ItemStack.EMPTY));
		managerDryingPan.add(new TeaMakingRecipe(new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(ItemRegister.half_dried_tea, 8), new ItemStack(ItemRegister.oolong_tea_leaf, 8), ItemStack.EMPTY, ItemStack.EMPTY));
	}
	
	@Nonnull
	public static <T> List<T> getActualInputs(Class<T> type, String ore, int count)
	{
		if (type == ItemStack.class)
		{
			return OreDictionary.getOres(ore)
					.stream()
					.map(ItemStack::copy)
					.peek(stack -> stack.setCount(count))
					.map(type::cast)
					.collect(Collectors.toList());
		}
		else
		{
			return NonNullList.create();
		}
	}
	
	private static void addTeaTableRecipe()
	{
		NonNullList<ItemStack> sugar3 = NonNullListHelper.createNonNullList(getActualInputs(ItemStack.class, "listAllsugar", 3));
		NonNullList<ItemStack> tea_whisk = NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.tea_whisk, 1, OreDictionary.WILDCARD_VALUE));
		for (int i = 0; i <= 5; i++)
		{
			if(i != 1)
			{
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag), OreDictionary.getOres("listAllmilk"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.milk_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), OreDictionary.getOres("listAllmilk"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.milk_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag), OreDictionary.getOres("cropLemon"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.lemon_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), OreDictionary.getOres("cropLemon"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.lemon_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.matcha_powder, 8), tea_whisk, new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.matcha_drink, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.black_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.black_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.green_tea_bag), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.green_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.dried_tea, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.green_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.yellow_tea_bag), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.yellow_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.yellow_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.yellow_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.white_tea_bag), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.white_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.white_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.white_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.oolong_tea_bag), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.oolong_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.oolong_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.oolong_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.puer_tea_bag), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.puer_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.puer_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.puer_tea, 1, i)));
			}
		}

		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag, 1), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar3, new ItemStack(BlockRegister.milk_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar3, new ItemStack(BlockRegister.milk_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag, 1), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar3, new ItemStack(BlockRegister.lemon_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar3, new ItemStack(BlockRegister.lemon_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.matcha_powder, 8), tea_whisk, new ItemStack(BlockRegister.empty_porcelain_kettle), sugar3, new ItemStack(BlockRegister.matcha_drink_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.green_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.dried_tea, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.yellow_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.yellow_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.white_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.white_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.oolong_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.oolong_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.puer_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_porcelain_kettle, 1, 3)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.puer_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_porcelain_kettle, 1, 3)));
		

		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag, 1), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar3, new ItemStack(BlockRegister.milk_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar3, new ItemStack(BlockRegister.milk_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag, 1), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar3, new ItemStack(BlockRegister.lemon_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar3, new ItemStack(BlockRegister.lemon_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.matcha_powder, 8), tea_whisk, new ItemStack(BlockRegister.empty_zisha_kettle), sugar3, new ItemStack(BlockRegister.matcha_drink_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.black_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.green_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.dried_tea, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.yellow_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.yellow_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.white_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.white_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.oolong_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.oolong_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.puer_tea_bag, 1), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_zisha_kettle, 1, 7)));
		managerTeaTable.add(new TeaTableRecipe(new ItemStack(ItemRegister.puer_tea_leaf, 8), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_zisha_kettle, 1, 7)));
	}
}
