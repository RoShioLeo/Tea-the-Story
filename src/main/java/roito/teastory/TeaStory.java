package roito.teastory;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import roito.teastory.common.CommonProxy;

@Mod(modid = TeaStory.MODID,
		name = TeaStory.NAME,
		version = TeaStory.VERSION,
		updateJSON = "https://raw.githubusercontent.com/LuoXiao-Wing/Tea-TheStory/1.10.2-3.X/update.json",
		guiFactory = TeaStory.CONFIG_GUI_CLASS,
		acceptedMinecraftVersions = "[1.12.2,1.13)",
		dependencies = "required-after:forge@[14.23.4.2741,);" +
				"after:jei@[4.12.0.215,);" +
				"after:waila@[1.8.23-B38,);" +
				"after:theoneprobe@[1.4.22,);" +
				"after:toughasnails@[3.1.0,);"
)

public class TeaStory
{
	public static final String MODID = "teastory";
	public static final String NAME = "Tea: The Story";
	public static final String VERSION = "@version@";
	public static final String CONFIG_GUI_CLASS = "roito.teastory.config.ConfigGuiFactory";

	public static Logger logger;

	@Instance(TeaStory.MODID)
	public static TeaStory instance;

	@SidedProxy(clientSide = "roito.teastory.client.ClientProxy",
			serverSide = "roito.teastory.common.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
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
