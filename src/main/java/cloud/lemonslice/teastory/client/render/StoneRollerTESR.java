package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.tileentity.StoneRollerTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class StoneRollerTESR extends TileEntityRenderer<StoneRollerTileEntity>
{
    public StoneRollerTESR(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(StoneRollerTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        Minecraft mc = Minecraft.getInstance();

        ItemRenderer renderItem = mc.getItemRenderer();

        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0.5, 0.5);
        float woodenFrameAngel = tileEntityIn.getWoodenFrameAngel();
        if (tileEntityIn.isWorking())
        {
            woodenFrameAngel += partialTicks * 3.0F;
        }
        float stoneAngel = tileEntityIn.getStoneAngel();
        if (tileEntityIn.isWorking())
        {
            stoneAngel += partialTicks * 4.0F;
        }

        matrixStackIn.push();
        matrixStackIn.rotate(new Quaternion(Vector3f.YP, woodenFrameAngel, true));
        matrixStackIn.rotate(new Quaternion(Vector3f.ZP, stoneAngel, true));
        RenderHelper.enableStandardItemLighting();
        renderItem.renderItem(new ItemStack(ItemRegistry.STONE_ROLLER_TOP), ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
        RenderHelper.disableStandardItemLighting();
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(new Quaternion(Vector3f.YP, woodenFrameAngel, true));
        RenderHelper.enableStandardItemLighting();
        renderItem.renderItem(new ItemStack(ItemRegistry.STONE_ROLLER_WOODEN_FRAME), ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
        RenderHelper.disableStandardItemLighting();
        matrixStackIn.pop();

        matrixStackIn.pop();

        matrixStackIn.push();
        ItemStack item = tileEntityIn.getStackInSlot(0).copy();
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                matrixStackIn.push();

                matrixStackIn.translate(0.3 + 0.4 * i, 0.32, 0.3 + 0.4 * j);
                matrixStackIn.scale(0.5F, 0.5F, 0.5F);
                matrixStackIn.rotate(new Quaternion(Vector3f.YP, 127 * (i + 133 * j) % 360, true));
                matrixStackIn.rotate(new Quaternion(Vector3f.XP, 90, true));
                RenderHelper.enableStandardItemLighting();
                renderItem.renderItem(item, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                RenderHelper.disableStandardItemLighting();

                matrixStackIn.pop();
            }
        }
        matrixStackIn.pop();
    }
}
