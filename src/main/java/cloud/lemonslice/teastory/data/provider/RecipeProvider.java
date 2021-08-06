package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry;
import cloud.lemonslice.teastory.data.builder.BambooTrayRecipeBuilder;
import cloud.lemonslice.teastory.data.builder.DrinkRecipeBuilder;
import cloud.lemonslice.teastory.data.builder.StoneMillRecipeBuilder;
import cloud.lemonslice.teastory.data.builder.StoneRollerRecipeBuilder;
import cloud.lemonslice.teastory.data.tag.NormalTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public final class RecipeProvider extends net.minecraft.data.RecipeProvider
{
    public RecipeProvider(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        // Special Custom Recipes 自定义特殊配方
        CustomRecipeBuilder.customRecipe(RecipeSerializerRegistry.CRAFTING_SPECIAL_FLOWERDYE.get()).build(consumer, "teastory:flower_dye");

        // Decoration Recipes 装饰品配方
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.BAMBOO_PLANK).key('x', Items.BAMBOO).patternLine("xx").patternLine("xx").setGroup("bamboo_plank").addCriterion("has_bamboo", hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_DOOR, 3).key('x', ItemRegistry.BAMBOO_PLANK).patternLine("xx").patternLine("xx").patternLine("xx").setGroup("bamboo_door").addCriterion("has_bamboo_plank", hasItem(ItemRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_GLASS_DOOR, 3).key('x', ItemRegistry.BAMBOO_PLANK).key('#', Tags.Items.GLASS_COLORLESS).patternLine("##").patternLine("xx").patternLine("xx").setGroup("bamboo_glass_door").addCriterion("has_bamboo_plank", hasItem(ItemRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_CHAIR).key('x', ItemRegistry.BAMBOO_PLANK).key('#', Items.BAMBOO).patternLine("  #").patternLine("xxx").patternLine("# #").setGroup("bamboo_chair").addCriterion("has_bamboo_plank", hasItem(ItemRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_LANTERN).key('x', Blocks.TORCH).key('#', Items.BAMBOO).patternLine("###").patternLine("#x#").patternLine("###").setGroup("bamboo_lantern").addCriterion("has_bamboo", hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_TABLE).key('x', ItemRegistry.BAMBOO_PLANK).key('#', Items.BAMBOO).patternLine("xxx").patternLine("# #").patternLine("# #").setGroup("bamboo_table").addCriterion("has_bamboo_plank", hasItem(ItemRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_TRAY).key('x', ItemRegistry.BAMBOO_PLANK).key('#', Items.BAMBOO).patternLine("# #").patternLine("#x#").setGroup("bamboo_tray").addCriterion("has_bamboo_plank", hasItem(ItemRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.WOODEN_FRAME).key('x', Tags.Items.RODS_WOODEN).key('#', ItemTags.PLANKS).patternLine("#x#").patternLine("x#x").patternLine("x x").setGroup("wooden_frame").addCriterion("has_plank", hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.FRESH_BAMBOO_WALL, 2).key('x', Items.BAMBOO).key('#', Tags.Items.STRING).patternLine("xxx").patternLine("###").patternLine("xxx").setGroup("fresh_bamboo_wall").addCriterion("has_bamboo", hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.WOODEN_TABLE).key('x', ItemTags.PLANKS).key('#', Tags.Items.RODS_WOODEN).patternLine("xxx").patternLine("# #").patternLine("# #").setGroup("wooden_table").addCriterion("has_plank", hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.WOODEN_CHAIR).key('x', ItemTags.PLANKS).key('#', Tags.Items.RODS_WOODEN).patternLine("x  ").patternLine("xxx").patternLine("# #").setGroup("wooden_chair").addCriterion("has_plank", hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.STONE_TABLE).key('x', Blocks.STONE).key('#', Blocks.COBBLESTONE_WALL).patternLine("xxx").patternLine("# #").patternLine("# #").setGroup("stone_table").addCriterion("has_stone", hasItem(Blocks.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.STONE_CHAIR).key('x', Blocks.STONE).key('#', Blocks.COBBLESTONE_WALL).patternLine("xxx").patternLine("# #").setGroup("stone_chair").addCriterion("has_stone", hasItem(Blocks.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.WOODEN_TRAY).key('#', Tags.Items.RODS_WOODEN).patternLine("# #").patternLine("###").setGroup("wooden_tray").addCriterion("has_rod", hasItem(Tags.Items.RODS_WOODEN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_LATTICE, 2).key('x', Items.BAMBOO).patternLine("x x").patternLine(" x ").patternLine("x x").setGroup("bamboo_lattice").addCriterion("has_bamboo", hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.SCARECROW).key('#', NormalTags.Items.CROPS_STRAW).key('*', BlockRegistry.DRY_HAYSTACK).key('/', Tags.Items.RODS_WOODEN).patternLine(" # ").patternLine("/*/").patternLine(" / ").setGroup("scarecrow").addCriterion("has_haystack", hasItem(BlockRegistry.DRY_HAYSTACK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.WET_HAYSTACK).key('#', ItemRegistry.WET_STRAW).patternLine(" # ").patternLine("###").patternLine("###").setGroup("haystack").addCriterion("has_straw", hasItem(ItemRegistry.WET_STRAW)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.DRY_HAYSTACK).key('#', NormalTags.Items.CROPS_STRAW).patternLine(" # ").patternLine("###").patternLine("###").setGroup("haystack").addCriterion("has_straw", hasItem(NormalTags.Items.CROPS_STRAW)).build(consumer);

        // Drink Ingredient Recipes 茶饮配料配方
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.EMPTY_TEA_BAG, 3).key('/', Items.STRING).key('x', Items.PAPER).patternLine(" / ").patternLine("xxx").patternLine("xxx").setGroup("empty_tea_bag").addCriterion("has_paper", hasItem(Items.PAPER)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemRegistry.BLACK_TEA_BAG).addIngredient(ItemRegistry.EMPTY_TEA_BAG).addIngredient(Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), 3).setGroup("tea_bag").addCriterion("has_tea_bag", hasItem(ItemRegistry.EMPTY_TEA_BAG)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemRegistry.GREEN_TEA_BAG).addIngredient(ItemRegistry.EMPTY_TEA_BAG).addIngredient(Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), 3).setGroup("tea_bag").addCriterion("has_tea_bag", hasItem(ItemRegistry.EMPTY_TEA_BAG)).build(consumer);

        // Tea Set Recipes 茶具配方
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.BOTTLE).key('x', Tags.Items.NUGGETS_IRON).key('#', Tags.Items.GLASS_PANES_COLORLESS).patternLine(" x ").patternLine("# #").patternLine("###").setGroup("bottle").addCriterion("has_glass_pane", hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.CLAY_CUP).key('x', Items.CLAY_BALL).patternLine("x x").patternLine(" x ").setGroup("clay_cup").addCriterion("has_clay_ball", hasItem(Items.CLAY_BALL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.CLAY_TEAPOT).key('x', Blocks.CLAY).patternLine("x x").patternLine(" x ").setGroup("clay_teapot").addCriterion("has_clay_ball", hasItem(Items.CLAY_BALL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.IRON_KETTLE).key('*', Items.BUCKET).key('x', Tags.Items.INGOTS_IRON).patternLine(" x ").patternLine("x*x").patternLine("xxx").setGroup("iron_kettle").addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);

        // Craft Block Recipes 工艺方块配方
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.DIRT_STOVE).key('x', NormalTags.Items.DIRT).patternLine("xxx").patternLine("x x").patternLine("xxx").setGroup("stove").addCriterion("has_dirt", hasItem(NormalTags.Items.DIRT)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.STONE_STOVE).key('x', Tags.Items.STONE).key('#', BlockRegistry.DIRT_STOVE).patternLine("xxx").patternLine("x#x").patternLine("xxx").setGroup("stove").addCriterion("has_dirt", hasItem(NormalTags.Items.DIRT)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.DRINK_MAKER).key('x', ItemTags.PLANKS).key('#', Tags.Items.RODS_WOODEN).patternLine("# #").patternLine("xxx").setGroup("drink_maker").addCriterion("has_planks", hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.FILTER_SCREEN).key('x', Tags.Items.RODS_WOODEN).key('#', Tags.Items.STRING).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("x#x").patternLine("#*#").patternLine("x#x").setGroup("filter_screen").addCriterion("has_string", hasItem(Tags.Items.STRING)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BAMBOO_CATAPULT_BOARD).key('x', ItemRegistry.BAMBOO_PLANK).key('#', Tags.Items.STONE).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("xxx").patternLine("xxx").patternLine("#*#").setGroup("catapult_board").addCriterion("has_bamboo_plank", hasItem(ItemRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.IRON_CATAPULT_BOARD).key('x', Tags.Items.INGOTS_IRON).key('#', Tags.Items.STONE).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("xxx").patternLine("xxx").patternLine("#*#").setGroup("catapult_board").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.STONE_CATAPULT_BOARD).key('x', Blocks.STONE).key('#', Tags.Items.STONE).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("xxx").patternLine("xxx").patternLine("#*#").setGroup("catapult_board").addCriterion("has_stone", hasItem(Blocks.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.OAK_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.OAK_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", hasItem(Blocks.OAK_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.BIRCH_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.BIRCH_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", hasItem(Blocks.BIRCH_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.SPRUCE_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.SPRUCE_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", hasItem(Blocks.SPRUCE_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.JUNGLE_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.JUNGLE_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", hasItem(Blocks.JUNGLE_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.DARK_OAK_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.DARK_OAK_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", hasItem(Blocks.DARK_OAK_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.ACACIA_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.ACACIA_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", hasItem(Blocks.ACACIA_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.STONE_MILL).key('#', Blocks.SMOOTH_STONE_SLAB).key('*', Blocks.SMOOTH_STONE).key('/', Tags.Items.RODS_WOODEN).patternLine(" / ").patternLine(" * ").patternLine("###").setGroup("stone_mill").addCriterion("has_smooth_stone", hasItem(Blocks.SMOOTH_STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.STONE_ROLLER).key('#', Blocks.SMOOTH_STONE_SLAB).key('*', Blocks.SMOOTH_STONE).key('/', Tags.Items.RODS_WOODEN).key('+', BlockRegistry.WOODEN_FRAME).patternLine(" / ").patternLine(" +*").patternLine("###").setGroup("stone_roller").addCriterion("has_smooth_stone", hasItem(Blocks.SMOOTH_STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.SAUCEPAN).key('#', Tags.Items.INGOTS_IRON).key('*', Items.BUCKET).patternLine(" # ").patternLine("#*#").patternLine("###").setGroup("saucepan").addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegistry.WOODEN_BARREL).key('#', ItemTags.PLANKS).key('*', ItemTags.WOODEN_SLABS).patternLine("* *").patternLine("# #").patternLine("###").setGroup("wooden_barrel").addCriterion("has_planks", hasItem(ItemTags.PLANKS)).build(consumer);

        // Tool & Ingredient Recipes 工具和原料配方
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.WOODEN_AQUEDUCT_SHOVEL).key('#', Items.WOODEN_SHOVEL).key('*', ItemTags.PLANKS).patternLine(" * ").patternLine(" # ").setGroup("aqueduct_shovel").addCriterion("has_shovel", hasItem(Items.WOODEN_SHOVEL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.STONE_AQUEDUCT_SHOVEL).key('#', Items.STONE_SHOVEL).key('*', Tags.Items.COBBLESTONE).patternLine(" * ").patternLine(" # ").setGroup("aqueduct_shovel").addCriterion("has_shovel", hasItem(Items.STONE_SHOVEL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.GOLD_AQUEDUCT_SHOVEL).key('#', Items.GOLDEN_SHOVEL).key('*', Tags.Items.INGOTS_GOLD).patternLine(" * ").patternLine(" # ").setGroup("aqueduct_shovel").addCriterion("has_shovel", hasItem(Items.GOLDEN_SHOVEL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.IRON_AQUEDUCT_SHOVEL).key('#', Items.IRON_SHOVEL).key('*', Tags.Items.INGOTS_IRON).patternLine(" * ").patternLine(" # ").setGroup("aqueduct_shovel").addCriterion("has_shovel", hasItem(Items.IRON_SHOVEL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.DIAMOND_AQUEDUCT_SHOVEL).key('#', Items.DIAMOND_SHOVEL).key('*', Tags.Items.GEMS_DIAMOND).patternLine(" * ").patternLine(" # ").setGroup("aqueduct_shovel").addCriterion("has_shovel", hasItem(Items.DIAMOND_SHOVEL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.IRON_SICKLE).key('/', Tags.Items.RODS_WOODEN).key('*', Tags.Items.INGOTS_IRON).patternLine("** ").patternLine("*/ ").patternLine(" / ").setGroup("sickle").addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.HONEYCOMB_BRIQUETTE).key('#', ItemTags.COALS).key('*', Items.CLAY_BALL).patternLine("# #").patternLine(" # ").patternLine("* *").setGroup("honeycomb_briquette").addCriterion("has_coal", hasItem(ItemTags.COALS)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemRegistry.WET_STRAW, 7).addIngredient(BlockRegistry.WET_HAYSTACK).setGroup("wet_straw").addCriterion("has_wet_haystack", hasItem(BlockRegistry.WET_HAYSTACK)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemRegistry.DRY_STRAW, 7).addIngredient(BlockRegistry.DRY_HAYSTACK).setGroup("dry_straw").addCriterion("has_dry_haystack", hasItem(BlockRegistry.DRY_HAYSTACK)).build(consumer);

        // Smelting Recipes 熔炼配方
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Items.WATER_BUCKET), FluidRegistry.BOILING_WATER_BUCKET.get(), 0.2F, 200).addCriterion("has_water_bucket", hasItem(Items.WATER_BUCKET)).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemRegistry.CLAY_CUP), ItemRegistry.PORCELAIN_CUP, 0.2F, 200).addCriterion("has_clay_cup", hasItem(ItemRegistry.CLAY_CUP)).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemRegistry.CLAY_TEAPOT), ItemRegistry.PORCELAIN_TEAPOT, 0.2F, 200).addCriterion("has_clay_teapot", hasItem(ItemRegistry.CLAY_TEAPOT)).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Items.BAMBOO), ItemRegistry.BAMBOO_CHARCOAL, 0.2F, 200).addCriterion("has_bamboo", hasItem(Items.BAMBOO)).build(consumer);

        // Bamboo Tray In-rain Recipes 竹篾匾淋雨配方
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemRegistry.RABBIT_JERKY), Items.RABBIT, 200).build(consumer, "teastory:rabbit");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemRegistry.PORK_JERKY), Items.PORKCHOP, 200).build(consumer, "teastory:porkchop");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemRegistry.BEEF_JERKY), Items.BEEF, 200).build(consumer, "teastory:beef");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemRegistry.MUTTON_JERKY), Items.MUTTON, 200).build(consumer, "teastory:mutton");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemRegistry.CHICKEN_JERKY), Items.CHICKEN, 200).build(consumer, "teastory:chicken");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemRegistry.DRIED_CARROT), Items.CARROT, 200).build(consumer, "teastory:carrot");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemRegistry.DRIED_BEETROOT), Items.BEETROOT, 200).build(consumer, "teastory:beetroot");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemRegistry.TEA_RESIDUES, 200).build(consumer, "teastory:tea_residue");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), ItemRegistry.TEA_RESIDUES, 200).build(consumer, "teastory:black_tea_residue");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), ItemRegistry.TEA_RESIDUES, 200).build(consumer, "teastory:green_tea_residue");
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), ItemRegistry.TEA_RESIDUES, 200).build(consumer, "teastory:white_tea_residue");

        // Bamboo Tray Outdoors Recipes 竹篾匾户外配方
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemRegistry.GREEN_TEA_LEAVES, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.ROTTEN_FLESH), Items.LEATHER, 200).build(consumer, "teastory:leather");
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.RABBIT), ItemRegistry.RABBIT_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.PORKCHOP), ItemRegistry.PORK_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.BEEF), ItemRegistry.BEEF_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.MUTTON), ItemRegistry.MUTTON_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.CHICKEN), ItemRegistry.CHICKEN_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.BEETROOT), ItemRegistry.DRIED_BEETROOT, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.CARROT), ItemRegistry.DRIED_CARROT, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromTag(NormalTags.Items.FOOD_JERKY), Items.LEATHER, 200).build(consumer, "teastory:jerky_leather");
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_GRAPE), ItemRegistry.RAISINS, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(BlockRegistry.FRESH_BAMBOO_WALL), BlockRegistry.DRIED_BAMBOO_WALL, 200).build(consumer);

        // Bamboo Tray Indoors Recipes 竹篾匾室内配方
        BambooTrayRecipeBuilder.indoorsRecipe(Ingredient.fromTag(NormalTags.Items.FOOD_MEAT), Items.ROTTEN_FLESH, 200).build(consumer, "teastory:rotten_flesh");
        BambooTrayRecipeBuilder.indoorsRecipe(Ingredient.fromItems(Items.SPIDER_EYE), Items.FERMENTED_SPIDER_EYE, 200).build(consumer, "teastory:fermented_spider_eye");
        BambooTrayRecipeBuilder.indoorsRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemRegistry.BLACK_TEA_LEAVES, 200).build(consumer);

        // Bamboo Tray Bake Recipes 竹篾匾烘焙配方
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemRegistry.WHITE_TEA_LEAVES, 200).build(consumer);
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.ROTTEN_FLESH), Items.LEATHER, 200).build(consumer, "teastory:bake_flesh");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.RABBIT), ItemRegistry.RABBIT_JERKY, 200).build(consumer, "teastory:bake_rabbit");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.PORKCHOP), ItemRegistry.PORK_JERKY, 200).build(consumer, "teastory:bake_porkchop");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.BEEF), ItemRegistry.BEEF_JERKY, 200).build(consumer, "teastory:bake_beef");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.MUTTON), ItemRegistry.MUTTON_JERKY, 200).build(consumer, "teastory:bake_mutton");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.CHICKEN), ItemRegistry.CHICKEN_JERKY, 200).build(consumer, "teastory:bake_chicken");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.BEETROOT), ItemRegistry.DRIED_BEETROOT, 200).build(consumer, "teastory:bake_beetroot");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.CARROT), ItemRegistry.DRIED_CARROT, 200).build(consumer, "teastory:bake_carrot");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromTag(NormalTags.Items.FOOD_JERKY), Items.LEATHER, 200).build(consumer, "teastory:bake_leather");

        // Drink Maker Recipes 沏茶台配方
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.SUGARY_WATER_STILL.get(), Ingredient.fromItems(Items.SUGAR), Ingredient.fromItems(Items.SUGAR), Ingredient.fromItems(Items.SUGAR), Ingredient.fromItems(Items.SUGAR)).build(consumer);

        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.WEAK_BLACK_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.BLACK_TEA_STILL.get(), Ingredient.fromItems(ItemRegistry.BLACK_TEA_BAG)).build(consumer, "teastory:black_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidRegistry.BLACK_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidRegistry.WEAK_BLACK_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer, "teastory:weak_to_black_tea");
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.BLACK_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.STRONG_BLACK_TEA_STILL.get(), Ingredient.fromItems(ItemRegistry.BLACK_TEA_BAG), Ingredient.fromItems(ItemRegistry.BLACK_TEA_BAG)).build(consumer, "teastory:strong_black_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidRegistry.STRONG_BLACK_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidRegistry.BLACK_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer);

        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.WEAK_GREEN_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.GREEN_TEA_STILL.get(), Ingredient.fromItems(ItemRegistry.GREEN_TEA_BAG)).build(consumer, "teastory:green_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidRegistry.GREEN_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidRegistry.WEAK_GREEN_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer, "teastory:weak_to_green_tea");
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.GREEN_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.STRONG_GREEN_TEA_STILL.get(), Ingredient.fromItems(ItemRegistry.GREEN_TEA_BAG), Ingredient.fromItems(ItemRegistry.GREEN_TEA_BAG)).build(consumer, "teastory:strong_green_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidRegistry.STRONG_GREEN_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidRegistry.GREEN_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer);

        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.WEAK_WHITE_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.drinkRecipe(FluidRegistry.WHITE_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidRegistry.WEAK_WHITE_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer, "teastory:weak_to_white_tea");
        DrinkRecipeBuilder.boilingRecipe(FluidRegistry.WHITE_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.drinkRecipe(FluidRegistry.STRONG_WHITE_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidRegistry.WHITE_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer);

        // Stone Mill Recipes 石磨配方
        StoneMillRecipeBuilder.recipe(600, Ingredient.fromTag(Tags.Items.STONE), FluidIngredient.fromFluid(100, Fluids.WATER), new FluidStack(Fluids.WATER, 100), new ItemStack(Blocks.GRAVEL)).build(consumer, "teastory:gravel");
        StoneMillRecipeBuilder.recipeWithDefaultTime(Ingredient.fromTag(NormalTags.Items.CROPS_CUCUMBER), FluidIngredient.EMPTY, new FluidStack(FluidRegistry.CUCUMBER_JUICE_STILL.get(), 100), ItemStack.EMPTY).build(consumer, "teastory:cucumber_juice");
        StoneMillRecipeBuilder.recipeWithDefaultTime(Ingredient.fromTag(NormalTags.Items.CROPS_GRAPE), FluidIngredient.EMPTY, new FluidStack(FluidRegistry.GRAPE_JUICE_STILL.get(), 100), ItemStack.EMPTY).build(consumer, "teastory:grape_juice");
        StoneMillRecipeBuilder.recipeWithDefaultTime(Ingredient.fromTag(NormalTags.Items.CROPS_SUGAR_CANE), FluidIngredient.EMPTY, new FluidStack(FluidRegistry.SUGAR_CANE_JUICE_STILL.get(), 100), ItemStack.EMPTY).build(consumer, "teastory:sugar_cane_juice");
        StoneMillRecipeBuilder.recipeWithDefaultTime(Ingredient.fromTag(Tags.Items.CROPS_CARROT), FluidIngredient.EMPTY, new FluidStack(FluidRegistry.CARROT_JUICE_STILL.get(), 100), ItemStack.EMPTY).build(consumer, "teastory:carrot_juice");
        StoneMillRecipeBuilder.recipeWithDefaultTime(Ingredient.fromTag(NormalTags.Items.CROPS_APPLE), FluidIngredient.EMPTY, new FluidStack(FluidRegistry.APPLE_JUICE_STILL.get(), 100), ItemStack.EMPTY).build(consumer, "teastory:apple_juice");
        StoneMillRecipeBuilder.recipeWithDefaultTimeWithoutFluid(Ingredient.fromTag(NormalTags.Items.CROPS_STRAW), new ItemStack(ItemRegistry.CRUSHED_STRAW, 2)).build(consumer, "teastory:crushed_straw");
        StoneMillRecipeBuilder.recipeWithDefaultTimeWithoutFluid(Ingredient.fromTag(Tags.Items.BONES), new ItemStack(Items.BONE_MEAL, 4)).build(consumer, "teastory:bone_meal");

        // Stone Roller Recipes 石碾配方
        StoneRollerRecipeBuilder.recipeWithDefaultTime(Ingredient.fromTag(NormalTags.Items.SEEDS_RICE), new ItemStack(ItemRegistry.RICE)).build(consumer, "teastory:rice");
        StoneRollerRecipeBuilder.recipeWithDefaultTime(Ingredient.fromTag(Tags.Items.BONES), new ItemStack(Items.BONE_MEAL, 4)).build(consumer, "teastory:bone_meal");
    }

    @Override
    public String getName()
    {
        return "Tea the Story Recipes";
    }
}
