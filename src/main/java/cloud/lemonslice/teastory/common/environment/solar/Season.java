package cloud.lemonslice.teastory.common.environment.solar;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public enum Season
{
    SPRING(TextFormatting.DARK_GREEN),
    SUMMER(TextFormatting.RED),
    AUTUMN(TextFormatting.GOLD),
    WINTER(TextFormatting.BLUE),
    NONE(TextFormatting.DARK_AQUA);

    private final TextFormatting color;

    Season(TextFormatting color)
    {
        this.color = color;
    }

    public String getName()
    {
        return this.toString().toLowerCase();
    }

    public ITextComponent getTranslation()
    {
        return new TranslationTextComponent("info.teastory.environment.season." + getName()).mergeStyle(color);
    }

    public TextFormatting getColor()
    {
        return color;
    }
}
