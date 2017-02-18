package starryskyline.teastory.item;

import net.minecraft.item.ItemStack;

public class MortarAndPestle extends TSItem
{
	public MortarAndPestle()
    {
		super("mortar_and_pestle", 1);
		this.setContainerItem(this);
        this.setMaxDamage(64);
	}
	
	public ItemStack getContainerItem(ItemStack itemStack)
    {
        ItemStack stack = itemStack.copy();
        stack.setItemDamage(stack.getItemDamage() + 1);
        stack.stackSize = 1;
        return stack;
    }
}
