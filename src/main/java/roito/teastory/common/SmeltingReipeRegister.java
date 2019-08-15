package roito.teastory.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import roito.teastory.block.BlockRegister;
import roito.teastory.item.ItemRegister;

public class SmeltingReipeRegister
{
    public SmeltingReipeRegister()
    {
        registerSmelting();
    }

    private static void registerSmelting()
    {
        GameRegistry.addSmelting(ItemRegister.wet_tea, new ItemStack(ItemRegister.tea_leaf), 0.1f);
        GameRegistry.addSmelting(new ItemStack(BlockRegister.clay_kettle), new ItemStack(BlockRegister.empty_porcelain_kettle), 0.1f);
        GameRegistry.addSmelting(new ItemStack(BlockRegister.zisha_clay_kettle), new ItemStack(BlockRegister.empty_zisha_kettle), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.clay_cup), new ItemStack(ItemRegister.cup, 1, 4), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.zisha_clay_cup), new ItemStack(ItemRegister.cup, 1, 5), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.pot_clay), new ItemStack(ItemRegister.pot_porcelain), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.pot_zisha_clay), new ItemStack(ItemRegister.pot_zisha), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.cw_pot_stone), new ItemStack(ItemRegister.hw_pot_stone), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.cw_pot_iron), new ItemStack(ItemRegister.hw_pot_iron), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.cw_pot_porcelain), new ItemStack(ItemRegister.hw_pot_porcelain), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegister.cw_pot_zisha), new ItemStack(ItemRegister.hw_pot_zisha), 0.1f);
    }
}