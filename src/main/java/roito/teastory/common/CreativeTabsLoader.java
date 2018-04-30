package roito.teastory.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.item.ItemLoader;

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
			public ItemStack getTabIconItem()
			{
				return new ItemStack(ItemLoader.half_dried_tea);
			}
		};
		tabDrink = new CreativeTabs("tabTeaDrink")
		{
			@Override
			public ItemStack getTabIconItem()
			{
				return new ItemStack(ItemLoader.green_tea);
			}
		};
		tabRice = new CreativeTabs("tabTeaRice")
		{
			@Override
			public ItemStack getTabIconItem()
			{
				return new ItemStack(ItemLoader.xian_rice);
			}
		};
	}
}
