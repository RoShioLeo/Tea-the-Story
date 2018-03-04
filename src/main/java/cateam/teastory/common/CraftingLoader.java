package cateam.teastory.common;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.recipe.TeaStoveRecipe;
import cateam.teastory.recipe.TeaStoveRecipeManger;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingLoader
{
	public CraftingLoader()
	{
		registerRecipe();
		registerSmelting();
		registerFuel();
	}

	private static void registerRecipe()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.matcha_powder), new Object[]{ItemLoader.matcha_leaf, new ItemStack(ItemLoader.wooden_mortar_and_pestle, 1, 32767)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.rice), new Object[]{new ItemStack(ItemLoader.rice_seeds), new ItemStack(ItemLoader.wooden_mortar_and_pestle, 1, 32767)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.tea_egg), new Object[]{new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.dried_tea), new ItemStack(Items.EGG)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.chicken_rice), new Object[]{new ItemStack(Items.COOKED_CHICKEN), new ItemStack(ItemLoader.rice_ball), new ItemStack(Items.BOWL)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.porkchop_rice), new Object[]{new ItemStack(Items.COOKED_PORKCHOP), new ItemStack(ItemLoader.rice_ball), new ItemStack(Items.BOWL)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.potato_rice), new Object[]{new ItemStack(Items.BAKED_POTATO), new ItemStack(ItemLoader.rice_ball), new ItemStack(Items.BOWL)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.rabbit_rice), new Object[]{new ItemStack(Items.COOKED_RABBIT), new ItemStack(ItemLoader.rice_ball), new ItemStack(Items.BOWL)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.steak_rice), new Object[]{new ItemStack(Items.COOKED_BEEF), new ItemStack(ItemLoader.rice_ball), new ItemStack(Items.BOWL)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.green_tea_bag), new Object[]{new ItemStack(ItemLoader.empty_tea_bag), new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.dried_tea), new ItemStack(ItemLoader.dried_tea)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.black_tea_bag), new Object[]{new ItemStack(ItemLoader.empty_tea_bag), new ItemStack(ItemLoader.black_tea_leaf), new ItemStack(ItemLoader.black_tea_leaf), new ItemStack(ItemLoader.black_tea_leaf), new ItemStack(ItemLoader.black_tea_leaf), new ItemStack(ItemLoader.black_tea_leaf), new ItemStack(ItemLoader.black_tea_leaf)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.yellow_tea_bag), new Object[]{new ItemStack(ItemLoader.empty_tea_bag), new ItemStack(ItemLoader.yellow_tea_leaf), new ItemStack(ItemLoader.yellow_tea_leaf), new ItemStack(ItemLoader.yellow_tea_leaf), new ItemStack(ItemLoader.yellow_tea_leaf), new ItemStack(ItemLoader.yellow_tea_leaf), new ItemStack(ItemLoader.yellow_tea_leaf)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.white_tea_bag), new Object[]{new ItemStack(ItemLoader.empty_tea_bag), new ItemStack(ItemLoader.white_tea_leaf), new ItemStack(ItemLoader.white_tea_leaf), new ItemStack(ItemLoader.white_tea_leaf), new ItemStack(ItemLoader.white_tea_leaf), new ItemStack(ItemLoader.white_tea_leaf), new ItemStack(ItemLoader.white_tea_leaf)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.oolong_tea_bag), new Object[]{new ItemStack(ItemLoader.empty_tea_bag), new ItemStack(ItemLoader.oolong_tea_leaf), new ItemStack(ItemLoader.oolong_tea_leaf), new ItemStack(ItemLoader.oolong_tea_leaf), new ItemStack(ItemLoader.oolong_tea_leaf), new ItemStack(ItemLoader.oolong_tea_leaf), new ItemStack(ItemLoader.oolong_tea_leaf)});
		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.puer_tea_bag), new Object[]{new ItemStack(ItemLoader.empty_tea_bag), new ItemStack(ItemLoader.puer_tea_leaf), new ItemStack(ItemLoader.puer_tea_leaf), new ItemStack(ItemLoader.puer_tea_leaf), new ItemStack(ItemLoader.puer_tea_leaf), new ItemStack(ItemLoader.puer_tea_leaf), new ItemStack(ItemLoader.puer_tea_leaf)});
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.broken_tea, 3), new Object[]
				{"###", "###", "###", '#', "treeLeaves"}));
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.tea_leaf), new Object[]
				{"###", "###", "###", '#', ItemLoader.broken_tea});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.wooden_mortar_and_pestle), new Object[]
				{"#", "*", "+", '#', "stickWood", '*',"plankWood", '+',Items.BOWL}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.teapan), new Object[]
				{"#*#", "***", "#*#", '#', "plankWood", '*', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.teapan), new Object[]
				{"*#*", "#*#", "*#*", '#', "plankWood", '*', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.barrel), new Object[]
				{"# #", "***", "###", '#', "plankWood", '*', "ingotIron"}));
		GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.clay_kettle), new Object[]
				{" # ", "# #", "###", '#', ItemLoader.zisha_clay});
		GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.zisha_clay_kettle), new Object[]
				{" # ", "# #", "###", '#', Blocks.CLAY});
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.clay_cup), new Object[]
				{"# #", "*#*", '#', Blocks.CLAY, '*', Items.CLAY_BALL});
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.zisha_clay_cup), new Object[]
				{"# #", "*#*", '#', ItemLoader.zisha_clay, '*', Items.CLAY_BALL});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.cup, 1, 0), new Object[]
				{"# #", "*#*", '#', "plankWood", '*', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.cup, 1, 2), new Object[]
				{"# #", "*#*", '#', "stone", '*', "cobblestone"}));
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.cup, 1, 3), new Object[]
				{"# #", "*#*", '#', Blocks.GLASS, '*', Blocks.GLASS_PANE});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.tea_drying_pan), new Object[]
				{"# #", "###", "***", '#', "ingotIron", '*', "logWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.tea_stove), new Object[]
				{"#+#", "#-#", "#*#", '#', "cobblestone", '*', Blocks.FURNACE, '+', "gemQuartz", '-', BlockLoader.tea_drying_pan}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.soil_detection_meter), new Object[]
				{"#+#", "#-#", "*#*", '#', "cobblestone", '+', "dustRedstone", '*', "gemQuartz", '-', BlockLoader.half_dried_leaf_block}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.sickle), new Object[]
				{" ##", "# *", "  *", '#', "ingotIron", '*', "stickWood"}));
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.zisha_clay), new Object[]
				{"*+*", "+#+", "*+*", '#', new ItemStack(Blocks.DIRT, 1, 1), '*', Items.CLAY_BALL, '#', new ItemStack(Blocks.SAND, 1, 1)});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.matcha_cookie, 8), new Object[]
				{"#*#", '#', "cropWheat", '*', ItemLoader.matcha_powder}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.wooden_lid), new Object[]
				{" * ", "###", '#', "slabWood", '*', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.straw_rope), new Object[]
				{"#  ", " # ", "  #", '#', "cropStraw"}));
		GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.straw_cushion), new Object[]
				{"###", "###", "###", '#', ItemLoader.straw_rope});
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.straw_blanket), new Object[]
				{"###", "###", '#', ItemLoader.straw_rope});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.watering_hoe), new Object[]
				{"###", "*+*", "###", '#', "gemLapis", '*', Items.WATER_BUCKET, '+', Items.IRON_HOE}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.pot_stone), new Object[]
				{"# #", "# #", " * ", '#', "cobblestone", '*', "stone"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.pot_iron), new Object[]
				{"# #", "#+#", " * ", '#', "ingotIron", '*', "blockIron", '+', ItemLoader.pot_stone}));
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.pot_clay), new Object[]
				{"# #", "#+#", " # ", '#', Blocks.CLAY, '+', ItemLoader.pot_iron});
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.pot_zisha_clay), new Object[]
				{"# #", "#+#", " # ", '#', ItemLoader.zisha_clay, '+', ItemLoader.pot_porcelain});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.empty_tea_bag, 3), new Object[]
				{" * ", "# #", "###", '#', "paper", '*', "string"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.tea_whisk), new Object[]
				{"## ", "## ", "  #", '#', "stickWood"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.tea_table), new Object[]
				{"+*-", "###", '#', "plankWood", '+', new ItemStack(ItemLoader.cup), '*', new ItemStack(BlockLoader.empty_porcelain_kettle), '-', new ItemStack(ItemLoader.pot_stone)}));
	}

	private static void registerSmelting()
	{
		GameRegistry.addSmelting(ItemLoader.wet_tea, new ItemStack(ItemLoader.tea_leaf), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockLoader.clay_kettle), new ItemStack(BlockLoader.empty_porcelain_kettle), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockLoader.zisha_clay_kettle), new ItemStack(BlockLoader.empty_zisha_kettle), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.clay_cup), new ItemStack(ItemLoader.cup, 1, 4), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.zisha_clay_cup), new ItemStack(ItemLoader.cup, 1, 5), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.pot_clay), new ItemStack(ItemLoader.pot_porcelain), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.pot_zisha_clay), new ItemStack(ItemLoader.pot_zisha), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_stone), new ItemStack(ItemLoader.hw_pot_stone), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_iron), new ItemStack(ItemLoader.hw_pot_iron), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_porcelain), new ItemStack(ItemLoader.hw_pot_porcelain), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_zisha), new ItemStack(ItemLoader.hw_pot_zisha), 0.1f);
	}

	private static void registerFuel()
	{
		GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
            	if (ItemLoader.straw.equals(fuel.getItem()))
            	{
            		return 200;
            	}
            	else if (ItemLoader.straw_rope.equals(fuel.getItem()))
            	{
            		return 400;
            	}
            	else if (ItemLoader.straw_blanket.equals(fuel.getItem()))
            	{
            		return 800;
            	}
            	else if (Item.getItemFromBlock(BlockLoader.straw_cushion).equals(fuel.getItem()))
            	{
            		return 800;
            	}
            	return 0;
            }
        });
	}
}