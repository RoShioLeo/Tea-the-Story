package cloud.lemonslice.teastory.common.container;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.tileentity.StoneMillTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import static cloud.lemonslice.teastory.common.container.ContainerTypeRegistry.STONE_MILL_CONTAINER;

public class StoneMillContainer extends NormalContainer
{
    private final StoneMillTileEntity tileEntity;

    public StoneMillContainer(int windowId, PlayerInventory inv, BlockPos pos, World world)
    {
        super(STONE_MILL_CONTAINER, windowId);
        this.tileEntity = (StoneMillTileEntity) world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(h ->
        {
            addSlot(new SlotItemHandler(h, 0, 73, 38));
        });
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(h ->
        {
            addSlot(new SlotItemHandler(h, 0, 123, 20));
            addSlot(new SlotItemHandler(h, 1, 123, 38));
            addSlot(new SlotItemHandler(h, 2, 123, 56));
        });
        addPlayerInventory(inv);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, BlockRegistry.STONE_MILL);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged;

        // 0~1: Input; 1~4: Output; 4~31: Player Backpack; 31~40: Hot Bar.

        if (index == 0)
        {
            isMerged = mergeItemStack(newStack, 31, 40, true)
                    || mergeItemStack(newStack, 4, 31, false);
        }
        else if (index < 4)
        {
            isMerged = mergeItemStack(newStack, 31, 40, true)
                    || mergeItemStack(newStack, 4, 31, false);
        }
        else if (index < 31)
        {
            isMerged = mergeItemStack(newStack, 0, 1, false)
                    || mergeItemStack(newStack, 31, 40, true);
        }
        else
        {
            isMerged = mergeItemStack(newStack, 0, 1, false)
                    || mergeItemStack(newStack, 4, 31, false);
        }

        if (!isMerged)
        {
            return ItemStack.EMPTY;
        }

        if (newStack.getCount() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }

        slot.onTake(playerIn, newStack);

        return oldStack;
    }

    public StoneMillTileEntity getTileEntity()
    {
        return tileEntity;
    }
}
