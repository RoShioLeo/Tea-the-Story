package roito.teastory.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import roito.teastory.common.AchievementLoader;
import roito.teastory.item.ItemLoader;

public class Teaplant extends BlockCrops
{
	public Teaplant()
	{
		this.setUnlocalizedName("teaplant");
	}

	@Override
	protected Item getSeed()
	{
		return ItemLoader.tea_seeds;
	}

	@Override
	protected Item getCrop()
	{
		return ItemLoader.tea_leaf;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
		{
			int i = state.getValue(AGE).intValue();
			if (i < 7)
			{
				float f = getGrowthChance(this, worldIn, pos);
				f = f * environmentChance(worldIn, pos);
				if (f != 0)
				{
					if (rand.nextInt((int)(25.0F / f) + 1) == 0)
					{
						worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
					}
				}
			}
		}
	}

	public static float environmentChance(World worldIn, BlockPos pos)
	{
		float c = 1.0F;
		float temperature = worldIn.getBiome(pos).getFloatTemperature(pos);
		float humidity = worldIn.getBiome(pos).getRainfall();
		float height = pos.getY();
		if (height <= 79)
		{
			c = 1.0F - (80 - height) * 0.02F;
		}
		else if (height >= 111)
		{
			c = 1.0F - (height - 110) * 0.04F;
		}
		c = c * (temperature >= 0.15F ? temperature >= 0.5F ? temperature > 0.95F ? 0.2F : 1.0F : 0.8F : 0.4F) * (humidity >= 0.2F ? humidity >= 0.5F ? humidity >= 0.8F ? 1.0F : 0.8F : 0.6F : 0.2F);
		if (c < 0.0F)
		{
			c = 0.0F;
		}
		return c * 0.4F;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		{
			if ((!worldIn.isRemote) && (state.getValue(AGE).intValue() == 7))
			{
				playerIn.addStat(AchievementLoader.teaPick);
				worldIn.setBlockState(pos, BlockLoader.teaplant.getStateFromMeta(4));
				worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(ItemLoader.tea_leaf, playerIn.getRNG().nextInt(3) + 1)));
				worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(ItemLoader.broken_tea, playerIn.getRNG().nextInt(5))));
				return true;
			}
			else return false;
		}
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		int age = state.getValue(AGE).intValue();
		if (age >= 7)
		{
			ret.add(new ItemStack(this.getSeed(), 1, 0));
		}
		return ret;
	}

	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return net.minecraftforge.common.EnumPlantType.Crop;
	}
}
