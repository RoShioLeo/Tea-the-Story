package roito.teastory.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHasTooltip extends TSItem
{
	private String name;

	public ItemHasTooltip(String name, int maxStack, CreativeTabs creativeTab)
	{
		super(name, maxStack, creativeTab);
		this.name = name;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip." + name));
		}
		else
		{
			tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
		}
	}
}
