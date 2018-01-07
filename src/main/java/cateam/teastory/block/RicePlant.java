package cateam.teastory.block;

import java.util.Random;

import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RicePlant extends BlockCrops
{
	public RicePlant()
	{
		this.setUnlocalizedName("rice_plant");
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.WATER;
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
        int age = ((Integer)state.getValue(AGE)).intValue();
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (age >= 7)
        {
         	ret.add(new ItemStack(this.getCrop(), rand.nextInt(3) + 1));
         	ret.add(new ItemStack(ItemLoader.straw, 1));
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
		super.updateTick(worldIn, pos, state, rand);
		int i = this.getAge(state);
		if ((worldIn.getBlockState(pos.down()).getBlock() != BlockLoader.field) && (i >= 5))
		{
			worldIn.setBlockState(pos.down(), BlockLoader.field.getDefaultState());
		}
    }
	
	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
        Material material = iblockstate1.getMaterial();
        IBlockState iblockstate2 = worldIn.getBlockState(pos.down(2));
        
        return (material == Material.WATER && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && iblockstate2.getBlock() instanceof BlockFarmland) || (iblockstate1.getBlock() == BlockLoader.field);
    }
}
