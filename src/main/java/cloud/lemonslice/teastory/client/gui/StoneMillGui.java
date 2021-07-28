package cloud.lemonslice.teastory.client.gui;

import cloud.lemonslice.silveroak.client.texture.TexturePos;
import cloud.lemonslice.silveroak.helper.GuiHelper;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.container.StoneMillContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;


public class StoneMillGui extends ContainerScreen<StoneMillContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/container/gui_stone_mill.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TeaStory.MODID, TEXTURE_PATH);

    private StoneMillContainer container;

    public StoneMillGui(StoneMillContainer container, PlayerInventory inv, ITextComponent name)
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
        blit(matrixStack, offsetX + 95, offsetY + 37, 176, 0, textureWidth, 16);
        container.getTileEntity().getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(fluidHandler ->
        {
            int capacity = fluidHandler.getTankCapacity(0);
            int height = 0;
            if (capacity != 0)
            {
                height = (int) Math.ceil(48.0 * fluidHandler.getFluidInTank(0).getAmount() / capacity);
            }
            GuiHelper.drawTank(this, new TexturePos(offsetX + 37, offsetY + 22, 16, 48), fluidHandler.getFluidInTank(0), height);
        });
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

    protected void renderHoveredTooltip(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        super.renderHoveredTooltip(matrixStack, mouseX, mouseY);
        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;

        GuiHelper.drawFluidTooltip(this, matrixStack, mouseX, mouseY, offsetX + 37, offsetY + 22, 16, 48, this.container.getTileEntity().getFluidTank().getFluid().getDisplayName(), this.container.getTileEntity().getFluidTank().getFluid().getAmount());
    }
}
