package cloud.lemonslice.teastory.client.gui;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.container.StoneRollerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;


public class StoneRollerGui extends ContainerScreen<StoneRollerContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/container/gui_stone_roller.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TeaStory.MODID, TEXTURE_PATH);

    private StoneRollerContainer container;

    public StoneRollerGui(StoneRollerContainer container, PlayerInventory inv, ITextComponent name)
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
        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.enableAlphaTest();
        minecraft.getTextureManager().bindTexture(TEXTURE);
        blit(matrixStack, offsetX, offsetY, 0, 0, xSize, ySize);

        int totalTicks = 0;
        if (this.container.getTileEntity().getCurrentRecipe() != null)
        {
            totalTicks = this.container.getTileEntity().getCurrentRecipe().getWorkTime();
        }
        int processTicks = this.container.getTileEntity().getProcessTicks();
        int textureWidth = 0;
        if (totalTicks != 0)
        {
            textureWidth = (int) Math.ceil(22.0 * processTicks / totalTicks);
        }
        blit(matrixStack, offsetX + 77, offsetY + 37, 176, 0, textureWidth, 16);
        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();
        this.container.detectAndSendChanges();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        this.font.drawString(matrixStack, this.title.getString(), (this.xSize - this.font.getStringWidth(this.title.getString())) / 2.0F, 8.0F, 0x262626);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 95), 0x262626);
    }
}
