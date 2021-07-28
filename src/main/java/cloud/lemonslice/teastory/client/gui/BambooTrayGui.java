package cloud.lemonslice.teastory.client.gui;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.container.BambooTrayContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BambooTrayGui extends ContainerScreen<BambooTrayContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/container/gui_bamboo_tray.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TeaStory.MODID, TEXTURE_PATH);
    private BambooTrayContainer container;

    public BambooTrayGui(BambooTrayContainer container, PlayerInventory inv, ITextComponent name)
    {
        super(container, inv, name);
        this.container = container;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTick)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTick);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;

        blit(matrixStack, offsetX, offsetY, 0, 0, xSize, ySize);
        blit(matrixStack, offsetX + 51, offsetY + 29, 176, 107, 20, 20);

        int totalTicks = container.getTileEntity().getTotalTicks();
        int processTicks = container.getTileEntity().getProcessTicks();
        int textureWidth = 0;
        if (totalTicks != 0)
        {
            textureWidth = (int) Math.ceil(24 * processTicks / totalTicks);
        }
        blit(matrixStack, offsetX + 76, offsetY + 31, 176, 0, textureWidth, 17);

        int id = container.getTileEntity().getMode().ordinal();
        blit(matrixStack, offsetX + 52, offsetY + 30, 176, 17 + id * 18, 18, 18);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        this.font.drawString(matrixStack, this.title.getString(), (float) (this.xSize / 2 - this.font.getStringWidth(this.title.getString()) / 2), 6.0F, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void renderHoveredTooltip(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        super.renderHoveredTooltip(matrixStack, mouseX, mouseY);
        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;
        if (offsetX + 52 < mouseX && mouseX < offsetX + 70 && offsetY + 30 < mouseY && mouseY < offsetY + 48)
        {
            this.renderTooltip(matrixStack, container.getTileEntity().getMode().getTranslationKey(), mouseX, mouseY);
        }
    }
}
