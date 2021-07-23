package cloud.lemonslice.teastory.common.handler.event;

import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public final class BlockEventHandler
{
    public static void dropAsh(BlockEvent.NeighborNotifyEvent event)
    {
        if (ServerConfig.Others.woodDropsAshWhenBurning.get())
            event.getNotifiedSides().forEach(direction ->
            {
                if (event.getWorld().getBlockState(event.getPos()).isFlammable(event.getWorld(), event.getPos(), direction) && event.getWorld().getBlockState(event.getPos().offset(direction)).getBlock() instanceof FireBlock)
                {
                    Block.spawnAsEntity((World) event.getWorld(), event.getPos(), new ItemStack(ItemRegistry.ASH));
                }
            });
    }
}
