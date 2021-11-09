package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.block.HorizontalConnectedBlock;
import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class PaddyFieldBlock extends HorizontalConnectedBlock implements IWaterLoggable
{
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Water> WATER = EnumProperty.create("water", Water.class);
    private static final VoxelShape[] SHAPES;

    public PaddyFieldBlock()
    {
        super("paddy_field", Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.6F).sound(SoundType.GROUND).notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false).with(WATER, Water.SKIP));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        if (state.get(WATERLOGGED))
        {
            return VoxelShapes.fullCube();
        }
        else
        {
            return getPaddyFieldShape(state);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return getPaddyFieldShape(state);
    }

    private VoxelShape getPaddyFieldShape(BlockState state)
    {
        int north = state.get(NORTH) ? 0 : 8;
        int south = state.get(SOUTH) ? 0 : 4;
        int west = state.get(WEST) ? 0 : 2;
        int east = state.get(EAST) ? 0 : 1;
        return SHAPES[north + south + west + east];
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn)
    {
        return false;
    }

    @Override
    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn)
    {
        return false;
    }

    @Override
    public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state)
    {
        return Fluids.WATER;
    }

    public boolean canConnect(BlockState state)
    {
        return state.getBlock() instanceof PaddyFieldBlock || state.getBlock() instanceof AqueductConnectorBlock;
    }

    public BlockState getStateForPlacement(World world, BlockPos pos)
    {
        BlockState state = this.getDefaultState();

        for (Direction facing : Direction.Plane.HORIZONTAL)
        {
            BlockPos facingPos = pos.offset(facing);
            BlockState facingState = world.getBlockState(facingPos);
            if (this.canConnect(facingState))
            {
                state = state.with(FACING_TO_PROPERTY_MAP.get(facing), true);
            }
        }
        return state;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {

        return getStateForPlacement(context.getWorld(), context.getPos()).with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state)
    {

        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        updateWater(worldIn, pos);
    }

    public void updateWater(IWorld world, BlockPos pos)
    {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() == this)
        {
            boolean hasWater = state.get(WATERLOGGED);
            Water trend = state.get(WATER);

            if (trend != Water.SKIP)
            {
                if (trend == Water.POUR)
                {
                    if (!hasWater)
                    {
                        world.setBlockState(pos, state.with(WATERLOGGED, true).with(WATER, Water.SKIP), 3);
                        setToUpdate(world, pos.north(), trend);
                        setToUpdate(world, pos.south(), trend);
                        setToUpdate(world, pos.west(), trend);
                        setToUpdate(world, pos.east(), trend);
                    }
                }
                else
                {
                    if (hasWater)
                    {
                        world.setBlockState(pos, state.with(WATERLOGGED, false).with(WATER, Water.SKIP), 3);
                        setToUpdate(world, pos.north(), trend);
                        setToUpdate(world, pos.south(), trend);
                        setToUpdate(world, pos.west(), trend);
                        setToUpdate(world, pos.east(), trend);
                    }
                }
            }
        }
    }

    public void setToUpdate(IWorld world, BlockPos pos, Water water)
    {
        if (water == Water.SKIP) return;
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() == this)
        {
            if (water == Water.POUR && !state.get(WATERLOGGED))
            {
                world.setBlockState(pos, state.with(WATER, water), 3);
                world.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(world) + 1);
            }
            else if (water == Water.DRAIN && state.get(WATERLOGGED))
            {
                world.setBlockState(pos, state.with(WATER, water), 3);
                world.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(world));
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL)
        {
            return stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), this.canConnect(facingState));
        }
        else return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid)
    {
        getBlock().onBlockHarvested(world, pos, state, player);
        return world.setBlockState(pos, Blocks.AIR.getDefaultState(), world.isRemote ? 11 : 3);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(WATERLOGGED, WATER);
    }

    @Override
    public boolean isFertile(BlockState state, IBlockReader world, BlockPos pos)
    {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(Blocks.DIRT));
    }

    public enum Water implements IStringSerializable
    {
        SKIP,
        DRAIN,
        POUR;

        @Override
        public String getString()
        {
            return toString().toLowerCase();
        }
    }

    static
    {
        VoxelShape BOTTOM = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
        VoxelShape WALL_NORTH = VoxelShapeHelper.createVoxelShape(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 3.0D);
        VoxelShape WALL_SOUTH = VoxelShapeHelper.createVoxelShape(0.0D, 6.0D, 13.0D, 8.0D, 10.0D, 3.0D);
        VoxelShape WALL_WEST = VoxelShapeHelper.createVoxelShape(0.0D, 6.0D, 0.0D, 3.0D, 10.0D, 16.0D);
        VoxelShape WALL_EAST = VoxelShapeHelper.createVoxelShape(13.0D, 6.0D, 0.0D, 3.0D, 10.0D, 16.0D);
        SHAPES = new VoxelShape[]{BOTTOM, VoxelShapes.or(BOTTOM, WALL_EAST).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST, WALL_EAST).simplify(),
                VoxelShapes.or(BOTTOM, WALL_SOUTH).simplify(), VoxelShapes.or(BOTTOM, WALL_EAST, WALL_SOUTH).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST, WALL_SOUTH).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST, WALL_EAST, WALL_SOUTH).simplify(),
                VoxelShapes.or(BOTTOM, WALL_NORTH).simplify(), VoxelShapes.or(BOTTOM, WALL_EAST, WALL_NORTH).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST, WALL_NORTH).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST, WALL_EAST, WALL_NORTH).simplify(),
                VoxelShapes.or(BOTTOM, WALL_SOUTH, WALL_NORTH).simplify(), VoxelShapes.or(BOTTOM, WALL_EAST, WALL_SOUTH, WALL_NORTH).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST, WALL_SOUTH, WALL_NORTH).simplify(), VoxelShapes.or(BOTTOM, WALL_WEST, WALL_EAST, WALL_SOUTH, WALL_NORTH).simplify()};
    }
}
