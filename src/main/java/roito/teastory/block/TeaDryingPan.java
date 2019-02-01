package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.item.ItemRegister;

import java.util.Random;

public class TeaDryingPan extends Block
{
	protected static final AxisAlignedBB TEADRYINGPAN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 4);

	public TeaDryingPan()
	{
		super(Material.IRON);
		this.setHardness(3.0F);
		this.setSoundType(SoundType.METAL);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0));
		this.setCreativeTab(CreativeTabsRegister.tabTeaStory);
		this.setTranslationKey("tea_drying_pan");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "tea_drying_pan"));
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
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return TEADRYINGPAN_AABB;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		drops.add(new ItemStack(BlockRegister.tea_drying_pan, 1));
	}

	@Override
	public IBlockState getStateFromMeta(int step)
	{
		return this.getDefaultState().withProperty(this.getStepProperty(), Integer.valueOf(step));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, STEP);
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
		if (this.getMetaFromState(state) == 0)
		{
			if (!playerIn.getHeldItem(hand).isEmpty())
			{
				if (playerIn.getHeldItem(hand).getItem() == Items.FLINT_AND_STEEL)
				{
					if (!playerIn.capabilities.isCreativeMode)
					{
						playerIn.getHeldItem(hand).setItemDamage(playerIn.getHeldItem(hand).getItemDamage() + 1);
					}
					worldIn.setBlockState(pos, BlockRegister.lit_tea_drying_pan.getDefaultState());
					return true;
				}
				else if ((playerIn.getHeldItem(hand).getItem() == ItemRegister.washed_rice) && (playerIn.getHeldItem(hand).getCount() >= ConfigMain.others.cookRiceBallEachTime * 8))
				{
					if (!playerIn.capabilities.isCreativeMode)
					{
						playerIn.getHeldItem(hand).shrink(ConfigMain.others.cookRiceBallEachTime * 8);
					}
					worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(STEP, 1));
					return true;
				}
			}
			if (worldIn.isRemote && !playerIn.getHeldItem(hand).isEmpty() && playerIn.getHeldItem(hand).getItem() == ItemRegister.washed_rice && playerIn.getHeldItem(hand).getCount() < ConfigMain.others.cookRiceBallEachTime * 8)
			{
				playerIn.sendMessage(new TextComponentTranslation("teastory.message.cooking_pan.notenough"));
				return true;
			}
			else if (worldIn.isRemote && (playerIn.getHeldItem(hand).isEmpty() || Block.getBlockFromItem(playerIn.getHeldItem(hand).getItem()) != BlockRegister.tea_drying_pan))
			{
				playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.tips"));
				return true;
			}
		}
		else if (this.getMetaFromState(state) == 1)
		{
			if (!playerIn.getHeldItem(hand).isEmpty())
			{
				if (playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET)
				{
					worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(STEP, 2));
					if (!playerIn.capabilities.isCreativeMode)
					{
						playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET.getContainerItem()));
					}
					return true;
				}
			}
			if (worldIn.isRemote)
			{
				playerIn.sendMessage(new TextComponentTranslation("teastory.message.cooking_pan.water"));
			}
		}
		else if (this.getMetaFromState(state) == 2)
		{
			if (!playerIn.getHeldItem(hand).isEmpty())
			{
				if (playerIn.getHeldItem(hand).getItem() == ItemRegister.wooden_lid)
				{
					if (!playerIn.capabilities.isCreativeMode)
					{
						playerIn.getHeldItem(hand).shrink(1);
					}
					worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(STEP, 3));
					return true;
				}
			}
			if (worldIn.isRemote)
			{
				playerIn.sendMessage(new TextComponentTranslation("teastory.message.cooking_pan.lid"));
			}
		}
		else if (this.getMetaFromState(state) == 3)
		{
			if (!playerIn.getHeldItem(hand).isEmpty())
			{
				if (playerIn.getHeldItem(hand).getItem() == Items.FLINT_AND_STEEL)
				{
					if (!playerIn.capabilities.isCreativeMode)
					{
						playerIn.getHeldItem(hand).setItemDamage(playerIn.getHeldItem(hand).getItemDamage() + 1);
					}
					worldIn.setBlockState(pos, BlockRegister.lit_cooking_pan.getDefaultState());
					return true;
				}
			}
			if (worldIn.isRemote)
			{
				playerIn.sendMessage(new TextComponentTranslation("teastory.message.cooking_pan.ignite"));
			}
		}
		else if (this.getMetaFromState(state) == 4)
		{
			if (!worldIn.isRemote)
			{
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegister.wooden_lid, 1));
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegister.rice_ball, ConfigMain.others.cookRiceBallEachTime));
			}
			worldIn.setBlockState(pos, BlockRegister.tea_drying_pan.getDefaultState());
			return true;
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		if (meta == 4)
		{
			double d0 = pos.getX();
			double d1 = pos.getY();
			double d2 = pos.getZ();
			worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.1D, 0.0D);
		}
	}

	public static String getSpecialName(ItemStack stack)
	{
		int meta = stack.getItemDamage();
		return "." + meta;
	}
}
