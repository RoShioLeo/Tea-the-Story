package cloud.lemonslice.teastory.common.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry.IRON_KETTLE;
import static cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry.TEAPOT;

public class TeapotTileEntity extends NormalContainerTileEntity
{
    private final LazyOptional<FluidTank> fluidTank = LazyOptional.of(this::createFluidHandler);
    private final int capacity;

    public TeapotTileEntity(int capacity)
    {
        super(getTeapotTileEntityType(capacity));
        this.capacity = capacity;
    }

    public static TileEntityType<?> getTeapotTileEntityType(int capacity)
    {
        if (capacity == 2000)
        {
            return IRON_KETTLE;
        }
        return TEAPOT;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (!this.removed)
        {
            if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(cap))
            {
                return fluidTank.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag)
    {
        super.read(state, tag);
        this.fluidTank.ifPresent(f -> f.readFromNBT(tag.getCompound("FluidTank")));
    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {
        fluidTank.ifPresent(f -> tag.put("FluidTank", f.writeToNBT(new CompoundNBT())));
        return super.write(tag);
    }

    private FluidTank createFluidHandler()
    {
        return new FluidTank(capacity)
        {
            @Override
            protected void onContentsChanged()
            {
                TeapotTileEntity.this.markDirty();
                TeapotTileEntity.this.refresh();
                super.onContentsChanged();
            }

            @Override
            public boolean isFluidValid(FluidStack stack)
            {
                return !stack.getFluid().getAttributes().isLighterThanAir() && stack.getFluid().getAttributes().getTemperature() < 500;
            }
        };
    }

    public FluidTank getFluidTank()
    {
        return this.fluidTank.orElse(new FluidTank(0));
    }

    public Fluid getFluid()
    {
        return this.fluidTank.map(f -> f.getFluid().getFluid()).orElse(Fluids.EMPTY);
    }

    public int getFluidAmount()
    {
        return getFluidTank().getFluidAmount();
    }

    public void setFluidTank(FluidStack stack)
    {
        this.fluidTank.ifPresent(f -> f.setFluid(stack));
    }

    public void setFluid(Fluid fluid)
    {
        this.fluidTank.ifPresent(f -> f.setFluid(new FluidStack(fluid, getFluidAmount())));
        this.refresh();
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return null;
    }

    @Override
    protected boolean isOpeningContainer(Container container)
    {
        return false;
    }
}
