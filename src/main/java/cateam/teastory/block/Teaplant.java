package cateam.teastory.block;

import java.util.List;
import java.util.Random;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.Teapan.EnumType;
import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.IPlantable;

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
            int i = ((Integer)state.getValue(AGE)).intValue();
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
        float temperature = worldIn.getBiomeGenForCoords(pos).getFloatTemperature(pos);
        float humidity = worldIn.getBiomeGenForCoords(pos).rainfall;
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
        return c;
    }
    
    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            if (((Integer)state.getValue(AGE)).intValue() == 7)
        	{
            	playerIn.triggerAchievement(AchievementLoader.teaLeaf);
            	playerIn.triggerAchievement(AchievementLoader.teaPlant);
    	    	worldIn.setBlockState(pos, BlockLoader.teaplant.getStateFromMeta(4));
    	    	worldIn.spawnEntityInWorld(new EntityItem(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, new ItemStack(ItemLoader.tea_leaf, playerIn.getRNG().nextInt(4) + 1)));
    	        return true;
        	}
            else return false;
        }
    }
    
    @Override
    public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
        int age = ((Integer)state.getValue(AGE)).intValue();
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (age >= 7)
        {
         	ret.add(new ItemStack(this.getSeed(), 1, 0));
        }
        return ret;
    }
    
    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (!worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(worldIn, pos.down(), EnumFacing.UP, ItemLoader.tea_seeds))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 3);
        }
    }
}
