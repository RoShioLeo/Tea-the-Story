package cateam.teastory.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import cateam.teastory.common.AchievementLoader;
import cateam.teastory.common.CreativeTabsLoader;
import cateam.teastory.helper.EntironmentHelper;
import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

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

	public static String getName(int meta)
	{
		return String.valueOf(meta);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		((EntityPlayer) placer).addStat(AchievementLoader.teaBasket);
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(itemIn, 1, 0));
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int step = getMetaFromState(worldIn.getBlockState(pos));
		if (worldIn.isRemote)
		{
			switch (step)
			{
			case 0:
				if ((heldItem == null)
						|| heldItem.stackSize < 8
						|| (Block.getBlockFromItem(heldItem.getItem()) != BlockLoader.teapan
						&& heldItem.getItem() != ItemLoader.half_dried_tea
						&& heldItem.getItem() != ItemLoader.tea_leaf 
						&& heldItem.getItem() != ItemLoader.wet_tea
						&& heldItem.getItem() != ItemLoader.dried_tea))
				{
					playerIn.addChatMessage(new TextComponentTranslation("teastory.message.teapan"));
				}
			}
			return true;
		} 
		else
		{
			if (step == 0)
			{
				if (heldItem != null)
				{
					if (heldItem.stackSize >= 8)
					{
						if (heldItem.getItem() == ItemLoader.tea_leaf)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(2));
							if (!playerIn.capabilities.isCreativeMode)
							{
								heldItem.stackSize = heldItem.stackSize - 8;
							}
							return true;
						} 
						else if (heldItem.getItem() == ItemLoader.wet_tea)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(1));
							if (!playerIn.capabilities.isCreativeMode)
							{
								heldItem.stackSize = heldItem.stackSize - 8;
							}
							return true;
						} 
						else if (heldItem.getItem() == ItemLoader.half_dried_tea)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(8));
							if (!playerIn.capabilities.isCreativeMode)
							{
								heldItem.stackSize = heldItem.stackSize - 8;
							}
							return true;
						}
						else if (heldItem.getItem() == ItemLoader.dried_tea)
						{
							worldIn.setBlockState(pos, BlockLoader.teapan.getStateFromMeta(9));
							if (!playerIn.capabilities.isCreativeMode)
							{
								heldItem.stackSize = heldItem.stackSize - 8;
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
				playerIn.addStat(AchievementLoader.halfDriedTea);
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
				//playerIn.addStat(AchievementLoader.wetTea);
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.yellow_tea_leaf, 8));
				return true;
			}
			else if (step == 1) 
			{
				worldIn.setBlockState(pos, BlockLoader.teapan.getDefaultState());
				playerIn.addStat(AchievementLoader.wetTea);
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.wet_tea, 8));
				return true;
			}
		}
		return false;
	}

}