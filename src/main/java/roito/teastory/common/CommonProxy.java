package roito.teastory.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.block.BlockLoader;
import roito.teastory.compat.waila.WailaCompatRegistry;
import roito.teastory.config.ConfigMain;
import roito.teastory.entity.EntityLoader;
import roito.teastory.inventory.GuiElementLoader;
import roito.teastory.item.ItemLoader;
import roito.teastory.potion.PotionLoader;
import roito.teastory.recipe.RecipeLoader;
import roito.teastory.tileentity.TileEntityLoader;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		new ConfigMain(event);
		new CreativeTabsLoader(event);
		new ItemLoader(event);
		new BlockLoader(event);
		new OreDictionaryLoader(event);
		new PotionLoader(event);
		new EntityLoader();
		new TileEntityLoader(event);
	}

	public void init(FMLInitializationEvent event)
	{
		new RecipeLoader();
		new CraftingLoader();
		new AchievementLoader();
		new EventLoader();
		new GuiElementLoader();
		new SeedDrops();
		new WailaCompatRegistry();
	}

	public void postInit(FMLPostInitializationEvent event)
	{
	}
}