package roito.teastory.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import roito.teastory.TeaStory;

public class EntityRegister
{
    private static int nextID = 0;

    public EntityRegister()
    {
        registerEntity(EntityStrawCushion.class, "StrawCushion", 64, 1, false);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
                                       int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(TeaStory.MODID, "entity"), entityClass, name, nextID++, TeaStory.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
}
