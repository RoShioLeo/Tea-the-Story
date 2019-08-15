package roito.teastory.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;

public class TileEntityCookingPan extends TileEntity implements ITickable
{
    protected static int totalTime = ConfigMain.others.riceCookingBasicTime;
    protected int time = 0;
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
        if (++time >= totalTime)
        {
            this.getWorld().setBlockState(pos, BlockRegister.tea_drying_pan.getStateFromMeta(4));
            time = 0;
        }

        remainingTime = totalTime - time;

        this.markDirty();
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
