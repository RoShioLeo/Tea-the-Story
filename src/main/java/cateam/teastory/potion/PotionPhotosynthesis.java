package cateam.teastory.potion;

import cateam.teastory.TeaStory;
import cateam.teastory.common.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumSkyBlock;

public class PotionPhotosynthesis extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionPhotosynthesis()
    {
        super(new ResourceLocation(TeaStory.MODID + ":" + "photosynthesis"), false, 0xff9900);
        this.setPotionName("potion.photosynthesis");
    }
    
    @Override
    public boolean isReady(int p_76397_1_, int p_76397_2_)
    {
        if (this.id == PotionLoader.PotionPhotosynthesis.id)
        {
        	int k = 60 >> p_76397_2_;
            return k > 0 ? p_76397_1_ % k == 0 : true;
        }
        return false;
    }
    
    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_)
    {
    	if(!entityLivingBaseIn.worldObj.isRemote)
    	{
        	if (this.id == PotionLoader.PotionPhotosynthesis.id)
            {
        		boolean isDaytime = entityLivingBaseIn.worldObj.getWorldTime() % 24000L < 12000L;
        		BlockPos pos = entityLivingBaseIn.getPosition();
        		if ((!entityLivingBaseIn.worldObj.canLightningStrike(pos)) && ((isDaytime && (entityLivingBaseIn.worldObj.getLight(pos) >= 13 - 2 * p_76394_2_)) || ((!isDaytime) && (entityLivingBaseIn.worldObj.getLightFor(EnumSkyBlock.BLOCK, pos) >= 13 - 2 * p_76394_2_))))
                {
                	if (p_76394_2_ > 2)
                	{
                		p_76394_2_ = 3;
                	}
                	if (p_76394_2_%2!=0)
                	{
                		p_76394_2_ = (p_76394_2_ - 1)/2;
                	}
                	else
                	{
                		p_76394_2_ =p_76394_2_/2;
                	}
                	entityLivingBaseIn.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, p_76394_2_)); 
                }
            }
    	}
    }
    
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(PotionPhotosynthesis.res);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 18, 0, 18, 18);
    }
}
