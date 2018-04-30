package roito.teastory.item;

import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;

public class TeaEgg extends ItemFood
{
	public TeaEgg()
	{
		super(5, false);
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
		this.setUnlocalizedName("tea_egg");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "tea_egg"));
	}
}
