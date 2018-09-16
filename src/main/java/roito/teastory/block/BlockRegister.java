package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.item.ItemBlockEmptyKettle;
import roito.teastory.item.ItemBlockFullKettle;

@Mod.EventBusSubscriber(modid = TeaStory.MODID)
public final class BlockRegister
{
    public static Block teapan = new Teapan();
    public static Block tea_drying_pan = new TeaDryingPan();
    public static Block lit_tea_drying_pan = new LitTeaDryingPan();
    public static Block lit_cooking_pan = new LitCookingPan();
    public static Block tea_stove = new TeaStove(0.0F, "tea_stove", false).setCreativeTab(CreativeTabsRegister.tabTeaStory);
    public static Block lit_tea_stove = new TeaStove(0.875F, "lit_tea_stove", true);
    public static Block barrel = new Barrel();
    public static Block tea_table = new TeaTable();

    public static Kettle clay_kettle = new ClayKettle("clay_kettle");
    public static Kettle zisha_clay_kettle = new ClayKettle("zisha_clay_kettle");
    public static Kettle empty_porcelain_kettle = new EmptyKettle("porcelain_kettle", 4);
    public static Kettle green_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "green_tea", "empty_porcelain_kettle");
    public static Kettle matcha_drink_porcelain_kettle = new FullKettle("porcelain_kettle", "matcha_drink", "empty_porcelain_kettle");
    public static Kettle black_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "black_tea", "empty_porcelain_kettle");
    public static Kettle milk_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "milk_tea", "empty_porcelain_kettle");
    public static Kettle lemon_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "lemon_tea", "empty_porcelain_kettle");
    public static Kettle yellow_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "yellow_tea", "empty_porcelain_kettle");
    public static Kettle white_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "white_tea", "empty_porcelain_kettle");
    public static Kettle oolong_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "oolong_tea", "empty_porcelain_kettle");
    public static Kettle puer_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "puer_tea", "empty_porcelain_kettle");

    public static Kettle empty_zisha_kettle = new EmptyKettle("zisha_kettle", 8);
    public static Kettle green_tea_zisha_kettle = new FullKettle("zisha_kettle", "green_tea", "empty_zisha_kettle");
    public static Kettle matcha_drink_zisha_kettle = new FullKettle("zisha_kettle", "matcha_drink", "empty_zisha_kettle");
    public static Kettle black_tea_zisha_kettle = new FullKettle("zisha_kettle", "black_tea", "empty_zisha_kettle");
    public static Kettle milk_tea_zisha_kettle = new FullKettle("zisha_kettle", "milk_tea", "empty_zisha_kettle");
    public static Kettle lemon_tea_zisha_kettle = new FullKettle("zisha_kettle", "lemon_tea", "empty_zisha_kettle");
    public static Kettle yellow_tea_zisha_kettle = new FullKettle("zisha_kettle", "yellow_tea", "empty_zisha_kettle");
    public static Kettle white_tea_zisha_kettle = new FullKettle("zisha_kettle", "white_tea", "empty_zisha_kettle");
    public static Kettle oolong_tea_zisha_kettle = new FullKettle("zisha_kettle", "oolong_tea", "empty_zisha_kettle");
    public static Kettle puer_tea_zisha_kettle = new FullKettle("zisha_kettle", "puer_tea", "empty_zisha_kettle");

    public static Block half_dried_leaf_block = new HalfDriedLeafBlock();
    public static Block teaplant = new Teaplant();
    public static BlockCrops xian_rice_seedling = new XianRiceSeedling();
    public static Block xian_rice_plant = new XianRicePlant();
    public static Block field = new Field();
    public static Block paddy_field = new PaddyField();

    public static Block straw_blanket = new StrawBlanket();
    public static Block straw_cushion = new StrawCushion();

    public static TeaDrinkEmpty wood_cup = new TeaDrinkEmpty(1.0F, "wood_cup", Material.WOOD, SoundType.WOOD, 0);
    public static TeaDrinkEmpty stone_cup = new TeaDrinkEmpty(1.3F, "stone_cup", Material.ROCK, SoundType.STONE, 2);
    public static TeaDrinkEmpty glass_cup = new TeaDrinkEmpty(0.8F, "glass_cup", Material.GLASS, SoundType.GLASS, 3);
    public static TeaDrinkEmpty porcelain_cup = new TeaDrinkEmpty(1.5F, "porcelain_cup", Material.ROCK, SoundType.STONE, 4);
    public static TeaDrinkEmpty zisha_cup = new TeaDrinkEmpty(1.5F, "zisha_cup", Material.ROCK, SoundType.STONE, 5);

    public static TeaDrinkFull greentea_wood_cup = new TeaDrinkFull(1.0F, "greentea_wood_cup", Material.WOOD, SoundType.WOOD, 1, 0);
    public static TeaDrinkFull matchadrink_wood_cup = new TeaDrinkFull(1.0F, "matchadrink_wood_cup", Material.WOOD, SoundType.WOOD, 2, 0);
    public static TeaDrinkFull blacktea_wood_cup = new TeaDrinkFull(1.0F, "blacktea_wood_cup", Material.WOOD, SoundType.WOOD, 3, 0);
    public static TeaDrinkFull milktea_wood_cup = new TeaDrinkFull(1.0F, "milktea_wood_cup", Material.WOOD, SoundType.WOOD, 4, 0);
    public static TeaDrinkFull lemontea_wood_cup = new TeaDrinkFull(1.0F, "lemontea_wood_cup", Material.WOOD, SoundType.WOOD, 5, 0);
    public static TeaDrinkFull yellowtea_wood_cup = new TeaDrinkFull(1.0F, "yellowtea_wood_cup", Material.WOOD, SoundType.WOOD, 6, 0);
    public static TeaDrinkFull whitetea_wood_cup = new TeaDrinkFull(1.0F, "whitetea_wood_cup", Material.WOOD, SoundType.WOOD, 7, 0);
    public static TeaDrinkFull oolongtea_wood_cup = new TeaDrinkFull(1.0F, "oolongtea_wood_cup", Material.WOOD, SoundType.WOOD, 8, 0);
    public static TeaDrinkFull puertea_wood_cup = new TeaDrinkFull(1.0F, "puertea_wood_cup", Material.WOOD, SoundType.WOOD, 9, 0);

    public static TeaDrinkFull greentea_stone_cup = new TeaDrinkFull(1.3F, "greentea_stone_cup", Material.ROCK, SoundType.STONE, 1, 2);
    public static TeaDrinkFull matchadrink_stone_cup = new TeaDrinkFull(1.3F, "matchadrink_stone_cup", Material.ROCK, SoundType.STONE, 2, 2);
    public static TeaDrinkFull blacktea_stone_cup = new TeaDrinkFull(1.3F, "blacktea_stone_cup", Material.ROCK, SoundType.STONE, 3, 2);
    public static TeaDrinkFull milktea_stone_cup = new TeaDrinkFull(1.3F, "milktea_stone_cup", Material.ROCK, SoundType.STONE, 4, 2);
    public static TeaDrinkFull lemontea_stone_cup = new TeaDrinkFull(1.3F, "lemontea_stone_cup", Material.ROCK, SoundType.STONE, 5, 2);
    public static TeaDrinkFull yellowtea_stone_cup = new TeaDrinkFull(1.3F, "yellowtea_stone_cup", Material.ROCK, SoundType.STONE, 6, 2);
    public static TeaDrinkFull whitetea_stone_cup = new TeaDrinkFull(1.3F, "whitetea_stone_cup", Material.ROCK, SoundType.STONE, 7, 2);
    public static TeaDrinkFull oolongtea_stone_cup = new TeaDrinkFull(1.3F, "oolongtea_stone_cup", Material.ROCK, SoundType.STONE, 8, 2);
    public static TeaDrinkFull puertea_stone_cup = new TeaDrinkFull(1.3F, "puertea_stone_cup", Material.ROCK, SoundType.STONE, 9, 2);

    public static TeaDrinkFull greentea_glass_cup = new TeaDrinkFull(0.8F, "greentea_glass_cup", Material.GLASS, SoundType.GLASS, 1, 3);
    public static TeaDrinkFull matchadrink_glass_cup = new TeaDrinkFull(0.8F, "matchadrink_glass_cup", Material.GLASS, SoundType.GLASS, 2, 3);
    public static TeaDrinkFull blacktea_glass_cup = new TeaDrinkFull(0.8F, "blacktea_glass_cup", Material.GLASS, SoundType.GLASS, 3, 3);
    public static TeaDrinkFull milktea_glass_cup = new TeaDrinkFull(0.8F, "milktea_glass_cup", Material.GLASS, SoundType.GLASS, 4, 3);
    public static TeaDrinkFull lemontea_glass_cup = new TeaDrinkFull(0.8F, "lemontea_glass_cup", Material.GLASS, SoundType.GLASS, 5, 3);
    public static TeaDrinkFull yellowtea_glass_cup = new TeaDrinkFull(0.8F, "yellowtea_glass_cup", Material.GLASS, SoundType.GLASS, 6, 3);
    public static TeaDrinkFull whitetea_glass_cup = new TeaDrinkFull(0.8F, "whitetea_glass_cup", Material.GLASS, SoundType.GLASS, 7, 3);
    public static TeaDrinkFull oolongtea_glass_cup = new TeaDrinkFull(0.8F, "oolongtea_glass_cup", Material.GLASS, SoundType.GLASS, 8, 3);
    public static TeaDrinkFull puertea_glass_cup = new TeaDrinkFull(0.8F, "puertea_glass_cup", Material.GLASS, SoundType.GLASS, 9, 3);

    public static TeaDrinkFull greentea_porcelain_cup = new TeaDrinkFull(1.5F, "greentea_porcelain_cup", Material.ROCK, SoundType.STONE, 1, 4);
    public static TeaDrinkFull matchadrink_porcelain_cup = new TeaDrinkFull(1.5F, "matchadrink_porcelain_cup", Material.ROCK, SoundType.STONE, 2, 4);
    public static TeaDrinkFull blacktea_porcelain_cup = new TeaDrinkFull(1.5F, "blacktea_porcelain_cup", Material.ROCK, SoundType.STONE, 3, 4);
    public static TeaDrinkFull milktea_porcelain_cup = new TeaDrinkFull(1.5F, "milktea_porcelain_cup", Material.ROCK, SoundType.STONE, 4, 4);
    public static TeaDrinkFull lemontea_porcelain_cup = new TeaDrinkFull(1.5F, "lemontea_porcelain_cup", Material.ROCK, SoundType.STONE, 5, 4);
    public static TeaDrinkFull yellowtea_porcelain_cup = new TeaDrinkFull(1.5F, "yellowtea_porcelain_cup", Material.ROCK, SoundType.STONE, 6, 4);
    public static TeaDrinkFull whitetea_porcelain_cup = new TeaDrinkFull(1.5F, "whitetea_porcelain_cup", Material.ROCK, SoundType.STONE, 7, 4);
    public static TeaDrinkFull oolongtea_porcelain_cup = new TeaDrinkFull(1.5F, "oolongtea_porcelain_cup", Material.ROCK, SoundType.STONE, 8, 4);
    public static TeaDrinkFull puertea_porcelain_cup = new TeaDrinkFull(1.5F, "puertea_porcelain_cup", Material.ROCK, SoundType.STONE, 9, 4);

    public static TeaDrinkFull greentea_zisha_cup = new TeaDrinkFull(1.5F, "greentea_zisha_cup", Material.ROCK, SoundType.STONE, 1, 5);
    public static TeaDrinkFull matchadrink_zisha_cup = new TeaDrinkFull(1.5F, "matchadrink_zisha_cup", Material.ROCK, SoundType.STONE, 2, 5);
    public static TeaDrinkFull blacktea_zisha_cup = new TeaDrinkFull(1.5F, "blacktea_zisha_cup", Material.ROCK, SoundType.STONE, 3, 5);
    public static TeaDrinkFull milktea_zisha_cup = new TeaDrinkFull(1.5F, "milktea_zisha_cup", Material.ROCK, SoundType.STONE, 4, 5);
    public static TeaDrinkFull lemontea_zisha_cup = new TeaDrinkFull(1.5F, "lemontea_zisha_cup", Material.ROCK, SoundType.STONE, 5, 5);
    public static TeaDrinkFull yellowtea_zisha_cup = new TeaDrinkFull(1.5F, "yellowtea_zisha_cup", Material.ROCK, SoundType.STONE, 6, 5);
    public static TeaDrinkFull whitetea_zisha_cup = new TeaDrinkFull(1.5F, "whitetea_zisha_cup", Material.ROCK, SoundType.STONE, 7, 5);
    public static TeaDrinkFull oolongtea_zisha_cup = new TeaDrinkFull(1.5F, "oolongtea_zisha_cup", Material.ROCK, SoundType.STONE, 8, 5);
    public static TeaDrinkFull puertea_zisha_cup = new TeaDrinkFull(1.5F, "puertea_zisha_cup", Material.ROCK, SoundType.STONE, 9, 5);

    public BlockRegister(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void loadExtraResourceLocation()
    {
        ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockRegister.lit_tea_drying_pan), new ResourceLocation(TeaStory.MODID, "tea_drying_pan"));
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        registerRender(barrel, "barrel");
        registerRender(teapan, "teapan");
        registerRender(clay_kettle, "clay_kettle");
        registerRender(empty_porcelain_kettle, "empty_porcelain_kettle");
        registerRender(green_tea_porcelain_kettle, "green_tea_porcelain_kettle");
        registerRender(matcha_drink_porcelain_kettle, "matcha_drink_porcelain_kettle");
        registerRender(black_tea_porcelain_kettle, "black_tea_porcelain_kettle");
        registerRender(milk_tea_porcelain_kettle, "milk_tea_porcelain_kettle");
        registerRender(lemon_tea_porcelain_kettle, "lemon_tea_porcelain_kettle");
        registerRender(yellow_tea_porcelain_kettle, "yellow_tea_porcelain_kettle");
        registerRender(white_tea_porcelain_kettle, "white_tea_porcelain_kettle");
        registerRender(oolong_tea_porcelain_kettle, "oolong_tea_porcelain_kettle");
        registerRender(puer_tea_porcelain_kettle, "puer_tea_porcelain_kettle");

        registerRender(zisha_clay_kettle, "zisha_clay_kettle");
        registerRender(empty_zisha_kettle, "empty_zisha_kettle");
        registerRender(green_tea_zisha_kettle, "green_tea_zisha_kettle");
        registerRender(matcha_drink_zisha_kettle, "matcha_drink_zisha_kettle");
        registerRender(black_tea_zisha_kettle, "black_tea_zisha_kettle");
        registerRender(milk_tea_zisha_kettle, "milk_tea_zisha_kettle");
        registerRender(lemon_tea_zisha_kettle, "lemon_tea_zisha_kettle");
        registerRender(yellow_tea_zisha_kettle, "yellow_tea_zisha_kettle");
        registerRender(white_tea_zisha_kettle, "white_tea_zisha_kettle");
        registerRender(oolong_tea_zisha_kettle, "oolong_tea_zisha_kettle");
        registerRender(puer_tea_zisha_kettle, "puer_tea_zisha_kettle");

        registerRender(tea_drying_pan, "tea_drying_pan");
        registerRender(lit_tea_drying_pan, "tea_drying_pan");
        registerRender(tea_stove, "tea_stove");
        registerRender(lit_tea_stove, "lit_tea_stove");
        registerRender(tea_table, "tea_table");
        registerRender(teaplant, "teaplant");

        registerRender(wood_cup, "wood_cup");
        registerRender(stone_cup, "stone_cup");
        registerRender(glass_cup, "glass_cup");
        registerRender(porcelain_cup, "porcelain_cup");
        registerRender(zisha_cup, "zisha_cup");

        registerRender(greentea_wood_cup, "greentea_wood_cup");
        registerRender(matchadrink_wood_cup, "matchadrink_wood_cup");
        registerRender(blacktea_wood_cup, "blacktea_wood_cup");
        registerRender(milktea_wood_cup, "milktea_wood_cup");
        registerRender(lemontea_wood_cup, "lemontea_wood_cup");
        registerRender(yellowtea_wood_cup, "yellowtea_wood_cup");
        registerRender(whitetea_wood_cup, "whitetea_wood_cup");
        registerRender(oolongtea_wood_cup, "oolongtea_wood_cup");
        registerRender(puertea_wood_cup, "puertea_wood_cup");

        registerRender(greentea_stone_cup, "greentea_stone_cup");
        registerRender(matchadrink_stone_cup, "matchadrink_stone_cup");
        registerRender(blacktea_stone_cup, "blacktea_stone_cup");
        registerRender(milktea_stone_cup, "milktea_stone_cup");
        registerRender(lemontea_stone_cup, "lemontea_stone_cup");
        registerRender(yellowtea_stone_cup, "yellowtea_stone_cup");
        registerRender(whitetea_stone_cup, "whitetea_stone_cup");
        registerRender(oolongtea_stone_cup, "oolongtea_stone_cup");
        registerRender(puertea_stone_cup, "puertea_stone_cup");

        registerRender(greentea_glass_cup, "greentea_glass_cup");
        registerRender(matchadrink_glass_cup, "matchadrink_glass_cup");
        registerRender(blacktea_glass_cup, "blacktea_glass_cup");
        registerRender(milktea_glass_cup, "milktea_glass_cup");
        registerRender(lemontea_glass_cup, "lemontea_glass_cup");
        registerRender(yellowtea_glass_cup, "yellowtea_glass_cup");
        registerRender(whitetea_glass_cup, "whitetea_glass_cup");
        registerRender(oolongtea_glass_cup, "oolongtea_glass_cup");
        registerRender(puertea_glass_cup, "puertea_glass_cup");

        registerRender(greentea_porcelain_cup, "greentea_porcelain_cup");
        registerRender(matchadrink_porcelain_cup, "matchadrink_porcelain_cup");
        registerRender(blacktea_porcelain_cup, "blacktea_porcelain_cup");
        registerRender(milktea_porcelain_cup, "milktea_porcelain_cup");
        registerRender(lemontea_porcelain_cup, "lemontea_porcelain_cup");
        registerRender(yellowtea_porcelain_cup, "yellowtea_porcelain_cup");
        registerRender(whitetea_porcelain_cup, "whitetea_porcelain_cup");
        registerRender(oolongtea_porcelain_cup, "oolongtea_porcelain_cup");
        registerRender(puertea_porcelain_cup, "puertea_porcelain_cup");

        registerRender(greentea_zisha_cup, "greentea_zisha_cup");
        registerRender(matchadrink_zisha_cup, "matchadrink_zisha_cup");
        registerRender(blacktea_zisha_cup, "blacktea_zisha_cup");
        registerRender(milktea_zisha_cup, "milktea_zisha_cup");
        registerRender(lemontea_zisha_cup, "lemontea_zisha_cup");
        registerRender(yellowtea_zisha_cup, "yellowtea_zisha_cup");
        registerRender(whitetea_zisha_cup, "whitetea_zisha_cup");
        registerRender(oolongtea_zisha_cup, "oolongtea_zisha_cup");
        registerRender(puertea_zisha_cup, "puertea_zisha_cup");

        registerRender(xian_rice_seedling, "rice_seedling");
        registerRender(xian_rice_plant, "rice_plant");
        registerRender(lit_cooking_pan, "lit_cooking_pan");
        registerRender(straw_blanket, "straw_blanket");
        registerRender(straw_cushion, "straw_cushion");
        registerRender(half_dried_leaf_block, "half_dried_leaf_block");
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(lit_tea_drying_pan, tea_drying_pan, lit_cooking_pan, tea_stove, lit_tea_stove, tea_table, teapan, barrel, half_dried_leaf_block,
                empty_porcelain_kettle, green_tea_porcelain_kettle, matcha_drink_porcelain_kettle, black_tea_porcelain_kettle, milk_tea_porcelain_kettle, lemon_tea_porcelain_kettle, yellow_tea_porcelain_kettle,
                white_tea_porcelain_kettle, oolong_tea_porcelain_kettle, puer_tea_porcelain_kettle, empty_zisha_kettle, green_tea_zisha_kettle, matcha_drink_zisha_kettle, black_tea_zisha_kettle, milk_tea_zisha_kettle,
                lemon_tea_zisha_kettle, yellow_tea_zisha_kettle, white_tea_zisha_kettle, oolong_tea_zisha_kettle, puer_tea_zisha_kettle, clay_kettle, zisha_clay_kettle, teaplant, wood_cup,
                stone_cup, glass_cup, porcelain_cup, zisha_cup, greentea_wood_cup, matchadrink_wood_cup, blacktea_wood_cup, milktea_wood_cup, lemontea_wood_cup, yellowtea_wood_cup, whitetea_wood_cup,
                oolongtea_wood_cup, puertea_wood_cup, greentea_stone_cup, matchadrink_stone_cup, blacktea_stone_cup, milktea_stone_cup, lemontea_stone_cup, yellowtea_stone_cup, whitetea_stone_cup,
                oolongtea_stone_cup, puertea_stone_cup, greentea_glass_cup, matchadrink_glass_cup, blacktea_glass_cup, milktea_glass_cup, lemontea_glass_cup, yellowtea_glass_cup, whitetea_glass_cup,
                oolongtea_glass_cup, puertea_glass_cup, greentea_porcelain_cup, matchadrink_porcelain_cup, blacktea_porcelain_cup, milktea_porcelain_cup, lemontea_porcelain_cup, yellowtea_porcelain_cup,
                whitetea_porcelain_cup, oolongtea_porcelain_cup, puertea_porcelain_cup, greentea_zisha_cup, matchadrink_zisha_cup, blacktea_zisha_cup, milktea_zisha_cup, lemontea_zisha_cup, yellowtea_zisha_cup,
                whitetea_zisha_cup, oolongtea_zisha_cup, puertea_zisha_cup, xian_rice_seedling, xian_rice_plant, field, paddy_field, straw_blanket, straw_cushion);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                new ItemBlockEmptyKettle(empty_porcelain_kettle),
                new ItemBlockFullKettle(green_tea_porcelain_kettle, 1, 4),
                new ItemBlockFullKettle(matcha_drink_porcelain_kettle, 2, 4),
                new ItemBlockFullKettle(black_tea_porcelain_kettle, 3, 4),
                new ItemBlockFullKettle(milk_tea_porcelain_kettle, 4, 4),
                new ItemBlockFullKettle(lemon_tea_porcelain_kettle, 5, 4),
                new ItemBlockFullKettle(yellow_tea_porcelain_kettle, 6, 4),
                new ItemBlockFullKettle(white_tea_porcelain_kettle, 7, 4),
                new ItemBlockFullKettle(oolong_tea_porcelain_kettle, 8, 4),
                new ItemBlockFullKettle(puer_tea_porcelain_kettle, 9, 4),
                new ItemBlockEmptyKettle(empty_zisha_kettle),
                new ItemBlockFullKettle(green_tea_zisha_kettle, 1, 8),
                new ItemBlockFullKettle(matcha_drink_zisha_kettle, 2, 8),
                new ItemBlockFullKettle(black_tea_zisha_kettle, 3, 8),
                new ItemBlockFullKettle(milk_tea_zisha_kettle, 4, 8),
                new ItemBlockFullKettle(lemon_tea_zisha_kettle, 5, 8),
                new ItemBlockFullKettle(yellow_tea_zisha_kettle, 6, 8),
                new ItemBlockFullKettle(white_tea_zisha_kettle, 7, 8),
                new ItemBlockFullKettle(oolong_tea_zisha_kettle, 8, 8),
                new ItemBlockFullKettle(puer_tea_zisha_kettle, 9, 8),
                getRegItemBlock(half_dried_leaf_block),
                getRegItemBlock(lit_tea_drying_pan),
                getRegItemBlock(tea_drying_pan),
                getRegItemBlock(lit_cooking_pan),
                getRegItemBlock(tea_stove),
                getRegItemBlock(lit_tea_stove),
                getRegItemBlock(tea_table),
                getRegItemBlock(teapan),
                getRegItemBlock(barrel),

                getRegItemBlock(clay_kettle),
                getRegItemBlock(zisha_clay_kettle),
                getRegItemBlock(teaplant),

                getRegItemBlock(wood_cup),
                getRegItemBlock(stone_cup),
                getRegItemBlock(glass_cup),
                getRegItemBlock(porcelain_cup),
                getRegItemBlock(zisha_cup),

                getRegItemBlock(greentea_wood_cup),
                getRegItemBlock(matchadrink_wood_cup),
                getRegItemBlock(blacktea_wood_cup),
                getRegItemBlock(milktea_wood_cup),
                getRegItemBlock(lemontea_wood_cup),
                getRegItemBlock(yellowtea_wood_cup),
                getRegItemBlock(whitetea_wood_cup),
                getRegItemBlock(oolongtea_wood_cup),
                getRegItemBlock(puertea_wood_cup),

                getRegItemBlock(greentea_stone_cup),
                getRegItemBlock(matchadrink_stone_cup),
                getRegItemBlock(blacktea_stone_cup),
                getRegItemBlock(milktea_stone_cup),
                getRegItemBlock(lemontea_stone_cup),
                getRegItemBlock(yellowtea_stone_cup),
                getRegItemBlock(whitetea_stone_cup),
                getRegItemBlock(oolongtea_stone_cup),
                getRegItemBlock(puertea_stone_cup),

                getRegItemBlock(greentea_glass_cup),
                getRegItemBlock(matchadrink_glass_cup),
                getRegItemBlock(blacktea_glass_cup),
                getRegItemBlock(milktea_glass_cup),
                getRegItemBlock(lemontea_glass_cup),
                getRegItemBlock(yellowtea_glass_cup),
                getRegItemBlock(whitetea_glass_cup),
                getRegItemBlock(oolongtea_glass_cup),
                getRegItemBlock(puertea_glass_cup),

                getRegItemBlock(greentea_porcelain_cup),
                getRegItemBlock(matchadrink_porcelain_cup),
                getRegItemBlock(blacktea_porcelain_cup),
                getRegItemBlock(milktea_porcelain_cup),
                getRegItemBlock(lemontea_porcelain_cup),
                getRegItemBlock(yellowtea_porcelain_cup),
                getRegItemBlock(whitetea_porcelain_cup),
                getRegItemBlock(oolongtea_porcelain_cup),
                getRegItemBlock(puertea_porcelain_cup),

                getRegItemBlock(greentea_zisha_cup),
                getRegItemBlock(matchadrink_zisha_cup),
                getRegItemBlock(blacktea_zisha_cup),
                getRegItemBlock(milktea_zisha_cup),
                getRegItemBlock(lemontea_zisha_cup),
                getRegItemBlock(yellowtea_zisha_cup),
                getRegItemBlock(whitetea_zisha_cup),
                getRegItemBlock(oolongtea_zisha_cup),
                getRegItemBlock(puertea_zisha_cup),

                getRegItemBlock(xian_rice_seedling),
                getRegItemBlock(xian_rice_plant),
                getRegItemBlock(straw_blanket),
                new ItemBlock(straw_cushion)
                {
                    @Override
                    public int getItemBurnTime(ItemStack itemStack)
                    {
                        return 800;
                    }
                }.setRegistryName(straw_cushion.getRegistryName())
        );
    }

    private static Item getRegItemBlock(Block block)
    {
        return new ItemBlock(block).setRegistryName(block.getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block, String name)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(TeaStory.MODID + ":" + name, "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block, int meta, String name)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(TeaStory.MODID + ":" + name, "inventory"));
    }
}