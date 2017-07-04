package cateam.teastory.common;

import cateam.teastory.TeaStory;
import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.crafting.CraftingLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.inventory.GuiElementLoader;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.potion.PotionLoader;
import cateam.teastory.tileentity.TileEntityLoader;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

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