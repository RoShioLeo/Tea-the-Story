package roito.teastory.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import roito.teastory.api.recipe.IRecipeManager;
import roito.teastory.api.recipe.ITeaMakingRecipe;
import roito.teastory.api.recipe.TeaMakingRecipe;
import roito.teastory.common.RecipeRegister;
import roito.teastory.helper.EntironmentHelper;
import roito.teastory.helper.NonNullListHelper;

public class TileEntityTeapan extends TileEntity implements ITickable
{
	protected int processTicks = 0;
	protected int totalTicks = 0;
	protected ITeaMakingRecipe usedRecipe = new TeaMakingRecipe(NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY);

	protected ItemStackHandler leafInventory = new ItemStackHandler();

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
			return (T) leafInventory;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.leafInventory.deserializeNBT(compound.getCompoundTag("LeafInventory"));
		this.processTicks = compound.getInteger("ProcessTick");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("LeafInventory", this.leafInventory.serializeNBT());
		compound.setInteger("ProcessTick", this.processTicks);
		return super.writeToNBT(compound);
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.getNbtCompound());
	}

	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			Biome biome = this.getWorld().getBiome(pos);
			switch (this.getMode())
			{
				case -1:
				{
					refreshTotalTicks(0);
					getWet();
					return;
				}
				case 1:
				{
					refreshTotalTicks(EntironmentHelper.getFermentationTicks(biome.getRainfall(), biome.getTemperature(pos)));
					process(RecipeRegister.managerTeapanIndoors);
					return;
				}
				default:
				{
					if (!this.isWorldRaining())
					{
						refreshTotalTicks(EntironmentHelper.getDryingTicks(biome.getRainfall(), biome.getTemperature(pos)));
						process(RecipeRegister.managerTeapanInSun);
					}
					return;
				}
			}
		}
	}

	private boolean process(IRecipeManager<ITeaMakingRecipe> recipeManager)
	{
		ItemStack input = this.leafInventory.getStackInSlot(0).copy();
		if (input.isEmpty())
		{
			this.processTicks = 0;
			this.markDirty();
			return false;
		}
		if (!usedRecipe.isTheSameInput(input))
		{
			usedRecipe = recipeManager.getRecipe(input);
		}
		if (!usedRecipe.getOutput().isEmpty())
		{
			if (++this.processTicks >= this.totalTicks)
			{
				ItemStack output = usedRecipe.getOutput().copy();
				output.setCount(input.getCount());
				this.leafInventory.setStackInSlot(0, output);
				refresh();
				this.processTicks = 0;
			}
			this.markDirty();
			return true;
		}
		this.processTicks = 0;
		this.markDirty();
		return false;
	}

	private void getWet()
	{
		ItemStack input = this.leafInventory.getStackInSlot(0).copy();
		this.processTicks = 0;
		if (!usedRecipe.isTheSameInput(input))
		{
			usedRecipe = RecipeRegister.managerWet.getRecipe(input);
		}
		if (!usedRecipe.getOutput().isEmpty())
		{
			ItemStack wetOutput = usedRecipe.getOutput().copy();
			wetOutput.setCount(input.getCount());
			this.leafInventory.setStackInSlot(0, wetOutput);
			refresh();
		}
		this.markDirty();
	}

	/*
		-1 for in rain, 0 for drying, 1 for fermentation, 2 for bake.
	 */
	public int getMode()
	{
		if (this.isInRain())
		{
			return -1;
		}
		else if (!this.getWorld().canSeeSky(this.getPos()))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public NonNullList<ItemStack> getContents()
	{
		NonNullList<ItemStack> list = NonNullList.create();
		for (int i = this.leafInventory.getStackInSlot(0).getCount(); i > 0; i -= 16)
		{
			list.add(this.leafInventory.getStackInSlot(0));
		}
		return list;
	}

	public void refreshTotalTicks(int basicTime)
	{
		if (leafInventory.getStackInSlot(0).getCount() >= 32)
		{
			this.totalTicks = 32;
		}
		else if (leafInventory.getStackInSlot(0).getCount() > 0 && leafInventory.getStackInSlot(0).getCount() <= 8)
		{
			this.totalTicks = 8;
		}
		else
		{
			this.totalTicks = leafInventory.getStackInSlot(0).getCount();
		}
		this.totalTicks *= basicTime;
	}

	public int getTotalTicks()
	{
		return this.totalTicks;
	}

	public boolean isWorldRaining()
	{
		return this.getWorld().isRaining();
	}

	public boolean isInRain()
	{
		return this.getWorld().isRainingAt(pos.up());
	}

	public boolean hasEnoughLight()
	{
		return this.getWorld().getLightFromNeighbors(pos) >= 9;
	}

	public int getProcessTicks()
	{
		return this.processTicks;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
	{
		return oldState.getBlock() != newSate.getBlock();
	}

	void refresh()
	{
		if (hasWorld() && !world.isRemote)
		{
			IBlockState state = world.getBlockState(pos);
			world.markAndNotifyBlock(pos, null, state, state, 11);
		}
	}
}
