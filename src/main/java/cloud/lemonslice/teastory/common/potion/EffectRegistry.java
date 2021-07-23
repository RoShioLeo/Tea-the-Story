package cloud.lemonslice.teastory.common.potion;

import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public final class EffectRegistry extends RegistryModule
{
    public static final Effect AGILITY = new NormalEffect("agility", 0x828282, EffectType.BENEFICIAL);
    public static final Effect PHOTOSYNTHESIS = new PhotosynthesisEffect();
    public static final Effect LIFE_DRAIN = new NormalEffect("life_drain", 0x7F0000, EffectType.BENEFICIAL);
    public static final Effect DEFENCE = new NormalEffect("defence", 0x959aa6, EffectType.BENEFICIAL);
    public static final Effect EXCITEMENT = new NormalEffect("excitement", 0xffca65, EffectType.NEUTRAL);
}
