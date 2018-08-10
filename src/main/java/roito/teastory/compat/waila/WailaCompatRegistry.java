package roito.teastory.compat.waila;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class WailaCompatRegistry
{
	public WailaCompatRegistry()
	{
		if (Loader.isModLoaded("Waila"))
		{
			FMLInterModComms.sendMessage("Waila", "register", "roito.teastory.compat.waila.WailaBarrel.register");
			FMLInterModComms.sendMessage("Waila", "register", "roito.teastory.compat.waila.WailaTeapan.register");
			FMLInterModComms.sendMessage("Waila", "register", "roito.teastory.compat.waila.WailaFullKettle.register");
		}
	}
}
