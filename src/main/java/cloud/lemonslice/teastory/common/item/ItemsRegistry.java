package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.teastory.common.block.BlocksRegistry;
import cloud.lemonslice.teastory.common.item.food.FoodItem;
import cloud.lemonslice.teastory.common.item.food.NormalFoods;
import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.ItemFluidContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.common.item.NormalItem.*;

public final class ItemsRegistry extends RegistryModule
{
    // FOOD 食物
    public static final Item DRIED_BEETROOT = new FoodItem("dried_beetroot", NormalFoods.DRIED_BEETROOT);
    public static final Item DRIED_CARROT = new FoodItem("dried_carrot", NormalFoods.DRIED_CARROT);
    public static final Item BEEF_JERKY = new FoodItem("beef_jerky", NormalFoods.BEEF_JERKY);
    public static final Item PORK_JERKY = new FoodItem("pork_jerky", NormalFoods.PORK_JERKY);
    public static final Item CHICKEN_JERKY = new FoodItem("chicken_jerky", NormalFoods.CHICKEN_JERKY);
    public static final Item RABBIT_JERKY = new FoodItem("rabbit_jerky", NormalFoods.RABBIT_JERKY);
    public static final Item MUTTON_JERKY = new FoodItem("mutton_jerky", NormalFoods.MUTTON_JERKY);

    public static final Item RAISINS = new NormalItem("raisins", getNormalItemProperties().food(NormalFoods.RAISINS));

    // MISC 杂项
    public static final Item BAMBOO_PLANK = new NormalItem("bamboo_plank");
    public static final Item ASH = new FertilizerItem("ash");
    public static final Item TEA_RESIDUES = new NormalItem("tea_residues");

    public static final Item BAMBOO_CHARCOAL = new NormalBurntItem("bamboo_charcoal", 800);
    public static final Item HONEYCOMB_BRIQUETTE = new NormalBurntItem("honeycomb_briquette", 2000);

    // INGREDIENTS 原料
    public static final Item TEA_LEAVES = new NormalItem("tea_leaves");
    public static final Item GREEN_TEA_LEAVES = new NormalItem("green_tea_leaves", getTeaLeavesItemProperties());
    public static final Item BLACK_TEA_LEAVES = new NormalItem("black_tea_leaves", getTeaLeavesItemProperties());
    public static final Item WHITE_TEA_LEAVES = new NormalItem("white_tea_leaves", getTeaLeavesItemProperties());
    public static final Item EMPTY_TEA_BAG = new NormalItem("empty_tea_bag", getTeaLeavesItemProperties());
    public static final Item GREEN_TEA_BAG = new NormalItem("green_tea_bag", getTeaLeavesItemProperties());
    public static final Item BLACK_TEA_BAG = new NormalItem("black_tea_bag", getTeaLeavesItemProperties());

    // CROPS 作物
    public static final Item TEA_SEEDS = new BlockNamedItem(BlocksRegistry.TEA_PLANT, getNormalItemProperties()).setRegistryName("tea_seeds");
    public static final Item RICE_SEEDS = new BlockNamedItem(BlocksRegistry.RICE_SEEDLING, getNormalItemProperties()).setRegistryName("rice_seeds");
    public static final Item RICE_SEEDLINGS = new BlockNamedItem(BlocksRegistry.RICE_PLANT, getNormalItemProperties()).setRegistryName("rice_seedlings");
    public static final Item GRAPES = new GrapeSeedsItem();

    // DRINK 饮品
    public static final Item CLAY_CUP = new NormalItem("clay_cup", getDrinkItemProperties());
    public static final Item PORCELAIN_CUP = new ItemFluidContainer(getDrinkItemProperties(), 250)
    {
        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt)
        {
            return super.initCapabilities(new ItemStack(ItemsRegistry.PORCELAIN_CUP_DRINK), nbt);
        }
    }.setRegistryName("porcelain_cup");
    public static final Item BOTTLE = new ItemFluidContainer(getDrinkItemProperties(), 500)
    {
        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt)
        {
            return super.initCapabilities(new ItemStack(ItemsRegistry.BOTTLE_DRINK), nbt);
        }
    }.setRegistryName("bottle");
    public static final Item CLAY_TEAPOT = new NormalItem("clay_teapot", getDrinkItemProperties());
    public static final Item PORCELAIN_TEAPOT = new TeapotItem(BlocksRegistry.TEAPOT, 1000, false);
    public static final Item IRON_KETTLE = new IronKettleItem(BlocksRegistry.IRON_KETTLE, 4000);
    public static final Item PORCELAIN_CUP_DRINK = new CupDrinkItem(250, PORCELAIN_CUP, "porcelain_cup_drink");
    public static final Item BOTTLE_DRINK = new CupDrinkItem(500, BOTTLE, "bottle_drink");
}
