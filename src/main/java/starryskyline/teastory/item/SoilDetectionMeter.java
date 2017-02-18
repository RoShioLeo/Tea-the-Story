package starryskyline.teastory.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
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
        list.add(I18n.translateToLocal("teastory.tooltip.soil_detection_meter"));
    }
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(worldIn.isRemote)
		{
		    Biome biome = worldIn.getBiomeGenForCoords(pos);
            float humidity = biome.getRainfall();
            float temperature = biome.getFloatTemperature(pos);
            String temp = (temperature >= 0.15F) ? (temperature >= 0.5F) ? (temperature > 0.95F) ? I18n.translateToLocal("teastory.soil_detection_meter.temperature.hot") : I18n.translateToLocal("teastory.soil_detection_meter.temperature.warm") : I18n.translateToLocal("teastory.soil_detection_meter.temperature.cold") : I18n.translateToLocal("teastory.soil_detection_meter.temperature.snowy");
            String rainfall = (humidity >= 0.2F) ? (humidity >= 0.5F) ? (humidity >= 0.8F) ? I18n.translateToLocal("teastory.soil_detection_meter.humidity.humid") : I18n.translateToLocal("teastory.soil_detection_meter.humidity.semi-humid") : I18n.translateToLocal("teastory.soil_detection_meter.humidity.semi-arid") : I18n.translateToLocal("teastory.soil_detection_meter.humidity.arid");
            int height = pos.getY();
            playerIn.addChatMessage(new TextComponentTranslation("teastory.soil_detection_meter.message.total", temp, rainfall, ("\u00a78" + String.valueOf(height)), ("\u00a7b" + biome.getBiomeName())));
            if ((temperature >= 0.5F))
            {
            	if(humidity <0.5)
            	{
            	    playerIn.addChatMessage(new TextComponentTranslation("teastory.soil_detection_meter.message.drying"));
            	}
            	else playerIn.addChatMessage(new TextComponentTranslation("teastory.soil_detection_meter.message.fermentation"));
            }
            int chance = (int)(Teaplant.environmentChance(worldIn, pos) * 100);
            String f = (chance < 80) ? (chance < 40) ? String.valueOf("\u00a7c" + chance + "%") : String.valueOf("\u00a7e" + chance + "%") : String.valueOf("\u00a7a" + chance + "%");
            String h = (height <= 110) ? (height < 80) ? I18n.translateToLocal("teastory.soil_detection_meter.height.low") : I18n.translateToLocal("teastory.soil_detection_meter.height.medium") : I18n.translateToLocal("teastory.soil_detection_meter.height.high");
            playerIn.addChatMessage(new TextComponentTranslation("teastory.soil_detection_meter.message.teaplant", f, h));
            return EnumActionResult.SUCCESS;
		}
		else 
		{
			playerIn.addStat(AchievementLoader.soilDetectionMeter);
			return EnumActionResult.SUCCESS;
		}
    }
}
