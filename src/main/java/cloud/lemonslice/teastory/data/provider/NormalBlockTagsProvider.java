package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.teastory.common.block.BlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.TeaStory.MODID;

public class NormalBlockTagsProvider extends BlockTagsProvider
{
    public NormalBlockTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, MODID, existingFileHelper);
    }

    @Override
    protected void registerTags()
    {
        getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(BlocksRegistry.BAMBOO_LATTICE).add(BlocksRegistry.TRELLIS_BLOCKS.toArray(new Block[0]));
        getOrCreateBuilder(BlockTags.WOODEN_DOORS).add(BlocksRegistry.BAMBOO_DOOR, BlocksRegistry.BAMBOO_GLASS_DOOR);
        getOrCreateBuilder(BlockTags.SMALL_FLOWERS).add(BlocksRegistry.HYACINTH, BlocksRegistry.CHRYSANTHEMUM, BlocksRegistry.ZINNIA);
    }

    @Override
    public String getName()
    {
        return "After the Drizzle Block Tags";
    }
}
