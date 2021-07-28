package cloud.lemonslice.teastory.client.color.block;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.block.crops.TrellisWithVineBlock;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;

public final class BlockColorsRegistry
{
    public static final IBlockColor HYBRIDIZABLE_FLOWER_COLOR = new HybridizableFlowerBlockColor();
    public static final IBlockColor FLUID_COLOR = new FluidBlockColor();
    public static final IBlockColor GRASS_BLOCK_COLOR = new GrassBlockColor();
    public static final IBlockColor BIRCH_LEAVES_COLOR = new BirchLeavesColor();
    public static final IBlockColor TEA_CUP_COLOR = new TeaCupBlockColor();
    public static final IBlockColor SAUCEPAN_COLOR = new SaucepanBlockColor();

    public static void init()
    {
        Minecraft.getInstance().getBlockColors().register(HYBRIDIZABLE_FLOWER_COLOR, BlockRegistry.CHRYSANTHEMUM, BlockRegistry.HYACINTH, BlockRegistry.ZINNIA);
        FluidRegistry.BLOCKS.getEntries().forEach(e -> Minecraft.getInstance().getBlockColors().register(FLUID_COLOR, e.get()));
        Minecraft.getInstance().getBlockColors().register(GRASS_BLOCK_COLOR, BlockRegistry.GRASS_BLOCK_WITH_HOLE, BlockRegistry.WATERMELON_VINE);
        BlockRegistry.TRELLIS_BLOCKS.stream().filter(block -> block instanceof TrellisWithVineBlock).forEach(block -> Minecraft.getInstance().getBlockColors().register(GRASS_BLOCK_COLOR, block));
        Minecraft.getInstance().getBlockColors().register(BIRCH_LEAVES_COLOR, Blocks.BIRCH_LEAVES);
        Minecraft.getInstance().getBlockColors().register(TEA_CUP_COLOR, BlockRegistry.WOODEN_TRAY);
        Minecraft.getInstance().getBlockColors().register(SAUCEPAN_COLOR, BlockRegistry.SAUCEPAN);
    }
}
