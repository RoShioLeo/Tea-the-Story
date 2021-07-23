package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;

public class FertilizerItem extends NormalItem
{
    public FertilizerItem(String name)
    {
        super(name, new Properties().group(TeaStory.GROUP_CORE));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        if (ServerConfig.Agriculture.useAshAsBoneMeal.get())
        {
            return Items.BONE_MEAL.onItemUse(context);
        }
        return ActionResultType.PASS;
    }
}
