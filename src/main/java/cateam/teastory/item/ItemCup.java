package cateam.teastory.item;

import java.util.List;

import cateam.teastory.block.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
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
		else return false;
	}
}
