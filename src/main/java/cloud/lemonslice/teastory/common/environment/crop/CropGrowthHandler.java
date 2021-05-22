package cloud.lemonslice.teastory.common.environment.crop;

import cloud.lemonslice.silveroak.common.environment.Humidity;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.helper.SeasonHelper;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TeaStory.MODID)
public final class CropGrowthHandler
{
    @SubscribeEvent
    public static void canCropGrowUp(BlockEvent.CropGrowEvent.Pre event)
    {
        Block block = event.getState().getBlock();
        World world = (World) event.getWorld();
        BlockPos pos = event.getPos();
        CropSeasonInfo seasonInfo = CropInfoManager.getSeasonInfo(block);
        if (seasonInfo != null && ServerConfig.Season.enable.get())
        {
            if (seasonInfo.isSuitable(SeasonHelper.getSeason(world)))
            {
                Humidity env = Humidity.getHumid(world.getBiome(pos).getDownfall(), world.getBiome(pos).getTemperature(pos));
                CropHumidityInfo humidityInfo = CropInfoManager.getHumidityInfo(block);
                checkHumidity(event, world, humidityInfo, env);
            }
            else if (world.getRandom().nextInt(100) < 25)
            {
                Humidity env = Humidity.getHumid(world.getBiome(pos).getDownfall(), world.getBiome(pos).getTemperature(pos));
                CropHumidityInfo humidityInfo = CropInfoManager.getHumidityInfo(block);
                checkHumidity(event, world, humidityInfo, env);
            }
            else
            {
                event.setResult(Event.Result.DENY);
            }
        }
        else
        {
            Humidity env = Humidity.getHumid(world.getBiome(pos).getDownfall(), world.getBiome(pos).getTemperature(pos));
            CropHumidityInfo humidityInfo = CropInfoManager.getHumidityInfo(block);
            checkHumidity(event, world, humidityInfo, env);
        }
    }

    public static void checkHumidity(BlockEvent.CropGrowEvent.Pre event, IWorld world, CropHumidityInfo humidityInfo, Humidity env)
    {
        if (humidityInfo != null)
        {
            float f = humidityInfo.getGrowChance(env);
            if (f == 0)
            {
                event.setResult(Event.Result.DENY);
            }
            else if (f > 1.0F)
            {
                event.setResult(Event.Result.ALLOW);
            }
            else
            {
                if (world.getRandom().nextInt(1000) < 1000 * f)
                {
                    event.setResult(Event.Result.DEFAULT);
                }
                else
                {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}
