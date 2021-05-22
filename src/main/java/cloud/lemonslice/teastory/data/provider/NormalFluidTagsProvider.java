package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.teastory.common.fluid.FluidsRegistry;
import cloud.lemonslice.teastory.data.tag.NormalTags;
import com.google.common.collect.Lists;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.List;

import static cloud.lemonslice.teastory.TeaStory.MODID;

public class NormalFluidTagsProvider extends FluidTagsProvider
{
    public NormalFluidTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, MODID, existingFileHelper);
    }

    @Override
    protected void registerTags()
    {
        getOrCreateBuilder(NormalTags.Fluids.DRINK).add(FluidsRegistry.BOILING_WATER_STILL.get(), FluidsRegistry.SUGARY_WATER_STILL.get(), FluidsRegistry.WEAK_BLACK_TEA_STILL.get(), FluidsRegistry.BLACK_TEA_STILL.get(), FluidsRegistry.STRONG_BLACK_TEA_STILL.get(),
                FluidsRegistry.WEAK_GREEN_TEA_STILL.get(), FluidsRegistry.GREEN_TEA_STILL.get(), FluidsRegistry.STRONG_GREEN_TEA_STILL.get(),
                FluidsRegistry.WEAK_WHITE_TEA_STILL.get(), FluidsRegistry.WHITE_TEA_STILL.get(), FluidsRegistry.STRONG_WHITE_TEA_STILL.get());

        List<Fluid> water = Lists.newArrayList();
        FluidsRegistry.FLUIDS.getEntries().forEach(fluid -> water.add(fluid.get()));
        getOrCreateBuilder(FluidTags.WATER).add(water.toArray(new Fluid[0]));
    }

    @Override
    public String getName()
    {
        return "Tea the Story Fluid Tags";
    }
}
