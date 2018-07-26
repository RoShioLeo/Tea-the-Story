package roito.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.config.ConfigMain;

public class TeaResidue extends TSItem
{
	public TeaResidue()
	{
		super("tea_residue", 64, CreativeTabsLoader.tabDrink);
		this.setHasSubtypes(true);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.tea_residue"));
		}
		else
			tooltip.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
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
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if (this.isInCreativeTab(tab))
		{
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote && ConfigMain.useTeaResidueAsBoneMeal && ItemDye.applyBonemeal(playerIn.getHeldItem(hand), worldIn, pos))
		{
			worldIn.playEvent(2005, pos, 0);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
