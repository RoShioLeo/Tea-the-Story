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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.helper.EntironmentHelper;
import roito.teastory.item.ItemLoader;

public class Teapan extends Block
{
	protected static final AxisAlignedBB TEAPAN_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);

	public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 12);

	public Teapan()
	{
		super(Material.WOOD);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName("teapan");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "teapan"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0));
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return TEAPAN_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
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

	public boolean isTeapan(IBlockAccess worldIn, BlockPos pos)
	{
		if (worldIn.getBlockState(pos).getBlock() == BlockLoader.teapan)
			return false;
		else
			return true;
	}

	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune)
	{
		ArrayList drops = new ArrayList();
		drops.add(new ItemStack(BlockLoader.teapan, 1));
		int meta = BlockLoader.teapan.getMetaFromState(blockstate);
		if (meta == 1)
		{
			drops.add(new ItemStack(ItemLoader.wet_tea, 8));
		} 
		else if ((meta >= 2) && (meta <= 7))
		{
			drops.add(new ItemStack(ItemLoader.tea_leaf, 8));
		} 
		else if (meta == 8)
		{
			drops.add(new ItemStack(ItemLoader.half_dried_tea, 8));
		} 
		else if ((meta >= 9) && (meta <= 11))
		{
			drops.add(new ItemStack(ItemLoader.dried_tea, 8));
		}
		else if (meta == 12)
		{
			drops.add(new ItemStack(ItemLoader.yellow_tea, 8));
		}
		return drops;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);
		int step = getMetaFromState(worldIn.getBlockState(pos));
		if (step > 1 && worldIn.isRainingAt(pos.up()))
		{
			worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(1));
		} 
		else if ((step >= 1) && (step <= 12))
		{
			float f = EntironmentHelper.getDryingChance(worldIn, pos);
			if (f == 0.0F)
			{
				return;
			} 
			else if (rand.nextInt((int) (25.0F / f) + 1) == 0)
			{
				if (step >= 1 && step <= 7)
				{
					worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(step + 1));
				} 
				else if (step == 8 && worldIn.canSeeSky(pos))
				{
					worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(step + 1));
				}
				else if (step >= 9 && step <= 11 && !worldIn.canSeeSky(pos))
				{
					worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(step + 1));
				}
			}
		}
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
		return new ItemStack(this);
    }

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { STEP });
	}

	@Override
	public IBlockState getStateFromMeta(int step)
	{
		return this.getDefaultState().withProperty(this.getStepProperty(), Integer.valueOf(step));
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

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int step = getMetaFromState(worldIn.getBlockState(pos));
		if (worldIn.isRemote)
		{
			switch (step)
			{
			case 0:
				if ((playerIn.getHeldItem(hand).isEmpty())
						|| (Block.getBlockFromItem(playerIn.getHeldItem(hand).getItem()) != BlockLoader.teapan
						&& playerIn.getHeldItem(hand).getItem() != ItemLoader.half_dried_tea
						&& playerIn.getHeldItem(hand).getItem() != ItemLoader.tea_leaf 
						&& playerIn.getHeldItem(hand).getItem() != ItemLoader.wet_tea
						&& playerIn.getHeldItem(hand).getItem() != ItemLoader.dried_tea))
				{
					playerIn.sendMessage(new TextComponentTranslation("teastory.message.teapan.tips"));
				}
				else if (playerIn.getHeldItem(hand).getCount() < 8
						&& (playerIn.getHeldItem(hand).getItem() == ItemLoader.half_dried_tea
						|| playerIn.getHeldItem(hand).getItem() == ItemLoader.tea_leaf 
						|| playerIn.getHeldItem(hand).getItem() == ItemLoader.wet_tea
						|| playerIn.getHeldItem(hand).getItem() == ItemLoader.dried_tea))
				{
					playerIn.sendMessage(new TextComponentTranslation("teastory.message.teapan.notenough"));
				}
			}
			return true;
		} 
		else
		{
			if (step == 0)
			{
				if (!playerIn.getHeldItem(hand).isEmpty())
				{
					if (playerIn.getHeldItem(hand).getCount() >= 8)
					{
						if (playerIn.getHeldItem(hand).getItem() == ItemLoader.tea_leaf)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(2));
							if (!playerIn.capabilities.isCreativeMode)
							{
								playerIn.getHeldItem(hand).shrink(8);
							}
							return true;
						} 
						else if (playerIn.getHeldItem(hand).getItem() == ItemLoader.wet_tea)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(1));
							if (!playerIn.capabilities.isCreativeMode)
							{
								playerIn.getHeldItem(hand).shrink(8);
							}
							return true;
						} 
						else if (playerIn.getHeldItem(hand).getItem() == ItemLoader.half_dried_tea)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(8));
							if (!playerIn.capabilities.isCreativeMode)
							{
								playerIn.getHeldItem(hand).shrink(8);
							}
							return true;
						}
						else if (playerIn.getHeldItem(hand).getItem() == ItemLoader.dried_tea)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(9));
							if (!playerIn.capabilities.isCreativeMode)
							{
								playerIn.getHeldItem(hand).shrink(8);
							}
							return true;
						}
					}
				}
				return false;
			} 
			else if ((step >= 2) && (step <= 7))
			{
				worldIn.setBlockState(pos, BlockLoader.teapan.getDefaultState());
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.tea_leaf, 8));
				return true;
			} 
			else if (step == 8)
			{
				worldIn.setBlockState(pos, BlockLoader.teapan.getDefaultState());
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.half_dried_tea, 8));
				return true;
			} 
			else if ((step >= 9) && (step <= 11))
			{
				worldIn.setBlockState(pos, BlockLoader.teapan.getDefaultState());
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.dried_tea, 8));
				return true;
			}
			else if (step == 12) 
			{
				worldIn.setBlockState(pos, BlockLoader.teapan.getDefaultState());
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.yellow_tea_leaf, 8));
				return true;
			}
			else if (step == 1) 
			{
				worldIn.setBlockState(pos, BlockLoader.teapan.getDefaultState());
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.wet_tea, 8));
				return true;
			}
		}
		return false;
	}

}