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
import roito.teastory.block.Teaplant;

public class TOPTeaPlant implements IProbeInfoProvider
{
    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
    {
        if (blockState.getBlock() instanceof Teaplant)
        {
            int metaData = blockState.getBlock().getMetaFromState(blockState);
            switch (metaData)
            {
                case 0:
                case 1:
                    probeInfo.text(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.seed"));
                    return;
                case 2:
                case 3:
                case 4:
                case 5:
                    probeInfo.text(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.sapling"));
                    return;
                case 6:
                case 7:
                    probeInfo.text(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.plant_1"));
                    return;
                case 8:
                case 9:
                    probeInfo.text(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.lush"));
                    return;
                case 10:
                case 11:
                    probeInfo.text(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.plant_2"));
                    return;
                case 12:
                case 13:
                    probeInfo.text(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.blossom"));
                    return;
                case 14:
                case 15:
                    probeInfo.text(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.last"));
            }
        }
    }

    @Override
    public String getID()
    {
        return TeaStory.MODID + ".tea_plant";
    }
}
