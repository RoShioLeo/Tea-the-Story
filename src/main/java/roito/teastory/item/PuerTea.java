package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockLoader;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionLoader;

public class PuerTea extends ItemTeaDrink
{
	public PuerTea()
	{
		super("puer_tea");
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
		ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemLoader.tea_residue, 1, 5));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionLifeDrain, ConfigMain.puerTeaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.puerTeaDrink_Time / (tier + 1), 0));
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.puertea_stone_cup;
		case 3:
			return BlockLoader.puertea_glass_cup;
		case 4:
			return BlockLoader.puertea_porcelain_cup;
		case 5:
			return BlockLoader.puertea_zisha_cup;
		default:
			return BlockLoader.puertea_wood_cup;
		}
	}
}
