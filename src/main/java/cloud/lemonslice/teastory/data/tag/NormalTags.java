package cloud.lemonslice.teastory.data.tag;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public final class NormalTags
{
    public static class Items
    {
        public final static Tags.IOptionalNamedTag<Item> ARID = ItemTags.createOptional(new ResourceLocation("teastory:crops/arid_arid"));
        public final static Tags.IOptionalNamedTag<Item> ARID_DRY = ItemTags.createOptional(new ResourceLocation("teastory:crops/arid_dry"));
        public final static Tags.IOptionalNamedTag<Item> ARID_AVERAGE = ItemTags.createOptional(new ResourceLocation("teastory:crops/arid_average"));
        public final static Tags.IOptionalNamedTag<Item> ARID_MOIST = ItemTags.createOptional(new ResourceLocation("teastory:crops/arid_moist"));
        public final static Tags.IOptionalNamedTag<Item> ARID_HUMID = ItemTags.createOptional(new ResourceLocation("teastory:crops/arid_humid"));
        public final static Tags.IOptionalNamedTag<Item> DRY = ItemTags.createOptional(new ResourceLocation("teastory:crops/dry_dry"));
        public final static Tags.IOptionalNamedTag<Item> DRY_AVERAGE = ItemTags.createOptional(new ResourceLocation("teastory:crops/dry_average"));
        public final static Tags.IOptionalNamedTag<Item> DRY_MOIST = ItemTags.createOptional(new ResourceLocation("teastory:crops/dry_moist"));
        public final static Tags.IOptionalNamedTag<Item> DRY_HUMID = ItemTags.createOptional(new ResourceLocation("teastory:crops/dry_humid"));
        public final static Tags.IOptionalNamedTag<Item> AVERAGE = ItemTags.createOptional(new ResourceLocation("teastory:crops/average_average"));
        public final static Tags.IOptionalNamedTag<Item> AVERAGE_MOIST = ItemTags.createOptional(new ResourceLocation("teastory:crops/average_moist"));
        public final static Tags.IOptionalNamedTag<Item> AVERAGE_HUMID = ItemTags.createOptional(new ResourceLocation("teastory:crops/average_humid"));
        public final static Tags.IOptionalNamedTag<Item> MOIST = ItemTags.createOptional(new ResourceLocation("teastory:crops/moist_moist"));
        public final static Tags.IOptionalNamedTag<Item> MOIST_HUMID = ItemTags.createOptional(new ResourceLocation("teastory:crops/moist_humid"));
        public final static Tags.IOptionalNamedTag<Item> HUMID = ItemTags.createOptional(new ResourceLocation("teastory:crops/humid_humid"));

        public final static Tags.IOptionalNamedTag<Item> SPRING = ItemTags.createOptional(new ResourceLocation("teastory:crops/spring"));
        public final static Tags.IOptionalNamedTag<Item> SUMMER = ItemTags.createOptional(new ResourceLocation("teastory:crops/summer"));
        public final static Tags.IOptionalNamedTag<Item> AUTUMN = ItemTags.createOptional(new ResourceLocation("teastory:crops/autumn"));
        public final static Tags.IOptionalNamedTag<Item> WINTER = ItemTags.createOptional(new ResourceLocation("teastory:crops/winter"));
        public final static Tags.IOptionalNamedTag<Item> SP_SU = ItemTags.createOptional(new ResourceLocation("teastory:crops/spring_summer"));
        public final static Tags.IOptionalNamedTag<Item> SP_AU = ItemTags.createOptional(new ResourceLocation("teastory:crops/spring_autumn"));
        public final static Tags.IOptionalNamedTag<Item> SP_WI = ItemTags.createOptional(new ResourceLocation("teastory:crops/spring_winter"));
        public final static Tags.IOptionalNamedTag<Item> SU_AU = ItemTags.createOptional(new ResourceLocation("teastory:crops/summer_autumn"));
        public final static Tags.IOptionalNamedTag<Item> SU_WI = ItemTags.createOptional(new ResourceLocation("teastory:crops/summer_winter"));
        public final static Tags.IOptionalNamedTag<Item> AU_WI = ItemTags.createOptional(new ResourceLocation("teastory:crops/autumn_winter"));
        public final static Tags.IOptionalNamedTag<Item> SP_SU_AU = ItemTags.createOptional(new ResourceLocation("teastory:crops/spring_summer_autumn"));
        public final static Tags.IOptionalNamedTag<Item> SP_SU_WI = ItemTags.createOptional(new ResourceLocation("teastory:crops/spring_summer_winter"));
        public final static Tags.IOptionalNamedTag<Item> SP_AU_WI = ItemTags.createOptional(new ResourceLocation("teastory:crops/spring_autumn_winter"));
        public final static Tags.IOptionalNamedTag<Item> SU_AU_WI = ItemTags.createOptional(new ResourceLocation("teastory:crops/summer_autumn_winter"));
        public final static Tags.IOptionalNamedTag<Item> ALL_SEASONS = ItemTags.createOptional(new ResourceLocation("teastory:crops/all_seasons"));

        public final static Tags.IOptionalNamedTag<Item> SEEDS_AGAVE = ItemTags.createOptional(new ResourceLocation("forge:seeds/agave"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_AMARANTH = ItemTags.createOptional(new ResourceLocation("forge:seeds/amaranth"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_ARROWROOT = ItemTags.createOptional(new ResourceLocation("forge:seeds/arrowroot"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_ARTICHOKE = ItemTags.createOptional(new ResourceLocation("forge:seeds/artichoke"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_ASPARAGUS = ItemTags.createOptional(new ResourceLocation("forge:seeds/asparagus"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_BARLEY = ItemTags.createOptional(new ResourceLocation("forge:seeds/barley"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_BEAN = ItemTags.createOptional(new ResourceLocation("forge:seeds/bean"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_BELL_PEPPER = ItemTags.createOptional(new ResourceLocation("forge:seeds/bell_pepper"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_BLACKBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/blackberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_BLUEBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/blueberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_BROCCOLI = ItemTags.createOptional(new ResourceLocation("forge:seeds/broccoli"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_BRUSSELS_SPROUT = ItemTags.createOptional(new ResourceLocation("forge:seeds/brussels_sprout"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CABBAGE = ItemTags.createOptional(new ResourceLocation("forge:seeds/cabbage"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CACTUS_FRUIT = ItemTags.createOptional(new ResourceLocation("forge:seeds/cactus_fruit"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CANDLE_BERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/candle_berry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CANTALOUPE = ItemTags.createOptional(new ResourceLocation("forge:seeds/cantaloupe"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CARROT = ItemTags.createOptional(new ResourceLocation("forge:seeds/carrot"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CASSAVA = ItemTags.createOptional(new ResourceLocation("forge:seeds/cassava"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CAULIFLOWER = ItemTags.createOptional(new ResourceLocation("forge:seeds/cauliflower"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CELERY = ItemTags.createOptional(new ResourceLocation("forge:seeds/celery"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CHICKPEA = ItemTags.createOptional(new ResourceLocation("forge:seeds/chickpea"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CHILI_PEPPER = ItemTags.createOptional(new ResourceLocation("forge:seeds/chili_pepper"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_COFFEE_BEAN = ItemTags.createOptional(new ResourceLocation("forge:seeds/coffee_bean"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CORN = ItemTags.createOptional(new ResourceLocation("forge:seeds/corn"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_COTTON = ItemTags.createOptional(new ResourceLocation("forge:seeds/cotton"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CRANBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/cranberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CUCUMBER = ItemTags.createOptional(new ResourceLocation("forge:seeds/cucumber"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_CUMIN = ItemTags.createOptional(new ResourceLocation("forge:seeds/cumin"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_EGGPLANT = ItemTags.createOptional(new ResourceLocation("forge:seeds/eggplant"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_ELDERBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/elderberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_FLAX = ItemTags.createOptional(new ResourceLocation("forge:seeds/flax"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_GARLIC = ItemTags.createOptional(new ResourceLocation("forge:seeds/garlic"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_GINGER = ItemTags.createOptional(new ResourceLocation("forge:seeds/ginger"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_GRAPE = ItemTags.createOptional(new ResourceLocation("forge:seeds/grape"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_GREEN_GRAPE = ItemTags.createOptional(new ResourceLocation("forge:seeds/green_grape"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_HONEYDEW = ItemTags.createOptional(new ResourceLocation("forge:seeds/honeydew"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_HUCKLEBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/huckleberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_JICAMA = ItemTags.createOptional(new ResourceLocation("forge:seeds/jicama"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_JUNIPER_BERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/juniper_berry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_JUTE = ItemTags.createOptional(new ResourceLocation("forge:seeds/jute"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_KALE = ItemTags.createOptional(new ResourceLocation("forge:seeds/kale"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_KENAF = ItemTags.createOptional(new ResourceLocation("forge:seeds/kenaf"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_KIWI = ItemTags.createOptional(new ResourceLocation("forge:seeds/kiwi"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_KOHLRABI = ItemTags.createOptional(new ResourceLocation("forge:seeds/kohlrabi"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_LEEK = ItemTags.createOptional(new ResourceLocation("forge:seeds/leek"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_LENTIL = ItemTags.createOptional(new ResourceLocation("forge:seeds/lentil"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_LETTUCE = ItemTags.createOptional(new ResourceLocation("forge:seeds/lettuce"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_MILLET = ItemTags.createOptional(new ResourceLocation("forge:seeds/millet"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_MULBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/mulberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_MUSTARD_SEEDS = ItemTags.createOptional(new ResourceLocation("forge:seeds/mustard_seeds"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_OAT = ItemTags.createOptional(new ResourceLocation("forge:seeds/oat"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_OKRA = ItemTags.createOptional(new ResourceLocation("forge:seeds/okra"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_ONION = ItemTags.createOptional(new ResourceLocation("forge:seeds/onion"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_PARSNIP = ItemTags.createOptional(new ResourceLocation("forge:seeds/parsnip"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_PEA = ItemTags.createOptional(new ResourceLocation("forge:seeds/pea"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_PEANUT = ItemTags.createOptional(new ResourceLocation("forge:seeds/peanut"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_PEPPER = ItemTags.createOptional(new ResourceLocation("forge:seeds/pepper"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_PINEAPPLE = ItemTags.createOptional(new ResourceLocation("forge:seeds/pineapple"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_POTATO = ItemTags.createOptional(new ResourceLocation("forge:seeds/potato"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_QUINOA = ItemTags.createOptional(new ResourceLocation("forge:seeds/quinoa"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_RADISH = ItemTags.createOptional(new ResourceLocation("forge:seeds/radish"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_RASPBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/raspberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_RHUBARB = ItemTags.createOptional(new ResourceLocation("forge:seeds/rhubarb"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_RICE = ItemTags.createOptional(new ResourceLocation("forge:seeds/rice"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_RUTABAGA = ItemTags.createOptional(new ResourceLocation("forge:seeds/rutabaga"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_RYE = ItemTags.createOptional(new ResourceLocation("forge:seeds/rye"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SCALLION = ItemTags.createOptional(new ResourceLocation("forge:seeds/scallion"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SESAME_SEEDS = ItemTags.createOptional(new ResourceLocation("forge:seeds/sesame_seeds"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SISAL = ItemTags.createOptional(new ResourceLocation("forge:seeds/sisal"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SORGHUM = ItemTags.createOptional(new ResourceLocation("forge:seeds/sorghum"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SOYBEAN = ItemTags.createOptional(new ResourceLocation("forge:seeds/soybean"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SPINACH = ItemTags.createOptional(new ResourceLocation("forge:seeds/spinach"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_STRAWBERRY = ItemTags.createOptional(new ResourceLocation("forge:seeds/strawberry"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SUNFLOWER = ItemTags.createOptional(new ResourceLocation("forge:seeds/sunflower"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_SWEET_POTATO = ItemTags.createOptional(new ResourceLocation("forge:seeds/sweet_potato"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_TARO = ItemTags.createOptional(new ResourceLocation("forge:seeds/taro"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_TEA_LEAF = ItemTags.createOptional(new ResourceLocation("forge:seeds/tea_leaf"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_TOMATILLO = ItemTags.createOptional(new ResourceLocation("forge:seeds/tomatillo"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_TOMATO = ItemTags.createOptional(new ResourceLocation("forge:seeds/tomato"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_TURNIP = ItemTags.createOptional(new ResourceLocation("forge:seeds/turnip"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_WATER_CHESTNUT = ItemTags.createOptional(new ResourceLocation("forge:seeds/water_chestnut"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_WHITE_MUSHROOM = ItemTags.createOptional(new ResourceLocation("forge:seeds/white_mushroom"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_WINTER_SQUASH = ItemTags.createOptional(new ResourceLocation("forge:seeds/winter_squash"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_YAM = ItemTags.createOptional(new ResourceLocation("forge:seeds/yam"));
        public final static Tags.IOptionalNamedTag<Item> SEEDS_ZUCCHINI = ItemTags.createOptional(new ResourceLocation("forge:seeds/zucchini"));

        public final static Tags.IOptionalNamedTag<Item> CROPS_BLACK_TEA_LEAF = ItemTags.createOptional(new ResourceLocation("forge:crops/black_tea_leaf"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_GREEN_TEA_LEAF = ItemTags.createOptional(new ResourceLocation("forge:crops/green_tea_leaf"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_TEA_LEAF = ItemTags.createOptional(new ResourceLocation("forge:crops/tea_leaf"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_WHITE_TEA_LEAF = ItemTags.createOptional(new ResourceLocation("forge:crops/white_tea_leaf"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_GRAPE = ItemTags.createOptional(new ResourceLocation("forge:crops/grape"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_CUCUMBER = ItemTags.createOptional(new ResourceLocation("forge:crops/cucumber"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_STRAW = ItemTags.createOptional(new ResourceLocation("forge:crops/straw"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_RICE = ItemTags.createOptional(new ResourceLocation("forge:crops/rice"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_APPLE = ItemTags.createOptional(new ResourceLocation("forge:crops/apple"));
        public final static Tags.IOptionalNamedTag<Item> CROPS_SUGAR_CANE = ItemTags.createOptional(new ResourceLocation("forge:crops/sugar_cane"));

        public final static Tags.IOptionalNamedTag<Item> FOOD_JERKY = ItemTags.createOptional(new ResourceLocation("forge:food/jerky"));
        public final static Tags.IOptionalNamedTag<Item> FOOD_MEAT = ItemTags.createOptional(new ResourceLocation("forge:food/meat"));

        public final static Tags.IOptionalNamedTag<Item> DIRT = ItemTags.createOptional(new ResourceLocation("forge:dirt"));
        public final static Tags.IOptionalNamedTag<Item> DUSTS_ASH = ItemTags.createOptional(new ResourceLocation("forge:dusts/ash"));
    }

    public static class Blocks
    {

    }

    public static class Fluids
    {
        public final static Tags.IOptionalNamedTag<Fluid> DRINK = FluidTags.createOptional(new ResourceLocation("teastory:drink"));
    }
}
