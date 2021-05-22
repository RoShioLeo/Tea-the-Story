package cloud.lemonslice.teastory.common.network;

import cloud.lemonslice.silveroak.network.INormalMessage;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.common.environment.solar.BiomeTemperatureManager;
import cloud.lemonslice.teastory.common.environment.solar.SolarTerm;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class SolarTermsMessage implements INormalMessage
{
    int solarDay;

    public SolarTermsMessage(int solarDay)
    {
        this.solarDay = solarDay;
    }

    public SolarTermsMessage(PacketBuffer buf)
    {
        solarDay = buf.readInt();
    }

    @Override
    public void toBytes(PacketBuffer buf)
    {
        buf.writeInt(solarDay);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            if (context.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            {
                TeaStory.proxy.getClientWorld().getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).ifPresent(data ->
                {
                    data.setSolarTermsDay(solarDay);
                    ForgeRegistries.BIOMES.forEach(biome ->
                            biome.climate.temperature = BiomeTemperatureManager.getDefaultTemperature(biome) + SolarTerm.get(data.getSolarTermIndex()).getTemperatureChange());
                    if (solarDay % ServerConfig.Season.lastingDaysOfEachTerm.get() == 0 && Minecraft.getInstance().player != null)
                    {
                        Minecraft.getInstance().player.sendStatusMessage(new TranslationTextComponent("info.teastory.environment.solar_term.message", SolarTerm.get(data.getSolarTermIndex()).getAlternationText()), false);
                    }
                });
            }
        });
    }
}
