package cateam.teastory.common;

import cateam.teastory.item.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class SeedDrops
{
	public static void init()
    {
		MinecraftForge.addGrassSeed(new ItemStack(ItemLoader.rice_seeds), 5);
    }
}
