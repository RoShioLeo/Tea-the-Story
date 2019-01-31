/**
 * This file is copied from Chinese Workshop
 * (https://github.com/574448121/ChineseWorkshop)
 */

package roito.teastory.entity;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface ISeat
{

	Vec3d getSeat(IBlockState state);

	class Seat extends Entity
	{

		public Seat(World world, Vec3d pos)
		{
			this(world);
			setPosition(pos.x, pos.y + 0.001, pos.z);
		}

		public Seat(World par1World)
		{
			super(par1World);
			setSize(0F, 0F);
		}

		@Override
		public void onUpdate()
		{
			super.onUpdate();

			// if (worldObj.isRemote) {
			BlockPos pos = getPosition();
			if (pos == null || !(getEntityWorld().getBlockState(pos).getBlock() instanceof ISeat))
			{
				setDead();
				return;
			}

			List<Entity> passangers = getPassengers();
			if (passangers.isEmpty())
			{
				setDead();
			}
			for (Entity e : passangers)
			{
				if (e.isSneaking())
				{
					setDead();
				}
			}
			// }
		}

		@Override
		protected void entityInit()
		{
		}

		@Override
		protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
		{
		}

		@Override
		protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
		{
		}
	}
}
