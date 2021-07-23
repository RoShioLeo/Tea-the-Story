package cloud.lemonslice.teastory.common.handler.event;

import cloud.lemonslice.teastory.common.potion.EffectRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public final class DrinkEffectEventHandler
{
    public static void applyAgilityEffect(LivingHurtEvent event)
    {
        if (event.getEntityLiving().isServerWorld())
        {
            EffectInstance effect = event.getEntityLiving().getActivePotionEffect(EffectRegistry.AGILITY);
            if (effect != null)
            {
                if (event.getEntityLiving().getRNG().nextFloat() < ((effect.getAmplifier() + 1) * 0.15F + 0.05F))
                {
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void applyDefenceEffect(LivingEvent event)
    {
        if (event.getEntityLiving().isServerWorld())
        {
            if (event.getEntityLiving().getActivePotionEffect(EffectRegistry.DEFENCE) != null)
            {
                if (event instanceof LivingHurtEvent)
                {
                    event.getEntityLiving().playSound(SoundEvents.ITEM_SHIELD_BREAK, 1.0F, 1.0F);
                }
                event.setCanceled(true);
            }
        }
    }

    public static void applyLifeDrainEffect(LivingAttackEvent event)
    {
        if (event.getEntityLiving().isServerWorld())
        {
            EffectInstance effect = event.getEntityLiving().getActivePotionEffect(EffectRegistry.LIFE_DRAIN);
            if (effect != null)
            {
                int level = effect.getAmplifier() + 1;
                float r = event.getEntityLiving().getRNG().nextFloat();
                float health = event.getEntityLiving().getHealth();
                if (health < event.getEntityLiving().getMaxHealth() && r <= level * 0.2F)
                {
                    event.getEntityLiving().heal(4.0F - 6.0F / (level + 1.0F));
                }
            }
        }
    }

    public static void applyExcitementEffect(PlayerSleepInBedEvent event)
    {
        if (!event.getPlayer().world.isRemote)
        {
            EffectInstance effect = event.getPlayer().getActivePotionEffect(EffectRegistry.EXCITEMENT);
            if (effect != null)
            {
                event.setResult(PlayerEntity.SleepResult.OTHER_PROBLEM);
                event.getPlayer().sendStatusMessage(new TranslationTextComponent("info.teastory.bed.excited"), true);
            }
        }
    }
}
