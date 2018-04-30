package roito.teastory.config;

import java.util.Arrays;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import roito.teastory.TeaStory;

public class ConfigGui extends GuiConfig
{
	public ConfigGui(GuiScreen parentScreen)
	{ 
		super(parentScreen, Arrays.asList(
				new ConfigElement(ConfigMain.config.getCategory("General")),
				new ConfigElement(ConfigMain.config.getCategory("Drink"))
		), TeaStory.MODID, TeaStory.MODID, false, false, "Tea: The Story", "Config Interface");
	}
}
