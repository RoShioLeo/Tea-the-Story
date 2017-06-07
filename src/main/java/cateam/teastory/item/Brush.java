package cateam.teastory.item;

import net.minecraft.item.ItemStack;

public class Brush extends TSItem
{
	public Brush()
	{
		super("brush", 64);
		this.setContainerItem(this);
        this.setMaxDamage(32);
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
