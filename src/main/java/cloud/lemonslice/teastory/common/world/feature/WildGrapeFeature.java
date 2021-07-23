package cloud.lemonslice.teastory.common.world.feature;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class WildGrapeFeature extends Feature<NoFeatureConfig>
{
    public WildGrapeFeature(Codec<NoFeatureConfig> configFactoryIn)
    {
        super(configFactoryIn);
        this.setRegistryName("wild_grape");
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockState blockstate = BlockRegistry.WILD_GRAPE.getDefaultState();
        int i = 0;
        for (int j = 0; j < 12; ++j)
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
