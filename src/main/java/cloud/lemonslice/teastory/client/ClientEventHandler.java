package cloud.lemonslice.teastory.client;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import cloud.lemonslice.teastory.common.environment.crop.CropHumidityInfo;
import cloud.lemonslice.teastory.common.environment.crop.CropInfoManager;
import cloud.lemonslice.teastory.common.environment.crop.CropSeasonInfo;
import cloud.lemonslice.teastory.common.fluid.NormalFlowingFluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
            if (event.getItemStack().getItem() instanceof BlockItem)
            {
                if (CropInfoManager.getHumidityCrops().contains(((BlockItem) event.getItemStack().getItem()).getBlock()))
                {
                    CropHumidityInfo info = CropInfoManager.getHumidityInfo(((BlockItem) event.getItemStack().getItem()).getBlock());
                    event.getToolTip().addAll(info.getTooltip());
                }
                if (CropInfoManager.getSeasonCrops().contains(((BlockItem) event.getItemStack().getItem()).getBlock()))
                {
                    CropSeasonInfo info = CropInfoManager.getSeasonInfo(((BlockItem) event.getItemStack().getItem()).getBlock());
                    event.getToolTip().addAll(info.getTooltip());
                }
            }
        }
    }
}
