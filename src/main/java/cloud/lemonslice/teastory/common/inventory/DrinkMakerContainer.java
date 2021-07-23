package cloud.lemonslice.teastory.common.inventory;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.tileentity.DrinkMakerTileEntity;
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

public class DrinkMakerContainer extends NormalContainer
{
    private DrinkMakerTileEntity tileEntity;

    public DrinkMakerContainer(int windowId, PlayerInventory inv, BlockPos pos, World world)
    {
        super(ContainerTypeRegistry.DRINK_MAKER_CONTAINER, windowId);
        this.tileEntity = (DrinkMakerTileEntity) world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(h ->
        {
            addSlot(new SlotItemHandler(h, 0, 21, 24));
            addSlot(new SlotItemHandler(h, 1, 39, 24));
            addSlot(new SlotItemHandler(h, 2, 57, 24));
            addSlot(new SlotItemHandler(h, 3, 75, 24));
        });
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(h ->
        {
            addSlot(new SlotItemHandler(h, 0, 21, 51));
            addSlot(new SlotItemHandler(h, 1, 39, 51));
            addSlot(new SlotItemHandler(h, 2, 57, 51));
            addSlot(new SlotItemHandler(h, 3, 75, 51));
        });
        addPlayerInventory(inv);
        tileEntity.getContainerInventory().ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 102, 13)));
        tileEntity.getInputInventory().ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 152, 15)));
        tileEntity.getOutputInventory().ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 152, 59)));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, BlockRegistry.DRINK_MAKER);
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

        if (index < 4)
        {
            isMerged = mergeItemStack(newStack, 8, 44, true);
        }
        else if (index < 8)
        {
            isMerged = mergeItemStack(newStack, 8, 44, true);
        }
        else if (index < 35)
        {
            isMerged = mergeItemStack(newStack, 0, 4, false)
                    || mergeItemStack(newStack, 35, 44, false);
        }
        else if (index < 44)
        {
            isMerged = mergeItemStack(newStack, 0, 4, false)
                    || mergeItemStack(newStack, 8, 35, false);
        }
        else
        {
            isMerged = mergeItemStack(newStack, 8, 44, true);
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

    public DrinkMakerTileEntity getTileEntity()
    {
        return tileEntity;
    }
}
