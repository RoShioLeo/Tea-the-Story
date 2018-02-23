package cateam.teastory.item;

import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.item.ItemFood;

public class MatchaCookie extends ItemFood
{
	public MatchaCookie()
	{
		super(1, false);
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
		this.setUnlocalizedName("matcha_cookie");
	}
}
