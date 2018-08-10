package roito.teastory.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import roito.teastory.block.BlockRegister;
import roito.teastory.item.BlackTea;
import roito.teastory.item.GreenTea;
import roito.teastory.item.LemonTea;
import roito.teastory.item.MatchaDrink;
import roito.teastory.item.MilkTea;
import roito.teastory.item.OolongTea;
import roito.teastory.item.PuerTea;
import roito.teastory.item.WhiteTea;
import roito.teastory.item.YellowTea;

public class TileEntityTeaDrink extends TileEntity
{
	protected int drunk = 0;

	public int getDrink()
	{
		return this.drunk;
	}

	public void setDrink(int drink)
	{
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
				MilkTea.addPotion(meta, world, player);
				break;
			case 5:
				LemonTea.addPotion(meta, world, player);
				break;
			case 6:
				YellowTea.addPotion(meta, world, player);
				break;
			case 7:
				WhiteTea.addPotion(meta, world, player);
				break;
			case 8:
				OolongTea.addPotion(meta, world, player);
				break;
			case 9:
				PuerTea.addPotion(meta, world, player);
				break;
			}
			switch (meta)
			{
			case 0:
				world.setBlockState(pos, BlockRegister.wood_cup.getDefaultState());
				break;
			case 2:
				world.setBlockState(pos, BlockRegister.stone_cup.getDefaultState());
				break;
			case 3:
				world.setBlockState(pos, BlockRegister.glass_cup.getDefaultState());
				break;
			case 4:
				world.setBlockState(pos, BlockRegister.porcelain_cup.getDefaultState());
				break;
			case 5:
				world.setBlockState(pos, BlockRegister.zisha_cup.getDefaultState());
				break;
			}
			world.playSound(player, pos, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		}
		return false;
	}
}
