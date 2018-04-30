package roito.teastory.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.TeaStory;

import java.io.File;

public class ConfigMain
{
	public static Configuration config;

	public static int greenTeaDrink_Time;
	public static int matchaDrink_Time;
	public static int blackTeaDrink_Time;
	public static int milkTeaDrink_Time;
	public static int lemonTeaDrink_Time;
	public static int yellowTeaDrink_Time;
	public static int whiteTeaDrink_Time;
	public static int oolongTeaDrink_Time;
	public static int puerTeaDrink_Time;
	public static boolean info;

	public ConfigMain(FMLPreInitializationEvent event)
	{
		File configDir = new File(event.getModConfigurationDirectory(), TeaStory.MODID);	
		if (!configDir.exists())
		{
			configDir.mkdirs();
		}
		config = new Configuration(new File(configDir, "TeaStory.cfg"), "3.0.1");
		
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
		greenTeaDrink_Time = config.getInt("GreenTeaDrinksEffectTime", "Drink", 1800, 0, 12000, "The ticks of the effect of green tea. (1 second = 20 ticks)", "teastory.config.drink.greentea");
		matchaDrink_Time = config.getInt("MatchaDrinksEffectTime", "Drink", 2400, 0, 12000, "The ticks of the effect of matcha. (1 second = 20 ticks)", "teastory.config.drink.matchadrink");
		blackTeaDrink_Time = config.getInt("BlackTeaDrinksEffectTime", "Drink", 1800, 0, 12000, "The ticks of the effect of black tea. (1 second = 20 ticks)", "teastory.config.drink.blacktea");
		milkTeaDrink_Time = config.getInt("MilkTeaDrinksEffectTime", "Drink", 480, 0, 12000, "The ticks of the effect of milk tea. (1 second = 20 ticks)", "teastory.config.drink.milktea");
		lemonTeaDrink_Time = config.getInt("LemonTeaDrinksEffectTime", "Drink", 480, 0, 12000, "The ticks of the effect of lemon tea. (1 second = 20 ticks)", "teastory.config.drink.lemontea");
		yellowTeaDrink_Time = config.getInt("YellowTeaDrinksEffectTime", "Drink", 300, 0, 12000, "The ticks of the effect of yellow tea. (1 second = 20 ticks)", "teastory.config.drink.yellowtea");
		whiteTeaDrink_Time = config.getInt("WhiteTeaDrinksEffectTime", "Drink", 1800, 0, 12000, "The ticks of the effect of white tea. (1 second = 20 ticks)", "teastory.config.drink.whitetea");
		oolongTeaDrink_Time = config.getInt("OolongTeaDrinksEffectTime", "Drink", 1800, 0, 12000, "The ticks of the effect of oolong tea. (1 second = 20 ticks)", "teastory.config.drink.oolongtea");
		puerTeaDrink_Time = config.getInt("PuerTeaDrinksEffectTime", "Drink", 1800, 0, 12000, "The ticks of the effect of pu'er tea. (1 second = 20 ticks)", "teastory.config.drink.puertea");
	}
}
