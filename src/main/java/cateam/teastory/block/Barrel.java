package cateam.teastory.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class Barrel extends Block
{
	public Barrel()
	{
		super(Material.WOOD);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.WOOD);
		this.setTickRandomly(true);
		this.setUnlocalizedName("barrel");
		this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0));
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
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
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune)
	{
		ArrayList drops = new ArrayList();
		drops.add(new ItemStack(BlockLoader.barrel, 1));
		int meta = BlockLoader.barrel.getMetaFromState(blockstate);
		if ((meta >= 1) && (meta <= 7))
		{
			drops.add(new ItemStack(ItemLoader.half_dried_tea, 8));
		} 
		else if (meta == 8)
		{
			drops.add(new ItemStack(ItemLoader.black_tea_leaf, 8));
		}
		return drops;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		if ((meta >= 2) && (meta <= 7))
		{
			float f = EntironmentHelper.getFermentationChance(worldIn, pos, false);
			if (f == 0.0F)
			{
				return;
			} 
			else if (rand.nextInt((int) (25.0F / f) + 1) == 0)
			{
				worldIn.setBlockState(pos, BlockLoader.barrel.getStateFromMeta(meta + 1));
			}
		}
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer)
	{
		((EntityPlayer) placer).addStat(AchievementLoader.barrel);
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

	public static String getName(int meta)
	{
		return String.valueOf(meta);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(this.getStepProperty()).intValue();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int step = getMetaFromState(worldIn.getBlockState(pos));
		if (worldIn.isRemote)
		{
			if (step == 0)
			{
				if ((heldItem == null) || !(heldItem.getItem() == ItemLoader.half_dried_tea && heldItem.stackSize >= 8)
						&& (Block.getBlockFromItem(heldItem.getItem()) != BlockLoader.barrel))
				{
					playerIn.addChatMessage(new TextComponentTranslation("teastory.message.barrel.1"));
				} 
				else if ((heldItem != null) && (heldItem.getItem() == ItemLoader.half_dried_tea && heldItem.stackSize >= 8))
				{
					playerIn.addChatMessage(new TextComponentTranslation("teastory.message.barrel.2"));
				}
				return true;
			} 
			else if ((step >= 2) && (step <= 4))
			{
				if (!playerIn.isSneaking())
				{
					playerIn.addChatMessage(new TextComponentTranslation("teastory.message.barrel.3"));
				}
				return true;
			} 
			else if ((step >= 5) && (step <= 7))
			{
				if (!(playerIn.isSneaking()))
				{
					playerIn.addChatMessage(new TextComponentTranslation("teastory.message.barrel.4"));
				}
				return true;
			} 
			else if (step == 8)
			{
				if (!(playerIn.isSneaking()))
				{
					playerIn.addChatMessage(new TextComponentTranslation("teastory.message.barrel.5"));
				}
				return true;
			}
		} 
		else
		{
			if (step == 0)
			{
				if (heldItem != null)
				{
					if (heldItem.getItem() == ItemLoader.half_dried_tea && heldItem.stackSize >= 8)
					{
						worldIn.setBlockState(pos, BlockLoader.barrel.getStateFromMeta(1));
						if (!playerIn.capabilities.isCreativeMode)
							heldItem.stackSize = heldItem.stackSize - 8;
						return true;
					} 
					else
						return false;
				} 
				else
					return false;
			} 
			else if (step == 1)
			{
				if ((heldItem == null))
				{
					if (playerIn.isSneaking())
					{
						worldIn.setBlockState(pos, BlockLoader.barrel.getStateFromMeta(0));
						ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.half_dried_tea, 8));
						return true;
					} 
					else
					{
						worldIn.setBlockState(pos, BlockLoader.barrel.getStateFromMeta(2));
						return true;
					}
				} 
				else
					return false;
			} 
			else if ((step >= 2) && (step <= 7))
			{
				if (heldItem == null)
				{
					if (playerIn.isSneaking())
					{
						worldIn.setBlockState(pos, BlockLoader.barrel.getStateFromMeta(0));
						ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.half_dried_tea, 8));
						return true;
					} 
					else
						return false;
				}
			} 
			else if (step == 8)
			{
				if (heldItem == null)
				{
					if (playerIn.isSneaking())
					{
						worldIn.setBlockState(pos, BlockLoader.barrel.getStateFromMeta(0));
						ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.black_tea_leaf, 8));
						return true;
					} 
					else
						return false;
				} 
				else
					return false;
			}
		}
		return false;
	}

	public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 8);
}