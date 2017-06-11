package cateam.teastory.item;

import java.util.List;

import cateam.teastory.block.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemCup extends TSItem
{
	public ItemCup()
	{
    	super("cup", 64);
    	this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		String name;
		switch(stack.getItemDamage())
		{
		    case 1:
		    	name = "stone";
		    	break;
		    case 2:
		    	name = "glass";
		    	break;
		    case 3:
		    	name = "porcelain";
		    	break;
		    default:
		    	name = "wood";
		}
	    return super.getUnlocalizedName() + "." + name;
	}
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
	    subItems.add(new ItemStack(itemIn, 1, 0));
	    subItems.add(new ItemStack(itemIn, 1, 1));
	    subItems.add(new ItemStack(itemIn, 1, 2));
	    subItems.add(new ItemStack(itemIn, 1, 3));
	}
	
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		    case 1:
		    	return BlockLoader.stone_cup;
		    case 2:
		    	return BlockLoader.glass_cup;
		    case 3:
		    	return BlockLoader.porcelain_cup;
		    default:
		    	return BlockLoader.wood_cup;
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote)
		{
			return true;
		}
		else if (playerIn.isSneaking())
		{
			IBlockState s = worldIn.getBlockState(pos);
			Block block = getBlock(stack.getItemDamage());
			BlockPos blockPos = pos.offset(side);
			if(s.getBlock().isReplaceable(worldIn, pos))
				blockPos = pos;
			if(!playerIn.canPlayerEdit(pos, side, null) || !block.canPlaceBlockAt(worldIn, blockPos) || !block.canPlaceBlockOnSide(worldIn, blockPos, side))
			    return false;
			IBlockState state = block.onBlockPlaced(worldIn, blockPos, side, 0, 0, 0, 0, playerIn);
			worldIn.setBlockState(blockPos, state);
			block.onBlockPlacedBy(worldIn, blockPos, state, playerIn, stack);			
			if (!playerIn.capabilities.isCreativeMode)
				stack.stackSize--;
			return true;
		}
		else
		{
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

	        if (movingobjectposition == null)
	        {
	            return false;
	        }
	        else
	        {
	            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
	            {
	                BlockPos blockpos = movingobjectposition.getBlockPos();

	                if (!worldIn.isBlockModifiable(playerIn, blockpos))
	                {
	                    return false;
	                }

	                if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, stack))
	                {
	                    return false;
	                }

	                if (worldIn.getBlockState(blockpos).getBlock().getMaterial() == Material.water)
	                {
	                    --stack.stackSize;

	                    if (stack.stackSize <= 0)
	                    {
	                        stack = new ItemStack(ItemLoader.cold_water, 1, stack.getItemDamage());
	                    }

	                    if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ItemLoader.cold_water, 1, stack.getItemDamage())))
	                    {
	                        playerIn.dropPlayerItemWithRandomChoice(new ItemStack(ItemLoader.cold_water, 1, stack.getItemDamage()), false);
	                    }
	                    if (playerIn instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                        }
	                    return true;
	                }
	            }

	            return false;
	        }
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

        if (movingobjectposition == null)
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
                    --itemStackIn.stackSize;

                    if (itemStackIn.stackSize <= 0)
                    {
                        return new ItemStack(ItemLoader.cold_water, 1, itemStackIn.getItemDamage());
                    }

                    if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ItemLoader.cold_water, 1, itemStackIn.getItemDamage())))
                    {
                        playerIn.dropPlayerItemWithRandomChoice(new ItemStack(ItemLoader.cold_water, 1, itemStackIn.getItemDamage()), false);
                    }
                    else if (playerIn instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                    }
                }
            }

            return itemStackIn;
        }
    }
}
