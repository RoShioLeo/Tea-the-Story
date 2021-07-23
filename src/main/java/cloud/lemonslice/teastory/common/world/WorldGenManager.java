package cloud.lemonslice.teastory.common.world;

import cloud.lemonslice.silveroak.common.environment.Humidity;
import cloud.lemonslice.silveroak.common.environment.Rainfall;
import cloud.lemonslice.silveroak.common.environment.Temperature;
import cloud.lemonslice.teastory.common.world.feature.FeatureRegistry;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cloud.lemonslice.teastory.TeaStory.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public final class WorldGenManager
{
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event)
    {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        Temperature temperature = Temperature.getTemperatureLevel(event.getClimate().temperature);
        Rainfall rainfall = Rainfall.getRainfallLevel(event.getClimate().downfall);
        Humidity humidity = Humidity.getHumid(rainfall, temperature);
        if (humidity.getId() >= 4)
        {
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureRegistry.TEA_PLANT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_SPREAD_DOUBLE_PLACEMENT).chance(1));
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureRegistry.BAMBOO_DIRT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_SPREAD_DOUBLE_PLACEMENT).chance(2));
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureRegistry.WILD_GRAPE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_SPREAD_DOUBLE_PLACEMENT).chance(1));
        }
        if (temperature.getId() <= 4 && rainfall.getId() >= 2 && rainfall.getId() <= 4)
        {
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureRegistry.COLD_FLOWER.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_SPREAD_DOUBLE_PLACEMENT).chance(1));
        }
        else if (temperature.getId() >= 3 && temperature.getId() <= 5 && rainfall.getId() >= 2)
        {
            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureRegistry.WARM_FLOWER.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_SPREAD_DOUBLE_PLACEMENT).chance(1));
        }
    }
}
