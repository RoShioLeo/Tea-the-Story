package cateam.teastory.creativetab;

import cateam.teastory.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader
{
	public static CreativeTabs tabteastory;
	public static CreativeTabs tabrice;

	public CreativeTabsLoader(FMLPreInitializationEvent event)
	{
		tabteastory = new CreativeTabs("tabTeaStory")
		{
			@Override
			public Item getTabIconItem()
			{
				return ItemLoader.half_dried_tea;
			}
		};
		tabrice = new CreativeTabs("tabRice")
		{
			@Override
			public Item getTabIconItem()
			{
				return ItemLoader.rice;
			}
		};
	}
}
