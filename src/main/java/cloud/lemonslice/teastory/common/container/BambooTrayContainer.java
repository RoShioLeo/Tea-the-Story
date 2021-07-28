package cloud.lemonslice.teastory.common.container;

import cloud.lemonslice.teastory.common.tileentity.BambooTrayTileEntity;
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

import static cloud.lemonslice.teastory.common.container.ContainerTypeRegistry.BAMBOO_TRAY_CONTAINER;

public class BambooTrayContainer extends Container
{
    private final BambooTrayTileEntity tileEntity;

    public BambooTrayContainer(int windowId, PlayerInventory inv, BlockPos pos, World world)
    {
        super(BAMBOO_TRAY_CONTAINER, windowId);
        this.tileEntity = (BambooTrayTileEntity) world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(h ->
                addSlot(new SlotItemHandler(h, 0, 107, 31)));
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

        // 0~1: Input; 1~28: Player Backpack; 28~37: Hot Bar.

        if (index == 0)
        {
            isMerged = mergeItemStack(newStack, 28, 37, true)
                    || mergeItemStack(newStack, 1, 28, false);
        }
        else if (index < 28)
        {
            isMerged = mergeItemStack(newStack, 0, 1, false)
                    || mergeItemStack(newStack, 28, 37, true);
        }
        else
        {
            isMerged = mergeItemStack(newStack, 0, 28, false);
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

    public BambooTrayTileEntity getTileEntity()
    {
        return tileEntity;
    }
}
