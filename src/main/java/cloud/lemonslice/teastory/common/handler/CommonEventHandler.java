package cloud.lemonslice.teastory.common.handler;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.block.TeaPlantBlock;
import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.common.environment.crop.CropHumidityInfo;
import cloud.lemonslice.teastory.common.environment.crop.CropInfoManager;
import cloud.lemonslice.teastory.common.environment.crop.CropSeasonInfo;
import cloud.lemonslice.teastory.common.item.ItemsRegistry;
import cloud.lemonslice.teastory.common.network.SimpleNetworkHandler;
import cloud.lemonslice.teastory.common.network.SolarTermsMessage;
import cloud.lemonslice.teastory.common.potion.EffectsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = TeaStory.MODID)
public final class CommonEventHandler
{
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {
        if (event.getEntityLiving().isServerWorld())
        {
            EffectInstance effect = event.getEntityLiving().getActivePotionEffect(EffectsRegistry.AGILITY);
            if (effect != null)
            {
                if (event.getEntityLiving().getRNG().nextFloat() < ((effect.getAmplifier() + 1) * 0.15F + 0.05F))
                {
                    event.setCanceled(true);
                }
            }
            if (event.getEntityLiving().getActivePotionEffect(EffectsRegistry.DEFENCE) != null)
            {
                event.getEntityLiving().playSound(SoundEvents.ITEM_SHIELD_BREAK, 1.0F, 1.0F);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event)
    {
        if (event.getEntityLiving().isServerWorld())
        {
            EffectInstance effect = event.getEntityLiving().getActivePotionEffect(EffectsRegistry.LIFE_DRAIN);
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
        if (event.getEntityLiving().getActivePotionEffect(EffectsRegistry.DEFENCE) != null)
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event)
    {
        if (!event.getPlayer().world.isRemote)
        {
            EffectInstance effect = event.getPlayer().getActivePotionEffect(EffectsRegistry.EXCITEMENT);
            if (effect != null)
            {
                event.setResult(PlayerEntity.SleepResult.OTHER_PROBLEM);
                event.getPlayer().sendStatusMessage(new TranslationTextComponent("info.teastory.bed.excited"), true);
            }
        }
    }

    @SubscribeEvent
    public static void onUseBoneMeal(BonemealEvent event)
    {
        if (!ServerConfig.Agriculture.canUseBoneMeal.get())
        {
            if (event.getBlock().getBlock() instanceof TeaPlantBlock)
            {
                if (event.getBlock().get(TeaPlantBlock.AGE) == 7 || event.getBlock().get(TeaPlantBlock.AGE) == 6)
                {
                    event.setResult(Event.Result.DEFAULT);
                }
                else
                {
                    event.setCanceled(true);
                }
            }
            else if (event.getBlock().getBlock() instanceof IPlantable && ((IPlantable) event.getBlock().getBlock()).getPlantType(event.getWorld(), event.getPos()) == PlantType.CROP)
            {
                event.setCanceled(true);
            }
            else
                event.setResult(Event.Result.DEFAULT);
        }
    }

    @SubscribeEvent
    public static void onNeighborChanged(BlockEvent.NeighborNotifyEvent event)
    {
        if (ServerConfig.Others.woodDropsAshWhenBurning.get())
            event.getNotifiedSides().forEach(direction ->
            {
                if (event.getWorld().getBlockState(event.getPos()).isFlammable(event.getWorld(), event.getPos(), direction) && event.getWorld().getBlockState(event.getPos().offset(direction)).getBlock() instanceof FireBlock)
                {
                    Block.spawnAsEntity((World) event.getWorld(), event.getPos(), new ItemStack(ItemsRegistry.ASH));
                }
            });
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
//        PlayerEntity player = event.getPlayer();
//        ItemStack held = player.getHeldItem(event.getHand());
//        if (!held.isEmpty())
//        {
//            BlockState state = event.getWorld().getBlockState(event.getPos());
//            if (state.isIn(Tags.Blocks.DIRT) && held.getItem().getToolTypes(held).contains(ToolType.SHOVEL))
//            {
//                event.getWorld().setBlockState(event.getPos(), BlocksRegistry.DIRT_AQUEDUCT.getDefaultState());
//                event.getWorld().playSound(event.getPlayer(), event.getPos(), SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                held.damageItem(1, player, p -> p.sendBreakAnimation(event.getHand()));
//                event.setUseItem(Event.Result.ALLOW);
//            }
//        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event)
    {

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<World> event)
    {
        if (ServerConfig.Season.enable.get() && event.getObject().getDimensionKey() == World.OVERWORLD)
        {
            event.addCapability(new ResourceLocation(TeaStory.MODID, "world_solar_terms"), new CapabilitySolarTermTime.Provider());
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.getPlayer() instanceof ServerPlayerEntity && !(event.getPlayer() instanceof FakePlayer))
        {
            if (ServerConfig.Season.enable.get())
            {
                event.getPlayer().getEntityWorld().getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).ifPresent(t -> SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new SolarTermsMessage(t.getSolarTermsDay())));
            }
        }
    }

    public static void addCropTooltips(ItemTooltipEvent event)
    {
        if (event.getItemStack().getItem() instanceof BlockItem)
        {
            if (CropInfoManager.getHumidityCrops().contains(((BlockItem) event.getItemStack().getItem()).getBlock()))
            {
                CropHumidityInfo info = CropInfoManager.getHumidityInfo(((BlockItem) event.getItemStack().getItem()).getBlock());
                event.getToolTip().addAll(info.getTooltip());
            }
            if (CropInfoManager.getSeasonCrops().contains(((BlockItem) event.getItemStack().getItem()).getBlock()))
            {
                CropSeasonInfo info = CropInfoManager.getSeasonInfo(((BlockItem) event.getItemStack().getItem()).getBlock());
                event.getToolTip().addAll(info.getTooltip());
            }
        }
    }
}
