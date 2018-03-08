package roito.teastory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import roito.teastory.common.AchievementLoader;
import roito.teastory.common.CreativeTabsLoader;

public class MatchaCookie extends ItemFood
{
	public MatchaCookie()
	{
		super(1, false);
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
		this.setUnlocalizedName("matcha_cookie");
	}
	
	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		
	}
}
