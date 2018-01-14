package cateam.teastory.item;

import cateam.teastory.TeaStory;
import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class Record extends ItemRecord
{
	protected Record(String name, String name2) {
		super(name,  new SoundEvent(new ResourceLocation(TeaStory.MODID, "records.caichawuqu")));
		this.setUnlocalizedName(name2);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
	}

	@Override
	public ResourceLocation getRecordResource(String name)
	{
		return new ResourceLocation(TeaStory.MODID, name);
	}
}
