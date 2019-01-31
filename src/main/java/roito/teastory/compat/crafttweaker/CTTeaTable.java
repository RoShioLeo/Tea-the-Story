package roito.teastory.compat.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import roito.teastory.api.recipe.TeaMakingRecipe;
import roito.teastory.api.recipe.TeaTableRecipe;
import roito.teastory.common.RecipeRegister;
import roito.teastory.helper.CraftTweakerHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.teastory.TeaTable")
@ZenRegister
public class CTTeaTable
{
	@ZenMethod
	public static void add(IIngredient teaLeaf, IIngredient tool, IItemStack cup, IIngredient sugar, IItemStack output)
	{
		ItemStack out = CraftTweakerMC.getItemStack(output);
		ItemStack teacup = CraftTweakerMC.getItemStack(cup);
		RecipeRegister.actions.add(new Addition(CraftTweakerHelper.getItemStacks(teaLeaf), CraftTweakerHelper.getItemStacks(tool), teacup, CraftTweakerHelper.getItemStacks(sugar), out));
	}

	private static final class Addition implements IAction
	{
		private final ItemStack cup, output;
		private final NonNullList<ItemStack> teaLeaf, tool, sugar;

		private Addition(NonNullList<ItemStack> teaLeaf, NonNullList<ItemStack> tool, ItemStack cup, NonNullList<ItemStack> sugar, ItemStack output)
		{
			this.teaLeaf = teaLeaf;
			this.tool = tool;
			this.cup = cup;
			this.sugar = sugar;
			this.output = output;
		}

		@Override
		public void apply()
		{
			RecipeRegister.managerTeaTable.add(new TeaTableRecipe(teaLeaf, tool, cup, sugar, output));
		}

		@Override
		public String describe()
		{
			return String.format("Add Tea Table recipe: tea leaves %s, tool %s, cup %s, sugar %s -> output %s", teaLeaf, tool, cup, sugar, output);
		}
	}

	@ZenMethod
	public static void remove(IIngredient teaLeaf, IIngredient tool, IItemStack cup, IIngredient sugar, IItemStack output)
	{
		ItemStack out = CraftTweakerMC.getItemStack(output);
		ItemStack teacup = CraftTweakerMC.getItemStack(cup);
		RecipeRegister.actions.add(new Removal(CraftTweakerHelper.getItemStacks(teaLeaf), CraftTweakerHelper.getItemStacks(tool), teacup, CraftTweakerHelper.getItemStacks(sugar), out));
	}

	private static final class Removal implements IAction
	{
		private final ItemStack cup, output;
		private final NonNullList<ItemStack> teaLeaf, tool, sugar;

		private Removal(NonNullList<ItemStack> teaLeaf, NonNullList<ItemStack> tool, ItemStack cup, NonNullList<ItemStack> sugar, ItemStack output)
		{
			this.teaLeaf = teaLeaf;
			this.tool = tool;
			this.cup = cup;
			this.sugar = sugar;
			this.output = output;
		}

		@Override
		public void apply()
		{
			RecipeRegister.managerTeaTable.remove(teaLeaf, tool, cup, sugar, output);
		}

		@Override
		public String describe()
		{
			return null;
		}
	}

	@ZenMethod
	public static void removeAll()
	{
		RecipeRegister.actions.add(new Clear());
	}

	private static final class Clear implements IAction
	{
		@Override
		public void apply()
		{
			RecipeRegister.managerTeaTable.removeAll();
		}

		@Override
		public String describe()
		{
			return "Removing all recipes from Tea Table";
		}
	}
}
