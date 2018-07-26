package roito.teastory.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

public class MilkTea extends ItemTeaDrink
{
	public MilkTea()
	{
		super("milk_tea");
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(!world.isRemote)
		{
			int tier = itemstack.getItemDamage() / 2;
			addPotion(tier, world, entityplayer);
		}
	}

	public static void addPotion(int tier, World world, EntityPlayer entityplayer)
	{
		if (ConfigMain.useTeaResidueAsBoneMeal)
		{
			ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemLoader.tea_residue, 1, 1));
		}
		entityplayer.addStat(AchievementLoader.milkDrink);
		Collection<PotionEffect> effectList = entityplayer.getActivePotionEffects();
		List<PotionEffect> list = new ArrayList<>();
		for (PotionEffect effect : effectList)
		{
			if (effect.getPotion().isBadEffect())
			{
				list.add(effect);
			}
		}
		for (PotionEffect effect : list)
		{
			entityplayer.removePotionEffect(effect.getPotion());
			entityplayer.addStat(AchievementLoader.cure);
		}
        
		entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, ConfigMain.milkTeaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.milkTeaDrink_Time / (tier + 1), 0));
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.milktea_stone_cup;
		case 3:
			return BlockLoader.milktea_glass_cup;
		case 4:
			return BlockLoader.milktea_porcelain_cup;
		case 5:
			return BlockLoader.milktea_zisha_cup;
		default:
			return BlockLoader.milktea_wood_cup;
		}
	}
}
