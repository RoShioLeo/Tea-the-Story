package roito.teastory.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import roito.teastory.TeaStory;
import roito.teastory.block.EmptyKettle;
import roito.teastory.item.ItemCup;
import roito.teastory.item.ItemTeaLeaf;
import roito.teastory.item.ItemWaterPot;

public class TileEntityTeaTable extends TileEntity implements ITickable
{
	protected int teaTime = 0;
	
	protected ItemStackHandler InventoryTool = new ItemStackHandler();
	protected ItemStackHandler InventoryWater = new ItemStackHandler();
	protected ItemStackHandler InventoryLeaf = new ItemStackHandler();
	protected ItemStackHandler InventoryCup = new ItemStackHandler();
	protected ItemStackHandler InventorySugar = new ItemStackHandler();
	protected ItemStackHandler InventoryDrink = new ItemStackHandler();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
		{
			if(facing == EnumFacing.EAST)
			{
				return (T) InventoryTool;
			}
			else if(facing == EnumFacing.SOUTH)
			{
				return (T) InventoryWater;
			}
			else if(facing == EnumFacing.UP)
			{
				return (T) InventoryLeaf;
			}
			else if(facing == EnumFacing.NORTH)
			{
				return (T) InventoryCup;
			}
			else if(facing == EnumFacing.WEST)
			{
				return (T) InventorySugar;
			}
			else
			{
				return (T) InventoryDrink;
			}
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.InventoryTool.deserializeNBT(compound.getCompoundTag("InventoryTool"));
		this.InventoryWater.deserializeNBT(compound.getCompoundTag("InventoryWater"));
		this.InventoryLeaf.deserializeNBT(compound.getCompoundTag("InventoryLeaf"));
		this.InventoryCup.deserializeNBT(compound.getCompoundTag("InventoryCup"));
		this.InventorySugar.deserializeNBT(compound.getCompoundTag("InventorySugar"));
		this.InventoryDrink.deserializeNBT(compound.getCompoundTag("InventoryDrink"));
		this.teaTime = compound.getInteger("TeaTime");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("InventoryTool", this.InventoryTool.serializeNBT());
		compound.setTag("InventoryWater", this.InventoryWater.serializeNBT());
		compound.setTag("InventoryLeaf", this.InventoryLeaf.serializeNBT());
		compound.setTag("InventoryCup", this.InventoryCup.serializeNBT());
		compound.setTag("InventorySugar", this.InventorySugar.serializeNBT());
		compound.setTag("InventoryDrink", this.InventoryDrink.serializeNBT());
		compound.setInteger("TeaTime", this.teaTime);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update()
	{
		//TODO 改用Recipe
		if (!this.worldObj.isRemote)
		{
			ItemStack leaf = InventoryLeaf.extractItem(0, 1, true);
			ItemStack cup = InventoryCup.extractItem(0, 1, true);
			ItemStack water = InventoryWater.extractItem(0, 1, true);
			if(leaf != null && leaf.getItem() instanceof ItemTeaLeaf && cup != null && (cup.getItem() instanceof ItemCup || Block.getBlockFromItem(cup.getItem()) instanceof EmptyKettle) && water != null && water.getItem() instanceof ItemWaterPot)
			{
				boolean teaset = cup.getItem() instanceof ItemCup;  //True for Cups
				ItemStack tool = InventoryTool.extractItem(0, 1, true);
				ItemTeaLeaf teaLeaf = ((ItemTeaLeaf) leaf.getItem());
				String drinkName = teaLeaf.getTeaDrinkName(tool != null ? tool.getItem() : null);
				if (drinkName != null && (InventoryDrink.insertItem(0, new ItemStack(Item.getByNameOrId(TeaStory.MODID + ":" + drinkName), 1, cup.getMetadata()), true) == null || InventoryDrink.extractItem(0, 1, true) == null))
				{
					boolean sugar = teaLeaf.needSugar(drinkName);
					int toolKind = teaLeaf.getToolType(drinkName);	// 0: Null, 1: Amount, 2:Damage, 3:Bucket
					int waterRemain = ((ItemWaterPot) water.getItem()).getRemainWater(water);
					int toolRemain;
					switch (toolKind)
					{
						case 1:
							toolRemain = InventoryTool.getStackInSlot(0).stackSize;
							break;
						case 2:
							toolRemain = InventoryTool.getStackInSlot(0).getMaxDamage() - InventoryTool.getStackInSlot(0).getItemDamage() + 1;
							break;
						case 3:
							toolRemain = 16;
							break;
						default:
							toolRemain = 0;
					}
					int cc = 1;
					int teaAmount = ((ItemTeaLeaf)InventoryLeaf.getStackInSlot(0).getItem()).getAmount();
					if (!teaset)
					{
						int kettleCapacity = ((EmptyKettle)Block.getBlockFromItem(cup.getItem())).getKettleName() == "porcelain_kettle" ? 4 : 8;
						cc =getMin(waterRemain, kettleCapacity, InventoryLeaf.getStackInSlot(0).stackSize / teaAmount, toolKind != 0, toolRemain, sugar, sugar ? (InventorySugar.getStackInSlot(0) != null && InventorySugar.getStackInSlot(0).getItem() == Items.SUGAR) ? InventorySugar.getStackInSlot(0).stackSize / 3 : 0 : 32767);
					}
					else
					{
						cc =getMin(waterRemain, 1, InventoryLeaf.getStackInSlot(0).stackSize / teaAmount, toolKind != 0, toolRemain, sugar, sugar ? (InventorySugar.getStackInSlot(0) != null && InventorySugar.getStackInSlot(0).getItem() == Items.SUGAR) ? InventorySugar.getStackInSlot(0).stackSize / 3 : 0 : 32767);
					}
					if (cc != 0)
					{
						this.teaTime++;
					}
					else
					{
						this.teaTime = 0;
					}
					if (teaTime >= 400)
					{
						if (teaset)
						{
							InventoryDrink.insertItem(0, new ItemStack(Item.getByNameOrId(TeaStory.MODID + ":" + drinkName), 1, cup.getMetadata()), false);
						}
						else
						{
							if (cc > 4)
							{
								InventoryDrink.insertItem(0, new ItemStack(Block.getBlockFromName(TeaStory.MODID + ":" + drinkName + "_" + ((EmptyKettle)Block.getBlockFromItem(cup.getItem())).getKettleName() + String.valueOf((cc - 1) / 4 + 1)), 1, ((3 - (cc - 1) % 4)) << 2), false);
							}
							else
							{
								InventoryDrink.insertItem(0, new ItemStack(Block.getBlockFromName(TeaStory.MODID + ":" + drinkName + "_" + ((EmptyKettle)Block.getBlockFromItem(cup.getItem())).getKettleName()), 1, (4 - cc) << 2), false);
							}
						}
						InventoryWater.setStackInSlot(0, ((ItemWaterPot) InventoryWater.getStackInSlot(0).getItem()).getNext(InventoryWater.getStackInSlot(0), cc));
						InventoryLeaf.extractItem(0, teaAmount * cc, false);
						InventoryCup.extractItem(0, 1, false);
						if (toolKind != 0)
						{
							if (toolKind == 1)
							{
								InventoryTool.extractItem(0, cc, false);
							}
							else if (toolKind == 2)
							{
								if (InventoryTool.getStackInSlot(0).getItemDamage() + cc <= InventoryTool.getStackInSlot(0).getMaxDamage())
								{
									InventoryTool.setStackInSlot(0, new ItemStack(InventoryTool.getStackInSlot(0).getItem(), 1, InventoryTool.getStackInSlot(0).getItemDamage() + cc));
								}
								else
								{
									InventoryTool.extractItem(0, 1, false);
								}
							}
							else if (toolKind == 3)
							{
								InventoryTool.setStackInSlot(0, InventoryTool.getStackInSlot(0).getItem().getContainerItem(InventoryTool.getStackInSlot(0)));
							}
						}
						if (sugar)
						{
							InventorySugar.extractItem(0, cc * 3, false);
						}
						this.teaTime = 0;
					}
				}
				else
				{
					this.teaTime = 0;
				}
			}
			else
			{
				this.teaTime = 0;
			}
			this.markDirty();
		}
	}
	
	public static int getMin(int hotWater, int kettleCapacity, int teaLeaf, boolean tool, int toolRemain, boolean sugar, int sugars)
	{
		int min = Math.min(hotWater, Math.min(kettleCapacity, teaLeaf));
		if (tool)
		{
			min = Math.min(min, toolRemain);
		}
		if (sugar)
		{
			min = Math.min(min, sugars);
		}
		return min;
	}
	
	public int getTeaTime()
	{
		return this.teaTime;
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return oldState.getBlock() != newState.getBlock();
	}
}
