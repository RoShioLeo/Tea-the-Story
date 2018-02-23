package cateam.teastory.common;

import cateam.teastory.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader
{
	public static CreativeTabs tabTeaStory;
	public static CreativeTabs tabRice;
	public static CreativeTabs tabDrink;

	public CreativeTabsLoader(FMLPreInitializationEvent event)
	{
		tabTeaStory = new CreativeTabs("tabTeaStory")
		{
			@Override
			public Item getTabIconItem()
			{
				return ItemLoader.half_dried_tea;
			}
		};
		tabDrink = new CreativeTabs("tabTeaDrink")
		{
			@Override
			public Item getTabIconItem()
			{
				return ItemLoader.green_tea;
			}
		};
		tabRice = new CreativeTabs("tabTeaRice")
		{
			@Override
			public Item getTabIconItem()
			{
				return ItemLoader.rice;
			}
		};
	}
}
