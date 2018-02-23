package cateam.teastory.item;

import cateam.teastory.TeaStory;
import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemWaterPot extends TSItem
{
	String container;
	public ItemWaterPot(String name, int maxDamageIn)
	{
		super("boiled_water_"  + name, 1, CreativeTabsLoader.tabDrink);
		this.container = TeaStory.MODID + ":" + name;
		this.setMaxDamage(maxDamageIn);
		this.setNoRepair();
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
