package cateam.teastory.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemHasTooltip extends TSItem
{
	private String name;
	public ItemHasTooltip(String name, int maxstack)
	{
		super(name, maxstack);
		this.name = name;
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(StatCollector.translateToLocal("teastory.tooltip." + name));
    }
}
