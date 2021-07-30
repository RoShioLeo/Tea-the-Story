package cloud.lemonslice.teastory.common.item.food;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import cloud.lemonslice.teastory.TeaStory;
import net.minecraft.item.Food;

public class FoodItem extends NormalItem
{
    public FoodItem(String name, Properties properties)
    {
        super(name, properties.group(TeaStory.GROUP_CORE));
    }

    public FoodItem(String name, Food food)
    {
        this(name, new Properties().food(food));
    }
}
