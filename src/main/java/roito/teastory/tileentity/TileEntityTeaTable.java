package roito.teastory.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import roito.teastory.api.recipe.ITeaTableRecipe;
import roito.teastory.item.ItemWaterPot;

import static roito.teastory.api.recipe.TeaTableRecipe.EMPTY_RECIPE;
import static roito.teastory.common.RecipeRegister.managerTeaTable;

public class TileEntityTeaTable extends TileEntity implements ITickable
{
    protected int teaTime = 0;
    protected ITeaTableRecipe usedRecipe = EMPTY_RECIPE;

    protected ItemStackHandler InventoryTool = new ItemStackHandler();
    protected ItemStackHandler InventoryWater = new ItemStackHandler();
    protected ItemStackHandler InventoryLeaf = new ItemStackHandler();
    protected ItemStackHandler InventoryCup = new ItemStackHandler();
    protected ItemStackHandler InventorySugar = new ItemStackHandler();
    protected ItemStackHandler InventoryDrink = new ItemStackHandler();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            if (facing == EnumFacing.EAST)
            {
                return (T) InventoryTool;
            }
            else if (facing == EnumFacing.SOUTH)
            {
                return (T) InventoryWater;
            }
            else if (facing == EnumFacing.UP)
            {
                return (T) InventoryLeaf;
            }
            else if (facing == EnumFacing.NORTH)
            {
                return (T) InventoryCup;
            }
            else if (facing == EnumFacing.WEST)
            {
                return (T) InventorySugar;
            }
            else
            {
                return (T) InventoryDrink;
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.InventoryTool.deserializeNBT(compound.getCompoundTag("InventoryTool"));
        this.InventoryWater.deserializeNBT(compound.getCompoundTag("InventoryWater"));
        this.InventoryLeaf.deserializeNBT(compound.getCompoundTag("InventoryLeaf"));
        this.InventoryCup.deserializeNBT(compound.getCompoundTag("InventoryCup"));
        this.InventorySugar.deserializeNBT(compound.getCompoundTag("InventorySugar"));
        this.InventoryDrink.deserializeNBT(compound.getCompoundTag("InventoryDrink"));
        this.teaTime = compound.getInteger("TeaTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag("InventoryTool", this.InventoryTool.serializeNBT());
        compound.setTag("InventoryWater", this.InventoryWater.serializeNBT());
        compound.setTag("InventoryLeaf", this.InventoryLeaf.serializeNBT());
        compound.setTag("InventoryCup", this.InventoryCup.serializeNBT());
        compound.setTag("InventorySugar", this.InventorySugar.serializeNBT());
        compound.setTag("InventoryDrink", this.InventoryDrink.serializeNBT());
        compound.setInteger("TeaTime", this.teaTime);
        return super.writeToNBT(compound);
    }

    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            ItemStack leaf = InventoryLeaf.getStackInSlot(0).copy();
            ItemStack cup = InventoryCup.getStackInSlot(0).copy();
            ItemStack water = InventoryWater.getStackInSlot(0).copy();
            ItemStack sugar = InventorySugar.getStackInSlot(0).copy();
            ItemStack tool = InventoryTool.getStackInSlot(0).copy();
            if (water.isEmpty() || !(water.getItem() instanceof ItemWaterPot))
            {
                this.teaTime = 0;
                this.markDirty();
                return;
            }
            if (!usedRecipe.isTheSameInput(leaf, tool, cup, sugar))
            {
                usedRecipe = managerTeaTable.getRecipe(leaf, tool, cup, sugar);
            }
            if (!usedRecipe.getOutput().isEmpty())
            {
                ItemStack output = usedRecipe.getOutput().copy();
                if (this.InventoryDrink.insertItem(0, output, true).isEmpty() && leaf.getCount() >= usedRecipe.getTeaLeafInput().get(0).getCount() && cup.getCount() >= usedRecipe.getCupInput().getCount() && sugar.getCount() >= usedRecipe.getSugarInput().get(0).getCount() && tool.getCount() >= usedRecipe.getToolInput().get(0).getCount())
                {
                    if (++this.teaTime >= 400)
                    {
                        InventoryDrink.insertItem(0, output, false);
                        InventoryLeaf.extractItem(0, usedRecipe.getTeaLeafInput().get(0).getCount(), false);
                        InventoryCup.extractItem(0, usedRecipe.getCupInput().getCount(), false);
                        InventoryWater.setStackInSlot(0, ((ItemWaterPot) water.getItem()).getNext(InventoryWater.getStackInSlot(0), 1));

                        if (tool.isItemStackDamageable())
                        {
                            if (tool.getItemDamage() + 1 <= tool.getMaxDamage())
                            {
                                InventoryTool.setStackInSlot(0, new ItemStack(tool.getItem(), 1, tool.getItemDamage() + 1));
                            }
                            else
                            {
                                InventoryTool.extractItem(0, 1, false);
                            }
                        }
                        else if (tool.isStackable())
                        {
                            InventoryTool.extractItem(0, usedRecipe.getToolInput().get(0).getCount(), false);
                        }
                        else
                        {
                            InventoryTool.setStackInSlot(0, tool.getItem().getContainerItem(tool));
                        }

                        if (!sugar.isEmpty())
                        {
                            InventorySugar.extractItem(0, sugar.getCount(), false);
                        }

                        this.teaTime = 0;
                    }
                    this.markDirty();
                    return;
                }
            }
            this.teaTime = 0;
            this.markDirty();
            return;
        }
    }

    public int getTeaTime()
    {
        return this.teaTime;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }
}
