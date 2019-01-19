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
import roito.teastory.block.Teapan;

public class TOPTeapan implements IProbeInfoProvider
{
	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
	{
		if (blockState.getBlock() instanceof Teapan)
		{
			int metaData = blockState.getBlock().getMetaFromState(blockState);
			if (metaData == 0)
			{
				probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("waila.teastory.hud.empty"));
				probeInfo.text(I18n.format("waila.teastory.hud.next") + "-");
				return;
			}
			else if (metaData == 1)
			{
				probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.wet_tea.name"));
				probeInfo.text(I18n.format("waila.teastory.hud.next") + I18n.format("item.tea_leaf.name"));
				return;
			}
			else if (metaData == 3)
			{
				probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.half_dried_tea.name"));
				String next = world.canSeeSky(data.getPos()) ? I18n.format("item.dried_tea.name") : "-";
				probeInfo.text(I18n.format("waila.teastory.hud.next") + next);
				return;
			}
			else if (metaData == 2)
			{
				probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.tea_leaf.name"));
				probeInfo.text(I18n.format("waila.teastory.hud.next") + I18n.format("item.half_dried_tea.name"));
				return;
			}
			else if (metaData == 4)
			{
				probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.dried_tea.name"));
				String next = !world.canSeeSky(data.getPos()) ? I18n.format("item.yellow_tea_leaf.name") : "-";
				probeInfo.text(I18n.format("waila.teastory.hud.next") + next);
				return;
			}
			probeInfo.text(I18n.format("waila.teastory.hud.step") + I18n.format("item.yellow_tea_leaf.name"));
			probeInfo.text(I18n.format("waila.teastory.hud.next") + "-");
		}
	}

	@Override
	public String getID()
	{
		return TeaStory.MODID + ".teapan";
	}
}
