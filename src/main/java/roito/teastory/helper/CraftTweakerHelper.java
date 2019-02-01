package roito.teastory.helper;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.List;

public final class CraftTweakerHelper
{
	public static NonNullList<ItemStack> getItemStacks(@Nonnull IIngredient input)
	{
		List<IItemStack> list = input.getItems();
		NonNullList<ItemStack> result = NonNullList.create();
		for (IItemStack in : list)
		{
			result.add(CraftTweakerMC.getItemStack(in));
		}
		return result;
	}

	public static boolean containsMatch(boolean strict, NonNullList<ItemStack> inputs, @Nonnull ItemStack target)
	{
		for (ItemStack input : inputs)
		{
			if (OreDictionary.itemMatches(input, target, strict))
			{
				return true;
			}
		}
		return false;
	}
}
