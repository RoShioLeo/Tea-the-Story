package cloud.lemonslice.teastory.data.loot;

import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class RiceSeedsDropModifier extends LootModifier
{
    protected RiceSeedsDropModifier(ILootCondition[] conditionsIn)
    {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
    {
        if (ServerConfig.Agriculture.dropRiceGrains.get())
        {
            generatedLoot.add(ItemRegistry.RICE_GRAINS.getDefaultInstance());
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<RiceSeedsDropModifier>
    {
        @Override
        public RiceSeedsDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ilootcondition)
        {
            return new RiceSeedsDropModifier(ilootcondition);
        }

        @Override
        public JsonObject write(RiceSeedsDropModifier instance)
        {
            return makeConditions(instance.conditions);
        }
    }
}
