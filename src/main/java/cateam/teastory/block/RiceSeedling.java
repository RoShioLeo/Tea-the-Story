package cateam.teastory.block;

import java.util.Random;

import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RiceSeedling extends BlockCrops
{
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	public RiceSeedling()
	{
		this.setUnlocalizedName("rice_seedling");
	}

	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return net.minecraftforge.common.EnumPlantType.Crop;
	}

	@Override
	protected Item getSeed()
	{
		return ItemLoader.rice_seeds;
	}

	@Override
	protected Item getCrop()
	{
		return ItemLoader.rice_seedlings;
	}

	protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
	{
		return BlockCrops.getGrowthChance(blockIn, worldIn, pos) * 2;
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		int age = state.getValue(AGE).intValue();
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		if (age == 7)
		{
			ret.add(new ItemStack(this.getCrop(), 3));
		}
		else
		{
			ret.add(new ItemStack(this.getSeed(), 1));
		}
		return ret;
	}
}
