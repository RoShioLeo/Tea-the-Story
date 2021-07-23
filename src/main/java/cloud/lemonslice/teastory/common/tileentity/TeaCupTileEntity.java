package cloud.lemonslice.teastory.common.tileentity;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry.PORCELAIN_CUP;

public class TeaCupTileEntity extends TileEntity
{
    private final List<LazyOptional<FluidTank>> fluidTanks = Lists.newArrayList();

    public TeaCupTileEntity(int capacity)
    {
        super(PORCELAIN_CUP);
        for (int i = 0; i < 3; i++)
        {
            fluidTanks.add(LazyOptional.of(() -> createFluidHandler(capacity)));
        }
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbtTag = new CompoundNBT();
        this.write(nbtTag);
        return new SUpdateTileEntityPacket(getPos(), 1, nbtTag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return super.getCapability(cap, side);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag)
    {
        super.read(state, tag);
        fluidTanks.get(0).ifPresent(f -> f.readFromNBT(tag.getCompound("FluidTank_0")));
        fluidTanks.get(1).ifPresent(f -> f.readFromNBT(tag.getCompound("FluidTank_1")));
        fluidTanks.get(2).ifPresent(f -> f.readFromNBT(tag.getCompound("FluidTank_2")));
    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {
        fluidTanks.get(0).ifPresent(f -> tag.put("FluidTank_0", f.writeToNBT(new CompoundNBT())));
        fluidTanks.get(1).ifPresent(f -> tag.put("FluidTank_1", f.writeToNBT(new CompoundNBT())));
        fluidTanks.get(2).ifPresent(f -> tag.put("FluidTank_2", f.writeToNBT(new CompoundNBT())));
        return super.write(tag);
    }

    private FluidTank createFluidHandler(int capacity)
    {
        return new FluidTank(capacity)
        {
            @Override
            protected void onContentsChanged()
            {
                TeaCupTileEntity.this.refresh();
                TeaCupTileEntity.this.markDirty();
                super.onContentsChanged();
            }

            @Override
            public boolean isFluidValid(FluidStack stack)
            {
                return !stack.getFluid().getAttributes().isLighterThanAir() && stack.getFluid().getAttributes().getTemperature() < 500;
            }
        };
    }

    public FluidTank getFluidTank(int index)
    {
        return this.fluidTanks.get(index).orElse(new FluidTank(0));
    }

    public Fluid getFluid(int index)
    {
        return this.fluidTanks.get(index).map(f -> f.getFluid().getFluid()).orElse(Fluids.EMPTY);
    }

    public int getFluidAmount(int index)
    {
        return getFluidTank(index).getFluidAmount();
    }

    public void setFluidTank(int index, FluidStack stack)
    {
        this.fluidTanks.get(index).ifPresent(f -> f.setFluid(stack));
    }

    public void setFluid(int index, Fluid fluid)
    {
        this.fluidTanks.get(index).ifPresent(f -> f.setFluid(new FluidStack(fluid, getFluidAmount(index))));
        this.refresh();
    }

    public void refresh()
    {
        if (this.hasWorld() && !this.world.isRemote)
        {
            SUpdateTileEntityPacket packet = this.getUpdatePacket();
            Stream<ServerPlayerEntity> playerEntity = ((ServerWorld) this.world).getChunkProvider().chunkManager.getTrackingPlayers(new ChunkPos(this.pos.getX() >> 4, this.pos.getZ() >> 4), false);
            for (ServerPlayerEntity player : playerEntity.collect(Collectors.toList()))
            {
                player.connection.sendPacket(packet);
            }
        }
    }
}
