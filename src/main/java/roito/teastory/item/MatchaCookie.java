package roito.teastory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

public class MatchaCookie extends ItemFood
{
	public MatchaCookie()
	{
		super(1, false);
		this.setCreativeTab(CreativeTabsRegister.tabTeaStory);
		this.setTranslationKey("matcha_cookie");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "matcha_cookie"));
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{

	}
}
