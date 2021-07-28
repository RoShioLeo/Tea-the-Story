package cloud.lemonslice.teastory.common.recipe.type;

import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayBakeRecipe;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayInRainRecipe;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayIndoorsRecipe;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayOutdoorsRecipe;
import cloud.lemonslice.teastory.common.recipe.drink.DrinkRecipe;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneMillRecipe;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneRollerRecipe;
import net.minecraft.item.crafting.IRecipeType;

import static cloud.lemonslice.teastory.TeaStory.MODID;

public class NormalRecipeTypes
{
    public static final IRecipeType<BambooTrayInRainRecipe> BT_IN_RAIN = IRecipeType.register(MODID + ":bamboo_tray_in_rain");
    public static final IRecipeType<BambooTrayOutdoorsRecipe> BT_OUTDOORS = IRecipeType.register(MODID + ":bamboo_tray_outdoors");
    public static final IRecipeType<BambooTrayIndoorsRecipe> BT_INDOORS = IRecipeType.register(MODID + ":bamboo_tray_indoors");
    public static final IRecipeType<BambooTrayBakeRecipe> BT_BAKE = IRecipeType.register(MODID + ":bamboo_tray_bake");
    public static final IRecipeType<DrinkRecipe> DRINK_MAKER = IRecipeType.register(MODID + ":drink_maker");
    public static final IRecipeType<StoneMillRecipe> STONE_MILL = IRecipeType.register(MODID + ":stone_mill");
    public static final IRecipeType<StoneRollerRecipe> STONE_ROLLER = IRecipeType.register(MODID + ":stone_roller");
}
