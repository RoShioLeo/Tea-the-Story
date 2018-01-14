package cateam.teastory;

import cateam.teastory.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TeaStory.MODID, name = TeaStory.NAME, version = TeaStory.VERSION, updateJSON = "https://raw.githubusercontent.com/LuoXiao-Wing/Tea-TheStory/1.10.2/update.json")
public class TeaStory
{
    public static final String MODID = "teastory";
    public static final String NAME = "Tea: The Story";
    public static final String VERSION = "@version@";

    @Instance(TeaStory.MODID)
    public static TeaStory instance;
    
    @SidedProxy(clientSide = "cateam.teastory.client.ClientProxy", 
                         serverSide = "cateam.teastory.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
}
