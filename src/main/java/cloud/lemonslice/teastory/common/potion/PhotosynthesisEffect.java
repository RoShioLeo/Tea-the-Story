package cloud.lemonslice.teastory.common.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;

public class PhotosynthesisEffect extends NormalEffect
{
    public PhotosynthesisEffect()
    {
        super("photosynthesis", 0xff9900, EffectType.BENEFICIAL);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration % 60 == 0;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        boolean isDaytime = entityLivingBaseIn.getEntityWorld().getDayTime() < 12000L;
        BlockPos pos = entityLivingBaseIn.getPosition();
        if ((!entityLivingBaseIn.getEntityWorld().isRainingAt(pos)) && ((isDaytime && (entityLivingBaseIn.getEntityWorld().getLight(pos) >= 13 - 2 * amplifier)) || ((!isDaytime) && (entityLivingBaseIn.getEntityWorld().getLightFor(LightType.BLOCK, pos) >= 13 - 2 * amplifier))))
        {
            entityLivingBaseIn.heal(1);
        }
    }
}
