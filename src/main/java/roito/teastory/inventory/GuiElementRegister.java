package roito.teastory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import roito.teastory.TeaStory;
import roito.teastory.client.gui.GuiContainerBarrel;
import roito.teastory.client.gui.GuiContainerTeaStove;
import roito.teastory.client.gui.GuiContainerTeaTable;
import roito.teastory.client.gui.GuiContainerTeapan;

public class GuiElementRegister implements IGuiHandler
{
	public static final int GUI_TEASTOVE = 1;
	public static final int GUI_TEATABLE = 2;
	public static final int GUI_TEAPAN = 3;
	public static final int GUI_BARREL = 4;

	public GuiElementRegister()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(TeaStory.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case GUI_TEASTOVE:
				return new ContainerTeaStove(player, world.getTileEntity(new BlockPos(x, y, z)));
			case GUI_TEATABLE:
				return new ContainerTeaTable(player, world.getTileEntity(new BlockPos(x, y, z)));
			case GUI_TEAPAN:
				return new ContainerTeapan(player, world.getTileEntity(new BlockPos(x, y, z)));
			case GUI_BARREL:
				return new ContainerBarrel(player, world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case GUI_TEASTOVE:
				return new GuiContainerTeaStove(new ContainerTeaStove(player, world.getTileEntity(new BlockPos(x, y, z))));
			case GUI_TEATABLE:
				return new GuiContainerTeaTable(new ContainerTeaTable(player, world.getTileEntity(new BlockPos(x, y, z))));
			case GUI_TEAPAN:
			return new GuiContainerTeapan(new ContainerTeapan(player, world.getTileEntity(new BlockPos(x, y, z))));
			case GUI_BARREL:
				return new GuiContainerBarrel(new ContainerBarrel(player, world.getTileEntity(new BlockPos(x, y, z))));
			default:
				return null;
		}
	}


}
