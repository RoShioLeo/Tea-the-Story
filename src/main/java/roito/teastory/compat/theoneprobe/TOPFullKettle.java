package roito.teastory.compat.theoneprobe;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.block.FullKettle;

public class TOPFullKettle implements IProbeInfoProvider
{
    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
    {
        if (blockState.getBlock() instanceof FullKettle)
        {
            FullKettle kettle = (FullKettle) blockState.getBlock();
            if (data.getPickBlock() != null)
            {
                probeInfo.text(I18n.format("teastory.tooltip.kettle.remain", kettle.getMaxCapacity() - data.getPickBlock().getItemDamage(), kettle.getMaxCapacity()));
            }
        }
    }

    @Override
    public String getID()
    {
        return TeaStory.MODID + ".full_kettle";
    }
}
