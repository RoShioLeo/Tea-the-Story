package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.tileentity.NormalContainerTileEntity;
import cloud.lemonslice.teastory.common.tileentity.StoneRollerTileEntity;
import com.google.common.collect.Lists;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

import static cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry.STONE_ROLLER;

public class StoneRollerBlock extends NormalBlock
{
    private static final VoxelShape SHAPE = VoxelShapeHelper.createVoxelShape(0, 0, 0, 16, 12, 16);

    public StoneRollerBlock()
    {
        super("stone_roller", AbstractBlock.Properties.create(Material.ROCK).notSolid().hardnessAndResistance(1.5F).sound(SoundType.STONE));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return STONE_ROLLER.create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.hasTileEntity() && !(newState.getBlock() == this))
        {
            ((NormalContainerTileEntity) worldIn.getTileEntity(pos)).prepareForRemove();
            dropItems(worldIn, pos);
            worldIn.removeTileEntity(pos);
        }
    }

    private void dropItems(World worldIn, BlockPos pos)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null)
        {
            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(inv ->
            {
                for (int i = inv.getSlots() - 1; i >= 0; --i)
                {
                    if (inv.getStackInSlot(i) != ItemStack.EMPTY)
                    {
                        Block.spawnAsEntity(worldIn, pos, inv.getStackInSlot(i));
                        ((IItemHandlerModifiable) inv).setStackInSlot(i, ItemStack.EMPTY);
                    }
                }
            });
            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(inv ->
            {
                for (int i = inv.getSlots() - 1; i >= 0; --i)
                {
                    if (inv.getStackInSlot(i) != ItemStack.EMPTY)
                    {
                        Block.spawnAsEntity(worldIn, pos, inv.getStackInSlot(i));
                        ((IItemHandlerModifiable) inv).setStackInSlot(i, ItemStack.EMPTY);
                    }
                }
            });
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(this));
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof StoneRollerTileEntity)
            {
                if (!playerIn.isSneaking())
                {
                    if (!playerIn.getHeldItem(handIn).isEmpty())
                    {
                        return te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).map(container ->
                        {
                            playerIn.setHeldItem(handIn, container.insertItem(0, playerIn.getHeldItem(handIn), false));
                            return ActionResultType.SUCCESS;
                        }).orElse(ActionResultType.FAIL);
                    }
                    else
                    {
                        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(container ->
                        {
                            for (int i = 0; i <= 2; i++)
                            {
                                ItemStack itemStack = container.extractItem(i, 64, false);
                                if (!itemStack.isEmpty())
                                {
                                    Block.spawnAsEntity(worldIn, pos, itemStack);
                                }
                            }
                        });
                        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(container ->
                        {
                            if (((StoneRollerTileEntity) te).isCompleted())
                            {
                                ItemStack itemStack = container.extractItem(0, container.getStackInSlot(0).getCount(), false);
                                Block.spawnAsEntity(worldIn, pos, itemStack);
                            }
                        });
                        return ActionResultType.SUCCESS;
                    }
                }
                else
                {
                    NetworkHooks.openGui((ServerPlayerEntity) playerIn, (INamedContainerProvider) te, te.getPos());
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
