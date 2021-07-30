package cloud.lemonslice.teastory.common.tileentity;

import cloud.lemonslice.teastory.common.block.craft.StoveBlock;
import cloud.lemonslice.teastory.common.container.StoveContainer;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry.STOVE_TILE;
import static net.minecraft.state.properties.BlockStateProperties.LIT;

public class StoveTileEntity extends NormalContainerTileEntity implements ITickableTileEntity
{
    private int remainTicks = 0;
    private int fuelTicks = 0;
    private int workTicks = 0;
    private boolean lit = false;

    private int doubleClickTicks = 0;

    private final LazyOptional<ItemStackHandler> fuelInventory = LazyOptional.of(this::createFuelHandler);
    private final LazyOptional<ItemStackHandler> ashInventory = LazyOptional.of(this::createAshHandler);

    public StoveTileEntity()
    {
        super(STOVE_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag)
    {
        super.read(state, tag);
        this.fuelInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Fuel")));
        this.ashInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Ash")));
        this.fuelTicks = tag.getInt("FuelTicks");
        this.remainTicks = tag.getInt("RemainTicks");
        this.workTicks = tag.getInt("WorkTicks");
        this.lit = tag.getBoolean("Lit");

    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {
        fuelInventory.ifPresent(h -> tag.put("Fuel", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        ashInventory.ifPresent(h -> tag.put("Ash", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        tag.putInt("FuelTicks", fuelTicks);
        tag.putInt("RemainTicks", remainTicks);
        tag.putInt("WorkTicks", workTicks);
        tag.putBoolean("Lit", lit);
        return super.write(tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (!this.removed && CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(cap))
        {
            if (side == Direction.DOWN)
            {
                return ashInventory.cast();
            }
            else
            {
                return fuelInventory.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick()
    {
        if (doubleClickTicks > 0)
        {
            doubleClickTicks--;
        }
        if (this.lit)
        {
            this.addFuel();
        }
        if (this.remainTicks > 0)
        {
            this.remainTicks--;
            this.markDirty();
        }
        else
        {
            if (!this.lit && this.getBlockState().get(LIT))
            {
                StoveBlock.setState(false, this.world, this.pos);
                refresh();
            }
        }
    }

    private boolean addFuel()
    {
        if (this.isBurning())
        {
            this.lit = true;
            return true;
        }
        else
        {
            ItemStack itemFuel = this.fuelInventory.orElse(new ItemStackHandler()).extractItem(0, 1, true);
            if (ForgeHooks.getBurnTime(itemFuel) > 0)
            {
                this.fuelTicks = ForgeHooks.getBurnTime(itemFuel) * 2;
                this.remainTicks = ForgeHooks.getBurnTime(itemFuel) * 2;
                ItemStack cItem = this.fuelInventory.orElse(new ItemStackHandler()).getStackInSlot(0).getContainerItem();
                if (!cItem.isEmpty())
                {
                    this.fuelInventory.orElse(new ItemStackHandler()).extractItem(0, 1, false);
                    this.fuelInventory.orElse(new ItemStackHandler()).insertItem(0, cItem, false);
                }
                else
                {
                    this.fuelInventory.orElse(new ItemStackHandler()).extractItem(0, 1, false);
                }
                this.ashInventory.orElse(new ItemStackHandler()).insertItem(0, new ItemStack(ItemRegistry.ASH), false);
                this.markDirty();
                StoveBlock.setState(true, getWorld(), pos);
                this.lit = true;
                return true;
            }
            else
            {
                this.lit = false;
                return false;
            }
        }
    }

    public boolean isBurning()
    {
        return this.remainTicks > 0;
    }

    public NonNullList<ItemStack> getContents()
    {
        NonNullList<ItemStack> list = NonNullList.create();
        this.fuelInventory.ifPresent(inv ->
        {
            ItemStack con = inv.getStackInSlot(0).copy();
            con.setCount(1);
            for (int i = this.fuelInventory.orElse(new ItemStackHandler()).getStackInSlot(0).getCount(); i > 0; i -= 4)
            {
                list.add(con);
            }
        });
        return list;
    }

    public int getRemainTicks()
    {
        return this.remainTicks;
    }

    public int getFuelTicks()
    {
        return this.fuelTicks;
    }

    public void setToLit()
    {
        this.lit = true;
    }

    public void singleClickStart()
    {
        this.doubleClickTicks = 10;
        this.markDirty();
    }

    public boolean isDoubleClick()
    {
        return doubleClickTicks > 0;
    }

    private ItemStackHandler createAshHandler()
    {
        return new ItemStackHandler()
        {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
            {
                return stack.getItem().equals(ItemRegistry.ASH);
            }
        };
    }

    private ItemStackHandler createFuelHandler()
    {
        return new ItemStackHandler()
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                super.onContentsChanged(slot);
                StoveTileEntity.this.markDirty();
                StoveTileEntity.this.refresh();
            }
        };
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new StoveContainer(i, playerInventory, pos, world);
    }

    @Override
    protected boolean isOpeningContainer(Container container)
    {
        return container instanceof StoveContainer;
    }
}
