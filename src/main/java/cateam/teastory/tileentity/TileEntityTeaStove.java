package cateam.teastory.tileentity;

import cateam.teastory.block.TeaStove;
import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityTeaStove extends TileEntity implements ITickable
{
	protected int dryTime = 0;
	protected int fuelTime = 0;
	protected int fuelTotalTime = 1;
	protected int hasWater = -1;
	protected int steam = 0;

	protected ItemStackHandler leafInventory = new ItemStackHandler();
	protected ItemStackHandler fuelInventory = new ItemStackHandler();
	protected ItemStackHandler outputInventory = new ItemStackHandler();

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
			T result = (T) (facing == EnumFacing.DOWN ? outputInventory : facing == EnumFacing.UP ? leafInventory : fuelInventory);
			return result;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.leafInventory.deserializeNBT(compound.getCompoundTag("LeafInventory"));
		this.fuelInventory.deserializeNBT(compound.getCompoundTag("FuelInventory"));
		this.outputInventory.deserializeNBT(compound.getCompoundTag("OutputInventory"));
		this.dryTime = compound.getInteger("DryTime");
		this.fuelTime = compound.getInteger("FuelTime");
		this.fuelTotalTime = compound.getInteger("FuelTotalTime");
		this.steam = compound.getInteger("Steam");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("LeafInventory", this.leafInventory.serializeNBT());
		compound.setTag("FuelInventory", this.fuelInventory.serializeNBT());
		compound.setTag("OutputInventory", this.outputInventory.serializeNBT());
		compound.setInteger("DryTime", this.dryTime);
		compound.setInteger("FuelTime", this.fuelTime);
		compound.setInteger("FuelTotalTime", this.fuelTotalTime);
		compound.setInteger("Steam", this.steam);
		return super.writeToNBT(compound);
	}

	@Override
	public void update()
	{
		//TODO 改用Recipe
		ItemStack teaLeaf = leafInventory.extractItem(0, 1, true);
		if (!this.getWorld().isRemote)
		{
			if (teaLeaf != null && ((teaLeaf.getItem() == ItemLoader.half_dried_tea && outputInventory.insertItem(0, new ItemStack(ItemLoader.matcha_leaf), true) == null) || (teaLeaf.getItem() == ItemLoader.tea_leaf && outputInventory.insertItem(0, new ItemStack(ItemLoader.white_tea_leaf), true) == null)))
			{
				if (teaLeaf.getItem() == ItemLoader.half_dried_tea)
				{
					if (this.hasWaterOrSteam())
					{
						if (this.hasFuelOrIsBurning())
						{
							if (!(this.getSteam() > 0))
							{
								this.worldObj.setBlockState(pos.up(), Blocks.CAULDRON.getStateFromMeta(this.hasWater() - 1));
								this.steam = this.getTotalSteam();
							}
							this.dryTime++;
						}
						else
						{
							this.dryTime = 0;
						}
					}
					else
					{
						this.dryTime = 0;
					}
				}
				else
				{
					if (this.hasFuelOrIsBurning())
					{
						this.dryTime++;
					}
					else
					{
						this.dryTime = 0;
					}
				}
			}
			else
			{
				this.dryTime = 0;
			}
			this.fuelTime--;
			if (this.fuelTime == 0)
			{
				TeaStove.setState(false, this.worldObj, this.pos);
			}
			if (this.dryTime == this.getTotalDryTime())
			{
				leafInventory.extractItem(0, 1, false);
				if (teaLeaf.getItem() == ItemLoader.half_dried_tea)
				{
					outputInventory.insertItem(0, new ItemStack(ItemLoader.matcha_leaf), false);
					this.steam--;
				}
				else if (teaLeaf.getItem() == ItemLoader.tea_leaf)
				{
					outputInventory.insertItem(0, new ItemStack(ItemLoader.white_tea_leaf), false);
				}
				this.dryTime = 0;
			}
			this.markDirty();
		}
	}

	public boolean hasFuelOrIsBurning()
	{
		if (this.isBurning())
		{
			return true;
		}
		else
		{
			ItemStack itemFuel = fuelInventory.extractItem(0, 1, true);
			if(isItemFuel(itemFuel))
			{
				this.fuelTime = getItemBurnTime(itemFuel);
				this.fuelTotalTime = getItemBurnTime(itemFuel);
				Item cItem = fuelInventory.getStackInSlot(0).getItem().getContainerItem();
				if (cItem != null)
				{
					fuelInventory.extractItem(0, 1, false);
					fuelInventory.insertItem(0, new ItemStack(cItem, 1), false);
				}
				else fuelInventory.extractItem(0, 1, false);
				this.markDirty();
				TeaStove.setState(true, this.worldObj, this.pos);
				return true;
			}
			else return false;
		}
	}
	
	public boolean isBurning()
	{
		if (this.fuelTime > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static int getItemBurnTime(ItemStack stack)
	{
		if (stack == null)
		{
			return 0;
		}
		else
		{
			Item item = stack.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
			{
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.WOODEN_SLAB)
				{
					return 150;
				}

				if (block.getDefaultState().getMaterial() == Material.WOOD)
				{
					return 300;
				}

				if (block == Blocks.COAL_BLOCK)
				{
					return 16000;
				}
			}

			if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
			if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
			if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return 200;
			if (item == Items.STICK) return 100;
			if (item == Items.COAL) return 1600;
			if (item == Items.LAVA_BUCKET) return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
			if (item == Items.BLAZE_ROD) return 2400;
			return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
		}
	}

	public static boolean isItemFuel(ItemStack stack)
	{
		return getItemBurnTime(stack) > 0;
	}

	public int getDryTime()
	{
		return this.dryTime;
	}

	public int getTotalDryTime()
	{
		return 1200;
	}

	public int getFuelTime()
	{
		return this.fuelTime;
	}

	public int getFuelTotalTime()
	{
		return this.fuelTotalTime;
	}

	public int getTotalSteam()
	{
		return 32;
	}

	public int hasWater()
	{
		IBlockState state = this.worldObj.getBlockState(pos.up());
		if(state.getBlock() == Blocks.CAULDRON)
		{
			this.hasWater = Blocks.CAULDRON.getMetaFromState(state);
		}
		else this.hasWater = -1;
		return this.hasWater;
	}
	
	public boolean hasWaterOrSteam()
	{
		if (this.getSteam() > 0)
		{
			return true;
		}
		else
		{
			if (this.hasWater() > 0)
			{
				return true;
			}
			else return false;
		}
	}

	public int getSteam()
	{
		return this.steam;
	}

	public void setSteam(int steam)
	{
		this.steam = steam;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return oldState.getBlock() != newState.getBlock();
	}
}