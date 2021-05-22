package cloud.lemonslice.teastory.common.block;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;

import java.util.List;

public class NormalBlock extends Block
{
    public NormalBlock(String name, Properties properties)
    {
        super(properties);
        this.setRegistryName(name);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        List<ItemStack> list = Lists.newArrayList();
        list.add(new ItemStack(this));
        return list;
    }
}
