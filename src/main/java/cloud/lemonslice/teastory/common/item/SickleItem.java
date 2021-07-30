package cloud.lemonslice.teastory.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.Collections;

public class SickleItem extends ToolItem
{
    public SickleItem(String id, IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
    {
        super(attackDamageIn, attackSpeedIn, tier, Collections.emptySet(), builder.addToolType(ToolType.get("sickle"), tier.getHarvestLevel()));
        this.setRegistryName(id);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state)
    {
        if (getToolTypes(stack).stream().anyMatch(state::isToolEffective)) return efficiency;
        return 1.0F;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        stack.damageItem(1, entityLiving, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        harvestCrops(worldIn, pos, 0);
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    private static void harvestCrops(World worldIn, BlockPos pos, int time)
    {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block instanceof IGrowable)
        {
            if (!((IGrowable) block).canGrow(worldIn, pos, worldIn.getBlockState(pos), worldIn.isRemote))
            {
                worldIn.destroyBlock(pos, true);
                if (time < 8)
                {
                    harvestCrops(worldIn, pos.east(), time + 1);
                    harvestCrops(worldIn, pos.north(), time + 1);
                    harvestCrops(worldIn, pos.west(), time + 1);
                    harvestCrops(worldIn, pos.south(), time + 1);
                }
            }
        }
    }
}
