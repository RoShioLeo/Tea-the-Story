package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.tileentity.StoneMillTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

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

            IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getTranslucentNoCrumbling());
            TextureAtlasSprite flowing = mc.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(fluid.getFluid().getAttributes().getFlowingTexture());

            int color = fluid.getAttributes().getColor();
            int r = color >> 16 & 0xFF;
            int g = color >> 8 & 0xFF;
            int b = color & 0xFF;
            int a = color >> 24 & 0xFF;

            Direction facing = tileEntityIn.getBlockState().get(HORIZONTAL_FACING);
            int light = WorldRenderer.getCombinedLight(tileEntityIn.getWorld(), tileEntityIn.getPos());

            switch (facing)
            {
                case EAST:
                {
                    float min_u_0 = flowing.getInterpolatedU(1);
                    float max_u_0 = flowing.getInterpolatedU(15);
                    float min_v_0 = flowing.getInterpolatedV(2);
                    float max_v_0 = flowing.getInterpolatedU(16);

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.9375F).color(r, g, b, a).tex(min_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.9375F).color(r, g, b, a).tex(min_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.0625F).color(r, g, b, a).tex(max_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.0625F).color(r, g, b, a).tex(max_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_1 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_1 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_1 = getPosition(0.0000F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_1 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_2 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_2 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_2 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_2 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, -0.7435F, 0.5625F).color(r, g, b, a).tex(min_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, -0.7435F, 0.4375F).color(r, g, b, a).tex(max_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_3 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_3 = getPosition(0.6875F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, 0.065F, 0.4375F).color(r, g, b, a).tex(min_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, -0.7435F, 0.4375F).color(r, g, b, a).tex(min_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, -0.7435F, 0.4375F).color(r, g, b, a).tex(max_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_4 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_4 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, -0.7435F, 0.5625F).color(r, g, b, a).tex(min_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, -0.7435F, 0.5625F).color(r, g, b, a).tex(max_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.1875F, 0.065F, 0.5625F).color(r, g, b, a).tex(max_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_5 = getPosition(0.1875F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_5 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, 0.065F, 0.4375F).color(r, g, b, a).tex(min_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, -0.7435F, 0.4375F).color(r, g, b, a).tex(min_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, -0.7435F, 0.5625F).color(r, g, b, a).tex(max_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 1.0625F, 0.065F, 0.5625F).color(r, g, b, a).tex(max_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    break;
                }
                case WEST:
                {
                    float min_u_0 = getPosition(0.0625F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_0 = getPosition(0.9375F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_0 = getPosition(0.125F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_0 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.0625F).color(r, g, b, a).tex(min_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.0625F).color(r, g, b, a).tex(min_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.9375F).color(r, g, b, a).tex(max_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.9375F).color(r, g, b, a).tex(max_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_1 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_1 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_1 = getPosition(0.0000F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_1 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.4375F).color(r, g, b, a).tex(min_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, 0.065F, 0.4375F).color(r, g, b, a).tex(min_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, 0.065F, 0.5625F).color(r, g, b, a).tex(max_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.5625F).color(r, g, b, a).tex(max_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_2 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_2 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_2 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_2 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, 0.065F, 0.4375F).color(r, g, b, a).tex(min_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, -0.7435F, 0.4375F).color(r, g, b, a).tex(min_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, -0.7435F, 0.5625F).color(r, g, b, a).tex(max_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, 0.065F, 0.5625F).color(r, g, b, a).tex(max_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_3 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_3 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, -0.7435F, 0.5625F).color(r, g, b, a).tex(min_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, -0.7435F, 0.5625F).color(r, g, b, a).tex(max_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, 0.065F, 0.5625F).color(r, g, b, a).tex(max_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_4 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_4 = getPosition(0.6875F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, 0.065F, 0.4375F).color(r, g, b, a).tex(min_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, -0.7435F, 0.4375F).color(r, g, b, a).tex(min_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, -0.7435F, 0.4375F).color(r, g, b, a).tex(max_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.1875F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_5 = getPosition(0.1875F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_5 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, 0.065F, 0.5625F).color(r, g, b, a).tex(min_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, -0.7435F, 0.5625F).color(r, g, b, a).tex(min_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, -0.7435F, 0.4375F).color(r, g, b, a).tex(max_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), -0.0625F, 0.065F, 0.4375F).color(r, g, b, a).tex(max_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    break;
                }
                case NORTH:
                {
                    float min_u_0 = getPosition(0.0625F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_0 = getPosition(0.9375F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_0 = getPosition(0.125F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_0 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.9375F).color(r, g, b, a).tex(min_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.0625F).color(r, g, b, a).tex(min_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.0625F).color(r, g, b, a).tex(max_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.9375F).color(r, g, b, a).tex(max_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_1 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_1 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_1 = getPosition(0.0000F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_1 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, 0.0625F).color(r, g, b, a).tex(min_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, -0.1875F).color(r, g, b, a).tex(min_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, -0.1875F).color(r, g, b, a).tex(max_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, 0.0625F).color(r, g, b, a).tex(max_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_2 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_2 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_2 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_2 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, -0.1875F).color(r, g, b, a).tex(min_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, -0.1875F).color(r, g, b, a).tex(min_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, -0.1875F).color(r, g, b, a).tex(max_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, -0.1875F).color(r, g, b, a).tex(max_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_3 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_3 = getPosition(0.6875F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, -0.1875F).color(r, g, b, a).tex(min_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, -0.1875F).color(r, g, b, a).tex(min_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, -0.0625F).color(r, g, b, a).tex(max_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, -0.0625F).color(r, g, b, a).tex(max_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_4 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_4 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, -0.0625F).color(r, g, b, a).tex(min_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, -0.0625F).color(r, g, b, a).tex(min_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, -0.1875F).color(r, g, b, a).tex(max_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, -0.1875F).color(r, g, b, a).tex(max_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_5 = getPosition(0.1875F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_5 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, -0.0625F).color(r, g, b, a).tex(min_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, -0.0625F).color(r, g, b, a).tex(min_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, -0.0625F).color(r, g, b, a).tex(max_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, -0.0625F).color(r, g, b, a).tex(max_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    break;
                }
                case SOUTH:
                {
                    float min_u_0 = getPosition(0.0625F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_0 = getPosition(0.9375F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_0 = getPosition(0.125F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_0 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.0625F).color(r, g, b, a).tex(min_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.0625F, 0.065F, 0.9375F).color(r, g, b, a).tex(min_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.9375F).color(r, g, b, a).tex(max_u_0, max_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.9375F, 0.065F, 0.0625F).color(r, g, b, a).tex(max_u_0, min_v_0).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_1 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_1 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_1 = getPosition(0.0000F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_1 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, 0.9375F).color(r, g, b, a).tex(min_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, 1.1875F).color(r, g, b, a).tex(min_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, 1.1875F).color(r, g, b, a).tex(max_u_1, max_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, 0.9375F).color(r, g, b, a).tex(max_u_1, min_v_1).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_2 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_2 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float min_v_2 = getPosition(0.25F, flowing.getMinV(), flowing.getMaxV());
                    float max_v_2 = getPosition(1F, flowing.getMinV(), flowing.getMaxV());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, 1.1875F).color(r, g, b, a).tex(min_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, 1.1875F).color(r, g, b, a).tex(min_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, 1.1875F).color(r, g, b, a).tex(max_u_2, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, 1.1875F).color(r, g, b, a).tex(max_u_2, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_3 = getPosition(0.5625F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_3 = getPosition(0.6875F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, 1.1875F).color(r, g, b, a).tex(min_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, 1.1875F).color(r, g, b, a).tex(min_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, 1.0625F).color(r, g, b, a).tex(max_u_3, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, 1.0625F).color(r, g, b, a).tex(max_u_3, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_4 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_4 = getPosition(0.4375F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, 1.0625F).color(r, g, b, a).tex(min_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, 1.0625F).color(r, g, b, a).tex(min_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, 1.1875F).color(r, g, b, a).tex(max_u_4, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, 1.1875F).color(r, g, b, a).tex(max_u_4, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();

                    float min_u_5 = getPosition(0.1875F, flowing.getMinU(), flowing.getMaxU());
                    float max_u_5 = getPosition(0.3125F, flowing.getMinU(), flowing.getMaxU());

                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, 0.065F, 1.0625F).color(r, g, b, a).tex(min_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.5625F, -0.7435F, 1.0625F).color(r, g, b, a).tex(min_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, -0.7435F, 1.0625F).color(r, g, b, a).tex(max_u_5, max_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                    buffer.pos(matrixStackIn.getLast().getMatrix(), 0.4375F, 0.065F, 1.0625F).color(r, g, b, a).tex(max_u_5, min_v_2).lightmap(light).normal(1.0F, 0, 0).endVertex();
                }
            }

            matrixStackIn.pop();
        }
    }

    public static float getPosition(float add, float min, float max)
    {
        return add * (max - min) + min;
    }
}
