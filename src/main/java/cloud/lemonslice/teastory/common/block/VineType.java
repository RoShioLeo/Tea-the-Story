package cloud.lemonslice.teastory.common.block;

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
