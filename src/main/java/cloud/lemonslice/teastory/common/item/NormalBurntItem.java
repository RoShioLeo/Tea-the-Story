package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import net.minecraft.item.ItemStack;

public class NormalBurntItem extends NormalItem
{
    private final int burnTime;

    public NormalBurntItem(String name, Properties properties, int burnTime)
    {
        super(name, properties);
        this.burnTime = burnTime;
    }

    public NormalBurntItem(String name, int burnTime)
    {
        super(name, ItemRegistry.getNormalItemProperties());
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        return burnTime;
    }
}
