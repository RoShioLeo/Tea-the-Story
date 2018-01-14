package cateam.teastory.item;

import java.util.List;

import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TSItem extends Item
{
	public TSItem(String name, int maxstack)
    {
 	    super();
        this.setUnlocalizedName(name);
        this.setMaxStackSize(maxstack);
        this.setCreativeTab(CreativeTabsLoader.tabteastory);
    }
}
