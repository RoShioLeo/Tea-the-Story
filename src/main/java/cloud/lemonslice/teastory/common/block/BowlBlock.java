package cloud.lemonslice.teastory.common.block;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BowlBlock extends NormalBlock {
    protected static VoxelShape SHAPE = Block.makeCuboidShape(4d, 0d, 4d, 12d, 7d, 12d);
    public BowlBlock(String name, Properties properties) {
        super(name, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
