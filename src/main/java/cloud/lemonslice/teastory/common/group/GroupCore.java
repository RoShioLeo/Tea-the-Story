package cloud.lemonslice.teastory.common.group;

import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static cloud.lemonslice.teastory.TeaStory.MODID;

public class GroupCore extends ItemGroup
{
    public GroupCore()
    {
        super(MODID + ".core");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ItemRegistry.TEA_LEAVES);
    }
}
