package roito.teastory.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import roito.teastory.tileentity.TileEntityTeapan;

import java.util.List;

public class TESRTeapan extends TileEntitySpecialRenderer<TileEntityTeapan>
{
    @Override
    public void render(TileEntityTeapan tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        super.render(tile, x, y, z, partialTicks, destroyStage, alpha);
        Minecraft mc = Minecraft.getMinecraft();
        if (y > mc.player.eyeHeight)
        {
            return;
        }

        List<ItemStack> list = tile.getContents();

        if (list.isEmpty())
        {
            return;
        }

        RenderItem renderItem = mc.getRenderItem();

        GlStateManager.pushMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();

        GlStateManager.translate(x + 0.5, y + 0.1, z + 0.5);
        int count = 0;

        for (ItemStack stack : list)
        {
            GlStateManager.pushMatrix();
            int seed = stack.hashCode() + count * 4447;

            GlStateManager.scale(0.5, 0.5, 0.5);
            GlStateManager.translate(((seed % 100) - 50) / 150D, 0 + count / 16D, ((seed % 56) - 28) / 84D);
            GlStateManager.rotate(360 * (seed % 943) / 943F, 0, 1, 0);
            GlStateManager.rotate(90, 1, 0, 0);

            RenderHelper.enableStandardItemLighting();
            renderItem.renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();

            count++;
        }

        GlStateManager.popMatrix();
    }
}
