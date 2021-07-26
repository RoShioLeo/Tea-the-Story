package cloud.lemonslice.teastory.common.block.drink;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.tileentity.TeaCupTileEntity;
import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

import static net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack.FLUID_NBT_KEY;

public class WoodenTrayBlock extends NormalBlock
{
    public static final IntegerProperty DRINK = IntegerProperty.create("drink", 0, 3);
    public static final IntegerProperty CUP = IntegerProperty.create("cup", 0, 3);

    private static final VoxelShape TRAY = VoxelShapeHelper.createVoxelShape(0, 0, 0, 16, 2, 16);
    private static final VoxelShape WITH_CUP = VoxelShapeHelper.createVoxelShape(0, 0, 0, 16, 6, 16);

    public WoodenTrayBlock()
    {
        super("wooden_tray", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.5F).notSolid());
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        if (state.get(CUP) > 0)
        {
            return WITH_CUP;
        }
        else
        {
            return TRAY;
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(CUP, DRINK);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntityTypeRegistry.PORCELAIN_CUP.create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        ItemStack held = player.getHeldItem(handIn);
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TeaCupTileEntity)
        {
            if (held.isEmpty())
            {
                int index = state.get(CUP) - 1;
                if (index > -1)
                {
                    ItemStack itemStack = getCup((TeaCupTileEntity) te, index);
                    ItemHandlerHelper.giveItemToPlayer(player, itemStack);
                    if (itemStack.getItem() == ItemRegistry.PORCELAIN_CUP)
                    {
                        worldIn.setBlockState(pos, state.with(CUP, state.get(CUP) - 1));
                    }
                    else
                    {
                        worldIn.setBlockState(pos, state.with(CUP, state.get(CUP) - 1).with(DRINK, state.get(DRINK) - 1));
                    }
                }
            }
            else
            {
                int index = state.get(CUP);
                if (!setCup((TeaCupTileEntity) te, index, held, worldIn, pos, state))
                {
                    if (held.getItem() == ItemRegistry.PORCELAIN_TEAPOT)
                    {
                        FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(player.getHeldItem(handIn), 1)).ifPresent(item ->
                        {
                            for (int i = 0; i < 3; i++)
                            {
                                FluidTank tank = ((TeaCupTileEntity) te).getFluidTank(i);
                                if (tank.isEmpty())
                                {
                                    if (FluidUtil.interactWithFluidHandler(player, handIn, tank))
                                    {
                                        if (state.get(DRINK) + 1 <= 3)
                                        {
                                            worldIn.setBlockState(pos, state.with(DRINK, state.get(DRINK) + 1));
                                        }
                                        worldIn.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 0.5F, 0.9F);
                                    }
                                    break;
                                }
                            }
                        });
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    public boolean setCup(TeaCupTileEntity te, int index, ItemStack itemStack, World world, BlockPos pos, BlockState state)
    {
        if (index >= 3)
        {
            return false;
        }
        if (itemStack.getItem() == ItemRegistry.PORCELAIN_CUP_DRINK || itemStack.getItem() == ItemRegistry.PORCELAIN_CUP)
        {
            FluidStack stack = FluidUtil.getFluidContained(itemStack).orElse(FluidStack.EMPTY);
            if (!stack.isEmpty())
            {
                if (state.get(DRINK) + 1 <= 3)
                {
                    world.setBlockState(pos, state.with(CUP, state.get(CUP) + 1).with(DRINK, state.get(DRINK) + 1));
                    world.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 0.5F, 0.9F);
                }
            }
            else
            {
                world.setBlockState(pos, state.with(CUP, state.get(CUP) + 1));
                world.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 0.5F, 0.9F);
            }
            te.setFluidTank(index, stack);
            itemStack.shrink(1);
            return true;
        }
        else return false;
    }

    public ItemStack getCup(TeaCupTileEntity te, int index)
    {
        if (index > 3)
        {
            return ItemStack.EMPTY;
        }
        FluidStack fluidStack = te.getFluidTank(index).getFluidInTank(0);
        if (fluidStack.isEmpty())
        {
            return new ItemStack(ItemRegistry.PORCELAIN_CUP);
        }
        else
        {
            ItemStack itemStack = new ItemStack(ItemRegistry.PORCELAIN_CUP_DRINK);
            CompoundNBT fluidTag = new CompoundNBT();
            fluidStack.writeToNBT(fluidTag);
            itemStack.getOrCreateTag().put(FLUID_NBT_KEY, fluidTag);
            return itemStack;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TeaCupTileEntity)
            {
                for (int i = 0; i < 3; i++)
                {
                    if (!worldIn.isRemote && state.get(CUP) > i)
                    {
                        Block.spawnAsEntity(worldIn, pos, getCup((TeaCupTileEntity) tileEntity, i));
                    }
                }
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(this));
    }
}
