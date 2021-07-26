package cloud.lemonslice.teastory.common.handler.event;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.block.crops.MelonVineBlock;
import cloud.lemonslice.teastory.common.block.crops.TeaPlantBlock;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public final class AgricultureEventHandler
{
    public static void boneMealUsingLimit(BonemealEvent event)
    {
        if (!ServerConfig.Agriculture.canUseBoneMeal.get())
        {
            if (event.getBlock().getBlock() instanceof TeaPlantBlock)
            {
                if (event.getBlock().get(TeaPlantBlock.AGE) == 7 || event.getBlock().get(TeaPlantBlock.AGE) == 6)
                {
                    event.setResult(Event.Result.DEFAULT);
                }
                else
                {
                    event.setCanceled(true);
                }
            }
            else if (event.getBlock().getBlock() instanceof IPlantable && ((IPlantable) event.getBlock().getBlock()).getPlantType(event.getWorld(), event.getPos()) == PlantType.CROP)
            {
                event.setCanceled(true);
            }
            else
                event.setResult(Event.Result.DEFAULT);
        }
    }

    public static void onAqueductShovelUsing(BlockEvent.BlockToolInteractEvent event)
    {

    }

    @SuppressWarnings("deprecation")
    public static void plantMelon(PlayerInteractEvent.RightClickBlock event)
    {
        BlockPos posToPlace = event.getPos().offset(event.getHitVec().getFace());
        BlockState toPlace = event.getWorld().getBlockState(posToPlace);
        Block farmland = event.getWorld().getBlockState(posToPlace.down()).getBlock();

        ItemStack melon_seeds = event.getPlayer().getHeldItem(event.getHand());
        if (farmland instanceof FarmlandBlock)
        {
            if (melon_seeds.getItem() == Items.MELON_SEEDS && toPlace.isAir())
            {
                event.getWorld().setBlockState(posToPlace, BlockRegistry.WATERMELON_VINE.getDefaultState());
                event.getWorld().playSound(null, posToPlace, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!event.getPlayer().isCreative())
                {
                    melon_seeds.shrink(1);
                }
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }
    }

    public static void cutMelon(PlayerInteractEvent.RightClickBlock event)
    {
        BlockState melon = event.getWorld().getBlockState(event.getPos());
        if (melon.getBlock() == Blocks.MELON && event.getPlayer().getHeldItem(event.getHand()).getItem() instanceof SwordItem)
        {
            event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState());
            event.getPlayer().getHeldItem(event.getHand()).damageItem(1, event.getPlayer(), e -> e.sendBreakAnimation(event.getHand()));
            event.getWorld().playSound(null, event.getPos().up(), SoundEvents.BLOCK_STEM_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!event.getWorld().isRemote)
            {
                List<ItemStack> list = melon.getDrops(new LootContext.Builder((ServerWorld) event.getWorld()).withRandom(event.getWorld().rand).withParameter(LootParameters.ORIGIN, Vector3d.copyCentered(event.getPos())).withParameter(LootParameters.TOOL, event.getPlayer().getHeldItem(event.getHand())));
                for (ItemStack drop : list)
                {
                    Block.spawnAsEntity(event.getWorld(), event.getPos(), drop);
                }
            }
            event.setCanceled(true);
            event.setCancellationResult(ActionResultType.SUCCESS);
        }
    }

    public static void stopTramplingMelonField(BlockEvent.FarmlandTrampleEvent event)
    {
        if (event.getWorld().getBlockState(event.getPos().up()).getBlock() instanceof MelonVineBlock)
        {
            event.setCanceled(true);
        }
    }
}
