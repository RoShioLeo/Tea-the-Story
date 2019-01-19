package roito.teastory.helper;

import roito.teastory.config.ConfigMain;

public class EntironmentHelper
{
	public static int getDryingTemperatureLevel(float temp)
	{
		if (temp < 0.15F)
		{
			return -3;
		}
		else if (temp < 0.3F)
		{
			return -2;
		}
		else if (temp < 0.6F)
		{
			return -1;
		}
		else if (temp < 0.8F)
		{
			return 0;
		}
		else if (temp < 1.5F)
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}

	public static int getDryingRainfallLevel(float rainfall)
	{
		if (rainfall < 0.2F)
		{
			return 1;
		}
		else if (rainfall < 0.5F)
		{
			return 0;
		}
		else if (rainfall < 0.8F)
		{
			return -1;
		}
		else
		{
			return -2;
		}
	}

	public static int getDryingTicks(float temp, float rainfall)
	{
		if (ConfigMain.teaMaking.isDryingLimited)
		{
			return ConfigMain.teaMaking.dryingBasicTime - (getDryingTemperatureLevel(temp) + getDryingRainfallLevel(rainfall)) * 100;
		}
		else
		{
			return ConfigMain.teaMaking.dryingBasicTime;
		}
	}

	public static int getFermentationTemperatureLevel(float temp)
	{
		if (temp < 0.15F)
		{
			return -2;
		}
		else if (temp < 0.3F)
		{
			return -1;
		}
		else if (temp < 0.6F)
		{
			return 1;
		}
		else if (temp < 0.8F)
		{
			return 2;
		}
		else if (temp < 1.5F)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}

	public static int getFermentationRainfallLevel(float rainfall)
	{
		if (rainfall < 0.2F)
		{
			return -2;
		}
		else if (rainfall < 0.5F)
		{
			return -1;
		}
		else if (rainfall < 0.8F)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	public static int getFermentationTicks(float temp, float rainfall)
	{
		if (ConfigMain.teaMaking.isFermentationLimited)
		{
			return ConfigMain.teaMaking.fermentationBasicTime - (getFermentationTemperatureLevel(temp) + getFermentationRainfallLevel(rainfall)) * 160;
		}
		else
		{
			return ConfigMain.teaMaking.fermentationBasicTime;
		}
	}

	public static float getTeaPlantTemperatureLevel(float temp)
	{
		if (temp < 0.15F)
		{
			return -0.60F;
		}
		else if (temp < 0.3F)
		{
			return -0.20F;
		}
		else if (temp < 0.6F)
		{
			return 0.10F;
		}
		else if (temp < 0.8F)
		{
			return 0.10F;
		}
		else if (temp < 1.5F)
		{
			return -0.20F;
		}
		else
		{
			return -0.60F;
		}
	}

	public static float getTeaPlantHeightLevel(int height)
	{
		if (height > 110)
		{
			height = height - 110;
		}
		else if (height < 80)
		{
			height = 80 - height;
		}
		else
		{
			height = 0;
		}
		return (float) (1.0F - Math.pow(height, 2) / 300);
	}

	public static float getRiceCropsRainfallLevel(float rainfall)
	{
		if (rainfall < 0.2F)
		{
			return -0.4F;
		}
		else if (rainfall < 0.5F)
		{
			return -0.2F;
		}
		else if (rainfall < 0.8F)
		{
			return 0.1F;
		}
		else
		{
			return 0.2F;
		}
	}

	public static float getTeaPlantGrowPercent(float temp, int height)
	{
		return ConfigMain.teaMaking.isTeaPlantLimited ? Math.max(getTeaPlantHeightLevel(height) + getTeaPlantTemperatureLevel(temp), 0) : 1.0F;
	}

	public static float getRiceCropsGrowPercent(float temp, float rainfall)
	{
		return ConfigMain.teaMaking.isRiceLimited ? Math.max(1.0F + getTeaPlantTemperatureLevel(temp) + getRiceCropsRainfallLevel(rainfall), 0) : 1.0F;
	}
}
