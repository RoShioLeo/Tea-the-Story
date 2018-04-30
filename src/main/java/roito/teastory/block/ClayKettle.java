package roito.teastory.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import roito.teastory.common.CreativeTabsLoader;

public class ClayKettle extends Kettle
{
	public ClayKettle(String name)
	{
		super(name, Material.CLAY);
		this.setHardness(0.6F);
		this.setSoundType(SoundType.GROUND);
		this.setCreativeTab(CreativeTabsLoader.tabDrink);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
}
