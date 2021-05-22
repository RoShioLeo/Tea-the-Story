package cloud.lemonslice.teastory.client;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.common.fluid.NormalFlowingFluidBlock;
import net.minecraft.block.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cloud.lemonslice.teastory.common.handler.CommonEventHandler.addCropTooltips;

@Mod.EventBusSubscriber(modid = TeaStory.MODID, value = Dist.CLIENT)
public final class ClientEventHandler
{
    @SubscribeEvent
    public static void addFogColor(EntityViewRenderEvent.FogColors event)
    {
        BlockState state = event.getInfo().getFluidState().getBlockState();

        if (state.getBlock() instanceof NormalFlowingFluidBlock)
        {
            int color = state.getFluidState().getFluid().getAttributes().getColor();

            event.setRed((float) (((color >> 16) & 255) / 255.0));
            event.setGreen((float) (((color >> 8) & 255) / 255.0));
            event.setBlue((float) ((color & 255) / 255.0));
        }
    }

    @SubscribeEvent
    public static void addTooltips(ItemTooltipEvent event)
    {
        if (ServerConfig.Season.enable.get())
        {
            addCropTooltips(event);
        }
    }
}
