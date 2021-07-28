package cloud.lemonslice.teastory.common.tileentity;

import cloud.lemonslice.teastory.common.container.StoneMillContainer;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneMillRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes.STONE_MILL;
import static net.minecraft.block.HorizontalBlock.HORIZONTAL_FACING;

public class StoneMillTileEntity extends NormalContainerTileEntity implements ITickableTileEntity, IInventory
{
    private int angel = 0;
    private boolean isWorking = false;

    private int processTicks = 0;
    private StoneMillRecipe currentRecipe;

    private ItemStackHandler inputInventory = new ItemStackHandler()
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            StoneMillTileEntity.this.markDirty();
            StoneMillTileEntity.this.refresh();
        }
    };
    private ItemStackHandler outputInventory = new ItemStackHandler(3)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            StoneMillTileEntity.this.markDirty();
        }
    };
    private FluidTank fluidTank = new FluidTank(2000)
    {
        @Override
        protected void onContentsChanged()
        {
            StoneMillTileEntity.this.markDirty();
            StoneMillTileEntity.this.refresh();
        }
    };

    public StoneMillTileEntity()
    {
        super(TileEntityTypeRegistry.STONE_MILL);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt)
    {
        super.read(state, nbt);
        this.inputInventory.deserializeNBT(nbt.getCompound("InputInventory"));
        this.outputInventory.deserializeNBT(nbt.getCompound("OutputInventory"));
        this.fluidTank.readFromNBT(nbt.getCompound("FluidTank"));
        this.processTicks = nbt.getInt("ProcessTicks");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.put("InputInventory", this.inputInventory.serializeNBT());
        compound.put("OutputInventory", this.outputInventory.serializeNBT());
        compound.put("FluidTank", this.fluidTank.writeToNBT(new CompoundNBT()));
        compound.putInt("ProcessTicks", this.processTicks);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (!this.removed)
        {
            if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(cap))
            {
                if (side == Direction.DOWN)
                    return LazyOptional.of(() -> outputInventory).cast();
                else
                    return LazyOptional.of(() -> inputInventory).cast();
            }
            else if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(cap))
            {
                return LazyOptional.of(this::getFluidTank).cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected boolean isOpeningContainer(Container container)
    {
        return container instanceof StoneMillContainer;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new StoneMillContainer(id, playerInventory, pos, world);
    }

    @Override
    public int getSizeInventory()
    {
        return 4;
    }

    @Override
    public boolean isEmpty()
    {
        return inputInventory.getStackInSlot(0).isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        if (index == 0)
        {
            return this.inputInventory.getStackInSlot(0);
        }
        else if (index > 0 && index < 4)
        {
            return this.outputInventory.getStackInSlot(index - 1);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return getStackInSlot(index).split(count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack stack = getStackInSlot(index).copy();
        setInventorySlotContents(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        if (index == 0)
        {
            inputInventory.setStackInSlot(0, stack);
        }
        else if (index > 0 && index < 4)
        {
            outputInventory.setStackInSlot(index - 1, stack);
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player)
    {
        return true;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < getSizeInventory(); i++)
        {
            removeStackFromSlot(i);
        }
    }

    public FluidTank getFluidTank()
    {
        return fluidTank;
    }

    @Override
    public void tick()
    {
        ItemStack input = getStackInSlot(0);
        if (input.isEmpty())
        {
            setProcessTicks(0);
            this.currentRecipe = null;
            return;
        }

        if (this.currentRecipe == null || !this.currentRecipe.matches(this, getWorld()))
        {
            this.currentRecipe = this.world.getRecipeManager().getRecipe(STONE_MILL, this, this.world).orElse(null);
        }

        if (this.currentRecipe != null)
        {
            angel += 3;
            angel %= 360;
            boolean flag = true;
            for (ItemStack out : this.currentRecipe.getOutputItems())
            {
                if (!ItemHandlerHelper.insertItem(this.outputInventory, out.copy(), true).isEmpty())
                {
                    flag = false;
                }
            }
            if (flag)
            {
                if (++this.processTicks >= currentRecipe.getWorkTime())
                {
                    for (ItemStack out : this.currentRecipe.getOutputItems())
                    {
                        ItemHandlerHelper.insertItem(this.outputInventory, out.copy(), false);
                    }
                    this.inputInventory.extractItem(0, 1, false);

                    if (!currentRecipe.getInputFluid().hasNoMatchingFluids())
                    {
                        FluidStack[] fluidStacks = currentRecipe.getInputFluid().getMatchingStacks();
                        for (FluidStack fluidStack : fluidStacks)
                        {
                            if (fluidStack.getFluid() == this.fluidTank.getFluid().getFluid())
                            {
                                this.fluidTank.drain(fluidStack.getAmount(), IFluidHandler.FluidAction.EXECUTE);
                            }
                        }
                    }

                    if (!this.currentRecipe.getOutputFluid().isEmpty())
                    {
                        FluidUtil.getFluidHandler(world, pos.down().offset(this.getBlockState().get(HORIZONTAL_FACING)), Direction.UP).ifPresent(handler ->
                                handler.fill(this.currentRecipe.getOutputFluid(), IFluidHandler.FluidAction.EXECUTE));
                    }
                    processTicks = 0;
                }
            }
        }
        else
        {
            setProcessTicks(0);
        }
    }

    public Fluid getOutputFluid()
    {
        if (currentRecipe != null)
        {
            return currentRecipe.getOutputFluid().getFluid();
        }
        else return Fluids.EMPTY;
    }

    private void setProcessTicks(int ticks)
    {
        if (ticks != this.processTicks)
        {
            this.processTicks = ticks;
            markDirty();
        }
    }

    public boolean isCompleted()
    {
        return this.currentRecipe == null;
    }

    public int getAngel()
    {
        return angel;
    }

    public boolean isWorking()
    {
        return isWorking;
    }

    public int getProcessTicks()
    {
        return processTicks;
    }

    public StoneMillRecipe getCurrentRecipe()
    {
        return currentRecipe;
    }
}
