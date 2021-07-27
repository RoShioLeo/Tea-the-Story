package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.tileentity.StoneMillTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import org.lwjgl.opengl.GL11;

import static net.minecraft.block.HorizontalBlock.HORIZONTAL_FACING;

public class StoneMillTESR extends TileEntityRenderer<StoneMillTileEntity>
{
    public StoneMillTESR(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(StoneMillTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        Minecraft mc = Minecraft.getInstance();

        ItemRenderer renderItem = mc.getItemRenderer();

        matrixStackIn.push();

        matrixStackIn.translate(0.5, 0.5, 0.5);
        float angel = tileEntityIn.getAngel();
        if (tileEntityIn.isWorking())
        {
            angel += partialTicks * 3.0F;
        }
        matrixStackIn.rotate(new Quaternion(Vector3f.YP, angel, true));

        RenderHelper.enableStandardItemLighting();
        renderItem.renderItem(new ItemStack(ItemRegistry.STONE_MILL_TOP), ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
        RenderHelper.disableStandardItemLighting();

        matrixStackIn.pop();

        Fluid fluid = tileEntityIn.getOutputFluid();
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

            TextureAtlasSprite flowing = mc.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(fluid.getFluid().getAttributes().getFlowingTexture());

            int color = fluid.getAttributes().getColor();
            int r = color >> 16 & 0xFF;
            int g = color >> 8 & 0xFF;
            int b = color & 0xFF;
            int a = color >> 24 & 0xFF;

            Direction facing = tileEntityIn.getBlockState().get(HORIZONTAL_FACING);

            switch (facing)
            {
                case EAST:
                {
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0, 0.065F, 1).color(r, g, b, a).tex(flowing.getMinU(), flowing.getMinV()).lightmap(combinedLightIn).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1, 0.065F, 1).color(r, g, b, a).tex(flowing.getMinU(), flowing.getMaxV()).lightmap(combinedLightIn).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1, 0.065F, 0).color(r, g, b, a).tex(flowing.getMaxU(), flowing.getMaxV()).lightmap(combinedLightIn).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0, 0.065F, 0).color(r, g, b, a).tex(flowing.getMaxU(), flowing.getMinV()).lightmap(combinedLightIn).endVertex();
                }
            }


            float min_u_0 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
            float max_u_0 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
            float min_v_0 = getPosition(0.0000F, flowing.getMinV(), flowing.getMaxV());
            float max_v_0 = getPosition(0.09375F, flowing.getMinV(), flowing.getMaxV());

            buffer.pos(matrixStackIn.getLast().getMatrix(), 1F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_0, min_v_0).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 1.09375F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_0, max_v_0).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 1.09375F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_0, max_v_0).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 1F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_0, min_v_0).lightmap(combinedLightIn).endVertex();

            float min_u_1 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
            float max_u_1 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
            float min_v_1 = getPosition(0.09375F, flowing.getMinV(), flowing.getMaxV());
            float max_v_1 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

            buffer.pos(matrixStackIn.getLast().getMatrix(), 1.09375F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_1, min_v_1).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 1.09375F, -0.84125F, 0.5625F).color(r, g, b, a).tex(min_u_1, max_v_1).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 1.09375F, -0.84125F, 0.4375F).color(r, g, b, a).tex(max_u_1, max_v_1).lightmap(combinedLightIn).endVertex();
            buffer.pos(matrixStackIn.getLast().getMatrix(), 1.09375F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_1, min_v_1).lightmap(combinedLightIn).endVertex();

            tessellator.draw();
            matrixStackIn.pop();

            RenderSystem.disableBlend();
            RenderSystem.disableDepthTest();
            matrixStackIn.pop();
            RenderHelper.enableStandardItemLighting();
        }
    }

    public static float getPosition(float add, float min, float max)
    {
        return add * (max - min) + min;
    }
}
