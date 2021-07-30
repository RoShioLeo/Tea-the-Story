package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.environment.crop.CropInfoManager;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Random;

public class TrellisWithVineBlock extends TrellisBlock
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE_0_7;
    private static final VoxelShape[] SHAPES;
    private final VineType type;

    public TrellisWithVineBlock(String name, VineType type, Properties properties)
    {
        super(name + "_" + type.getName(), properties.tickRandomly());
        this.type = type;
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(DISTANCE, 0).with(POST, false).with(UP, false).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false));
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
    {
        return new ItemStack(getEmptyTrellis());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        // Grow vertically. 垂直方向生长。
        if (hasPost(state))
        {
            int i = state.get(AGE);
            float f = 8.0F; //TODO Connected with humidity.
            if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0))
            {
                if (i < 3)
                {
                    worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
                }
                else
                {
                    BlockState up = worldIn.getBlockState(pos.up());
                    if (up.getBlock() instanceof TrellisBlock && !(up.getBlock() instanceof TrellisWithVineBlock))
                    {
                        worldIn.setBlockState(pos.up(), CropInfoManager.getVineTrellis(type, (TrellisBlock) up.getBlock()).getRelevantState(up).with(DISTANCE, state.get(DISTANCE)), 2);
                    }
                }
                ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                return;
            }
        }
        // Grow horizontally and bear fruit. 水平方向蔓延和结果。
        if (hasHorizontalBar(state))
        {
            int i = state.get(AGE);
            float f = 5.0F; //TODO Connected with humidity.
            if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0))
            {
                if (!hasPost(state))
                {
                    if (random.nextBoolean()) // Leaves grow up.
                    {
                        if (worldIn.getBlockState(pos.down()).getBlock() != type.getFruit())
                            worldIn.setBlockState(pos, state.with(AGE, (i + 1) % 4), 2);
                    }
                    else // Bear fruit.
                    {
                        if (worldIn.getBlockState(pos.down()).isAir() && !hasNearFruit(worldIn, pos.down(), type.getFruit()))
                        {
                            worldIn.setBlockState(pos, state.with(AGE, (i + 1) % 4));
                            worldIn.setBlockState(pos.down(), type.getFruit().getDefaultState());
                            ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                            return;
                        }
                    }
                }
                BlockPos blockPos = pos;
                switch (random.nextInt(4))
                {
                    case 0:
                        blockPos = blockPos.north();
                        break;
                    case 1:
                        blockPos = blockPos.south();
                        break;
                    case 2:
                        blockPos = blockPos.east();
                        break;
                    default:
                        blockPos = blockPos.west();
                }
                BlockState next = worldIn.getBlockState(blockPos);
                if (next.getBlock() instanceof TrellisBlock && !(next.getBlock() instanceof TrellisWithVineBlock) && state.get(DISTANCE) < 7)
                {
                    worldIn.setBlockState(blockPos, CropInfoManager.getVineTrellis(type, (TrellisBlock) next.getBlock()).getRelevantState(next).with(DISTANCE, state.get(DISTANCE) + 1), 2);
                }
                ForgeHooks.onCropsGrowPost(worldIn, pos, state);
            }
        }
    }

    public static boolean hasNearFruit(IBlockReader worldIn, BlockPos pos, Block fruit)
    {
        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            BlockState state = worldIn.getBlockState(pos.offset(direction));
            if (state.matchesBlock(fruit))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        int bar = hasHorizontalBar(state) ? 4 : 0;
        int post = hasPost(state) ? 2 : 0;
        int up = state.get(UP) ? 1 : 0;
        return SHAPES[bar + post + up];
    }

    public int getNearDistance(IWorld world, BlockPos pos)
    {
        int distance = 7;
        distance = Math.min(distance, getDistance(world.getBlockState(pos.north())));
        distance = Math.min(distance, getDistance(world.getBlockState(pos.south())));
        distance = Math.min(distance, getDistance(world.getBlockState(pos.east())));
        distance = Math.min(distance, getDistance(world.getBlockState(pos.west())));
        return distance;
    }

    public int getDistance(BlockState state)
    {
        if (state.getBlock() instanceof TrellisWithVineBlock && ((TrellisWithVineBlock) state.getBlock()).type == type)
        {
            return state.get(DISTANCE);
        }
        return 7;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        // Update the connecting state of trellis. 更新棚架方块的连接状态。
        if (facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL)
        {
            stateIn = stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), this.canConnect(facingState, facingState.isSolidSide(worldIn, facingPos, facing.getOpposite())));
        }
        else if (facing == Direction.DOWN)
        {
            BlockPos posDown = currentPos.offset(facing);
            BlockState state = worldIn.getBlockState(posDown);
            stateIn = stateIn.with(POST, state.isIn(BlockTags.WOODEN_FENCES) || state.isSolidSide(worldIn, posDown, Direction.UP));
        }
        else if (facing == Direction.UP)
        {
            BlockPos posUp = currentPos.offset(facing);
            BlockState state = worldIn.getBlockState(posUp);
            stateIn = stateIn.with(UP, state.isIn(BlockTags.WOODEN_FENCES) || state.isSolidSide(worldIn, posUp, Direction.DOWN));
        }

        // To judge whether vines can be stay here. 判断缠绕藤（棚架）作物在此是否合理。
        // Distance should be within 7. 攀爬距离应该小于等于7。
        boolean valid = false;
        if (hasHorizontalBar(stateIn))
        {
            int distance = getNearDistance(worldIn, currentPos);
            if (distance < 7)
            {
                stateIn = stateIn.with(DISTANCE, distance + 1);
                valid = true;
            }
        }
        if (hasPost(stateIn))
        {
            BlockState down = worldIn.getBlockState(currentPos.down());
            if (down.getBlock().isIn(Tags.Blocks.DIRT))
            {
                stateIn = stateIn.with(DISTANCE, 0);
                valid = true;
            }
            else if (down.getBlock() instanceof TrellisWithVineBlock)
            {
                if (down.get(AGE) == 3 && ((TrellisWithVineBlock) down.getBlock()).type == type)
                {
                    stateIn = stateIn.with(DISTANCE, down.get(DISTANCE));
                    valid = true;
                }
            }
        }
        if (!valid)
        {
            stateIn = CropInfoManager.getEmptyTrellis((TrellisWithVineBlock) stateIn.getBlock()).getRelevantState(stateIn);
        }
        return stateIn;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(AGE, DISTANCE);
    }

    public Block getEmptyTrellis()
    {
        return CropInfoManager.getEmptyTrellis(this);
    }

    @Override
    public BlockState getRelevantState(BlockState old)
    {
        BlockState newState = super.getRelevantState(old);
        return newState.with(AGE, 0);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(getEmptyTrellis()));
    }

    public VineType getType()
    {
        return type;
    }

    static
    {
        VoxelShape TOP_SHAPE = VoxelShapeHelper.createVoxelShape(0.0D, 4.0D, 0.0D, 16.0D, 9.0D, 16.0D);
        VoxelShape POST_SHAPE = VoxelShapeHelper.createVoxelShape(6.0D, 0.0D, 6.0D, 4.0D, 12.0D, 4.0D);
        VoxelShape POST_UP_SHAPE = VoxelShapeHelper.createVoxelShape(6.0D, 7.0D, 6.0D, 4.0D, 9.0D, 4.0D);
        SHAPES = new VoxelShape[]{TOP_SHAPE, POST_UP_SHAPE, POST_SHAPE, VoxelShapes.or(POST_UP_SHAPE, POST_SHAPE),
                TOP_SHAPE, VoxelShapes.or(TOP_SHAPE, POST_UP_SHAPE), VoxelShapes.or(TOP_SHAPE, POST_SHAPE), VoxelShapes.or(TOP_SHAPE, POST_UP_SHAPE, POST_SHAPE)};
    }
}
