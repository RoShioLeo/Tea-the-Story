package cateam.teastory.item;

import java.util.List;

import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ColdWater extends TSItem
{
	public ColdWater()
	{
		super("cold_water", 1, CreativeTabsLoader.tabteastory);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name;
		switch(stack.getItemDamage())
		{
		case 1:
			name = "stone";
			break;
		case 2:
			name = "glass";
			break;
		case 3:
			name = "porcelain";
			break;
		default:
			name = "wood";
		}
		return super.getUnlocalizedName() + "." + name;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 1));
		subItems.add(new ItemStack(itemIn, 1, 2));
		subItems.add(new ItemStack(itemIn, 1, 3));
	}
}
