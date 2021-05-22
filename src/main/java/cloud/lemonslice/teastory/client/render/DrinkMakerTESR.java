package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.tileentity.DrinkMakerTileEntity;
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

import java.util.List;

public class DrinkMakerTESR extends TileEntityRenderer<DrinkMakerTileEntity>
{

    public DrinkMakerTESR(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void render(DrinkMakerTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        Minecraft mc = Minecraft.getInstance();

        List<ItemStack> list = tileEntityIn.getContent();

        ItemRenderer renderItem = mc.getItemRenderer();

        matrixStackIn.push();
        RenderHelper.disableStandardItemLighting();
        for (int i = 0; i < 5; i++)
        {
            ItemStack itemStack = list.get(i);
            if (itemStack.isEmpty())
            {
                continue;
            }

            matrixStackIn.push();

            switch (tileEntityIn.getFacing())
            {
                case NORTH:
                    matrixStackIn.translate(0.35 * (i - 2) + 1, 0.35, 0.5);
                    break;
                case SOUTH:
                    matrixStackIn.translate(0.35 * (2 - i), 0.35, 0.5);
                    break;
                case WEST:
                    matrixStackIn.translate(0.5, 0.35, 0.35 * (2 - i));
                    break;
                default:
                    matrixStackIn.translate(0.5, 0.35, 0.35 * (i - 2) + 1);
            }
            matrixStackIn.rotate(new Quaternion(Vector3f.YP, 45, true));
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);

            RenderHelper.enableStandardItemLighting();
            renderItem.renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            RenderHelper.disableStandardItemLighting();
            matrixStackIn.pop();
        }
        matrixStackIn.pop();
    }
}
