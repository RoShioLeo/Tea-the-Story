package starryskyline.teastory.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.*;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import starryskyline.teastory.block.BlockLoader;
import starryskyline.teastory.common.ConfigLoader;
import starryskyline.teastory.item.ItemLoader;

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
    	GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.sieve, 1), new Object[]{new ItemStack(ItemLoader.brush, 1, 32767), new ItemStack(ItemLoader.dirty_sieve, 1, 32767)});
    	GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.matcha, 1), new Object[]{ItemLoader.matcha_leaf, new ItemStack(ItemLoader.mortar_and_pestle, 1, 32767)});
    	GameRegistry.addShapelessRecipe(new ItemStack(BlockLoader.empty_kettle, 1, 4), new Object[]{Items.water_bucket, new ItemStack(BlockLoader.empty_kettle, 1, 0)});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.tea_leaf, 1), new Object[]
    	        {"###", "###", "###", '#', "treeLeaves"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.tea_leaf, 3), new Object[]
    	        {"###", "#*#", "###", '#',"treeLeaves", '*', new ItemStack(ItemLoader.sieve.setContainerItem(ItemLoader.dirty_sieve), 1, 32767)}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.brush, 1), new Object[]
    	        {"#", "*", '#', Items.string, '*', "stickWood"}));
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.tea_leaf, 1), new Object[]
    	        {"###", "###", "###", '#', ItemLoader.broken_tea});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.mortar_and_pestle, 1), new Object[]
    	        {"#", "*", "+", '#', "stickWood", '*',"plankWood", '+',Items.bowl}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.teapan, 1, 0), new Object[]
    	        {"#*#", "***", "#*#", '#', "plankWood", '*', "stickWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.teapan, 1, 0), new Object[]
    	        {"*#*", "#*#", "*#*", '#', "plankWood", '*', "stickWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.barrel, 1, 0), new Object[]
    	        {"# #", "***", "###", '#', "plankWood", '*', "ingotIron"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.sieve, 1), new Object[]
    	        {"#*#", "***", "#*#", '#', "stickWood", '*',Items.string}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.sieve, 1), new Object[]
    	        {"*#*", "#*#", "*#*", '#', "stickWood", '*',Items.string}));
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.burntgreentea_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.burnt_tea, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.greentea_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.dried_tea, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.matcha_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.matcha, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.blacktea_kettle, 1, 0), new Object[]
    	        {"###", "#*#", "###", '#', ItemLoader.black_tea_leaf, '*',new ItemStack(BlockLoader.empty_kettle, 1, 12)});
    	GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.clay_kettle, 1, 0), new Object[]
    	        {" # ", "#*#", "###", '#', Blocks.clay, '*', Items.bucket});
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.clay_cup, 1), new Object[]
    			{"#+#", "*#*", '#', Blocks.clay, '*', Items.clay_ball, '+', new ItemStack(ItemLoader.cup, 1, 2)});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.cup, 1, 0), new Object[]
    	        {"# #", "*#*", '#', "plankWood", '*', "stickWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.cup, 1, 1), new Object[]
    	        {"#+#", "*#*", '#', "stone", '*', "cobblestone", '+', new ItemStack(ItemLoader.cup, 1, 0)}));
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.cup, 1, 2), new Object[]
    	        {"#+#", "*#*", '#', Blocks.glass, '*', Blocks.glass_pane, '+', new ItemStack(ItemLoader.cup, 1, 1)});
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.tea_drying_pan, 1), new Object[]
    	        {"# #", "###", "***", '#', "ingotIron", '*', "logWood"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockLoader.tea_stove, 1), new Object[]
    	        {"#+#", "#-#", "#*#", '#', "cobblestone", '*', Blocks.furnace, '+', "gemQuartz", '-', BlockLoader.tea_drying_pan}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.soil_detection_meter, 1), new Object[]
    	        {"#+#", "#-#", "*#*", '#', "cobblestone", '+', "dustRedstone", '*', "gemQuartz", '-', "treeLeaves"}));
    }
    
    private static void registerSmelting()
    {
    	GameRegistry.addSmelting(ItemLoader.wet_tea, new ItemStack(ItemLoader.tea_leaf), 0.1f);
    	GameRegistry.addSmelting(ItemLoader.tea_leaf, new ItemStack(ItemLoader.burnt_tea), 0.1f);
        GameRegistry.addSmelting(new ItemStack(BlockLoader.empty_kettle, 1, 4), new ItemStack(BlockLoader.empty_kettle, 1, 12), 0.1f);
        GameRegistry.addSmelting(new ItemStack(BlockLoader.clay_kettle, 1, 0), new ItemStack(BlockLoader.empty_kettle, 1, 0), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemLoader.clay_cup, 1), new ItemStack(ItemLoader.cup, 1, 3), 0.1f);
    }

    private static void registerFuel()
    {
    }
}