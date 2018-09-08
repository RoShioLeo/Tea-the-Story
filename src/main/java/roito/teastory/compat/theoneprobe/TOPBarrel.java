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
import roito.teastory.block.Barrel;

public class TOPBarrel implements IProbeInfoProvider
{
    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
    {
        if (blockState.getBlock() instanceof Barrel)
        {
            int metaData = blockState.getBlock().getMetaFromState(blockState);
            if (metaData == 0)
            {
                probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("waila.teastory.hud.empty"));
                probeInfo.text(I18n.format("waila.teastory.hud.next") + "-");
            } else if (metaData == 1 || metaData == 2)
            {
                probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.half_dried_tea.name"));
                probeInfo.text(I18n.format("waila.teastory.hud.next") + I18n.format("item.black_tea_leaf.name"));
            } else if (metaData == 3)
            {
                probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.black_tea_leaf.name"));
                probeInfo.text(I18n.format("waila.teastory.hud.next") + I18n.format("item.puer_tea_leaf.name"));
            } else if (metaData == 4)
            {
                probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.puer_tea_leaf.name"));
                probeInfo.text(I18n.format("waila.teastory.hud.next") + "-");
            }
        }
    }

    @Override
    public String getID()
    {
        return TeaStory.MODID + ".barrel";
    }
}
