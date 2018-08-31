package roito.teastory.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import roito.teastory.config.ConfigMain;
import roito.teastory.item.ItemRegister;

public class SeedDrops
{
    public SeedDrops()
    {
        MinecraftForge.addGrassSeed(new ItemStack(ItemRegister.xian_rice_seeds), ConfigMain.general.riceSeedsDropWeight);
    }
}
