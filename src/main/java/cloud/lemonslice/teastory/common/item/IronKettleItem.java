package cloud.lemonslice.teastory.common.item;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack.FLUID_NBT_KEY;

public class IronKettleItem extends TeapotItem
{
    public IronKettleItem(Block block, int capacity)
    {
        super(block, capacity, true);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        CompoundNBT nbt = stack.getChildTag(FLUID_NBT_KEY);
        if (nbt != null)
        {
            FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(nbt);
            if (fluidStack.getFluid() == Fluids.WATER)
            {
                tooltip.add(new TranslationTextComponent("info.teastory.tooltip.iron_kettle.to_boil").mergeStyle(TextFormatting.GRAY));
            }
        }
        else
        {
            tooltip.add(new TranslationTextComponent("info.teastory.tooltip.iron_kettle.to_fill").mergeStyle(TextFormatting.GRAY));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
