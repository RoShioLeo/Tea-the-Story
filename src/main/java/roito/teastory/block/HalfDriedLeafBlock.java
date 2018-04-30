package roito.teastory.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.helper.EntironmentHelper;
import roito.teastory.item.ItemLoader;

public class HalfDriedLeafBlock extends Block
{
	public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 8);
	
	public HalfDriedLeafBlock()
	{
		super(Material.GRASS);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.PLANT);
		this.setTickRandomly(true);
		this.setUnlocalizedName("half_dried_leaf_block");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "half_dried_leaf_block"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0));
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { STEP });
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		if ((meta >= 0) && (meta <= 7))
		{
			int i = getLeafBlock(worldIn, pos.up()) + getLeafBlock(worldIn, pos.down()) + getLeafBlock(worldIn, pos.east()) + getLeafBlock(worldIn, pos.west()) + getLeafBlock(worldIn, pos.south()) + getLeafBlock(worldIn, pos.north());
			if (i>4) i = 4;
			float f = EntironmentHelper.getFermentationChance(worldIn, pos, false) * (1 + 0.125F * i);
			if (f == 0.0F || worldIn.canSeeSky(pos))
			{
				return;
			} 
			else if (rand.nextInt((int) (25.0F / f) + 1) == 0)
			{
				worldIn.setBlockState(pos, this.getStateFromMeta(meta + 1));
			}
		}
	}
	
	public static int getLeafBlock(World world, BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock() == BlockLoader.half_dried_leaf_block)
		{
			return 1;
		}
		return 0;
	}
	
	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockState, int fortune)
	{
		ArrayList drops = new ArrayList();
		int meta = this.getMetaFromState(blockState);
		if ((meta >= 0) && (meta <= 7))
		{
			drops.add(new ItemStack(ItemLoader.half_dried_tea, 9));
		} 
		else if (meta == 8)
		{
			drops.add(new ItemStack(ItemLoader.puer_tea_leaf, 9));
		}
		return drops;
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 8));
    }
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
		return new ItemStack(this, 1, getMetaFromState(state) == 8 ? 8 : 0);
    }
	
	@Override
	public IBlockState getStateFromMeta(int step)
	{
		return this.getDefaultState().withProperty(this.getStepProperty(), Integer.valueOf(step));
	}
	
	public static String getName(int meta)
	{
		return String.valueOf(meta);
	}

	protected PropertyInteger getStepProperty()
	{
		return STEP;
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(this.getStepProperty()).intValue();
	}
}
