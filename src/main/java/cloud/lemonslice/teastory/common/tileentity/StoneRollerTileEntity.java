package cloud.lemonslice.teastory.common.tileentity;

import cloud.lemonslice.teastory.common.container.StoneRollerContainer;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneRollerRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes.STONE_ROLLER;

public class StoneRollerTileEntity extends NormalContainerTileEntity implements ITickableTileEntity, IInventory
{
    private int woodenFrameAngel = 0;
    private int stoneAngel = 0;
    private boolean isWorking = false;

    private int processTicks = 0;
    private StoneRollerRecipe currentRecipe;

    private ItemStackHandler inputInventory = new ItemStackHandler()
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            StoneRollerTileEntity.this.markDirty();
            StoneRollerTileEntity.this.refresh();
        }
    };
    private ItemStackHandler outputInventory = new ItemStackHandler(3)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            StoneRollerTileEntity.this.markDirty();
        }
    };

    public StoneRollerTileEntity()
    {
        super(TileEntityTypeRegistry.STONE_ROLLER);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt)
    {
        super.read(state, nbt);
        this.inputInventory.deserializeNBT(nbt.getCompound("InputInventory"));
        this.outputInventory.deserializeNBT(nbt.getCompound("OutputInventory"));
        this.processTicks = nbt.getInt("ProcessTicks");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.put("InputInventory", this.inputInventory.serializeNBT());
        compound.put("OutputInventory", this.outputInventory.serializeNBT());
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
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected boolean isOpeningContainer(Container container)
    {
        return container instanceof StoneRollerContainer;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new StoneRollerContainer(id, playerInventory, pos, world);
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
            this.currentRecipe = this.world.getRecipeManager().getRecipe(STONE_ROLLER, this, this.world).orElse(null);
        }

        if (this.currentRecipe != null)
        {
            woodenFrameAngel += 3;
            woodenFrameAngel %= 360;
            stoneAngel += 4;
            stoneAngel %= 360;
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

                    processTicks = 0;
                }
            }
        }
        else
        {
            setProcessTicks(0);
        }
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

    public int getWoodenFrameAngel()
    {
        return woodenFrameAngel;
    }

    public int getStoneAngel()
    {
        return stoneAngel;
    }

    public boolean isWorking()
    {
        return isWorking;
    }

    public int getProcessTicks()
    {
        return processTicks;
    }

    public StoneRollerRecipe getCurrentRecipe()
    {
        return currentRecipe;
    }
}
