package cloud.lemonslice.teastory.common.environment.solar;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TeaStory.MODID)
public final class WorldSolarTermManager
{
    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase.equals(TickEvent.Phase.END) && ServerConfig.Season.enable.get() && !event.world.isRemote() && event.world.getDimensionKey() == World.OVERWORLD)
        {
            event.world.getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).ifPresent(data ->
            {
                if (!event.world.getPlayers().isEmpty())
                {
                    data.updateTicks((ServerWorld) event.world);
                }
            });
        }
    }
}
