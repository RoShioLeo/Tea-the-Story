package cloud.lemonslice.teastory.common.network;

import cloud.lemonslice.silveroak.network.INormalMessage;
import cloud.lemonslice.teastory.TeaStory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Function;

public final class SimpleNetworkHandler
{
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(TeaStory.MODID, "main")).networkProtocolVersion(() -> TeaStory.NETWORK_VERSION).serverAcceptedVersions(TeaStory.NETWORK_VERSION::equals).clientAcceptedVersions(TeaStory.NETWORK_VERSION::equals).simpleChannel();

    public static void init()
    {
        int id = 0;
        registerMessage(id++, SolarTermsMessage.class, SolarTermsMessage::new);
    }

    private static <T extends INormalMessage> void registerMessage(int index, Class<T> messageType, Function<PacketBuffer, T> decoder)
    {
        CHANNEL.registerMessage(index, messageType, INormalMessage::toBytes, decoder, (message, context) ->
        {
            message.process(context);
            context.get().setPacketHandled(true);
        });
    }
}
