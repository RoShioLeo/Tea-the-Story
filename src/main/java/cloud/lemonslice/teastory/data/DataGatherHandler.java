package cloud.lemonslice.teastory.data;

import cloud.lemonslice.teastory.data.provider.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cloud.lemonslice.teastory.TeaStory.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGatherHandler
{
    @SubscribeEvent
    public static void onDataGather(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        if (event.includeServer())
        {
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            ForgeBlockTagsProvider blockTags = new ForgeBlockTagsProvider(gen, existingFileHelper);
            gen.addProvider(new CropInfoProvider(gen, blockTags, existingFileHelper));
            gen.addProvider(new NormalItemTagProvider(gen, blockTags, existingFileHelper));
            gen.addProvider(new NormalBlockTagProvider(gen, existingFileHelper));
            gen.addProvider(new NormalFluidTagProvider(gen, existingFileHelper));
            gen.addProvider(new RecipeProvider(gen));
        }
    }
}
