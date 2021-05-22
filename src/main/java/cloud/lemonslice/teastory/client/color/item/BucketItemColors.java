package cloud.lemonslice.teastory.client.color.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BucketItemColors implements IItemColor
{
    @Override
    public int getColor(ItemStack itemStack, int tintIndex)
    {
        if (itemStack.getItem() instanceof BucketItem && tintIndex == 1)
        {
            return ((BucketItem) itemStack.getItem()).getFluid().getAttributes().getColor();
        }
        return -1;
    }
}
