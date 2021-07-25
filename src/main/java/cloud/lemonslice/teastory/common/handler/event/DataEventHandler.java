package cloud.lemonslice.teastory.common.handler.event;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.common.environment.solar.SolarTerm;
import cloud.lemonslice.teastory.common.network.SimpleNetworkHandler;
import cloud.lemonslice.teastory.common.network.SolarTermsMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = TeaStory.MODID)
public final class DataEventHandler
{
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
                event.getPlayer().getEntityWorld().getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).ifPresent(t ->
                {
                    SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new SolarTermsMessage(t.getSolarTermsDay()));
                    if (t.getSolarTermsDay() % ServerConfig.Season.lastingDaysOfEachTerm.get() == 0)
                    {
                        event.getPlayer().sendStatusMessage(new TranslationTextComponent("info.teastory.environment.solar_term.message", SolarTerm.get(t.getSolarTermIndex()).getAlternationText()), false);
                    }
                });
            }
        }
    }
}
