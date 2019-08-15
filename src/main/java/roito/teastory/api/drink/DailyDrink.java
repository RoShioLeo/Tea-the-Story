package roito.teastory.api.drink;

public class DailyDrink
{
    private int level;
    private float time;
    private long instantDay;

    public DailyDrink(int level, float time, long instantDay)
    {
        this.level = level;
        this.time = time;
        this.instantDay = instantDay;
    }

    public int getLevel()
    {
        return level;
    }

    public float getTime()
    {
        return time;
    }

    public long getInstantDay()
    {
        return instantDay;
    }
}
