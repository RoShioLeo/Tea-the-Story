package cloud.lemonslice.teastory.common.environment.crop;

import cloud.lemonslice.silveroak.common.environment.Humidity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class CropHumidityInfo
{
    private final Humidity min;
    private final Humidity max;

    public CropHumidityInfo(Humidity min, Humidity max)
    {
        this.min = min;
        this.max = max;
    }

    public CropHumidityInfo(Humidity env)
    {
        this.min = env;
        this.max = env;
    }

    public boolean isSuitable(Humidity env)
    {
        return min.getId() <= env.getId() && env.getId() <= max.getId();
    }

    public float getGrowChance(Humidity env)
    {
        if (isSuitable(env))
        {
            return 1.0F;
        }
        else if (env.getId() < min.getId())
        {
            return Math.max(0, 1.0F - 0.5F * (min.getId() - env.getId()) * (min.getId() - env.getId()));
        }
        else
        {
            return Math.max(0, 1.0F - 0.5F * (env.getId() - max.getId()) * (env.getId() - max.getId()));
        }
    }

    public List<ITextComponent> getTooltip()
    {
        List<ITextComponent> list = new ArrayList<>();
        list.add(new TranslationTextComponent("info.teastory.environment.humidity").mergeStyle(TextFormatting.GRAY));
        if (min != max)
        {
            list.add(((TranslationTextComponent) min.getTranslation()).appendSibling(new StringTextComponent(" - ").mergeStyle(TextFormatting.GRAY)).appendSibling(max.getTranslation()));
        }
        else
        {
            list.add(min.getTranslation());
        }
        return list;
    }
}
