package cateam.teastory.item;

import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.item.ItemFood;

public class TeaEgg extends ItemFood
{
	public TeaEgg()
	{
		super(5, false);
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
		this.setUnlocalizedName("tea_egg");
	}
}
