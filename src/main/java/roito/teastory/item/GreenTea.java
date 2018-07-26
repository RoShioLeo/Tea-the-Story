package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockLoader;
import roito.teastory.common.AchievementLoader;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionLoader;

public class GreenTea extends ItemTeaDrink
{
	public GreenTea()
	{
		super("green_tea");
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
			ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemLoader.tea_residue, 1, 0));
		}
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionAgility, ConfigMain.greenTeaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.greenTeaDrink_Time / (tier + 1), 0));
		entityplayer.addStat(AchievementLoader.greenDrink);
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.greentea_stone_cup;
		case 3:
			return BlockLoader.greentea_glass_cup;
		case 4:
			return BlockLoader.greentea_porcelain_cup;
		case 5:
			return BlockLoader.greentea_zisha_cup;
		default:
			return BlockLoader.greentea_wood_cup;
		}
	}
}