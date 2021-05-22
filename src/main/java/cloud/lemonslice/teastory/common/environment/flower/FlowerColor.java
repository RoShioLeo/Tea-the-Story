package cloud.lemonslice.teastory.common.environment.flower;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IStringSerializable;

import java.util.HashMap;
import java.util.Map;

public enum FlowerColor implements IStringSerializable
{
    WHITE(0xFFFFFB, Items.WHITE_DYE),
    RED(0xEF4136, Items.RED_DYE),
    YELLOW(0xFFD400, Items.YELLOW_DYE),
    BLUE(0x2468A2, Items.BLUE_DYE),
    ORANGE(0xF36C21, Items.ORANGE_DYE),
    PINK(0xF391A9, Items.PINK_DYE),
    MAGENTA(0xEF5B9C, Items.MAGENTA_DYE),
    LIGHT_BLUE(0x33A3DC, Items.LIGHT_BLUE_DYE),
    LIME(0x8DDF0F, Items.LIME_DYE),
    GRAY(0x8A8C8E, Items.GRAY_DYE),
    LIGHT_GRAY(0xC3C7C4, Items.LIGHT_GRAY_DYE),
    CYAN(0x008792, Items.CYAN_DYE),
    PURPLE(0x8552A1, Items.PURPLE_DYE),
    BROWN(0x5F3C23, Items.BROWN_DYE),
    GREEN(0x32CD32, Items.GREEN_DYE),
    BLACK(0x131C1E, Items.BLACK_DYE);

    private static final Map<FlowerColorPair, FlowerColor> COLOR_MAP;

    private final int color;
    private final Item dye;

    FlowerColor(int colorValue, Item dye)
    {
        this.color = colorValue;
        this.dye = dye;
    }

    public String getString()
    {
        return this.toString().toLowerCase();
    }

    public int getColorValue()
    {
        return color;
    }

    public static FlowerColor getFlowerColor(String name)
    {
        return FlowerColor.valueOf(name.toUpperCase());
    }

    public Item getDye()
    {
        return dye;
    }

    public String getTranslation()
    {
        return I18n.format("item.minecraft.firework_star." + getString());
    }

    public static FlowerColor getHybColor(FlowerColor color1, FlowerColor color2)
    {
        if (color1 == color2)
        {
            return color1;
        }
        return COLOR_MAP.getOrDefault(new FlowerColorPair(color1, color2), BLACK);
    }

    private static void registerColorRecipe(FlowerColor in1, FlowerColor in2, FlowerColor out)
    {
        COLOR_MAP.put(new FlowerColorPair(in1, in2), out);
    }

    static
    {
        COLOR_MAP = new HashMap<>();
        registerColorRecipe(RED, YELLOW, ORANGE);
        registerColorRecipe(RED, BLUE, PURPLE);
        registerColorRecipe(RED, WHITE, PINK);
        registerColorRecipe(YELLOW, BLUE, GREEN);
        registerColorRecipe(BLUE, WHITE, LIGHT_BLUE);
        registerColorRecipe(RED, ORANGE, BROWN);
        registerColorRecipe(PINK, PURPLE, MAGENTA);
        registerColorRecipe(YELLOW, GREEN, LIME);
        registerColorRecipe(WHITE, GREEN, LIME);
        registerColorRecipe(BLACK, WHITE, GRAY);
        registerColorRecipe(GRAY, WHITE, LIGHT_GRAY);
        registerColorRecipe(BLUE, GREEN, CYAN);
    }
}
