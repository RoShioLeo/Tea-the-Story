package cloud.lemonslice.teastory.client.sound;

import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class SoundEventsRegistry extends RegistryModule
{
    public static final SoundEvent CUP_BROKEN = new NormalSoundEvent(new ResourceLocation("teastory:block.cup_broken"));
    public static final SoundEvent RECORD_MOVING_UP = new NormalSoundEvent(new ResourceLocation("teastory:record.moving_up"));
    public static final SoundEvent RECORD_PICKING_TEA = new NormalSoundEvent(new ResourceLocation("teastory:record.picking_tea"));
    public static final SoundEvent RECORD_SPRING_FESTIVAL_OVERTURE = new NormalSoundEvent(new ResourceLocation("teastory:record.spring_festival_overture"));
    public static final SoundEvent RECORD_FLOWERS_AND_MOON = new NormalSoundEvent(new ResourceLocation("teastory:record.flowers_and_moon"));
    public static final SoundEvent RECORD_DANCING_GOLDEN_SNAKE = new NormalSoundEvent(new ResourceLocation("teastory:record.dancing_golden_snake"));
    public static final SoundEvent RECORD_JOYFUL = new NormalSoundEvent(new ResourceLocation("teastory:record.joyful"));
    public static final SoundEvent RECORD_GREEN_WILLOW = new NormalSoundEvent(new ResourceLocation("teastory:record.green_willow"));
    public static final SoundEvent RECORD_PURPLE_BAMBOO_MELODY = new NormalSoundEvent(new ResourceLocation("teastory:record.purple_bamboo_melody"));
    public static final SoundEvent RECORD_WELCOME_MARCH = new NormalSoundEvent(new ResourceLocation("teastory:record.welcome_march"));
}
