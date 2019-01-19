package roito.teastory.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import roito.teastory.TeaStory;

import java.util.Collections;
import java.util.Set;

public class ConfigGuiFactory implements IModGuiFactory
{

	@Override
	public void initialize(Minecraft minecraftInstance)
	{

	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	@Override
	public boolean hasConfigGui()
	{
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen)
	{
		return new GuiConfig(parentScreen, Collections.singletonList(ConfigElement.from(ConfigMain.class)), TeaStory.MODID, false, false, "TeaStory", I18n.format("teastory.config.3"));
	}

}