package cloud.lemonslice.teastory.client.color.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GrassColors;

public class GrassBlockItemColors implements IItemColor
{
    @Override
    public int getColor(ItemStack itemStack, int tintIndex)
    {
        return GrassColors.get(0.5D, 1.0D);
    }
}
