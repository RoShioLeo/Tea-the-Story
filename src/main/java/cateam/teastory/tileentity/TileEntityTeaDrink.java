package cateam.teastory.tileentity;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.item.BlackTea;
import cateam.teastory.item.BurntGreenTea;
import cateam.teastory.item.GreenTea;
import cateam.teastory.item.MatchaDrink;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class TileEntityTeaDrink extends TileEntity
{
	protected int drunk = 0;
	
	public int getDrink() {
		return this.drunk;
	}

	public void setDrink(int drink) {
		this.drunk = drink;
	}
	
	public boolean bite(EntityPlayer player, World world, int drinkIn, int meta)
	{
		drunk++;
		world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		if (drunk < 0)
		{
			drunk = 0;
		}
		else if (drunk > 6)
		{
			drunk = 0;
			switch (drinkIn)
	        {
	            case 1:
	            	GreenTea.addPotion(meta, world, player);
	            	break;
		        case 2:
		        	MatchaDrink.addPotion(meta, world, player);
		        	break;
		        case 3:
		        	BlackTea.addPotion(meta, world, player);
		        	break;
		        case 4:
		        	player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 0));
		        	break;
		        default:
		        	BurntGreenTea.addPotion(meta, world, player);
	        }
			switch (meta)
	        {
	            case 1:
	            	world.setBlockState(pos, BlockLoader.stone_cup.getDefaultState());
	            	break;
		        case 2:
		        	world.setBlockState(pos, BlockLoader.glass_cup.getDefaultState());
		        	break;
		        case 3:
		        	world.setBlockState(pos, BlockLoader.porcelain_cup.getDefaultState());
		        	break;
		        default:
		        	world.setBlockState(pos, BlockLoader.wood_cup.getDefaultState());
	        }
			world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		    return true;
		}
		return false;
	}
}
