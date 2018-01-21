package cateam.teastory.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import cateam.teastory.TeaStory;
import net.minecraftforge.common.config.Config;

public class ConfigMain
{
	public static Configuration config;

	public static int greenTeaDrink_Time;
	public static int matchaDrink_Time;
	public static int blackTeaDrink_Time;
	public static boolean info;

	public ConfigMain(FMLPreInitializationEvent event)
	{
		File configDir = new File(event.getModConfigurationDirectory(), TeaStory.MODID);	
		if (!configDir.exists())
		{
			configDir.mkdirs();
		}
		config = new Configuration(new File(configDir, "TeaStory.cfg"), "2.2.0");
		
		config.load();

		registerConfig();

		if (config.hasChanged())
		{
			config.save();
		}
	}

	public static void registerConfig()
	{
		info = config.getBoolean("EnableInfo", "General", true, "Set it to false to let it not show information when players log in.", "teastory.config.general.enableinfo");
		greenTeaDrink_Time = (int)Math.max(0, config.get("Drink", "GreenTeaDrinksEffectTime", 2400).getInt());
		matchaDrink_Time = (int)Math.max(0, config.get("Drink", "MatchaDrinksEffectTime", 2400).getInt());
		blackTeaDrink_Time = (int)Math.max(0, config.get("Drink", "BlackTeaDrinksEffectTime", 2400).getInt());
	}
}
