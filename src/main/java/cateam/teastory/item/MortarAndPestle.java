package cateam.teastory.item;

import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.item.ItemStack;

public class MortarAndPestle extends TSItem
{
	public MortarAndPestle(String name, int maxDamage)
	{
		super(name, 1, CreativeTabsLoader.tabTeaStory);
		this.setContainerItem(this);
		this.setMaxDamage(maxDamage);
		this.setNoRepair();
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack stack = itemStack.copy();
		stack.setItemDamage(stack.getItemDamage() + 1);
		stack.stackSize = 1;
		return stack;
	}
}
