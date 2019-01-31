package roito.teastory.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.block.BlockRegister;
import roito.teastory.compat.waila.WailaCompatRegistry;
import roito.teastory.entity.EntityRegister;
import roito.teastory.inventory.GuiElementRegister;
import roito.teastory.item.ItemRegister;
import roito.teastory.potion.PotionRegister;
import roito.teastory.tileentity.TileEntityRegister;

@Mod.EventBusSubscriber
public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		new CreativeTabsRegister();
		new ItemRegister();
		new BlockRegister();
		new PotionRegister();
		new EntityRegister();
		new TileEntityRegister();
		new CapabilityRegister();
	}

	public void init(FMLInitializationEvent event)
	{
		new OreDictionaryRegister();
		new RecipeRegister();
		RecipeRegister.doDelayTask();
		new SmeltingReipeRegister();
		new EventRegister();
		new GuiElementRegister();
		new SeedDrops();
		new WailaCompatRegistry();

		FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "roito.teastory.compat.theoneprobe.TOPCompatRegistry");
	}

	public void postInit(FMLPostInitializationEvent event)
	{
	}
}