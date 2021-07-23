package cloud.lemonslice.teastory.common.world.feature;

import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public final class FeatureRegistry extends RegistryModule
{
    public static final Feature<NoFeatureConfig> TEA_PLANT = new TeaPlantFeature(NoFeatureConfig.CODEC);
    public static final Feature<NoFeatureConfig> COLD_FLOWER = new ColdWarmFlowerFeature(NoFeatureConfig.CODEC);
    public static final Feature<NoFeatureConfig> WARM_FLOWER = new WarmFlowerFeature(NoFeatureConfig.CODEC);
    public static final Feature<NoFeatureConfig> BAMBOO_DIRT = new BambooDirtFeature(NoFeatureConfig.CODEC);
    public static final Feature<NoFeatureConfig> WILD_GRAPE = new WildGrapeFeature(NoFeatureConfig.CODEC);
}
