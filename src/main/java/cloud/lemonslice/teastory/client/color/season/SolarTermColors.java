package cloud.lemonslice.teastory.client.color.season;

import cloud.lemonslice.teastory.helper.ColorHelper;
import net.minecraft.world.FoliageColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum SolarTermColors
{
    // Spring Solar Terms
    BEGINNING_OF_SPRING(0x0, 0.0F),
    RAIN_WATER(0x1d953f, 0.1F),
    INSECTS_AWAKENING(0x1d953f, 0.22F),
    SPRING_EQUINOX(0x1d953f, 0.35F),
    FRESH_GREEN(0x1d953f, 0.47F),
    GRAIN_RAIN(0x1d953f, 0.6F),

    // Summer Solar Terms
    BEGINNING_OF_SUMMER(0x1d953f, 0.7F),
    LESSER_FULLNESS(ColorHelper.simplyMixColor(0x1d953f, 0.9F, 0x8c531b, 0.1F), 0.8F),
    GRAIN_IN_EAR(ColorHelper.simplyMixColor(0x1d953f, 0.8F, 0x8c531b, 0.2F), 0.8F),
    SUMMER_SOLSTICE(ColorHelper.simplyMixColor(0x1d953f, 0.7F, 0x8c531b, 0.3F), 0.8F),
    LESSER_HEAT(ColorHelper.simplyMixColor(0x1d953f, 0.6F, 0x8c531b, 0.4F), 0.8F, 0xffe600, 0.3F),
    GREATER_HEAT(ColorHelper.simplyMixColor(0x1d953f, 0.5F, 0x8c531b, 0.5F), 0.8F, 0xffe600, 0.5F),

    // Autumn Solar Terms
    BEGINNING_OF_AUTUMN(ColorHelper.simplyMixColor(0x1d953f, 0.38F, 0x8c531b, 0.62F), 0.75F, 0xffe600, 0.7F),
    END_OF_HEAT(ColorHelper.simplyMixColor(0x1d953f, 0.25F, 0x8c531b, 0.75F), 0.7F, 0xffe600, 0.8F),
    WHITE_DEW(ColorHelper.simplyMixColor(0x1d953f, 0.12F, 0x8c531b, 0.87F), 0.7F, 0xffe600, 0.9F),
    AUTUMNAL_EQUINOX(0x8c531b, 0.6F, 0xffe600, 0.9F),
    COLD_DEW(0x8c531b, 0.48F, 0xffe600, 0.8F),
    FIRST_FROST(0x8c531b, 0.35F, 0xffe600, 0.7F),

    // Winter Solar Terms
    BEGINNING_OF_WINTER(0x8c531b, 0.22F, 0xffe600, 0.5F),
    LIGHT_SNOW(0x8c531b, 0.1F, 0xffe600, 0.3F),
    HEAVY_SNOW(0x0, 0.0F),
    WINTER_SOLSTICE(0x0, 0.0F),
    LESSER_COLD(0x0, 0.0F),
    GREATER_COLD(0x0, 0.0F),

    NONE(0x0, 0.0F);

    private final int mixColorIn;
    private final float alpha;
    private final int birchColor;

    SolarTermColors(int mixColorIn, float alpha, int birchColor, float birchAlpha)
    {
        this.mixColorIn = mixColorIn;
        this.alpha = alpha;
        this.birchColor = ColorHelper.simplyMixColor(birchColor, birchAlpha, FoliageColors.getBirch(), 1.0F - birchAlpha);
    }

    SolarTermColors(int mixColorIn, float alpha)
    {
        this.mixColorIn = mixColorIn;
        this.alpha = alpha;
        this.birchColor = FoliageColors.getBirch();
    }

    public int getColor()
    {
        return mixColorIn;
    }

    public float getAlpha()
    {
        return alpha;
    }

    public static SolarTermColors get(int index)
    {
        return values()[index];
    }

    public int getBirchColor()
    {
        return birchColor;
    }
}
