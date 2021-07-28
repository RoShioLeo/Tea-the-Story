package cloud.lemonslice.teastory.common.block.craft;

import net.minecraft.util.IStringSerializable;

public enum CookStep implements IStringSerializable
{
    EMPTY,
    RAW,
    WATER,
    COOKED;

    @Override
    public String getString()
    {
        return toString().toLowerCase();
    }
}
