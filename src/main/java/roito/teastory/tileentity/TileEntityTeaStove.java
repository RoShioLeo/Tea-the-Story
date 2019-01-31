package roito.teastory.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import roito.teastory.api.recipe.ITeaMakingRecipe;
import roito.teastory.api.recipe.TeaMakingRecipe;
import roito.teastory.block.TeaStove;
import roito.teastory.common.RecipeRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.helper.NonNullListHelper;

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

	protected ITeaMakingRecipe usedSteamRecipe = new TeaMakingRecipe(NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY);
	protected ITeaMakingRecipe usedDryingRecipe = new TeaMakingRecipe(NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY);

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
		if (!world.isRemote)
		{
			ItemStack input = this.leafInventory.getStackInSlot(0).copy();
			if (input.isEmpty())
			{
				this.dryTime = 0;
				reduceFuelTime();
				this.markDirty();
				return;
			}
			if (hasWaterOrSteam())      // 蒸青模式
			{
				if (!usedSteamRecipe.isTheSameInput(input))
				{
					usedSteamRecipe = RecipeRegister.managerStoveSteam.getRecipe(input);
				}
				if (!usedSteamRecipe.getOutput().isEmpty())
				{
					process(true);
					reduceFuelTime();
					this.markDirty();
					return;
				}
			}
			//烘青模式
			if (!usedDryingRecipe.isTheSameInput(input))
			{
				usedDryingRecipe = RecipeRegister.managerStoveDrying.getRecipe(input);
			}
			if (!usedDryingRecipe.getOutput().isEmpty())
			{
				process(false);
				reduceFuelTime();
				this.markDirty();
				return;
			}
		}
	}

	public void reduceFuelTime()
	{
		if (isBurning())
		{
			this.fuelTime--;

			if (this.fuelTime == 0)
			{
				ItemStack itemFuel = fuelInventory.extractItem(0, 1, true);
				if (!isItemFuel(itemFuel))
				{
					TeaStove.setState(false, this.getWorld(), this.pos);
				}
			}
		}
	}

	public void process(boolean needWater)
	{
		if (needWater && (outputInventory.insertItem(0, usedSteamRecipe.getOutput().copy(), true).isEmpty()))
		{
			if (this.hasWaterOrSteam())
			{
				if (this.hasFuelOrIsBurning())
				{
					if (!(this.getSteam() > 0))
					{
						this.getWorld().setBlockState(pos.up(), Blocks.CAULDRON.getStateFromMeta(this.hasWater() - 1));
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
		else if (!needWater && (outputInventory.insertItem(0, usedDryingRecipe.getOutput().copy(), true).isEmpty()))
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
		else
		{
			this.dryTime = 0;
		}
		if (this.dryTime == this.getTotalDryTime())
		{
			leafInventory.extractItem(0, 1, false);
			if (needWater)
			{
				outputInventory.insertItem(0, usedSteamRecipe.getOutput().copy(), false);
				this.steam--;
			}
			else
			{
				outputInventory.insertItem(0, usedDryingRecipe.getOutput().copy(), false);
			}
			this.dryTime = 0;
		}
		this.markDirty();
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
			if (isItemFuel(itemFuel))
			{
				this.fuelTime = getItemBurnTime(itemFuel);
				this.fuelTotalTime = getItemBurnTime(itemFuel);
				Item cItem = fuelInventory.getStackInSlot(0).getItem().getContainerItem();
				if (cItem != null)
				{
					fuelInventory.extractItem(0, 1, false);
					fuelInventory.insertItem(0, new ItemStack(cItem, 1), false);
				}
				else
				{
					fuelInventory.extractItem(0, 1, false);
				}
				this.markDirty();
				TeaStove.setState(true, this.getWorld(), this.pos);
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	public boolean isBurning()
	{
		return this.fuelTime > 0;
	}

	public static int getItemBurnTime(ItemStack stack)
	{
		return TileEntityFurnace.getItemBurnTime(stack);
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
		return ConfigMain.teaMaking.steamingBasicTime;
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
		IBlockState state = this.getWorld().getBlockState(pos.up());
		if (state.getBlock() == Blocks.CAULDRON)
		{
			this.hasWater = Blocks.CAULDRON.getMetaFromState(state);
		}
		else
		{
			this.hasWater = -1;
		}
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
			return this.hasWater() > 0;
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