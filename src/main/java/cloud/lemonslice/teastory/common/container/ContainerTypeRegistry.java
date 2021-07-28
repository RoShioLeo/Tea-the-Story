package cloud.lemonslice.teastory.common.container;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.client.gui.*;
import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;

public final class ContainerTypeRegistry extends RegistryModule
{
    public final static ContainerType<?> STOVE_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new StoveContainer(windowId, inv, data.readBlockPos(), TeaStory.proxy.getClientWorld()))).setRegistryName("stove");
    public final static ContainerType<?> BAMBOO_TRAY_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new BambooTrayContainer(windowId, inv, data.readBlockPos(), TeaStory.proxy.getClientWorld()))).setRegistryName("bamboo_tray");
    public final static ContainerType<?> DRINK_MAKER_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new DrinkMakerContainer(windowId, inv, data.readBlockPos(), TeaStory.proxy.getClientWorld()))).setRegistryName("drink_maker");
    public final static ContainerType<?> STONE_MILL_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new StoneMillContainer(windowId, inv, data.readBlockPos(), TeaStory.proxy.getClientWorld()))).setRegistryName("stone_mill");
    public final static ContainerType<?> STONE_ROLLER_CONTAINER = IForgeContainerType.create(((windowId, inv, data) -> new StoneRollerContainer(windowId, inv, data.readBlockPos(), TeaStory.proxy.getClientWorld()))).setRegistryName("stone_roller");

    public static void clientInit()
    {
        ScreenManager.registerFactory((ContainerType<StoveContainer>) STOVE_CONTAINER, StoveGui::new);
        ScreenManager.registerFactory((ContainerType<BambooTrayContainer>) BAMBOO_TRAY_CONTAINER, BambooTrayGui::new);
        ScreenManager.registerFactory((ContainerType<DrinkMakerContainer>) DRINK_MAKER_CONTAINER, DrinkMakerGui::new);
        ScreenManager.registerFactory((ContainerType<StoneMillContainer>) STONE_MILL_CONTAINER, StoneMillGui::new);
        ScreenManager.registerFactory((ContainerType<StoneRollerContainer>) STONE_ROLLER_CONTAINER, StoneRollerGui::new);
    }
}
