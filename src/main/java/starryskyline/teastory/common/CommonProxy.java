package starryskyline.teastory.common;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import starryskyline.teastory.TeaStory;
import starryskyline.teastory.achievement.AchievementLoader;
import starryskyline.teastory.block.BlockLoader;
import starryskyline.teastory.crafting.CraftingLoader;
import starryskyline.teastory.creativetab.CreativeTabsLoader;
import starryskyline.teastory.inventory.GuiElementLoader;
import starryskyline.teastory.item.ItemLoader;
import starryskyline.teastory.potion.PotionLoader;
import starryskyline.teastory.tileentity.TileEntityLoader;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
    	new ConfigLoader(event);
    	new CreativeTabsLoader(event);
    	new ItemLoader(event);
    	new BlockLoader(event);
    	new PotionLoader(event);
    	new TileEntityLoader(event);
    }

    public void init(FMLInitializationEvent event)
    {
    	new CraftingLoader();
    	new AchievementLoader();
    	new EventLoader();
    	new GuiElementLoader();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}