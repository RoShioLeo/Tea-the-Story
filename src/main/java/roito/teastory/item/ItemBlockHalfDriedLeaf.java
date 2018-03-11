package roito.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

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
