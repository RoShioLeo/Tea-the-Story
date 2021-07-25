package cloud.lemonslice.teastory.common.handler.event;

import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public final class BattleEventHandler
{
    public static void onShennongChiAttack(LivingHurtEvent event)
    {
        if (event.getSource().getImmediateSource() instanceof PlayerEntity && ((PlayerEntity) event.getSource().getImmediateSource()).getHeldItemMainhand().getItem() == ItemRegistry.SHENNONG_CHI)
        {
            event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
        }
    }
}
