package roito.teastory.compat.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import roito.teastory.block.BlockLoader;
import roito.teastory.block.FullKettle;

public class WailaFullKettle implements IWailaDataProvider
{
	public static void register(IWailaRegistrar registrar)
	{
        registrar.registerBodyProvider(new WailaFullKettle(), FullKettle.class);
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
		FullKettle kettle = (FullKettle) accessor.getBlock();
		if (!kettle.full && kettle.getNextKettle() == BlockLoader.empty_zisha_kettle)
		{
			currenttip.add(I18n.translateToLocalFormatted("teastory.tooltip.kettle.remain", 4 - itemStack.getItemDamage() / 4, kettle.getMaxCapacity()));
		}
		else
		{
			currenttip.add(I18n.translateToLocalFormatted("teastory.tooltip.kettle.remain", kettle.getMaxCapacity() - itemStack.getItemDamage() / 4, kettle.getMaxCapacity()));
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
