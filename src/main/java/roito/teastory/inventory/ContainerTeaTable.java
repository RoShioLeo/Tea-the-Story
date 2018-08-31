package roito.teastory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.item.ItemBlockEmptyKettle;
import roito.teastory.item.ItemCup;
import roito.teastory.item.ItemTeaLeaf;
import roito.teastory.item.ItemWaterPot;
import roito.teastory.tileentity.TileEntityTeaTable;

public class ContainerTeaTable extends Container
{
    public TileEntityTeaTable tileEntity;
    private IItemHandler toolItem;
    private IItemHandler sugarItem;
    private IItemHandler waterItem;
    private IItemHandler leafItem;
    private IItemHandler cupItem;
    private IItemHandler drinkItem;
    protected int teaTime = 0;

    public ContainerTeaTable(EntityPlayer player, TileEntity tileEntity)
    {
        super();
        this.toolItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        this.waterItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.SOUTH);
        this.leafItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.cupItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        this.sugarItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.WEST);
        this.drinkItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        this.addSlotToContainer(new SlotItemHandler(this.toolItem, 0, 44, 24));
        this.addSlotToContainer(new SlotItemHandler(this.sugarItem, 0, 62, 24)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return OreDictionary.containsMatch(false, OreDictionary.getOres("listAllsugar"), stack);
            }
        });
        this.addSlotToContainer(new SlotItemHandler(this.waterItem, 0, 44, 42)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack.getItem() instanceof ItemWaterPot;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(this.leafItem, 0, 62, 42)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack.getItem() instanceof ItemTeaLeaf;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(this.cupItem, 0, 116, 20)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack.getItem() instanceof ItemCup || stack.getItem() instanceof ItemBlockEmptyKettle;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(this.drinkItem, 0, 116, 48)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return false;
            }
        });
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
        this.tileEntity = (TileEntityTeaTable) tileEntity;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged = false;

        if (index >= 0 && index < 6)
        {
            isMerged = mergeItemStack(newStack, 6, 42, true);
        } else if (index >= 6 && index < 33)
        {
            isMerged = mergeItemStack(newStack, 1, 5, false)
                    || mergeItemStack(newStack, 33, 42, false);
        } else if (index >= 33 && index < 42)
        {
            isMerged = mergeItemStack(newStack, 1, 5, false)
                    || mergeItemStack(newStack, 6, 33, false);
        }

        if (!isMerged)
        {
            return ItemStack.EMPTY;
        }

        if (newStack.getCount() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        } else
        {
            slot.onSlotChanged();
        }

        slot.onTake(playerIn, newStack);

        return oldStack;
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.teaTime = tileEntity.getTeaTime();

        for (IContainerListener i : this.listeners)
        {
            i.sendWindowProperty(this, 0, this.teaTime);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);
        switch (id)
        {
            case 0:
                this.teaTime = data;
                break;
            default:
                break;
        }
    }

    public int getTeaTime()
    {
        return this.teaTime;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
    }
}
