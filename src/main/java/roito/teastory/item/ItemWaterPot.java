package roito.teastory.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

public class ItemWaterPot extends TSItem
{
	String container;
	public ItemWaterPot(String name, int maxDamageIn)
	{
		super("boiled_water_"  + name, 1, CreativeTabsRegister.tabDrink);
		this.container = TeaStory.MODID + ":" + name;
		this.setMaxDamage(maxDamageIn);
		this.setNoRepair();
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}
	
	public int getRemainWater(ItemStack stack)
	{
		return this.getMaxDamage(stack) - this.getDamage(stack);
	}
	
	public Item getEmptyPot()
	{
		return Item.getByNameOrId(container);
	}
	
	public ItemStack getNext(ItemStack stack, int use)
	{
		ItemStack itemStack = stack.copy();
		itemStack.setItemDamage(itemStack.getItemDamage() + use);
		if (itemStack.getItemDamage() >= itemStack.getMaxDamage())
		{
			return new ItemStack(Item.getByNameOrId(this.container));
		}
		else
		{
			return itemStack;
		}
	}
	
	/*@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack stack = itemStack.copy();
		if (stack.getItemDamage() < stack.getMaxDamage())
		{
			stack.setItemDamage(stack.getItemDamage() + 1);
		}
		else
		{
			stack = new ItemStack(Item.getByNameOrId(container));
		}
		return stack;
	}*/
}
