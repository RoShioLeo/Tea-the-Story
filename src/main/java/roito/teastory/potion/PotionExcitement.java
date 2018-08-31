package roito.teastory.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;

public class PotionExcitement extends Potion
{
    private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionExcitement()
    {
        super(false, 0xffca65);
        this.setPotionName("teastory.potion.excitement");
        this.setRegistryName(TeaStory.MODID, "excitement");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(PotionExcitement.res);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 72, 0, 18, 18);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
    {
        mc.getTextureManager().bindTexture(PotionExcitement.res);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 72, 0, 18, 18, 256, 256);
    }
}
