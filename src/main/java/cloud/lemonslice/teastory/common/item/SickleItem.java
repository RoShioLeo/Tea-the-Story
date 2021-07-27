package cloud.lemonslice.teastory.common.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;

import java.util.Collections;

public class SickleItem extends ToolItem
{
    public SickleItem(String id, IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
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

//    @Override
//    public ActionResultType onItemUse(ItemUseContext context)
//    {
//
//    }
}
