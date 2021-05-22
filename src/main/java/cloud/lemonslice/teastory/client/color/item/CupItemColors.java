package cloud.lemonslice.teastory.client.color.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;

public class CupItemColors implements IItemColor
{

    @Override
    public int getColor(ItemStack itemStack, int tintIndex)
    {
        if (tintIndex == 1)
        {
            int color = FluidUtil.getFluidHandler(itemStack).map(h ->
                    h.getFluidInTank(0).getFluid().getAttributes().getColor()).orElse(-1);
            if (color == 0)
            {
                return -1;
            }
            return color;
        }
        else return -1;
    }
}
