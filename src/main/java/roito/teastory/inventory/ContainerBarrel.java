package roito.teastory.inventory;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
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
import roito.teastory.common.AchievementLoader;
import roito.teastory.item.ItemLoader;
import roito.teastory.item.PuerTea;
import roito.teastory.tileentity.TileEntityBarrel;

public class ContainerBarrel extends Container
{
	public TileEntityBarrel tileEntity;
	private IItemHandler leafItem;
	protected int realTicks = 0;
	protected int totalTicks = 0;
	
	public ContainerBarrel(EntityPlayer player, TileEntity tileEntity)
	{
		super();
		this.leafItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		this.addSlotToContainer(new SlotItemHandler(this.leafItem, 0, 44, 31)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return stack.getItem() == ItemLoader.half_dried_tea || stack.getItem() == ItemLoader.black_tea_leaf;
			}
		});
		this.addSlotToContainer(new SlotItemHandler(this.leafItem, 1, 80, 31)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return stack.getItem() == ItemLoader.half_dried_tea || stack.getItem() == ItemLoader.black_tea_leaf;
			}
		});
		this.addSlotToContainer(new SlotItemHandler(this.leafItem, 2, 116, 31)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return stack.getItem() == ItemLoader.half_dried_tea || stack.getItem() == ItemLoader.black_tea_leaf;
			}
		});
		
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18 + 33));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
		this.tileEntity = (TileEntityBarrel) tileEntity;
	}
	
	@Nullable
	@Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
    {
		if ((clickTypeIn == ClickType.PICKUP || clickTypeIn == ClickType.QUICK_MOVE) && (slotId <= 2))
		{
			if (this.leafItem.getStackInSlot(slotId) != null && this.leafItem.getStackInSlot(slotId).getItem() == ItemLoader.black_tea_leaf)
			{
				player.addStat(AchievementLoader.blackTea);
			}
			if (this.leafItem.getStackInSlot(slotId) != null && this.leafItem.getStackInSlot(slotId).getItem() == ItemLoader.puer_tea_leaf)
			{
				player.addStat(AchievementLoader.puerTea);
			}
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		Slot slot = inventorySlots.get(index);

		if (slot == null || !slot.getHasStack())
		{
			return null;
		}

		ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

		boolean isMerged = false;

		if (index >= 0 && index <= 2)
		{
			isMerged = mergeItemStack(newStack, 3, 39, true);
		}
		else if (index >= 3 && index < 30)
		{
			isMerged = mergeItemStack(newStack, 0, 3, false)
					|| mergeItemStack(newStack, 30, 39, false);
		}
		else if (index >= 30 && index < 39)
		{
			isMerged = mergeItemStack(newStack, 0, 3, false)
					|| mergeItemStack(newStack, 3, 30, false);
		}

		if (!isMerged)
		{
			return null;
		}

		if (newStack.stackSize == 0)
		{
			slot.putStack(null);
		}
		else
		{
			slot.onSlotChanged();
		}

		slot.onPickupFromSlot(playerIn, newStack);

		return oldStack;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		this.realTicks = tileEntity.getRealTicks();
		this.totalTicks = tileEntity.getTotalTicks();

		for (IContainerListener i : this.listeners)
		{
			i.sendProgressBarUpdate(this, 0, this.realTicks);
			i.sendProgressBarUpdate(this, 1, this.totalTicks);
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
			this.realTicks = data;
			break;
		case 1:
			this.totalTicks = data;
			break;
		default:
			break;
		}
	}
	
	public int getRealTicks()
	{
		return this.realTicks;
	}
	
	public int getTotalTicks()
	{
		return this.totalTicks;
	}
}
