package roito.teastory.item;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

public class ItemBlockName extends ItemMultiTexture
{

	public ItemBlockName(Block block, Block block2, Function<ItemStack, String> nameFunction)
	{
		super(block, block2, nameFunction);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + this.nameFunction.apply(stack);
	}
}
