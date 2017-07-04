package cateam.teastory.client;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.CommonProxy;
import cateam.teastory.item.ItemLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        BlockLoader.preInit();
        ItemLoader.preInit();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new ItemRenderLoader();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}
