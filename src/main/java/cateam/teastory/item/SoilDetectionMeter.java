package cateam.teastory.item;

import java.util.List;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.Barrel;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.block.Teapan;
import cateam.teastory.block.Teaplant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SoilDetectionMeter extends TSItem
{
	public SoilDetectionMeter() {
		super("soil_detection_meter", 1);
	}
	
	@Override
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
            
            float FermentationChance = worldIn.canSeeSky(pos) ? Barrel.getFermentationChance(worldIn, pos) * 2 : Barrel.getFermentationChance(worldIn, pos);
            String fermentation1 = (FermentationChance >= 1.60F) ? (FermentationChance >= 2.56F) ? StatCollector.translateToLocal("teastory.soil_detection_meter.fast") : StatCollector.translateToLocal("teastory.soil_detection_meter.normal") : StatCollector.translateToLocal("teastory.soil_detection_meter.slow");
            String fermentation2 = ((FermentationChance/2) >= 1.60F) ? ((FermentationChance/2) >= 2.56F) ? StatCollector.translateToLocal("teastory.soil_detection_meter.fast") : StatCollector.translateToLocal("teastory.soil_detection_meter.normal") : StatCollector.translateToLocal("teastory.soil_detection_meter.slow");
            String fermentationRate1 =  (FermentationChance >= 1.60F) ? (FermentationChance >= 2.56F) ? String.valueOf("\u00a7a" + (int)(FermentationChance / 2.0F * 100) + "%") : String.valueOf("\u00a7e" + (int)(FermentationChance / 2.0F * 100) + "%") : String.valueOf("\u00a7c" + (int)(FermentationChance / 2.0F * 100) + "%");
            String fermentationRate2 =  ((FermentationChance/2) >= 1.60F) ? ((FermentationChance/2) >= 2.56F) ? String.valueOf("\u00a7a" + (int)(FermentationChance / 4.0F * 100) + "%") : String.valueOf("\u00a7e" + (int)(FermentationChance / 4.0F * 100) + "%") : String.valueOf("\u00a7c" + (int)(FermentationChance / 4.0F * 100) + "%");
            playerIn.addChatMessage(new ChatComponentTranslation("teastory.soil_detection_meter.message.fermentation", fermentation1, fermentationRate1, fermentation2, fermentationRate2));
            
            float c1 = worldIn.getLight(pos.up()) * 0.07F;
            float c2 = worldIn.getLightFor(EnumSkyBlock.BLOCK, pos.up()) * 0.025F;
            float DryingChance = (float)(1.0D * ((double)humidity >= 0.2D ? (double)humidity >= 0.5D ? (double)humidity >= 0.8D ? 0.3D : 0.7D : 1.0D : 1.4D));
            DryingChance = (float)((double)DryingChance * ((double)temperature >= 0.15D ? (double)temperature >= 0.5D ? (double)temperature > 0.95D ? 1.3D : 0.9D : 0.5D : 0.1D));
            String drying1 = ((DryingChance * c1) >= 0.80F) ? ((DryingChance * c1) >= 1.20F) ? StatCollector.translateToLocal("teastory.soil_detection_meter.fast") : StatCollector.translateToLocal("teastory.soil_detection_meter.normal") : StatCollector.translateToLocal("teastory.soil_detection_meter.slow");
            String drying2 = ((DryingChance * c2) >= 0.80F) ? ((DryingChance * c2) >= 1.20F) ? StatCollector.translateToLocal("teastory.soil_detection_meter.fast") : StatCollector.translateToLocal("teastory.soil_detection_meter.normal") : StatCollector.translateToLocal("teastory.soil_detection_meter.slow");
            String dryingRate1 =  ((DryingChance * c1) >= 0.80F) ? ((DryingChance * c1) >= 1.20F) ? String.valueOf("\u00a7a" + (int)(DryingChance * c1 * 100) + "%") : String.valueOf("\u00a7e" + (int)(DryingChance * c1 * 100) + "%") : String.valueOf("\u00a7c" + (int)(DryingChance * c1 * 100) + "%");
            String dryingRate2 =  ((DryingChance * c2) >= 0.80F) ? ((DryingChance * c2) >= 1.20F) ? String.valueOf("\u00a7a" + (int)(DryingChance * c2 * 100) + "%") : String.valueOf("\u00a7e" + (int)(DryingChance * c2 * 100) + "%") : String.valueOf("\u00a7c" + (int)(DryingChance * c2 * 100) + "%");
            playerIn.addChatMessage(new ChatComponentTranslation("teastory.soil_detection_meter.message.drying", drying1, dryingRate1, drying2, dryingRate2));
            
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
