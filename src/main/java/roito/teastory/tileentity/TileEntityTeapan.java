package roito.teastory.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import roito.teastory.block.BlockLoader;
import roito.teastory.block.Teapan;
import roito.teastory.config.ConfigMain;
import roito.teastory.helper.EntironmentHelper;
import roito.teastory.item.ItemLoader;

public class TileEntityTeapan extends TileEntity implements ITickable
{
	protected int realTicks = 0;
	protected int totalTicks = 0;
	
	protected ItemStackHandler leafInventory = new ItemStackHandler(3);

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
			T result = (T) (leafInventory);
			return result;
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.leafInventory.deserializeNBT(compound.getCompoundTag("LeafInventory"));
		this.realTicks = compound.getInteger("RealTick");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("LeafInventory", this.leafInventory.serializeNBT());
		compound.setInteger("RealTick", this.realTicks);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update()
	{
		if (!this.getWorld().isRemote)
		{
			Biome biome = this.getWorld().getBiome(pos);
			int basicTick = EntironmentHelper.getDryingTicks(biome.getFloatTemperature(pos), biome.getRainfall());
			int step = this.getBlockMetadata();
			if (this.getWorld().isRainingAt(pos.up()))
			{
				for (int i = 0; i < 3; i++)
				{
					if (leafInventory.getStackInSlot(i) != null && leafInventory.getStackInSlot(i).getItem() != ItemLoader.wet_tea)
					{
						leafInventory.setStackInSlot(i, new ItemStack(ItemLoader.wet_tea, leafInventory.getStackInSlot(i).stackSize));
					}
				}
				realTicks = 0;
			}
			int wetLeafNumber = getLeafNumber(ItemLoader.wet_tea);
			if (wetLeafNumber > 0)
			{
				if (step != 1)
				{
					Teapan.setState(1, worldObj, pos);
				}
				totalTicks = (wetLeafNumber < 16 ? 16 : wetLeafNumber) * basicTick;
				jugde(ItemLoader.wet_tea, ItemLoader.tea_leaf);
				this.markDirty();
				return;
			}
			int freshLeafNumber = getLeafNumber(ItemLoader.tea_leaf);
			if (freshLeafNumber > 0)
			{
				if (step != 2)
				{
					Teapan.setState(2, worldObj, pos);
				}
				totalTicks = (freshLeafNumber < 16 ? 16 : freshLeafNumber) * basicTick;
				jugde(ItemLoader.tea_leaf, ItemLoader.half_dried_tea);
				this.markDirty();
				return;
			}
			int halfDriedLeafNumber = getLeafNumber(ItemLoader.half_dried_tea);
			int driedLeafNumber = getLeafNumber(ItemLoader.dried_tea);
			if (halfDriedLeafNumber > 0)
			{
				if (step != 3)
				{
					Teapan.setState(3, worldObj, pos);
				}
				if (!this.getWorld().canSeeSky(pos))
				{
					if (driedLeafNumber > 0)
					{
						totalTicks = (driedLeafNumber < 16 ? 16 : driedLeafNumber) * basicTick;
						jugde(ItemLoader.dried_tea, ItemLoader.yellow_tea_leaf);
						this.markDirty();
						return;
					}
					realTicks = 0;
					return;
				}
				totalTicks = (halfDriedLeafNumber < 16 ? 16 : halfDriedLeafNumber) * basicTick / 2;
				jugde(ItemLoader.half_dried_tea, ItemLoader.dried_tea);
				this.markDirty();
				return;
			}
			if (driedLeafNumber > 0)
			{
				if (step != 4)
				{
					Teapan.setState(4, worldObj, pos);
				}
				if (this.getWorld().canSeeSky(pos))
				{
					realTicks = 0;
					return;
				}
				totalTicks = (driedLeafNumber < 16 ? 16 : driedLeafNumber) * basicTick;
				jugde(ItemLoader.dried_tea, ItemLoader.yellow_tea_leaf);
				this.markDirty();
				return;
			}

			realTicks = 0;
			int yellowLeafNumber = getLeafNumber(ItemLoader.yellow_tea_leaf);
			if (yellowLeafNumber > 0)
			{
				if (step != 5)
				{
					Teapan.setState(5, worldObj, pos);
				}
				return;
			}
			if (step != 0)
			{
				Teapan.setState(0, worldObj, pos);
			}
			this.markDirty();
		}
	}
	
	private void jugde(Item before, Item after)
	{
		if (!isRaining() && enoughLight())
		{
			realTicks++;
		}
		if (this.realTicks >= this.totalTicks)
		{
			for (int i = 0; i < 3; i++)
			{
				if (leafInventory.getStackInSlot(i) != null && leafInventory.getStackInSlot(i).getItem() == before)
				{
					leafInventory.setStackInSlot(i, new ItemStack(after, leafInventory.getStackInSlot(i).stackSize));
				}
			}
			realTicks = 0;
		}
	}
	
	public int getStep()
	{
		if (getLeafNumber(ItemLoader.wet_tea) > 0)
		{
			return 1;
		}
		else if (getLeafNumber(ItemLoader.tea_leaf) > 0)
		{
			return 2;
		}
		else if (getLeafNumber(ItemLoader.half_dried_tea) > 0)
		{
			return 3;
		}
		else if (getLeafNumber(ItemLoader.dried_tea) > 0)
		{
			return 4;
		}
		else if (getLeafNumber(ItemLoader.yellow_tea) > 0)
		{
			return 5;
		}
		return 0;
	}
	
	public int getTotalTicks()
	{
		return this.totalTicks;
	}
	
	public boolean isRaining()
	{
		return this.getWorld().isRaining();
	}
	
	public boolean enoughLight()
	{
		return this.getWorld().getLightFromNeighbors(pos) >= 10;
	}
	
	public int getRealTicks()
	{
		return this.realTicks;
	}
	
	public int getLeafNumber(Item leaf)
	{
		int leafNumber = 0;
		for (int i = 0; i < 3; i++)
		{
			if (leafInventory.getStackInSlot(i) != null && leafInventory.getStackInSlot(i).getItem() == leaf)
			{
				leafNumber += leafInventory.getStackInSlot(i).stackSize;
			}
		}
		return leafNumber;
	}
}
