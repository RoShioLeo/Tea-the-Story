package roito.teastory.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;

public class PotionDefence extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");
	public PotionDefence()
	{
		super(false, 0x959aa6);
		this.setPotionName("teastory.potion.defence");
		this.setRegistryName(TeaStory.MODID, "defence");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
	{
		mc.getTextureManager().bindTexture(PotionDefence.res);
		mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 54, 0, 18, 18);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
	{
		mc.getTextureManager().bindTexture(PotionDefence.res);
		Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 54, 0, 18, 18, 256, 256);
	}
}
