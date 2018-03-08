package roito.teastory.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import roito.teastory.item.ItemLoader;

public class SeedDrops
{
	public SeedDrops()
	{
		MinecraftForge.addGrassSeed(new ItemStack(ItemLoader.rice_seeds), 5);
	}
}
