package cloud.lemonslice.teastory.common.world.feature;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BambooDirtFeature extends Feature<NoFeatureConfig>
{
    public BambooDirtFeature(Codec<NoFeatureConfig> configFactoryIn)
    {
        super(configFactoryIn);
        this.setRegistryName("bamboo_dirt");
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockState blockstate = BlockRegistry.GRASS_BLOCK_WITH_HOLE.getDefaultState();
        int i = 0;
        for (int j = 0; j < 4; ++j)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (blockpos.getY() < 255 && worldIn.getBlockState(blockpos).getBlock().equals(Blocks.GRASS_BLOCK))
            {
                worldIn.setBlockState(blockpos, blockstate, 2);
                ++i;
            }
        }

        return i > 0;
    }
}
