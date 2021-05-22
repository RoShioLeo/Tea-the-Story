package cloud.lemonslice.teastory.client.color.item;

import cloud.lemonslice.teastory.common.environment.flower.FlowerColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class HybridizableFlowerItemColor implements IItemColor
{

    @Override
    public int getColor(ItemStack itemStack, int tintIndex)
    {
        if (tintIndex == 1)
        {
            if (itemStack.hasTag() && itemStack.getTag().contains("color"))
            {
                return FlowerColor.getFlowerColor(itemStack.getTag().getString("color")).getColorValue();
            }
        }
        return -1;
    }
}
