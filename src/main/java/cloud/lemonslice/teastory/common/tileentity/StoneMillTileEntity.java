package cloud.lemonslice.teastory.common.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StoneMillTileEntity extends NormalContainerTileEntity implements ITickableTileEntity, IInventory
{
    private int angel = 0;
    private int processTicks = 0;
    private static final int TOTAL_TICKS = 200;

    private ItemStackHandler inputInventory = new ItemStackHandler();
    private ItemStackHandler outputInventory = new ItemStackHandler(3);
    private FluidTank fluidTank = new FluidTank(2000)
    {
        @Override
        protected void onContentsChanged()
        {
            StoneMillTileEntity.this.markDirty();
        }
    };

    public StoneMillTileEntity(TileEntityType<?> tileEntityType)
    {
        super(tileEntityType);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt)
    {
        super.read(state, nbt);
        this.inputInventory.deserializeNBT(nbt.getCompound("InputInventory"));
        this.outputInventory.deserializeNBT(nbt.getCompound("OutputInventory"));
        this.fluidTank.readFromNBT(nbt.getCompound("FluidTank"));
        this.processTicks = nbt.getInt("ProcessTicks");
        this.angel = nbt.getInt("Angel");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.put("InputInventory", this.inputInventory.serializeNBT());
        compound.put("OutputInventory", this.outputInventory.serializeNBT());
        compound.put("FluidTank", this.fluidTank.writeToNBT(new CompoundNBT()));
        compound.putInt("ProcessTicks", this.processTicks);
        compound.putInt("Angel", this.angel);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return super.getCapability(cap, side);
    }

    @Override
    protected boolean isOpeningContainer(Container container)
    {
        return false;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return null;
    }

    @Override
    public int getSizeInventory()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player)
    {
        return false;
    }

    @Override
    public void clear()
    {

    }

    public FluidTank getFluidTank()
    {
        return fluidTank;
    }

    @Override
    public void tick()
    {
//        angel += 3.0F;
//        angel %= 360;
//        ItemStack input = getInput();
//        if (input.isEmpty())
//        {
//            this.processTicks = 0;
//            this.markDirty();
//        }
//
//        if (!this.currentRecipe.canWork(fluidTank.getFluid(), input))
//        {
//            this.currentRecipe = RecipesRegistry.MANAGER_STONE_MILL.getRecipe(fluidTank.getFluid(), input);
//        }
//        if (!this.currentRecipe.getOutputStacks().isEmpty())
//        {
//            boolean flag = true;
//            for (ItemStack out : this.currentRecipe.getOutputStacks())
//            {
//                if (!ItemHandlerHelper.insertItem(this.outputInventory, out, true).isEmpty())
//                {
//                    flag = false;
//                }
//            }
//            if (flag)
//            {
//                this.angel += 3;
//                this.angel %= 360;
//                if (++this.processTicks >= totalTicks)
//                {
//                    for (ItemStack out : this.currentRecipe.getOutputStacks())
//                    {
//                        ItemHandlerHelper.insertItem(this.outputInventory, out, false);
//                    }
//                    this.inputInventory.extractItem(0, 1, false);
//                    this.fluidTank.drain(currentRecipe.getFluidAmount(), true);
//                    if (this.currentRecipe.getOutputFluid() != null && this.currentRecipe.getOutputFluid().amount != 0)
//                    {
//                        IFluidHandler handler = FluidUtil.getFluidHandler(world, pos.down().offset(((BlockStoneMill) this.getBlockType()).getFacing(this.getBlockMetadata())), EnumFacing.UP);
//                        if (handler != null)
//                        {
//                            handler.fill(this.currentRecipe.getOutputFluid(), true);
//                            world.getTileEntity(pos.down().offset(((BlockStoneMill) this.getBlockType()).getFacing(this.getBlockMetadata()))).markDirty();
//                        }
//                    }
//                    this.processTicks = 0;
//                }
//                this.syncToTrackingClients();
//            }
//        }
//        else
//        {
//            this.processTicks = 0;
//        }
//        this.markDirty();
    }
}
