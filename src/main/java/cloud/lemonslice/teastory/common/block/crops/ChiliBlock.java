package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;
import java.util.List;

public class ChiliBlock extends CropsBlock {
    public ChiliBlock(String name) {
        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP));
        this.setRegistryName(name);
    }

    @Override
    public int getMaxAge() {
        return 6;
    }
    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegistry.CHILI_SEEDS;
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = new ArrayList<>();
        if (state.get(AGE) == this.getMaxAge()) {
            list.add(new ItemStack(ItemRegistry.CHILI, 2 + RANDOM.nextInt(3)));
            list.add(new ItemStack(getSeedsItem(), RANDOM.nextInt(2)));
        }
        list.add(new ItemStack(getSeedsItem()));
        return list;
    }
}
