package cloud.lemonslice.teastory.client.color.block;

import cloud.lemonslice.teastory.common.block.crops.HybridizableFlowerBlock;
import cloud.lemonslice.teastory.common.environment.flower.FlowerColor;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

public class HybridizableFlowerBlockColor implements IBlockColor
{
    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int index)
    {
        if (index == 1)
        {
            return FlowerColor.getFlowerColor(state.get(HybridizableFlowerBlock.FLOWER_COLOR).getString()).getColorValue();
        }
        return -1;
    }
}
