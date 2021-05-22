package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.entity.SeatEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SeatEntityRenderer extends EntityRenderer<SeatEntity>
{
    public SeatEntityRenderer(EntityRendererManager renderManager)
    {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(SeatEntity entity)
    {
        return null;
    }
}
