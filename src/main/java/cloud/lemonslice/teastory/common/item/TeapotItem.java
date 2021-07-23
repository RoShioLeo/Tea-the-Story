package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalBlockItem;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack.FLUID_NBT_KEY;

public class TeapotItem extends NormalBlockItem
{
    private final int capacity;
    private final boolean canFillWater;

    public TeapotItem(Block block, int capacity, boolean fillWater)
    {
        super(block, new Properties().group(TeaStory.GROUP_DRINK).maxStackSize(1));
        this.capacity = capacity;
        this.canFillWater = fillWater;
    }

    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt)
    {
        return new FluidHandlerItemStack(stack, capacity);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        return this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType() != ActionResultType.SUCCESS ? this.tryPlace(new BlockItemUseContext(context)) : ActionResultType.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if (canFillWater)
        {
            ItemStack itemStack = playerIn.getHeldItem(handIn);
            RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (raytraceresult.getType() == RayTraceResult.Type.MISS)
            {
                return new ActionResult<>(ActionResultType.PASS, itemStack);
            }
            else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK)
            {
                return new ActionResult<>(ActionResultType.PASS, itemStack);
            }
            else
            {
                BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
                BlockPos blockpos = blockraytraceresult.getPos();
                if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemStack))
                {
                    BlockState blockstate1 = worldIn.getBlockState(blockpos);
                    if (blockstate1.getBlock() instanceof FlowingFluidBlock)
                    {
                        Fluid fluid = ((FlowingFluidBlock) blockstate1.getBlock()).getFluid();
                        if (fluid != Fluids.EMPTY && fluid.isIn(FluidTags.WATER))
                        {
                            ((FlowingFluidBlock) blockstate1.getBlock()).pickupFluid(worldIn, blockpos, blockstate1);
                            playerIn.addStat(Stats.ITEM_USED.get(this));

                            SoundEvent soundevent = SoundEvents.ITEM_BOTTLE_FILL;
                            playerIn.playSound(soundevent, 1.0F, 1.0F);

                            if (!playerIn.isCreative())
                            {
                                ItemStack itemStack1 = new ItemStack(this);
                                CompoundNBT fluidTag = new CompoundNBT();
                                new FluidStack(fluid, capacity).writeToNBT(fluidTag);
                                itemStack1.getOrCreateTag().put(FLUID_NBT_KEY, fluidTag);
                                ItemHandlerHelper.giveItemToPlayer(playerIn, itemStack1);

                                itemStack.shrink(1);
                            }

                            return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
                        }
                    }
                    return new ActionResult<>(ActionResultType.FAIL, itemStack);
                }
                else
                {
                    return new ActionResult<>(ActionResultType.FAIL, itemStack);
                }
            }
        }
        else return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items)
    {
        if (group == TeaStory.GROUP_DRINK)
        {
            items.add(new ItemStack(this));
            for (Fluid fluid : FluidTags.getCollection().getTagByID(new ResourceLocation("teastory:drink")).getAllElements())
            {
                ItemStack itemStack = new ItemStack(this);
                CompoundNBT fluidTag = new CompoundNBT();
                new FluidStack(fluid, capacity).writeToNBT(fluidTag);
                itemStack.getOrCreateTag().put(FLUID_NBT_KEY, fluidTag);
                items.add(itemStack);
            }
            ItemStack itemStack = new ItemStack(this);
            CompoundNBT fluidTag = new CompoundNBT();
            new FluidStack(FluidRegistry.BOILING_WATER_STILL.get(), capacity).writeToNBT(fluidTag);
            itemStack.getOrCreateTag().put(FLUID_NBT_KEY, fluidTag);
            items.add(itemStack);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.getChildTag(FLUID_NBT_KEY) != null)
        {
            FluidUtil.getFluidHandler(stack).ifPresent(f ->
                    tooltip.add(((IFormattableTextComponent) f.getFluidInTank(0).getDisplayName()).appendString(String.format(": %d / %dmB", f.getFluidInTank(0).getAmount(), capacity)).mergeStyle(TextFormatting.GRAY)));
        }
    }
}
