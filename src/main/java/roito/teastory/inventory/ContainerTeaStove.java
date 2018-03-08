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
import roito.teastory.item.ItemLoader;
import roito.teastory.item.ItemTeaLeaf;
import roito.teastory.tileentity.TileEntityTeaStove;

public class ContainerTeaStove extends Container
{
	public TileEntityTeaStove tileEntity;
	private IItemHandler leafItem;
	private IItemHandler fuelItem;
	private IItemHandler driedItem;
	protected int dryTime = 0;
	protected int fuelTime = 0;
	protected int fuelTotalTime = 0;
	protected int hasWater = -1;
	protected int steam = 0;

	public ContainerTeaStove(EntityPlayer player, TileEntity tileEntity)
	{
		super();
		this.leafItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		this.fuelItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		this.driedItem = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		this.addSlotToContainer(new SlotItemHandler(this.leafItem, 0, 53, 20)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return stack.getItem() == ItemLoader.tea_leaf || stack.getItem() == ItemLoader.half_dried_tea;
			}
		});
		this.addSlotToContainer(new SlotItemHandler(this.fuelItem, 0, 53, 56));
		this.addSlotToContainer(new SlotItemHandler(this.driedItem, 0, 116, 38)
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
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18 + 33));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
		this.tileEntity = (TileEntityTeaStove) tileEntity;
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

		if (index >= 0 && index < 2)
		{
			isMerged = mergeItemStack(newStack, 3, 39, true);
		}
		else if (index >= 3 && index < 30)
		{
			isMerged = mergeItemStack(newStack, 0, 1, false)
					|| TileEntityTeaStove.isItemFuel(newStack) && mergeItemStack(newStack, 1, 2, false)
					|| mergeItemStack(newStack, 30, 39, false);
		}
		else if (index >= 30 && index < 39)
		{
			isMerged = mergeItemStack(newStack, 0, 1, false)
					|| TileEntityTeaStove.isItemFuel(newStack) && mergeItemStack(newStack, 1, 2, false)
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
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		this.dryTime = tileEntity.getDryTime();
		this.fuelTime = tileEntity.getFuelTime();
		this.fuelTotalTime = tileEntity.getFuelTotalTime();
		this.hasWater = tileEntity.hasWater();
		this.steam = tileEntity.getSteam();

		for (IContainerListener i : this.listeners)
		{
			i.sendProgressBarUpdate(this, 0, this.dryTime);
			i.sendProgressBarUpdate(this, 1, this.fuelTime);
			i.sendProgressBarUpdate(this, 2, this.fuelTotalTime);
			i.sendProgressBarUpdate(this, 3, this.hasWater);
			i.sendProgressBarUpdate(this, 4, this.steam);
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
			this.dryTime = data;
			break;
		case 1:
			this.fuelTime = data;
		case 2:
			this.fuelTotalTime = data;
		case 3:
			this.hasWater = data;
		case 4:
			this.steam = data;
		default:
			break;
		}
	}

	public int getDryTime()
	{
		return this.dryTime;
	}

	public int getTotalDryTime()
	{
		return this.tileEntity.getTotalDryTime();
	}

	public int getFuelTime()
	{
		return this.fuelTime;
	}

	public int getTotalFuelTime()
	{
		return this.fuelTotalTime;
	}

	public int hasWater()
	{
		return this.hasWater;
	}

	public int getSteam()
	{
		return this.steam;
	}

	public int getTotalSteam()
	{
		return this.tileEntity.getTotalSteam();
	}
}
