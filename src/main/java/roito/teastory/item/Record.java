package roito.teastory.item;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;

public class Record extends ItemRecord
{
	protected Record(String name, String name2, String name3)
	{
		super(name,  new SoundEvent(new ResourceLocation(TeaStory.MODID, name3)));
		this.setUnlocalizedName(name2);
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
	}

	@Override
	public ResourceLocation getRecordResource(String name)
	{
		return new ResourceLocation(TeaStory.MODID, name);
	}
}
