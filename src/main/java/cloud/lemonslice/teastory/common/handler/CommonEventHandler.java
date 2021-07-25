package cloud.lemonslice.teastory.common.handler;

import cloud.lemonslice.teastory.TeaStory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cloud.lemonslice.teastory.common.handler.event.AgricultureEventHandler.*;
import static cloud.lemonslice.teastory.common.handler.event.BattleEventHandler.onShennongChiAttack;
import static cloud.lemonslice.teastory.common.handler.event.BlockEventHandler.dropAsh;
import static cloud.lemonslice.teastory.common.handler.event.DrinkEffectEventHandler.*;

@Mod.EventBusSubscriber(modid = TeaStory.MODID)
public final class CommonEventHandler
{
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {
        applyAgilityEffect(event);
        applyDefenceEffect(event);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event)
    {
        applyLifeDrainEffect(event);
        applyDefenceEffect(event);
    }

    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event)
    {
        applyExcitementEffect(event);
    }

    @SubscribeEvent
    public static void onUseBoneMeal(BonemealEvent event)
    {
        boneMealUsingLimit(event);
    }

    @SubscribeEvent
    public static void onNeighborChanged(BlockEvent.NeighborNotifyEvent event)
    {
        dropAsh(event);
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        plantMelon(event);
        cutMelon(event);
    }

    @SubscribeEvent
    public static void onToolUsing(BlockEvent.BlockToolInteractEvent event)
    {
        onAqueductShovelUsing(event);
    }

    @SubscribeEvent
    public static void onFarmlandTrampled(BlockEvent.FarmlandTrampleEvent event)
    {
        stopTramplingMelonField(event);
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event)
    {
        onShennongChiAttack(event);
    }
}
