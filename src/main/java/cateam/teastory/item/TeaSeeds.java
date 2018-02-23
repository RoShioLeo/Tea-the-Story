package cateam.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class TeaSeeds extends ItemSeeds
{
	public TeaSeeds()
	{
		super(BlockLoader.teaplant, Blocks.FARMLAND);
		this.setUnlocalizedName("tea_seeds");
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE +(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.tea_seeds")));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}
}
