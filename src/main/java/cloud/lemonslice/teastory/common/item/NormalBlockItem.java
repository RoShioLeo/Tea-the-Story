package cloud.lemonslice.teastory.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class NormalBlockItem extends BlockItem
{
    public NormalBlockItem(Block blockIn, Properties builder)
    {
        super(blockIn, builder);
        this.setRegistryName(blockIn.getRegistryName());
    }

    public NormalBlockItem(Block blockIn)
    {
        super(blockIn, NormalItem.getNormalItemProperties());
        this.setRegistryName(blockIn.getRegistryName());
    }
}
