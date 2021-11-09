package cloud.lemonslice.teastory.common.block;

import cloud.lemonslice.teastory.common.block.craft.*;
import cloud.lemonslice.teastory.common.block.crops.*;
import cloud.lemonslice.teastory.common.block.decorations.*;
import cloud.lemonslice.teastory.common.block.drink.DrinkMakerBlock;
import cloud.lemonslice.teastory.common.block.drink.IronKettleBlock;
import cloud.lemonslice.teastory.common.block.drink.TeapotBlock;
import cloud.lemonslice.teastory.common.block.drink.WoodenTrayBlock;
import cloud.lemonslice.teastory.registry.RegistryModule;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

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
    public static final Block STONE_CATAPULT_BOARD = new CatapultBoardBlock(0.25F, "stone_catapult_board", Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F).notSolid());
    public static final Block STONE_CATAPULT_BOARD_WITH_TRAY = new CatapultBoardBlockWithTray("stone_catapult_board_with_tray", Block.Properties.create(Material.ROCK).sound(SoundType.BAMBOO).notSolid());
    public static final Block BAMBOO_CATAPULT_BOARD = new CatapultBoardBlock(0.5F, "bamboo_catapult_board", Block.Properties.create(Material.BAMBOO).sound(SoundType.BAMBOO).notSolid());
    public static final Block IRON_CATAPULT_BOARD = new CatapultBoardBlock(0.75F, "iron_catapult_board", Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.5F).notSolid());
    public static final Block FILTER_SCREEN = new FilterScreenBlock();
    public static final Block WOODEN_TRAY = new WoodenTrayBlock();
    public static final Block STONE_MILL = new StoneMillBlock();
    public static final Block STONE_ROLLER = new StoneRollerBlock();
    public static final Block SAUCEPAN = new SaucepanBlock();
    public static final Block WOODEN_BARREL = new WoodenBarrelBlock();
    public static final Block STONE_CAMPFIRE = new StoneCampfireBlock();//TODO 竹筒饭

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
    public static final TrellisWithVineBlock OAK_TRELLIS_BITTER_GOURD = new TrellisWithVineBlock("oak_trellis", VineType.BITTER_GOURD, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock BIRCH_TRELLIS_BITTER_GOURD = new TrellisWithVineBlock("birch_trellis", VineType.BITTER_GOURD, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock JUNGLE_TRELLIS_BITTER_GOURD = new TrellisWithVineBlock("jungle_trellis", VineType.BITTER_GOURD, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock SPRUCE_TRELLIS_BITTER_GOURD = new TrellisWithVineBlock("spruce_trellis", VineType.BITTER_GOURD, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock DARK_OAK_TRELLIS_BITTER_GOURD = new TrellisWithVineBlock("dark_oak_trellis", VineType.BITTER_GOURD, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());
    public static final TrellisWithVineBlock ACACIA_TRELLIS_BITTER_GOURD = new TrellisWithVineBlock("acacia_trellis", VineType.BITTER_GOURD, Block.Properties.create(Material.WOOD).sound(SoundType.PLANT).hardnessAndResistance(0.6F).notSolid());

    public static final Block COBBLESTONE_AQUEDUCT = new AqueductBlock("cobblestone_aqueduct", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F).sound(SoundType.STONE).notSolid());
    public static final Block MOSSY_COBBLESTONE_AQUEDUCT = new AqueductConnectorBlock("mossy_cobblestone_aqueduct", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F).sound(SoundType.STONE).notSolid());

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
    public static final Block BITTER_GOURD = new StemFruitBlock("bitter_gourd_plant", Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP), VineType.BITTER_GOURD);
    public static final Block WATERMELON_VINE = new MelonVineBlock("watermelon_vine", Blocks.MELON);
    public static final Block CHILI_PLANT = new ChiliBlock("chili_plant");
    public static final Block CHINESE_CABBAGE_PLANT = new ChineseCabbageBlock("chinese_cabbage_plant");

    // FLOWERS 花朵
    public static final Block CHRYSANTHEMUM = new HybridizableFlowerBlock("chrysanthemum");
    public static final Block HYACINTH = new HybridizableFlowerBlock("hyacinth");
    public static final Block ZINNIA = new HybridizableFlowerBlock("zinnia");

    // MISC 杂项
    public static final Block GRASS_BLOCK_WITH_HOLE = new GrassBlock(Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).setRegistryName("grass_block_with_hole");
    public static final Block WOODEN_BOWL = new BowlBlock("wooden_bowl", Block.Properties.create(Material.WOOD).notSolid().hardnessAndResistance(0.4f).harvestTool(ToolType.AXE));
}
