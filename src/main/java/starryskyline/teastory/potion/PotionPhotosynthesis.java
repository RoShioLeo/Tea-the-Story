package starryskyline.teastory.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starryskyline.teastory.TeaStory;
import starryskyline.teastory.common.ConfigLoader;

public class PotionPhotosynthesis extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionPhotosynthesis()
    {
        super(false, 0xff9900);
        this.setPotionName("potion.photosynthesis");
        this.setRegistryName(TeaStory.MODID, "photosynthesis");
    }
    
    public boolean isReady(int p_76397_1_, int p_76397_2_)
    {
        if (this == PotionLoader.PotionPhotosynthesis)
        {
        	int k = 60 >> p_76397_2_;
            return k > 0 ? p_76397_1_ % k == 0 : true;
        }
        return false;
    }
    
    public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_)
    {
    	if(!entityLivingBaseIn.worldObj.isRemote)
    	{
    	    if (this == PotionLoader.PotionPhotosynthesis)
            {
    		    boolean isDaytime = entityLivingBaseIn.worldObj.getWorldTime() % 24000L < 12000L;
                if (isDaytime && (!entityLivingBaseIn.worldObj.isRainingAt(entityLivingBaseIn.getPosition().up())) && (entityLivingBaseIn.worldObj.getLight(entityLivingBaseIn.getPosition().up()) >= 14 - 2 * p_76394_2_))
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
