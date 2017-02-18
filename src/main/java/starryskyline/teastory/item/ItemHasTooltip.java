package starryskyline.teastory.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemHasTooltip extends TSItem
{
	private String name;
	public ItemHasTooltip(String name, int maxstack)
	{
		super(name, maxstack);
		this.name = name;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(I18n.translateToLocal("teastory.tooltip." + name));
    }
}
