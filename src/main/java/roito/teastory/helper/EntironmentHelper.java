package roito.teastory.helper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import roito.teastory.config.ConfigMain;

public class EntironmentHelper 
{
	public static float getFermentationChance(World worldIn, BlockPos pos, Boolean test) 
	{
		float f;
		Biome biome = worldIn.getBiome(pos);
		boolean isDaytime = worldIn.getWorldTime() % 24000L < 12000L;
		float humidity = biome.getRainfall();
		float temperature = biome.getTemperature(pos);
		if (ConfigMain.isFermentationLimited)
		{
			if (temperature <= 0.8F) 
			{
				f = humidity * 2.0F * temperature;
			}
			else 
			{
				f = humidity * (4F - 3 * temperature);
			}
			if (f < 0.0F)
				f = 0.0F;
		}
		else
		{
			f = 0.8F;
		}

		if (!test) 
		{
			if (!worldIn.canSeeSky(pos))
				return f;
			else
				return f = f * 0.8F;
		} 
		else 
		{
			return f * 3;
		}
	}

	public static float getDryingChance(World worldIn, BlockPos pos)
	{
		boolean isDaytime = worldIn.getWorldTime() % 24000L < 12000L;
		boolean isRaining = worldIn.isRaining();
		if (isRaining) 
		{
			return 0.0F;
		}
		return getDryingChance(worldIn, pos, isDaytime);
	}

	public static float getDryingChance(World worldIn, BlockPos pos, boolean isDaytime)
	{
		float f;
		if (isDaytime) 
		{
			f = worldIn.getLightFromNeighbors(pos) * 0.06F;
		} 
		else 
		{
			f = worldIn.getLightFor(EnumSkyBlock.BLOCK, pos) * 0.025F;
		}
		Biome biome = worldIn.getBiome(pos);
		float humidity = biome.getRainfall();
		float temperature = biome.getTemperature(pos);
		if (ConfigMain.isDryingLimited)
		{
			if (temperature <= 1.3F) 
			{
				f = f * (1 - humidity) * temperature;
			} 
			else {
				f = f * 0.7F * (1 - humidity) * temperature;
			}
			if (f < 0.0F)
				f = 0.0F;
		}
		else
		{
			f = f * 0.9F;
		}
		return f * 3;
	}

	public static String getFermentationRateLevel(float fermentationChance)
	{
		return (fermentationChance >= 0.50F) ? (fermentationChance >= 1.00F) ? I18n.translateToLocal("teastory.message.soil_detection_meter.fast") : I18n.translateToLocal("teastory.message.soil_detection_meter.normal") : I18n.translateToLocal("teastory.message.soil_detection_meter.slow");
	}

	public static Object getFermentationRate(float fermentationChance)
	{
		return (fermentationChance >= 0.50F) ? (fermentationChance >= 1.00F) ? TextFormatting.GREEN + String.valueOf((int) (fermentationChance * 100) + "%") : TextFormatting.YELLOW + String.valueOf((int) (fermentationChance * 100) + "%") : TextFormatting.RED + String.valueOf((int) (fermentationChance * 100) + "%");
	}

	public static String getDryingRateLevel(float dryingChance)
	{
		return ((dryingChance) >= 0.50F) ? ((dryingChance) >= 1.0F) ? I18n.translateToLocal("teastory.message.soil_detection_meter.fast") : I18n.translateToLocal("teastory.message.soil_detection_meter.normal") : I18n.translateToLocal("teastory.message.soil_detection_meter.slow");
	}

	public static Object getDryingRate(float dryingChance)
	{
		return ((dryingChance) >= 0.50F) ? ((dryingChance) >= 1.0F) ? TextFormatting.GREEN + String.valueOf((int) (dryingChance * 100) + "%") : TextFormatting.YELLOW + String.valueOf((int) (dryingChance * 100) + "%") : TextFormatting.RED + String.valueOf((int) (dryingChance * 100) + "%");
	}

	/* -1: Snowy, 0: Cold , 1: Warm, 2: Hot */
	public static int getTemperatureLevel(float temperature)
	{
		return (temperature >= 0.15F) ? (temperature >= 0.5F) ? (temperature > 0.95F) ? 2 : 1 : 0 : -1;
	}

	/* -1: Arid, 0: Semi-arid , 1: Semi-humid, 2: Humid */
	public static int getRainfallLevel(float rainfall)
	{
		return (rainfall >= 0.2F) ? (rainfall >= 0.5F) ? (rainfall > 0.8F) ? 2 : 1 : 0 : -1;
	}
}
