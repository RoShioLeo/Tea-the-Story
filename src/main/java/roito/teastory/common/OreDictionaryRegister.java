package roito.teastory.common;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.item.ItemRegister;

public class OreDictionaryRegister
{
    public OreDictionaryRegister()
    {
        OreDictionary.registerOre("cropLemon", ItemRegister.lemon);
        OreDictionary.registerOre("cropRice", ItemRegister.xian_rice);
        OreDictionary.registerOre("cropStraw", ItemRegister.straw);
        OreDictionary.registerOre("cropTea", ItemRegister.tea_leaf);
        OreDictionary.registerOre("seedTea", ItemRegister.tea_seeds);
        OreDictionary.registerOre("record", ItemRegister.caichawuqu_g20);
        OreDictionary.registerOre("record", ItemRegister.caichawuqu_folk);
        OreDictionary.registerOre("record", ItemRegister.bubugao);
        OreDictionary.registerOre("record", ItemRegister.caichaji_erhu);
        OreDictionary.registerOre("record", ItemRegister.chunjiexuqu);
        OreDictionary.registerOre("record", ItemRegister.huahaoyueyuan);
        OreDictionary.registerOre("record", ItemRegister.jinshekuangwu);
        OreDictionary.registerOre("record", ItemRegister.xiyangyang);
        OreDictionary.registerOre("record", ItemRegister.yangliuqing);
        OreDictionary.registerOre("record", ItemRegister.zizhudiao);
        OreDictionary.registerOre("listAllmilk", Items.MILK_BUCKET);
        OreDictionary.registerOre("listAllwater", Items.WATER_BUCKET);
        OreDictionary.registerOre("listAllsugar", Items.SUGAR);
        OreDictionary.registerOre("listAllegg", Items.EGG);
        OreDictionary.registerOre("listAllwheat", Items.WHEAT);
    }
}
