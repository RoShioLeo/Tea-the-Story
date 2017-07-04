package cateam.teastory.crafting;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.ConfigLoader;
import cateam.teastory.item.ItemLoader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.*;
import net.minecraftforge.common.ForgeHooks;
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
    	GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.matcha, 1), new Object[]{ItemLoader.matcha_leaf, new ItemStack(ItemLoader.mortar_and_pestle, 1, 32767)});
    	GameRegistry.addShapelessRecipe(new ItemStack(BlockLoader.empty_kettle, 1, 4), new Object[]{Items.WATER_BUCKET, new ItemStack(BlockLoader.empty_kettle, 1, 0)});
    	for (int i = 0; i < 4; ++i)
        {
    		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.matcha_drink, 1, i), new Object[]{new ItemStack(ItemLoader.hot_water, 1, i), ItemLoader.matcha, ItemLoader.matcha, ItemLoader.matcha});
        }
    	for (int i = 0; i < 4; ++i)
        {
    		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.green_tea, 1, i), new Object[]{new ItemStack(ItemLoader.hot_water, 1, i), new ItemStack(ItemLoader.tea_bag, 1, 0)});
        }
    	for (int i = 0; i < 4; ++i)
        {
    		GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.black_tea, 1, i), new Object[]{new ItemStack(ItemLoader.hot_water, 1, i), new ItemStack(ItemLoader.tea_bag, 1, 1)});
        }
    	GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.tea_egg, 1), new Object[]{new ItemStack(ItemLoader.dried_tea, 1), new ItemStack(ItemLoader.dried_tea, 1), new ItemStack(ItemLoader.dried_tea, 1), new ItemStack(Items.EGG, 1)});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.broken_tea, 6), new Object[]
    	        {"###", "###", "###", '#', "treeLeaves"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.tea_leaf, 1), new Object[]
    	        {"###", "#*#", "###", '#',"treeLeaves", '*', ItemLoader.sieve.setContainerItem(ItemLoader.sieve)}));
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.tea_leaf, 1), new Object[]
    	        {"###", "###", "###", '#', ItemLoader.broken_tea});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.mortar_and_pestle, 1), new Object[]
    	        {"#", "*", "+", '#', "stickWood", '*',"plankWood", '+',Items.BOWL}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.teapan, 1, 0), new Object[]
    	        {"#*#", "***", "#*#", '#', "plankWood", '*', "stickWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.teapan, 1, 0), new Object[]
    	        {"*#*", "#*#", "*#*", '#', "plankWood", '*', "stickWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.barrel, 1, 0), new Object[]
    	        {"# #", "***", "###", '#', "plankWood", '*', "ingotIron"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.sieve, 1), new Object[]
    	        {"#*#", "***", "#*#", '#', "stickWood", '*',Items.STRING}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.sieve, 1), new Object[]
    	        {"*#*", "#*#", "*#*", '#', "stickWood", '*',Items.STRING}));
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.burntgreentea_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.burnt_tea, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.greentea_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.dried_tea, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.matcha_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.matcha, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.blacktea_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.black_tea_leaf, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.clay_kettle, 1, 0), new Object[]
    	        {" # ", "#*#", "###", '#', Blocks.CLAY, '*', Items.BUCKET});
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.clay_cup, 1), new Object[]
    			{"#+#", "*#*", '#', Blocks.CLAY, '*', Items.CLAY_BALL, '+', new ItemStack(ItemLoader.cup, 1, 2)});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.cup, 1, 0), new Object[]
    	        {"# #", "*#*", '#', "plankWood", '*', "stickWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.cup, 1, 1), new Object[]
    	        {"#+#", "*#*", '#', "stone", '*', "cobblestone", '+', new ItemStack(ItemLoader.cup, 1, 0)}));
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.cup, 1, 2), new Object[]
    	        {"#+#", "*#*", '#', Blocks.GLASS, '*', Blocks.GLASS_PANE, '+', new ItemStack(ItemLoader.cup, 1, 1)});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.tea_drying_pan, 1), new Object[]
    	        {"# #", "###", "***", '#', "ingotIron", '*', "logWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.tea_stove, 1), new Object[]
    	        {"#+#", "#-#", "#*#", '#', "cobblestone", '*', Blocks.FURNACE, '+', "gemQuartz", '-', BlockLoader.tea_drying_pan}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.soil_detection_meter, 1), new Object[]
    	        {"#+#", "#-#", "*#*", '#', "cobblestone", '+', "dustRedstone", '*', "gemQuartz", '-', "treeLeaves"}));
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.tea_bag, 1, 0), new Object[]
    	        {"###", "***", "###", '#', Items.PAPER, '*', ItemLoader.dried_tea});
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.tea_bag, 1, 1), new Object[]
    	        {"###", "***", "###", '#', Items.PAPER, '*', ItemLoader.black_tea_leaf});
    }
    
    private static void registerSmelting()
    {
    	GameRegistry.addSmelting(ItemLoader.wet_tea, new ItemStack(ItemLoader.tea_leaf), 0.1f);
    	GameRegistry.addSmelting(ItemLoader.tea_leaf, new ItemStack(ItemLoader.burnt_tea), 0.1f);
    	for (int i = 0; i < 4; ++i)
        {
    	    GameRegistry.addSmelting(new ItemStack(ItemLoader.cold_water, 1, i), new ItemStack(ItemLoader.hot_water, 1, i), 0.1f);
        }
        GameRegistry.addSmelting(new ItemStack(BlockLoader.empty_kettle, 1, 4), new ItemStack(BlockLoader.empty_kettle, 1, 12), 0.1f);
        GameRegistry.addSmelting(new ItemStack(BlockLoader.clay_kettle, 1, 0), new ItemStack(BlockLoader.empty_kettle, 1, 0), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemLoader.clay_cup, 1), new ItemStack(ItemLoader.cup, 1, 3), 0.1f);
    }

    private static void registerFuel()
    {
    }
}