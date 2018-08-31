package roito.teastory.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.lwjgl.input.Keyboard;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.helper.EntironmentHelper;

import javax.annotation.Nullable;
import java.util.List;

public class SoilDetectionMeter extends TSItem
{
    public SoilDetectionMeter()
    {
        super("soil_detection_meter", 1, CreativeTabsRegister.tabTeaStory);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.soil_detection_meter"));
        } else
            tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            Biome biome = worldIn.getBiome(pos);
            float humidity = biome.getRainfall();
            float temperature = biome.getTemperature(pos);
            String temp = (temperature >= 0.15F) ? (temperature >= 0.3F) ? (temperature >= 0.6F) ? (temperature >= 0.8F) ? (temperature >= 1.5F) ? I18n.format("teastory.message.soil_detection_meter.temperature.heat") : I18n.format("teastory.message.soil_detection_meter.temperature.hot") : I18n.format("teastory.message.soil_detection_meter.temperature.warm") : I18n.format("teastory.message.soil_detection_meter.temperature.cool") : I18n.format("teastory.message.soil_detection_meter.temperature.cold") : I18n.format("teastory.message.soil_detection_meter.temperature.frozen");
            String rainfall = (humidity >= 0.2F) ? (humidity >= 0.5F) ? (humidity >= 0.8F) ? I18n.format("teastory.message.soil_detection_meter.humidity.humid") : I18n.format("teastory.message.soil_detection_meter.humidity.semi-humid") : I18n.format("teastory.message.soil_detection_meter.humidity.semi-arid") : I18n.format("teastory.message.soil_detection_meter.humidity.arid");
            int height = pos.getY();
            playerIn.sendMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.total", temp, rainfall, TextFormatting.DARK_GRAY + String.valueOf(height), TextFormatting.AQUA + biome.getBiomeName()));

            int dryingTime = EntironmentHelper.getDryingTicks(temperature, humidity) / 20;
            playerIn.sendMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.drying", dryingTime));

            int fermentationTime = EntironmentHelper.getFermentationTicks(temperature, humidity) / 20;
            playerIn.sendMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.fermentation", fermentationTime, (int) (fermentationTime * 1.5F)));

            float teaChance = ((int) (EntironmentHelper.getTeaPlantGrowPercent(temperature, height) * 1000)) / 10;
            Object f = (teaChance < 80) ? (teaChance < 40) ? TextFormatting.RED + String.valueOf(teaChance + "%") : TextFormatting.YELLOW + String.valueOf(teaChance + "%") : TextFormatting.GREEN + String.valueOf(teaChance + "%");
            String h = (height <= 110) ? (height < 80) ? I18n.format("teastory.message.soil_detection_meter.height.low") : I18n.format("teastory.message.soil_detection_meter.height.medium") : I18n.format("teastory.message.soil_detection_meter.height.high");
            playerIn.sendMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.teaplant", f, h));

            float riceChance = ((int) (EntironmentHelper.getRiceCropsGrowPercent(temperature, humidity) * 1000)) / 10;
            Object g = (riceChance < 80) ? (riceChance < 40) ? TextFormatting.RED + String.valueOf(riceChance + "%") : TextFormatting.YELLOW + String.valueOf(riceChance + "%") : TextFormatting.GREEN + String.valueOf(riceChance + "%");
            playerIn.sendMessage(new TextComponentTranslation("teastory.message.soil_detection_meter.ricecrop", g));
            return EnumActionResult.SUCCESS;
        } else
        {
            return EnumActionResult.SUCCESS;
        }
    }
}
