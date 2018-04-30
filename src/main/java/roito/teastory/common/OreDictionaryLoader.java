package roito.teastory.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.item.ItemLoader;

public class OreDictionaryLoader
{
	public OreDictionaryLoader(FMLInitializationEvent event)
    {
        OreDictionary.registerOre("cropLemon", new ItemStack(ItemLoader.lemon));
        OreDictionary.registerOre("cropRice", new ItemStack(ItemLoader.xian_rice));
        OreDictionary.registerOre("cropStraw", new ItemStack(ItemLoader.straw));
        OreDictionary.registerOre("record", new ItemStack(ItemLoader.caichawuqu_g20));
        OreDictionary.registerOre("record", new ItemStack(ItemLoader.caichawuqu_folk));
    }
}
