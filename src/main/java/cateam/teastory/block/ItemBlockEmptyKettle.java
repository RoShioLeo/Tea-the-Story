package cateam.teastory.block;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBlockEmptyKettle extends ItemMultiTexture
{
	public ItemBlockEmptyKettle(Block block, Block block2, Function<ItemStack, String> nameFunction)
    {
		super(block, block, nameFunction);
		this.setMaxStackSize(1);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + (String)this.nameFunction.apply(stack);
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);
		BlockPos pos = movingobjectposition.getBlockPos();
		IBlockState iblockstate = worldIn.getBlockState(pos);
        Material material = iblockstate.getBlock().getMaterial();
        if (material == Material.water && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0)
        {
        	return new ItemStack(BlockLoader.empty_kettle, 1, 4);
        }
        else return itemStackIn;
	}
}
