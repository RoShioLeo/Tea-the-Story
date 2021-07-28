package cloud.lemonslice.teastory.common.tileentity;

import cloud.lemonslice.teastory.common.container.DrinkMakerContainer;
import cloud.lemonslice.teastory.common.recipe.drink.DrinkRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes.DRINK_MAKER;
import static net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack.FLUID_NBT_KEY;

public class DrinkMakerTileEntity extends NormalContainerTileEntity implements ITickableTileEntity, IInventory
{
    private final LazyOptional<ItemStackHandler> ingredientsInventory = LazyOptional.of(() -> this.createItemHandler(4));
    private final LazyOptional<ItemStackHandler> residuesInventory = LazyOptional.of(() -> this.createItemHandler(4));
    private final LazyOptional<ItemStackHandler> containerInventory = LazyOptional.of(() -> this.createContainerItemHandler(1));
    private final LazyOptional<ItemStackHandler> inputInventory = LazyOptional.of(() -> this.createItemHandler(1));
    private final LazyOptional<ItemStackHandler> outputInventory = LazyOptional.of(() -> this.createContainerItemHandler(1));

    private int processTicks = 0;
    private static final int totalTicks = 200;

    private DrinkRecipe currentRecipe;

    public DrinkMakerTileEntity()
    {
        super(TileEntityTypeRegistry.DRINK_MAKER);
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
                    return residuesInventory.cast();
                else
                    return ingredientsInventory.cast();
            }
            else if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(cap))
            {
                return getFluidHandler().cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag)
    {
        super.read(state, tag);
        this.ingredientsInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Ingredients")));
        this.residuesInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Residues")));
        this.containerInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Container")));
        this.inputInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Input")));
        this.outputInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Output")));
        this.processTicks = tag.getInt("ProcessTicks");
    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {
        ingredientsInventory.ifPresent(h -> tag.put("Ingredients", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        residuesInventory.ifPresent(h -> tag.put("Residues", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        containerInventory.ifPresent(h -> tag.put("Container", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        inputInventory.ifPresent(h -> tag.put("Input", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        outputInventory.ifPresent(h -> tag.put("Output", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        tag.putInt("ProcessTicks", processTicks);
        return super.write(tag);
    }

    private boolean isEnoughAmount()
    {
        if (currentRecipe != null)
        {
            int n = (int) Math.ceil(1.0F * getFluidAmount() / currentRecipe.getFluidAmount());
            for (int i = 0; i < 4; i++)
            {
                if (!getStackInSlot(i).isEmpty() && getStackInSlot(i).getCount() < n)
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void tick()
    {
        if (this.getFluidAmount() != 0 && !isIngredientsEmpty())
        {
            if (this.currentRecipe == null || !this.currentRecipe.matches(this, this.world))
            {
                this.currentRecipe = this.world.getRecipeManager().getRecipe(DRINK_MAKER, this, this.world).orElse(null);
            }
            if (currentRecipe != null && isEnoughAmount())
            {
                this.processTicks++;
                if (this.processTicks >= totalTicks)
                {
                    this.ingredientsInventory.ifPresent(inv ->
                            getFluidHandler().ifPresent(fluid ->
                            {
                                this.residuesInventory.ifPresent(h ->
                                {
                                    int n = (int) Math.ceil(this.getFluidAmount() * 1.0F / currentRecipe.getFluidIngredient().getMatchingStacks()[0].getAmount());
                                    for (int i = 0; i < 4; i++)
                                    {
                                        ItemStack residue = inv.getStackInSlot(i).getContainerItem();
                                        inv.extractItem(i, n, false);
                                        if (!residue.isEmpty())
                                        {
                                            residue.setCount(n);
                                            h.insertItem(i, residue, false);
                                        }
                                    }
                                });
                                CompoundNBT nbt = new FluidStack(currentRecipe.getFluidResult(), this.getFluidAmount()).writeToNBT(new CompoundNBT());
                                fluid.getContainer().getOrCreateTag().put(FLUID_NBT_KEY, nbt);
                                this.setToZero();
                            }));
                }
            }
            else
            {
                setToZero();
            }
        }
        else
        {
            this.currentRecipe = null;
            setToZero();
        }

        getFluidHandler().ifPresent(fluid ->
                inputInventory.ifPresent(in ->
                        outputInventory.ifPresent(out ->
                        {
                            {
                                ItemStack inputCup = ItemHandlerHelper.copyStackWithSize(in.getStackInSlot(0), 1);
                                ItemStack outputCup = out.getStackInSlot(0);
                                if (outputCup.isEmpty())
                                {
                                    FluidActionResult fluidActionResult = FluidUtil.tryFillContainerAndStow(inputCup, fluid, out, Integer.MAX_VALUE, null, true);
                                    if (!fluidActionResult.isSuccess())
                                    {
                                        fluidActionResult = FluidUtil.tryEmptyContainerAndStow(inputCup, fluid, out, Integer.MAX_VALUE, null, true);
                                    }
                                    if (fluidActionResult.isSuccess())
                                    {
                                        out.setStackInSlot(0, fluidActionResult.getResult());
                                        in.getStackInSlot(0).shrink(1);
                                    }
                                    if (fluid.getFluidInTank(0).isEmpty())
                                    {
                                        containerInventory.ifPresent(container ->
                                        {
                                            if (container.getStackInSlot(0).hasContainerItem())
                                                container.setStackInSlot(0, container.getStackInSlot(0).getContainerItem());
                                        });
                                    }
                                }
                            }
                        })));
    }

    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new DrinkMakerContainer(i, playerInventory, pos, world);
    }

    private ItemStackHandler createItemHandler(int size)
    {
        return new ItemStackHandler(size)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                DrinkMakerTileEntity.this.markDirty();
                super.onContentsChanged(slot);
            }
        };
    }

    private ItemStackHandler createContainerItemHandler(int size)
    {
        return new ItemStackHandler(size)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                DrinkMakerTileEntity.this.markDirty();
                DrinkMakerTileEntity.this.refresh();
                super.onContentsChanged(slot);
            }

            @Override
            public int getSlotLimit(int slot)
            {
                return 1;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
            {
                return !(stack.getItem() instanceof BucketItem);
            }
        };
    }

    private void setToZero()
    {
        if (this.processTicks != 0)
        {
            this.processTicks = 0;
            this.markDirty();
        }
    }

    public int getProcessTicks()
    {
        return processTicks;
    }

    public int getTotalTicks()
    {
        return totalTicks;
    }

    public int getFluidAmount()
    {
        return getFluidHandler().map(h -> h.getFluidInTank(0).getAmount()).orElse(0);
    }

    @Nullable
    public DrinkRecipe getCurrentRecipe()
    {
        return currentRecipe;
    }

    public int getNeededAmount()
    {
        if (currentRecipe != null)
            return (int) Math.ceil(this.getFluidAmount() * 1.0F / currentRecipe.getFluidIngredient().getMatchingStacks()[0].getAmount());
        else
            return 0;
    }

    @Nullable
    public ITextComponent getFluidTranslation()
    {
        return getFluidHandler().map(h -> h.getFluidInTank(0).getDisplayName()).orElse(null);
    }

    public LazyOptional<IFluidHandlerItem> getFluidHandler()
    {
        return this.containerInventory.map(h -> FluidUtil.getFluidHandler(h.getStackInSlot(0))).orElse(LazyOptional.empty());
    }

    public LazyOptional<ItemStackHandler> getContainerInventory()
    {
        return containerInventory;
    }

    public LazyOptional<ItemStackHandler> getInputInventory()
    {
        return inputInventory;
    }

    public LazyOptional<ItemStackHandler> getOutputInventory()
    {
        return outputInventory;
    }

    public List<ItemStack> getContent()
    {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            int index = i;
            ingredientsInventory.ifPresent(h -> list.add(h.getStackInSlot(index)));
        }
        containerInventory.ifPresent(h -> list.add(h.getStackInSlot(0)));
        return list;
    }

    public Direction getFacing()
    {
        return this.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
    }

    @Override
    public int getSizeInventory()
    {
        return 11;
    }

    public boolean isIngredientsEmpty()
    {
        return ingredientsInventory.map(h ->
        {
            for (int i = 0; i < 4; i++)
            {
                if (!h.getStackInSlot(i).isEmpty())
                {
                    return false;
                }
            }
            return true;
        }).orElse(true);
    }

    @Override
    public boolean isEmpty()
    {
        return isIngredientsEmpty() &&
                residuesInventory.map(h ->
                {
                    for (int i = 0; i < 4; i++)
                    {
                        if (!h.getStackInSlot(i).isEmpty())
                        {
                            return false;
                        }
                    }
                    return true;
                }).orElse(true) &&
                containerInventory.map(h -> h.getStackInSlot(0).isEmpty()).orElse(true) &&
                inputInventory.map(h -> h.getStackInSlot(0).isEmpty()).orElse(true) &&
                outputInventory.map(h -> h.getStackInSlot(0).isEmpty()).orElse(true);
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        if (index < 4)
        {
            return ingredientsInventory.map(h -> h.getStackInSlot(index)).orElse(ItemStack.EMPTY);
        }
        else if (index < 8)
        {
            return residuesInventory.map(h -> h.getStackInSlot(index - 4)).orElse(ItemStack.EMPTY);
        }
        else if (index == 8)
        {
            return containerInventory.map(h -> h.getStackInSlot(0)).orElse(ItemStack.EMPTY);
        }
        else if (index == 9)
        {
            return inputInventory.map(h -> h.getStackInSlot(0)).orElse(ItemStack.EMPTY);
        }
        else
        {
            return outputInventory.map(h -> h.getStackInSlot(0)).orElse(ItemStack.EMPTY);
        }
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (index < 4)
        {
            return ingredientsInventory.map(h -> h.getStackInSlot(index).split(count)).orElse(ItemStack.EMPTY);
        }
        else if (index < 8)
        {
            return residuesInventory.map(h -> h.getStackInSlot(index - 4).split(count)).orElse(ItemStack.EMPTY);
        }
        else if (index == 8)
        {
            return containerInventory.map(h -> h.getStackInSlot(0).split(count)).orElse(ItemStack.EMPTY);
        }
        else if (index == 9)
        {
            return inputInventory.map(h -> h.getStackInSlot(0).split(count)).orElse(ItemStack.EMPTY);
        }
        else
        {
            return outputInventory.map(h -> h.getStackInSlot(0).split(count)).orElse(ItemStack.EMPTY);
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        setInventorySlotContents(index, ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        if (index < 4)
        {
            ingredientsInventory.ifPresent(h -> h.setStackInSlot(index, stack));
        }
        else if (index < 8)
        {
            residuesInventory.ifPresent(h -> h.setStackInSlot(index - 4, stack));
        }
        else if (index == 8)
        {
            containerInventory.ifPresent(h -> h.setStackInSlot(0, stack));
        }
        else if (index == 9)
        {
            inputInventory.ifPresent(h -> h.setStackInSlot(0, stack));
        }
        else
        {
            outputInventory.ifPresent(h -> h.setStackInSlot(0, stack));
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
    protected boolean isOpeningContainer(Container container)
    {
        return container instanceof DrinkMakerContainer;
    }
}
