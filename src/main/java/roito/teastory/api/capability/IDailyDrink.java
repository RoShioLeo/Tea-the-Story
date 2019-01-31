package roito.teastory.api.capability;

public interface IDailyDrink
{
	long getLastDay();

	long getInstantDay();

	void updateDay(long nowDay);

	void setDay(long lastDay, long instantDay);
}
