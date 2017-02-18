package starryskyline.teastory.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starryskyline.teastory.achievement.AchievementLoader;
import starryskyline.teastory.block.BlockLoader;
import starryskyline.teastory.block.Teaplant;

public class SoilDetectionMeter extends TSItem
{
	public SoilDetectionMeter() {
		super("soil_detection_meter", 1);
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(StatCollector.translateToLocal("teastory.tooltip.soil_detection_meter"));
    }
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(worldIn.isRemote)
		{
		    BiomeGenBase biome = worldIn.getBiomeGenForCoords(pos);
            float humidity = biome.rainfall;
            float temperature = biome.getFloatTemperature(pos);
            String temp = (temperature >= 0.15F) ? (temperature >= 0.5F) ? (temperature > 0.95F) ? StatCollector.translateToLocal("teastory.soil_detection_meter.temperature.hot") : StatCollector.translateToLocal("teastory.soil_detection_meter.temperature.warm") : StatCollector.translateToLocal("teastory.soil_detection_meter.temperature.cold") : StatCollector.translateToLocal("teastory.soil_detection_meter.temperature.snowy");
            String rainfall = (humidity >= 0.2F) ? (humidity >= 0.5F) ? (humidity >= 0.8F) ? StatCollector.translateToLocal("teastory.soil_detection_meter.humidity.humid") : StatCollector.translateToLocal("teastory.soil_detection_meter.humidity.semi-humid") : StatCollector.translateToLocal("teastory.soil_detection_meter.humidity.semi-arid") : StatCollector.translateToLocal("teastory.soil_detection_meter.humidity.arid");
            int height = pos.getY();
            playerIn.addChatMessage(new ChatComponentTranslation("teastory.soil_detection_meter.message.total", temp, rainfall, ("\u00a78" + String.valueOf(height)), ("\u00a7b" + biome.biomeName)));
            if ((temperature >= 0.5F))
            {
            	if(humidity <0.5)
            	{
            	    playerIn.addChatMessage(new ChatComponentTranslation("teastory.soil_detection_meter.message.drying"));
            	}
            	else playerIn.addChatMessage(new ChatComponentTranslation("teastory.soil_detection_meter.message.fermentation"));
            }
            int chance = (int)(Teaplant.environmentChance(worldIn, pos) * 100);
            String f = (chance < 80) ? (chance < 40) ? String.valueOf("\u00a7c" + chance + "%") : String.valueOf("\u00a7e" + chance + "%") : String.valueOf("\u00a7a" + chance + "%");
            String h = (height <= 110) ? (height < 80) ? StatCollector.translateToLocal("teastory.soil_detection_meter.height.low") : StatCollector.translateToLocal("teastory.soil_detection_meter.height.medium") : StatCollector.translateToLocal("teastory.soil_detection_meter.height.high");
            playerIn.addChatMessage(new ChatComponentTranslation("teastory.soil_detection_meter.message.teaplant", f, h));
            return true;
		}
		else 
		{
			playerIn.triggerAchievement(AchievementLoader.soilDetectionMeter);
			return true;
		}
    }
}
