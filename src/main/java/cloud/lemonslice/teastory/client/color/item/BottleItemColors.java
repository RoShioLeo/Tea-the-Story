package cloud.lemonslice.teastory.client.color.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidUtil;

@OnlyIn(Dist.CLIENT)
public class BottleItemColors implements IItemColor
{

    @Override
    public int getColor(ItemStack itemStack, int tintIndex)
    {
        if (tintIndex == 0)
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
