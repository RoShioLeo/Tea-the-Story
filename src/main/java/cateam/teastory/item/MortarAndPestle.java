package cateam.teastory.item;

import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.item.ItemStack;

public class MortarAndPestle extends TSItem
{
	public MortarAndPestle()
	{
		super("mortar_and_pestle", 1, CreativeTabsLoader.tabteastory);
		this.setContainerItem(this);
		this.setMaxDamage(64);
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
