package cateam.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

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
		super("tea_residue", 64);
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
		return super.getUnlocalizedName() + "." + (stack.getItemDamage() == 0 ? "green" : "black");
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 1));
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (ItemDye.applyBonemeal(stack, worldIn, pos, playerIn))
		{
			if (!worldIn.isRemote)
			{
				worldIn.playEvent(2005, pos, 0);
			}
			return EnumActionResult.SUCCESS;
		}
		else return EnumActionResult.FAIL;
	}
}
