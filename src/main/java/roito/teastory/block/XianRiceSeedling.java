package roito.teastory.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.item.ItemRegister;

public class XianRiceSeedling extends BlockCrops
{
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	private static final AxisAlignedBB[] SEEDLING_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)};
	
	public XianRiceSeedling()
	{
		this.setUnlocalizedName("xian_rice_seedling");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "xian_rice_seedling"));
	}

	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return net.minecraftforge.common.EnumPlantType.Crop;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SEEDLING_AABB[state.getValue(this.getAgeProperty()).intValue()];
    }

	@Override
	protected Item getSeed()
	{
		return ItemRegister.xian_rice_seedlings;
	}

	@Override
	protected Item getCrop()
	{
		return ItemRegister.xian_rice_seedlings;
	}

	protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
	{
		return BlockCrops.getGrowthChance(blockIn, worldIn, pos) * 2;
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.isMaxAge(state) ? this.getCrop() : ItemRegister.xian_rice_seeds;
    }

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
		int age = state.getValue(AGE).intValue();
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		if (age == 7)
		{
			drops.add(new ItemStack(this.getCrop(), 3));
		}
		else
		{
			drops.add(new ItemStack(ItemRegister.xian_rice_seeds));
		}
	}
}
