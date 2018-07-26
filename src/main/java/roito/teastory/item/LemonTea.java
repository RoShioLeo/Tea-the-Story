package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockLoader;
import roito.teastory.common.AchievementLoader;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionLoader;

public class LemonTea extends ItemTeaDrink
{
	public LemonTea()
	{
		super("lemon_tea");
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
		entityplayer.addStat(AchievementLoader.lemonDrink);
		if (entityplayer.isBurning())
		{
			entityplayer.extinguish();
			entityplayer.addStat(AchievementLoader.outfire);
		}
		entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, ConfigMain.lemonTeaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.lemonTeaDrink_Time / (tier + 1), 0));
		for (int x= -1 - tier; x<=1 + tier; x++)
		{
			for (int y= 0; y<=2 + 2 * tier; y++)
			{
				for (int z= -1 - tier; z<=1 + tier; z++)
				{
					if (entityplayer.canPlayerEdit(entityplayer.getPosition().add(x, y, z).down(), EnumFacing.UP, null))
					{
						if (world.getBlockState(entityplayer.getPosition().add(x, y, z)).getBlock() instanceof BlockFire)
						{
							world.setBlockToAir(entityplayer.getPosition().add(x, y, z));
							world.playSound(null, entityplayer.getPosition().add(x, y, z), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
							entityplayer.addStat(AchievementLoader.outfire);
						}
					}
				}
			}
		}
	}

	@Override
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.lemontea_stone_cup;
		case 3:
			return BlockLoader.lemontea_glass_cup;
		case 4:
			return BlockLoader.lemontea_porcelain_cup;
		case 5:
			return BlockLoader.lemontea_zisha_cup;
		default:
			return BlockLoader.lemontea_wood_cup;
		}
	}
}
