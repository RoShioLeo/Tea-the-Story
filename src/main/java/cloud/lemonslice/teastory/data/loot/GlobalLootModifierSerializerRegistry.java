package cloud.lemonslice.teastory.data.loot;

import cloud.lemonslice.teastory.TeaStory;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class GlobalLootModifierSerializerRegistry
{
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, TeaStory.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<RiceSeedsDropModifier>> RICE_GRAINS_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("rice_grains_drop", RiceSeedsDropModifier.Serializer::new);
}
