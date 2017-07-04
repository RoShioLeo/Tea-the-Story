package cateam.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TeaDrink extends Block
{
	public int meta;
	protected static final AxisAlignedBB TEADRINK_AABB = new AxisAlignedBB(0.3125F, 0F, 0.3125F, 0.6875F, 0.3125F, 0.6875F);

	public TeaDrink(float hardness, String name, Material materialIn, SoundType soundType, int level)
	{
		super(materialIn);
        this.setHardness(hardness);
        this.setSoundType(soundType);
        this.setUnlocalizedName(name);
        this.meta = level;
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return TEADRINK_AABB;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}
