package cloud.lemonslice.teastory.common.block;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BowlBlock extends NormalBlock
{
    protected static VoxelShape SHAPE = VoxelShapeHelper.createVoxelShape(4, 0, 4, 8, 7, 8);

    public BowlBlock(String name, Properties properties)
    {
        super(name, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }
}
