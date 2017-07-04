package cateam.teastory.potion;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class PotionLoader
{
    public static Potion PotionAgility;
    public static Potion PotionLifeDrain;
    public static Potion PotionPhotosynthesis;

    public PotionLoader(FMLPreInitializationEvent event)
    {
    	PotionAgility = new PotionAgility();
    	PotionLifeDrain = new PotionLifeDrain();
    	PotionPhotosynthesis = new PotionPhotosynthesis();
    }
}
