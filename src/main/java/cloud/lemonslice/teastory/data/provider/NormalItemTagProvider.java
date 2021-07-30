package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.data.tag.NormalTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.TeaStory.MODID;
import static cloud.lemonslice.teastory.data.tag.NormalTags.Items.*;
import static net.minecraft.item.Items.*;

public final class NormalItemTagProvider extends ItemTagsProvider
{
    public NormalItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, blockTagProvider, MODID, existingFileHelper);
    }

    @Override
    protected void registerTags()
    {
        getOrCreateBuilder(NormalTags.Items.DIRT).add(Blocks.DIRT.asItem(), COARSE_DIRT.asItem(), GRASS_BLOCK.asItem(), MYCELIUM.asItem(), PODZOL.asItem());
        getOrCreateBuilder(FOOD_JERKY).add(ItemRegistry.BEEF_JERKY, ItemRegistry.PORK_JERKY, ItemRegistry.CHICKEN_JERKY, ItemRegistry.RABBIT_JERKY, ItemRegistry.MUTTON_JERKY);
        getOrCreateBuilder(FOOD_MEAT).addTags(FOOD_JERKY).add(RABBIT, PORKCHOP, BEEF, MUTTON, CHICKEN);
        getOrCreateBuilder(ItemTags.SMALL_FLOWERS).add(BlockRegistry.CHRYSANTHEMUM.asItem(), BlockRegistry.HYACINTH.asItem(), BlockRegistry.ZINNIA.asItem());
        getOrCreateBuilder(DUSTS_ASH).add(ItemRegistry.ASH);
        /*getOrCreateBuilder(AGAVE);
        getOrCreateBuilder(AMARANTH);
        getOrCreateBuilder(ARROWROOT);
        getOrCreateBuilder(ARTICHOKE);
        getOrCreateBuilder(ASPARAGUS);
        getOrCreateBuilder(BARLEY);
        getOrCreateBuilder(BEAN);
        getOrCreateBuilder(BELL_PEPPER);
        getOrCreateBuilder(BLACKBERRY);
        getOrCreateBuilder(BLUEBERRY);
        getOrCreateBuilder(BROCCOLI);
        getOrCreateBuilder(BRUSSELS_SPROUT);
        getOrCreateBuilder(CABBAGE);
        getOrCreateBuilder(CACTUS_FRUIT);
        getOrCreateBuilder(CANDLE_BERRY);
        getOrCreateBuilder(CANTALOUPE);*/
        getOrCreateBuilder(SEEDS_CARROT).add(Items.CARROT);
        /*getOrCreateBuilder(CASSAVA);
        getOrCreateBuilder(CAULIFLOWER);
        getOrCreateBuilder(CELERY);
        getOrCreateBuilder(CHICKPEA);
        getOrCreateBuilder(CHILI_PEPPER);
        getOrCreateBuilder(COFFEE_BEAN);
        getOrCreateBuilder(CORN);
        getOrCreateBuilder(COTTON);
        getOrCreateBuilder(CRANBERRY);*/
        getOrCreateBuilder(SEEDS_CUCUMBER).add(ItemRegistry.CUCUMBER);
        /*getOrCreateBuilder(CUMIN);
        getOrCreateBuilder(EGGPLANT);
        getOrCreateBuilder(ELDERBERRY);
        getOrCreateBuilder(FLAX);
        getOrCreateBuilder(GARLIC);
        getOrCreateBuilder(GINGER);*/
        getOrCreateBuilder(SEEDS_GRAPE).add(ItemRegistry.GRAPES);
        /*getOrCreateBuilder(GREEN_GRAPE);
        getOrCreateBuilder(HONEYDEW);
        getOrCreateBuilder(HUCKLEBERRY);
        getOrCreateBuilder(JICAMA);
        getOrCreateBuilder(JUNIPER_BERRY);
        getOrCreateBuilder(JUTE);
        getOrCreateBuilder(KALE);
        getOrCreateBuilder(KENAF);
        getOrCreateBuilder(KIWI);
        getOrCreateBuilder(KOHLRABI);
        getOrCreateBuilder(LEEK);
        getOrCreateBuilder(LENTIL);
        getOrCreateBuilder(LETTUCE);
        getOrCreateBuilder(MILLET);
        getOrCreateBuilder(MULBERRY);
        getOrCreateBuilder(MUSTARD_SEEDS);
        getOrCreateBuilder(OAT);
        getOrCreateBuilder(OKRA);
        getOrCreateBuilder(ONION);
        getOrCreateBuilder(PARSNIP);
        getOrCreateBuilder(PEA);
        getOrCreateBuilder(PEANUT);
        getOrCreateBuilder(PEPPER);
        getOrCreateBuilder(PINEAPPLE);*/
        getOrCreateBuilder(SEEDS_POTATO).add(Items.POTATO);
        /*getOrCreateBuilder(QUINOA);
        getOrCreateBuilder(RADISH);
        getOrCreateBuilder(RASPBERRY);
        getOrCreateBuilder(RHUBARB);*/
        getOrCreateBuilder(SEEDS_RICE).add(ItemRegistry.RICE_GRAINS);
        /*getOrCreateBuilder(RUTABAGA);
        getOrCreateBuilder(RYE);
        getOrCreateBuilder(SCALLION);
        getOrCreateBuilder(SESAME_SEEDS);
        getOrCreateBuilder(SISAL);
        getOrCreateBuilder(SORGHUM);
        getOrCreateBuilder(SOYBEAN);
        getOrCreateBuilder(SPINACH);
        getOrCreateBuilder(SUNFLOWER);
        getOrCreateBuilder(SWEET_POTATO);
        getOrCreateBuilder(TARO);
        getOrCreateBuilder(STRAWBERRY);*/
        getOrCreateBuilder(SEEDS_TEA_LEAF).add(ItemRegistry.TEA_SEEDS);
        /*getOrCreateBuilder(TOMATILLO);
        getOrCreateBuilder(TOMATO);
        getOrCreateBuilder(TURNIP);
        getOrCreateBuilder(WATER_CHESTNUT);
        getOrCreateBuilder(WHITE_MUSHROOM);
        getOrCreateBuilder(WINTER_SQUASH);
        getOrCreateBuilder(YAM);
        getOrCreateBuilder(ZUCCHINI);*/
        getOrCreateBuilder(CROPS_BLACK_TEA_LEAF).add(ItemRegistry.BLACK_TEA_LEAVES);
        getOrCreateBuilder(CROPS_GREEN_TEA_LEAF).add(ItemRegistry.GREEN_TEA_LEAVES);
        getOrCreateBuilder(CROPS_TEA_LEAF).add(ItemRegistry.TEA_LEAVES);
        getOrCreateBuilder(CROPS_WHITE_TEA_LEAF).add(ItemRegistry.WHITE_TEA_LEAVES);
        getOrCreateBuilder(CROPS_GRAPE).add(ItemRegistry.GRAPES);
        getOrCreateBuilder(CROPS_CUCUMBER).add(ItemRegistry.CUCUMBER);
        getOrCreateBuilder(CROPS_STRAW).add(ItemRegistry.DRY_STRAW);
        getOrCreateBuilder(CROPS_RICE).add(ItemRegistry.RICE);
        getOrCreateBuilder(CROPS_APPLE).add(APPLE);
        getOrCreateBuilder(CROPS_SUGAR_CANE).add(SUGAR_CANE);
    }

    @Override
    public String getName()
    {
        return "Tea the Story Item Tags";
    }
}
