package cloud.lemonslice.teastory.common.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry.WOODEN_BARREL;

public class WoodenBarrelTileEntity extends NormalContainerTileEntity
{
    private final LazyOptional<FluidTank> fluidTank = LazyOptional.of(this::createFluidHandler);
    private Fluid remainFluid = Fluids.EMPTY;
    private final int capacity;
    private int heightAmount = 0;

    public WoodenBarrelTileEntity()
    {
        super(WOODEN_BARREL);
        this.capacity = 4000;
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
        this.fluidTank.ifPresent(f ->
        {
            f.readFromNBT(tag.getCompound("FluidTank"));
            recordPreviousFluid(f.getFluid());
        });
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
                WoodenBarrelTileEntity.this.markDirty();
                WoodenBarrelTileEntity.this.refresh();
                WoodenBarrelTileEntity.this.recordPreviousFluid(this.fluid);
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

    public void recordPreviousFluid(FluidStack fluid)
    {
        if (!fluid.isEmpty() && fluid.getAmount() > 0)
        {
            remainFluid = fluid.getFluid();
        }
    }

    public Fluid getRemainFluid()
    {
        return remainFluid;
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

    private void updateHeight()
    {
        if (this.world.isRemote)
        {
            int viscosity = Math.max(this.remainFluid.getAttributes().getViscosity() / 50, 10);
            if (heightAmount > this.getFluidAmount())
            {
                heightAmount -= Math.max(1, (heightAmount - this.getFluidAmount()) / viscosity);
            }
            else if (heightAmount < this.getFluidAmount())
            {
                heightAmount += Math.max(1, (this.getFluidAmount() - heightAmount) / viscosity);
            }
        }
    }

    public float getHeight()
    {
        updateHeight();
        return 0.0625F + 0.875F * this.heightAmount / capacity;
    }
}
