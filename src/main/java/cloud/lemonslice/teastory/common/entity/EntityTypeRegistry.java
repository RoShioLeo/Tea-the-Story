package cloud.lemonslice.teastory.common.entity;

import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public final class EntityTypeRegistry extends RegistryModule
{
    public static final EntityType<SeatEntity> SEAT_ENTITY = (EntityType<SeatEntity>) EntityType.Builder.create((type, world) -> new SeatEntity(world), EntityClassification.MISC).size(0F, 0F).build("seat").setRegistryName("seat");
}