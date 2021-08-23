package cloud.lemonslice.teastory.common;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import static net.minecraft.block.ComposterBlock.CHANCES;

public class CommonProxy
{
    public World getClientWorld()
    {
        throw new IllegalStateException("Only run this on the client!");
    }

    public PlayerEntity getClientPlayer()
    {
        throw new IllegalStateException("Only run this on the client!");
    }

    public static void registerCompostable()
    {
        CHANCES.put(ItemRegistry.TEA_SEEDS, 0.3F);
        CHANCES.put(ItemRegistry.TEA_LEAVES, 0.15F);
        CHANCES.put(ItemRegistry.GREEN_TEA_LEAVES, 0.3F);
        CHANCES.put(ItemRegistry.BLACK_TEA_LEAVES, 0.4F);
        CHANCES.put(ItemRegistry.TEA_RESIDUES, 0.5F);
        CHANCES.put(ItemRegistry.ASH, 0.3F);
        CHANCES.put(ItemRegistry.WET_STRAW, 0.25F);
        CHANCES.put(ItemRegistry.DRY_STRAW, 0.2F);
        CHANCES.put(ItemRegistry.CRUSHED_STRAW, 0.2F);
        CHANCES.put(ItemRegistry.GRAPES, 0.2F);
        CHANCES.put(ItemRegistry.CUCUMBER, 0.2F);
        CHANCES.put(ItemRegistry.BITTER_GOURD, 0.2F);
        CHANCES.put(BlockRegistry.CHRYSANTHEMUM, 0.3F);
        CHANCES.put(BlockRegistry.ZINNIA, 0.3F);
        CHANCES.put(BlockRegistry.HYACINTH, 0.3F);
        CHANCES.put(Items.POISONOUS_POTATO, 0.3F);
        CHANCES.put(ItemRegistry.CHILI_SEEDS, 0.3F);
    }
}
