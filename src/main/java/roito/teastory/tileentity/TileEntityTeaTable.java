package roito.teastory.tileentity;

import java.util.Collections;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
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
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.TeaStory;
import roito.teastory.block.EmptyKettle;
import roito.teastory.helper.NonNullListHelper;
import roito.teastory.item.ItemCup;
import roito.teastory.item.ItemTeaLeaf;
import roito.teastory.item.ItemWaterPot;
import roito.teastory.recipe.ITeaTableRecipe;
import roito.teastory.recipe.RecipeRegister;
import roito.teastory.recipe.TeaTableRecipe;

public class TileEntityTeaTable extends TileEntity implements ITickable
{
	protected int teaTime = 0;
	
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
			if(facing == EnumFacing.EAST)
			{
				return (T) InventoryTool;
			}
			else if(facing == EnumFacing.SOUTH)
			{
				return (T) InventoryWater;
			}
			else if(facing == EnumFacing.UP)
			{
				return (T) InventoryLeaf;
			}
			else if(facing == EnumFacing.NORTH)
			{
				return (T) InventoryCup;
			}
			else if(facing == EnumFacing.WEST)
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
			ItemStack leaf = InventoryLeaf.extractItem(0, 1, true).copy();
			ItemStack cup = InventoryCup.extractItem(0, 1, true).copy();
			ItemStack water = InventoryWater.extractItem(0, 1, true).copy();
			ItemStack sugar = InventorySugar.extractItem(0, 1, true).copy();
			ItemStack tool = InventoryTool.extractItem(0, 1, true).copy();
			tool.setItemDamage(OreDictionary.WILDCARD_VALUE);
			if (water != ItemStack.EMPTY && water.getItem() instanceof ItemWaterPot)
			{
				ITeaTableRecipe recipeIn = new TeaTableRecipe(leaf, NonNullListHelper.createNonNullList(tool), cup, NonNullListHelper.createNonNullList(sugar), ItemStack.EMPTY);
				ITeaTableRecipe recipeUse = new TeaTableRecipe(ItemStack.EMPTY, NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY, NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY);
				for (ITeaTableRecipe recipe : RecipeRegister.managerTeaTable.getRecipes())
				{
					if (recipe.equals(recipeIn))
					{
						recipeUse = recipe;
						break;
					}
				}
				if (recipeUse.getOutput() == ItemStack.EMPTY)
				{
					recipeIn = new TeaTableRecipe(leaf, NonNullListHelper.createNonNullList(ItemStack.EMPTY), cup, NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY);
					for (ITeaTableRecipe recipe : RecipeRegister.managerTeaTable.getRecipes())
					{
						if (recipe.equals(recipeIn))
						{
							recipeUse = recipe;
							break;
						}
					}
				}
				if (recipeUse.getOutput().isEmpty() || !InventoryDrink.insertItem(0, recipeUse.getOutput(), true).isEmpty())
				{
					this.teaTime = 0;
					this.markDirty();
					return;
				}
				tool = InventoryTool.extractItem(0, 1, true).copy();
				int waterRemain = ((ItemWaterPot) water.getItem()).getRemainWater(water);
				boolean needSugar = recipeUse.getSugarInput().get(0) != ItemStack.EMPTY;
				int sugars = 0;
				if (needSugar)
				{
					sugars = InventorySugar.getStackInSlot(0).copy().getCount();
				}
				boolean isKettle = Block.getBlockFromItem(cup.getItem()) instanceof EmptyKettle;
				int capacity = isKettle ? ((EmptyKettle)Block.getBlockFromItem(cup.getItem())).getKettleName() == "porcelain_kettle" ? 4 : 8 : 1;
				boolean needTool = recipeUse.getToolInput().get(0) != ItemStack.EMPTY;
				int toolRemain = 0;
				int toolType = 0; // 0: Null, 1: Amount, 2:Damage, 3:Bucket
				if (needTool)
				{
					if (tool.getMaxDamage() != 0)
					{
						toolRemain = tool.getMaxDamage() - tool.getItemDamage() + 1;
						toolType = 2;
					}
					else if (tool.getMaxStackSize() == 1)
					{
						toolRemain = 16;
						toolType = 3;
					}
					else
					{
						toolRemain = InventoryTool.getStackInSlot(0).copy().getCount();
						toolType = 1;
					}
				}
				int cc = 0;
				int teaAmount = InventoryLeaf.getStackInSlot(0).copy().getCount() / recipeUse.getTeaLeafInput().copy().getCount();
				cc =getMin(waterRemain, capacity, teaAmount, needTool, toolRemain, needSugar, sugars);
				if (cc > 0)
				{
					this.teaTime++;
				}
				else
				{
					this.teaTime = 0;
				}
				if (teaTime >= 400)
				{
					if (isKettle)
					{
						ItemStack kettle = recipeUse.getOutput().copy();
						kettle.setItemDamage(capacity - cc);
						InventoryDrink.insertItem(0, kettle, false);
					}
					else
					{
						InventoryDrink.insertItem(0, recipeUse.getOutput().copy(), false);
					}
					InventoryLeaf.extractItem(0, cc * recipeUse.getTeaLeafInput().copy().getCount(), false);
					InventoryCup.extractItem(0, 1, false);
					if (needSugar)
					{
						InventorySugar.extractItem(0, cc * 3, false);
					}
					if (needTool)
					{
						if (toolType == 1)
						{
							InventoryTool.extractItem(0, cc, false);
						}
						else if (toolType == 2)
						{
							if (InventoryTool.getStackInSlot(0).copy().getItemDamage() + cc <= InventoryTool.getStackInSlot(0).copy().getMaxDamage())
							{
								InventoryTool.setStackInSlot(0, new ItemStack(InventoryTool.getStackInSlot(0).copy().getItem(), 1, InventoryTool.getStackInSlot(0).copy().getItemDamage() + cc));
							}
							else
							{
								InventoryTool.extractItem(0, 1, false);
							}
						}
						else if (toolType == 3)
						{
							InventoryTool.setStackInSlot(0, InventoryTool.getStackInSlot(0).getItem().getContainerItem(InventoryTool.getStackInSlot(0)));
						}
					}
					InventoryWater.setStackInSlot(0, ((ItemWaterPot) InventoryWater.getStackInSlot(0).getItem()).getNext(InventoryWater.getStackInSlot(0), cc));
					this.teaTime = 0;
				}
			}
			else
			{
				this.teaTime = 0;
			}
			this.markDirty();
		}
	}
	
	public static int getMin(int hotWater, int capacity, int teaLeaf, boolean tool, int toolRemain, boolean sugar, int sugars)
	{
		int min = Math.min(hotWater, Math.min(capacity, teaLeaf));
		if (tool)
		{
			min = Math.min(min, toolRemain);
		}
		if (sugar)
		{
			min = Math.min(min, sugars/3);
		}
		return min;
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
