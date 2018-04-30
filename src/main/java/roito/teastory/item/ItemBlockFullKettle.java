package roito.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import roito.teastory.block.BlockLoader;
import roito.teastory.block.FullKettle;

public class ItemBlockFullKettle extends ItemBlock
{
	int drink;

	public ItemBlockFullKettle(Block block, int drink)
	{
		super(block);
		this.setMaxDamage(16);
		this.setMaxStackSize(1);
		this.drink = drink;
		this.setNoRepair();
		this.setRegistryName(block.getRegistryName());
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
        return false;
    }

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		FullKettle kettle = (FullKettle) Block.getBlockFromItem(stack.getItem());
		if (!kettle.full && kettle.getNextKettle() == BlockLoader.empty_zisha_kettle)
		{
			tooltip.add(TextFormatting.WHITE + I18n.translateToLocalFormatted("teastory.tooltip.kettle.remain", 4 - stack.getItemDamage() / 4, kettle.getMaxCapacity()));
		}
		else
		{
			tooltip.add(TextFormatting.WHITE + I18n.translateToLocalFormatted("teastory.tooltip.kettle.remain", kettle.getMaxCapacity() - stack.getItemDamage() / 4, kettle.getMaxCapacity()));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.kettle.tips"));
		}
		else
			tooltip.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}

	public void pourTeaDrink(EntityPlayer player, ItemStack stack, int meta)
	{
		if ((meta >> 2) == 3)
		{
			player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(((FullKettle) Block.getBlockFromItem(stack.getItem())).getNextKettle()));
			stack.setItemDamage(0);
		}
		else
		{
			stack.setItemDamage(meta + 4);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		Block block = worldIn.getBlockState(pos).getBlock();
		int meta = player.getHeldItem(hand).getItemDamage();
		if (block == BlockLoader.wood_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockLoader.greentea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockLoader.matchadrink_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockLoader.blacktea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockLoader.milktea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockLoader.lemontea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockLoader.yellowtea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockLoader.whitetea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockLoader.oolongtea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockLoader.puertea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockLoader.stone_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockLoader.greentea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockLoader.matchadrink_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockLoader.blacktea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockLoader.milktea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockLoader.lemontea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockLoader.yellowtea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockLoader.whitetea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockLoader.oolongtea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockLoader.puertea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockLoader.glass_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockLoader.greentea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockLoader.matchadrink_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockLoader.blacktea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockLoader.milktea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockLoader.lemontea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockLoader.yellowtea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockLoader.whitetea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockLoader.oolongtea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockLoader.puertea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockLoader.porcelain_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockLoader.greentea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockLoader.matchadrink_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockLoader.blacktea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockLoader.milktea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockLoader.lemontea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockLoader.yellowtea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockLoader.whitetea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockLoader.oolongtea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockLoader.puertea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockLoader.zisha_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockLoader.greentea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockLoader.matchadrink_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockLoader.blacktea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockLoader.milktea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockLoader.lemontea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockLoader.yellowtea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockLoader.whitetea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockLoader.oolongtea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockLoader.puertea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitZ, hitZ, hitZ);
	}
}
