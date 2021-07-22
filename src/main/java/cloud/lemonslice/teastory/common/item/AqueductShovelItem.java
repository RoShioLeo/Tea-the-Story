package cloud.lemonslice.teastory.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

public class AqueductShovelItem extends ShovelItem
{
    public AqueductShovelItem(String id, IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
    {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.setRegistryName(id);
    }
}
