package roito.teastory.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;

public class TSItem extends Item
{
	public TSItem(String name, int maxStack, CreativeTabs creativeTab)
	{
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, name));
		this.setMaxStackSize(maxStack);
		this.setCreativeTab(creativeTab);
	}
}
