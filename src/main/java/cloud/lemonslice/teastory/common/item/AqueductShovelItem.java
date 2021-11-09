package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.block.crops.AqueductBlock;
import cloud.lemonslice.teastory.common.block.crops.PaddyFieldBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

public class AqueductShovelItem extends ShovelItem
{
    public AqueductShovelItem(String id, IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
    {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.setRegistryName(id);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state)
    {
        if (getToolTypes(stack).stream().anyMatch(state::isToolEffective)) return efficiency;
        return 1.0F;
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn)
    {
        return blockIn.matchesBlock(Blocks.SNOW) || blockIn.matchesBlock(Blocks.SNOW_BLOCK);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        World world = context.getWorld();
        BlockPos blockPos = context.getPos();
        BlockState blockState = world.getBlockState(blockPos);
        PlayerEntity playerEntity = context.getPlayer();
        if (blockState.getBlock().isIn(Tags.Blocks.COBBLESTONE))
        {
            if (!world.isRemote)
            {
                world.setBlockState(blockPos, ((AqueductBlock) BlockRegistry.COBBLESTONE_AQUEDUCT).getStateForPlacement(world, blockPos), 3);
                world.getPendingBlockTicks().scheduleTick(blockPos, BlockRegistry.COBBLESTONE_AQUEDUCT, Fluids.WATER.getTickRate(world));
                if (playerEntity != null)
                {
                    context.getItem().damageItem(1, playerEntity, player -> player.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                }
            }
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResultType.SUCCESS;
        }
        else if (blockState.getBlock() == Blocks.MOSSY_COBBLESTONE)
        {
            if (!world.isRemote)
            {
                world.setBlockState(blockPos, ((AqueductBlock) BlockRegistry.MOSSY_COBBLESTONE_AQUEDUCT).getStateForPlacement(world, blockPos), 3);
                world.getPendingBlockTicks().scheduleTick(blockPos, BlockRegistry.MOSSY_COBBLESTONE_AQUEDUCT, Fluids.WATER.getTickRate(world));
                if (playerEntity != null)
                {
                    context.getItem().damageItem(1, playerEntity, player -> player.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                }
            }
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResultType.SUCCESS;
        }
        else if (blockState.getBlock() instanceof FarmlandBlock)
        {
            if (!world.isRemote)
            {
                world.setBlockState(blockPos, ((PaddyFieldBlock) BlockRegistry.PADDY_FIELD).getStateForPlacement(world, blockPos), 3);
                if (playerEntity != null)
                {
                    context.getItem().damageItem(1, playerEntity, player -> player.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                }
            }
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResultType.SUCCESS;
        }
        else return super.onItemUse(context);
    }
}
