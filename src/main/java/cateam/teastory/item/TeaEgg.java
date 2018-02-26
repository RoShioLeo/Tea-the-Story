package cateam.teastory.item;

import cateam.teastory.common.AchievementLoader;
import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TeaEgg extends ItemFood
{
	public TeaEgg()
	{
		super(5, false);
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
		this.setUnlocalizedName("tea_egg");
	}
	
	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		entityplayer.addStat(AchievementLoader.teaEgg);
	}
}
