package cloud.lemonslice.teastory.client.gui;

import cloud.lemonslice.silveroak.client.texture.TexturePos;
import cloud.lemonslice.silveroak.helper.GuiHelper;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.container.DrinkMakerContainer;
import cloud.lemonslice.teastory.common.recipe.drink.DrinkRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collections;


public class DrinkMakerGui extends ContainerScreen<DrinkMakerContainer>
{
    private static final String TEXTURE_PATH = "textures/gui/container/gui_drink_maker.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TeaStory.MODID, TEXTURE_PATH);

    private static final int QUESTION_X = 83;
    private static final int QUESTION_Y = 16;
    private static final int EXCLAMATION_X = 96;
    private static final int EXCLAMATION_Y = 54;

    private final DrinkMakerContainer container;
    private boolean isEnough = true;

    public DrinkMakerGui(DrinkMakerContainer container, PlayerInventory inv, ITextComponent name)
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

        GuiHelper.drawLayer(matrixStack, offsetX + QUESTION_X, offsetY + QUESTION_Y, new TexturePos(176, 94, 11, 11));

        if (this.container.getTileEntity().getCurrentRecipe() == null || !isEnough)
        {
            GuiHelper.drawLayer(matrixStack, offsetX + EXCLAMATION_X, offsetY + EXCLAMATION_Y, new TexturePos(187, 94, 11, 11));
        }

        int totalTicks = this.container.getTileEntity().getTotalTicks();
        int processTicks = this.container.getTileEntity().getProcessTicks();
        int textureWidth = 0;
        if (totalTicks != 0)
        {
            textureWidth = (int) Math.ceil(22.0 * processTicks / totalTicks);
        }
        blit(matrixStack, offsetX + 99, offsetY + 37, 176, 0, textureWidth, 16);
        container.getTileEntity().getFluidHandler().ifPresent(f ->
        {
            int capacity = f.getTankCapacity(0);
            int height;
            if (capacity != 0)
            {
                height = (int) Math.ceil(64.0 * this.container.getTileEntity().getFluidAmount() / capacity);
                GuiHelper.drawLayer(matrixStack, offsetX + 126, offsetY + 10, new TexturePos(176, 17, 20, 68));
            }
            else
            {
                height = 0;
            }
            GuiHelper.drawTank(this, new TexturePos(offsetX + 128, offsetY + 12, 16, 64), f.getFluidInTank(0), height);
        });
        RenderSystem.disableAlphaTest();
        RenderSystem.disableBlend();
        this.container.detectAndSendChanges();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        DrinkRecipe recipe = container.getTileEntity().getCurrentRecipe();
        if (!isEnough) isEnough = true;
        if (recipe != null)
        {
            int n = container.getTileEntity().getNeededAmount();
            for (int i = 0; i < 4; i++)
            {
                Slot slot = this.container.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (!itemStack.isEmpty() && itemStack.getCount() < n)
                {
                    renderSlotWarning(matrixStack, slot.xPos, slot.yPos);
                    isEnough = false;
                }
            }
        }

        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;
        this.font.drawString(matrixStack, this.title.getString(), this.xSize / 3.0F - this.font.getStringWidth(this.title.getString()) / 2.0F - 1, 8.0F, 0xdec674);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 95), 0xdec674);
        if (offsetX + QUESTION_X <= mouseX && mouseX <= offsetX + QUESTION_X + 11 && offsetY + QUESTION_Y <= mouseY && mouseY <= offsetY + QUESTION_Y + 11)
        {
            TranslationTextComponent ingredient = new TranslationTextComponent("info.teastory.tooltip.drink_maker.help.1");
            TranslationTextComponent residue = new TranslationTextComponent("info.teastory.tooltip.drink_maker.help.2");

            GuiHelper.drawTransparentStringDefault(this.font, ingredient.getString(), this.xSize / 3.0F - this.font.getStringWidth(ingredient.getString()) / 2.0F - 1, 28, 0xbfdec674, true);
            GuiHelper.drawTransparentStringDefault(this.font, residue.getString(), this.xSize / 3.0F - this.font.getStringWidth(residue.getString()) / 2.0F - 1, 55, 0xbfdec674, true);
        }
    }

    private void renderSlotWarning(MatrixStack matrixStack, int x, int y)
    {
        this.fillGradient(matrixStack, x, y, x + 16, y + 16, 0x9fd64f44, 0x9fd64f44);
    }

    protected void renderHoveredTooltip(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        super.renderHoveredTooltip(matrixStack, mouseX, mouseY);
        int offsetX = (width - xSize) / 2, offsetY = (height - ySize) / 2;

        GuiHelper.drawFluidTooltip(this, matrixStack, mouseX, mouseY, offsetX + 128, offsetY + 12, 16, 64, this.container.getTileEntity().getFluidTranslation(), this.container.getTileEntity().getFluidAmount());

        TranslationTextComponent warn_1 = new TranslationTextComponent("info.teastory.tooltip.drink_maker.warn.1");
        TranslationTextComponent warn_2 = new TranslationTextComponent("info.teastory.tooltip.drink_maker.warn.2");

        if (this.container.getTileEntity().getCurrentRecipe() == null)
        {
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, offsetX + EXCLAMATION_X, offsetY + EXCLAMATION_Y, 11, 11, Collections.singletonList(warn_1));
        }
        else if (!isEnough)
        {
            GuiHelper.drawTooltip(this, matrixStack, mouseX, mouseY, offsetX + EXCLAMATION_X, offsetY + EXCLAMATION_Y, 11, 11, Collections.singletonList(warn_2));
        }
    }
}
