package cateam.teastory.potion;

import cateam.teastory.TeaStory;
import cateam.teastory.common.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionPhotosynthesis extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionPhotosynthesis()
    {
        super(false, 0xff9900);
        this.setPotionName("potion.photosynthesis");
        this.setRegistryName(TeaStory.MODID, "photosynthesis");
    }
    
    @Override
    public boolean isReady(int p_76397_1_, int p_76397_2_)
    {
        if (this == PotionLoader.PotionPhotosynthesis)
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
    	    if (this == PotionLoader.PotionPhotosynthesis)
            {
    	    	boolean isDaytime = entityLivingBaseIn.worldObj.getWorldTime() % 24000L < 12000L;
        		BlockPos pos = entityLivingBaseIn.getPosition();
        		if ((!entityLivingBaseIn.worldObj.isRainingAt(pos)) && ((isDaytime && (entityLivingBaseIn.worldObj.getLight(pos) >= 13 - 2 * p_76394_2_)) || ((!isDaytime) && (entityLivingBaseIn.worldObj.getLightFor(EnumSkyBlock.BLOCK, pos) >= 13 - 2 * p_76394_2_))))
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
                	entityLivingBaseIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, p_76394_2_)); 
                }
            }
    	}
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(PotionPhotosynthesis.res);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 18, 0, 18, 18);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
    {
            mc.getTextureManager().bindTexture(PotionPhotosynthesis.res);
            Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 18, 0, 18, 18, 256, 256);
    }
}
