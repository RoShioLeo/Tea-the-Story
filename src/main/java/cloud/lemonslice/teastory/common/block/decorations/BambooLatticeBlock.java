package cloud.lemonslice.teastory.common.block.decorations;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class BambooLatticeBlock extends NormalBlock implements IWaterLoggable
{
    public static final BooleanProperty NORTH = SixWayBlock.NORTH;
    public static final BooleanProperty EAST = SixWayBlock.EAST;
    public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
    public static final BooleanProperty WEST = SixWayBlock.WEST;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape NORTH_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 4.0D);
    protected static final VoxelShape WEST_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_SHAPE = VoxelShapeHelper.createVoxelShape(12.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        int count = 0;
        List<ItemStack> list = Lists.newArrayList();
        if (state.get(NORTH)) count++;
        if (state.get(SOUTH)) count++;
        if (state.get(WEST)) count++;
        if (state.get(EAST)) count++;
        list.add(new ItemStack(this.asItem(), count));
        return list;
    }

    public BambooLatticeBlock(String name, Properties properties)
    {
        super(name, properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return !state.get(WATERLOGGED);
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
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return 1.0F;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
    {
//        return useContext.getItem().getItem() == this.asItem();
        if (useContext.getItem().getItem() == this.asItem())
        {
            if (useContext.getFace() != Direction.UP)
            {
                return true;
            }
            else if (useContext.replacingClickedOnBlock())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else return false;
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

    protected BlockState getStateWithFacing(PlayerEntity player, BlockState state)
    {
        switch (player.getHorizontalFacing())
        {
            case NORTH:
                state = state.with(NORTH, true);
                break;
            case SOUTH:
                state = state.with(SOUTH, true);
                break;
            case WEST:
                state = state.with(WEST, true);
                break;
            default:
                state = state.with(EAST, true);
        }
        return state;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        if (state.getBlock() != this)
        {
            state = this.getDefaultState();
        }
        if (context.getPlayer() != null)
        {
            state = getStateWithFacing(context.getPlayer(), state);
        }
        return state.with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (stateIn.get(WATERLOGGED))
        {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot)
    {
        switch (rot)
        {
            case CLOCKWISE_180:
                return state.with(NORTH, state.get(SOUTH)).with(EAST, state.get(WEST)).with(SOUTH, state.get(NORTH)).with(WEST, state.get(EAST));
            case COUNTERCLOCKWISE_90:
                return state.with(NORTH, state.get(EAST)).with(EAST, state.get(SOUTH)).with(SOUTH, state.get(WEST)).with(WEST, state.get(NORTH));
            case CLOCKWISE_90:
                return state.with(NORTH, state.get(WEST)).with(EAST, state.get(NORTH)).with(SOUTH, state.get(EAST)).with(WEST, state.get(SOUTH));
            default:
                return state;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        switch (mirrorIn)
        {
            case LEFT_RIGHT:
                return state.with(NORTH, state.get(SOUTH)).with(SOUTH, state.get(NORTH));
            case FRONT_BACK:
                return state.with(EAST, state.get(WEST)).with(WEST, state.get(EAST));
            default:
                return super.mirror(state, mirrorIn);
        }
    }
}
