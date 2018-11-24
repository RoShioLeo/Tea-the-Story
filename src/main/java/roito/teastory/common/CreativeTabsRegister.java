package roito.teastory.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.item.ItemRegister;

public class CreativeTabsRegister
{
    public static CreativeTabs tabTeaStory;
    public static CreativeTabs tabRice;
    public static CreativeTabs tabDrink;

    public CreativeTabsRegister(FMLPreInitializationEvent event)
    {
        tabTeaStory = new CreativeTabs("tabTeaStory")
        {
            @Override
            public ItemStack createIcon()
            {
                return new ItemStack(ItemRegister.half_dried_tea);
            }
        };
        tabDrink = new CreativeTabs("tabTeaDrink")
        {
            @Override
            public ItemStack createIcon()
            {
                return new ItemStack(ItemRegister.green_tea);
            }
        };
        tabRice = new CreativeTabs("tabTeaRice")
        {
            @Override
            public ItemStack createIcon()
            {
                return new ItemStack(ItemRegister.xian_rice);
            }
        };
    }
}
