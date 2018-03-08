package roito.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import roito.teastory.block.Barrel;
import roito.teastory.block.Teapan;
import roito.teastory.block.Teaplant;
import roito.teastory.common.AchievementLoader;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.helper.EntironmentHelper;

public class SoilDetectionMeter extends TSItem
{
	public SoilDetectionMeter()
	{
		super("soil_detection_meter", 1, CreativeTabsLoader.tabTeaStory);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE + (TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.soil_detection_meter")));
		} 
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote) 
		{
			Biome biome = worldIn.getBiome(pos);
			float humidity = biome.getRainfall();
			float temperature = biome.getFloatTemperature(pos);
			String temp = (temperature >= 0.15F) ? (temperature >= 0.5F) ? (temperature > 0.95F) ? I18n.translateToLocal("teastory.message.soil_detection_meter.temperature.hot") : I18n.translateToLocal("teastory.message.soil_detection_meter.temperature.warm") : I18n.translateToLocal("teastory.message.soil_detection_meter.temperature.cold") : I18n.translateToLocal("teastory.message.soil_detection_meter.temperature.snowy");
			String rainfall = (humidity >= 0.2F) ? (humidity >= 0.5F) ? (humidity >= 0.8F) ? I18n.translateToLocal("teastory.message.soil_detection_meter.humidity.humid") : I18n.translateToLocal("teastory.message.soil_detection_meter.humidity.semi-humid") : I18n.translateToLocal("teastory.message.soil_detection_meter.humidity.semi-arid") : I18n.translateToLocal("teastory.message.soil_detection_meter.humidity.arid");
			int height = pos.getY();
			playerIn.addChatMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.total", temp, rainfall, TextFormatting.DARK_GRAY + String.valueOf(height), TextFormatting.AQUA + biome.getBiomeName()));

			float fermentationChance = EntironmentHelper.getFermentationChance(worldIn, pos, true) * 0.5F;
			Object fermentationRateLevel1 = EntironmentHelper.getFermentationRateLevel(fermentationChance);
			Object fermentationRateLevel2 = EntironmentHelper.getFermentationRateLevel(fermentationChance / 2);
			Object fermentationRate1 = EntironmentHelper.getFermentationRate(fermentationChance);
			Object fermentationRate2 = EntironmentHelper.getFermentationRate(fermentationChance / 2);
			playerIn.addChatMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.fermentation", fermentationRateLevel1, fermentationRate1, fermentationRateLevel2, fermentationRate2));

			float dryingChance1 = EntironmentHelper.getDryingChance(worldIn, pos, true) * 0.5F;
			float dryingChance2 = EntironmentHelper.getDryingChance(worldIn, pos, false) * 0.5F;
			String dryingRateLevel1 = EntironmentHelper.getDryingRateLevel(dryingChance1);
			String dryingRateLevel2 = EntironmentHelper.getDryingRateLevel(dryingChance2);
			Object dryingRate1 = EntironmentHelper.getDryingRate(dryingChance1);
			Object dryingRate2 = EntironmentHelper.getDryingRate(dryingChance2);
			playerIn.addChatMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.drying", dryingRateLevel1, dryingRate1, dryingRateLevel2, dryingRate2));

			int chance = (int) (Teaplant.environmentChance(worldIn, pos) * 250);
			Object f = (chance < 80) ? (chance < 40) ? TextFormatting.RED + String.valueOf(chance + "%") : TextFormatting.YELLOW + String.valueOf(chance + "%") : TextFormatting.GREEN + String.valueOf(chance + "%");
			String h = (height <= 110) ? (height < 80) ? I18n.translateToLocal("teastory.message.soil_detection_meter.height.low") : I18n.translateToLocal("teastory.message.soil_detection_meter.height.medium") : I18n.translateToLocal("teastory.message.soil_detection_meter.height.high");
			playerIn.addChatMessage( new TextComponentTranslation("teastory.message.soil_detection_meter.teaplant", f, h));
			return EnumActionResult.SUCCESS;
		} 
		else 
		{
			playerIn.addStat(AchievementLoader.soilDetectionMeter);
			return EnumActionResult.SUCCESS;
		}
	}
}
