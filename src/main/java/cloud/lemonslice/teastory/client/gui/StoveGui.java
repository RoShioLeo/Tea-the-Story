package cloud.lemonslice.teastory.client.gui;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.container.StoveContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class StoveGui extends ContainerScreen<StoveContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/container/gui_stove.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TeaStory.MODID, TEXTURE_PATH);
    private StoveContainer container;

    public StoveGui(StoveContainer container, PlayerInventory inv, ITextComponent name)
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
        int offsetX = (this.width - this.xSize) / 2, offsetY = (height - ySize) / 2;

        blit(matrixStack, offsetX, offsetY, 0, 0, xSize, ySize);

        int fuelTicks = this.container.getTileEntity().getFuelTicks();
        int remainTicks = this.container.getTileEntity().getRemainTicks();
        int textureHeight = fuelTicks == 0 ? 0 : (int) Math.ceil(14.0F * remainTicks / fuelTicks);

        blit(matrixStack, offsetX + 81, offsetY + 16 + 14 - textureHeight, 176, 14 - textureHeight, 14, textureHeight);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        this.font.drawString(matrixStack, this.title.getString(), (float) (this.xSize / 2 - this.font.getStringWidth(this.title.getString()) / 2), 6.0F, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }
}
