package cloud.lemonslice.teastory.common.tileentity;

import cloud.lemonslice.silveroak.common.environment.Humidity;
import cloud.lemonslice.teastory.common.block.craft.BambooTrayMode;
import cloud.lemonslice.teastory.common.block.craft.CatapultBoardBlockWithTray;
import cloud.lemonslice.teastory.common.block.craft.IStoveBlock;
import cloud.lemonslice.teastory.common.container.BambooTrayContainer;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTraySingleInRecipe;
import cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.ENABLED;

public class BambooTrayTileEntity extends NormalContainerTileEntity implements ITickableTileEntity, IInventory
{
    private int processTicks = 0;
    private int totalTicks = 0;

    private int randomSeed = new Random().nextInt(943943);
    private int doubleClickTicks = 0;

    private BambooTrayMode mode = BambooTrayMode.OUTDOORS;

    private final LazyOptional<ItemStackHandler> containerInventory = LazyOptional.of(this::createHandler);
    private BambooTraySingleInRecipe currentRecipe;

    public BambooTrayTileEntity()
    {
        super(TileEntityTypeRegistry.BAMBOO_TRAY);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag)
    {
        super.read(state, tag);
        this.containerInventory.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(tag.getCompound("Inv")));
        this.processTicks = tag.getInt("ProcessTicks");
        this.totalTicks = tag.getInt("TotalTicks");
        this.mode = BambooTrayMode.values()[tag.getInt("Mode")];
    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {
        containerInventory.ifPresent(h -> tag.put("Inv", ((INBTSerializable<CompoundNBT>) h).serializeNBT()));
        tag.putInt("ProcessTicks", processTicks);
        tag.putInt("TotalTicks", totalTicks);
        tag.putInt("Mode", mode.ordinal());
        return super.write(tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (!this.removed && CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(cap))
        {
            return containerInventory.cast();
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
        float temp = this.world.getBiome(pos).getTemperature(pos);
        float rainfall = this.world.getBiome(pos).getDownfall();
        switch (BambooTrayMode.getMode(this.world, this.pos))
        {
            case IN_RAIN:
                this.process(NormalRecipeTypes.BT_IN_RAIN, Humidity.getHumid(rainfall, temp).getCoefficient());
                this.mode = BambooTrayMode.IN_RAIN;
                return;
            case OUTDOORS:
                if (!this.isWorldRaining())
                    this.process(NormalRecipeTypes.BT_OUTDOORS, Humidity.getHumid(rainfall, temp).getCoefficient());
                this.mode = BambooTrayMode.OUTDOORS;
                return;
            case INDOORS:
                this.process(NormalRecipeTypes.BT_INDOORS, Humidity.getHumid(rainfall, temp).getCoefficient());
                this.mode = BambooTrayMode.INDOORS;
                return;
            case BAKE:
                this.process(NormalRecipeTypes.BT_BAKE, Humidity.getHumid(rainfall, temp).getCoefficient());
                this.mode = BambooTrayMode.BAKE;
                return;
            case PROCESS:
                //TODO
                this.mode = BambooTrayMode.PROCESS;
        }
    }

    private boolean process(IRecipeType recipeType, float coefficient)
    {
        ItemStack input = getInput();
        if (input.isEmpty())
        {
            setToZero();
            return false;
        }
        if (this.currentRecipe == null || !this.currentRecipe.getIngredient().test(input) || mode != BambooTrayMode.getMode(this.world, this.pos))
        {
            this.currentRecipe = this.world.getRecipeManager().getRecipe((IRecipeType<BambooTraySingleInRecipe>) recipeType, this, this.world).orElse(null);
        }
        if (currentRecipe != null && !getOutput().isEmpty())
        {
            this.refreshTotalTicks(currentRecipe.getWorkTime(), coefficient);
            if (this.mode == BambooTrayMode.BAKE)
            {
                this.processTicks += ((IStoveBlock) this.getWorld().getBlockState(pos.down()).getBlock()).getFuelPower();
            }
            else
            {
                this.processTicks++;
            }
            if (this.processTicks >= this.totalTicks)
            {
                ItemStack output = this.getOutput();
                output.setCount(input.getCount());
                this.containerInventory.ifPresent(inv ->
                        inv.setStackInSlot(0, output));
                this.processTicks = 0;
                if (this.getBlockState().getBlock() instanceof CatapultBoardBlockWithTray && world.isBlockPowered(pos))
                {
                    world.setBlockState(pos, this.getBlockState().with(ENABLED, true));
                    CatapultBoardBlockWithTray.shoot(world, pos);
                    world.getPendingBlockTicks().scheduleTick(pos, this.getBlockState().getBlock(), 5);
                }
                this.markDirty();
            }
            return true;
        }
        setToZero();
        return false;
    }

    public ItemStack getInput()
    {
        return this.containerInventory.orElse(new ItemStackHandler()).getStackInSlot(0).copy();
    }

    public ItemStack getOutput()
    {
        if (currentRecipe == null)
        {
            return ItemStack.EMPTY;
        }
        return this.currentRecipe.getRecipeOutput().copy();
    }

    public int getTotalTicks()
    {
        return this.totalTicks;
    }

    public int getProcessTicks()
    {
        return this.processTicks;
    }

    public BambooTrayMode getMode()
    {
        return this.mode;
    }

    public IRecipeType<?> getRecipeType()
    {
        switch (this.mode)
        {
            case IN_RAIN:
                return NormalRecipeTypes.BT_IN_RAIN;
            case OUTDOORS:
                return NormalRecipeTypes.BT_OUTDOORS;
            case INDOORS:
                return NormalRecipeTypes.BT_INDOORS;
            default:
                return NormalRecipeTypes.BT_BAKE;
        }
    }

    public int getRandomSeed()
    {
        return this.randomSeed;
    }

    public void refreshSeed()
    {
        this.randomSeed = (int) (Math.random() * 10000);
    }

    public boolean isWorldRaining()
    {
        return this.getWorld().isRaining();
    }

    public boolean isWorking()
    {
        if (this.currentRecipe == null)
        {
            return false;
        }
        return !this.currentRecipe.getRecipeOutput().isEmpty();
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

    private void refreshTotalTicks(int basicTicks, float coefficient)
    {
        this.totalTicks = (int) (this.getInput().getCount() * basicTicks * coefficient);
    }

    private void setToZero()
    {
        if (this.processTicks != 0)
        {
            this.processTicks = 0;
            this.markDirty();
        }
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new BambooTrayContainer(i, playerInventory, pos, world);
    }

    private ItemStackHandler createHandler()
    {
        return new ItemStackHandler()
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                BambooTrayTileEntity.this.markDirty();
                BambooTrayTileEntity.this.refresh();
                super.onContentsChanged(slot);
            }
        };
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return this.containerInventory.map(h -> h.getStackInSlot(0).isEmpty()).orElse(true);
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.containerInventory.map(h -> h.getStackInSlot(0)).orElse(ItemStack.EMPTY);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return this.containerInventory.map(h -> h.getStackInSlot(0).split(count)).orElse(ItemStack.EMPTY);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        this.containerInventory.ifPresent(h -> h.setStackInSlot(0, ItemStack.EMPTY));
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.containerInventory.ifPresent(h -> h.setStackInSlot(0, stack));
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player)
    {
        return true;
    }

    @Override
    public void clear()
    {
        removeStackFromSlot(0);
    }

    @Override
    protected boolean isOpeningContainer(Container container)
    {
        return container instanceof BambooTrayContainer;
    }
}
