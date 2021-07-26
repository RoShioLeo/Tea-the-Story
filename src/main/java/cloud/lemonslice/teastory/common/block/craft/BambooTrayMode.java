package cloud.lemonslice.teastory.common.block.craft;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public enum BambooTrayMode
{
    IN_RAIN,
    OUTDOORS,
    INDOORS,
    BAKE,
    PROCESS;

    public static BambooTrayMode getMode(World world, BlockPos pos)
    {
        if (isInRain(world, pos))
        {
            return IN_RAIN;
        }
        if (hasHeat(world, pos))
        {
            return BAKE;
        }
        else if (isOnFrame(world, pos))
        {
            return PROCESS;
        }
        else if (isInSun(world, pos))
        {
            return OUTDOORS;
        }
        else
        {
            return INDOORS;
        }
    }

    public static boolean isInRain(World world, BlockPos pos)
    {
        return world.isRainingAt(pos.up());
    }

    public static boolean hasHeat(World world, BlockPos pos)
    {
        return IStoveBlock.isBurning(world, pos.down());
    }

    public static boolean isInSun(World world, BlockPos pos)
    {
        return world.canBlockSeeSky(pos);
    }

    public static boolean isWorldRaining(World world)
    {
        return world.isRaining();
    }

    public static boolean isOnFrame(World world, BlockPos pos)
    {
        return world.getBlockState(pos.down()).getBlock() instanceof WoodenFrameBlock;
    }

    @Override
    public String toString()
    {
        return super.toString().toLowerCase();
    }

    public TranslationTextComponent getTranslationKey()
    {
        return new TranslationTextComponent("info.teastory.bamboo_tray.mode." + this);
    }
}
