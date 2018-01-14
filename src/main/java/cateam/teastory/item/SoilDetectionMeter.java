package cateam.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.Barrel;
import cateam.teastory.block.Teapan;
import cateam.teastory.block.Teaplant;
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

public class SoilDetectionMeter extends TSItem
{
	public SoilDetectionMeter() {
		super("soil_detection_meter", 1);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE +(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.soil_detection_meter")));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote)
		{
			Biome biome = worldIn.getBiome(pos);
			float humidity = biome.getRainfall();
			float temperature = biome.getFloatTemperature(pos);
			String temp = (temperature >= 0.15F) ? (temperature >= 0.5F) ? (temperature > 0.95F) ? I18n.translateToLocal("teastory.soil_detection_meter.temperature.hot") : I18n.translateToLocal("teastory.soil_detection_meter.temperature.warm") : I18n.translateToLocal("teastory.soil_detection_meter.temperature.cold") : I18n.translateToLocal("teastory.soil_detection_meter.temperature.snowy");
			String rainfall = (humidity >= 0.2F) ? (humidity >= 0.5F) ? (humidity >= 0.8F) ? I18n.translateToLocal("teastory.soil_detection_meter.humidity.humid") : I18n.translateToLocal("teastory.soil_detection_meter.humidity.semi-humid") : I18n.translateToLocal("teastory.soil_detection_meter.humidity.semi-arid") : I18n.translateToLocal("teastory.soil_detection_meter.humidity.arid");
			int height = pos.getY();
			playerIn.addChatMessage(new TextComponentTranslation("teastory.soil_detection_meter.message.total", temp, rainfall, TextFormatting.DARK_GRAY + String.valueOf(height), TextFormatting.AQUA + biome.getBiomeName()));

			float FermentationChance = Barrel.getFermentationChance(worldIn, pos, true) * 0.5F;
			Object fermentation1 = (FermentationChance >= 0.50F) ? (FermentationChance >= 1.00F) ? I18n.translateToLocal("teastory.soil_detection_meter.fast") : I18n.translateToLocal("teastory.soil_detection_meter.normal") : I18n.translateToLocal("teastory.soil_detection_meter.slow");
			Object fermentation2 = ((FermentationChance/2) >= 0.50F) ? ((FermentationChance/2) >= 1.00F) ? I18n.translateToLocal("teastory.soil_detection_meter.fast") : I18n.translateToLocal("teastory.soil_detection_meter.normal") : I18n.translateToLocal("teastory.soil_detection_meter.slow");
			Object fermentationRate1 =  (FermentationChance >= 0.50F) ? (FermentationChance >= 1.00F) ? TextFormatting.GREEN + String.valueOf((int)(FermentationChance * 100) + "%") : TextFormatting.YELLOW + String.valueOf((int)(FermentationChance * 100) + "%") : TextFormatting.RED + String.valueOf((int)(FermentationChance / 2.0F * 100) + "%");
			Object fermentationRate2 =  ((FermentationChance/2) >= 0.50F) ? ((FermentationChance/2) >= 1.00F) ? TextFormatting.GREEN + String.valueOf((int)(FermentationChance / 2.0F * 100) + "%") : TextFormatting.YELLOW + String.valueOf((int)(FermentationChance / 2.0F * 100) + "%") : TextFormatting.RED + String.valueOf((int)(FermentationChance / 4.0F * 100) + "%");
			playerIn.addChatMessage(new TextComponentTranslation("teastory.soil_detection_meter.message.fermentation", fermentation1, fermentationRate1, fermentation2, fermentationRate2));

			float DryingChance1 = Teapan.getDryingChance(worldIn, pos, true) * 0.5F;
			float DryingChance2 = Teapan.getDryingChance(worldIn, pos, false) * 0.5F;
			String drying1 = ((DryingChance1) >= 0.50F) ? ((DryingChance1) >= 1.0F) ? I18n.translateToLocal("teastory.soil_detection_meter.fast") : I18n.translateToLocal("teastory.soil_detection_meter.normal") : I18n.translateToLocal("teastory.soil_detection_meter.slow");
			String drying2 = ((DryingChance2) >= 0.50F) ? ((DryingChance2) >= 1.0F) ? I18n.translateToLocal("teastory.soil_detection_meter.fast") : I18n.translateToLocal("teastory.soil_detection_meter.normal") : I18n.translateToLocal("teastory.soil_detection_meter.slow");
			Object dryingRate1 =  ((DryingChance1) >= 0.50F) ? ((DryingChance1) >= 1.0F) ? TextFormatting.GREEN + String.valueOf((int)(DryingChance1 * 100) + "%") : TextFormatting.YELLOW + String.valueOf((int)(DryingChance1 * 100) + "%") : TextFormatting.RED + String.valueOf((int)(DryingChance1 * 100) + "%");
			Object dryingRate2 =  ((DryingChance2) >= 0.50F) ? ((DryingChance2) >= 1.0F) ? TextFormatting.GREEN + String.valueOf((int)(DryingChance2 * 100) + "%") : TextFormatting.YELLOW + String.valueOf((int)(DryingChance2 * 100) + "%") : TextFormatting.RED + String.valueOf((int)(DryingChance2 * 100) + "%");
			playerIn.addChatMessage(new TextComponentTranslation("teastory.soil_detection_meter.message.drying", drying1, dryingRate1, drying2, dryingRate2));

			int chance = (int)(Teaplant.environmentChance(worldIn, pos) * 250);
			Object f = (chance < 80) ? (chance < 40) ? TextFormatting.RED + String.valueOf(chance + "%") : TextFormatting.YELLOW + String.valueOf(chance + "%") : TextFormatting.GREEN + String.valueOf(chance + "%");
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
