package roito.teastory.common;

import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import roito.teastory.block.BlockLoader;
import roito.teastory.item.ItemLoader;

public class SmeltingLoader
{
	public SmeltingLoader()
	{
		registerSmelting();
	}

	private static void registerSmelting()
	{
		GameRegistry.addSmelting(ItemLoader.wet_tea, new ItemStack(ItemLoader.tea_leaf), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockLoader.clay_kettle), new ItemStack(BlockLoader.empty_porcelain_kettle), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockLoader.zisha_clay_kettle), new ItemStack(BlockLoader.empty_zisha_kettle), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.clay_cup), new ItemStack(ItemLoader.cup, 1, 4), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.zisha_clay_cup), new ItemStack(ItemLoader.cup, 1, 5), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.pot_clay), new ItemStack(ItemLoader.pot_porcelain), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.pot_zisha_clay), new ItemStack(ItemLoader.pot_zisha), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_stone), new ItemStack(ItemLoader.hw_pot_stone), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_iron), new ItemStack(ItemLoader.hw_pot_iron), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_porcelain), new ItemStack(ItemLoader.hw_pot_porcelain), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemLoader.cw_pot_zisha), new ItemStack(ItemLoader.hw_pot_zisha), 0.1f);
	}
}