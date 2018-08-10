package roito.teastory.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.item.ItemLoader;

public class OreDictionaryLoader
{
	public OreDictionaryLoader(FMLPreInitializationEvent event)
    {
        OreDictionary.registerOre("cropLemon", ItemLoader.lemon);
        OreDictionary.registerOre("cropRice", ItemLoader.xian_rice);
        OreDictionary.registerOre("cropStraw", ItemLoader.straw);
        OreDictionary.registerOre("record", ItemLoader.caichawuqu_g20);
        OreDictionary.registerOre("record", ItemLoader.caichawuqu_folk);
        OreDictionary.registerOre("cropTea", ItemLoader.tea_leaf);
        OreDictionary.registerOre("seedTea", ItemLoader.tea_seeds);
    }
}
