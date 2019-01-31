package roito.teastory.common;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import roito.teastory.api.capability.IDailyDrink;
import roito.teastory.capability.CapabilityDailyDrink;

public class CapabilityRegister
{
	@CapabilityInject(IDailyDrink.class)
	public static Capability<IDailyDrink> dailyDrink;

	public CapabilityRegister()
	{
		CapabilityManager.INSTANCE.register(IDailyDrink.class, new CapabilityDailyDrink.Storage(), CapabilityDailyDrink.Implementation::new);
	}
}