package cateam.teastory.item;

import cateam.teastory.TeaStory;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
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
	public static Item tea_leaf = new ItemHasTooltip("tea_leaf", 64, CreativeTabsLoader.tabteastory);
	public static Item half_dried_tea = new ItemHasTooltip("half_dried_tea", 64, CreativeTabsLoader.tabteastory);
	public static ItemTeaLeaf dried_tea = new ItemTeaLeaf("dried_tea", 64, BlockLoader.greentea_kettle);
	public static ItemTeaLeaf burnt_tea = new ItemTeaLeaf("burnt_tea", 64, BlockLoader.burntgreentea_kettle);
	public static Item matcha_leaf = new ItemHasTooltip("matcha_leaf", 64, CreativeTabsLoader.tabteastory);
	public static ItemTeaLeaf matcha = new ItemTeaLeaf("matcha", 64, BlockLoader.matcha_kettle);
	public static ItemTeaLeaf black_tea_leaf = new ItemTeaLeaf("black_tea_leaf", 64, BlockLoader.blacktea_kettle);
	public static Item wet_tea = new ItemHasTooltip("wet_tea", 64, CreativeTabsLoader.tabteastory);
	public static Item broken_tea = new ItemHasTooltip("broken_tea", 64, CreativeTabsLoader.tabteastory);
	public static Item tea_seeds = new TeaSeeds();
	public static Item tea_residue = new TeaResidue();
	public static Item tea_bag = new TeaBag();

	//稻米
	public static Item rice_seeds = new ItemSeeds(BlockLoader.rice_seedling, Blocks.FARMLAND).setUnlocalizedName("rice_seeds").setCreativeTab(CreativeTabsLoader.tabrice);
	public static Item rice_seedlings = new ItemRiceSeedling();
	public static Item straw = new TSItem("straw", 64, CreativeTabsLoader.tabrice);
	public static Item rice = new TSItem("rice", 64, CreativeTabsLoader.tabrice);
	public static Item washed_rice = new TSItem("washed_rice", 64, CreativeTabsLoader.tabrice);
	public static Item wooden_lid = new TSItem("wooden_lid", 64, CreativeTabsLoader.tabrice);
	public static Item straw_rope = new TSItem("straw_rope", 64, CreativeTabsLoader.tabrice);
	
	public static Item rice_ball = new ItemFood(6, 0.8F, false).setUnlocalizedName("rice_ball").setCreativeTab(CreativeTabsLoader.tabrice);
	public static Item porkchop_rice = new TSItemFoodWithBowl("porkchop_rice", 17, 0.5F);
	public static Item steak_rice = new TSItemFoodWithBowl("steak_rice", 17, 0.5F);
	public static Item chicken_rice = new TSItemFoodWithBowl("chicken_rice", 15, 0.5F);
	public static Item potato_rice = new TSItemFoodWithBowl("potato_rice", 13, 0.5F);

	//工具
	public static Item sieve = new TSItem("sieve", 64, CreativeTabsLoader.tabteastory);
	public static Item mortar_and_pestle = new MortarAndPestle();
	public static Item soil_detection_meter = new SoilDetectionMeter();
	public static Item watering_hoe = new WaterPloughingHoe();
	public static Item straw_blanket = new ItemStrawBlanket();
	public static Item sickle = new  ItemSickle();

	//茶具
	public static Item clay_cup = new ItemHasTooltip("clay_cup", 64, CreativeTabsLoader.tabteastory);
	public static Item cup = new ItemCup();

	//茶饮
	public static ItemFood hot_water = new HotWater();
	public static Item cold_water = new ColdWater();
	public static ItemTeaDrink green_tea = new GreenTea();
	public static ItemTeaDrink burnt_green_tea = new BurntGreenTea();
	public static ItemTeaDrink matcha_drink = new MatchaDrink();
	public static ItemTeaDrink black_tea = new BlackTea();
	public static ItemFood tea_egg = new TeaEgg();

	public static ItemSword shennongruler = new ShennongRuler();//其他
	public static ItemRecord caichawuqu = new Record("caichawuqu", "record_caichawuqu");

	public ItemLoader(FMLPreInitializationEvent event)
	{
		register(tea_leaf);
		register(half_dried_tea);
		register(dried_tea);
		register(burnt_tea);
		register(matcha_leaf);
		register(matcha);
		register(black_tea_leaf);
		register(wet_tea);
		register(broken_tea);
		register(tea_seeds);
		register(tea_residue);
		register(tea_bag);
		register(sieve);
		register(mortar_and_pestle);
		register(soil_detection_meter);
		register(clay_cup);
		register(cup);
		register(hot_water);
		register(cold_water);
		register(green_tea);
		register(burnt_green_tea);
		register(matcha_drink);
		register(black_tea);
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
	}

	public static void loadExtraResourceLocation() {
		ModelBakery.registerItemVariants(tea_residue,  new ResourceLocation(TeaStory.MODID, "tea_residue_green"),  new ResourceLocation(TeaStory.MODID, "tea_residue_black"));
		ModelBakery.registerItemVariants(cup,  new ResourceLocation(TeaStory.MODID, "cup_wood"),  new ResourceLocation(TeaStory.MODID, "cup_stone"), new ResourceLocation(TeaStory.MODID, "cup_glass"), new ResourceLocation(TeaStory.MODID, "cup_porcelain"));
		ModelBakery.registerItemVariants(green_tea,  new ResourceLocation(TeaStory.MODID, "green_tea_wood"),  new ResourceLocation(TeaStory.MODID, "green_tea_stone"), new ResourceLocation(TeaStory.MODID, "green_tea_glass"), new ResourceLocation(TeaStory.MODID, "green_tea_porcelain"));
		ModelBakery.registerItemVariants(burnt_green_tea,  new ResourceLocation(TeaStory.MODID, "burnt_green_tea_wood"),  new ResourceLocation(TeaStory.MODID, "burnt_green_tea_stone"), new ResourceLocation(TeaStory.MODID, "burnt_green_tea_glass"), new ResourceLocation(TeaStory.MODID, "burnt_green_tea_porcelain"));
		ModelBakery.registerItemVariants(matcha_drink,  new ResourceLocation(TeaStory.MODID, "matcha_drink_wood"),  new ResourceLocation(TeaStory.MODID, "matcha_drink_stone"), new ResourceLocation(TeaStory.MODID, "matcha_drink_glass"), new ResourceLocation(TeaStory.MODID, "matcha_drink_porcelain"));
		ModelBakery.registerItemVariants(black_tea,  new ResourceLocation(TeaStory.MODID, "black_tea_wood"),  new ResourceLocation(TeaStory.MODID, "black_tea_stone"), new ResourceLocation(TeaStory.MODID, "black_tea_glass"), new ResourceLocation(TeaStory.MODID, "black_tea_porcelain"));
		ModelBakery.registerItemVariants(hot_water,  new ResourceLocation(TeaStory.MODID, "hot_water_wood"),  new ResourceLocation(TeaStory.MODID, "hot_water_stone"), new ResourceLocation(TeaStory.MODID, "hot_water_glass"), new ResourceLocation(TeaStory.MODID, "hot_water_porcelain"));
		ModelBakery.registerItemVariants(cold_water,  new ResourceLocation(TeaStory.MODID, "hot_water_wood"),  new ResourceLocation(TeaStory.MODID, "hot_water_stone"), new ResourceLocation(TeaStory.MODID, "hot_water_glass"), new ResourceLocation(TeaStory.MODID, "hot_water_porcelain"));
		ModelBakery.registerItemVariants(tea_bag,  new ResourceLocation(TeaStory.MODID, "green_tea_bag"),  new ResourceLocation(TeaStory.MODID, "black_tea_bag"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders()
	{
		registerRender(tea_leaf);
		registerRender(half_dried_tea);
		registerRender(dried_tea);
		registerRender(hot_water, 0, "hot_water_wood");
		registerRender(hot_water, 1, "hot_water_stone");
		registerRender(hot_water, 2, "hot_water_glass");
		registerRender(hot_water, 3, "hot_water_porcelain");
		registerRender(cold_water, 0, "hot_water_wood");
		registerRender(cold_water, 1, "hot_water_stone");
		registerRender(cold_water, 2, "hot_water_glass");
		registerRender(cold_water, 3, "hot_water_porcelain");
		registerRender(green_tea, 0, "green_tea_wood");
		registerRender(green_tea, 1, "green_tea_stone");
		registerRender(green_tea, 2, "green_tea_glass");
		registerRender(green_tea, 3, "green_tea_porcelain");
		registerRender(sieve);
		registerRender(burnt_tea);
		registerRender(wet_tea);
		registerRender(broken_tea);
		registerRender(matcha);
		registerRender(mortar_and_pestle);
		registerRender(soil_detection_meter);
		registerRender(black_tea_leaf);
		registerRender(matcha_leaf);
		registerRender(clay_cup);
		registerRender(cup, 0, "cup_wood");
		registerRender(cup, 1, "cup_stone");
		registerRender(cup, 2, "cup_glass");
		registerRender(cup, 3, "cup_porcelain");
		registerRender(tea_seeds);
		registerRender(burnt_green_tea, 0, "burnt_green_tea_wood");
		registerRender(burnt_green_tea, 1, "burnt_green_tea_stone");
		registerRender(burnt_green_tea, 2, "burnt_green_tea_glass");
		registerRender(burnt_green_tea, 3, "burnt_green_tea_porcelain");
		registerRender(matcha_drink, 0, "matcha_drink_wood");
		registerRender(matcha_drink, 1, "matcha_drink_stone");
		registerRender(matcha_drink, 2, "matcha_drink_glass");
		registerRender(matcha_drink, 3, "matcha_drink_porcelain");
		registerRender(black_tea, 0, "black_tea_wood");
		registerRender(black_tea, 1, "black_tea_stone");
		registerRender(black_tea, 2, "black_tea_glass");
		registerRender(black_tea, 3, "black_tea_porcelain");
		registerRender(tea_residue, 0, "tea_residue_green");
		registerRender(tea_residue, 1, "tea_residue_black");
		registerRender(tea_bag, 0, "green_tea_bag");
		registerRender(tea_bag, 1, "black_tea_bag");
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

