package starryskyline.teastory.client.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starryskyline.teastory.TeaStory;
import starryskyline.teastory.inventory.ContainerTeaStove;
import starryskyline.teastory.tileentity.TileEntityTeaStove;

@SideOnly(Side.CLIENT)
public class GuiContainerTeaStove extends GuiContainer
{
	private static final String TEXTURE_PATH = TeaStory.MODID + ":" + "textures/gui/container/gui_tea_stove.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    private ContainerTeaStove inventory;
    private int totalDryTime;
    private int totalSteamTime;
    
	public GuiContainerTeaStove(ContainerTeaStove inventorySlotsIn)
	{
		super(inventorySlotsIn);
		this.xSize = 176;
        this.ySize = 166;
        this.inventory = inventorySlotsIn;
        this.totalDryTime = inventorySlotsIn.getTotalDryTime();
        this.totalSteamTime = inventorySlotsIn.getTotalSteamTime();
	}
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
        
        int dryTime = this.inventory.getDryTime();
        int fuelTotalTime = this.inventory.getTotalFuelTime();
        int fuelTime = this.inventory.getFuelTime();
        int hasWater = this.inventory.hasWater();
        int steamTime = this.inventory.getSteamTime();
        int textureWidth1 =(int) Math.ceil(24.0 * dryTime / this.totalDryTime);
        int textureLength2 =(int) Math.ceil(14.0 * fuelTime / fuelTotalTime);
        int textureLength3 =(int) Math.ceil(29.0 * steamTime / this.totalSteamTime);
        this.drawTexturedModalRect(offsetX + 78, offsetY + 38, 176, 14, textureWidth1, 17);
        this.drawTexturedModalRect(offsetX + 53, offsetY + 53 - textureLength2, 176, 14 - textureLength2, 14, textureLength2);
        if((hasWater >= 1) && (hasWater <= 3))
        {
        	this.drawTexturedModalRect(offsetX + 33, offsetY + 53, 192, 60, 16, 16);
        }
        else if(hasWater == 0)
        {
        	this.drawTexturedModalRect(offsetX + 33, offsetY + 53, 176, 60, 16, 16);
        }
        this.drawTexturedModalRect(offsetX + 34, offsetY + 52 - textureLength3, 176, 59 - textureLength3, 12, textureLength3);
    }


	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String title = I18n.translateToLocal("teastory.container.tea_stove");
        this.fontRendererObj.drawString(title, (this.xSize - this.fontRendererObj.getStringWidth(title)) / 2, 6, 0x404040);
    }
}
