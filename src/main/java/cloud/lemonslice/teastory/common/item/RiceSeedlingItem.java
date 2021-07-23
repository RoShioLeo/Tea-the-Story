package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class RiceSeedlingItem extends NormalItem
{
    public RiceSeedlingItem(String name)
    {
        super(name, ItemRegistry.getNormalItemProperties());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() == RayTraceResult.Type.MISS)
        {
            return ActionResult.resultPass(itemstack);
        }
        else
        {
            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK)
            {
                BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
                BlockPos blockpos = blockraytraceresult.getPos();
                Direction direction = blockraytraceresult.getFace();
                if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(direction), direction, itemstack))
                {
                    return ActionResult.resultFail(itemstack);
                }

                BlockPos blockpos1 = blockpos.up();
                BlockState blockstate = worldIn.getBlockState(blockpos);
                FluidState ifluidstate = worldIn.getFluidState(blockpos);
                if ((ifluidstate.getFluid() == Fluids.WATER || blockstate.getBlock() == BlockRegistry.PADDY_FIELD) && worldIn.isAirBlock(blockpos1))
                {
                    net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.create(worldIn.getDimensionKey(), worldIn, blockpos1);
                    worldIn.setBlockState(blockpos1, BlockRegistry.RICE_PLANT.getDefaultState(), 11);
                    if (net.minecraftforge.event.ForgeEventFactory.onBlockPlace(playerIn, blocksnapshot, net.minecraft.util.Direction.UP))
                    {
                        blocksnapshot.restore(true, false);
                        return ActionResult.resultFail(itemstack);
                    }

                    if (playerIn instanceof ServerPlayerEntity)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerIn, blockpos1, itemstack);
                    }

                    if (!playerIn.abilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }

                    playerIn.addStat(Stats.ITEM_USED.get(this));
                    worldIn.playSound(playerIn, blockpos, BlockRegistry.RICE_PLANT.getDefaultState().getSoundType(worldIn, blockpos, playerIn).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResult.resultSuccess(itemstack);
                }
            }

            return ActionResult.resultFail(itemstack);
        }
    }
}
