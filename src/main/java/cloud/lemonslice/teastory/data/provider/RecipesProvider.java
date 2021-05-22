package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.block.BlocksRegistry;
import cloud.lemonslice.teastory.common.fluid.FluidsRegistry;
import cloud.lemonslice.teastory.common.item.ItemsRegistry;
import cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializersRegistry;
import cloud.lemonslice.teastory.data.builder.BambooTrayRecipeBuilder;
import cloud.lemonslice.teastory.data.builder.DrinkRecipeBuilder;
import cloud.lemonslice.teastory.data.tag.NormalTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public final class RecipesProvider extends RecipeProvider
{
    public RecipesProvider(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        // Special Custom Recipes 自定义特殊配方
        CustomRecipeBuilder.customRecipe(RecipeSerializersRegistry.CRAFTING_SPECIAL_FLOWERDYE.get()).build(consumer, "teastory:flower_dye");

        // Decoration Recipes 装饰品配方
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.BAMBOO_PLANK).key('x', Items.BAMBOO).patternLine("xx").patternLine("xx").setGroup("bamboo_plank").addCriterion("has_bamboo", this.hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_DOOR, 3).key('x', ItemsRegistry.BAMBOO_PLANK).patternLine("xx").patternLine("xx").patternLine("xx").setGroup("bamboo_door").addCriterion("has_bamboo_plank", this.hasItem(ItemsRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_GLASS_DOOR, 3).key('x', ItemsRegistry.BAMBOO_PLANK).key('#', Tags.Items.GLASS_COLORLESS).patternLine("##").patternLine("xx").patternLine("xx").setGroup("bamboo_glass_door").addCriterion("has_bamboo_plank", this.hasItem(ItemsRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_CHAIR).key('x', ItemsRegistry.BAMBOO_PLANK).key('#', Items.BAMBOO).patternLine("  #").patternLine("xxx").patternLine("# #").setGroup("bamboo_chair").addCriterion("has_bamboo_plank", this.hasItem(ItemsRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_LANTERN).key('x', Blocks.TORCH).key('#', Items.BAMBOO).patternLine("###").patternLine("#x#").patternLine("###").setGroup("bamboo_lantern").addCriterion("has_bamboo", this.hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_TABLE).key('x', ItemsRegistry.BAMBOO_PLANK).key('#', Items.BAMBOO).patternLine("xxx").patternLine("# #").patternLine("# #").setGroup("bamboo_table").addCriterion("has_bamboo_plank", this.hasItem(ItemsRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_TRAY).key('x', ItemsRegistry.BAMBOO_PLANK).key('#', Items.BAMBOO).patternLine("# #").patternLine("#x#").setGroup("bamboo_tray").addCriterion("has_bamboo_plank", this.hasItem(ItemsRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WOODEN_FRAME).key('x', Tags.Items.RODS_WOODEN).key('#', ItemTags.PLANKS).patternLine("#x#").patternLine("x#x").patternLine("x x").setGroup("wooden_frame").addCriterion("has_plank", this.hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.FRESH_BAMBOO_WALL, 2).key('x', Items.BAMBOO).key('#', Tags.Items.STRING).patternLine("xxx").patternLine("###").patternLine("xxx").setGroup("fresh_bamboo_wall").addCriterion("has_bamboo", this.hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WOODEN_TABLE).key('x', ItemTags.PLANKS).key('#', Tags.Items.RODS_WOODEN).patternLine("xxx").patternLine("# #").patternLine("# #").setGroup("wooden_table").addCriterion("has_plank", this.hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WOODEN_CHAIR).key('x', ItemTags.PLANKS).key('#', Tags.Items.RODS_WOODEN).patternLine("x  ").patternLine("xxx").patternLine("# #").setGroup("wooden_chair").addCriterion("has_plank", this.hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.STONE_TABLE).key('x', Blocks.STONE).key('#', Blocks.COBBLESTONE_WALL).patternLine("xxx").patternLine("# #").patternLine("# #").setGroup("stone_table").addCriterion("has_stone", this.hasItem(Blocks.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.STONE_CHAIR).key('x', Blocks.STONE).key('#', Blocks.COBBLESTONE_WALL).patternLine("xxx").patternLine("# #").setGroup("stone_chair").addCriterion("has_stone", this.hasItem(Blocks.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.WOODEN_TRAY).key('#', Tags.Items.RODS_WOODEN).patternLine("# #").patternLine("###").setGroup("wooden_tray").addCriterion("has_rod", this.hasItem(Tags.Items.RODS_WOODEN)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_LATTICE, 2).key('x', Items.BAMBOO).patternLine("x x").patternLine(" x ").patternLine("x x").setGroup("bamboo_lattice").addCriterion("has_bamboo", this.hasItem(Items.BAMBOO)).build(consumer);

        // Drink Ingredient Recipes 茶饮配料配方
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.EMPTY_TEA_BAG, 3).key('/', Items.STRING).key('x', Items.PAPER).patternLine(" / ").patternLine("xxx").patternLine("xxx").setGroup("empty_tea_bag").addCriterion("has_paper", this.hasItem(Items.PAPER)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemsRegistry.BLACK_TEA_BAG).addIngredient(ItemsRegistry.EMPTY_TEA_BAG).addIngredient(Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), 3).setGroup("tea_bag").addCriterion("has_tea_bag", this.hasItem(ItemsRegistry.EMPTY_TEA_BAG)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemsRegistry.GREEN_TEA_BAG).addIngredient(ItemsRegistry.EMPTY_TEA_BAG).addIngredient(Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), 3).setGroup("tea_bag").addCriterion("has_tea_bag", this.hasItem(ItemsRegistry.EMPTY_TEA_BAG)).build(consumer);

        // Tea Set Recipes 茶具配方
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.BOTTLE).key('x', Tags.Items.NUGGETS_IRON).key('#', Tags.Items.GLASS_PANES_COLORLESS).patternLine(" x ").patternLine("# #").patternLine("###").setGroup("bottle").addCriterion("has_glass_pane", this.hasItem(Tags.Items.GLASS_PANES_COLORLESS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.CLAY_CUP).key('x', Items.CLAY_BALL).patternLine("x x").patternLine(" x ").setGroup("clay_cup").addCriterion("has_clay_ball", this.hasItem(Items.CLAY_BALL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.CLAY_TEAPOT).key('x', Blocks.CLAY).patternLine("x x").patternLine(" x ").setGroup("clay_teapot").addCriterion("has_clay_ball", this.hasItem(Items.CLAY_BALL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemsRegistry.IRON_KETTLE).key('*', Items.BUCKET).key('x', Tags.Items.INGOTS_IRON).patternLine(" x ").patternLine("x*x").patternLine("xxx").setGroup("iron_kettle").addCriterion("has_iron", this.hasItem(Tags.Items.INGOTS_IRON)).build(consumer);

        // Craft Block Recipes 工艺方块配方
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.DIRT_STOVE).key('x', NormalTags.Items.DIRT).patternLine("xxx").patternLine("x x").patternLine("xxx").setGroup("stove").addCriterion("has_dirt", this.hasItem(NormalTags.Items.DIRT)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.STONE_STOVE).key('x', Tags.Items.STONE).key('#', BlocksRegistry.DIRT_STOVE).patternLine("xxx").patternLine("x#x").patternLine("xxx").setGroup("stove").addCriterion("has_dirt", this.hasItem(NormalTags.Items.DIRT)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.DRINK_MAKER).key('x', ItemTags.PLANKS).key('#', Tags.Items.RODS_WOODEN).patternLine("# #").patternLine("xxx").setGroup("drink_maker").addCriterion("has_planks", this.hasItem(ItemTags.PLANKS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.FILTER_SCREEN).key('x', Tags.Items.RODS_WOODEN).key('#', Tags.Items.STRING).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("x#x").patternLine("#*#").patternLine("x#x").setGroup("filter_screen").addCriterion("has_string", this.hasItem(Tags.Items.STRING)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BAMBOO_CATAPULT_BOARD).key('x', ItemsRegistry.BAMBOO_PLANK).key('#', Tags.Items.STONE).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("xxx").patternLine("xxx").patternLine("#*#").setGroup("catapult_board").addCriterion("has_bamboo_plank", this.hasItem(ItemsRegistry.BAMBOO_PLANK)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.IRON_CATAPULT_BOARD).key('x', Tags.Items.INGOTS_IRON).key('#', Tags.Items.STONE).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("xxx").patternLine("xxx").patternLine("#*#").setGroup("catapult_board").addCriterion("has_iron_ingot", this.hasItem(Tags.Items.INGOTS_IRON)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.STONE_CATAPULT_BOARD).key('x', Blocks.STONE).key('#', Tags.Items.STONE).key('*', Tags.Items.DUSTS_REDSTONE).patternLine("xxx").patternLine("xxx").patternLine("#*#").setGroup("catapult_board").addCriterion("has_stone", this.hasItem(Blocks.STONE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.OAK_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.OAK_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", this.hasItem(Blocks.OAK_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.BIRCH_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.BIRCH_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", this.hasItem(Blocks.BIRCH_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.SPRUCE_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.SPRUCE_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", this.hasItem(Blocks.SPRUCE_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.JUNGLE_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.JUNGLE_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", this.hasItem(Blocks.JUNGLE_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.DARK_OAK_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.DARK_OAK_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", this.hasItem(Blocks.DARK_OAK_FENCE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlocksRegistry.ACACIA_TRELLIS, 2).key('#', Tags.Items.RODS_WOODEN).key('*', Blocks.ACACIA_FENCE).patternLine("#*#").patternLine(" # ").setGroup("trellis").addCriterion("has_planks", this.hasItem(Blocks.ACACIA_FENCE)).build(consumer);

        // Smelting Recipes 熔炼配方
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Items.WATER_BUCKET), FluidsRegistry.BOILING_WATER_BUCKET.get(), 0.2F, 200).addCriterion("has_water_bucket", this.hasItem(Items.WATER_BUCKET)).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemsRegistry.CLAY_CUP), ItemsRegistry.PORCELAIN_CUP, 0.2F, 200).addCriterion("has_clay_cup", this.hasItem(ItemsRegistry.CLAY_CUP)).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemsRegistry.CLAY_TEAPOT), ItemsRegistry.PORCELAIN_TEAPOT, 0.2F, 200).addCriterion("has_clay_teapot", this.hasItem(ItemsRegistry.CLAY_TEAPOT)).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Items.BAMBOO), ItemsRegistry.BAMBOO_CHARCOAL, 0.2F, 200).addCriterion("has_bamboo", this.hasItem(Items.BAMBOO)).build(consumer);

        // Bamboo Tray In-rain Recipes 竹篾匾淋雨配方
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemsRegistry.RABBIT_JERKY), Items.RABBIT, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemsRegistry.PORK_JERKY), Items.PORKCHOP, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemsRegistry.BEEF_JERKY), Items.BEEF, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemsRegistry.MUTTON_JERKY), Items.MUTTON, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemsRegistry.CHICKEN_JERKY), Items.CHICKEN, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemsRegistry.DRIED_CARROT), Items.CARROT, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromItems(ItemsRegistry.DRIED_BEETROOT), Items.BEETROOT, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemsRegistry.TEA_RESIDUES, 0).build(consumer);
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), ItemsRegistry.TEA_RESIDUES, 0).build(consumer, new ResourceLocation("teastory:black_tea_residue"));
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), ItemsRegistry.TEA_RESIDUES, 0).build(consumer, new ResourceLocation("teastory:green_tea_residue"));
        BambooTrayRecipeBuilder.wetRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), ItemsRegistry.TEA_RESIDUES, 0).build(consumer, new ResourceLocation("teastory:white_tea_residue"));

        // Bamboo Tray Outdoors Recipes 竹篾匾户外配方
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemsRegistry.GREEN_TEA_LEAVES, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.ROTTEN_FLESH), Items.LEATHER, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.RABBIT), ItemsRegistry.RABBIT_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.PORKCHOP), ItemsRegistry.PORK_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.BEEF), ItemsRegistry.BEEF_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.MUTTON), ItemsRegistry.MUTTON_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.CHICKEN), ItemsRegistry.CHICKEN_JERKY, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.BEETROOT), ItemsRegistry.DRIED_BEETROOT, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(Items.CARROT), ItemsRegistry.DRIED_CARROT, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromTag(NormalTags.Items.FOOD_JERKY), Items.LEATHER, 200).build(consumer, "teastory:jerky_leather");
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_GRAPE), ItemsRegistry.RAISINS, 200).build(consumer);
        BambooTrayRecipeBuilder.outdoorsRecipe(Ingredient.fromItems(BlocksRegistry.FRESH_BAMBOO_WALL), BlocksRegistry.DRIED_BAMBOO_WALL, 200).build(consumer);

        // Bamboo Tray Indoors Recipes 竹篾匾室内配方
        BambooTrayRecipeBuilder.indoorsRecipe(Ingredient.fromTag(NormalTags.Items.FOOD_MEAT), Items.ROTTEN_FLESH, 200).build(consumer);
        BambooTrayRecipeBuilder.indoorsRecipe(Ingredient.fromItems(Items.SPIDER_EYE), Items.FERMENTED_SPIDER_EYE, 200).build(consumer);
        BambooTrayRecipeBuilder.indoorsRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemsRegistry.BLACK_TEA_LEAVES, 200).build(consumer);

        // Bamboo Tray Bake Recipes 竹篾匾烘焙配方
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromTag(NormalTags.Items.CROPS_TEA_LEAF), ItemsRegistry.WHITE_TEA_LEAVES, 200).build(consumer);
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.ROTTEN_FLESH), Items.LEATHER, 200).build(consumer, "teastory:bake_flesh");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.RABBIT), ItemsRegistry.RABBIT_JERKY, 200).build(consumer, "teastory:bake_rabbit");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.PORKCHOP), ItemsRegistry.PORK_JERKY, 200).build(consumer, "teastory:bake_porkchop");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.BEEF), ItemsRegistry.BEEF_JERKY, 200).build(consumer, "teastory:bake_beef");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.MUTTON), ItemsRegistry.MUTTON_JERKY, 200).build(consumer, "teastory:bake_mutton");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.CHICKEN), ItemsRegistry.CHICKEN_JERKY, 200).build(consumer, "teastory:bake_chicken");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.BEETROOT), ItemsRegistry.DRIED_BEETROOT, 200).build(consumer, "teastory:bake_beetroot");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromItems(Items.CARROT), ItemsRegistry.DRIED_CARROT, 200).build(consumer, "teastory:bake_carrot");
        BambooTrayRecipeBuilder.bakeRecipe(Ingredient.fromTag(NormalTags.Items.FOOD_JERKY), Items.LEATHER, 200).build(consumer, "teastory:bake_leather");

        // Drink Maker Recipes 沏茶台配方
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.SUGARY_WATER_STILL.get(), Ingredient.fromItems(Items.SUGAR), Ingredient.fromItems(Items.SUGAR), Ingredient.fromItems(Items.SUGAR), Ingredient.fromItems(Items.SUGAR)).build(consumer);

        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.WEAK_BLACK_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.BLACK_TEA_STILL.get(), Ingredient.fromItems(ItemsRegistry.BLACK_TEA_BAG)).build(consumer, "teastory:black_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidsRegistry.BLACK_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidsRegistry.WEAK_BLACK_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer, "teastory:weak_to_black_tea");
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.BLACK_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.STRONG_BLACK_TEA_STILL.get(), Ingredient.fromItems(ItemsRegistry.BLACK_TEA_BAG), Ingredient.fromItems(ItemsRegistry.BLACK_TEA_BAG)).build(consumer, "teastory:strong_black_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidsRegistry.STRONG_BLACK_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidsRegistry.BLACK_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_BLACK_TEA_LEAF)).build(consumer);

        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.WEAK_GREEN_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.GREEN_TEA_STILL.get(), Ingredient.fromItems(ItemsRegistry.GREEN_TEA_BAG)).build(consumer, "teastory:green_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidsRegistry.GREEN_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidsRegistry.WEAK_GREEN_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer, "teastory:weak_to_green_tea");
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.GREEN_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.STRONG_GREEN_TEA_STILL.get(), Ingredient.fromItems(ItemsRegistry.GREEN_TEA_BAG), Ingredient.fromItems(ItemsRegistry.GREEN_TEA_BAG)).build(consumer, "teastory:strong_green_tea_bag");
        DrinkRecipeBuilder.drinkRecipe(FluidsRegistry.STRONG_GREEN_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidsRegistry.GREEN_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_GREEN_TEA_LEAF)).build(consumer);

        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.WEAK_WHITE_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.drinkRecipe(FluidsRegistry.WHITE_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidsRegistry.WEAK_WHITE_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer, "teastory:weak_to_white_tea");
        DrinkRecipeBuilder.boilingRecipe(FluidsRegistry.WHITE_TEA_STILL.get(), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer);
        DrinkRecipeBuilder.drinkRecipe(FluidsRegistry.STRONG_WHITE_TEA_STILL.get(), FluidIngredient.fromFluid(500, FluidsRegistry.WHITE_TEA_STILL.get()), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF), Ingredient.fromTag(NormalTags.Items.CROPS_WHITE_TEA_LEAF)).build(consumer);
    }

    @Override
    public String getName()
    {
        return "After the Drizzle Recipes";
    }
}
