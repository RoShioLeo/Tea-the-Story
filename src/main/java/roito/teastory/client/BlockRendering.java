package roito.teastory.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import roito.teastory.TeaStory;
import roito.teastory.client.renderer.TESRTeapan;
import roito.teastory.tileentity.TileEntityTeapan;

@Mod.EventBusSubscriber(modid = TeaStory.MODID, value = Side.CLIENT)
public final class BlockRendering
{
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeapan.class, new TESRTeapan());
	}
}
