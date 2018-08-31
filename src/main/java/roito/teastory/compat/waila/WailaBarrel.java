package roito.teastory.compat.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import roito.teastory.block.Barrel;

import java.util.List;

public class WailaBarrel implements IWailaDataProvider
{
	public static void register(IWailaRegistrar registrar)
	{
        registrar.registerBodyProvider(new WailaBarrel(), Barrel.class);
    }

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		if (accessor.getMetadata() == 0)
		{
            currenttip.add(I18n.format("waila.teastory.hud.step") + I18n.format("waila.teastory.hud.empty"));
            currenttip.add(I18n.format("waila.teastory.hud.next") + "-");
			return currenttip;
		}
		else if (accessor.getMetadata() == 1 || accessor.getMetadata() == 2)
		{
            currenttip.add(I18n.format("waila.teastory.hud.step") + I18n.format("item.half_dried_tea.name"));
            currenttip.add(I18n.format("waila.teastory.hud.next") + I18n.format("item.black_tea_leaf.name"));
			return currenttip;
		}
		else if (accessor.getMetadata() == 3)
		{
            currenttip.add(I18n.format("waila.teastory.hud.step") + I18n.format("item.black_tea_leaf.name"));
            currenttip.add(I18n.format("waila.teastory.hud.next") + I18n.format("item.puer_tea_leaf.name"));
			return currenttip;
		}
		else if (accessor.getMetadata() == 4)
		{
            currenttip.add(I18n.format("waila.teastory.hud.step") + I18n.format("item.puer_tea_leaf.name"));
            currenttip.add(I18n.format("waila.teastory.hud.next") + "-");
			return currenttip;
		}
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
	{
		return tag;
	}
	
}
