package cateam.teastory.achievement;

import cateam.teastory.TeaStory;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.item.ItemLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class AchievementLoader
{
	public static Achievement teaSeeds = new Achievement("achievement.teastory.teaSeeds", "teastory.teaSeeds", 0, 0, ItemLoader.tea_seeds, null);
	public static Achievement teaLeaf = new Achievement("achievement.teastory.teaLeaf", "teastory.teaLeaf", 0, 2, ItemLoader.tea_leaf, null);
	public static Achievement burntLeaf = new Achievement("achievement.teastory.burntLeaf", "teastory.burntLeaf", 2, 2, ItemLoader.burnt_tea, teaLeaf);
	public static Achievement teaBasket = new Achievement("achievement.teastory.teaBasket", "teastory.teaBasket", -2, 2, BlockLoader.teapan, teaLeaf);
	public static Achievement teaPlant = new Achievement("achievement.teastory.teaPlant", "teastory.teaPlant", 0, -2, Items.STONE_HOE, null);
	public static Achievement halfDriedTea = new Achievement("achievement.teastory.halfDriedTea", "teastory.halfDriedTea", -2, 4, ItemLoader.half_dried_tea, teaBasket);
	public static Achievement teaDryingPan = new Achievement("achievement.teastory.teaDryingPan", "teastory.teaDryingPan", -2, 6, BlockLoader.tea_drying_pan, halfDriedTea);
	public static Achievement teaStove = new Achievement("achievement.teastory.teaStove", "teastory.teaStove", -2, 8, BlockLoader.tea_stove, teaDryingPan);
	public static Achievement barrel = new Achievement("achievement.teastory.Barrel", "teastory.Barrel", -4, 4, BlockLoader.barrel, halfDriedTea);
	public static Achievement greenTea = new Achievement("achievement.teastory.greenTea", "teastory.greenTea", -2, 0, ItemLoader.green_tea, teaBasket);
	public static Achievement matchaDrink = new Achievement("achievement.teastory.matchaDrink", "teastory.matchaDrink", 0, 8, ItemLoader.matcha_drink, teaStove);
	public static Achievement blackTea = new Achievement("achievement.teastory.blackTea", "teastory.blackTea", -4, 6, ItemLoader.black_tea, barrel);
	public static Achievement kettle = new Achievement("achievement.teastory.Kettle", "teastory.Kettle", -4, -2, BlockLoader.empty_kettle, null);
	public static Achievement hotWater = new Achievement("achievement.teastory.hotWater", "teastory.hotWater", -2, -2, ItemLoader.hot_water, kettle);
	public static Achievement wetTea = new Achievement("achievement.teastory.wetTea", "teastory.wetTea", -4, 2, ItemLoader.wet_tea, teaBasket);
	public static Achievement woodenCup = new Achievement("achievement.teastory.woodenCup", "teastory.woodenCup", 2, 4, new ItemStack(ItemLoader.cup, 1, 0), null);
	public static Achievement stoneCup = new Achievement("achievement.teastory.stoneCup", "teastory.stoneCup", 2, 6, new ItemStack(ItemLoader.cup, 1, 1), woodenCup);
	public static Achievement glassCup = new Achievement("achievement.teastory.glassCup", "teastory.glassCup", 2, 8, new ItemStack(ItemLoader.cup, 1, 2), stoneCup);
	public static Achievement porcelainCup = new Achievement("achievement.teastory.porcelainCup", "teastory.porcelainCup", 2, 10, new ItemStack(ItemLoader.cup, 1, 3), glassCup);
	public static Achievement soilDetectionMeter = new Achievement("achievement.teastory.soilDetectionMeter", "teastory.soilDetectionMeter", 2, 0, ItemLoader.soil_detection_meter, null);
	public static AchievementPage pageTeaStory = new AchievementPage(TeaStory.NAME, teaSeeds, teaLeaf, burntLeaf, teaBasket, teaPlant, halfDriedTea,
			teaDryingPan, teaStove, barrel, greenTea, matchaDrink, blackTea, kettle, hotWater, wetTea, woodenCup, stoneCup, glassCup, porcelainCup, soilDetectionMeter);
	public AchievementLoader()
    {
		teaSeeds.registerStat();
		teaLeaf.registerStat();
		burntLeaf.registerStat();
		teaBasket.registerStat();
		teaPlant.registerStat();
		halfDriedTea.registerStat();
		teaDryingPan.registerStat();
		teaStove.registerStat();
		barrel.registerStat();
		greenTea.registerStat();
		matchaDrink.registerStat();
		blackTea.registerStat();
		kettle.registerStat();
		hotWater.registerStat();
		wetTea.registerStat();
		woodenCup.registerStat();
		stoneCup.registerStat();
		glassCup.registerStat();
		porcelainCup.registerStat();
		soilDetectionMeter.registerStat();
		AchievementPage.registerAchievementPage(pageTeaStory);
    }
}
