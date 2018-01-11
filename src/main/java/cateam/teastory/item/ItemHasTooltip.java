package cateam.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class ItemHasTooltip extends TSItem
{
	private String name;
	public ItemHasTooltip(String name, int maxstack)
	{
		super(name, maxstack);
		this.name = name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE +(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip." + name)));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}
}
