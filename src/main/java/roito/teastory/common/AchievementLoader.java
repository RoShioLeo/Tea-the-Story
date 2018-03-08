package roito.teastory.common;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import roito.teastory.TeaStory;
import roito.teastory.block.BlockLoader;
import roito.teastory.item.ItemLoader;

public class AchievementLoader
{
	public static Achievement teaSeeds = new Achievement("achievement.teastory.teaSeeds", "teastory.teaSeeds", 0, 0, ItemLoader.tea_seeds, null);
	public static Achievement soilDetectionMeter = new Achievement("achievement.teastory.soilDetectionMeter", "teastory.soilDetectionMeter", 3, -1, ItemLoader.soil_detection_meter, teaSeeds);
	public static Achievement teaPlant = new Achievement("achievement.teastory.teaPlant", "teastory.teaPlant", 2, 1, BlockLoader.teaplant, teaSeeds);
	public static Achievement teaPick = new Achievement("achievement.teastory.teaPick", "teastory.teaPick", 0, 3, ItemLoader.tea_leaf, teaPlant);
	public static Achievement teaTable = new Achievement("achievement.teastory.teaTable", "teastory.teaTable", 3, 5, BlockLoader.tea_table, teaPick);
	public static Achievement excitement = new Achievement("achievement.teastory.excitement", "teastory.excitement", 5, 5, Items.BED, teaTable).setSpecial();
	public static Achievement teaResidue = new Achievement("achievement.teastory.teaResidue", "teastory.teaResidue", 3, 3, ItemLoader.tea_residue, teaTable).setSpecial();
	public static Achievement teaDryingPan = new Achievement("achievement.teastory.teaDryingPan", "teastory.teaDryingPan", -2, 2, BlockLoader.tea_drying_pan, teaPick);
	public static Achievement burntLeaf = new Achievement("achievement.teastory.burntLeaf", "teastory.burntLeaf", -3, -1, Items.FLINT_AND_STEEL, teaDryingPan).setSpecial();
	public static Achievement teaBasket = new Achievement("achievement.teastory.teaBasket", "teastory.teaBasket", -3, 4, BlockLoader.teapan, teaPick);
	public static Achievement driedTea = new Achievement("achievement.teastory.driedTea", "teastory.driedTea", -2, -3, ItemLoader.dried_tea, teaDryingPan);
	public static Achievement greenDrink = new Achievement("achievement.teastory.greenDrink", "teastory.greenDrink", -5, -3, ItemLoader.green_tea, driedTea);
	public static Achievement teaEgg = new Achievement("achievement.teastory.teaEgg", "teastory.teaEgg", 1, -3, ItemLoader.tea_egg, driedTea);
	public static Achievement yellowTea = new Achievement("achievement.teastory.yellowTea", "teastory.yellowTea", -7, 5, ItemLoader.yellow_tea_leaf, teaBasket);
	public static Achievement yellowDrink = new Achievement("achievement.teastory.yellowDrink", "teastory.yellowDrink", -7, 7, ItemLoader.yellow_tea, yellowTea);
	public static Achievement wetTea = new Achievement("achievement.teastory.wetTea", "teastory.wetTea", -4, 1, ItemLoader.wet_tea, teaBasket).setSpecial();
	public static Achievement halfDriedTea = new Achievement("achievement.teastory.halfDriedTea", "teastory.halfDriedTea", -3, 6, ItemLoader.half_dried_tea, teaBasket);
	public static Achievement teaStove = new Achievement("achievement.teastory.teaStove", "teastory.teaStove", 1, 6, BlockLoader.tea_stove, halfDriedTea);
	public static Achievement whiteDrink = new Achievement("achievement.teastory.whiteDrink", "teastory.whiteDrink", 4, 8, ItemLoader.white_tea, teaStove);
	public static Achievement matchaDrink = new Achievement("achievement.teastory.matchaDrink", "teastory.matchaDrink", 6, 6, ItemLoader.matcha_drink, teaStove);
	public static Achievement halfDriedLeafBlock = new Achievement("achievement.teastory.halfDriedLeafBlock", "teastory.halfDriedLeafBlock", -7, 9, BlockLoader.half_dried_leaf_block, halfDriedTea);
	public static Achievement puerTea = new Achievement("achievement.teastory.puerTea", "teastory.puerTea", -7, 12, ItemLoader.puer_tea_leaf, halfDriedLeafBlock);
	public static Achievement puerDrink = new Achievement("achievement.teastory.puerDrink", "teastory.puerDrink", -5, 12, ItemLoader.puer_tea, puerTea);
	public static Achievement oolongTea = new Achievement("achievement.teastory.oolongTea", "teastory.oolongTea", -2, 8, ItemLoader.oolong_tea_leaf, halfDriedTea);
	public static Achievement oolongDrink = new Achievement("achievement.teastory.oolongDrink", "teastory.oolongDrink", 0, 7, ItemLoader.oolong_tea, oolongTea);
	public static Achievement barrel = new Achievement("achievement.teastory.barrel", "teastory.barrel", -3, 13, BlockLoader.barrel, halfDriedTea);
	public static Achievement blackTea = new Achievement("achievement.teastory.blackTea", "teastory.blackTea", 0, 13, ItemLoader.black_tea_leaf, barrel);
	public static Achievement blackDrink = new Achievement("achievement.teastory.blackDrink", "teastory.blackDrink", 0, 9, ItemLoader.black_tea, blackTea);
	public static Achievement lemonDrink = new Achievement("achievement.teastory.lemonDrink", "teastory.lemonDrink", 2, 13, ItemLoader.lemon_tea, blackTea);
	public static Achievement outfire = new Achievement("achievement.teastory.outfire", "teastory.outfire", 2, 11, Items.WATER_BUCKET, lemonDrink).setSpecial();
	public static Achievement milkDrink = new Achievement("achievement.teastory.milkDrink", "teastory.milkDrink", 6, 10, ItemLoader.milk_tea, blackTea);
	public static Achievement cure = new Achievement("achievement.teastory.cure", "teastory.cure", 6, 12, Items.MILK_BUCKET, milkDrink).setSpecial();
	public static Achievement emptyBag = new Achievement("achievement.teastory.emptyBag", "teastory.emptyBag", 5, 1, ItemLoader.empty_tea_bag, null);
	public static Achievement teaBag = new Achievement("achievement.teastory.teaBag", "teastory.teaBag", 5, 3, ItemLoader.green_tea_bag, emptyBag);
	public static Achievement woodenCup = new Achievement("achievement.teastory.woodenCup", "teastory.woodenCup", 10, 5, new ItemStack(ItemLoader.cup, 1, 0), null);
	public static Achievement stoneCup = new Achievement("achievement.teastory.stoneCup", "teastory.stoneCup", 12, 5, new ItemStack(ItemLoader.cup, 1, 2), null);
	public static Achievement glassCup = new Achievement("achievement.teastory.glassCup", "teastory.glassCup", 14, 5, new ItemStack(ItemLoader.cup, 1, 3), null);
	public static Achievement porcelainCup = new Achievement("achievement.teastory.porcelainCup", "teastory.porcelainCup", 10, 7, new ItemStack(ItemLoader.cup, 1, 4), null);
	public static Achievement zishaCup = new Achievement("achievement.teastory.zishaCup", "teastory.zishaCup", 12, 7, new ItemStack(ItemLoader.cup, 1, 5), null);
	public static Achievement hotWater = new Achievement("achievement.teastory.hotWater", "teastory.hotWater", 12, 3, ItemLoader.hw_pot_stone, null);
	public static Achievement kettle = new Achievement("achievement.teastory.kettle", "teastory.kettle", 10, 9, BlockLoader.empty_porcelain_kettle, null);
	public static Achievement getDrink = new Achievement("achievement.teastory.getDrink", "teastory.getDrink", 13, 9, BlockLoader.green_tea_porcelain_kettle, kettle).setSpecial();
	
	public static Achievement riceSeeds = new Achievement("achievement.teastory.riceSeeds", "teastory.riceSeeds", 13, 0, ItemLoader.rice_seeds, null);
	public static Achievement transplanting = new Achievement("achievement.teastory.transplanting", "teastory.transplanting", 11, -3, ItemLoader.rice_seedlings, riceSeeds);
	public static Achievement harvest = new Achievement("achievement.teastory.harvest", "teastory.harvest", 9, -2, ItemLoader.rice, transplanting);
	public static Achievement cook = new Achievement("achievement.teastory.cook", "teastory.cook", 5, -4, ItemLoader.rice_ball, harvest);
	public static Achievement sickle = new Achievement("achievement.teastory.sickle", "teastory.sickle", 9, 0, ItemLoader.sickle, transplanting).setSpecial();
	public static Achievement strawBlanket = new Achievement("achievement.teastory.strawBlanket", "teastory.strawBlanket", 6, -6, ItemLoader.straw_blanket, harvest);
	public static Achievement strawCushion = new Achievement("achievement.teastory.strawCushion", "teastory.strawCushion", 10, -6, BlockLoader.straw_cushion, harvest);
	
	public static AchievementPage pageTeaStory = new AchievementPage(TeaStory.NAME, teaSeeds, soilDetectionMeter, teaPlant, teaPick, teaTable, excitement, teaResidue, teaDryingPan, burntLeaf, teaBasket, 
			driedTea, greenDrink, teaEgg, yellowTea, yellowDrink, wetTea, halfDriedTea, teaStove, whiteDrink, matchaDrink, halfDriedLeafBlock, puerTea, puerDrink, oolongTea, oolongDrink, barrel, blackTea, blackDrink, 
			lemonDrink, outfire, milkDrink, cure, emptyBag, teaBag, woodenCup, stoneCup, glassCup, porcelainCup, zishaCup, hotWater, kettle, getDrink, riceSeeds, transplanting, harvest, cook, sickle, strawBlanket, strawCushion);
	
	public AchievementLoader()
	{
		teaSeeds.registerStat();
		soilDetectionMeter.registerStat();
		teaPlant.registerStat();
		teaPick.registerStat();
		teaTable.registerStat();
		excitement.registerStat();
		teaResidue.registerStat();
		teaDryingPan.registerStat();
		burntLeaf.registerStat();
		teaBasket.registerStat();
		driedTea.registerStat();
		greenDrink.registerStat();
		teaEgg.registerStat();
		yellowTea.registerStat();
		yellowDrink.registerStat();
		wetTea.registerStat();
		halfDriedTea.registerStat();
		teaStove.registerStat();
		whiteDrink.registerStat();
		matchaDrink.registerStat();
		halfDriedLeafBlock.registerStat();
		puerTea.registerStat();
		puerDrink.registerStat();
		oolongTea.registerStat();
		oolongDrink.registerStat();
		barrel.registerStat();
		blackTea.registerStat();
		blackDrink.registerStat();
		lemonDrink.registerStat();
		outfire.registerStat();
		milkDrink.registerStat();
		cure.registerStat();
		emptyBag.registerStat();
		teaBag.registerStat();
		woodenCup.registerStat();
		stoneCup.registerStat();
		glassCup.registerStat();
		porcelainCup.registerStat();
		zishaCup.registerStat();
		hotWater.registerStat();
		kettle.registerStat();
		getDrink.registerStat();
		riceSeeds.registerStat();
		transplanting.registerStat();
		harvest.registerStat();
		cook.registerStat();
		sickle.registerStat();
		strawBlanket.registerStat();
		strawCushion.registerStat();
		AchievementPage.registerAchievementPage(pageTeaStory);
	}
}
