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
import roito.teastory.block.Teaplant;

import java.util.List;

public class WailaTeaPlant implements IWailaDataProvider
{
    public static void register(IWailaRegistrar registrar)
    {
        registrar.registerBodyProvider(new WailaTeaPlant(), Teaplant.class);
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
        switch (accessor.getMetadata())
        {
            case 0:
            case 1:
                currenttip.add(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.seed"));
                return currenttip;
            case 2:
            case 3:
            case 4:
            case 5:
                currenttip.add(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.sapling"));
                return currenttip;
            case 6:
            case 7:
                currenttip.add(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.plant_1"));
                return currenttip;
            case 8:
            case 9:
                currenttip.add(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.lush"));
                return currenttip;
            case 10:
            case 11:
                currenttip.add(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.plant_2"));
                return currenttip;
            case 12:
            case 13:
                currenttip.add(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.blossom"));
                return currenttip;
            case 14:
            case 15:
                currenttip.add(I18n.format("waila.teastory.hud.tea_plant_stage.title") + I18n.format("waila.teastory.hud.tea_plant_stage.last"));
                return currenttip;
            default:
                return currenttip;
        }
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
