package roito.teastory.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;
import roito.teastory.inventory.ContainerTeaTable;

public class GuiContainerTeaTable extends GuiContainer
{
	private static final String TEXTURE_PATH = TeaStory.MODID + ":" + "textures/gui/container/gui_tea_table.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
	private ContainerTeaTable inventory;

	public GuiContainerTeaTable(ContainerTeaTable inventorySlotsIn)
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

		int teaTime = this.inventory.getTeaTime();
		int textureWidth = 1 + (int) Math.ceil(24.0 * teaTime / 400);
		this.drawTexturedModalRect(offsetX + 85, offsetY + 33, 176, 0, textureWidth, 17);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String title = I18n.format("teastory.container.tea_table");
		this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
	}
}
