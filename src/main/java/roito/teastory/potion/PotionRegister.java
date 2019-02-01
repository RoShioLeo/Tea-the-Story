package roito.teastory.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionRegister
{
	public static final RegistryNamespaced<ResourceLocation, Potion> REGISTRY = net.minecraftforge.registries.GameData.getWrapper(Potion.class);
	public static Potion PotionAgility;
	public static Potion PotionLifeDrain;
	public static Potion PotionPhotosynthesis;
	public static Potion PotionDefence;
	public static Potion PotionExcitement;

	public PotionRegister()
	{
		PotionAgility = new PotionAgility();
		PotionLifeDrain = new PotionLifeDrain();
		PotionPhotosynthesis = new PotionPhotosynthesis();
		PotionDefence = new PotionDefence();
		PotionExcitement = new PotionExcitement();

		register(PotionAgility);
		register(PotionLifeDrain);
		register(PotionPhotosynthesis);
		register(PotionDefence);
		register(PotionExcitement);
	}

	public static void register(Potion potion)
	{
		ForgeRegistries.POTIONS.register(potion);
	}
}
