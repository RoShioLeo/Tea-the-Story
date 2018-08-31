package roito.teastory.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import roito.teastory.block.FullKettle;

public class TileEntityKettle extends TileEntity implements ITickable
{
    protected int capacity = 0;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.capacity = compound.getInteger("CC");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("CC", this.capacity);
        return super.writeToNBT(compound);
    }

    public int getCapacity()
    {
        return this.capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
        this.markDirty();
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            if (world.getBlockState(pos).getValue(FullKettle.CAPACITY) <= this.capacity)
            {
                ((FullKettle) world.getBlockState(pos).getBlock()).setState(this.capacity, world, pos);
            }
            if (world.getBlockState(pos).getValue(FullKettle.CAPACITY) > this.capacity)
            {
                this.capacity = world.getBlockState(pos).getValue(FullKettle.CAPACITY);

                this.markDirty();
            }
        }
    }
}
