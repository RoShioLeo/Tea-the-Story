package cloud.lemonslice.teastory.common.block.decorations;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BambooWallBlock extends BambooLatticeBlock
{
    protected static final VoxelShape NORTH_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 5.0D);
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 11.0D, 16.0D, 16.0D, 5.0D);
    protected static final VoxelShape WEST_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 5.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_SHAPE = VoxelShapeHelper.createVoxelShape(11.0D, 0.0D, 0.0D, 5.0D, 16.0D, 16.0D);

    public BambooWallBlock(String name)
    {
        super(name, Block.Properties.create(Material.BAMBOO).sound(SoundType.BAMBOO).hardnessAndResistance(0.6F).notSolid());
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.get(BlockStateProperties.WATERLOGGED) ? 0 : 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.get(BlockStateProperties.WATERLOGGED) ? 0 : 60;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        VoxelShape shape = VoxelShapes.empty();
        if (state.get(NORTH))
        {
            shape = VoxelShapes.or(shape, NORTH_SHAPE);
        }
        if (state.get(SOUTH))
        {
            shape = VoxelShapes.or(shape, SOUTH_SHAPE);
        }
        if (state.get(WEST))
        {
            shape = VoxelShapes.or(shape, WEST_SHAPE);
        }
        if (state.get(EAST))
        {
            shape = VoxelShapes.or(shape, EAST_SHAPE);
        }
        return shape;
    }
}
