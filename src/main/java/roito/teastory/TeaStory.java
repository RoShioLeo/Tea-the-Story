package roito.teastory;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.common.CommonProxy;

@Mod(modid = TeaStory.MODID,
			name = TeaStory.NAME,
			version = TeaStory.VERSION,
			//updateJSON = "https://raw.githubusercontent.com/LuoXiao-Wing/Tea-TheStory/1.10.2/update.json",
			guiFactory = TeaStory.CONFIG_GUI_CLASS,
			acceptedMinecraftVersions = "[1.10.2,1.11)",
			dependencies = "required-after:Forge@[12.18.3.2185,);required-after:JEI@[3.14.7.416,);")

public class TeaStory
{
	public static final String MODID = "teastory";
	public static final String NAME = "Tea: The Story";
	public static final String VERSION = "@version@";
	public static final String CONFIG_GUI_CLASS = "cateam.teastory.config.ConfigGuiFactory";

	@Instance(TeaStory.MODID)
	public static TeaStory instance;

	@SidedProxy(clientSide = "roito.teastory.client.ClientProxy",
						serverSide = "roito.teastory.common.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
