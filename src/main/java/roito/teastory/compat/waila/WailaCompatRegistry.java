package roito.teastory.compat.waila;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class WailaCompatRegistry
{
	public WailaCompatRegistry()
	{
		if (Loader.isModLoaded("waila"))
		{
			FMLInterModComms.sendMessage("waila", "register", "roito.teastory.compat.waila.WailaTeapan.register");
			FMLInterModComms.sendMessage("waila", "register", "roito.teastory.compat.waila.WailaFullKettle.register");
			FMLInterModComms.sendMessage("waila", "register", "roito.teastory.compat.waila.WailaTeaPlant.register");
		}
	}
}
