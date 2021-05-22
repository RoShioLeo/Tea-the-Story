package cloud.lemonslice.teastory.common.group;

import cloud.lemonslice.teastory.common.fluid.FluidsRegistry;
import cloud.lemonslice.teastory.common.item.ItemsRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;

import static cloud.lemonslice.teastory.TeaStory.MODID;
import static net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack.FLUID_NBT_KEY;

public class GroupDrink extends ItemGroup
{
    public GroupDrink()
    {
        super(MODID + ".drink");
    }

    @Override
    public ItemStack createIcon()
    {
        ItemStack itemStack = new ItemStack(ItemsRegistry.PORCELAIN_CUP_DRINK);
        CompoundNBT fluidTag = new CompoundNBT();
        new FluidStack(FluidsRegistry.SUGARY_WATER_STILL.get(), 500).writeToNBT(fluidTag);
        itemStack.getOrCreateTag().put(FLUID_NBT_KEY, fluidTag);
        return itemStack;
    }
}
