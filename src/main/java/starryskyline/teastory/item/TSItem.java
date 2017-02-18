package starryskyline.teastory.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import starryskyline.teastory.creativetab.CreativeTabsLoader;

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
