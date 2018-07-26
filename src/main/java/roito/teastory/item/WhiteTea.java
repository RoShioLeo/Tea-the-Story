package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockLoader;
import roito.teastory.common.AchievementLoader;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionLoader;

public class WhiteTea extends ItemTeaDrink
{
	public WhiteTea()
	{
		super("white_tea");
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
		if (ConfigMain.useTeaResidueAsBoneMeal)
		{
			ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemLoader.tea_residue, 1, 3));
		}
		entityplayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, ConfigMain.whiteTeaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.whiteTeaDrink_Time / (tier + 1), 0));
		entityplayer.addStat(AchievementLoader.whiteDrink);
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.whitetea_stone_cup;
		case 3:
			return BlockLoader.whitetea_glass_cup;
		case 4:
			return BlockLoader.whitetea_porcelain_cup;
		case 5:
			return BlockLoader.whitetea_zisha_cup;
		default:
			return BlockLoader.whitetea_wood_cup;
		}
	}
}
