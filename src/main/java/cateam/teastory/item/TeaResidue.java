package cateam.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cateam.teastory.common.AchievementLoader;
import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class TeaResidue extends TSItem
{
	public TeaResidue()
	{
		super("tea_residue", 64, CreativeTabsLoader.tabDrink);
		this.setHasSubtypes(true);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE +(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.tea_residue")));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		switch (stack.getItemDamage())
		{
		case 1:
			return super.getUnlocalizedName() + "." + "black";
		case 2:
			return super.getUnlocalizedName() + "." + "yellow";
		case 3:
			return super.getUnlocalizedName() + "." + "white";
		case 4:
			return super.getUnlocalizedName() + "." + "oolong";
		case 5:
			return super.getUnlocalizedName() + "." + "puer";
		default:
			return super.getUnlocalizedName() + "." + "green";
		}
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 1));
		subItems.add(new ItemStack(itemIn, 1, 2));
		subItems.add(new ItemStack(itemIn, 1, 3));
		subItems.add(new ItemStack(itemIn, 1, 4));
		subItems.add(new ItemStack(itemIn, 1, 5));
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (ItemDye.applyBonemeal(stack, worldIn, pos, playerIn))
		{
			if (!worldIn.isRemote)
			{
				playerIn.addStat(AchievementLoader.teaResidue);
				worldIn.playEvent(2005, pos, 0);
			}
			return EnumActionResult.SUCCESS;
		}
		else return EnumActionResult.FAIL;
	}
}
