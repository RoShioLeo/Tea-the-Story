package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.item.ItemCup;

public class FullKettle extends Kettle
{
	private String drink;
	private String kettleKind;
	private String nextKettle;
	public boolean full;
	public FullKettle(String kettleKind, String drink, String nextKettle, boolean tabs, boolean a2)
	{
		super(a2 ? drink + "_" + kettleKind + "2" : drink + "_" + kettleKind, Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CAPACITY, 0));
		if (tabs)
		{
			this.setCreativeTab(CreativeTabsLoader.tabDrink);
		}
		this.drink = drink;
		this.full = tabs;
		this.kettleKind = kettleKind;
		this.nextKettle = nextKettle;
	}
	
	public int getMaxCapacity()
	{
		return ((EmptyKettle) Block.getBlockFromName(TeaStory.MODID + ":" + "empty_" + kettleKind)).getMaxCapacity();
	}
	
	public Block getNextKettle()
	{
		return Block.getBlockFromName(TeaStory.MODID + ":" + nextKettle);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,EnumFacing side, float hitX, float hitY, float hitZ)
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
					int meta2 = getMetaFromState(worldIn.getBlockState(pos));
					if ((meta2 >> 2) == 3)
					{
						worldIn.setBlockState(pos, getNextKettle().getDefaultState().withProperty(FACING, worldIn.getBlockState(pos).getValue(FACING)));
					}
					else
					{
						worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, worldIn.getBlockState(pos).getValue(FACING)).withProperty(CAPACITY, worldIn.getBlockState(pos).getValue(CAPACITY).intValue() + 1));
					}
					return true;
				}
				else return false;
			}
			else return false;
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
		EnumFacing facing = EnumFacing.getHorizontal(meta & 3);
		int cc = Integer.valueOf(meta >> 2);
		return this.getDefaultState().withProperty(FACING, facing).withProperty(CAPACITY, cc);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int facing = state.getValue(FACING).getHorizontalIndex();
		int cc = state.getValue(CAPACITY).intValue() << 2;
		return cc | facing;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state) & 12;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
		int cc = Integer.valueOf(placer.getHeldItem(hand).getItemDamage() >> 2);
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(CAPACITY, cc);
    }

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyInteger CAPACITY = PropertyInteger.create("capacity", 0, 3);
}

