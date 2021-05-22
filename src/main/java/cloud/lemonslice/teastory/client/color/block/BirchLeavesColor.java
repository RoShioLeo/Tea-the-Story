package cloud.lemonslice.teastory.client.color.block;

import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import cloud.lemonslice.teastory.common.environment.solar.SolarTerm;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

public class BirchLeavesColor implements IBlockColor
{
    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int index)
    {
        if (Minecraft.getInstance().world != null)
        {
            return Minecraft.getInstance().world.getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).map(data ->
                    SolarTerm.get(data.getSolarTermIndex()).getColorInfo().getBirchColor()).orElse(FoliageColors.getBirch());
        }
        return FoliageColors.getBirch();
    }
}
