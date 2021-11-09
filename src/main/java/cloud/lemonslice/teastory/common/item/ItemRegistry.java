package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalBlockItem;
import cloud.lemonslice.silveroak.common.item.NormalItem;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.block.crops.VineType;
import cloud.lemonslice.teastory.common.item.food.FoodItem;
import cloud.lemonslice.teastory.common.item.food.NormalFoods;
import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.ItemFluidContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.client.sound.SoundEventsRegistry.*;
import static cloud.lemonslice.teastory.common.block.BlockRegistry.*;

public final class ItemRegistry extends RegistryModule
{
    // BLOCKS 方块
    public static final BlockItem BAMBOO_TRAY_ITEM = new NormalBlockItem(BAMBOO_TRAY, TeaStory.GROUP_CORE);
    public static final BlockItem DRINK_MAKER_ITEM = new DrinkMakerItem();
    public static final BlockItem DIRT_STOVE_ITEM = new NormalBlockItem(DIRT_STOVE, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_STOVE_ITEM = new NormalBlockItem(STONE_STOVE, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_MILL_ITEM = new NormalBlockItem(STONE_MILL, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_ROLLER_ITEM = new NormalBlockItem(STONE_ROLLER, TeaStory.GROUP_CORE);
    public static final BlockItem WOODEN_BARREL_ITEM = new NormalBlockItem(WOODEN_BARREL, TeaStory.GROUP_CORE);
    public static final BlockItem SAUCEPAN_ITEM = new NormalBlockItem(SAUCEPAN, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_CATAPULT_BOARD_ITEM = new NormalBlockItem(STONE_CATAPULT_BOARD, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_CATAPULT_BOARD_ITEM = new NormalBlockItem(BAMBOO_CATAPULT_BOARD, TeaStory.GROUP_CORE);
    public static final BlockItem IRON_CATAPULT_BOARD_ITEM = new NormalBlockItem(IRON_CATAPULT_BOARD, TeaStory.GROUP_CORE);
    public static final BlockItem FILTER_SCREEN_ITEM = new NormalBlockItem(FILTER_SCREEN, TeaStory.GROUP_CORE);
    public static final BlockItem WOODEN_FRAME_ITEM = new NormalBlockItem(WOODEN_FRAME, TeaStory.GROUP_CORE);

    public static final BlockItem OAK_TRELLIS_ITEM = new NormalBlockItem(OAK_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem BIRCH_TRELLIS_ITEM = new NormalBlockItem(BIRCH_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem JUNGLE_TRELLIS_ITEM = new NormalBlockItem(JUNGLE_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem SPRUCE_TRELLIS_ITEM = new NormalBlockItem(SPRUCE_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem DARK_OAK_TRELLIS_ITEM = new NormalBlockItem(DARK_OAK_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem ACACIA_TRELLIS_ITEM = new NormalBlockItem(ACACIA_TRELLIS, TeaStory.GROUP_CORE);

    public static final BlockItem COBBLESTONE_AQUEDUCT_ITEM = new NormalBlockItem(COBBLESTONE_AQUEDUCT, TeaStory.GROUP_CORE);
    public static final BlockItem MOSSY_COBBLESTONE_AQUEDUCT_ITEM = new NormalBlockItem(MOSSY_COBBLESTONE_AQUEDUCT, TeaStory.GROUP_CORE);

    public static final BlockItem WOODEN_TRAY_ITEM = new NormalBlockItem(WOODEN_TRAY, TeaStory.GROUP_DRINK);

    public static final BlockItem WOODEN_TABLE_ITEM = new NormalBlockItem(WOODEN_TABLE, TeaStory.GROUP_CORE);
    public static final BlockItem WOODEN_CHAIR_ITEM = new NormalBlockItem(WOODEN_CHAIR, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_TABLE_ITEM = new NormalBlockItem(STONE_TABLE, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_CHAIR_ITEM = new NormalBlockItem(STONE_CHAIR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_TABLE_ITEM = new NormalBlockItem(BAMBOO_TABLE, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_CHAIR_ITEM = new NormalBlockItem(BAMBOO_CHAIR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_LANTERN_ITEM = new NormalBlockItem(BAMBOO_LANTERN, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_DOOR_ITEM = new NormalBlockItem(BAMBOO_DOOR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_GLASS_DOOR_ITEM = new NormalBlockItem(BAMBOO_GLASS_DOOR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_LATTICE_ITEM = new NormalBlockItem(BAMBOO_LATTICE, TeaStory.GROUP_CORE);
    public static final BlockItem FRESH_BAMBOO_WALL_ITEM = new NormalBlockItem(FRESH_BAMBOO_WALL, TeaStory.GROUP_CORE);
    public static final BlockItem DRIED_BAMBOO_WALL_ITEM = new NormalBlockItem(DRIED_BAMBOO_WALL, TeaStory.GROUP_CORE);
//    public static final BlockItem BAMBOO_GLASS_WINDOW_ITEM = new NormalBlockItem(BAMBOO_GLASS_WINDOW);

    public static final BlockItem PADDY_FIELD_ITEM = new NormalBlockItem(PADDY_FIELD, TeaStory.GROUP_CORE);
    public static final BlockItem WILD_TEA_PLANT_ITEM = new NormalBlockItem(WILD_TEA_PLANT, TeaStory.GROUP_CORE);
    public static final BlockItem WILD_GRAPE_ITEM = new NormalBlockItem(WILD_GRAPE, TeaStory.GROUP_CORE);
    public static final BlockItem CHRYSANTHEMUM_ITEM = new HybridizableFlowerBlockItem(CHRYSANTHEMUM);
    public static final BlockItem HYACINTH_ITEM = new HybridizableFlowerBlockItem(HYACINTH);
    public static final BlockItem ZINNIA_ITEM = new HybridizableFlowerBlockItem(ZINNIA);

    public static final BlockItem SCARECROW_ITEM = new NormalBlockItem(SCARECROW, TeaStory.GROUP_CORE);
    public static final BlockItem DRY_HAYSTACK_ITEM = new NormalBlockItem(DRY_HAYSTACK, TeaStory.GROUP_CORE);
    public static final BlockItem WET_HAYSTACK_ITEM = new NormalBlockItem(WET_HAYSTACK, TeaStory.GROUP_CORE);

    public static final BlockItem GRASS_BLOCK_WITH_HOLE_ITEM = new NormalBlockItem(GRASS_BLOCK_WITH_HOLE, TeaStory.GROUP_CORE);
    public static final BlockItem CHILI_PLANT_ITEM = new NormalBlockItem(CHILI_PLANT, (ItemGroup) null);
    public static final BlockItem CHINESE_CABBAGE_PLANT_ITEM = new NormalBlockItem(CHINESE_CABBAGE_PLANT, (ItemGroup) null);

    public static final BlockItem WOODEN_BOWL_ITEM = new NormalBlockItem(BlockRegistry.WOODEN_BOWL, TeaStory.GROUP_CORE);
    // TOOL 工具
    public static final Item WOODEN_AQUEDUCT_SHOVEL = new AqueductShovelItem("wooden_aqueduct_shovel", ItemTier.WOOD, 1.5F, -2.5F, getNormalItemProperties());
    public static final Item STONE_AQUEDUCT_SHOVEL = new AqueductShovelItem("stone_aqueduct_shovel", ItemTier.STONE, 1.5F, -2.5F, getNormalItemProperties());
    public static final Item GOLD_AQUEDUCT_SHOVEL = new AqueductShovelItem("gold_aqueduct_shovel", ItemTier.GOLD, 1.5F, -2.5F, getNormalItemProperties());
    public static final Item IRON_AQUEDUCT_SHOVEL = new AqueductShovelItem("iron_aqueduct_shovel", ItemTier.IRON, 1.5F, -2.5F, getNormalItemProperties());
    public static final Item DIAMOND_AQUEDUCT_SHOVEL = new AqueductShovelItem("diamond_aqueduct_shovel", ItemTier.DIAMOND, 1.5F, -2.5F, getNormalItemProperties());
    //    public static final Item NETHERITE_AQUEDUCT_SHOVEL = new AqueductShovelItem("netherite_aqueduct_shovel", ItemTier.NETHERITE, 1.5F, -2.5F, getNormalItemProperties());
    public static final Item IRON_SICKLE = new SickleItem("iron_sickle", ItemTier.IRON, 1.5F, -2.5F, getNormalItemProperties());
    public static final Item SHENNONG_CHI = new ShennongChiItem();

    // INGREDIENTS 原料
    public static final Item TEA_LEAVES = new NormalItem("tea_leaves", getNormalItemProperties());
    public static final Item GREEN_TEA_LEAVES = new NormalItem("green_tea_leaves", getTeaLeavesItemProperties());
    public static final Item BLACK_TEA_LEAVES = new NormalItem("black_tea_leaves", getTeaLeavesItemProperties());
    public static final Item WHITE_TEA_LEAVES = new NormalItem("white_tea_leaves", getTeaLeavesItemProperties());
    public static final Item EMPTY_TEA_BAG = new NormalItem("empty_tea_bag", getTeaLeavesItemProperties());
    public static final Item GREEN_TEA_BAG = new NormalItem("green_tea_bag", getTeaLeavesItemProperties());
    public static final Item BLACK_TEA_BAG = new NormalItem("black_tea_bag", getTeaLeavesItemProperties());

    public static final Item RICE = new NormalItem("rice", getNormalItemProperties());
    public static final Item WASHED_RICE = new NormalItem("washed_rice", getNormalItemProperties());

    // CROPS 作物
    public static final Item TEA_SEEDS = new BlockNamedItem(BlockRegistry.TEA_PLANT, getNormalItemProperties()).setRegistryName("tea_seeds");
    public static final Item RICE_GRAINS = new BlockNamedItem(BlockRegistry.RICE_SEEDLING, getNormalItemProperties()).setRegistryName("rice_grains");
    public static final Item RICE_SEEDLINGS = new BlockNamedItem(BlockRegistry.RICE_PLANT, getNormalItemProperties()).setRegistryName("rice_seedlings");
    public static final Item GRAPES = new VineSeedsItem("grapes", VineType.GRAPE, NormalFoods.GRAPE);
    public static final Item CUCUMBER = new VineSeedsItem("cucumber", VineType.CUCUMBER, NormalFoods.CUCUMBER);
    public static final Item BITTER_GOURD = new VineSeedsItem("bitter_gourd", VineType.BITTER_GOURD, NormalFoods.BITTER_GOURD);
    public static final Item CHILI = new NormalItem("chili", getNormalItemProperties());
    public static final Item CHILI_SEEDS = new BlockNamedItem(BlockRegistry.CHILI_PLANT, getNormalItemProperties()).setRegistryName("chili_seeds");
    public static final Item CHINESE_CABBAGE = new NormalItem("chinese_cabbage", getNormalItemProperties());
    public static final Item CHINESE_CABBAGE_SEEDS = new BlockNamedItem(BlockRegistry.CHINESE_CABBAGE_PLANT, getNormalItemProperties()).setRegistryName("chinese_cabbage_seeds");
    // DRINK 饮品
    public static final Item CLAY_CUP = new NormalItem("clay_cup", getDrinkItemProperties());
    public static final Item CLAY_TEAPOT = new NormalItem("clay_teapot", getDrinkItemProperties());
    public static final Item PORCELAIN_CUP = new ItemFluidContainer(getDrinkItemProperties(), 250)
    {
        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt)
        {
            return super.initCapabilities(new ItemStack(ItemRegistry.PORCELAIN_CUP_DRINK), nbt);
        }
    }.setRegistryName("porcelain_cup");
    public static final Item BOTTLE = new ItemFluidContainer(getDrinkItemProperties(), 500)
    {
        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt)
        {
            return super.initCapabilities(new ItemStack(ItemRegistry.BOTTLE_DRINK), nbt);
        }
    }.setRegistryName("bottle");
    public static final Item PORCELAIN_TEAPOT = new TeapotItem(BlockRegistry.TEAPOT, 1000, false);
    public static final Item IRON_KETTLE = new IronKettleItem(BlockRegistry.IRON_KETTLE, 2000);
    public static final Item PORCELAIN_CUP_DRINK = new CupDrinkItem(250, PORCELAIN_CUP, "porcelain_cup_drink");
    public static final Item BOTTLE_DRINK = new CupDrinkItem(500, BOTTLE, "bottle_drink");

    // FOOD 食物
    public static final Item DRIED_BEETROOT = new FoodItem("dried_beetroot", NormalFoods.DRIED_BEETROOT);
    public static final Item DRIED_CARROT = new FoodItem("dried_carrot", NormalFoods.DRIED_CARROT);
    public static final Item BEEF_JERKY = new FoodItem("beef_jerky", NormalFoods.BEEF_JERKY);
    public static final Item PORK_JERKY = new FoodItem("pork_jerky", NormalFoods.PORK_JERKY);
    public static final Item CHICKEN_JERKY = new FoodItem("chicken_jerky", NormalFoods.CHICKEN_JERKY);
    public static final Item RABBIT_JERKY = new FoodItem("rabbit_jerky", NormalFoods.RABBIT_JERKY);
    public static final Item MUTTON_JERKY = new FoodItem("mutton_jerky", NormalFoods.MUTTON_JERKY);

    public static final Item RAISINS = new FoodItem("raisins", NormalFoods.RAISINS);
    public static final Item RICE_BALL = new FoodItem("rice_ball", NormalFoods.RICE_BALL);
    public static final Item RICE_BALL_WITH_KELP = new FoodItem("rice_ball_with_kelp", NormalFoods.RICE_BALL_WITH_KELP);

    public static final Item NETHER_WART_RICE_BOWL = new FoodItem("nether_wart_rice_bowl", NormalFoods.NETHER_WART_RICE_BOWL);
    public static final Item SPICY_BEEF_RICE_BOWL = new FoodItem("spicy_beef_rice_bowl", NormalFoods.SPICY_BEEF_RICE_BOWL);
    public static final Item BEEF_RICE_BOWL = new FoodItem("beef_rice_bowl", NormalFoods.BEEF_RICE_BOWL);
    public static final Item RISE_BOWL = new FoodItem("rise_bowl", NormalFoods.RISE_BOWL);
    public static final Item PICKLED_CABBAGE_WITH_FISH = new FoodItem("pickled_cabbage_with_fish", NormalFoods.PICKLED_CABBAGE_WITH_FISH);
    public static final Item STEAMED_CHINESE_CABBAGE = new FoodItem("steamed_chinese_cabbage", NormalFoods.STEAMED_CHINESE_CABBAGE);
    public static final Item HONEY_BITTER_GOURD = new FoodItem("honey_bitter_gourd", NormalFoods.HONEY_BITTER_GOURD);
    public static final Item SHREDDED_CUCUMBER_SALAD = new FoodItem("shredded_cucumber_salad", NormalFoods.SHREDDED_CUCUMBER_SALAD);
    public static final Item PORK_BAOZI = new FoodItem("pork_baozi", NormalFoods.PORK_BAOZI);
    public static final Item BEEF_BURGER = new FoodItem("beef_burger", NormalFoods.BEEF_BURGER);
    public static final Item CHICKEN_BURGER = new FoodItem("chicken_burger", NormalFoods.CHICKEN_BURGER);

    // MISC 杂项
    public static final Item BAMBOO_PLANK = new NormalItem("bamboo_plank", getNormalItemProperties());
    public static final Item ASH = new FertilizerItem("ash");
    public static final Item TEA_RESIDUES = new NormalItem("tea_residues", getNormalItemProperties());
    public static final Item WET_STRAW = new NormalItem("wet_straw", getNormalItemProperties());
    public static final Item DRY_STRAW = new NormalItem("dry_straw", getNormalItemProperties());
    public static final Item CRUSHED_STRAW = new NormalItem("crushed_straw", getNormalItemProperties());

    public static final Item BAMBOO_CHARCOAL = new NormalBurntItem("bamboo_charcoal", 800);
    public static final Item HONEYCOMB_BRIQUETTE = new NormalBurntItem("honeycomb_briquette", 6000);

    public static final Item STONE_MILL_TOP = new NormalItem("stone_mill_top", new Item.Properties());
    public static final Item STONE_ROLLER_TOP = new NormalItem("stone_roller_top", new Item.Properties());
    public static final Item STONE_ROLLER_WOODEN_FRAME = new NormalItem("stone_roller_wooden_frame", new Item.Properties());
    public static final Item SAUCEPAN_LID = new NormalItem("saucepan_lid", new Item.Properties());

    public static final Item PICKING_TEA_RECORD = new RecordItem("picking_tea", () -> RECORD_PICKING_TEA, 1);
    public static final Item SPRING_FESTIVAL_OVERTURE_RECORD = new RecordItem("spring_festival_overture", () -> RECORD_SPRING_FESTIVAL_OVERTURE, 2);
    public static final Item FLOWERS_AND_MOON_RECORD = new RecordItem("flowers_moon", () -> RECORD_FLOWERS_AND_MOON, 3);
    public static final Item MOVING_UP_RECORD = new RecordItem("moving_up", () -> RECORD_MOVING_UP, 4);
    public static final Item JOYFUL_RECORD = new RecordItem("joyful", () -> RECORD_JOYFUL, 5);
    public static final Item DANCING_GOLDEN_SNAKE_RECORD = new RecordItem("dancing_golden_snake", () -> RECORD_DANCING_GOLDEN_SNAKE, 6);
    public static final Item GREEN_WILLOW_RECORD = new RecordItem("green_willow", () -> RECORD_GREEN_WILLOW, 7);
    public static final Item PURPLE_BAMBOO_MELODY_RECORD = new RecordItem("purple_bamboo_melody", () -> RECORD_PURPLE_BAMBOO_MELODY, 8);
    public static final Item WELCOME_MARCH_RECORD = new RecordItem("welcome_march", () -> RECORD_WELCOME_MARCH, 9);
    public static Item.Properties getNormalItemProperties()
    {
        return new Item.Properties().group(TeaStory.GROUP_CORE);
    }

    public static Item.Properties getDrinkItemProperties()
    {
        return new Item.Properties().group(TeaStory.GROUP_DRINK);
    }

    public static Item.Properties getTeaLeavesItemProperties()
    {
        return new Item.Properties().group(TeaStory.GROUP_CORE).containerItem(ItemRegistry.TEA_RESIDUES);
    }
}
