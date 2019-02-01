package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.helper.CraftTweakerHelper;
import roito.teastory.helper.NonNullListHelper;

public class TeaTableRecipe implements ITeaTableRecipe
{
	private final ItemStack cup, output;
	private final NonNullList<ItemStack> teaLeaf, tool, sugar;
	public static final ITeaTableRecipe EMPTY_RECIPE = new TeaTableRecipe(NonNullListHelper.createNonNullList(ItemStack.EMPTY), NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY, NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY);

	public TeaTableRecipe(NonNullList<ItemStack> teaLeaf, NonNullList<ItemStack> tool, ItemStack cup, NonNullList<ItemStack> sugar, ItemStack output)
	{
		this.teaLeaf = teaLeaf;
		this.tool = tool;
		this.cup = cup;
		this.sugar = sugar;
		this.output = output;
	}

	@Override
	public NonNullList<ItemStack> getTeaLeafInput()
	{
		return teaLeaf;
	}

	@Override
	public NonNullList<ItemStack> getToolInput()
	{
		return tool;
	}

	@Override
	public NonNullList<ItemStack> getSugarInput()
	{
		return sugar;
	}

	@Override
	public ItemStack getCupInput()
	{
		return cup;
	}

	@Override
	public ItemStack getOutput()
	{
		return output;
	}

	@Override
	public boolean isTheSameInput(ItemStack leaf, ItemStack tool, ItemStack cup, ItemStack sugar)
	{
		return !this.output.isEmpty() && CraftTweakerHelper.containsMatch(true, teaLeaf, leaf) && OreDictionary.itemMatches(this.cup, cup, true) && CraftTweakerHelper.containsMatch(false, this.tool, tool) && CraftTweakerHelper.containsMatch(false, this.sugar, sugar);
	}
}
