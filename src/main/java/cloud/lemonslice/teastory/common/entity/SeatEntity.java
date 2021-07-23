package cloud.lemonslice.teastory.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class SeatEntity extends Entity
{
    public SeatEntity(World worldIn)
    {
        super(EntityTypeRegistry.SEAT_ENTITY, worldIn);
    }

    private SeatEntity(World world, BlockPos pos, double height, double x, double z)
    {
        this(world);
        this.setPosition(pos.getX() + 0.5 + x, pos.getY() + height, pos.getZ() + 0.5 + z);
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (!this.world.isRemote)
        {
            if (this.getPassengers().isEmpty() || this.world.isAirBlock(this.getPosition()))
            {
                this.remove();
            }
        }
    }

    public static ActionResultType createSeat(World world, BlockPos pos, PlayerEntity player, double height, double x, double z)
    {
        if (!world.isRemote)
        {
            List<SeatEntity> seats = world.getEntitiesWithinAABB(SeatEntity.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
            if (seats.isEmpty())
            {
                SeatEntity seat = new SeatEntity(world, pos, height, x, z);
                world.addEntity(seat);
                player.startRiding(seat, false);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    protected void registerData()
    {
    }

    @Override
    protected void readAdditional(CompoundNBT compound)
    {
    }

    @Override
    protected void writeAdditional(CompoundNBT compound)
    {
    }
}
