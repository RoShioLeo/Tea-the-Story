package cloud.lemonslice.teastory.common;

import cloud.lemonslice.teastory.common.block.BlocksRegistry;
import cloud.lemonslice.teastory.common.item.ItemsRegistry;
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
        CHANCES.put(ItemsRegistry.TEA_SEEDS, 0.3F);
        CHANCES.put(ItemsRegistry.TEA_LEAVES, 0.15F);
        CHANCES.put(ItemsRegistry.GREEN_TEA_LEAVES, 0.3F);
        CHANCES.put(ItemsRegistry.BLACK_TEA_LEAVES, 0.4F);
        CHANCES.put(ItemsRegistry.TEA_RESIDUES, 0.5F);
        CHANCES.put(BlocksRegistry.CHRYSANTHEMUM_ITEM, 0.3F);
        CHANCES.put(BlocksRegistry.ZINNIA_ITEM, 0.3F);
        CHANCES.put(BlocksRegistry.HYACINTH_ITEM, 0.3F);
        CHANCES.put(Items.POISONOUS_POTATO, 0.3F);
    }
}
