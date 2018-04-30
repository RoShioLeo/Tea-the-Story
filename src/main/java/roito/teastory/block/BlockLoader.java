package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.item.ItemBlockEmptyKettle;
import roito.teastory.item.ItemBlockFullKettle;
import roito.teastory.item.ItemBlockHalfDriedLeaf;

public class BlockLoader
{
	public static Block teapan = new Teapan();
	public static Block tea_drying_pan = new TeaDryingPan();
	public static Block lit_tea_drying_pan = new LitTeaDryingPan();
	public static Block lit_cooking_pan = new LitCookingPan();
	public static Block tea_stove = new TeaStove(0.0F, "tea_stove", false).setCreativeTab(CreativeTabsLoader.tabTeaStory);
	public static Block lit_tea_stove = new TeaStove(0.875F, "lit_tea_stove", true);
	public static Block barrel = new Barrel();
	public static Block tea_table = new TeaTable();
	
	public static Kettle clay_kettle = new ClayKettle("clay_kettle");
	public static Kettle zisha_clay_kettle = new ClayKettle("zisha_clay_kettle");
	public static Kettle empty_porcelain_kettle = new EmptyKettle("porcelain_kettle", 4);
	public static Kettle green_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "green_tea", "empty_porcelain_kettle", true, false);
	public static Kettle matcha_drink_porcelain_kettle = new FullKettle("porcelain_kettle", "matcha_drink", "empty_porcelain_kettle", true, false);
	public static Kettle black_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "black_tea", "empty_porcelain_kettle", true, false);
	public static Kettle milk_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "milk_tea", "empty_porcelain_kettle", true, false);
	public static Kettle lemon_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "lemon_tea", "empty_porcelain_kettle", true, false);
	public static Kettle yellow_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "yellow_tea", "empty_porcelain_kettle", true, false);
	public static Kettle white_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "white_tea", "empty_porcelain_kettle", true, false);
	public static Kettle oolong_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "oolong_tea", "empty_porcelain_kettle", true, false);
	public static Kettle puer_tea_porcelain_kettle = new FullKettle("porcelain_kettle", "puer_tea", "empty_porcelain_kettle", true, false);
	
	public static Kettle empty_zisha_kettle = new EmptyKettle("zisha_kettle", 8);
	public static Kettle green_tea_zisha_kettle = new FullKettle("zisha_kettle", "green_tea", "empty_zisha_kettle", false, false);
	public static Kettle green_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "green_tea", "green_tea_zisha_kettle", true, true);
	public static Kettle matcha_drink_zisha_kettle = new FullKettle("zisha_kettle", "matcha_drink", "empty_zisha_kettle", false, false);
	public static Kettle matcha_drink_zisha_kettle2 = new FullKettle("zisha_kettle", "matcha_drink", "matcha_drink_zisha_kettle", true, true);
	public static Kettle black_tea_zisha_kettle = new FullKettle("zisha_kettle", "black_tea", "empty_zisha_kettle", false, false);
	public static Kettle black_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "black_tea", "black_tea_zisha_kettle", true, true);
	public static Kettle milk_tea_zisha_kettle = new FullKettle("zisha_kettle", "milk_tea", "empty_zisha_kettle", false, false);
	public static Kettle milk_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "milk_tea", "milk_tea_zisha_kettle", true, true);
	public static Kettle lemon_tea_zisha_kettle = new FullKettle("zisha_kettle", "lemon_tea", "empty_zisha_kettle", false, false);
	public static Kettle lemon_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "lemon_tea", "lemon_tea_zisha_kettle", true, true);
	public static Kettle yellow_tea_zisha_kettle = new FullKettle("zisha_kettle", "yellow_tea", "empty_zisha_kettle", false, false);
	public static Kettle yellow_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "yellow_tea", "yellow_tea_zisha_kettle", true, true);
	public static Kettle white_tea_zisha_kettle = new FullKettle("zisha_kettle", "white_tea", "empty_zisha_kettle", false, false);
	public static Kettle white_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "white_tea", "white_tea_zisha_kettle", true, true);
	public static Kettle oolong_tea_zisha_kettle = new FullKettle("zisha_kettle", "oolong_tea", "empty_zisha_kettle", false, false);
	public static Kettle oolong_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "oolong_tea", "oolong_tea_zisha_kettle", true, true);
	public static Kettle puer_tea_zisha_kettle = new FullKettle("zisha_kettle", "puer_tea", "empty_zisha_kettle", false, false);
	public static Kettle puer_tea_zisha_kettle2 = new FullKettle("zisha_kettle", "puer_tea", "puer_tea_zisha_kettle", true, true);
	
	public static Block half_dried_leaf_block = new HalfDriedLeafBlock();
	public static BlockCrops teaplant = new Teaplant();
	public static BlockCrops xian_rice_seedling = new XianRiceSeedling();
	public static BlockCrops xian_rice_plant = new XianRicePlant();
	public static Block field = new Field();
	
	public static Block straw_blanket = new StrawBlanket();
	public static Block straw_cushion =new StrawCushion();

	public static TeaDrinkEmpty wood_cup = new TeaDrinkEmpty(1.0F, "wood_cup", Material.WOOD, SoundType.WOOD, 0);
	public static TeaDrinkEmpty stone_cup = new TeaDrinkEmpty(1.3F, "stone_cup", Material.ROCK, SoundType.STONE, 1);
	public static TeaDrinkEmpty glass_cup = new TeaDrinkEmpty(0.8F, "glass_cup", Material.GLASS, SoundType.GLASS, 2);
	public static TeaDrinkEmpty porcelain_cup = new TeaDrinkEmpty(1.5F, "porcelain_cup", Material.ROCK, SoundType.STONE, 3);
	public static TeaDrinkEmpty zisha_cup = new TeaDrinkEmpty(1.5F, "zisha_cup", Material.ROCK, SoundType.STONE, 4);
	
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

	public BlockLoader(FMLPreInitializationEvent event)
	{
		register(lit_tea_drying_pan, "lit_tea_drying_pan");
		register(tea_drying_pan, "tea_drying_pan");
		register(lit_cooking_pan, "lit_cooking_pan");
		register(tea_stove, "tea_stove");
		register(lit_tea_stove, "lit_tea_stove");
		register(tea_table, "tea_table");
		register(teapan,  "teapan");
		register(barrel, "barrel");
		register(half_dried_leaf_block, new ItemBlockHalfDriedLeaf(half_dried_leaf_block), "half_dried_leaf_block");
		
		register(empty_porcelain_kettle, new ItemBlockEmptyKettle(empty_porcelain_kettle), "empty_porcelain_kettle");
		register(green_tea_porcelain_kettle, new ItemBlockFullKettle(green_tea_porcelain_kettle, 1), "green_tea_porcelain_kettle");
		register(matcha_drink_porcelain_kettle, new ItemBlockFullKettle(matcha_drink_porcelain_kettle, 2), "matcha_drink_porcelain_kettle");
		register(black_tea_porcelain_kettle, new ItemBlockFullKettle(black_tea_porcelain_kettle, 3), "black_tea_porcelain_kettle");
		register(milk_tea_porcelain_kettle, new ItemBlockFullKettle(milk_tea_porcelain_kettle, 4), "milk_tea_porcelain_kettle");
		register(lemon_tea_porcelain_kettle, new ItemBlockFullKettle(lemon_tea_porcelain_kettle, 5), "lemon_tea_porcelain_kettle");
		register(yellow_tea_porcelain_kettle, new ItemBlockFullKettle(yellow_tea_porcelain_kettle, 6), "yellow_tea_porcelain_kettle");
		register(white_tea_porcelain_kettle, new ItemBlockFullKettle(white_tea_porcelain_kettle, 7), "white_tea_porcelain_kettle");
		register(oolong_tea_porcelain_kettle, new ItemBlockFullKettle(oolong_tea_porcelain_kettle, 8), "oolong_tea_porcelain_kettle");
		register(puer_tea_porcelain_kettle, new ItemBlockFullKettle(puer_tea_porcelain_kettle, 9), "puer_tea_porcelain_kettle");
		
		register(empty_zisha_kettle, new ItemBlockEmptyKettle(empty_zisha_kettle), "empty_zisha_kettle");
		register(green_tea_zisha_kettle, new ItemBlockFullKettle(green_tea_zisha_kettle, 1), "green_tea_zisha_kettle");
		register(matcha_drink_zisha_kettle, new ItemBlockFullKettle(matcha_drink_zisha_kettle, 2), "matcha_drink_zisha_kettle");
		register(black_tea_zisha_kettle, new ItemBlockFullKettle(black_tea_zisha_kettle, 3), "black_tea_zisha_kettle");
		register(milk_tea_zisha_kettle, new ItemBlockFullKettle(milk_tea_zisha_kettle, 4), "milk_tea_zisha_kettle");
		register(lemon_tea_zisha_kettle, new ItemBlockFullKettle(lemon_tea_zisha_kettle, 5), "lemon_tea_zisha_kettle");
		register(yellow_tea_zisha_kettle, new ItemBlockFullKettle(yellow_tea_zisha_kettle, 6), "yellow_tea_zisha_kettle");
		register(white_tea_zisha_kettle, new ItemBlockFullKettle(white_tea_zisha_kettle, 7), "white_tea_zisha_kettle");
		register(oolong_tea_zisha_kettle, new ItemBlockFullKettle(oolong_tea_zisha_kettle, 8), "oolong_tea_zisha_kettle");
		register(puer_tea_zisha_kettle, new ItemBlockFullKettle(puer_tea_zisha_kettle, 9), "puer_tea_zisha_kettle");
		
		register(green_tea_zisha_kettle2, new ItemBlockFullKettle(green_tea_zisha_kettle2, 1), "green_tea_zisha_kettle2");
		register(matcha_drink_zisha_kettle2, new ItemBlockFullKettle(matcha_drink_zisha_kettle2, 2), "matcha_drink_zisha_kettle2");
		register(black_tea_zisha_kettle2, new ItemBlockFullKettle(black_tea_zisha_kettle2, 3), "black_tea_zisha_kettle2");
		register(milk_tea_zisha_kettle2, new ItemBlockFullKettle(milk_tea_zisha_kettle2, 4), "milk_tea_zisha_kettle2");
		register(lemon_tea_zisha_kettle2, new ItemBlockFullKettle(lemon_tea_zisha_kettle2, 5), "lemon_tea_zisha_kettle2");
		register(yellow_tea_zisha_kettle2, new ItemBlockFullKettle(yellow_tea_zisha_kettle2, 6), "yellow_tea_zisha_kettle2");
		register(white_tea_zisha_kettle2, new ItemBlockFullKettle(white_tea_zisha_kettle2, 7), "white_tea_zisha_kettle2");
		register(oolong_tea_zisha_kettle2, new ItemBlockFullKettle(oolong_tea_zisha_kettle2, 8), "oolong_tea_zisha_kettle2");
		register(puer_tea_zisha_kettle2, new ItemBlockFullKettle(puer_tea_zisha_kettle2, 9), "puer_tea_zisha_kettle2");
		
		register(clay_kettle, "clay_kettle");
		register(zisha_clay_kettle, "zisha_clay_kettle");
		register(teaplant, "teaplant");
		
		register(wood_cup, "wood_cup");
		register(stone_cup, "stone_cup");
		register(glass_cup, "glass_cup");
		register(porcelain_cup, "porcelain_cup");
		register(zisha_cup, "zisha_cup");
		
		register(greentea_wood_cup, "greentea_wood_cup");
		register(matchadrink_wood_cup, "matchadrink_wood_cup");
		register(blacktea_wood_cup, "blacktea_wood_cup");
		register(milktea_wood_cup, "milktea_wood_cup");
		register(lemontea_wood_cup, "lemontea_wood_cup");
		register(yellowtea_wood_cup, "yellowtea_wood_cup");
		register(whitetea_wood_cup, "whitetea_wood_cup");
		register(oolongtea_wood_cup, "oolongtea_wood_cup");
		register(puertea_wood_cup, "puertea_wood_cup");
		
		register(greentea_stone_cup, "greentea_stone_cup");
		register(matchadrink_stone_cup, "matchadrink_stone_cup");
		register(blacktea_stone_cup, "blacktea_stone_cup");
		register(milktea_stone_cup, "milktea_stone_cup");
		register(lemontea_stone_cup, "lemontea_stone_cup");
		register(yellowtea_stone_cup, "yellowtea_stone_cup");
		register(whitetea_stone_cup, "whitetea_stone_cup");
		register(oolongtea_stone_cup, "oolongtea_stone_cup");
		register(puertea_stone_cup, "puertea_stone_cup");
		
		register(greentea_glass_cup, "greentea_glass_cup");
		register(matchadrink_glass_cup, "matchadrink_glass_cup");
		register(blacktea_glass_cup, "blacktea_glass_cup");
		register(milktea_glass_cup, "milktea_glass_cup");
		register(lemontea_glass_cup, "lemontea_glass_cup");
		register(yellowtea_glass_cup, "yellowtea_glass_cup");
		register(whitetea_glass_cup, "whitetea_glass_cup");
		register(oolongtea_glass_cup, "oolongtea_glass_cup");
		register(puertea_glass_cup, "puertea_glass_cup");
		
		register(greentea_porcelain_cup, "greentea_porcelain_cup");
		register(matchadrink_porcelain_cup, "matchadrink_porcelain_cup");
		register(blacktea_porcelain_cup, "blacktea_porcelain_cup");
		register(milktea_porcelain_cup, "milktea_porcelain_cup");
		register(lemontea_porcelain_cup, "lemontea_porcelain_cup");
		register(yellowtea_porcelain_cup, "yellowtea_porcelain_cup");
		register(whitetea_porcelain_cup, "whitetea_porcelain_cup");
		register(oolongtea_porcelain_cup, "oolongtea_porcelain_cup");
		register(puertea_porcelain_cup, "puertea_porcelain_cup");
		
		register(greentea_zisha_cup, "greentea_zisha_cup");
		register(matchadrink_zisha_cup, "matchadrink_zisha_cup");
		register(blacktea_zisha_cup, "blacktea_zisha_cup");
		register(milktea_zisha_cup, "milktea_zisha_cup");
		register(lemontea_zisha_cup, "lemontea_zisha_cup");
		register(yellowtea_zisha_cup, "yellowtea_zisha_cup");
		register(whitetea_zisha_cup, "whitetea_zisha_cup");
		register(oolongtea_zisha_cup, "oolongtea_zisha_cup");
		register(puertea_zisha_cup, "puertea_zisha_cup");
		
		register(xian_rice_seedling, "xian_rice_seedling");
		register(xian_rice_plant, "xian_rice_plant");
		register(field, "field");
		register(straw_blanket, "straw_blanket");
		register(straw_cushion, "straw_cushion");
	}

	public static void loadExtraResourceLocation()
	{
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.lit_tea_drying_pan), new ResourceLocation(TeaStory.MODID, "tea_drying_pan"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.half_dried_leaf_block), new ResourceLocation(TeaStory.MODID, "half_dried_leaf_block"), new ResourceLocation(TeaStory.MODID, "half_dried_leaf_block6"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.green_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "green_tea_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.black_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "black_tea_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.matcha_drink_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "matcha_drink_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.lemon_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "lemon_tea_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.milk_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "milk_tea_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.white_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "white_tea_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.yellow_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "yellow_tea_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.oolong_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "oolong_tea_zisha_kettle"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.puer_tea_zisha_kettle2), new ResourceLocation(TeaStory.MODID, "puer_tea_zisha_kettle"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders()
	{
		registerRender(barrel, "barrel");
		registerRender(teapan, "teapan");
		registerRender(clay_kettle, "clay_kettle");
		registerRender(zisha_clay_kettle, "zisha_clay_kettle");
		registerRender(empty_porcelain_kettle, "empty_porcelain_kettle");
		registerRender(green_tea_porcelain_kettle, 0, "green_tea_porcelain_kettle");
		registerRender(green_tea_porcelain_kettle, 4, "green_tea_porcelain_kettle");
		registerRender(green_tea_porcelain_kettle, 8, "green_tea_porcelain_kettle");
		registerRender(green_tea_porcelain_kettle, 12, "green_tea_porcelain_kettle");
		registerRender(matcha_drink_porcelain_kettle, 0, "matcha_drink_porcelain_kettle");
		registerRender(matcha_drink_porcelain_kettle, 4, "matcha_drink_porcelain_kettle");
		registerRender(matcha_drink_porcelain_kettle, 8, "matcha_drink_porcelain_kettle");
		registerRender(matcha_drink_porcelain_kettle, 12, "matcha_drink_porcelain_kettle");
		registerRender(black_tea_porcelain_kettle, 0, "black_tea_porcelain_kettle");
		registerRender(black_tea_porcelain_kettle, 4, "black_tea_porcelain_kettle");
		registerRender(black_tea_porcelain_kettle, 8, "black_tea_porcelain_kettle");
		registerRender(black_tea_porcelain_kettle, 12, "black_tea_porcelain_kettle");
		registerRender(milk_tea_porcelain_kettle, 0, "milk_tea_porcelain_kettle");
		registerRender(milk_tea_porcelain_kettle, 4, "milk_tea_porcelain_kettle");
		registerRender(milk_tea_porcelain_kettle, 8, "milk_tea_porcelain_kettle");
		registerRender(milk_tea_porcelain_kettle, 12, "milk_tea_porcelain_kettle");
		registerRender(lemon_tea_porcelain_kettle, 0, "lemon_tea_porcelain_kettle");
		registerRender(lemon_tea_porcelain_kettle, 4, "lemon_tea_porcelain_kettle");
		registerRender(lemon_tea_porcelain_kettle, 8, "lemon_tea_porcelain_kettle");
		registerRender(lemon_tea_porcelain_kettle, 12, "lemon_tea_porcelain_kettle");
		registerRender(yellow_tea_porcelain_kettle, 0, "yellow_tea_porcelain_kettle");
		registerRender(yellow_tea_porcelain_kettle, 4, "yellow_tea_porcelain_kettle");
		registerRender(yellow_tea_porcelain_kettle, 8, "yellow_tea_porcelain_kettle");
		registerRender(yellow_tea_porcelain_kettle, 12, "yellow_tea_porcelain_kettle");
		registerRender(white_tea_porcelain_kettle, 0, "white_tea_porcelain_kettle");
		registerRender(white_tea_porcelain_kettle, 4, "white_tea_porcelain_kettle");
		registerRender(white_tea_porcelain_kettle, 8, "white_tea_porcelain_kettle");
		registerRender(white_tea_porcelain_kettle, 12, "white_tea_porcelain_kettle");
		registerRender(oolong_tea_porcelain_kettle, 0, "oolong_tea_porcelain_kettle");
		registerRender(oolong_tea_porcelain_kettle, 4, "oolong_tea_porcelain_kettle");
		registerRender(oolong_tea_porcelain_kettle, 8, "oolong_tea_porcelain_kettle");
		registerRender(oolong_tea_porcelain_kettle, 12, "oolong_tea_porcelain_kettle");
		registerRender(puer_tea_porcelain_kettle, 0, "puer_tea_porcelain_kettle");
		registerRender(puer_tea_porcelain_kettle, 4, "puer_tea_porcelain_kettle");
		registerRender(puer_tea_porcelain_kettle, 8, "puer_tea_porcelain_kettle");
		registerRender(puer_tea_porcelain_kettle, 12, "puer_tea_porcelain_kettle");
		
		registerRender(empty_zisha_kettle, "empty_zisha_kettle");
		registerRender(green_tea_zisha_kettle, 0, "green_tea_zisha_kettle");
		registerRender(green_tea_zisha_kettle, 4, "green_tea_zisha_kettle");
		registerRender(green_tea_zisha_kettle, 8, "green_tea_zisha_kettle");
		registerRender(green_tea_zisha_kettle, 12, "green_tea_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle, 0, "matcha_drink_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle, 4, "matcha_drink_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle, 8, "matcha_drink_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle, 12, "matcha_drink_zisha_kettle");
		registerRender(black_tea_zisha_kettle, 0, "black_tea_zisha_kettle");
		registerRender(black_tea_zisha_kettle, 4, "black_tea_zisha_kettle");
		registerRender(black_tea_zisha_kettle, 8, "black_tea_zisha_kettle");
		registerRender(black_tea_zisha_kettle, 12, "black_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle, 0, "milk_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle, 4, "milk_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle, 8, "milk_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle, 12, "milk_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle, 0, "lemon_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle, 4, "lemon_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle, 8, "lemon_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle, 12, "lemon_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle, 0, "yellow_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle, 4, "yellow_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle, 8, "yellow_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle, 12, "yellow_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle, 0, "white_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle, 4, "white_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle, 8, "white_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle, 12, "white_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle, 0, "oolong_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle, 4, "oolong_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle, 8, "oolong_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle, 12, "oolong_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle, 0, "puer_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle, 4, "puer_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle, 8, "puer_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle, 12, "puer_tea_zisha_kettle");
		
		registerRender(green_tea_zisha_kettle2, 0, "green_tea_zisha_kettle");
		registerRender(green_tea_zisha_kettle2, 4, "green_tea_zisha_kettle");
		registerRender(green_tea_zisha_kettle2, 8, "green_tea_zisha_kettle");
		registerRender(green_tea_zisha_kettle2, 12, "green_tea_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle2, 0, "matcha_drink_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle2, 4, "matcha_drink_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle2, 8, "matcha_drink_zisha_kettle");
		registerRender(matcha_drink_zisha_kettle2, 12, "matcha_drink_zisha_kettle");
		registerRender(black_tea_zisha_kettle2, 0, "black_tea_zisha_kettle");
		registerRender(black_tea_zisha_kettle2, 4, "black_tea_zisha_kettle");
		registerRender(black_tea_zisha_kettle2, 8, "black_tea_zisha_kettle");
		registerRender(black_tea_zisha_kettle2, 12, "black_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle2, 0, "milk_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle2, 4, "milk_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle2, 8, "milk_tea_zisha_kettle");
		registerRender(milk_tea_zisha_kettle2, 12, "milk_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle2, 0, "lemon_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle2, 4, "lemon_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle2, 8, "lemon_tea_zisha_kettle");
		registerRender(lemon_tea_zisha_kettle2, 12, "lemon_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle2, 0, "yellow_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle2, 4, "yellow_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle2, 8, "yellow_tea_zisha_kettle");
		registerRender(yellow_tea_zisha_kettle2, 12, "yellow_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle2, 0, "white_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle2, 4, "white_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle2, 8, "white_tea_zisha_kettle");
		registerRender(white_tea_zisha_kettle2, 12, "white_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle2, 0, "oolong_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle2, 4, "oolong_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle2, 8, "oolong_tea_zisha_kettle");
		registerRender(oolong_tea_zisha_kettle2, 12, "oolong_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle2, 0, "puer_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle2, 4, "puer_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle2, 8, "puer_tea_zisha_kettle");
		registerRender(puer_tea_zisha_kettle2, 12, "puer_tea_zisha_kettle");
		
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
		registerRender(field, "field");
		registerRender(lit_cooking_pan, "lit_cooking_pan");
		registerRender(straw_blanket, "straw_blanket");
		registerRender(straw_cushion, "straw_cushion");
		registerRender(half_dried_leaf_block, 0, "half_dried_leaf_block");
		registerRender(half_dried_leaf_block, 8, "half_dried_leaf_block6");
	}

	private static void register(Block block, String name)
	{
		GameRegistry.registerBlock(block, name);
	}

	private static void register(Block block, ItemBlock itemBlock, String name)
	{
		GameRegistry.registerBlock(block, null, name);
		GameRegistry.registerItem(itemBlock, name);
		GameData.getBlockItemMap().put(block, itemBlock);
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Block block, String name)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(TeaStory.MODID + ":" + name, "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Block block, int meta, String name)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(TeaStory.MODID + ":" + name, "inventory"));
	}
}