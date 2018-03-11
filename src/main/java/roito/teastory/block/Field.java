package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Field extends Block
{
	protected static final AxisAlignedBB FIELD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public Field()
	{
		super(Material.GOURD);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.GROUND);
		this.setUnlocalizedName("field");
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(true)).withProperty(EAST, Boolean.valueOf(true)).withProperty(SOUTH, Boolean.valueOf(true)).withProperty(WEST, Boolean.valueOf(true)));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return FIELD_AABB;
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
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
	{
		if (worldIn.getBlockState(pos.up()).getBlock() != BlockLoader.xian_rice_plant)
		{
			worldIn.setBlockToAir(pos);
		}
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return state.withProperty(NORTH, Boolean.valueOf(this.hasWater(worldIn, pos.north()))).withProperty(EAST, Boolean.valueOf(this.hasWater(worldIn, pos.east()))).withProperty(SOUTH, Boolean.valueOf(this.hasWater(worldIn, pos.south()))).withProperty(WEST, Boolean.valueOf(this.hasWater(worldIn, pos.west())));
	}

	public boolean hasWater(IBlockAccess worldIn, BlockPos pos)
	{
		if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER)
			return true;
		else
			return false;
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
	{
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
	}
}
