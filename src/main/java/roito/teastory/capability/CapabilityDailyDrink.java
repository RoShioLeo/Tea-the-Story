package roito.teastory.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import roito.teastory.api.capability.IDailyDrink;
import roito.teastory.common.CapabilityRegister;

import javax.annotation.Nullable;

public class CapabilityDailyDrink
{
    public static class Storage implements Capability.IStorage<IDailyDrink>
    {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IDailyDrink> capability, IDailyDrink instance, EnumFacing side)
        {
            NBTTagList list = new NBTTagList();
            NBTTagCompound compound = new NBTTagCompound();
            compound.setLong("LastDay", instance.getLastDay());
            compound.setLong("InstantDay", instance.getInstantDay());
            list.appendTag(compound);
            return list;
        }

        @Override
        public void readNBT(Capability<IDailyDrink> capability, IDailyDrink instance, EnumFacing side, NBTBase nbt)
        {
            NBTTagList list = (NBTTagList) nbt;
            NBTTagCompound compound = list.getCompoundTagAt(0);
            if (!compound.isEmpty())
            {
                instance.setDay(compound.getLong("LastDay"), compound.getLong("InstantDay"));
            }
        }
    }

    public static class Implementation implements IDailyDrink
    {
        private long lastDay = -1;

        private long instantDay = 0;

        @Override
        public long getLastDay()
        {
            return lastDay;
        }

        @Override
        public long getInstantDay()
        {
            return instantDay;
        }

        @Override
        public void updateDay(long nowDay)
        {
            if (nowDay - 1 == lastDay)
            {
                instantDay++;
            }
            else if (nowDay > lastDay)
            {
                instantDay -= nowDay - lastDay - 1;
                instantDay = Math.max(instantDay, 0);
            }
            lastDay = nowDay;
        }

        @Override
        public void setDay(long lastDay, long instantDay)
        {
            this.lastDay = lastDay;
            this.instantDay = instantDay;
        }
    }

    public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound>
    {
        private IDailyDrink dailyDrink = new Implementation();
        private Capability.IStorage<IDailyDrink> storage = CapabilityRegister.dailyDrink.getStorage();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return CapabilityRegister.dailyDrink.equals(capability);
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            if (CapabilityRegister.dailyDrink.equals(capability))
            {
                T result = (T) dailyDrink;
                return result;
            }
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("DailyDrink", storage.writeNBT(CapabilityRegister.dailyDrink, dailyDrink, null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound)
        {
            NBTTagList list = (NBTTagList) compound.getTag("DailyDrink");
            storage.readNBT(CapabilityRegister.dailyDrink, dailyDrink, null, list);
        }
    }
}
