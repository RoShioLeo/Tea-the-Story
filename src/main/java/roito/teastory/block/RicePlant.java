package roito.teastory.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import roito.teastory.item.ItemLoader;

public class RicePlant extends BlockCrops
{
	private static final AxisAlignedBB[] SEEDLING_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	public RicePlant()
	{
		this.setUnlocalizedName("rice_plant");
	}

	@Override
	protected boolean canSustainBush(IBlockState state)
	{
		return state.getBlock() == Blocks.WATER;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SEEDLING_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }

	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return net.minecraftforge.common.EnumPlantType.Water;
	}

	@Override
	protected Item getSeed()
	{
		return ItemLoader.rice_seedlings;
	}

	@Override
	protected Item getCrop()
	{
		return ItemLoader.rice_seeds;
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		int age = state.getValue(AGE).intValue();
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		if (age >= 7)
		{
			ret.add(new ItemStack(this.getCrop(), rand.nextInt(3) + 1));
			if (rand.nextBoolean())
			{
				ret.add(new ItemStack(ItemLoader.straw, 1));
			}
		}
		else
		{
			ret.add(new ItemStack(this.getSeed(), 1));
		}
		return ret;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.checkAndDropBlock(worldIn, pos, state);
		if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge())
            {
                float f = getGrowthChance(this, worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
		int i = this.getAge(state);
		if ((worldIn.getBlockState(pos.down()).getBlock() != BlockLoader.field) && (i >= 5))
		{
			worldIn.setBlockState(pos.down(), BlockLoader.field.getDefaultState());
		}
	}
	
	protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
		if (worldIn.isRainingAt(pos))
		{
			return BlockCrops.getGrowthChance(blockIn, worldIn, pos) * 1.25F;
		}
		return BlockCrops.getGrowthChance(blockIn, worldIn, pos);
    }

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	{
		IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
		Material material = iblockstate1.getMaterial();
		IBlockState iblockstate2 = worldIn.getBlockState(pos.down(2));

		return (material == Material.WATER && iblockstate1.getValue(BlockLiquid.LEVEL).intValue() == 0 && iblockstate2.getBlock() instanceof BlockFarmland) || (iblockstate1.getBlock() == BlockLoader.field);
	}
}
