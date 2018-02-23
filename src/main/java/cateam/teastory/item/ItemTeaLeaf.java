package cateam.teastory.item;

import java.util.Collections;
import java.util.List;

import cateam.teastory.TeaStory;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.block.EmptyKettle;
import cateam.teastory.block.Kettle;
import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemTeaLeaf extends TSItem
{
	private String drink;
	private int needLeavesPerCup;
	public ItemTeaLeaf(String name, int maxstack, String drink, int amount)
	{
		super(name, maxstack, CreativeTabsLoader.tabTeaStory);
		this.drink = drink;
		this.needLeavesPerCup = amount;
	}
	
	public int getAmount()
	{
		return this.needLeavesPerCup;
	}
	
	public String getTeaDrinkName(Item tool)
	{
		if (this == ItemLoader.black_tea_leaf || this == ItemLoader.black_tea_bag)
		{
			if (tool != null && tool == Items.MILK_BUCKET)
			{
				return "milk_tea";
			}
			if (tool != null)
			{
				for (ItemStack stack : OreDictionary.getOres("cropLemon"))
				{
					if (stack.getItem().equals(tool))
					{
						return "lemon_tea";
					}
				}
			}
		}
		if (this == ItemLoader.matcha_powder)
		{
			if (tool != null && tool == ItemLoader.tea_whisk)
			{
				return this.drink;
			}
			else return null;
		}
		return this.drink;
	}
	
	public boolean needSugar(String teaKind)
	{
		if (teaKind == "milk_tea" || teaKind == "lemon_tea")
		{
			return true;
		}
		else return false;
	}
	
	public Item getTool(String teaKind)
	{
		if (teaKind == "matcha_drink")
		{
			return ItemLoader.tea_whisk;
		}
		if (teaKind == "milk_tea")
		{
			return Items.MILK_BUCKET;
		}
		if (teaKind == "lemon_tea")
		{
			return ItemLoader.lemon;
		}
		return null;
	}
	
	public int getToolType(String teaKind)
	{
		if (teaKind == "matcha_drink")
		{
			return 2;
		}
		if (teaKind == "milk_tea")
		{
			return 3;
		}
		if (teaKind == "lemon_tea")
		{
			return 1;
		}
		return 0;
	}
}
