package cloud.lemonslice.teastory.common.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static cloud.lemonslice.teastory.TeaStory.MODID;

public final class FluidsRegistry
{
    public static final Item.Properties BUCKET_PROPERTIES = new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC);

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final ResourceLocation WATER_STILL_TEXTURE = new ResourceLocation("minecraft:block/water_still");
    public static final ResourceLocation WATER_FLOW_TEXTURE = new ResourceLocation("minecraft:block/water_flow");

    public static final RegistryObject<FlowingFluid> BOILING_WATER_STILL = FLUIDS.register("boiling_water", () -> new ForgeFlowingFluid.Source(FluidsRegistry.BOILING_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> BOILING_WATER_FLOW = FLUIDS.register("boiling_water_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.BOILING_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HOT_WATER_80_STILL = FLUIDS.register("hot_water_80", () -> new ForgeFlowingFluid.Source(FluidsRegistry.HOT_WATER_80_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HOT_WATER_80_FLOW = FLUIDS.register("hot_water_80_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.HOT_WATER_80_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HOT_WATER_60_STILL = FLUIDS.register("hot_water_60", () -> new ForgeFlowingFluid.Source(FluidsRegistry.HOT_WATER_60_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HOT_WATER_60_FLOW = FLUIDS.register("hot_water_60_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.HOT_WATER_60_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WARM_WATER_STILL = FLUIDS.register("warm_water", () -> new ForgeFlowingFluid.Source(FluidsRegistry.WARM_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WARM_WATER_FLOW = FLUIDS.register("warm_water_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.WARM_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SUGARY_WATER_STILL = FLUIDS.register("sugary_water", () -> new ForgeFlowingFluid.Source(FluidsRegistry.SUGARY_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SUGARY_WATER_FLOW = FLUIDS.register("sugary_water_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.SUGARY_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WEAK_GREEN_TEA_STILL = FLUIDS.register("weak_green_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.WEAK_GREEN_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WEAK_GREEN_TEA_FLOW = FLUIDS.register("weak_green_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.WEAK_GREEN_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WEAK_BLACK_TEA_STILL = FLUIDS.register("weak_black_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.WEAK_BLACK_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WEAK_BLACK_TEA_FLOW = FLUIDS.register("weak_black_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.WEAK_BLACK_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WEAK_WHITE_TEA_STILL = FLUIDS.register("weak_white_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.WEAK_WHITE_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WEAK_WHITE_TEA_FLOW = FLUIDS.register("weak_white_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.WEAK_WHITE_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> GREEN_TEA_STILL = FLUIDS.register("green_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.GREEN_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> GREEN_TEA_FLOW = FLUIDS.register("green_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.GREEN_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> BLACK_TEA_STILL = FLUIDS.register("black_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.BLACK_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> BLACK_TEA_FLOW = FLUIDS.register("black_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.BLACK_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WHITE_TEA_STILL = FLUIDS.register("white_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.WHITE_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> WHITE_TEA_FLOW = FLUIDS.register("white_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.WHITE_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> STRONG_GREEN_TEA_STILL = FLUIDS.register("strong_green_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.STRONG_GREEN_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> STRONG_GREEN_TEA_FLOW = FLUIDS.register("strong_green_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.STRONG_GREEN_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> STRONG_BLACK_TEA_STILL = FLUIDS.register("strong_black_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.STRONG_BLACK_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> STRONG_BLACK_TEA_FLOW = FLUIDS.register("strong_black_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.STRONG_BLACK_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> STRONG_WHITE_TEA_STILL = FLUIDS.register("strong_white_tea", () -> new ForgeFlowingFluid.Source(FluidsRegistry.STRONG_WHITE_TEA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> STRONG_WHITE_TEA_FLOW = FLUIDS.register("strong_white_tea_flowing", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.STRONG_WHITE_TEA_PROPERTIES));

    public static final RegistryObject<BucketItem> BOILING_WATER_BUCKET = ITEMS.register("boiling_water_bucket", () -> new BucketItem(FluidsRegistry.BOILING_WATER_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> HOT_WATER_80_BUCKET = ITEMS.register("hot_water_80_bucket", () -> new BucketItem(FluidsRegistry.HOT_WATER_80_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> HOT_WATER_60_BUCKET = ITEMS.register("hot_water_60_bucket", () -> new BucketItem(FluidsRegistry.HOT_WATER_60_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> WARM_WATER_BUCKET = ITEMS.register("warm_water_bucket", () -> new BucketItem(FluidsRegistry.WARM_WATER_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> SUGARY_WATER_BUCKET = ITEMS.register("sugary_water_bucket", () -> new BucketItem(FluidsRegistry.SUGARY_WATER_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> WEAK_GREEN_TEA_BUCKET = ITEMS.register("weak_green_tea_bucket", () -> new BucketItem(FluidsRegistry.WEAK_GREEN_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> WEAK_BLACK_TEA_BUCKET = ITEMS.register("weak_black_tea_bucket", () -> new BucketItem(FluidsRegistry.WEAK_BLACK_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> WEAK_WHITE_TEA_BUCKET = ITEMS.register("weak_white_tea_bucket", () -> new BucketItem(FluidsRegistry.WEAK_WHITE_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> GREEN_TEA_BUCKET = ITEMS.register("green_tea_bucket", () -> new BucketItem(FluidsRegistry.GREEN_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> BLACK_TEA_BUCKET = ITEMS.register("black_tea_bucket", () -> new BucketItem(FluidsRegistry.BLACK_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> WHITE_TEA_BUCKET = ITEMS.register("white_tea_bucket", () -> new BucketItem(FluidsRegistry.WHITE_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> STRONG_GREEN_TEA_BUCKET = ITEMS.register("strong_green_tea_bucket", () -> new BucketItem(FluidsRegistry.STRONG_GREEN_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> STRONG_BLACK_TEA_BUCKET = ITEMS.register("strong_black_tea_bucket", () -> new BucketItem(FluidsRegistry.STRONG_BLACK_TEA_STILL, BUCKET_PROPERTIES));
    public static final RegistryObject<BucketItem> STRONG_WHITE_TEA_BUCKET = ITEMS.register("strong_white_tea_bucket", () -> new BucketItem(FluidsRegistry.STRONG_WHITE_TEA_STILL, BUCKET_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> BOILING_WATER = BLOCKS.register("boiling_water", () -> new HotWaterFlowingFluidBlock(FluidsRegistry.BOILING_WATER_STILL));
    public static final RegistryObject<FlowingFluidBlock> HOT_WATER_80 = BLOCKS.register("hot_water_80", () -> new HotWaterFlowingFluidBlock(FluidsRegistry.HOT_WATER_80_STILL));
    public static final RegistryObject<FlowingFluidBlock> HOT_WATER_60 = BLOCKS.register("hot_water_60", () -> new HotWaterFlowingFluidBlock(FluidsRegistry.HOT_WATER_60_STILL));
    public static final RegistryObject<FlowingFluidBlock> WARM_WATER = BLOCKS.register("warm_water", () -> new HotWaterFlowingFluidBlock(FluidsRegistry.WARM_WATER_STILL));
    public static final RegistryObject<FlowingFluidBlock> SUGARY_WATER = BLOCKS.register("sugary_water", () -> new NormalFlowingFluidBlock(FluidsRegistry.SUGARY_WATER_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> WEAK_GREEN_TEA = BLOCKS.register("weak_green_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.WEAK_GREEN_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> WEAK_BLACK_TEA = BLOCKS.register("weak_black_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.WEAK_BLACK_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> WEAK_WHITE_TEA = BLOCKS.register("weak_white_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.WEAK_WHITE_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> GREEN_TEA = BLOCKS.register("green_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.GREEN_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> BLACK_TEA = BLOCKS.register("black_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.BLACK_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> WHITE_TEA = BLOCKS.register("white_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.WHITE_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> STRONG_GREEN_TEA = BLOCKS.register("strong_green_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.STRONG_GREEN_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> STRONG_BLACK_TEA = BLOCKS.register("strong_black_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.STRONG_BLACK_TEA_STILL, NormalFlowingFluidBlock.getProperties()));
    public static final RegistryObject<FlowingFluidBlock> STRONG_WHITE_TEA = BLOCKS.register("strong_white_tea", () -> new NormalFlowingFluidBlock(FluidsRegistry.STRONG_WHITE_TEA_STILL, NormalFlowingFluidBlock.getProperties()));

    public static final ForgeFlowingFluid.Properties BOILING_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(BOILING_WATER_STILL, BOILING_WATER_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFF4989E3).temperature(373)).bucket(BOILING_WATER_BUCKET).block(FluidsRegistry.BOILING_WATER).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties HOT_WATER_80_PROPERTIES = new ForgeFlowingFluid.Properties(HOT_WATER_80_STILL, HOT_WATER_80_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFF4989E3).temperature(353)).bucket(HOT_WATER_80_BUCKET).block(FluidsRegistry.HOT_WATER_80).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties HOT_WATER_60_PROPERTIES = new ForgeFlowingFluid.Properties(HOT_WATER_60_STILL, HOT_WATER_60_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFF4989E3).temperature(333)).bucket(HOT_WATER_60_BUCKET).block(FluidsRegistry.HOT_WATER_60).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties WARM_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(WARM_WATER_STILL, WARM_WATER_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFF4989E3).temperature(308)).bucket(WARM_WATER_BUCKET).block(FluidsRegistry.WARM_WATER).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties SUGARY_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(SUGARY_WATER_STILL, SUGARY_WATER_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFF5AB4E6)).bucket(SUGARY_WATER_BUCKET).block(FluidsRegistry.SUGARY_WATER).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties WEAK_GREEN_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(WEAK_GREEN_TEA_STILL, WEAK_GREEN_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0x7FAAB919)).bucket(WEAK_GREEN_TEA_BUCKET).block(FluidsRegistry.WEAK_GREEN_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties WEAK_BLACK_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(WEAK_BLACK_TEA_STILL, WEAK_BLACK_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0x7FCD511E)).bucket(WEAK_BLACK_TEA_BUCKET).block(FluidsRegistry.WEAK_BLACK_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties WEAK_WHITE_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(WEAK_WHITE_TEA_STILL, WEAK_WHITE_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0x7FB4AA64)).bucket(WEAK_WHITE_TEA_BUCKET).block(FluidsRegistry.WEAK_WHITE_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties GREEN_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(GREEN_TEA_STILL, GREEN_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xBFAAB919)).bucket(GREEN_TEA_BUCKET).block(FluidsRegistry.GREEN_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties BLACK_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(BLACK_TEA_STILL, BLACK_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xBFCD511E)).bucket(BLACK_TEA_BUCKET).block(FluidsRegistry.BLACK_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties WHITE_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(WHITE_TEA_STILL, WHITE_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xBFB4AA64)).bucket(WHITE_TEA_BUCKET).block(FluidsRegistry.WHITE_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties STRONG_GREEN_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(STRONG_GREEN_TEA_STILL, STRONG_GREEN_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFFAAB919)).bucket(STRONG_GREEN_TEA_BUCKET).block(FluidsRegistry.STRONG_GREEN_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties STRONG_BLACK_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(STRONG_BLACK_TEA_STILL, STRONG_BLACK_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFFCD511E)).bucket(STRONG_BLACK_TEA_BUCKET).block(FluidsRegistry.STRONG_BLACK_TEA).explosionResistance(100F);
    public static final ForgeFlowingFluid.Properties STRONG_WHITE_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(STRONG_WHITE_TEA_STILL, STRONG_WHITE_TEA_FLOW, FluidAttributes.builder(WATER_STILL_TEXTURE, WATER_FLOW_TEXTURE).color(0xFFB4AA64)).bucket(STRONG_WHITE_TEA_BUCKET).block(FluidsRegistry.STRONG_WHITE_TEA).explosionResistance(100F);
}