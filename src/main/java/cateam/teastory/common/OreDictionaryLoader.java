package cateam.teastory.common;

import cateam.teastory.item.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryLoader
{
	public OreDictionaryLoader(FMLPreInitializationEvent event)
    {
        OreDictionary.registerOre("cropLemon", new ItemStack(ItemLoader.lemon));
        OreDictionary.registerOre("cropRice", new ItemStack(ItemLoader.rice));
        OreDictionary.registerOre("cropStraw", new ItemStack(ItemLoader.straw));
    }
}
