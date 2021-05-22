package cloud.lemonslice.teastory.client.color.block;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

import static net.minecraft.world.biome.BiomeColors.GRASS_COLOR;

public class GrassBlockColor implements IBlockColor
{
    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int index)
    {
        if (reader != null & pos != null)
        {
            return reader.getBlockColor(pos, GRASS_COLOR);
        }
        return -1;
    }
}
