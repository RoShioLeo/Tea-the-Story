package cloud.lemonslice.teastory.client.color.item;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;

public final class ItemColorsRegistry
{
    private final static IItemColor BUCKET_COLOR = new BucketItemColors();
    private final static IItemColor CUP_COLOR = new CupItemColors();
    private final static IItemColor BOTTLE_COLOR = new BottleItemColors();
    private final static IItemColor HYBRIDIZABLE_FLOWER_COLOR = new HybridizableFlowerItemColor();
    private final static IItemColor GRASS_BLOCK_COLOR = new GrassBlockItemColors();

    public static void init()
    {
        FluidRegistry.ITEMS.getEntries().forEach(e -> Minecraft.getInstance().getItemColors().register(BUCKET_COLOR, e.get()));
        Minecraft.getInstance().getItemColors().register(CUP_COLOR, ItemRegistry.PORCELAIN_CUP_DRINK);
        Minecraft.getInstance().getItemColors().register(BOTTLE_COLOR, ItemRegistry.BOTTLE_DRINK);
        Minecraft.getInstance().getItemColors().register(HYBRIDIZABLE_FLOWER_COLOR, BlockRegistry.CHRYSANTHEMUM, BlockRegistry.HYACINTH, BlockRegistry.ZINNIA);
        Minecraft.getInstance().getItemColors().register(GRASS_BLOCK_COLOR, BlockRegistry.GRASS_BLOCK_WITH_HOLE);
    }
}
