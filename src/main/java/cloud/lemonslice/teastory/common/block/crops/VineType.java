package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum VineType
{
    GRAPE;

    public Block getFruit()
    {
        if (getName().equals("grape"))
        {
            return BlockRegistry.GRAPE;
        }
        else return Blocks.AIR;
    }

    public String getName()
    {
        return toString().toLowerCase();
    }
}
