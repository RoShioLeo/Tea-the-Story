package cloud.lemonslice.teastory.common.environment.flower;


import javax.annotation.Nonnull;

public class FlowerColorPair
{
    private final FlowerColor color1;
    private final FlowerColor color2;

    public FlowerColorPair(@Nonnull FlowerColor color1, @Nonnull FlowerColor color2)
    {
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    public int hashCode()
    {
        return color1.hashCode() + color2.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o instanceof FlowerColorPair)
        {
            FlowerColorPair pair = (FlowerColorPair) o;
            return (pair.color1 == this.color1 && pair.color2 == this.color2) || (pair.color1 == this.color2 && pair.color2 == this.color1);
        }
        return false;
    }

    public FlowerColor getColor1()
    {
        return color1;
    }

    public FlowerColor getColor2()
    {
        return color2;
    }
}
