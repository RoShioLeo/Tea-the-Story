package cloud.lemonslice.teastory.client.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class NormalSoundEvent extends SoundEvent
{
    public NormalSoundEvent(ResourceLocation name)
    {
        super(name);
        this.setRegistryName(name);
    }
}
