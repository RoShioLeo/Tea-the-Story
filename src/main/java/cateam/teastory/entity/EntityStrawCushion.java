package cateam.teastory.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityStrawCushion extends Entity
{
	public int blockPosX;
	public int blockPosY;
	public int blockPosZ;
	
	public EntityStrawCushion(World world)
	{
		super(world);
		this.noClip = true;
		this.height = 0.01F;
		this.width = 0.01F;
	}

	public EntityStrawCushion(World world, double x, double y, double z, double yOffset)
	{
		this(world);
		this.blockPosX = (int) x;
		this.blockPosY = (int) y;
		this.blockPosZ = (int) z;
		setPosition(x + 0.5D, y + yOffset, z + 0.5D);
	}

	@Override
	public double getMountedYOffset()
	{
		return this.height * 0.0D;
	}

	@Override
	protected boolean shouldSetPosAfterLoading()
	{
		return false;
	}

	@Override
	public void onEntityUpdate()
	{
		if (!this.worldObj.isRemote)
		{
			if (!this.isBeingRidden() || this.worldObj.isAirBlock(new BlockPos(blockPosX, blockPosY, blockPosZ)))
			{
				this.setDead();
				worldObj.updateComparatorOutputLevel(getPosition(), worldObj.getBlockState(getPosition()).getBlock());
			}
		}
	}

	@Override
	protected void entityInit()
	{
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		
	}
}
