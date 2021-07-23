package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.item.food.FoodItem;
import cloud.lemonslice.teastory.common.item.food.NormalFoods;
import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.ItemFluidContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class ItemRegistry extends RegistryModule
{
    // TOOL 工具
    public static final Item WOODEN_AQUEDUCT_SHOVEL = new AqueductShovelItem("wooden_aqueduct_shovel", ItemTier.WOOD, 1.5F, -2.5F, new Item.Properties().group(ItemGroup.TOOLS));
    public static final Item STONE_AQUEDUCT_SHOVEL = new AqueductShovelItem("stone_aqueduct_shovel", ItemTier.STONE, 1.5F, -2.5F, new Item.Properties().group(ItemGroup.TOOLS));
    public static final Item GOLD_AQUEDUCT_SHOVEL = new AqueductShovelItem("gold_aqueduct_shovel", ItemTier.GOLD, 1.5F, -2.5F, new Item.Properties().group(ItemGroup.TOOLS));
    public static final Item IRON_AQUEDUCT_SHOVEL = new AqueductShovelItem("iron_aqueduct_shovel", ItemTier.IRON, 1.5F, -2.5F, new Item.Properties().group(ItemGroup.TOOLS));
    public static final Item DIAMOND_AQUEDUCT_SHOVEL = new AqueductShovelItem("diamond_aqueduct_shovel", ItemTier.DIAMOND, 1.5F, -2.5F, new Item.Properties().group(ItemGroup.TOOLS));
//    public static final Item NETHERITE_AQUEDUCT_SHOVEL = new AqueductShovelItem("netherite_aqueduct_shovel", ItemTier.NETHERITE, 1.5F, -2.5F, new Item.Properties().group(ItemGroup.TOOLS));

    // FOOD 食物
    public static final Item DRIED_BEETROOT = new FoodItem("dried_beetroot", NormalFoods.DRIED_BEETROOT);
    public static final Item DRIED_CARROT = new FoodItem("dried_carrot", NormalFoods.DRIED_CARROT);
    public static final Item BEEF_JERKY = new FoodItem("beef_jerky", NormalFoods.BEEF_JERKY);
    public static final Item PORK_JERKY = new FoodItem("pork_jerky", NormalFoods.PORK_JERKY);
    public static final Item CHICKEN_JERKY = new FoodItem("chicken_jerky", NormalFoods.CHICKEN_JERKY);
    public static final Item RABBIT_JERKY = new FoodItem("rabbit_jerky", NormalFoods.RABBIT_JERKY);
    public static final Item MUTTON_JERKY = new FoodItem("mutton_jerky", NormalFoods.MUTTON_JERKY);

    public static final Item RAISINS = new FoodItem("raisins", NormalFoods.RAISINS);

    // MISC 杂项
    public static final Item BAMBOO_PLANK = new NormalItem("bamboo_plank", getNormalItemProperties());
    public static final Item ASH = new FertilizerItem("ash");
    public static final Item TEA_RESIDUES = new NormalItem("tea_residues", getNormalItemProperties());

    public static final Item BAMBOO_CHARCOAL = new NormalBurntItem("bamboo_charcoal", 800);
    public static final Item HONEYCOMB_BRIQUETTE = new NormalBurntItem("honeycomb_briquette", 2000);

    // INGREDIENTS 原料
    public static final Item TEA_LEAVES = new NormalItem("tea_leaves", getNormalItemProperties());
    public static final Item GREEN_TEA_LEAVES = new NormalItem("green_tea_leaves", getTeaLeavesItemProperties());
    public static final Item BLACK_TEA_LEAVES = new NormalItem("black_tea_leaves", getTeaLeavesItemProperties());
    public static final Item WHITE_TEA_LEAVES = new NormalItem("white_tea_leaves", getTeaLeavesItemProperties());
    public static final Item EMPTY_TEA_BAG = new NormalItem("empty_tea_bag", getTeaLeavesItemProperties());
    public static final Item GREEN_TEA_BAG = new NormalItem("green_tea_bag", getTeaLeavesItemProperties());
    public static final Item BLACK_TEA_BAG = new NormalItem("black_tea_bag", getTeaLeavesItemProperties());

    // CROPS 作物
    public static final Item TEA_SEEDS = new BlockNamedItem(BlockRegistry.TEA_PLANT, getNormalItemProperties()).setRegistryName("tea_seeds");
    public static final Item RICE_SEEDS = new BlockNamedItem(BlockRegistry.RICE_SEEDLING, getNormalItemProperties()).setRegistryName("rice_seeds");
    public static final Item RICE_SEEDLINGS = new BlockNamedItem(BlockRegistry.RICE_PLANT, getNormalItemProperties()).setRegistryName("rice_seedlings");
    public static final Item GRAPES = new GrapeSeedsItem();

    // DRINK 饮品
    public static final Item CLAY_CUP = new NormalItem("clay_cup", getDrinkItemProperties());
    public static final Item PORCELAIN_CUP = new ItemFluidContainer(getDrinkItemProperties(), 250)
    {
        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt)
        {
            return super.initCapabilities(new ItemStack(ItemRegistry.PORCELAIN_CUP_DRINK), nbt);
        }
    }.setRegistryName("porcelain_cup");
    public static final Item BOTTLE = new ItemFluidContainer(getDrinkItemProperties(), 500)
    {
        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt)
        {
            return super.initCapabilities(new ItemStack(ItemRegistry.BOTTLE_DRINK), nbt);
        }
    }.setRegistryName("bottle");
    public static final Item CLAY_TEAPOT = new NormalItem("clay_teapot", getDrinkItemProperties());
    public static final Item PORCELAIN_TEAPOT = new TeapotItem(BlockRegistry.TEAPOT, 1000, false);
    public static final Item IRON_KETTLE = new IronKettleItem(BlockRegistry.IRON_KETTLE, 4000);
    public static final Item PORCELAIN_CUP_DRINK = new CupDrinkItem(250, PORCELAIN_CUP, "porcelain_cup_drink");
    public static final Item BOTTLE_DRINK = new CupDrinkItem(500, BOTTLE, "bottle_drink");

    public static Item.Properties getNormalItemProperties()
    {
        return new Item.Properties().group(TeaStory.GROUP_CORE);
    }

    public static Item.Properties getDrinkItemProperties()
    {
        return new Item.Properties().group(TeaStory.GROUP_DRINK);
    }

    public static Item.Properties getTeaLeavesItemProperties()
    {
        return new Item.Properties().group(TeaStory.GROUP_CORE).containerItem(ItemRegistry.TEA_RESIDUES);
    }
}
