package starryskyline.teastory.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import starryskyline.teastory.creativetab.CreativeTabsLoader;

public class DirtySieve extends TSItem
{
	public DirtySieve()
    {
 	    super("dirty_sieve", 64);
 	    this.setContainerItem(ItemLoader.broken_tea);
 	    this.setMaxDamage(64);
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		int i = itemStackIn.stackSize;
		ItemStack stack = new ItemStack(ItemLoader.sieve, i);
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
}
