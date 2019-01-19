package roito.teastory.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public class NonNullListHelper
{
	public static NonNullList<ItemStack> createNonNullList(ItemStack stackIn)
	{
		NonNullList<ItemStack> list = NonNullList.create();
		list.add(stackIn);
		return list;
	}

	public static NonNullList<ItemStack> createNonNullList(List<ItemStack> stackIn)
	{
		NonNullList<ItemStack> list = NonNullList.create();
		list.addAll(stackIn);
		return list;
	}
}
