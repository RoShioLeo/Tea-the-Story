package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.tileentity.WoodenBarrelTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import org.lwjgl.opengl.GL11;

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

        Fluid fluid = tileEntityIn.getFluid();
        if (fluid != Fluids.EMPTY)
        {
            matrixStackIn.push();
            if (Minecraft.isAmbientOcclusionEnabled())
            {
                GL11.glShadeModel(GL11.GL_SMOOTH);
            }
            else
            {
                GL11.glShadeModel(GL11.GL_FLAT);
            }

            RenderHelper.disableStandardItemLighting();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.enableDepthTest();

            matrixStackIn.push();
            mc.getTextureManager().bindTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP);

            TextureAtlasSprite still = mc.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(fluid.getFluid().getAttributes().getStillTexture());

            int color = fluid.getAttributes().getColor();
            int r = color >> 16 & 0xFF;
            int g = color >> 8 & 0xFF;
            int b = color & 0xFF;
            int a = color >> 24 & 0xFF;

            float height = tileEntityIn.getHeight();

            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.125F, height, 0.125F).color(r, g, b, a).tex(still.getMinU(), still.getMinV()).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.125F, height, 0.875F).color(r, g, b, a).tex(still.getMinU(), still.getMaxV()).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.875F, height, 0.875F).color(r, g, b, a).tex(still.getMaxU(), still.getMaxV()).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 0.875F, height, 0.125F).color(r, g, b, a).tex(still.getMaxU(), still.getMinV()).lightmap(combinedLightIn).endVertex();

            tessellator.draw();
            matrixStackIn.pop();

            RenderSystem.disableBlend();
            RenderSystem.disableDepthTest();
            matrixStackIn.pop();
            RenderHelper.enableStandardItemLighting();
        }
    }
}
