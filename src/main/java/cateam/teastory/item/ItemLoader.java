package cateam.teastory.item;

import cateam.teastory.TeaStory;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader
{
	//茶叶
	public static Item tea_leaf = new TSItem("tea_leaf", 64, CreativeTabsLoader.tabTeaStory);
	public static Item half_dried_tea = new HalfDriedTeaLeaf();
	public static Item matcha_leaf = new TSItem("matcha_leaf", 64, CreativeTabsLoader.tabTeaStory);
	public static Item wet_tea = new TSItem("wet_tea", 64, CreativeTabsLoader.tabTeaStory);
	public static Item broken_tea = new TSItem("broken_tea", 64, CreativeTabsLoader.tabTeaStory);
	public static Item tea_seeds = new TeaSeeds();
	public static Item tea_residue = new TeaResidue();
	
	public static ItemTeaLeaf dried_tea = new ItemTeaLeaf("dried_tea", 64, "green_tea", 8);
	public static ItemTeaLeaf matcha_powder = new ItemTeaLeaf("matcha_powder", 64, "matcha_drink", 8);
	public static ItemTeaLeaf black_tea_leaf = new ItemTeaLeaf("black_tea_leaf", 64, "black_tea", 8);
	public static ItemTeaLeaf yellow_tea_leaf = new ItemTeaLeaf("yellow_tea_leaf", 64, "yellow_tea", 8);
	public static ItemTeaLeaf white_tea_leaf = new ItemTeaLeaf("white_tea_leaf", 64, "white_tea", 8);
	public static ItemTeaLeaf oolong_tea_leaf = new ItemTeaLeaf("oolong_tea_leaf", 64, "oolong_tea", 8);
	public static ItemTeaLeaf puer_tea_leaf = new ItemTeaLeaf("puer_tea_leaf", 64, "puer_tea", 8);
	
	public static Item empty_tea_bag = new TSItem("empty_tea_bag", 64, CreativeTabsLoader.tabTeaStory);
	public static ItemTeaLeaf green_tea_bag =new ItemTeaLeaf("green_tea_bag", 64, "green_tea", 1);
	public static ItemTeaLeaf black_tea_bag =new ItemTeaLeaf("black_tea_bag", 64, "black_tea", 1);
	public static ItemTeaLeaf yellow_tea_bag = new ItemTeaLeaf("yellow_tea_bag", 64, "yellow_tea", 1);
	public static ItemTeaLeaf white_tea_bag = new ItemTeaLeaf("white_tea_bag", 64, "white_tea", 1);
	public static ItemTeaLeaf oolong_tea_bag = new ItemTeaLeaf("oolong_tea_bag", 64, "oolong_tea", 1);
	public static ItemTeaLeaf puer_tea_bag = new ItemTeaLeaf("puer_tea_bag", 64, "puer_tea", 1);

	//稻米
	public static Item rice_seeds = new ItemSeeds(BlockLoader.rice_seedling, Blocks.FARMLAND).setUnlocalizedName("rice_seeds").setCreativeTab(CreativeTabsLoader.tabRice);
	public static Item rice_seedlings = new ItemRiceSeedling();
	public static Item straw = new TSItem("straw", 64, CreativeTabsLoader.tabRice);
	public static Item rice = new TSItem("rice", 64, CreativeTabsLoader.tabRice);
	public static Item washed_rice = new TSItem("washed_rice", 64, CreativeTabsLoader.tabRice);
	public static Item wooden_lid = new TSItem("wooden_lid", 64, CreativeTabsLoader.tabRice);
	public static Item straw_rope = new TSItem("straw_rope", 64, CreativeTabsLoader.tabRice);
	
	public static Item rice_ball = new ItemFood(6, 0.6F, false).setUnlocalizedName("rice_ball").setCreativeTab(CreativeTabsLoader.tabRice);
	public static Item porkchop_rice = new TSItemFoodWithBowl("porkchop_rice", 17, 0.5F);
	public static Item steak_rice = new TSItemFoodWithBowl("steak_rice", 17, 0.5F);
	public static Item chicken_rice = new TSItemFoodWithBowl("chicken_rice", 15, 0.5F);
	public static Item potato_rice = new TSItemFoodWithBowl("potato_rice", 13, 0.5F);
	public static Item rabbit_rice = new TSItemFoodWithBowl("rabbit_rice", 13, 0.5F);

	//茶具
	public static Item pot_stone = new ItemEmptyPot("pot_stone");
	public static Item pot_iron = new ItemEmptyPot("pot_iron");
	public static Item pot_porcelain = new ItemEmptyPot("pot_porcelain");
	public static Item pot_zisha = new ItemEmptyPot("pot_zisha");
	public static Item pot_clay = new TSItem("pot_clay", 64, CreativeTabsLoader.tabDrink);
	public static Item pot_zisha_clay = new TSItem("pot_zisha_clay", 64, CreativeTabsLoader.tabDrink);
	public static Item cw_pot_stone = new TSItem("cold_water_pot_stone", 1, CreativeTabsLoader.tabDrink);
	public static Item cw_pot_iron = new TSItem("cold_water_pot_iron", 64, CreativeTabsLoader.tabDrink);
	public static Item cw_pot_porcelain = new TSItem("cold_water_pot_porcelain", 64, CreativeTabsLoader.tabDrink);
	public static Item cw_pot_zisha = new TSItem("cold_water_pot_zisha", 64, CreativeTabsLoader.tabDrink);
	public static Item clay_cup = new TSItem("clay_cup", 64, CreativeTabsLoader.tabDrink);
	public static Item zisha_clay_cup = new TSItem("zisha_clay_cup", 64, CreativeTabsLoader.tabDrink);
	public static Item cup = new ItemCup();
	public static Item tea_whisk = new TSItem("tea_whisk", 1, CreativeTabsLoader.tabDrink).setMaxDamage(64);
	
	public static Item zisha_clay = new TSItem("zisha_clay", 64, CreativeTabsLoader.tabDrink);

	//茶饮
	public static Item hw_pot_stone = new ItemWaterPot("pot_stone", 2);
	public static Item hw_pot_iron = new ItemWaterPot("pot_iron", 4);
	public static Item hw_pot_porcelain = new ItemWaterPot("pot_porcelain", 8);
	public static Item hw_pot_zisha = new ItemWaterPot("pot_zisha", 16);
	
	public static ItemTeaDrink green_tea = new GreenTea();
	public static ItemTeaDrink matcha_drink = new MatchaDrink();
	public static ItemTeaDrink black_tea = new BlackTea();
	public static ItemTeaDrink milk_tea = new MilkTea();
	public static ItemTeaDrink lemon_tea = new LemonTea();
	public static ItemTeaDrink yellow_tea = new YellowTea();
	public static ItemTeaDrink white_tea = new WhiteTea();
	public static ItemTeaDrink oolong_tea = new OolongTea();
	public static ItemTeaDrink puer_tea = new PuerTea();
	
	public static ItemFood tea_egg = new TeaEgg();
	public static ItemFood matcha_cookie = new MatchaCookie();

	//其他工具
	public static Item wooden_mortar_and_pestle = new MortarAndPestle("wooden_mortar_and_pestle", 128);
	public static Item soil_detection_meter = new SoilDetectionMeter();
	public static Item straw_blanket = new ItemStrawBlanket();
	public static Item sickle = new  ItemSickle();
	public static Item lemon = new TSItem("lemon", 64, CreativeTabsLoader.tabDrink);
	public static ItemSword shennongruler = new ShennongRuler();
	public static Item watering_hoe = new WaterPloughingHoe();
	public static ItemRecord caichawuqu = new Record("caichawuqu", "record_caichawuqu");

	public ItemLoader(FMLPreInitializationEvent event)
	{
		register(tea_leaf);
		register(half_dried_tea);
		register(dried_tea);
		register(matcha_leaf);
		register(matcha_powder);
		register(black_tea_leaf);
		register(yellow_tea_leaf);
		register(white_tea_leaf);
		register(oolong_tea_leaf);
		register(puer_tea_leaf);
		register(wet_tea);
		register(broken_tea);
		register(tea_seeds);
		register(cup);
		register(empty_tea_bag);
		register(green_tea_bag);
		register(black_tea_bag);
		register(yellow_tea_bag);
		register(white_tea_bag);
		register(oolong_tea_bag);
		register(puer_tea_bag);
		register(wooden_mortar_and_pestle);
		register(soil_detection_meter);
		register(clay_cup);
		register(green_tea);
		register(matcha_drink);
		register(black_tea);
		register(milk_tea);
		register(lemon_tea);
		register(yellow_tea);
		register(white_tea);
		register(oolong_tea);
		register(puer_tea);
		register(tea_egg);
		register(shennongruler);
		register(caichawuqu);
		register(rice_seeds);
		register(rice_seedlings);
		register(straw);
		register(watering_hoe);
		register(rice);
		register(washed_rice);
		register(wooden_lid);
		register(rice_ball);
		register(straw_rope);
		register(straw_blanket);
		register(sickle);
		register(porkchop_rice);
		register(steak_rice);
		register(chicken_rice);
		register(potato_rice);
		register(rabbit_rice);
		register(tea_whisk);
		register(lemon);
		register(pot_stone);
		register(pot_iron);
		register(pot_porcelain);
		register(pot_zisha);
		register(pot_clay);
		register(pot_zisha_clay);
		register(cw_pot_zisha);
		register(cw_pot_porcelain);
		register(cw_pot_iron);
		register(cw_pot_stone);
		register(hw_pot_zisha);
		register(hw_pot_porcelain);
		register(hw_pot_iron);
		register(hw_pot_stone);
		register(zisha_clay);
		register(zisha_clay_cup);
		register(matcha_cookie);
		register(tea_residue);
	}

	public static void loadExtraResourceLocation()
	{
		ModelBakery.registerItemVariants(tea_residue,  new ResourceLocation(TeaStory.MODID, "tea_residue_green"),  new ResourceLocation(TeaStory.MODID, "tea_residue_black"),  new ResourceLocation(TeaStory.MODID, "tea_residue_yellow"),  new ResourceLocation(TeaStory.MODID, "tea_residue_white"),  new ResourceLocation(TeaStory.MODID, "tea_residue_oolong"),  new ResourceLocation(TeaStory.MODID, "tea_residue_puer"));
		ModelBakery.registerItemVariants(cup,  new ResourceLocation(TeaStory.MODID, "cup_wood"),  new ResourceLocation(TeaStory.MODID, "cup_stone"), new ResourceLocation(TeaStory.MODID, "cup_glass"), new ResourceLocation(TeaStory.MODID, "cup_porcelain"), new ResourceLocation(TeaStory.MODID, "cup_zisha"));
		ModelBakery.registerItemVariants(green_tea,  new ResourceLocation(TeaStory.MODID, "green_tea_wood"),  new ResourceLocation(TeaStory.MODID, "green_tea_stone"), new ResourceLocation(TeaStory.MODID, "green_tea_glass"), new ResourceLocation(TeaStory.MODID, "green_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "green_tea_zisha"));
		ModelBakery.registerItemVariants(black_tea,  new ResourceLocation(TeaStory.MODID, "black_tea_wood"),  new ResourceLocation(TeaStory.MODID, "black_tea_stone"), new ResourceLocation(TeaStory.MODID, "black_tea_glass"), new ResourceLocation(TeaStory.MODID, "black_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "black_tea_zisha"));
		ModelBakery.registerItemVariants(matcha_drink,  new ResourceLocation(TeaStory.MODID, "matcha_drink_wood"),  new ResourceLocation(TeaStory.MODID, "matcha_drink_stone"), new ResourceLocation(TeaStory.MODID, "matcha_drink_glass"), new ResourceLocation(TeaStory.MODID, "matcha_drink_porcelain"), new ResourceLocation(TeaStory.MODID, "matcha_drink_zisha"));
		ModelBakery.registerItemVariants(lemon_tea,  new ResourceLocation(TeaStory.MODID, "lemon_tea_wood"),  new ResourceLocation(TeaStory.MODID, "lemon_tea_stone"), new ResourceLocation(TeaStory.MODID, "lemon_tea_glass"), new ResourceLocation(TeaStory.MODID, "lemon_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "lemon_tea_zisha"));
		ModelBakery.registerItemVariants(milk_tea,  new ResourceLocation(TeaStory.MODID, "milk_tea_wood"),  new ResourceLocation(TeaStory.MODID, "milk_tea_stone"), new ResourceLocation(TeaStory.MODID, "milk_tea_glass"), new ResourceLocation(TeaStory.MODID, "milk_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "milk_tea_zisha"));
		ModelBakery.registerItemVariants(yellow_tea,  new ResourceLocation(TeaStory.MODID, "yellow_tea_wood"),  new ResourceLocation(TeaStory.MODID, "yellow_tea_stone"), new ResourceLocation(TeaStory.MODID, "yellow_tea_glass"), new ResourceLocation(TeaStory.MODID, "yellow_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "yellow_tea_zisha"));
		ModelBakery.registerItemVariants(white_tea,  new ResourceLocation(TeaStory.MODID, "white_tea_wood"),  new ResourceLocation(TeaStory.MODID, "white_tea_stone"), new ResourceLocation(TeaStory.MODID, "white_tea_glass"), new ResourceLocation(TeaStory.MODID, "white_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "white_tea_zisha"));
		ModelBakery.registerItemVariants(oolong_tea,  new ResourceLocation(TeaStory.MODID, "oolong_tea_wood"),  new ResourceLocation(TeaStory.MODID, "oolong_tea_stone"), new ResourceLocation(TeaStory.MODID, "oolong_tea_glass"), new ResourceLocation(TeaStory.MODID, "oolong_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "oolong_tea_zisha"));
		ModelBakery.registerItemVariants(puer_tea,  new ResourceLocation(TeaStory.MODID, "puer_tea_wood"),  new ResourceLocation(TeaStory.MODID, "puer_tea_stone"), new ResourceLocation(TeaStory.MODID, "puer_tea_glass"), new ResourceLocation(TeaStory.MODID, "puer_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "puer_tea_zisha"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders()
	{
		registerRender(tea_leaf);
		registerRender(half_dried_tea);
		registerRender(dried_tea);
		registerRender(green_tea, 0, "green_tea_wood");
		registerRender(green_tea, 2, "green_tea_stone");
		registerRender(green_tea, 3, "green_tea_glass");
		registerRender(green_tea, 4, "green_tea_porcelain");
		registerRender(green_tea, 5, "green_tea_zisha");
		registerRender(black_tea, 0, "black_tea_wood");
		registerRender(black_tea, 2, "black_tea_stone");
		registerRender(black_tea, 3, "black_tea_glass");
		registerRender(black_tea, 4, "black_tea_porcelain");
		registerRender(black_tea, 5, "black_tea_zisha");
		registerRender(wet_tea);
		registerRender(broken_tea);
		registerRender(matcha_powder);
		registerRender(wooden_mortar_and_pestle);
		registerRender(soil_detection_meter);
		registerRender(black_tea_leaf);
		registerRender(matcha_leaf);
		registerRender(yellow_tea_leaf);
		registerRender(white_tea_leaf);
		registerRender(oolong_tea_leaf);
		registerRender(puer_tea_leaf);
		registerRender(clay_cup);
		registerRender(cup, 0, "cup_wood");
		registerRender(cup, 2, "cup_stone");
		registerRender(cup, 3, "cup_glass");
		registerRender(cup, 4, "cup_porcelain");
		registerRender(cup, 5, "cup_zisha");
		registerRender(tea_seeds);
		registerRender(matcha_drink, 0, "matcha_drink_wood");
		registerRender(matcha_drink, 2, "matcha_drink_stone");
		registerRender(matcha_drink, 3, "matcha_drink_glass");
		registerRender(matcha_drink, 4, "matcha_drink_porcelain");
		registerRender(matcha_drink, 5, "matcha_drink_zisha");
		registerRender(lemon_tea, 0, "lemon_tea_wood");
		registerRender(lemon_tea, 2, "lemon_tea_stone");
		registerRender(lemon_tea, 3, "lemon_tea_glass");
		registerRender(lemon_tea, 4, "lemon_tea_porcelain");
		registerRender(lemon_tea, 5, "lemon_tea_zisha");
		registerRender(milk_tea, 0, "milk_tea_wood");
		registerRender(milk_tea, 2, "milk_tea_stone");
		registerRender(milk_tea, 3, "milk_tea_glass");
		registerRender(milk_tea, 4, "milk_tea_porcelain");
		registerRender(milk_tea, 5, "milk_tea_zisha");
		registerRender(yellow_tea, 0, "yellow_tea_wood");
		registerRender(yellow_tea, 2, "yellow_tea_stone");
		registerRender(yellow_tea, 3, "yellow_tea_glass");
		registerRender(yellow_tea, 4, "yellow_tea_porcelain");
		registerRender(yellow_tea, 5, "yellow_tea_zisha");
		registerRender(white_tea, 0, "white_tea_wood");
		registerRender(white_tea, 2, "white_tea_stone");
		registerRender(white_tea, 3, "white_tea_glass");
		registerRender(white_tea, 4, "white_tea_porcelain");
		registerRender(white_tea, 5, "white_tea_zisha");
		registerRender(oolong_tea, 0, "oolong_tea_wood");
		registerRender(oolong_tea, 2, "oolong_tea_stone");
		registerRender(oolong_tea, 3, "oolong_tea_glass");
		registerRender(oolong_tea, 4, "oolong_tea_porcelain");
		registerRender(oolong_tea, 5, "oolong_tea_zisha");
		registerRender(puer_tea, 0, "puer_tea_wood");
		registerRender(puer_tea, 2, "puer_tea_stone");
		registerRender(puer_tea, 3, "puer_tea_glass");
		registerRender(puer_tea, 4, "puer_tea_porcelain");
		registerRender(puer_tea, 5, "puer_tea_zisha");
		registerRender(tea_residue, 0, "tea_residue_green");
		registerRender(tea_residue, 1, "tea_residue_black");
		registerRender(tea_residue, 2, "tea_residue_yellow");
		registerRender(tea_residue, 3, "tea_residue_white");
		registerRender(tea_residue, 4, "tea_residue_oolong");
		registerRender(tea_residue, 5, "tea_residue_puer");
		registerRender(empty_tea_bag);
		registerRender(green_tea_bag);
		registerRender(black_tea_bag);
		registerRender(yellow_tea_bag);
		registerRender(white_tea_bag);
		registerRender(oolong_tea_bag);
		registerRender(puer_tea_bag);
		registerRender(tea_egg);
		registerRender(shennongruler);
		registerRender(caichawuqu);
		registerRender(rice_seeds);
		registerRender(rice_seedlings);
		registerRender(straw);
		registerRender(watering_hoe);
		registerRender(rice);
		registerRender(washed_rice);
		registerRender(wooden_lid);
		registerRender(rice_ball);
		registerRender(straw_rope);
		registerRender(straw_blanket);
		registerRender(sickle);
		registerRender(porkchop_rice);
		registerRender(steak_rice);
		registerRender(chicken_rice);
		registerRender(potato_rice);
		registerRender(rabbit_rice);
		registerRender(tea_whisk);
		registerRender(lemon);
		registerRender(pot_stone);
		registerRender(pot_iron);
		registerRender(pot_porcelain);
		registerRender(pot_zisha);
		registerRender(pot_clay);
		registerRender(pot_zisha_clay);
		registerRender(cw_pot_zisha);
		registerRender(cw_pot_porcelain);
		registerRender(cw_pot_iron);
		registerRender(cw_pot_stone);
		registerRender(hw_pot_zisha);
		registerRender(hw_pot_porcelain);
		registerRender(hw_pot_iron);
		registerRender(hw_pot_stone);
		registerRender(zisha_clay);
		registerRender(zisha_clay_cup);
		registerRender(matcha_cookie);
	}

	private static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(TeaStory.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerRender(Item item, int meta, String file)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta,
				new ModelResourceLocation(TeaStory.MODID + ":" + file, "inventory"));
	}
}

