package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

public class HalfDriedLeafBlock extends Block
{

	public HalfDriedLeafBlock()
	{
		super(Material.GRASS);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.PLANT);
		this.setTranslationKey("half_dried_leaf_block");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "half_dried_leaf_block"));
		this.setCreativeTab(CreativeTabsRegister.tabTeaStory);
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
}
