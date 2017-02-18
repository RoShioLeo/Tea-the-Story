package starryskyline.teastory.client;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import starryskyline.teastory.block.BlockLoader;
import starryskyline.teastory.common.CommonProxy;
import starryskyline.teastory.item.ItemLoader;


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
