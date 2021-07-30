package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.tileentity.WoodenBarrelTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;

public class WoodenBarrelTESR extends TileEntityRenderer<WoodenBarrelTileEntity>
{
    public WoodenBarrelTESR(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(WoodenBarrelTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        Minecraft mc = Minecraft.getInstance();

        Fluid fluid = tileEntityIn.getRemainFluid();
        if (fluid != Fluids.EMPTY && tileEntityIn.getHeight() > 0.0625F)
        {
            matrixStackIn.push();

            IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getTranslucentNoCrumbling());
            TextureAtlasSprite still = mc.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(fluid.getAttributes().getStillTexture());

            int color = fluid.getAttributes().getColor();
            int r = color >> 16 & 0xFF;
            int g = color >> 8 & 0xFF;
            int b = color & 0xFF;
            int a = color >> 24 & 0xFF;

            float height = tileEntityIn.getHeight();
            int light = WorldRenderer.getCombinedLight(tileEntityIn.getWorld(), tileEntityIn.getPos());

            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.125F, height, 0.125F).color(r, g, b, a).tex(still.getMinU(), still.getMinV()).lightmap(light).normal(1.0F, 0, 0).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.125F, height, 0.875F).color(r, g, b, a).tex(still.getMinU(), still.getMaxV()).lightmap(light).normal(1.0F, 0, 0).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.875F, height, 0.875F).color(r, g, b, a).tex(still.getMaxU(), still.getMaxV()).lightmap(light).normal(1.0F, 0, 0).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.875F, height, 0.125F).color(r, g, b, a).tex(still.getMaxU(), still.getMinV()).lightmap(light).normal(1.0F, 0, 0).endVertex();

            matrixStackIn.pop();
        }
    }
}
