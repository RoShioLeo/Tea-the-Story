package cloud.lemonslice.teastory.common.item.food;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import net.minecraft.item.Food;
import net.minecraft.item.ItemGroup;

public class FoodItem extends NormalItem
{
    public FoodItem(String name, Properties properties)
    {
        super(name, properties.group(ItemGroup.FOOD));
    }

    public FoodItem(String name, Food food)
    {
        this(name, new Properties().food(food));
    }
}
