package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.tileentity.NormalContainerTileEntity;
import cloud.lemonslice.teastory.common.tileentity.StoveTileEntity;
import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.List;
import java.util.Random;

public class StoveBlock extends NormalHorizontalBlock implements IStoveBlock
{
    protected int efficiency;
    private static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final VoxelShape SHAPE;

    public StoveBlock(Properties properties, String name, int efficiency)
    {
        super(properties, name);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LIT, false));
        this.efficiency = efficiency;
    }

    @Override
    public int getFuelPower()
    {
        return efficiency;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING, LIT);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (IStoveBlock.isBurning(stateIn))
        {
            double d0 = pos.getX() + 0.5D;
            double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = pos.getZ() + 0.5D;

            TileEntity te = worldIn.getTileEntity(pos);

            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(inv ->
            {
                int ash = inv.getStackInSlot(0).getCount();
                if (ash < 32)
                {
                    for (int i = 0; i < ash / 4 + 1; i++)
                    {
                        double d4 = rand.nextDouble() * 0.6D - 0.3D;
                        worldIn.addParticle(ParticleTypes.SMOKE, false, d0 + d4, d1 + 1.0D, d2 + d4, 0.0D, 0.1D, 0.0D);
                    }
                }
                else
                {
                    for (int i = 0; i < ash / 5; i++)
                    {
                        double d4 = rand.nextDouble() * 0.6D - 0.3D;
                        worldIn.addParticle(ParticleTypes.LARGE_SMOKE, false, d0 + d4, d1 + 1.0D, d2 + d4, 0.0D, 0.1D, 0.0D);
                    }
                }
                double d4 = rand.nextDouble() * 0.6D - 0.3D;
                worldIn.addParticle(ParticleTypes.FLAME, false, d0 + d4, d1, d2 + d4, 0.0D, 0.06D, 0.0D);
            });
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        Item held = player.getHeldItem(handIn).getItem();
        if (held == BlockRegistry.BAMBOO_TRAY.asItem() || held == ItemRegistry.IRON_KETTLE)
        {
            return ActionResultType.PASS;
        }
        if (te instanceof StoveTileEntity)
        {
            if (player.isSneaking())
            {
                if (!worldIn.isRemote)
                {
                    ((StoveTileEntity) te).refresh();
                    NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
                }
                return ActionResultType.SUCCESS;
            }
            else
            {
                if (held.equals(Items.FLINT_AND_STEEL))
                {
                    ((StoveTileEntity) te).setToLit();
                    player.getHeldItem(handIn).damageItem(1, player, onBroken -> onBroken.sendBreakAnimation(handIn));
                    worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.getRandom().nextFloat() * 0.4F + 0.8F);
                    return ActionResultType.SUCCESS;
                }
                else if (held.equals(Items.FIRE_CHARGE))
                {
                    ((StoveTileEntity) te).setToLit();
                    player.getHeldItem(handIn).shrink(1);
                    return ActionResultType.SUCCESS;
                }
                else if (ForgeHooks.getBurnTime(player.getHeldItem(handIn)) > 0)
                {
                    te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(fuel ->
                    {
                        player.setHeldItem(handIn, fuel.insertItem(0, player.getHeldItem(handIn), false));
                        te.markDirty();
                    });
                    return ActionResultType.SUCCESS;
                }
                else if (((StoveTileEntity) te).isDoubleClick())
                {
                    dropFuel(worldIn, pos);
                    return ActionResultType.SUCCESS;
                }
                else
                {
                    dropAsh(worldIn, pos);
                    if (!worldIn.isRemote)
                        ((StoveTileEntity) te).singleClickStart();
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntityTypeRegistry.STOVE_TILE.create();
    }

    private void dropAsh(World worldIn, BlockPos pos)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null)
        {
            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(ash ->
            {
                for (int i = ash.getSlots() - 1; i >= 0; --i)
                {
                    if (ash.getStackInSlot(i) != ItemStack.EMPTY)
                    {
                        Block.spawnAsEntity(worldIn, pos, ash.getStackInSlot(i));
                        ((IItemHandlerModifiable) ash).setStackInSlot(i, ItemStack.EMPTY);
                    }
                }
            });
        }
    }

    private void dropFuel(World worldIn, BlockPos pos)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null)
        {
            te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(fuel ->
            {
                for (int i = fuel.getSlots() - 1; i >= 0; --i)
                {
                    if (fuel.getStackInSlot(i) != ItemStack.EMPTY)
                    {
                        Block.spawnAsEntity(worldIn, pos, fuel.getStackInSlot(i));
                        ((IItemHandlerModifiable) fuel).setStackInSlot(i, ItemStack.EMPTY);
                    }
                }
            });
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.hasTileEntity() && newState.getBlock() != this)
        {
            ((NormalContainerTileEntity) worldIn.getTileEntity(pos)).prepareForRemove();
            dropFuel(worldIn, pos);
            dropAsh(worldIn, pos);
            worldIn.removeTileEntity(pos);
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        BlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active)
        {
            worldIn.setBlockState(pos, iblockstate.with(HORIZONTAL_FACING, iblockstate.get(HORIZONTAL_FACING)).with(LIT, true));
        }
        else
        {
            worldIn.setBlockState(pos, iblockstate.with(HORIZONTAL_FACING, iblockstate.get(HORIZONTAL_FACING)).with(LIT, false));
        }

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity)
        {
            if (!entityIn.isImmuneToFire() && IStoveBlock.isBurning(worldIn, pos) && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn))
            {
                if (worldIn.rand.nextBoolean())
                    entityIn.attackEntityFrom(DamageSource.IN_FIRE, 1.0F);
                ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60));
            }
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
    {
        return new ItemStack(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(this));
    }

    static
    {
        VoxelShape top = VoxelShapeHelper.createVoxelShape(0.0D, 14.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape body = VoxelShapeHelper.createVoxelShape(1.0D, 0.0D, 1.0D, 14.0D, 16.0D, 14.0D);
        SHAPE = VoxelShapes.or(top, body);
    }
}
