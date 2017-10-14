package cateam.teastory.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigLoader
{
	private static Configuration config;
	
	public static int TeaDrink_Time;
	public static boolean info;
	
	public ConfigLoader(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		registerConfig();
		
		config.save();
	}
	
	private static void registerConfig()
	{
		info = config.get(Configuration.CATEGORY_GENERAL, "Enable Info", true).getBoolean();
		TeaDrink_Time = config.get(Configuration.CATEGORY_GENERAL, "Tea Drinks Effect Time", 2400).getInt();
	}
}
