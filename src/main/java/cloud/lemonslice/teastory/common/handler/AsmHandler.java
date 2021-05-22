package cloud.lemonslice.teastory.common.handler;

import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import cloud.lemonslice.teastory.common.environment.solar.SolarTerm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IDayTimeReader;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class AsmHandler
{
    public static float getSeasonCelestialAngle(long worldTime, IDayTimeReader world)
    {
        return getCelestialAngle(getSolarAngelTime(worldTime, world));
    }

    public static int getSolarAngelTime(long worldTime, IDayTimeReader world)
    {
        if (world instanceof World)
        {
            return ((World) world).getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).map(data ->
            {
                int dayTime = SolarTerm.get(data.getSolarTermIndex()).getDayTime();
                int sunrise = 24000 - dayTime / 2;
                int sunset = dayTime / 2;
                int dayWorldTime = Math.toIntExact((worldTime + 18000) % 24000); // 0 for noon; 6000 for sunset; 18000 for sunrise.
                int solarAngelTime;
                if (0 <= dayWorldTime && dayWorldTime <= sunset)
                {
                    solarAngelTime = 6000 + dayWorldTime * 6000 / sunset;
                }
                else if (dayWorldTime > sunset && dayWorldTime <= sunrise)
                {
                    solarAngelTime = 12000 + (dayWorldTime - sunset) * 12000 / (24000 - dayTime);
                }
                else
                {
                    solarAngelTime = (dayWorldTime - sunrise) * 6000 / (24000 - sunrise);
                }
                return solarAngelTime;
            }).orElse(Math.toIntExact(worldTime % 24000));
        }
        return Math.toIntExact(worldTime % 24000);
    }

    public static float getCelestialAngle(long worldTime)
    {
        double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        return (float) (d0 * 2.0D + d1) / 3.0F;
    }
}
