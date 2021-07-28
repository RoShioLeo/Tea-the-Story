package cloud.lemonslice.teastory.common.recipe.serializer;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayBakeRecipe;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayInRainRecipe;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayIndoorsRecipe;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTrayOutdoorsRecipe;
import cloud.lemonslice.teastory.common.recipe.drink.DrinkRecipe;
import cloud.lemonslice.teastory.common.recipe.special.FlowerDyeRecipe;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneMillRecipe;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneRollerRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class RecipeSerializerRegistry
{
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TeaStory.MODID);

    public final static RegistryObject<SpecialRecipeSerializer<FlowerDyeRecipe>> CRAFTING_SPECIAL_FLOWERDYE = RECIPE_SERIALIZERS.register("crafting_special_flowerdye", () -> new SpecialRecipeSerializer<>(FlowerDyeRecipe::new));

    public final static RegistryObject<BambooTraySingleInRecipeSerializer<BambooTrayOutdoorsRecipe>> BAMBOO_TRAY_OUTDOORS = RECIPE_SERIALIZERS.register("bamboo_tray_outdoors", () -> new BambooTraySingleInRecipeSerializer<>(BambooTrayOutdoorsRecipe::new, 200));
    public final static RegistryObject<BambooTraySingleInRecipeSerializer<BambooTrayIndoorsRecipe>> BAMBOO_TRAY_INDOORS = RECIPE_SERIALIZERS.register("bamboo_tray_indoors", () -> new BambooTraySingleInRecipeSerializer<>(BambooTrayIndoorsRecipe::new, 200));
    public final static RegistryObject<BambooTraySingleInRecipeSerializer<BambooTrayInRainRecipe>> BAMBOO_TRAY_IN_RAIN = RECIPE_SERIALIZERS.register("bamboo_tray_in_rain", () -> new BambooTraySingleInRecipeSerializer<>(BambooTrayInRainRecipe::new, 0));
    public final static RegistryObject<BambooTraySingleInRecipeSerializer<BambooTrayBakeRecipe>> BAMBOO_TRAY_BAKE = RECIPE_SERIALIZERS.register("bamboo_tray_bake", () -> new BambooTraySingleInRecipeSerializer<>(BambooTrayBakeRecipe::new, 200));

    public final static RegistryObject<DrinkRecipeSerializer<DrinkRecipe>> DRINK_MAKER = RECIPE_SERIALIZERS.register("drink_maker", () -> new DrinkRecipeSerializer<>(DrinkRecipe::new));

    public final static RegistryObject<StoneMillRecipeSerializer<StoneMillRecipe>> STONE_MILL = RECIPE_SERIALIZERS.register("stone_mill", () -> new StoneMillRecipeSerializer<>(StoneMillRecipe::new, 200));
    public final static RegistryObject<StoneRollerRecipeSerializer<StoneRollerRecipe>> STONE_ROLLER = RECIPE_SERIALIZERS.register("stone_roller", () -> new StoneRollerRecipeSerializer<>(StoneRollerRecipe::new, 200));
}