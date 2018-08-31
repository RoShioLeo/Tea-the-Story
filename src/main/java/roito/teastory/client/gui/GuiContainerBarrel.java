package roito.teastory.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;
import roito.teastory.inventory.ContainerBarrel;

@SideOnly(Side.CLIENT)
public class GuiContainerBarrel extends GuiContainer
{
	private static final String TEXTURE_PATH = "textures/gui/container/gui_tea_making.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TeaStory.MODID, TEXTURE_PATH);
	private ContainerBarrel inventory;

	public GuiContainerBarrel(ContainerBarrel inventorySlotsIn)
	{
		super(inventorySlotsIn);
		this.xSize = 176;
		this.ySize = 166;
		this.inventory = inventorySlotsIn;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTick);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

		int totalTicks = this.inventory.getTotalTicks();
		int realTicks = this.inventory.getRealTicks();
		int textureWidth = 0;
		if (totalTicks != 0)
		{
			textureWidth =(int) Math.ceil(110.0 * realTicks / totalTicks);
		}
		this.drawTexturedModalRect(offsetX + 33, offsetY + 62, 0, 166, textureWidth, 17);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
        String title = I18n.format("teastory.container.barrel");
		this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
		int remainSeconds = inventory.getRealTicks() == 0 ? 0 : (inventory.getTotalTicks() - inventory.getRealTicks()) / 20;
		int remainMinutes = remainSeconds / 60;
		remainSeconds %= 60;
        String time = I18n.format("teastory.container.time", remainMinutes, remainSeconds);
		this.fontRenderer.drawString(time, (this.xSize - this.fontRenderer.getStringWidth(time)) / 2, 70, 0x404040);
	}
	
}
