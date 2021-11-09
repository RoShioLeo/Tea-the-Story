package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.block.HorizontalConnectedBlock;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class AqueductBlock extends HorizontalConnectedBlock implements IWaterLoggable
{
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 0, 32);
    public static final BooleanProperty BLOCKED = BooleanProperty.create("blocked");
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape[] SHAPES;
    private static final VoxelShape FULL_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public AqueductBlock(String name, Properties properties)
    {
        super(name, properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, 32).with(BLOCKED, false).with(BOTTOM, false).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (state.get(BLOCKED))
        {
            worldIn.setBlockState(pos, state.with(BLOCKED, false));
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(worldIn));
            if (!worldIn.isRemote)
            {
                spawnAsEntity(worldIn, pos, new ItemStack(Blocks.GRAVEL));
            }
            return ActionResultType.SUCCESS;
        }
        else if (player.getHeldItem(handIn).getItem() == Blocks.GRAVEL.asItem() && !state.get(BLOCKED))
        {
            worldIn.setBlockState(pos, state.with(BLOCKED, true).with(WATERLOGGED, false).with(DISTANCE, 32));
            if (worldIn instanceof ServerWorld)
            {
                updateWater((ServerWorld) worldIn, pos.north(), state);
                updateWater((ServerWorld) worldIn, pos.south(), state);
                updateWater((ServerWorld) worldIn, pos.east(), state);
                updateWater((ServerWorld) worldIn, pos.west(), state);
            }
            player.getHeldItem(handIn).shrink(1);
            return ActionResultType.SUCCESS;
        }
        else
        {
            return fillAqueduct(worldIn, pos, player, handIn);
        }
    }

    public ActionResultType fillAqueduct(World worldIn, BlockPos pos, PlayerEntity player, Hand handIn)
    {
        if (player.getHeldItem(handIn).getItem().isIn(Tags.Items.COBBLESTONE))
        {
            worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
            return ActionResultType.SUCCESS;
        }
        else return ActionResultType.PASS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        if (state.get(BLOCKED))
        {
            return FULL_SHAPE;
        }
        int bottom = state.get(BOTTOM) ? 0 : 16;
        int north = state.get(NORTH) ? 0 : 8;
        int south = state.get(SOUTH) ? 0 : 4;
        int west = state.get(WEST) ? 0 : 2;
        int east = state.get(EAST) ? 0 : 1;
        return SHAPES[bottom + north + south + west + east];
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

    public boolean canConnect(BlockState state)
    {
        return isAqueduct(state) || state.getFluidState().getFluid() == Fluids.WATER && !(state.getBlock() instanceof PaddyFieldBlock);
    }

    public static boolean isAqueduct(BlockState state)
    {
        return state.getBlock() instanceof AqueductBlock;
    }

    public int getNearDistance(IWorld world, BlockPos pos)
    {
        int distance = 31;
        distance = Math.min(distance, getDistance(world.getBlockState(pos.north()), false));
        distance = Math.min(distance, getDistance(world.getBlockState(pos.south()), false));
        distance = Math.min(distance, getDistance(world.getBlockState(pos.east()), false));
        distance = Math.min(distance, getDistance(world.getBlockState(pos.west()), false));
        distance = Math.min(distance, getDistance(world.getBlockState(pos.up()), true));
        return distance;
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid)
    {
        getBlock().onBlockHarvested(world, pos, state, player);
        return world.setBlockState(pos, Blocks.AIR.getDefaultState(), world.isRemote ? 11 : 3);
    }

    public int getDistance(BlockState state, boolean isUp)
    {
        if (!canConnect(state) || state.getBlock() instanceof AqueductBlock && state.get(BLOCKED))
        {
            return 32;
        }
        if (!isUp)
        {
            if (state.getBlock() instanceof AqueductBlock)
            {
                return state.get(DISTANCE);
            }
            else
            {
                Fluid fluid = state.getFluidState().getFluid();
                if (fluid == Fluids.WATER && !(state.getBlock() instanceof PaddyFieldBlock))
                {
                    return -1;
                }
            }
        }
        else
        {
            Fluid fluid = state.getFluidState().getFluid();
            if (fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER)
            {
                return -1;
            }
        }
        return 32;
    }

    public BlockState getStateForPlacement(World world, BlockPos pos)
    {
        BlockState state = this.getDefaultState();
        FluidState up = world.getBlockState(pos.up()).getFluidState();
        if (up.getFluid() == Fluids.WATER || up.getFluid() == Fluids.FLOWING_WATER)
        {
            state = state.with(DISTANCE, 0).with(WATERLOGGED, true);
        }

        for (Direction facing : Direction.Plane.HORIZONTAL)
        {
            BlockPos facingPos = pos.offset(facing);
            BlockState facingState = world.getBlockState(facingPos);
            if (this.canConnect(facingState))
            {
                state = state.with(FACING_TO_PROPERTY_MAP.get(facing), true);
            }
        }
        BlockPos facingPos = pos.offset(Direction.DOWN);
        BlockState facingState = world.getBlockState(facingPos);
        if (this.isAqueduct(facingState))
        {
            state = state.with(BOTTOM, true);
        }
        return state;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        return getStateForPlacement(world, pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        boolean flag = false;
        int nearDistance = getNearDistance(worldIn, pos);
        int currentDistance = state.get(DISTANCE);
        if (state.get(BLOCKED))
        {
            return;
        }
        if (nearDistance + 1 < currentDistance)
        {
            state = state.with(WATERLOGGED, true).with(DISTANCE, nearDistance + 1);
            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            updateConnected(worldIn, pos, state);
            flag = true;
        }
        else if (nearDistance + 1 != currentDistance)
        {
            state = state.with(WATERLOGGED, false).with(DISTANCE, 32);
            updateConnected(worldIn, pos, state);
            flag = true;
        }
        if (flag)
        {
            worldIn.setBlockState(pos, state);
            updateWater(worldIn, pos, state);
        }
    }

    public void updateConnected(ServerWorld worldIn, BlockPos pos, BlockState state)
    {
        if (state.get(NORTH)) updateWater(worldIn, pos.north(), state);
        if (state.get(SOUTH)) updateWater(worldIn, pos.south(), state);
        if (state.get(EAST)) updateWater(worldIn, pos.east(), state);
        if (state.get(WEST)) updateWater(worldIn, pos.west(), state);
        if (state.get(BOTTOM)) updateWater(worldIn, pos.down(), state);
    }

    public void updateWater(ServerWorld worldIn, BlockPos pos, BlockState origin)
    {
        BlockState state = worldIn.getBlockState(pos);
        if (state.getBlock() instanceof PaddyFieldBlock)
        {
            worldIn.setBlockState(pos, state.with(PaddyFieldBlock.WATER, origin.get(WATERLOGGED) ? PaddyFieldBlock.Water.POUR : PaddyFieldBlock.Water.DRAIN));
            ((PaddyFieldBlock) state.getBlock()).updateWater(worldIn, pos);
        }
        else if (state.getBlock() instanceof AqueductBlock)
        {
            worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), Fluids.WATER.getTickRate(worldIn));
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(worldIn));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (!(facingState.getBlock() instanceof AqueductBlock) && !(facingState.getBlock() instanceof PaddyFieldBlock))
        {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, Fluids.WATER.getTickRate(worldIn) / 2);
        }
        if (stateIn.get(WATERLOGGED))
        {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        if (facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL)
        {
            stateIn = stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), this.canConnect(facingState));
        }
        else if (facing == Direction.DOWN)
        {
            stateIn = stateIn.with(BOTTOM, this.isAqueduct(facingState));
        }
        return stateIn;
    }

    @Override
    public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state)
    {
        return Fluids.EMPTY;
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(DISTANCE, BLOCKED, BOTTOM, WATERLOGGED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(Blocks.COBBLESTONE));
    }

    static
    {
        VoxelShape POST_0 = VoxelShapeHelper.createVoxelShape(0.0D, 4.0D, 0.0D, 4.0D, 12.0D, 4.0D);
        VoxelShape POST_1 = VoxelShapeHelper.createVoxelShape(12.0D, 4.0D, 0.0D, 4.0D, 12.0D, 4.0D);
        VoxelShape POST_2 = VoxelShapeHelper.createVoxelShape(0.0D, 4.0D, 12.0D, 4.0D, 12.0D, 4.0D);
        VoxelShape POST_3 = VoxelShapeHelper.createVoxelShape(12.0D, 4.0D, 12.0D, 4.0D, 12.0D, 4.0D);
        VoxelShape BOTTOM = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
        VoxelShape BOTTOM_OPTIONAL = VoxelShapeHelper.createVoxelShape(4.0D, 0.0D, 4.0D, 8.0D, 4.0D, 8.0D);
        VoxelShape DEFAULT = VoxelShapes.or(POST_0, POST_1, POST_2, POST_3, VoxelShapes.combine(BOTTOM, BOTTOM_OPTIONAL, IBooleanFunction.ONLY_FIRST));
        VoxelShape WALL_NORTH = VoxelShapeHelper.createVoxelShape(4.0D, 4.0D, 0.0D, 8.0D, 12.0D, 4.0D);
        VoxelShape WALL_SOUTH = VoxelShapeHelper.createVoxelShape(4.0D, 4.0D, 12.0D, 8.0D, 12.0D, 4.0D);
        VoxelShape WALL_WEST = VoxelShapeHelper.createVoxelShape(0.0D, 4.0D, 4.0D, 4.0D, 12.0D, 8.0D);
        VoxelShape WALL_EAST = VoxelShapeHelper.createVoxelShape(12.0D, 4.0D, 4.0D, 4.0D, 12.0D, 8.0D);
        SHAPES = new VoxelShape[]{DEFAULT, VoxelShapes.or(DEFAULT, WALL_EAST).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST).simplify(),
                VoxelShapes.or(DEFAULT, WALL_SOUTH).simplify(), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_SOUTH).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_SOUTH).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_SOUTH).simplify(),
                VoxelShapes.or(DEFAULT, WALL_NORTH).simplify(), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_NORTH).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_NORTH).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_NORTH).simplify(),
                VoxelShapes.or(DEFAULT, WALL_SOUTH, WALL_NORTH).simplify(), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_SOUTH, WALL_NORTH).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_SOUTH, WALL_NORTH).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_SOUTH, WALL_NORTH).simplify(),
                VoxelShapes.or(DEFAULT, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_EAST, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, BOTTOM_OPTIONAL).simplify(),
                VoxelShapes.or(DEFAULT, WALL_SOUTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_SOUTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_SOUTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_SOUTH, BOTTOM_OPTIONAL).simplify(),
                VoxelShapes.or(DEFAULT, WALL_NORTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_NORTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_NORTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_NORTH, BOTTOM_OPTIONAL).simplify(),
                VoxelShapes.or(DEFAULT, WALL_SOUTH, WALL_NORTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_SOUTH, WALL_NORTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_SOUTH, WALL_NORTH, BOTTOM_OPTIONAL).simplify(), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_SOUTH, WALL_NORTH, BOTTOM_OPTIONAL).simplify()};
    }
}
