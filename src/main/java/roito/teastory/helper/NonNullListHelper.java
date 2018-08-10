package roito.teastory.helper;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

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
