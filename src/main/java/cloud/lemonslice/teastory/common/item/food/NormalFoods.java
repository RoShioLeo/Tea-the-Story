package cloud.lemonslice.teastory.common.item.food;

import net.minecraft.item.Food;

public final class NormalFoods
{
    public static final Food DRIED_BEETROOT = new Food.Builder().hunger(3).saturation(0.6F).build();
    public static final Food DRIED_CARROT = new Food.Builder().hunger(5).saturation(0.6F).build();

    public static final Food BEEF_JERKY = new Food.Builder().hunger(9).saturation(1.0F).meat().build();
    public static final Food PORK_JERKY = new Food.Builder().hunger(9).saturation(1.0F).meat().build();
    public static final Food CHICKEN_JERKY = new Food.Builder().hunger(8).saturation(0.8F).meat().build();
    public static final Food RABBIT_JERKY = new Food.Builder().hunger(6).saturation(1.0F).meat().build();
    public static final Food MUTTON_JERKY = new Food.Builder().hunger(8).saturation(1.0F).meat().build();

    public static final Food GRAPE = new Food.Builder().hunger(1).saturation(0.3F).build();
    public static final Food CUCUMBER = new Food.Builder().hunger(1).saturation(0.2F).build();
    public static final Food BITTER_GOURD = new Food.Builder().hunger(1).saturation(0.2F).build();
    public static final Food RAISINS = new Food.Builder().hunger(2).saturation(0.4F).fastToEat().build();
    public static final Food RICE_BALL = new Food.Builder().hunger(4).saturation(0.5F).build();
    public static final Food RICE_BALL_WITH_KELP = new Food.Builder().hunger(4).saturation(0.5F).fastToEat().build();

    public static final Food BEEF_BURGER = new Food.Builder().hunger(8).saturation(1.2F).build();
    public static final Food CHICKEN_BURGER = new Food.Builder().hunger(8).saturation(1.2F).build();
    //碗装类
    public static final Food NETHER_WART_RICE_BOWL = new Food.Builder().hunger(10).saturation(1.2F).build();
    public static final Food SPICY_BEEF_RICE_BOWL = new Food.Builder().hunger(10).saturation(1.2F).build();
    public static final Food BEEF_RICE_BOWL = new Food.Builder().hunger(10).saturation(1.2F).build();
    public static final Food RISE_BOWL = new Food.Builder().hunger(6).saturation(0.8F).build();

    public static final Food PICKLED_CABBAGE_WITH_FISH = new Food.Builder().hunger(8).saturation(1.0F).build();
    //碟装类
    public static final Food STEAMED_CHINESE_CABBAGE = new Food.Builder().hunger(5).saturation(0.7F).build();
    public static final Food HONEY_BITTER_GOURD = new Food.Builder().hunger(5).saturation(0.5F).build();
    public static final Food SHREDDED_CUCUMBER_SALAD = new Food.Builder().hunger(4).saturation(1.0F).build();
    public static final Food PORK_BAOZI = new Food.Builder().hunger(6).saturation(1.2F).build();
}
