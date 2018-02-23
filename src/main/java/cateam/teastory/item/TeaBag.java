package cateam.teastory.item;

import java.util.List;

import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TeaBag extends TSItem
{
	public TeaBag()
	{
		super("tea_bag", 64, CreativeTabsLoader.tabTeaStory);
		this.setHasSubtypes(true);
		this.setContainerItem(ItemLoader.tea_residue);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		ItemStack stack = new ItemStack(ItemLoader.tea_residue, 1, meta);
		return stack;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + (stack.getItemDamage() == 0 ? "green_tea" : "black_tea");
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 1));
	}
}
