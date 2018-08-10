package roito.teastory.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.block.BlockRegister;
import roito.teastory.common.CommonProxy;
import roito.teastory.item.ItemRegister;


public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		BlockRegister.loadExtraResourceLocation();
		ItemRegister.loadExtraResourceLocation();
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
