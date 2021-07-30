package cloud.lemonslice.teastory.common.block.drink;

import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import cloud.lemonslice.silveroak.helper.BlockHelper;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.tileentity.DrinkMakerTileEntity;
import cloud.lemonslice.teastory.common.tileentity.NormalContainerTileEntity;
import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class DrinkMakerBlock extends NormalHorizontalBlock
{
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    private boolean flag = false;
    private static final VoxelShape NORTH_LEFT = VoxelShapeHelper.createVoxelShape(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 14.0D);
    private static final VoxelShape SOUTH_LEFT = VoxelShapeHelper.createVoxelShape(0.0D, 0.0D, 1.0D, 15.0D, 4.0D, 14.0D);
    private static final VoxelShape WEST_LEFT = VoxelShapeHelper.createVoxelShape(1.0D, 0.0D, 0.0D, 14.0D, 4.0D, 15.0D);
    private static final VoxelShape EAST_LEFT = VoxelShapeHelper.createVoxelShape(1.0D, 0.0D, 1.0D, 14.0D, 4.0D, 15.0D);

    public DrinkMakerBlock()
    {
        super(Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.5F).notSolid(), "drink_maker");
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LEFT, true));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING, LEFT);
    }

    @Override
    @SuppressWarnings("deprecation")
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return 0.8F;
    }

    @Override
    @SuppressWarnings("deprecation")
    public PushReaction getPushReaction(BlockState state)
    {
        return PushReaction.DESTROY;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        if (state.get(LEFT))
        {
            switch (state.get(HORIZONTAL_FACING))
            {
                case NORTH:
                    return NORTH_LEFT;
                case SOUTH:
                    return SOUTH_LEFT;
                case EAST:
                    return EAST_LEFT;
                default:
                    return WEST_LEFT;
            }
        }
        else
        {
            switch (state.get(HORIZONTAL_FACING))
            {
                case NORTH:
                    return SOUTH_LEFT;
                case SOUTH:
                    return NORTH_LEFT;
                case EAST:
                    return WEST_LEFT;
                default:
                    return EAST_LEFT;
            }
        }
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!worldIn.isRemote)
        {
            if (player.isCreative())
            {
                removeBottomHalf(worldIn, pos, state, player);
            }
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    protected static void removeBottomHalf(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!state.get(LEFT))
        {
            Direction facing = state.get(HORIZONTAL_FACING);
            BlockPos blockPos = pos.offset(BlockHelper.getPreviousHorizontal(facing));
            BlockState blockstate = world.getBlockState(blockPos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.get(LEFT))
            {
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 35);
                world.playEvent(player, 2001, blockPos, Block.getStateId(blockstate));
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        if (state.get(LEFT))
        {
            BlockState blockstate = worldIn.getBlockState(pos.down());
            return blockstate.isSolidSide(worldIn, pos.down(), Direction.UP);
        }
        else
        {
            Direction facing = state.get(HORIZONTAL_FACING);
            BlockPos blockPos = pos.offset(BlockHelper.getPreviousHorizontal(facing));
            BlockState blockstate = worldIn.getBlockState(blockPos);
            return blockstate.matchesBlock(this);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (stateIn.get(LEFT))
        {
            if (facing == BlockHelper.getNextHorizontal(stateIn.get(HORIZONTAL_FACING)))
            {
                return facingState.getBlock() == this && !facingState.get(LEFT) ? stateIn : Blocks.AIR.getDefaultState();
            }
        }
        else if (facing == BlockHelper.getPreviousHorizontal(stateIn.get(HORIZONTAL_FACING)))
        {
            return facingState.getBlock() == this && facingState.get(LEFT) ? stateIn : Blocks.AIR.getDefaultState();
        }
        return stateIn;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (newState.getBlock() != this && !(newState.getBlock() == this))
        {
            if (state.hasTileEntity())
            {
                ((NormalContainerTileEntity) worldIn.getTileEntity(pos)).prepareForRemove();
                dropItems(worldIn, pos);
                worldIn.removeTileEntity(pos);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        List<ItemStack> list = Lists.newArrayList();
        if (state.get(LEFT)) list.add(new ItemStack(this));
        return list;
    }

    private void dropItems(World worldIn, BlockPos pos)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof DrinkMakerTileEntity)
        {
            for (int i = 0; i < 11; i++)
            {
                ItemStack stack = ((DrinkMakerTileEntity) te).decrStackSize(i, Integer.MAX_VALUE);
                if (stack != ItemStack.EMPTY)
                {
                    Block.spawnAsEntity(worldIn, pos, stack);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote)
        {
            flag = false;
            if (!state.get(LEFT))
            {
                pos = pos.offset(BlockHelper.getPreviousHorizontal(state.get(HORIZONTAL_FACING)));
            }
            TileEntity te = worldIn.getTileEntity(pos);
            FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(player.getHeldItem(handIn), 1)).ifPresent(item ->
                    te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getFace()).ifPresent(fluid ->
                            flag = FluidUtil.interactWithFluidHandler(player, handIn, fluid)));
            if (flag)
                return ActionResultType.SUCCESS;
            if (te instanceof DrinkMakerTileEntity)
            {
                ((DrinkMakerTileEntity) te).refresh();
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return state.get(LEFT);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntityTypeRegistry.DRINK_MAKER.create();
    }
}
