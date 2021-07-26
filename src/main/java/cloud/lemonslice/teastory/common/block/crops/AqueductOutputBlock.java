package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.data.tag.NormalTags;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class AqueductOutputBlock extends AqueductBlock
{
    private static final VoxelShape[] SHAPES;

    public AqueductOutputBlock(String name, Properties properties)
    {
        super(name, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (player.getHeldItem(handIn).getItem().isIn(NormalTags.Items.DIRT))
        {
            worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
            if (!worldIn.isRemote)
            {
                Block.spawnAsEntity(worldIn, pos, new ItemStack(Blocks.COBBLESTONE));
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canConnect(BlockState state)
    {
        return state.getBlock() instanceof PaddyFieldBlock || state.getBlock() instanceof AqueductBlock;
    }

    @Override
    public int getDistance(BlockState state, boolean isUp)
    {
        if (!canConnect(state))
        {
            return 64;
        }
        if (!isUp)
        {
            if (state.getBlock() instanceof AqueductBlock)
            {
                return state.get(DISTANCE);
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
        return 64;
    }

    @Override
    public void updateWater(ServerWorld worldIn, BlockPos pos, BlockState origin)
    {
        BlockState state = worldIn.getBlockState(pos);
        if (canConnect(state))
        {
            if (state.getBlock() instanceof AqueductBlock)
                worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), Fluids.WATER.getTickRate(worldIn));
            else if (state.getBlock() instanceof PaddyFieldBlock)
            {
                worldIn.setBlockState(pos, state.with(PaddyFieldBlock.WATER, origin.get(WATERLOGGED) ? PaddyFieldBlock.Water.POUR : PaddyFieldBlock.Water.DRAIN));
                ((PaddyFieldBlock) state.getBlock()).updateWater(worldIn, pos);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        boolean flag = false;
        int nearDistance = getNearDistance(worldIn, pos);
        int currentDistance = state.get(DISTANCE);
        if (nearDistance + 1 < currentDistance)
        {
            state = state.with(WATERLOGGED, true).with(DISTANCE, nearDistance + 1);
            updateWater(worldIn, pos.north(), state);
            updateWater(worldIn, pos.south(), state);
            updateWater(worldIn, pos.east(), state);
            updateWater(worldIn, pos.west(), state);
            flag = true;
        }
        else if (nearDistance + 1 != currentDistance)
        {
            state = state.with(WATERLOGGED, false).with(DISTANCE, 64);
            updateWater(worldIn, pos.north(), state);
            updateWater(worldIn, pos.south(), state);
            updateWater(worldIn, pos.east(), state);
            updateWater(worldIn, pos.west(), state);
            flag = true;
        }
        if (flag)
        {
            worldIn.setBlockState(pos, state);
            updateWater(worldIn, pos, state);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (!(facingState.getBlock() instanceof AqueductBlock) && !(facingState.getBlock() instanceof PaddyFieldBlock))
        {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, Fluids.WATER.getTickRate(worldIn));
        }
        if (facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL)
        {
            stateIn = stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), this.canConnect(facingState));
        }
        return stateIn;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        int north = state.get(NORTH) ? 0 : 8;
        int south = state.get(SOUTH) ? 0 : 4;
        int west = state.get(WEST) ? 0 : 2;
        int east = state.get(EAST) ? 0 : 1;
        return SHAPES[north + south + west + east];
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.DIRT));
    }

    static
    {
        VoxelShape POST_0 = VoxelShapeHelper.createVoxelShape(0.0D, 4.0D, 0.0D, 3.0D, 11.0D, 3.0D);
        VoxelShape POST_1 = VoxelShapeHelper.createVoxelShape(13.0D, 4.0D, 0.0D, 3.0D, 11.0D, 3.0D);
        VoxelShape POST_2 = VoxelShapeHelper.createVoxelShape(0.0D, 4.0D, 13.0D, 3.0D, 11.0D, 3.0D);
        VoxelShape POST_3 = VoxelShapeHelper.createVoxelShape(13.0D, 4.0D, 13.0D, 3.0D, 11.0D, 3.0D);
        VoxelShape BOTTOM = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
        VoxelShape DEFAULT = VoxelShapes.or(POST_0, POST_1, POST_2, POST_3, BOTTOM);
        VoxelShape WALL_NORTH = VoxelShapeHelper.createVoxelShape(3.0D, 4.0D, 0.0D, 10.0D, 11.0D, 3.0D);
        VoxelShape WALL_SOUTH = VoxelShapeHelper.createVoxelShape(3.0D, 4.0D, 13.0D, 10.0D, 11.0D, 3.0D);
        VoxelShape WALL_WEST = VoxelShapeHelper.createVoxelShape(0.0D, 4.0D, 3.0D, 3.0D, 11.0D, 10.0D);
        VoxelShape WALL_EAST = VoxelShapeHelper.createVoxelShape(13.0D, 4.0D, 3.0D, 3.0D, 11.0D, 10.0D);
        SHAPES = new VoxelShape[]{DEFAULT, VoxelShapes.or(DEFAULT, WALL_EAST), VoxelShapes.or(DEFAULT, WALL_WEST), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST),
                VoxelShapes.or(DEFAULT, WALL_SOUTH), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_SOUTH), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_SOUTH), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_SOUTH),
                VoxelShapes.or(DEFAULT, WALL_NORTH), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_NORTH), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_NORTH), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_NORTH),
                VoxelShapes.or(DEFAULT, WALL_SOUTH, WALL_NORTH), VoxelShapes.or(DEFAULT, WALL_EAST, WALL_SOUTH, WALL_NORTH), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_SOUTH, WALL_NORTH), VoxelShapes.or(DEFAULT, WALL_WEST, WALL_EAST, WALL_SOUTH, WALL_NORTH)};
    }
}
