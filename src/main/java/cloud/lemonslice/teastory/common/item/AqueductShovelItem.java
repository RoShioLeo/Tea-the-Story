package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.block.crops.AqueductBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;

import java.util.Collections;

public class AqueductShovelItem extends ToolItem
{
    public AqueductShovelItem(String id, IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
    {
        super(attackDamageIn, attackSpeedIn, tier, Collections.emptySet(), builder.addToolType(ToolType.SHOVEL, tier.getHarvestLevel()));
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
        if (context.getFace() == Direction.DOWN)
        {
            return ActionResultType.PASS;
        }
        else
        {
            PlayerEntity playerEntity = context.getPlayer();

            if (blockState.getBlock() instanceof FarmlandBlock)
            {
                if (!world.isRemote)
                {
                    world.setBlockState(blockPos, BlockRegistry.PADDY_FIELD.getDefaultState(), 3);
                    if (playerEntity != null)
                    {
                        context.getItem().damageItem(1, playerEntity, player -> player.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                    }
                }
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
            else if (blockState.getBlock().isIn(Tags.Blocks.DIRT) || blockState.getMaterial() == Material.EARTH)
            {
                if (!world.isRemote)
                {
                    world.setBlockState(blockPos, ((AqueductBlock) BlockRegistry.DIRT_AQUEDUCT).getStateForPlacement(world, blockPos), 3);
                    world.getPendingBlockTicks().scheduleTick(blockPos, BlockRegistry.DIRT_AQUEDUCT, Fluids.WATER.getTickRate(world));
                    if (playerEntity != null)
                    {
                        context.getItem().damageItem(1, playerEntity, player -> player.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                    }
                }
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
            else if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT))
            {
                if (!world.isRemote())
                {
                    world.playEvent(null, 1009, blockPos, 0);
                }

                CampfireBlock.extinguish(world, blockPos, blockState);

                if (!world.isRemote)
                {
                    world.setBlockState(blockPos, blockState.with(CampfireBlock.LIT, false), 11);
                    if (playerEntity != null)
                    {
                        context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                    }
                }
                return ActionResultType.func_233537_a_(world.isRemote);
            }
            else
            {
                return ActionResultType.PASS;
            }
        }
    }
}
