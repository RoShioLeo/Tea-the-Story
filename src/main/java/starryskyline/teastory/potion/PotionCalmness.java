package starryskyline.teastory.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import starryskyline.teastory.TeaStory;

public class PotionCalmness extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionCalmness()
    {
        super(false, 0x7F0000);
        this.setPotionName("potion.calmness");
        this.setRegistryName(TeaStory.MODID, "calmness");
        this.setIconIndex(0, 0);
    }
}
