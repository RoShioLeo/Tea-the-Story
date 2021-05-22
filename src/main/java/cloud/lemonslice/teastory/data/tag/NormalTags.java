package cloud.lemonslice.teastory.data.tag;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public final class NormalTags
{
    public static class Items
    {
        public final static ITag.INamedTag<Item> ARID = ItemTags.makeWrapperTag("teastory:crops/arid_arid");
        public final static ITag.INamedTag<Item> ARID_DRY = ItemTags.makeWrapperTag("teastory:crops/arid_dry");
        public final static ITag.INamedTag<Item> ARID_AVERAGE = ItemTags.makeWrapperTag("teastory:crops/arid_average");
        public final static ITag.INamedTag<Item> ARID_MOIST = ItemTags.makeWrapperTag("teastory:crops/arid_moist");
        public final static ITag.INamedTag<Item> ARID_HUMID = ItemTags.makeWrapperTag("teastory:crops/arid_humid");
        public final static ITag.INamedTag<Item> DRY = ItemTags.makeWrapperTag("teastory:crops/dry_dry");
        public final static ITag.INamedTag<Item> DRY_AVERAGE = ItemTags.makeWrapperTag("teastory:crops/dry_average");
        public final static ITag.INamedTag<Item> DRY_MOIST = ItemTags.makeWrapperTag("teastory:crops/dry_moist");
        public final static ITag.INamedTag<Item> DRY_HUMID = ItemTags.makeWrapperTag("teastory:crops/dry_humid");
        public final static ITag.INamedTag<Item> AVERAGE = ItemTags.makeWrapperTag("teastory:crops/average_average");
        public final static ITag.INamedTag<Item> AVERAGE_MOIST = ItemTags.makeWrapperTag("teastory:crops/average_moist");
        public final static ITag.INamedTag<Item> AVERAGE_HUMID = ItemTags.makeWrapperTag("teastory:crops/average_humid");
        public final static ITag.INamedTag<Item> MOIST = ItemTags.makeWrapperTag("teastory:crops/moist_moist");
        public final static ITag.INamedTag<Item> MOIST_HUMID = ItemTags.makeWrapperTag("teastory:crops/moist_humid");
        public final static ITag.INamedTag<Item> HUMID = ItemTags.makeWrapperTag("teastory:crops/humid_humid");

        public final static ITag.INamedTag<Item> SPRING = ItemTags.makeWrapperTag("teastory:crops/spring");
        public final static ITag.INamedTag<Item> SUMMER = ItemTags.makeWrapperTag("teastory:crops/summer");
        public final static ITag.INamedTag<Item> AUTUMN = ItemTags.makeWrapperTag("teastory:crops/autumn");
        public final static ITag.INamedTag<Item> WINTER = ItemTags.makeWrapperTag("teastory:crops/winter");
        public final static ITag.INamedTag<Item> SP_SU = ItemTags.makeWrapperTag("teastory:crops/spring_summer");
        public final static ITag.INamedTag<Item> SP_AU = ItemTags.makeWrapperTag("teastory:crops/spring_autumn");
        public final static ITag.INamedTag<Item> SP_WI = ItemTags.makeWrapperTag("teastory:crops/spring_winter");
        public final static ITag.INamedTag<Item> SU_AU = ItemTags.makeWrapperTag("teastory:crops/summer_autumn");
        public final static ITag.INamedTag<Item> SU_WI = ItemTags.makeWrapperTag("teastory:crops/summer_winter");
        public final static ITag.INamedTag<Item> AU_WI = ItemTags.makeWrapperTag("teastory:crops/autumn_winter");
        public final static ITag.INamedTag<Item> SP_SU_AU = ItemTags.makeWrapperTag("teastory:crops/spring_summer_autumn");
        public final static ITag.INamedTag<Item> SP_SU_WI = ItemTags.makeWrapperTag("teastory:crops/spring_summer_winter");
        public final static ITag.INamedTag<Item> SP_AU_WI = ItemTags.makeWrapperTag("teastory:crops/spring_autumn_winter");
        public final static ITag.INamedTag<Item> SU_AU_WI = ItemTags.makeWrapperTag("teastory:crops/summer_autumn_winter");
        public final static ITag.INamedTag<Item> ALL_SEASONS = ItemTags.makeWrapperTag("teastory:crops/all_seasons");

        public final static ITag.INamedTag<Item> SEEDS_AGAVE = ItemTags.makeWrapperTag("forge:seeds/agave");
        public final static ITag.INamedTag<Item> SEEDS_AMARANTH = ItemTags.makeWrapperTag("forge:seeds/amaranth");
        public final static ITag.INamedTag<Item> SEEDS_ARROWROOT = ItemTags.makeWrapperTag("forge:seeds/arrowroot");
        public final static ITag.INamedTag<Item> SEEDS_ARTICHOKE = ItemTags.makeWrapperTag("forge:seeds/artichoke");
        public final static ITag.INamedTag<Item> SEEDS_ASPARAGUS = ItemTags.makeWrapperTag("forge:seeds/asparagus");
        public final static ITag.INamedTag<Item> SEEDS_BARLEY = ItemTags.makeWrapperTag("forge:seeds/barley");
        public final static ITag.INamedTag<Item> SEEDS_BEAN = ItemTags.makeWrapperTag("forge:seeds/bean");
        public final static ITag.INamedTag<Item> SEEDS_BELL_PEPPER = ItemTags.makeWrapperTag("forge:seeds/bell_pepper");
        public final static ITag.INamedTag<Item> SEEDS_BLACKBERRY = ItemTags.makeWrapperTag("forge:seeds/blackberry");
        public final static ITag.INamedTag<Item> SEEDS_BLUEBERRY = ItemTags.makeWrapperTag("forge:seeds/blueberry");
        public final static ITag.INamedTag<Item> SEEDS_BROCCOLI = ItemTags.makeWrapperTag("forge:seeds/broccoli");
        public final static ITag.INamedTag<Item> SEEDS_BRUSSELS_SPROUT = ItemTags.makeWrapperTag("forge:seeds/brussels_sprout");
        public final static ITag.INamedTag<Item> SEEDS_CABBAGE = ItemTags.makeWrapperTag("forge:seeds/cabbage");
        public final static ITag.INamedTag<Item> SEEDS_CACTUS_FRUIT = ItemTags.makeWrapperTag("forge:seeds/cactus_fruit");
        public final static ITag.INamedTag<Item> SEEDS_CANDLE_BERRY = ItemTags.makeWrapperTag("forge:seeds/candle_berry");
        public final static ITag.INamedTag<Item> SEEDS_CANTALOUPE = ItemTags.makeWrapperTag("forge:seeds/cantaloupe");
        public final static ITag.INamedTag<Item> SEEDS_CARROT = ItemTags.makeWrapperTag("forge:seeds/carrot");
        public final static ITag.INamedTag<Item> SEEDS_CASSAVA = ItemTags.makeWrapperTag("forge:seeds/cassava");
        public final static ITag.INamedTag<Item> SEEDS_CAULIFLOWER = ItemTags.makeWrapperTag("forge:seeds/cauliflower");
        public final static ITag.INamedTag<Item> SEEDS_CELERY = ItemTags.makeWrapperTag("forge:seeds/celery");
        public final static ITag.INamedTag<Item> SEEDS_CHICKPEA = ItemTags.makeWrapperTag("forge:seeds/chickpea");
        public final static ITag.INamedTag<Item> SEEDS_CHILI_PEPPER = ItemTags.makeWrapperTag("forge:seeds/chili_pepper");
        public final static ITag.INamedTag<Item> SEEDS_COFFEE_BEAN = ItemTags.makeWrapperTag("forge:seeds/coffee_bean");
        public final static ITag.INamedTag<Item> SEEDS_CORN = ItemTags.makeWrapperTag("forge:seeds/corn");
        public final static ITag.INamedTag<Item> SEEDS_COTTON = ItemTags.makeWrapperTag("forge:seeds/cotton");
        public final static ITag.INamedTag<Item> SEEDS_CRANBERRY = ItemTags.makeWrapperTag("forge:seeds/cranberry");
        public final static ITag.INamedTag<Item> SEEDS_CUCUMBER = ItemTags.makeWrapperTag("forge:seeds/cucumber");
        public final static ITag.INamedTag<Item> SEEDS_CUMIN = ItemTags.makeWrapperTag("forge:seeds/cumin");
        public final static ITag.INamedTag<Item> SEEDS_EGGPLANT = ItemTags.makeWrapperTag("forge:seeds/eggplant");
        public final static ITag.INamedTag<Item> SEEDS_ELDERBERRY = ItemTags.makeWrapperTag("forge:seeds/elderberry");
        public final static ITag.INamedTag<Item> SEEDS_FLAX = ItemTags.makeWrapperTag("forge:seeds/flax");
        public final static ITag.INamedTag<Item> SEEDS_GARLIC = ItemTags.makeWrapperTag("forge:seeds/garlic");
        public final static ITag.INamedTag<Item> SEEDS_GINGER = ItemTags.makeWrapperTag("forge:seeds/ginger");
        public final static ITag.INamedTag<Item> SEEDS_GRAPE = ItemTags.makeWrapperTag("forge:seeds/grape");
        public final static ITag.INamedTag<Item> SEEDS_GREEN_GRAPE = ItemTags.makeWrapperTag("forge:seeds/green_grape");
        public final static ITag.INamedTag<Item> SEEDS_HONEYDEW = ItemTags.makeWrapperTag("forge:seeds/honeydew");
        public final static ITag.INamedTag<Item> SEEDS_HUCKLEBERRY = ItemTags.makeWrapperTag("forge:seeds/huckleberry");
        public final static ITag.INamedTag<Item> SEEDS_JICAMA = ItemTags.makeWrapperTag("forge:seeds/jicama");
        public final static ITag.INamedTag<Item> SEEDS_JUNIPER_BERRY = ItemTags.makeWrapperTag("forge:seeds/juniper_berry");
        public final static ITag.INamedTag<Item> SEEDS_JUTE = ItemTags.makeWrapperTag("forge:seeds/jute");
        public final static ITag.INamedTag<Item> SEEDS_KALE = ItemTags.makeWrapperTag("forge:seeds/kale");
        public final static ITag.INamedTag<Item> SEEDS_KENAF = ItemTags.makeWrapperTag("forge:seeds/kenaf");
        public final static ITag.INamedTag<Item> SEEDS_KIWI = ItemTags.makeWrapperTag("forge:seeds/kiwi");
        public final static ITag.INamedTag<Item> SEEDS_KOHLRABI = ItemTags.makeWrapperTag("forge:seeds/kohlrabi");
        public final static ITag.INamedTag<Item> SEEDS_LEEK = ItemTags.makeWrapperTag("forge:seeds/leek");
        public final static ITag.INamedTag<Item> SEEDS_LENTIL = ItemTags.makeWrapperTag("forge:seeds/lentil");
        public final static ITag.INamedTag<Item> SEEDS_LETTUCE = ItemTags.makeWrapperTag("forge:seeds/lettuce");
        public final static ITag.INamedTag<Item> SEEDS_MILLET = ItemTags.makeWrapperTag("forge:seeds/millet");
        public final static ITag.INamedTag<Item> SEEDS_MULBERRY = ItemTags.makeWrapperTag("forge:seeds/mulberry");
        public final static ITag.INamedTag<Item> SEEDS_MUSTARD_SEEDS = ItemTags.makeWrapperTag("forge:seeds/mustard_seeds");
        public final static ITag.INamedTag<Item> SEEDS_OAT = ItemTags.makeWrapperTag("forge:seeds/oat");
        public final static ITag.INamedTag<Item> SEEDS_OKRA = ItemTags.makeWrapperTag("forge:seeds/okra");
        public final static ITag.INamedTag<Item> SEEDS_ONION = ItemTags.makeWrapperTag("forge:seeds/onion");
        public final static ITag.INamedTag<Item> SEEDS_PARSNIP = ItemTags.makeWrapperTag("forge:seeds/parsnip");
        public final static ITag.INamedTag<Item> SEEDS_PEA = ItemTags.makeWrapperTag("forge:seeds/pea");
        public final static ITag.INamedTag<Item> SEEDS_PEANUT = ItemTags.makeWrapperTag("forge:seeds/peanut");
        public final static ITag.INamedTag<Item> SEEDS_PEPPER = ItemTags.makeWrapperTag("forge:seeds/pepper");
        public final static ITag.INamedTag<Item> SEEDS_PINEAPPLE = ItemTags.makeWrapperTag("forge:seeds/pineapple");
        public final static ITag.INamedTag<Item> SEEDS_POTATO = ItemTags.makeWrapperTag("forge:seeds/potato");
        public final static ITag.INamedTag<Item> SEEDS_QUINOA = ItemTags.makeWrapperTag("forge:seeds/quinoa");
        public final static ITag.INamedTag<Item> SEEDS_RADISH = ItemTags.makeWrapperTag("forge:seeds/radish");
        public final static ITag.INamedTag<Item> SEEDS_RASPBERRY = ItemTags.makeWrapperTag("forge:seeds/raspberry");
        public final static ITag.INamedTag<Item> SEEDS_RHUBARB = ItemTags.makeWrapperTag("forge:seeds/rhubarb");
        public final static ITag.INamedTag<Item> SEEDS_RICE = ItemTags.makeWrapperTag("forge:seeds/rice");
        public final static ITag.INamedTag<Item> SEEDS_RUTABAGA = ItemTags.makeWrapperTag("forge:seeds/rutabaga");
        public final static ITag.INamedTag<Item> SEEDS_RYE = ItemTags.makeWrapperTag("forge:seeds/rye");
        public final static ITag.INamedTag<Item> SEEDS_SCALLION = ItemTags.makeWrapperTag("forge:seeds/scallion");
        public final static ITag.INamedTag<Item> SEEDS_SESAME_SEEDS = ItemTags.makeWrapperTag("forge:seeds/sesame_seeds");
        public final static ITag.INamedTag<Item> SEEDS_SISAL = ItemTags.makeWrapperTag("forge:seeds/sisal");
        public final static ITag.INamedTag<Item> SEEDS_SORGHUM = ItemTags.makeWrapperTag("forge:seeds/sorghum");
        public final static ITag.INamedTag<Item> SEEDS_SOYBEAN = ItemTags.makeWrapperTag("forge:seeds/soybean");
        public final static ITag.INamedTag<Item> SEEDS_SPINACH = ItemTags.makeWrapperTag("forge:seeds/spinach");
        public final static ITag.INamedTag<Item> SEEDS_STRAWBERRY = ItemTags.makeWrapperTag("forge:seeds/strawberry");
        public final static ITag.INamedTag<Item> SEEDS_SUNFLOWER = ItemTags.makeWrapperTag("forge:seeds/sunflower");
        public final static ITag.INamedTag<Item> SEEDS_SWEET_POTATO = ItemTags.makeWrapperTag("forge:seeds/sweet_potato");
        public final static ITag.INamedTag<Item> SEEDS_TARO = ItemTags.makeWrapperTag("forge:seeds/taro");
        public final static ITag.INamedTag<Item> SEEDS_TEA_LEAF = ItemTags.makeWrapperTag("forge:seeds/tea_leaf");
        public final static ITag.INamedTag<Item> SEEDS_TOMATILLO = ItemTags.makeWrapperTag("forge:seeds/tomatillo");
        public final static ITag.INamedTag<Item> SEEDS_TOMATO = ItemTags.makeWrapperTag("forge:seeds/tomato");
        public final static ITag.INamedTag<Item> SEEDS_TURNIP = ItemTags.makeWrapperTag("forge:seeds/turnip");
        public final static ITag.INamedTag<Item> SEEDS_WATER_CHESTNUT = ItemTags.makeWrapperTag("forge:seeds/water_chestnut");
        public final static ITag.INamedTag<Item> SEEDS_WHITE_MUSHROOM = ItemTags.makeWrapperTag("forge:seeds/white_mushroom");
        public final static ITag.INamedTag<Item> SEEDS_WINTER_SQUASH = ItemTags.makeWrapperTag("forge:seeds/winter_squash");
        public final static ITag.INamedTag<Item> SEEDS_YAM = ItemTags.makeWrapperTag("forge:seeds/yam");
        public final static ITag.INamedTag<Item> SEEDS_ZUCCHINI = ItemTags.makeWrapperTag("forge:seeds/zucchini");

        public final static ITag.INamedTag<Item> CROPS_BLACK_TEA_LEAF = ItemTags.makeWrapperTag("forge:crops/black_tea_leaf");
        public final static ITag.INamedTag<Item> CROPS_GREEN_TEA_LEAF = ItemTags.makeWrapperTag("forge:crops/green_tea_leaf");
        public final static ITag.INamedTag<Item> CROPS_TEA_LEAF = ItemTags.makeWrapperTag("forge:crops/tea_leaf");
        public final static ITag.INamedTag<Item> CROPS_WHITE_TEA_LEAF = ItemTags.makeWrapperTag("forge:crops/white_tea_leaf");
        public final static ITag.INamedTag<Item> CROPS_GRAPE = ItemTags.makeWrapperTag("forge:crops/grape");

        public final static ITag.INamedTag<Item> FOOD_JERKY = ItemTags.makeWrapperTag("forge:food/jerky");
        public final static ITag.INamedTag<Item> FOOD_MEAT = ItemTags.makeWrapperTag("forge:food/meat");

        public final static ITag.INamedTag<Item> DIRT = ItemTags.makeWrapperTag("forge:dirt");
        public final static ITag.INamedTag<Item> DUSTS_ASH = ItemTags.makeWrapperTag("forge:dusts/ash");
    }

    public static class Fluids
    {
        public final static ITag.INamedTag<Fluid> DRINK = FluidTags.makeWrapperTag("teastory:drink");
    }
}
