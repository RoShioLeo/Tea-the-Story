package cloud.lemonslice.teastory;

import cloud.lemonslice.teastory.client.ClientProxy;
import cloud.lemonslice.teastory.client.color.block.BlockColorsRegistry;
import cloud.lemonslice.teastory.client.color.item.ItemColorsRegistry;
import cloud.lemonslice.teastory.client.sound.SoundEventsRegistry;
import cloud.lemonslice.teastory.common.CommonProxy;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.capability.CapabilityRegistry;
import cloud.lemonslice.teastory.common.command.SolarCommand;
import cloud.lemonslice.teastory.common.config.NormalConfigs;
import cloud.lemonslice.teastory.common.container.ContainerTypeRegistry;
import cloud.lemonslice.teastory.common.entity.EntityTypeRegistry;
import cloud.lemonslice.teastory.common.environment.crop.CropInfoManager;
import cloud.lemonslice.teastory.common.environment.solar.BiomeTemperatureManager;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import cloud.lemonslice.teastory.common.group.GroupCore;
import cloud.lemonslice.teastory.common.group.GroupDrink;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.network.SimpleNetworkHandler;
import cloud.lemonslice.teastory.common.potion.EffectRegistry;
import cloud.lemonslice.teastory.common.recipe.drink.DrinkEffectManager;
import cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry;
import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import cloud.lemonslice.teastory.common.world.feature.FeatureRegistry;
import cloud.lemonslice.teastory.data.loot.GlobalLootModifierSerializerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("teastory")
public final class TeaStory
{
    public static final String MODID = "teastory";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String NETWORK_VERSION = "1.0";

    public static final CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public TeaStory()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::interModEnqueue);
        MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, NormalConfigs.SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, NormalConfigs.CLIENT_CONFIG);
        FluidRegistry.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FluidRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FluidRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RecipeSerializerRegistry.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        new BlockRegistry();
        new ItemRegistry();
        new EffectRegistry();
        new TileEntityTypeRegistry();
        new EntityTypeRegistry();
        new FeatureRegistry();
        new SoundEventsRegistry();
        new ContainerTypeRegistry();
        GlobalLootModifierSerializerRegistry.LOOT_MODIFIER_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public void setup(FMLCommonSetupEvent event)
    {
        DrinkEffectManager.init();
        CapabilityRegistry.init();
        SimpleNetworkHandler.init();
        BiomeTemperatureManager.init();
        CommonProxy.registerCompostable();
        CropInfoManager.initTrellisBlocks();
    }

    public void clientSetup(FMLClientSetupEvent event)
    {
        ItemColorsRegistry.init();
        BlockColorsRegistry.init();
        ClientProxy.registerProperties();
        ClientProxy.initBiomeColors();
        ClientProxy.registerRenderType();
        ClientProxy.registerEntityRenderer();
        ContainerTypeRegistry.clientInit();
    }

    public void serverStarting(FMLServerStartingEvent event)
    {
        SolarCommand.register(event.getServer().getCommandManager().getDispatcher());
    }

    public void interModEnqueue(InterModEnqueueEvent event)
    {
        BlockRegistry.TRELLIS_BLOCKS = null;
    }

    public static final ItemGroup GROUP_CORE = new GroupCore();
    public static final ItemGroup GROUP_DRINK = new GroupDrink();
}