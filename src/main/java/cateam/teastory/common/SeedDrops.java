package cateam.teastory.common;

import cateam.teastory.item.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class SeedDrops
{
	public SeedDrops()
	{
		MinecraftForge.addGrassSeed(new ItemStack(ItemLoader.rice_seeds), 5);
	}
}
