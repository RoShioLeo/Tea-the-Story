package cateam.teastory.entity;

import cateam.teastory.TeaStory;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityLoader
{
	private static int nextID = 0;

    public EntityLoader()
    {
        registerEntity(EntityStrawCushion.class, "StrawCushion", 64, 1, false);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(entityClass, name, nextID++, TeaStory.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
}
