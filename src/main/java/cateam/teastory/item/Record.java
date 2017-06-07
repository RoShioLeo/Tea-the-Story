package cateam.teastory.item;

import cateam.teastory.TeaStory;
import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

public class Record extends ItemRecord
{
	protected Record(String name, String name2) {
		super(name);
		this.setUnlocalizedName(name2);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
	}
	
	public ResourceLocation getRecordResource(String name)
    {
        return new ResourceLocation(TeaStory.MODID, name);
    }
}
