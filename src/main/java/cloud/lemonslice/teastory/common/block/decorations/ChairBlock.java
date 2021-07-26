package cloud.lemonslice.teastory.common.block.decorations;

import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.entity.SeatEntity;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.List;

public class ChairBlock extends NormalHorizontalBlock
{
    private final static VoxelShape NORTH_SHAPE;
    private final static VoxelShape EAST_SHAPE;
    private final static VoxelShape WEST_SHAPE;
    private final static VoxelShape SOUTH_SHAPE;

    public ChairBlock(String name, Properties properties)
    {
        super(properties, name);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
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

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        double x = 0, z = 0;
        switch (state.get(HORIZONTAL_FACING))
        {
            case NORTH:
                z = 0.25;
                break;
            case SOUTH:
                z = -0.25;
                break;
            case EAST:
                x = -0.25;
                break;
            default:
                x = 0.25;
        }
        return SeatEntity.createSeat(worldIn, pos, player, 0.25, x, z);
    }

    @Override
    @SuppressWarnings("deprecation")
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return 1.0F;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return 60;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(this));
    }

    static
    {
        VoxelShape north_seat = VoxelShapeHelper.createVoxelShape(1D, 0D, 2D, 14D, 8D, 14D);
        VoxelShape north_back = VoxelShapeHelper.createVoxelShape(1D, 8D, 15D, 14D, 13D, 1D);
        VoxelShape south_seat = VoxelShapeHelper.createVoxelShape(1D, 0D, 0D, 14D, 8D, 14D);
        VoxelShape south_back = VoxelShapeHelper.createVoxelShape(1D, 8D, 0D, 14D, 13D, 1D);
        VoxelShape east_seat = VoxelShapeHelper.createVoxelShape(0D, 0D, 1D, 14D, 8D, 14D);
        VoxelShape east_back = VoxelShapeHelper.createVoxelShape(0D, 8D, 1D, 1D, 13D, 14D);
        VoxelShape west_seat = VoxelShapeHelper.createVoxelShape(2D, 0D, 1D, 14D, 8D, 14D);
        VoxelShape west_back = VoxelShapeHelper.createVoxelShape(15D, 8D, 1D, 1D, 13D, 14D);
        NORTH_SHAPE = VoxelShapes.or(north_seat, north_back);
        EAST_SHAPE = VoxelShapes.or(east_seat, east_back);
        WEST_SHAPE = VoxelShapes.or(west_seat, west_back);
        SOUTH_SHAPE = VoxelShapes.or(south_seat, south_back);
    }

}
