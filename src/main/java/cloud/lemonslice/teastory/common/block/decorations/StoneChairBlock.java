package cloud.lemonslice.teastory.common.block.decorations;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class StoneChairBlock extends ChairBlock
{
    private final static VoxelShape NORTH_SHAPE;
    private final static VoxelShape EAST_SHAPE;
    private final static VoxelShape WEST_SHAPE;
    private final static VoxelShape SOUTH_SHAPE;

    public StoneChairBlock(String name, Properties properties)
    {
        super(name, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        switch (state.get(HORIZONTAL_FACING))
        {
            case NORTH:
                return NORTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return SOUTH_SHAPE;
        }
    }

    static
    {
        NORTH_SHAPE = VoxelShapeHelper.createVoxelShape(0, 0, 5, 16, 8, 9);
        SOUTH_SHAPE = VoxelShapeHelper.createVoxelShape(0, 0, 2, 16, 8, 9);
        WEST_SHAPE = VoxelShapeHelper.createVoxelShape(5, 0, 0, 9, 8, 16);
        EAST_SHAPE = VoxelShapeHelper.createVoxelShape(2, 0, 0, 9, 8, 16);
    }
}
