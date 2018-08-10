package roito.teastory.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.common.CreativeTabsRegister;

public class ItemTeaLeaf extends TSItem
{
	public ItemTeaLeaf(String name, int maxstack)
	{
		super(name, maxstack, CreativeTabsRegister.tabTeaStory);
	}
}
