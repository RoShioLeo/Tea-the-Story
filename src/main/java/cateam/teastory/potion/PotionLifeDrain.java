package cateam.teastory.potion;

import cateam.teastory.TeaStory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionLifeDrain extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionLifeDrain()
    {
        super(false, 0x7F0000);
        this.setPotionName("potion.life_drain");
        this.setRegistryName(TeaStory.MODID, "life_drain");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(PotionLifeDrain.res);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 36, 0, 18, 18);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
    {
            mc.getTextureManager().bindTexture(PotionLifeDrain.res);
            Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 36, 0, 18, 18, 256, 256);
    }
}
