package cloud.lemonslice.teastory.common.recipe.drink;

import cloud.lemonslice.teastory.common.fluid.FluidsRegistry;
import cloud.lemonslice.teastory.common.potion.EffectsRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public final class DrinkEffectsManager
{
    private final static Map<Fluid, BiConsumer<LivingEntity, Integer>> DRINK_EFFECTS = new HashMap<>();

    public static void init()
    {
        registerDrinkEffects();
    }

    private static void registerDrinkEffects()
    {
        registerEffects(FluidsRegistry.SUGARY_WATER_STILL.get(), createSimpleDrinkEffect(Effects.SPEED, 2, 0));

        registerEffects(FluidsRegistry.WEAK_GREEN_TEA_STILL.get(), createSimpleDrinkEffect(EffectsRegistry.AGILITY, 2, 0));
        registerEffects(FluidsRegistry.GREEN_TEA_STILL.get(), createDrinkEffects(new DrinkEffectAttribute(EffectsRegistry.AGILITY, 2, 1), new DrinkEffectAttribute(EffectsRegistry.EXCITEMENT, 2, 0)));
        registerEffects(FluidsRegistry.STRONG_GREEN_TEA_STILL.get(), createDrinkEffects(new DrinkEffectAttribute(EffectsRegistry.AGILITY, 2, 2), new DrinkEffectAttribute(EffectsRegistry.EXCITEMENT, 4, 0)));

        registerEffects(FluidsRegistry.WEAK_BLACK_TEA_STILL.get(), createSimpleDrinkEffect(Effects.HEALTH_BOOST, 4, 0));
        registerEffects(FluidsRegistry.BLACK_TEA_STILL.get(), createDrinkEffects(new DrinkEffectAttribute(Effects.HEALTH_BOOST, 4, 1), new DrinkEffectAttribute(EffectsRegistry.EXCITEMENT, 4, 0)));
        registerEffects(FluidsRegistry.STRONG_BLACK_TEA_STILL.get(), createDrinkEffects(new DrinkEffectAttribute(Effects.HEALTH_BOOST, 4, 2), new DrinkEffectAttribute(EffectsRegistry.EXCITEMENT, 8, 0)));

        registerEffects(FluidsRegistry.WEAK_WHITE_TEA_STILL.get(), createSimpleDrinkEffect(Effects.HASTE, 2, 0));
        registerEffects(FluidsRegistry.WHITE_TEA_STILL.get(), createDrinkEffects(new DrinkEffectAttribute(Effects.HASTE, 2, 1), new DrinkEffectAttribute(EffectsRegistry.EXCITEMENT, 2, 0)));
        registerEffects(FluidsRegistry.STRONG_WHITE_TEA_STILL.get(), createDrinkEffects(new DrinkEffectAttribute(Effects.HASTE, 2, 2), new DrinkEffectAttribute(EffectsRegistry.EXCITEMENT, 4, 0)));
    }

    public static void registerEffects(Fluid fluid, BiConsumer<LivingEntity, Integer> doEffects)
    {
        DRINK_EFFECTS.put(fluid, doEffects);
    }

    public static BiConsumer<LivingEntity, Integer> getEffects(Fluid key)
    {
        return DRINK_EFFECTS.get(key);
    }

    public static BiConsumer<LivingEntity, Integer> createSimpleDrinkEffect(Effect potionIn, int durationIn, int level)
    {
        return (livingEntity, amount) -> livingEntity.addPotionEffect(new EffectInstance(potionIn, durationIn * amount, level));
    }

    public static BiConsumer<LivingEntity, Integer> createDrinkEffects(DrinkEffectAttribute... attributes)
    {
        return (livingEntity, amount) ->
        {
            for (DrinkEffectAttribute attribute : attributes)
            {
                livingEntity.addPotionEffect(new EffectInstance(attribute.getPotion(), amount * attribute.getDuration(), attribute.getLevel()));
            }
        };
    }
}
