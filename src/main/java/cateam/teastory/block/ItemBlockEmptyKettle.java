package cateam.teastory.block;

import com.google.common.base.Function;

import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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

        if (itemStackIn.getItemDamage() != 0 || movingobjectposition == null)
        {
            return itemStackIn;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos blockpos = movingobjectposition.getBlockPos();

                if (!worldIn.isBlockModifiable(playerIn, blockpos))
                {
                    return itemStackIn;
                }

                if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStackIn))
                {
                    return itemStackIn;
                }

                if (worldIn.getBlockState(blockpos).getBlock().getMaterial() == Material.water)
                {
                	itemStackIn.setItemDamage(4);
                	if (playerIn instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                    }
                    return itemStackIn;
                }
            }

            return itemStackIn;
        }
    }
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	Block block = worldIn.getBlockState(pos).getBlock();
    	int meta = stack.getItemDamage();
    	if(meta == 0)
    	{
    		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

            if (movingobjectposition == null)
            {
            	super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
            	return false;
            }
            else
            {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    BlockPos blockpos = movingobjectposition.getBlockPos();

                    if (!worldIn.isBlockModifiable(playerIn, blockpos))
                    {
                    	super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
                    	return false;
                    }
                    if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, stack))
                    {
                    	super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
                    	return false;
                    }
                    if (worldIn.getBlockState(blockpos).getBlock().getMaterial() == Material.water)
                    {
                    	stack.setItemDamage(4);
                    	if (playerIn instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                        }
                    	return true;
                    }
                }
            }
    	}
    	else if (meta == 12)
    	{
    	    if (block == BlockLoader.wood_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_wood_cup.getStateFromMeta(1));
    	    	return true;
    	    }
    	    else if(block == BlockLoader.stone_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_stone_cup.getStateFromMeta(1));
    	    	return true;
    	    }
    	    else if(block == BlockLoader.glass_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_glass_cup.getStateFromMeta(1));
    	    	return true;
    	    }
    	    else if(block == BlockLoader.porcelain_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_porcelain_cup.getStateFromMeta(1));
    	    	return true;
    	    }
    	}
		super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
    	return false;
    }
}
