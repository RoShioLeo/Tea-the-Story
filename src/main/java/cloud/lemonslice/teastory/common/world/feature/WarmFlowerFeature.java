package cloud.lemonslice.teastory.common.world.feature;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.block.crops.HybridizableFlowerBlock;
import cloud.lemonslice.teastory.common.environment.flower.FlowerColor;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class WarmFlowerFeature extends Feature<NoFeatureConfig>
{
    public WarmFlowerFeature(Codec<NoFeatureConfig> configFactoryIn)
    {
        super(configFactoryIn);
        this.setRegistryName("warm_flower");
    }

    public BlockState getRandomFlower(Random random, BlockPos pos)
    {
        double d = MathHelper.clamp((1.0D + Biome.INFO_NOISE.noiseAt((double) pos.getX() / 48.0D, (double) pos.getZ() / 48.0D, false)) / 2.0D, 0.0D, 0.9999D);
        return BlockRegistry.ZINNIA.getDefaultState().with(HybridizableFlowerBlock.FLOWER_COLOR, FlowerColor.values()[(int) (d * 4)]);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockState blockstate = this.getRandomFlower(rand, pos);
        int i = 0;
        for (int j = 0; j < 32; ++j)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, blockstate, 2);
                ++i;
            }
        }

        return i > 0;
    }
}
