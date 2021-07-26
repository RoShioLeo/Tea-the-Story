package cloud.lemonslice.teastory.common.environment.solar;

import cloud.lemonslice.teastory.client.color.season.SolarTermColors;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum SolarTerm
{
    // Spring Solar Terms
    BEGINNING_OF_SPRING(-0.25F, 10500),
    RAIN_WATER(-0.15F, 11000),
    INSECTS_AWAKENING(-0.1F, 11500),
    SPRING_EQUINOX(0, 12000),
    FRESH_GREEN(0, 12500),
    GRAIN_RAIN(0.05F, 13000),

    // Summer Solar Terms
    BEGINNING_OF_SUMMER(0.1F, 13500),
    LESSER_FULLNESS(0.15F, 14000),
    GRAIN_IN_EAR(0.15F, 14500),
    SUMMER_SOLSTICE(0.2F, 15000),
    LESSER_HEAT(0.2F, 14500),
    GREATER_HEAT(0.25F, 14000),

    // Autumn Solar Terms
    BEGINNING_OF_AUTUMN(0.15F, 13500),
    END_OF_HEAT(0.1F, 13000),
    WHITE_DEW(0.05F, 12500),
    AUTUMNAL_EQUINOX(0, 12000),
    COLD_DEW(-0.1F, 11500),
    FIRST_FROST(-0.2F, 11000),

    // Winter Solar Terms
    BEGINNING_OF_WINTER(-0.3F, 10500),
    LIGHT_SNOW(-0.35F, 10000),
    HEAVY_SNOW(-0.35F, 9500),
    WINTER_SOLSTICE(-0.4F, 9000),
    LESSER_COLD(-0.45F, 9500),
    GREATER_COLD(-0.4F, 10000),

    NONE(0.0F, 12000);

    private final float temperature;
    private final int dayTime;

    SolarTerm(float temperature, int dayTime)
    {
        this.temperature = temperature;
        this.dayTime = dayTime;
    }

    public String getName()
    {
        return this.toString().toLowerCase();
    }

    public ITextComponent getTranslation()
    {
        return new TranslationTextComponent("info.teastory.environment.solar_term." + getName());
    }

    public ITextComponent getAlternationText()
    {
        return new TranslationTextComponent("info.teastory.environment.solar_term.alternation." + getName()).mergeStyle(getSeason().getColor());
    }

    public static SolarTerm get(int index)
    {
        return values()[index];
    }

    @OnlyIn(Dist.CLIENT)
    public SolarTermColors getColorInfo()
    {
        return SolarTermColors.values()[this.ordinal()];
    }

    public float getTemperatureChange()
    {
        return temperature;
    }

    public int getDayTime()
    {
        return dayTime;
    }

    public Season getSeason()
    {
        return Season.values()[this.ordinal() / 6];
    }
}
