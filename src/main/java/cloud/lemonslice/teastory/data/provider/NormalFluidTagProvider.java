package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
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

public final class NormalFluidTagProvider extends FluidTagsProvider
{
    public NormalFluidTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, MODID, existingFileHelper);
    }

    @Override
    protected void registerTags()
    {
        getOrCreateBuilder(NormalTags.Fluids.DRINK).add(FluidRegistry.BOILING_WATER_STILL.get(), FluidRegistry.SUGARY_WATER_STILL.get(), FluidRegistry.WEAK_BLACK_TEA_STILL.get(), FluidRegistry.BLACK_TEA_STILL.get(), FluidRegistry.STRONG_BLACK_TEA_STILL.get(),
                FluidRegistry.WEAK_GREEN_TEA_STILL.get(), FluidRegistry.GREEN_TEA_STILL.get(), FluidRegistry.STRONG_GREEN_TEA_STILL.get(), FluidRegistry.WEAK_WHITE_TEA_STILL.get(), FluidRegistry.WHITE_TEA_STILL.get(), FluidRegistry.STRONG_WHITE_TEA_STILL.get(),
                FluidRegistry.APPLE_JUICE_STILL.get(), FluidRegistry.CARROT_JUICE_STILL.get(), FluidRegistry.SUGAR_CANE_JUICE_STILL.get(), FluidRegistry.GRAPE_JUICE_STILL.get(), FluidRegistry.CUCUMBER_JUICE_STILL.get());

        List<Fluid> water = Lists.newArrayList();
        FluidRegistry.FLUIDS.getEntries().forEach(fluid -> water.add(fluid.get()));
        getOrCreateBuilder(FluidTags.WATER).add(water.toArray(new Fluid[0]));
    }

    @Override
    public String getName()
    {
        return "Tea the Story Fluid Tags";
    }
}
