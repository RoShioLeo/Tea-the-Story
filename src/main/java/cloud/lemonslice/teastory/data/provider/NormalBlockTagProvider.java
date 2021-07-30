package cloud.lemonslice.teastory.data.provider;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static cloud.lemonslice.teastory.TeaStory.MODID;

public final class NormalBlockTagProvider extends BlockTagsProvider
{
    public NormalBlockTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, MODID, existingFileHelper);
    }

    @Override
    protected void registerTags()
    {
        getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(BlockRegistry.BAMBOO_LATTICE).add(BlockRegistry.TRELLIS_BLOCKS.toArray(new Block[0]));
        getOrCreateBuilder(BlockTags.WOODEN_DOORS).add(BlockRegistry.BAMBOO_DOOR, BlockRegistry.BAMBOO_GLASS_DOOR);
        getOrCreateBuilder(BlockTags.SMALL_FLOWERS).add(BlockRegistry.HYACINTH, BlockRegistry.CHRYSANTHEMUM, BlockRegistry.ZINNIA);
    }

    @Override
    public String getName()
    {
        return "Tea the Story Block Tags";
    }
}
