package cloud.lemonslice.teastory.common.environment.solar;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;

public final class BiomeTemperatureManager
{
    public final static HashMap<Biome, Float> BIOME_DEFAULT_TEMPERATURE_MAP = new HashMap<>();

    public static void init()
    {
        ForgeRegistries.BIOMES.forEach(biome ->
        {
            BIOME_DEFAULT_TEMPERATURE_MAP.put(biome, biome.getTemperature());
            if (biome.getCategory().equals(Biome.Category.SAVANNA))
            {
                biome.climate.downfall = 0.2F;
            }
        });
    }

    public static float getDefaultTemperature(Biome biome)
    {
        return BiomeTemperatureManager.BIOME_DEFAULT_TEMPERATURE_MAP.getOrDefault(biome, 0.6F);
    }
}
