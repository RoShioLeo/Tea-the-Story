package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.teastory.common.block.BlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum VineType
{
    GRAPE;

    public Block getFruit()
    {
        if (getName().equals("grape"))
        {
            return BlocksRegistry.GRAPE;
        }
        else return Blocks.AIR;
    }

    public String getName()
    {
        return toString().toLowerCase();
    }
}
