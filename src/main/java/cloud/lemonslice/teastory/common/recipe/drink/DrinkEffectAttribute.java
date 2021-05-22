package cloud.lemonslice.teastory.common.recipe.drink;

import net.minecraft.potion.Effect;

public class DrinkEffectAttribute
{
    private final Effect potion;
    private final int duration;
    private final int level;

    public DrinkEffectAttribute(Effect potionIn, int durationIn, int level)
    {
        this.potion = potionIn;
        this.duration = durationIn;
        this.level = level;
    }

    public Effect getPotion()
    {
        return potion;
    }

    public int getDuration()
    {
        return duration;
    }

    public int getLevel()
    {
        return level;
    }
}
