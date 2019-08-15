package roito.teastory.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import roito.teastory.api.capability.IDailyDrink;
import roito.teastory.api.drink.DailyDrink;
import roito.teastory.common.CapabilityRegister;

public final class DrinkingHelper
{
    public static DailyDrink getLevelAndTimeImprovement(World worldIn, EntityPlayer playerIn)
    {
        long nowDay = worldIn.getWorldTime() / 24000L % 2147483647L;
        IDailyDrink dailyDrink = playerIn.getCapability(CapabilityRegister.dailyDrink, null);
        dailyDrink.updateDay(nowDay);
        long instantDay = dailyDrink.getInstantDay();
        int day = instantDay > 17 ? 8 : (int) ((instantDay - 1) / 2);
        int level = day / 3;
        float time = (day % 3) * 0.1F;
        return new DailyDrink(level, time, instantDay);
    }
}
