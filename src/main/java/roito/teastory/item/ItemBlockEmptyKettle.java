package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockEmptyKettle extends ItemBlock
{
	public ItemBlockEmptyKettle(Block block)
	{
		super(block);
		this.setMaxStackSize(1);
		this.setRegistryName(block.getRegistryName());
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.kettle.tips"));
		}
		else
			tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
	}
}
