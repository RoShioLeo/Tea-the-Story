package starryskyline.teastory.item;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import starryskyline.teastory.TeaStory;
import starryskyline.teastory.creativetab.CreativeTabsLoader;

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
