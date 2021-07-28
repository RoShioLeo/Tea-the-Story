package cloud.lemonslice.teastory.common.container;

import cloud.lemonslice.teastory.common.tileentity.StoveTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import static cloud.lemonslice.teastory.common.container.ContainerTypeRegistry.STOVE_CONTAINER;

public class StoveContainer extends Container
{
    private final StoveTileEntity tileEntity;

    public StoveContainer(int windowId, PlayerInventory inv, BlockPos pos, World world)
    {
        super(STOVE_CONTAINER, windowId);
        this.tileEntity = (StoveTileEntity) world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(h ->
        {
            addSlot(new SlotItemHandler(h, 0, 80, 33));
        });
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(h ->
        {
            addSlot(new SlotItemHandler(h, 0, 80, 61)
            {
                @Override
                public boolean isItemValid(ItemStack stack)
                {
                    return false;
                }
            });
        });
        for (int i = 0; i < 3; ++i)
        {

            for (int j = 0; j < 9; ++j)
            {
                addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 51 + i * 18 + 33));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, tileEntity.getBlockState().getBlock());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        Slot slot = this.inventorySlots.get(index);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged;

        // 0~1: Fuel; 1~2: Ash 2~29: Player Backpack; 29~38: Hot Bar.

        if (index < 2)
        {
            isMerged = mergeItemStack(newStack, 29, 38, true)
                    || mergeItemStack(newStack, 2, 29, false);
        }
        else if (index < 29)
        {
            isMerged = mergeItemStack(newStack, 0, 1, false)
                    || mergeItemStack(newStack, 29, 38, true);
        }
        else
        {
            isMerged = mergeItemStack(newStack, 0, 1, false)
                    || mergeItemStack(newStack, 2, 29, false);
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

    public StoveTileEntity getTileEntity()
    {
        return tileEntity;
    }
}
