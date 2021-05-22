package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.teastory.common.block.BlocksRegistry;
import cloud.lemonslice.teastory.common.item.ItemsRegistry;
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

public class NormalItemTagsProvider extends ItemTagsProvider
{
    public NormalItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, blockTagProvider, MODID, existingFileHelper);
    }

    @Override
    protected void registerTags()
    {
        getOrCreateBuilder(NormalTags.Items.DIRT).add(Blocks.DIRT.asItem(), COARSE_DIRT.asItem(), GRASS_BLOCK.asItem(), MYCELIUM.asItem(), PODZOL.asItem());
        getOrCreateBuilder(FOOD_JERKY).add(ItemsRegistry.BEEF_JERKY, ItemsRegistry.PORK_JERKY, ItemsRegistry.CHICKEN_JERKY, ItemsRegistry.RABBIT_JERKY, ItemsRegistry.MUTTON_JERKY);
        getOrCreateBuilder(FOOD_MEAT).addTags(FOOD_JERKY).add(RABBIT, PORKCHOP, BEEF, MUTTON, CHICKEN);
        getOrCreateBuilder(ItemTags.SMALL_FLOWERS).add(BlocksRegistry.CHRYSANTHEMUM_ITEM, BlocksRegistry.HYACINTH_ITEM, BlocksRegistry.ZINNIA_ITEM);
        getOrCreateBuilder(DUSTS_ASH).add(ItemsRegistry.ASH);
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
        getOrCreateBuilder(CRANBERRY);
        getOrCreateBuilder(CUCUMBER);
        getOrCreateBuilder(CUMIN);
        getOrCreateBuilder(EGGPLANT);
        getOrCreateBuilder(ELDERBERRY);
        getOrCreateBuilder(FLAX);
        getOrCreateBuilder(GARLIC);
        getOrCreateBuilder(GINGER);*/
        getOrCreateBuilder(SEEDS_GRAPE).add(ItemsRegistry.GRAPES);
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
        getOrCreateBuilder(SEEDS_RICE).add(ItemsRegistry.RICE_SEEDS);
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
        getOrCreateBuilder(SEEDS_TEA_LEAF).add(ItemsRegistry.TEA_SEEDS);
        /*getOrCreateBuilder(TOMATILLO);
        getOrCreateBuilder(TOMATO);
        getOrCreateBuilder(TURNIP);
        getOrCreateBuilder(WATER_CHESTNUT);
        getOrCreateBuilder(WHITE_MUSHROOM);
        getOrCreateBuilder(WINTER_SQUASH);
        getOrCreateBuilder(YAM);
        getOrCreateBuilder(ZUCCHINI);*/
        getOrCreateBuilder(CROPS_BLACK_TEA_LEAF).add(ItemsRegistry.BLACK_TEA_LEAVES);
        getOrCreateBuilder(CROPS_GREEN_TEA_LEAF).add(ItemsRegistry.GREEN_TEA_LEAVES);
        getOrCreateBuilder(CROPS_TEA_LEAF).add(ItemsRegistry.TEA_LEAVES);
        getOrCreateBuilder(CROPS_WHITE_TEA_LEAF).add(ItemsRegistry.WHITE_TEA_LEAVES);
        getOrCreateBuilder(CROPS_GRAPE).add(ItemsRegistry.GRAPES);
    }

    @Override
    public String getName()
    {
        return "Tea the Story Item Tags";
    }
}
