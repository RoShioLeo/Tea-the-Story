package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockHalfDriedLeaf extends ItemBlock
{
	public ItemBlockHalfDriedLeaf(Block block)
	{
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
        return stack.getMetadata() == 8 ? "tile.half_dried_leaf_block.puer" : "tile.half_dried_leaf_block.leaf";
    }
}
