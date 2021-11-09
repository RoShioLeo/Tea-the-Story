package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import cloud.lemonslice.teastory.TeaStory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Supplier;

public class RecordItem extends MusicDiscItem
{
    public RecordItem(String name, Supplier<SoundEvent> soundSupplier, int comparatorValue)
    {
        super(comparatorValue, soundSupplier, NormalItem.getNormalItemProperties(TeaStory.GROUP_CORE).maxStackSize(1).rarity(Rarity.RARE));
        this.setRegistryName(name);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        ActionResultType result = super.onItemUse(context);
        if (context.getWorld().isRemote && result == ActionResultType.SUCCESS)
        {
            SoundEventAccessor accessor = Minecraft.getInstance().getSoundHandler().getAccessor(getSound().getName());
            if (accessor == null || accessor.cloneEntry() == SoundHandler.MISSING_SOUND)
            {
                if (context.getPlayer() != null)
                {
                    context.getPlayer().sendStatusMessage(new TranslationTextComponent("info.teastory.record"), false);
                }
                return ActionResultType.FAIL;
            }
        }
        return result;
    }
}
