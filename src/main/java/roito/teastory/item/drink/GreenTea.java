package roito.teastory.item.drink;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.item.ItemRegister;
import roito.teastory.potion.PotionRegister;

public class GreenTea extends ItemTeaDrink
{
	public GreenTea()
	{
		super("green_tea");
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if (!world.isRemote)
		{
			int tier = itemstack.getItemDamage() / 2;
			addPotion(tier, world, entityplayer);
		}
	}

	public static void addPotion(int tier, World world, EntityPlayer entityplayer)
	{
		if (ConfigMain.general.useTeaResidueAsBoneMeal)
		{
			ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemRegister.tea_residue, 1, 0));
		}
		if (Potion.getPotionFromResourceLocation(ConfigMain.drink.greenTeaDrink_Effect) != null)
		{
			entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(ConfigMain.drink.greenTeaDrink_Effect), ConfigMain.drink.greenTeaDrink_Time / (tier + 1), tier));
		}
		entityplayer.addPotionEffect(new PotionEffect(PotionRegister.PotionExcitement, ConfigMain.drink.greenTeaDrink_Time / (tier + 1), 0));
	}

	@Override
	public Block getBlock(int meta)
	{
		switch (meta)
		{
			case 2:
				return BlockRegister.greentea_stone_cup;
			case 3:
				return BlockRegister.greentea_glass_cup;
			case 4:
				return BlockRegister.greentea_porcelain_cup;
			case 5:
				return BlockRegister.greentea_zisha_cup;
			default:
				return BlockRegister.greentea_wood_cup;
		}
	}
}