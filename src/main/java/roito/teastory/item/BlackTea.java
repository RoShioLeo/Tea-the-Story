package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockLoader;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionLoader;

public class BlackTea extends ItemTeaDrink
{
	public BlackTea()
	{
		super("black_tea");
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
		entityplayer.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, ConfigMain.blackTeaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.blackTeaDrink_Time / (tier + 1), 0));
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.blacktea_stone_cup;
		case 3:
			return BlockLoader.blacktea_glass_cup;
		case 4:
			return BlockLoader.blacktea_porcelain_cup;
		case 5:
			return BlockLoader.blacktea_zisha_cup;
		default:
			return BlockLoader.blacktea_wood_cup;
		}
	}
}
