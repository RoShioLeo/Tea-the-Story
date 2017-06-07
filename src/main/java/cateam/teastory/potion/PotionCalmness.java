package cateam.teastory.potion;

import cateam.teastory.TeaStory;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionCalmness extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionCalmness()
    {
        super(new ResourceLocation(TeaStory.MODID + ":" + "calmness"), false, 0x7F0000);
        this.setPotionName("potion.calmness");
        this.setIconIndex(0, 0);
    }
}
