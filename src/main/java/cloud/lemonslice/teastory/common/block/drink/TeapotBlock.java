package cloud.lemonslice.teastory.common.block.drink;

import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import cloud.lemonslice.teastory.client.sound.SoundEventsRegistry;
import cloud.lemonslice.teastory.common.block.craft.IStoveBlock;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import cloud.lemonslice.teastory.common.tileentity.TeapotTileEntity;
import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

import static net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack.FLUID_NBT_KEY;

public class TeapotBlock extends NormalHorizontalBlock
{
    private static final VoxelShape SHAPE = makeCuboidShape(5F, 0F, 5F, 11F, 8F, 11F);

    public TeapotBlock(String name, Properties properties)
    {
        super(properties, name);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntityTypeRegistry.TEAPOT.create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = pos.getX() + 0.5D;
        double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = pos.getZ() + 0.5D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TeapotTileEntity && ((TeapotTileEntity) tileentity).getFluid() == FluidRegistry.BOILING_WATER_STILL.get())
        {
            worldIn.addParticle(ParticleTypes.CLOUD, false, d0 + d4, d1 + 0.5D, d2 + d4, 0.0D, 0.1D, 0.0D);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TeapotTileEntity)
        {
            if (IStoveBlock.isBurning(worldIn, pos.down()) && ((TeapotTileEntity) tileentity).getFluid() == Fluids.WATER)
            {
                ((TeapotTileEntity) tileentity).setFluid(FluidRegistry.BOILING_WATER_STILL.get());
            }
        }
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        if (!worldIn.isRemote)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TeapotTileEntity)
            {
                worldIn.destroyBlock(pos, false);
                Fluid fluid = ((TeapotTileEntity) te).getFluid();
                if (fluid instanceof FlowingFluid)
                    worldIn.setBlockState(pos, ((FlowingFluid) fluid).getFlowingFluid().getDefaultState().getBlockState());
                worldIn.playSound(null, pos, SoundEventsRegistry.CUP_BROKEN, SoundCategory.BLOCKS, 0.5F, 0.9F);
            }
        }

        super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (player.getHeldItem(handIn).isEmpty())
        {
            ItemHandlerHelper.giveItemToPlayer(player, getDrop(worldIn, pos));
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        else
        {
            TileEntity te = worldIn.getTileEntity(pos);
            FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(player.getHeldItem(handIn), 1)).ifPresent(item ->
                    te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getFace()).ifPresent(fluid ->
                            FluidUtil.interactWithFluidHandler(player, handIn, fluid)));
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
        spawnAsEntity(worldIn, pos, getDrop(worldIn, pos));
        worldIn.removeTileEntity(pos);
    }

    public ItemStack getDrop(World worldIn, BlockPos pos)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TeapotTileEntity)
        {
            FluidStack fluidStack = ((TeapotTileEntity) tileEntity).getFluidTank().getFluidInTank(0);
            ItemStack itemStack = new ItemStack(this);
            if (fluidStack.isEmpty())
            {
                return itemStack;
            }
            CompoundNBT fluidTag = new CompoundNBT();
            fluidStack.writeToNBT(fluidTag);
            itemStack.getOrCreateTag().put(FLUID_NBT_KEY, fluidTag);
            return itemStack;
        }
        else return ItemStack.EMPTY;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TeapotTileEntity)
        {
            FluidUtil.getFluidHandler(stack).ifPresent(f -> ((TeapotTileEntity) tileentity).setFluidTank(f.getFluidInTank(0)));
        }
    }
}
