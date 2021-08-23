package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum VineType
{
    GRAPE,
    CUCUMBER,
    BITTER_GOURD;

    public Block getFruit()
    {
        switch (this.ordinal())
        {
            case 0:
                return BlockRegistry.GRAPE;
            case 1:
                return BlockRegistry.CUCUMBER;
            case 2:
                return BlockRegistry.BITTER_GOURD;
            default:
                return Blocks.AIR;
        }
    }

    public String getName()
    {
        return toString().toLowerCase();
    }
}
