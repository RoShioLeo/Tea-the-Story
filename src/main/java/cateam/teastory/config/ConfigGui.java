package cateam.teastory.config;

import java.util.Arrays;

import cateam.teastory.TeaStory;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig
{
	public ConfigGui(GuiScreen parentScreen)
	{ 
		super(parentScreen, Arrays.asList(
				(IConfigElement) new ConfigElement(ConfigLoader.config.getCategory("General")),
				(IConfigElement) new ConfigElement(ConfigLoader.config.getCategory("Drink"))
		), TeaStory.MODID, TeaStory.MODID, false, false, "Tea: The Story", "Config Interface");
	}
}
