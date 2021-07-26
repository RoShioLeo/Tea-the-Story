package cloud.lemonslice.teastory.common.block.craft;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.state.properties.BlockStateProperties.LIT;

public interface IStoveBlock
{
    static boolean isBurning(World world, BlockPos pos)
    {
        return isBurning(world.getBlockState(pos));
    }

    static boolean isBurning(BlockState state)
    {
        if (state.getBlock() instanceof IStoveBlock)
        {
            return state.get(LIT);
        }
        return false;
    }

    int getFuelPower();
}
