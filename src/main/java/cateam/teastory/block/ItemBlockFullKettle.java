package cateam.teastory.block;

import java.util.List;

import com.google.common.base.Function;

import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemBlockFullKettle extends ItemMultiTexture
{
	int drink;
	
	public ItemBlockFullKettle(Block block, Block block2, Function<ItemStack, String> nameFunction, int drink)
    {
		super(block, block, nameFunction);
		this.setMaxStackSize(1);
		this.setMaxDamage(16);
		this.drink = drink;
		this.setNoRepair();
	}

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + (String)this.nameFunction.apply(stack);
    }
    
    @Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(I18n.translateToLocal("teastory.tooltip.kettle"));
    }
    
    public void pourTeaDrink(ItemStack stack, int meta)
    {
    	if ((meta >> 2) == 3)
		{
			stack.setItem(new ItemStack(BlockLoader.empty_kettle).getItem());
			stack.setItemDamage(0);
		}
		else
		{
			stack.setItemDamage(meta + 4);
		}
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	Block block = worldIn.getBlockState(pos).getBlock();
    	int meta = stack.getItemDamage();
    	if (block == BlockLoader.wood_cup)
    	{
    		switch(drink)
    		{
    		    case 1:
    		    	worldIn.setBlockState(pos, BlockLoader.greentea_wood_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 2:
    		    	worldIn.setBlockState(pos, BlockLoader.matchadrink_wood_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 3:
    		    	worldIn.setBlockState(pos, BlockLoader.blacktea_wood_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    default:
    		    	worldIn.setBlockState(pos, BlockLoader.burntgreentea_wood_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		}
    	}
    	else if(block == BlockLoader.stone_cup)
    	{
    		switch(drink)
    		{
    		    case 1:
    		    	worldIn.setBlockState(pos, BlockLoader.greentea_stone_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 2:
    		    	worldIn.setBlockState(pos, BlockLoader.matchadrink_stone_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 3:
    		    	worldIn.setBlockState(pos, BlockLoader.blacktea_stone_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    default:
    		    	worldIn.setBlockState(pos, BlockLoader.burntgreentea_stone_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		}
    	}
    	else if(block == BlockLoader.glass_cup)
    	{
    		switch(drink)
    		{
    		    case 1:
    		    	worldIn.setBlockState(pos, BlockLoader.greentea_glass_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 2:
    		    	worldIn.setBlockState(pos, BlockLoader.matchadrink_glass_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 3:
    		    	worldIn.setBlockState(pos, BlockLoader.blacktea_glass_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    default:
    		    	worldIn.setBlockState(pos, BlockLoader.burntgreentea_glass_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		}
    	}
    	else if(block == BlockLoader.porcelain_cup)
    	{
    		switch(drink)
    		{
    		    case 1:
    		    	worldIn.setBlockState(pos, BlockLoader.greentea_porcelain_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 2:
    		    	worldIn.setBlockState(pos, BlockLoader.matchadrink_porcelain_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    case 3:
    		    	worldIn.setBlockState(pos, BlockLoader.blacktea_porcelain_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		    default:
    		    	worldIn.setBlockState(pos, BlockLoader.burntgreentea_porcelain_cup.getStateFromMeta(1));
    		    	pourTeaDrink(stack, meta);
    		    	return EnumActionResult.SUCCESS;
    		}
    	}
    	else
    	{
    		super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitZ, hitZ, hitZ);
    	}
    	return EnumActionResult.PASS;
    }
}
