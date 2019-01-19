package roito.teastory.item;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

public class Record extends ItemRecord
{
	protected Record(String name, String name2, String name3)
	{
		super(name, new SoundEvent(new ResourceLocation(TeaStory.MODID, name3)));
		this.setTranslationKey(name2);
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, name2));
		this.setCreativeTab(CreativeTabsRegister.tabTeaStory);
	}
}
