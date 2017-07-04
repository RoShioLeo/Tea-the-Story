package cateam.teastory.item;

import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.item.ItemFood;

public class TeaEgg extends ItemFood
{
	public TeaEgg()
	{
		super(5, false);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
		this.setUnlocalizedName("tea_egg");
	}
}
