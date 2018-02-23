package cateam.teastory.tileentity;

import cateam.teastory.block.LitTeaDryingPan;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityTeaDryingPan extends TileEntity implements ITickable
{
	protected int totalTime = 600;
	protected int time = 0;
	protected int meta;
	protected int remainingTime = totalTime;

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.time = compound.getInteger("Time");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("Time", this.time);
		return super.writeToNBT(compound);
	}

	@Override
	public void update()
	{
		meta = this.getBlockMetadata();
		if ((meta > 0 && meta < 6) || (meta > 8 && meta < 14))
		{

			if (++time >= totalTime)
			{
				LitTeaDryingPan.setState(meta + 1, this.getWorld(), pos);
				time = 0;
			}

			remainingTime = totalTime - time;

			this.markDirty();
		}
	}

	public int getTime()
	{
		return this.time;
	}

	public void setTime(int time)
	{
		this.time = time;
	}
	public int getRemainingTime()
	{
		return this.remainingTime;
	}
}
