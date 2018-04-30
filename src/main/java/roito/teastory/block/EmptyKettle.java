package roito.teastory.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import roito.teastory.common.CreativeTabsLoader;

public class EmptyKettle extends Kettle
{
	private String kettle;
	private int max;
	public EmptyKettle(String name, int maxCapacity)
	{
		super("empty_" + name, Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(CreativeTabsLoader.tabDrink);
		this.kettle = name;
		this.max = maxCapacity;
	}
	
	public int getMaxCapacity()
	{
		return this.max;
	}
	
	public String getKettleName()
	{
		return this.kettle;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int facing = state.getValue(FACING).getHorizontalIndex();
		return facing;
	}

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
}
