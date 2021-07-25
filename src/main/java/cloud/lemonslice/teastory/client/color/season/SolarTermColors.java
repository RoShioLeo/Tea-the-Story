package cloud.lemonslice.teastory.client.color.season;

import cloud.lemonslice.silveroak.helper.ColorHelper;
import net.minecraft.world.FoliageColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum SolarTermColors
{
    // Spring Solar Terms
    BEGINNING_OF_SPRING(0x0, 0.0F),
    RAIN_WATER(0x7fb80e, 0.1F),
    INSECTS_AWAKENING(0x7fb80e, 0.2F),
    SPRING_EQUINOX(0x7fb80e, 0.35F),
    FRESH_GREEN(0x7fb80e, 0.5F),
    GRAIN_RAIN(0x7fb80e, 0.6F),

    // Summer Solar Terms
    BEGINNING_OF_SUMMER(0x7fb80e, 0.7F),
    LESSER_FULLNESS(0x7fb80e, 0.75F),
    GRAIN_IN_EAR(0x7fb80e, 0.8F),
    SUMMER_SOLSTICE(ColorHelper.simplyMixColor(0x7fb80e, 0.9F, 0xd1923f, 0.1F), 0.8F),
    LESSER_HEAT(ColorHelper.simplyMixColor(0x7fb80e, 0.8F, 0xd1923f, 0.2F), 0.8F),
    GREATER_HEAT(ColorHelper.simplyMixColor(0x7fb80e, 0.6F, 0xd1923f, 0.4F), 0.8F, 0xffd400, 0.3F),

    // Autumn Solar Terms
    BEGINNING_OF_AUTUMN(ColorHelper.simplyMixColor(0x7fb80e, 0.4F, 0xd1923f, 0.6F), 0.8F, 0xffd400, 0.6F),
    END_OF_HEAT(0xd1923f, 0.6F, 0xffd400, 0.9F),
    WHITE_DEW(0xd1923f, 0.75F, 0xffd400, 1.0F),
    AUTUMNAL_EQUINOX(0xd1923f, 0.6F, 0xffd400, 1.0F),
    COLD_DEW(0xd1923f, 0.5F, 0xffd400, 0.95F),
    FIRST_FROST(0xd1923f, 0.35F, 0xffd400, 0.5F),

    // Winter Solar Terms
    BEGINNING_OF_WINTER(0xd1923f, 0.2F, 0xffd400, 0.3F),
    LIGHT_SNOW(0xd1923f, 0.1F),
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
