package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.item.ItemCup;
import roito.teastory.tileentity.TileEntityKettle;

public class FullKettle extends Kettle implements ITileEntityProvider
{
	private String drink;
	private String kettleKind;

	public FullKettle(String kettleKind, String drink, String nextKettle)
	{
		super(drink + "_" + kettleKind, Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CAPACITY, 0));
		this.setCreativeTab(CreativeTabsRegister.tabDrink);
		this.drink = drink;
		this.kettleKind = kettleKind;
	}

	public int getMaxCapacity()
	{
		return ((EmptyKettle) Block.getBlockFromName(TeaStory.MODID + ":" + "empty_" + kettleKind)).getMaxCapacity();
	}

	public Block getEmptyKettle()
	{
		return Block.getBlockFromName(TeaStory.MODID + ":" + "empty_" + kettleKind);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
		if (worldIn.isRemote)
		{
			return true;
		}
		else
		{
			if (!playerIn.getHeldItem(hand).isEmpty())
			{
				if (playerIn.getHeldItem(hand).getItem() instanceof ItemCup)
				{
					if (!playerIn.capabilities.isCreativeMode)
					{
						playerIn.getHeldItem(hand).shrink(1);
					}
					int meta = playerIn.getHeldItem(hand).getItemDamage();
					ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(this.getDrink(this), 1, meta));
					if (state.getValue(CAPACITY) >= getMaxCapacity() - 1)
					{
						worldIn.setBlockState(pos, getEmptyKettle().getDefaultState().withProperty(FACING, state.getValue(FACING)));
					}
					else
					{
						TileEntity tileentity = worldIn.getTileEntity(pos);
						((TileEntityKettle) tileentity).setCapacity(state.getValue(CAPACITY) + 1);
					}
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}

	public Item getDrink(FullKettle kettle)
	{
		return Item.getByNameOrId(TeaStory.MODID + ":" + drink);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING, CAPACITY);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.byHorizontalIndex(meta);
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(CAPACITY);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(CAPACITY, placer.getHeldItem(hand).getItemDamage());
	}

	public void setState(int cc, World worldIn, BlockPos pos)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(CAPACITY, cc));

		if (tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityKettle();
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		TileEntityKettle te = new TileEntityKettle();
		te.setCapacity(state.getValue(CAPACITY));
		return te;
	}

	public static final PropertyInteger CAPACITY = PropertyInteger.create("capability", 0, 7);
}

