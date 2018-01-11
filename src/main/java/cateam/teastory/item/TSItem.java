package cateam.teastory.item;

import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.item.Item;

public class TSItem extends Item
{
	public TSItem(String name, int maxstack)
	{
		super();
		this.setUnlocalizedName(name);
		this.setMaxStackSize(maxstack);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
	}
}
