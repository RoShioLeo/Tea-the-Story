package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import roito.teastory.block.BlockRegister;
import roito.teastory.block.FullKettle;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockFullKettle extends ItemBlock
{
	private int drink;

	public ItemBlockFullKettle(Block block, int drink, int damage)
	{
		super(block);
		this.setMaxDamage(damage);
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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		FullKettle kettle = (FullKettle) Block.getBlockFromItem(stack.getItem());
        tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.kettle.remain", kettle.getMaxCapacity() - stack.getItemDamage(), kettle.getMaxCapacity()));
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
            tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.kettle.tips"));
		}
		else
            tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
	}

	public void pourTeaDrink(EntityPlayer player, ItemStack stack, int meta)
	{
		if (meta >= ((FullKettle) Block.getBlockFromItem(stack.getItem())).getMaxCapacity() - 1)
		{
			player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(((FullKettle) Block.getBlockFromItem(stack.getItem())).getEmptyKettle()));
			stack.setItemDamage(0);
		}
		else
		{
			stack.setItemDamage(meta + 1);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		Block block = worldIn.getBlockState(pos).getBlock();
		int meta = player.getHeldItem(hand).getItemDamage();
		if (block == BlockRegister.wood_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockRegister.greentea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockRegister.matchadrink_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockRegister.blacktea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockRegister.milktea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockRegister.lemontea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockRegister.yellowtea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockRegister.whitetea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockRegister.oolongtea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockRegister.puertea_wood_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockRegister.stone_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockRegister.greentea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockRegister.matchadrink_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockRegister.blacktea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockRegister.milktea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockRegister.lemontea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockRegister.yellowtea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockRegister.whitetea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockRegister.oolongtea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockRegister.puertea_stone_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockRegister.glass_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockRegister.greentea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockRegister.matchadrink_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockRegister.blacktea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockRegister.milktea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockRegister.lemontea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockRegister.yellowtea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockRegister.whitetea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockRegister.oolongtea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockRegister.puertea_glass_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockRegister.porcelain_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockRegister.greentea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockRegister.matchadrink_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockRegister.blacktea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockRegister.milktea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockRegister.lemontea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockRegister.yellowtea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockRegister.whitetea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockRegister.oolongtea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockRegister.puertea_porcelain_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		else if(block == BlockRegister.zisha_cup)
		{
			switch(drink)
			{
			case 1:
				worldIn.setBlockState(pos, BlockRegister.greentea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 2:
				worldIn.setBlockState(pos, BlockRegister.matchadrink_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 3:
				worldIn.setBlockState(pos, BlockRegister.blacktea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 4:
				worldIn.setBlockState(pos, BlockRegister.milktea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 5:
				worldIn.setBlockState(pos, BlockRegister.lemontea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 6:
				worldIn.setBlockState(pos, BlockRegister.yellowtea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 7:
				worldIn.setBlockState(pos, BlockRegister.whitetea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 8:
				worldIn.setBlockState(pos, BlockRegister.oolongtea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			case 9:
				worldIn.setBlockState(pos, BlockRegister.puertea_zisha_cup.getDefaultState());
				pourTeaDrink(player, player.getHeldItem(hand), meta);
				return EnumActionResult.SUCCESS;
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitZ, hitZ, hitZ);
	}
}
