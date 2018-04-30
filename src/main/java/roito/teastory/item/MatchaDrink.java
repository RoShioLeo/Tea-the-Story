package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import roito.teastory.block.BlockLoader;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionLoader;

public class MatchaDrink extends ItemTeaDrink
{
	public MatchaDrink()
	{
		super("matcha_drink");
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(!world.isRemote)
		{
			int tier = itemstack.getItemDamage();
			addPotion(tier, world, entityplayer);
		}
	}

	public static void addPotion(int tier, World world, EntityPlayer entityplayer)
	{
		entityplayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, ConfigMain.matchaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.matchaDrink_Time / (tier + 1), 0));
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.matchadrink_stone_cup;
		case 3:
			return BlockLoader.matchadrink_glass_cup;
		case 4:
			return BlockLoader.matchadrink_porcelain_cup;
		case 5:
			return BlockLoader.matchadrink_zisha_cup;
		default:
			return BlockLoader.matchadrink_wood_cup;
		}
	}
}
