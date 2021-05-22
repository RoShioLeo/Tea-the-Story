package cloud.lemonslice.teastory.client.sound;

import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class SoundEventsRegistry extends RegistryModule
{
    public static final SoundEvent CUP_BROKEN = new NormalSoundEvent(new ResourceLocation("teastory:block.cup_broken"));
}
