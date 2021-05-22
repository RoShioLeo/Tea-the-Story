package cloud.lemonslice.teastory.client.hud;

import cloud.lemonslice.silveroak.common.environment.Humidity;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import cloud.lemonslice.teastory.common.config.ClientConfig;
import cloud.lemonslice.teastory.common.handler.AsmHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = TeaStory.MODID)
public final class OverlayEventHandler
{
    public final static ResourceLocation DEFAULT = new ResourceLocation("minecraft", "textures/gui/icons.png");
    private final static DebugInfoRenderer BAR_4 = new DebugInfoRenderer(Minecraft.getInstance());

    @SubscribeEvent(receiveCanceled = true)
    public static void onEvent(RenderGameOverlayEvent.Pre event)
    {
        ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
        if (clientPlayer != null)
        {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL)
            {
                if (ClientConfig.GUI.debugInfo.get())
                {
                    int solar = clientPlayer.world.getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).orElse(new CapabilitySolarTermTime.Data()).getSolarTermsDay();
                    long dayTime = clientPlayer.world.getWorldInfo().getDayTime();
                    float temp = clientPlayer.getEntityWorld().getBiome(clientPlayer.getPosition()).getTemperature(clientPlayer.getPosition());
                    Humidity h = Humidity.getHumid(clientPlayer.getEntityWorld().getBiome(clientPlayer.getPosition()).getDownfall(), temp);
                    double env = clientPlayer.getEntityWorld().getBiome(clientPlayer.getPosition()).getTemperature(clientPlayer.getPosition());
                    int solarTime = AsmHandler.getSolarAngelTime(clientPlayer.getEntityWorld().getDayTime(), clientPlayer.getEntityWorld());
                    BAR_4.renderStatusBar(event.getMatrixStack(), event.getWindow().getScaledWidth(), event.getWindow().getScaledHeight(), solar, dayTime, env, solarTime);
                }
            }
        }
    }
}
