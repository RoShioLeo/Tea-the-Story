package cloud.lemonslice.teastory.common.item;

public interface IItemWithTemperature
{
    String getResistanceType();

    int getResistancePoint();

    boolean shouldHeld();

    default boolean onlyOnce()
    {
        return false;
    }
}
