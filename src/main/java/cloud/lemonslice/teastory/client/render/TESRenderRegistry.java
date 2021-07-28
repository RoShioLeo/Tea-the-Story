package cloud.lemonslice.teastory.client.render;

import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class TESRenderRegistry
{
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.STOVE_TILE, StoveTESR::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.BAMBOO_TRAY, BambooTrayTESR::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.DRINK_MAKER, DrinkMakerTESR::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.STONE_MILL, StoneMillTESR::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.STONE_ROLLER, StoneRollerTESR::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.WOODEN_BARREL, WoodenBarrelTESR::new);
    }
}
