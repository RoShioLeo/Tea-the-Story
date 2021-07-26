package cloud.lemonslice.teastory.common.block;

import cloud.lemonslice.silveroak.common.item.NormalBlockItem;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.block.craft.*;
import cloud.lemonslice.teastory.common.block.crops.*;
import cloud.lemonslice.teastory.common.block.decorations.*;
import cloud.lemonslice.teastory.common.block.drink.DrinkMakerBlock;
import cloud.lemonslice.teastory.common.block.drink.IronKettleBlock;
import cloud.lemonslice.teastory.common.block.drink.TeapotBlock;
import cloud.lemonslice.teastory.common.block.drink.WoodenTrayBlock;
import cloud.lemonslice.teastory.common.item.DrinkMakerItem;
import cloud.lemonslice.teastory.common.item.HybridizableFlowerBlockItem;
import cloud.lemonslice.teastory.registry.RegistryModule;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;

import java.util.List;

import static net.minecraft.state.properties.BlockStateProperties.LIT;

public final class BlockRegistry extends RegistryModule
{
    public static List<Block> TRELLIS_BLOCKS = Lists.newArrayList();
    // CRAFT 工艺
    public static final Block BAMBOO_TRAY = new BambooTrayBlock();
    public static final Block DRINK_MAKER = new DrinkMakerBlock();
    public static final Block DIRT_STOVE = new StoveBlock(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F).setLightLevel(state -> state.get(LIT) ? 15 : 0), "dirt_stove", 1);
    public static final Block STONE_STOVE = new StoveBlock(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F).setLightLevel(state -> state.get(LIT) ? 15 : 0), "stone_stove", 2);
    public static final Block WOODEN_FRAME = new WoodenFrameBlock();
    public static final Block TEAPOT = new TeapotBlock("porcelain_teapot", Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F));
    public static final Block IRON_KETTLE = new IronKettleBlock("iron_kettle", Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.5F).tickRandomly());
    public static final Block STONE_CATAPULT_BOARD = new CatapultBoardBlock(0.2F, "stone_catapult_board", Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F).notSolid());
    public static final Block STONE_CATAPULT_BOARD_WITH_TRAY = new CatapultBoardBlockWithTray("stone_catapult_board_with_tray", Block.Properties.create(Material.ROCK).sound(SoundType.BAMBOO).notSolid());
    public static final Block BAMBOO_CATAPULT_BOARD = new CatapultBoardBlock(0.4F, "bamboo_catapult_board", Block.Properties.create(Material.BAMBOO).sound(SoundType.BAMBOO).notSolid());
    public static final Block IRON_CATAPULT_BOARD = new CatapultBoardBlock(0.6F, "iron_catapult_board", Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.5F).notSolid());
    public static final Block FILTER_SCREEN = new FilterScreenBlock();
    public static final Block WOODEN_TRAY = new WoodenTrayBlock();

    public static final TrellisBlock OAK_TRELLIS = new TrellisBlock("oak_trellis", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisBlock BIRCH_TRELLIS = new TrellisBlock("birch_trellis", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisBlock JUNGLE_TRELLIS = new TrellisBlock("jungle_trellis", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisBlock SPRUCE_TRELLIS = new TrellisBlock("spruce_trellis", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisBlock DARK_OAK_TRELLIS = new TrellisBlock("dark_oak_trellis", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisBlock ACACIA_TRELLIS = new TrellisBlock("acacia_trellis", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());

    public static final TrellisWithVineBlock OAK_TRELLIS_GRAPE = new TrellisWithVineBlock("oak_trellis", VineType.GRAPE, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock BIRCH_TRELLIS_GRAPE = new TrellisWithVineBlock("birch_trellis", VineType.GRAPE, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock JUNGLE_TRELLIS_GRAPE = new TrellisWithVineBlock("jungle_trellis", VineType.GRAPE, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock SPRUCE_TRELLIS_GRAPE = new TrellisWithVineBlock("spruce_trellis", VineType.GRAPE, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock DARK_OAK_TRELLIS_GRAPE = new TrellisWithVineBlock("dark_oak_trellis", VineType.GRAPE, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock ACACIA_TRELLIS_GRAPE = new TrellisWithVineBlock("acacia_trellis", VineType.GRAPE, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock OAK_TRELLIS_CUCUMBER = new TrellisWithVineBlock("oak_trellis", VineType.CUCUMBER, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock BIRCH_TRELLIS_CUCUMBER = new TrellisWithVineBlock("birch_trellis", VineType.CUCUMBER, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock JUNGLE_TRELLIS_CUCUMBER = new TrellisWithVineBlock("jungle_trellis", VineType.CUCUMBER, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock SPRUCE_TRELLIS_CUCUMBER = new TrellisWithVineBlock("spruce_trellis", VineType.CUCUMBER, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock DARK_OAK_TRELLIS_CUCUMBER = new TrellisWithVineBlock("dark_oak_trellis", VineType.CUCUMBER, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock ACACIA_TRELLIS_CUCUMBER = new TrellisWithVineBlock("acacia_trellis", VineType.CUCUMBER, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());

    public static final Block DIRT_AQUEDUCT = new AqueductBlock("dirt_aqueduct", Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND).notSolid());
    public static final Block DIRT_AQUEDUCT_POOL = new AqueductOutputBlock("dirt_aqueduct_pool", Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND).notSolid());

    public static final BlockItem BAMBOO_TRAY_ITEM = new NormalBlockItem(BAMBOO_TRAY, TeaStory.GROUP_CORE);
    public static final BlockItem DRINK_MAKER_ITEM = new DrinkMakerItem();
    public static final BlockItem DIRT_STOVE_ITEM = new NormalBlockItem(DIRT_STOVE, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_STOVE_ITEM = new NormalBlockItem(STONE_STOVE, TeaStory.GROUP_CORE);
    public static final BlockItem WOODEN_FRAME_ITEM = new NormalBlockItem(WOODEN_FRAME, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_CATAPULT_BOARD_ITEM = new NormalBlockItem(STONE_CATAPULT_BOARD, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_CATAPULT_BOARD_ITEM = new NormalBlockItem(BAMBOO_CATAPULT_BOARD, TeaStory.GROUP_CORE);
    public static final BlockItem IRON_CATAPULT_BOARD_ITEM = new NormalBlockItem(IRON_CATAPULT_BOARD, TeaStory.GROUP_CORE);
    public static final BlockItem FILTER_SCREEN_ITEM = new NormalBlockItem(FILTER_SCREEN, TeaStory.GROUP_CORE);
    public static final BlockItem OAK_TRELLIS_ITEM = new NormalBlockItem(OAK_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem BIRCH_TRELLIS_ITEM = new NormalBlockItem(BIRCH_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem JUNGLE_TRELLIS_ITEM = new NormalBlockItem(JUNGLE_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem SPRUCE_TRELLIS_ITEM = new NormalBlockItem(SPRUCE_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem DARK_OAK_TRELLIS_ITEM = new NormalBlockItem(DARK_OAK_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem ACACIA_TRELLIS_ITEM = new NormalBlockItem(ACACIA_TRELLIS, TeaStory.GROUP_CORE);
    public static final BlockItem DIRT_AQUEDUCT_ITEM = new NormalBlockItem(DIRT_AQUEDUCT, TeaStory.GROUP_CORE);
    public static final BlockItem DIRT_AQUEDUCT_POOL_ITEM = new NormalBlockItem(DIRT_AQUEDUCT_POOL, TeaStory.GROUP_CORE);
    public static final BlockItem WOODEN_TRAY_ITEM = new NormalBlockItem(WOODEN_TRAY, TeaStory.GROUP_CORE);

    // DECORATIONS 装饰
    public static final Block WOODEN_TABLE = new TableBlock("wooden_table", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    public static final Block WOODEN_CHAIR = new ChairBlock("wooden_chair", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    public static final Block STONE_TABLE = new TableBlock("stone_table", Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F).notSolid());
    public static final Block STONE_CHAIR = new StoneChairBlock("stone_chair", Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F).notSolid());
    public static final Block BAMBOO_TABLE = new TableBlock("bamboo_table", Block.Properties.create(Material.BAMBOO).sound(SoundType.BAMBOO).hardnessAndResistance(0.5F).notSolid());
    public static final Block BAMBOO_CHAIR = new ChairBlock("bamboo_chair", Block.Properties.create(Material.BAMBOO).sound(SoundType.BAMBOO).hardnessAndResistance(0.5F).notSolid());
    public static final Block BAMBOO_LANTERN = new BambooLanternBlock();
    public static final Block BAMBOO_DOOR = new BambooDoorBlock("bamboo_door");
    public static final Block BAMBOO_GLASS_DOOR = new BambooDoorBlock("bamboo_glass_door");
    public static final Block BAMBOO_LATTICE = new BambooLatticeBlock("bamboo_lattice", Block.Properties.create(Material.BAMBOO).sound(SoundType.BAMBOO).hardnessAndResistance(0.6F).notSolid());
    public static final Block FRESH_BAMBOO_WALL = new BambooWallBlock("fresh_bamboo_wall");
    public static final Block DRIED_BAMBOO_WALL = new BambooWallBlock("dried_bamboo_wall");
//    public static final Block BAMBOO_GLASS_WINDOW = new BambooGlassWindow();

    public static final BlockItem WOODEN_TABLE_ITEM = new NormalBlockItem(WOODEN_TABLE, TeaStory.GROUP_CORE);
    public static final BlockItem WOODEN_CHAIR_ITEM = new NormalBlockItem(WOODEN_CHAIR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_TABLE_ITEM = new NormalBlockItem(BAMBOO_TABLE, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_CHAIR_ITEM = new NormalBlockItem(BAMBOO_CHAIR, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_TABLE_ITEM = new NormalBlockItem(STONE_TABLE, TeaStory.GROUP_CORE);
    public static final BlockItem STONE_CHAIR_ITEM = new NormalBlockItem(STONE_CHAIR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_LANTERN_ITEM = new NormalBlockItem(BAMBOO_LANTERN, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_DOOR_ITEM = new NormalBlockItem(BAMBOO_DOOR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_GLASS_DOOR_ITEM = new NormalBlockItem(BAMBOO_GLASS_DOOR, TeaStory.GROUP_CORE);
    public static final BlockItem BAMBOO_LATTICE_ITEM = new NormalBlockItem(BAMBOO_LATTICE, TeaStory.GROUP_CORE);
    public static final BlockItem FRESH_BAMBOO_WALL_ITEM = new NormalBlockItem(FRESH_BAMBOO_WALL, TeaStory.GROUP_CORE);
    public static final BlockItem DRIED_BAMBOO_WALL_ITEM = new NormalBlockItem(DRIED_BAMBOO_WALL, TeaStory.GROUP_CORE);
//    public static final BlockItem BAMBOO_GLASS_WINDOW_ITEM = new NormalBlockItem(BAMBOO_GLASS_WINDOW);

    // CROPS 作物
    public static final Block PADDY_FIELD = new PaddyFieldBlock();
    public static final Block SCARECROW = new ScarecrowBlock();
    public static final Block DRY_HAYSTACK = new HaystackBlock("dry_haystack");
    public static final Block WET_HAYSTACK = new HaystackBlock("wet_haystack");

    public static final Block TEA_PLANT = new TeaPlantBlock();
    public static final Block WILD_TEA_PLANT = new WildTeaPlantBlock();
    public static final Block WILD_GRAPE = new WildGrapeBlock();
    public static final Block RICE_SEEDLING = new RiceSeedlingBlock("rice_seedling");
    public static final Block RICE_PLANT = new RicePlantBlock("rice_plant");
    public static final Block GRAPE = new StemFruitBlock("grape_plant", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP), VineType.GRAPE);
    public static final Block CUCUMBER = new StemFruitBlock("cucumber_plant", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP), VineType.CUCUMBER);
    public static final Block WATERMELON_VINE = new MelonVineBlock("watermelon_vine", Blocks.MELON);

    public static final BlockItem PADDY_FIELD_ITEM = new NormalBlockItem(PADDY_FIELD, TeaStory.GROUP_CORE);
    public static final BlockItem SCARECROW_ITEM = new NormalBlockItem(SCARECROW, TeaStory.GROUP_CORE);
    public static final BlockItem DRY_HAYSTACK_ITEM = new NormalBlockItem(DRY_HAYSTACK, TeaStory.GROUP_CORE);
    public static final BlockItem WET_HAYSTACK_ITEM = new NormalBlockItem(WET_HAYSTACK, TeaStory.GROUP_CORE);

    public static final BlockItem WILD_TEA_PLANT_ITEM = new NormalBlockItem(WILD_TEA_PLANT, TeaStory.GROUP_CORE);
    public static final BlockItem WILD_GRAPE_ITEM = new NormalBlockItem(WILD_GRAPE, TeaStory.GROUP_CORE);

    // FLOWERS 花朵
    public static final Block CHRYSANTHEMUM = new HybridizableFlowerBlock("chrysanthemum");
    public static final Block HYACINTH = new HybridizableFlowerBlock("hyacinth");
    public static final Block ZINNIA = new HybridizableFlowerBlock("zinnia");

    public static final BlockItem CHRYSANTHEMUM_ITEM = new HybridizableFlowerBlockItem(CHRYSANTHEMUM);
    public static final BlockItem HYACINTH_ITEM = new HybridizableFlowerBlockItem(HYACINTH);
    public static final BlockItem ZINNIA_ITEM = new HybridizableFlowerBlockItem(ZINNIA);

    // MISC 杂项
    public static final Block GRASS_BLOCK_WITH_HOLE = new GrassBlock(Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("grass_block_with_hole");

    public static final BlockItem GRASS_BLOCK_WITH_HOLE_ITEM = new NormalBlockItem(GRASS_BLOCK_WITH_HOLE, TeaStory.GROUP_CORE);
}
