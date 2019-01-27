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
import roito.teastory.tileentity.TileEntityTeapan;

public class ContainerTeapan extends Container
{
	public TileEntityTeapan tileEntity;
	private IItemHandler inputItem;
	protected int processTicks = 0;
	protected int totalTicks = 0;
	protected int isRaining = 0;
	protected int isInRain = 0;
	protected int mode = 0;

	public ContainerTeapan(EntityPlayer player, TileEntity tileEntity)
	{
		super();
		this.inputItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		this.addSlotToContainer(new SlotItemHandler(this.inputItem, 0, 80, 31));

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
		this.tileEntity = (TileEntityTeapan) tileEntity;
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
			return ItemStack.EMPTY;
		}

		ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

		boolean isMerged = false;

		if (index >= 0 && index < 1)
		{
			isMerged = mergeItemStack(newStack, 1, 37, true);
		}
		else if (index >= 1 && index < 28)
		{
			isMerged = mergeItemStack(newStack, 0, 1, false)
					|| mergeItemStack(newStack, 29, 38, false);
		}
		else if (index >= 28 && index < 37)
		{
			isMerged = mergeItemStack(newStack, 0, 1, false)
					|| mergeItemStack(newStack, 1, 28, false);
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

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		this.processTicks = tileEntity.getProcessTicks();
		this.totalTicks = tileEntity.getTotalTicks();
		this.isRaining = tileEntity.isWorldRaining() ? 1 : 0;
		this.isInRain = tileEntity.isInRain() ? 1 : 0;
		this.mode = tileEntity.getMode();

		for (IContainerListener i : this.listeners)
		{
			i.sendWindowProperty(this, 0, this.processTicks);
			i.sendWindowProperty(this, 1, this.totalTicks);
			i.sendWindowProperty(this, 2, this.isRaining);
			i.sendWindowProperty(this, 3, this.isInRain);
			i.sendWindowProperty(this, 5, this.mode);
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
				this.processTicks = data;
				break;
			case 1:
				this.totalTicks = data;
				break;
			case 2:
				this.isRaining = data;
				break;
			case 3:
				this.isInRain = data;
				break;
			case 5:
				this.mode = data;
				break;
			default:
				break;
		}
	}

	public int getProcessTicks()
	{
		return this.processTicks;
	}

	public int getTotalTicks()
	{
		return this.totalTicks;
	}

	public boolean isRaining()
	{
		return this.isRaining == 1;
	}

	public boolean isInRain()
	{
		return this.isInRain == 1;
	}

	public int getMode()
	{
		return this.mode;
	}
}
