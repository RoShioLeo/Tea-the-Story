package cateam.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TeaDrink extends Block
{
	public int meta;

	public TeaDrink(float hardness, String name, Material materialIn, SoundType soundType, int level)
	{
		super(materialIn);
		this.setBlockBounds(0.3125F, 0F, 0.3125F, 0.6875F, 0.3125F, 0.6875F);
        this.setHardness(hardness);
        this.setStepSound(soundType);
        this.setUnlocalizedName(name);
        this.meta = level;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	@Override
	public boolean isFullCube()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
}
