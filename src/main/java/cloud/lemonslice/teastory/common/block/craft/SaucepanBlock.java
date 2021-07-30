package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.List;
import java.util.Random;

public class SaucepanBlock extends NormalHorizontalBlock
{
    public static final EnumProperty<CookStep> STEP = EnumProperty.create("step", CookStep.class);
    public static final BooleanProperty LID = BooleanProperty.create("lid");
    private static final VoxelShape PAN_SHAPE;
    private static final VoxelShape LID_SHAPE;

    // TODO 方块掉落
    public SaucepanBlock()
    {
        super(Block.Properties.create(Material.IRON).notSolid().sound(SoundType.METAL).hardnessAndResistance(3.5F).tickRandomly(), "saucepan");
        this.setDefaultState(this.stateContainer.getBaseState().with(STEP, CookStep.EMPTY).with(LID, true));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return state.get(LID) ? LID_SHAPE : PAN_SHAPE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(STEP, LID);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.get(STEP) == CookStep.COOKED)
        {
            double d0 = pos.getX() + 0.5D;
            double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = pos.getZ() + 0.5D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            worldIn.addParticle(ParticleTypes.CLOUD, false, d0 + d4, d1 + 0.5D, d2 + d4, 0.0D, 0.1D, 0.0D);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote)
        {
            ItemStack held = player.getHeldItem(handIn);
            if (state.get(LID))
            {
                worldIn.setBlockState(pos, state.with(LID, false));
                player.addItemStackToInventory(new ItemStack(ItemRegistry.SAUCEPAN_LID));
                return ActionResultType.SUCCESS;
            }
            else if (state.get(STEP) == CookStep.COOKED)
            {
                worldIn.setBlockState(pos, state.with(STEP, CookStep.EMPTY));
                player.addItemStackToInventory(new ItemStack(ItemRegistry.RICE_BALL, 3));
                return ActionResultType.SUCCESS;
            }
            else if (held.getItem() == ItemRegistry.SAUCEPAN_LID)
            {
                worldIn.setBlockState(pos, state.with(LID, true));
                held.shrink(1);
                worldIn.playSound(null, player.getPosX(), player.getPosY() + 0.5, player.getPosZ(), SoundEvents.BLOCK_METAL_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
            else if (state.get(STEP) == CookStep.RAW && FluidUtil.getFluidContained(held).isPresent())
            {
                FluidStack fluidStack = FluidUtil.getFluidContained(held).get();
                if (fluidStack.getFluid() == Fluids.WATER && fluidStack.getAmount() >= 1000)
                {
                    if (FluidUtil.interactWithFluidHandler(player, handIn, new FluidTank(1000)))
                    {
                        worldIn.setBlockState(pos, state.with(STEP, CookStep.WATER));
                        return ActionResultType.SUCCESS;
                    }
                    return ActionResultType.FAIL;
                }
                return ActionResultType.FAIL;
            }
            else if (state.get(STEP) == CookStep.EMPTY && held.getItem() == ItemRegistry.WASHED_RICE && held.getCount() >= 8)
            {
                worldIn.setBlockState(pos, state.with(STEP, CookStep.RAW));
                worldIn.playSound(null, player.getPosX(), player.getPosY() + 0.5, player.getPosZ(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative())
                {
                    held.shrink(8);
                }
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState state = super.getStateForPlacement(context);
        if (state != null && context.getItem().getOrCreateTag().contains("lid"))
        {
            state = state.with(LID, false);
        }
        return state;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        BlockState stove = worldIn.getBlockState(pos.down());
        if (IStoveBlock.isBurning(stove) && state.get(STEP) == CookStep.WATER)
        {
            int fuelPower = ((IStoveBlock) stove.getBlock()).getFuelPower();
            if (state.get(LID))
            {
                if (random.nextInt(6 / fuelPower) == 0)
                {
                    worldIn.setBlockState(pos, state.with(STEP, CookStep.COOKED));
                }
            }
            else if (random.nextInt(24 / fuelPower) == 0)
            {
                worldIn.setBlockState(pos, state.with(STEP, CookStep.COOKED));
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        ItemStack pan = new ItemStack(this);
        if (!state.get(LID))
        {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putBoolean("lid", false);
            pan.setTag(nbt);
        }
        return Lists.newArrayList(pan);
    }

    static
    {
        VoxelShape outer = VoxelShapeHelper.createVoxelShape(1, 0, 1, 14, 12, 14);
        VoxelShape inner = VoxelShapeHelper.createVoxelShape(2, 1, 2, 12, 11, 12);
        LID_SHAPE = VoxelShapeHelper.createVoxelShape(1, 0, 1, 14, 13, 14);
        PAN_SHAPE = VoxelShapes.combineAndSimplify(outer, inner, IBooleanFunction.NOT_SAME);
    }
}
