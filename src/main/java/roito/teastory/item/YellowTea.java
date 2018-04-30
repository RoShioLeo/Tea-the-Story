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

public class YellowTea extends ItemTeaDrink
{
	public YellowTea()
	{
		super("yellow_tea");
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
		ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemLoader.tea_residue, 1, 2));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionDefence, ConfigMain.yellowTeaDrink_Time, 0));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.yellowTeaDrink_Time, 0));
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.yellowtea_stone_cup;
		case 3:
			return BlockLoader.yellowtea_glass_cup;
		case 4:
			return BlockLoader.yellowtea_porcelain_cup;
		case 5:
			return BlockLoader.yellowtea_zisha_cup;
		default:
			return BlockLoader.yellowtea_wood_cup;
		}
	}
}