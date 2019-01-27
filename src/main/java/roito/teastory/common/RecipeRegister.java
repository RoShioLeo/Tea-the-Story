package roito.teastory.common;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.api.recipe.*;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.helper.NonNullListHelper;
import roito.teastory.item.ItemRegister;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeRegister
{
	@Nonnull
	public final static ITeaTableRecipeManager<ITeaTableRecipe> managerTeaTable = new TeaTableRecipeManager();
	public final static IRecipeManager<ITeaMakingRecipe> managerDryingPan = new TeaDryingPanRecipeManager();
	public final static IRecipeManager<ITeaMakingRecipe> managerCookingPan = new CookingPanRecipeManager();
	public final static IRecipeManager<ITeaMakingRecipe> managerTeapanInSun = new TeaMakingDryingInSunRecipeManager();
	public final static IRecipeManager<ITeaMakingRecipe> managerTeapanIndoors = new TeaMakingDryingIndoorsRecipeManager();
	public final static IRecipeManager<ITeaMakingRecipe> managerWet = new TeaMakingWetRecipeManager();
	public final static IRecipeManager<ITeaMakingRecipe> managerStoveDrying = new TeaStoveDryingRecipeManager();
	public final static IRecipeManager<ITeaMakingRecipe> managerStoveSteam = new TeaStoveSteamRecipeManager();

	public RecipeRegister()
	{
		addTeaTableRecipe();
		addDryingPanRecipe();
		addCookingPanRecipe();
		addTeapanRecipe();
		addBarrelRecipe();
		addWetRecipe();
		addTeaStoveRecipe();
	}

	private static void addTeaStoveRecipe()
	{
		managerStoveSteam.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.tea_leaf)), new ItemStack(ItemRegister.matcha_leaf)));
		managerStoveDrying.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.half_dried_tea)), new ItemStack(ItemRegister.white_tea_leaf)));
	}

	private static void addTeapanRecipe()
	{
		managerTeapanInSun.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.wet_tea)), new ItemStack(ItemRegister.tea_leaf)));
		managerTeapanInSun.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.tea_leaf)), new ItemStack(ItemRegister.half_dried_tea)));
		managerTeapanInSun.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.half_dried_tea)), new ItemStack(ItemRegister.dried_tea)));
	}

	private static void addBarrelRecipe()
	{
		managerTeapanIndoors.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.half_dried_tea)), new ItemStack(ItemRegister.black_tea_leaf)));
		managerTeapanIndoors.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf)), new ItemStack(ItemRegister.puer_tea_leaf)));
		managerTeapanIndoors.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.dried_tea)), new ItemStack(ItemRegister.yellow_tea_leaf)));
	}

	public static void addCookingPanRecipe()
	{
		managerCookingPan.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.washed_rice, ConfigMain.others.cookRiceBallEachTime * 8)), new ItemStack(ItemRegister.rice_ball, ConfigMain.others.cookRiceBallEachTime)));
	}

	private static void addDryingPanRecipe()
	{
		managerDryingPan.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.tea_leaf, 8)), new ItemStack(ItemRegister.dried_tea, 8)));
		managerDryingPan.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.half_dried_tea, 8)), new ItemStack(ItemRegister.oolong_tea_leaf, 8)));
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
		NonNullList<ItemStack> sugar12 = NonNullListHelper.createNonNullList(getActualInputs(ItemStack.class, "listAllsugar", 12));
		NonNullList<ItemStack> sugar24 = NonNullListHelper.createNonNullList(getActualInputs(ItemStack.class, "listAllsugar", 24));
		NonNullList<ItemStack> tea_whisk = NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.tea_whisk, 1, OreDictionary.WILDCARD_VALUE));
		for (int i = 0; i <= 5; i++)
		{
			if (i != 1)
			{
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag)), OreDictionary.getOres("listAllmilk"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.milk_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), OreDictionary.getOres("listAllmilk"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.milk_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag)), OreDictionary.getOres("cropLemon"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.lemon_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), OreDictionary.getOres("cropLemon"), new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.lemon_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.matcha_powder, 8)), tea_whisk, new ItemStack(ItemRegister.cup, 1, i), sugar3, new ItemStack(ItemRegister.matcha_drink, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.black_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.black_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.green_tea_bag)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.green_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.dried_tea, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.green_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.yellow_tea_bag)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.yellow_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.yellow_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.yellow_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.white_tea_bag)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.white_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.white_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.white_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.oolong_tea_bag)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.oolong_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.oolong_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.oolong_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.puer_tea_bag)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.puer_tea, 1, i)));
				managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.puer_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.cup, 1, i), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(ItemRegister.puer_tea, 1, i)));
			}
		}

		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag, 1)), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar12, new ItemStack(BlockRegister.milk_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar12, new ItemStack(BlockRegister.milk_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag, 1)), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar12, new ItemStack(BlockRegister.lemon_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_porcelain_kettle), sugar12, new ItemStack(BlockRegister.lemon_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.matcha_powder, 8)), tea_whisk, new ItemStack(BlockRegister.empty_porcelain_kettle), sugar12, new ItemStack(BlockRegister.matcha_drink_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.green_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.dried_tea, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.yellow_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.yellow_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.white_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.white_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.oolong_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.oolong_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.puer_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_porcelain_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.puer_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_porcelain_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_porcelain_kettle)));


		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag, 1)), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar24, new ItemStack(BlockRegister.milk_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), OreDictionary.getOres("listAllmilk"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar24, new ItemStack(BlockRegister.milk_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag, 1)), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar24, new ItemStack(BlockRegister.lemon_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), OreDictionary.getOres("cropLemon"), new ItemStack(BlockRegister.empty_zisha_kettle), sugar24, new ItemStack(BlockRegister.lemon_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.matcha_powder, 8)), tea_whisk, new ItemStack(BlockRegister.empty_zisha_kettle), sugar24, new ItemStack(BlockRegister.matcha_drink_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.black_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.green_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.dried_tea, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.green_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.yellow_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.yellow_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.yellow_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.white_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.white_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.white_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.oolong_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.oolong_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.oolong_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.puer_tea_bag, 1)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_zisha_kettle)));
		managerTeaTable.add(new TeaTableRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.puer_tea_leaf, 8)), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.empty_zisha_kettle), NonNullListHelper.createNonNullList(ItemStack.EMPTY), new ItemStack(BlockRegister.puer_tea_zisha_kettle)));
	}

	private static void addWetRecipe()
	{
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.tea_leaf)), new ItemStack(ItemRegister.wet_tea)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.half_dried_tea)), new ItemStack(ItemRegister.wet_tea)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.dried_tea)), new ItemStack(ItemRegister.tea_residue, 1, 0)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.black_tea_leaf)), new ItemStack(ItemRegister.tea_residue, 1, 1)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.yellow_tea_leaf)), new ItemStack(ItemRegister.tea_residue, 1, 2)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.white_tea_leaf)), new ItemStack(ItemRegister.tea_residue, 1, 3)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.oolong_tea_leaf)), new ItemStack(ItemRegister.tea_residue, 1, 4)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.puer_tea_leaf)), new ItemStack(ItemRegister.tea_residue, 1, 5)));
		managerWet.add(new TeaMakingRecipe(NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.matcha_leaf)), new ItemStack(ItemRegister.wet_tea)));
	}
}
