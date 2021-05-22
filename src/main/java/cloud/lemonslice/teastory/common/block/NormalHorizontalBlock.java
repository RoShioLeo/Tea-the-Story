package cloud.lemonslice.teastory.common.block;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.StateContainer;

import java.util.List;

public class NormalHorizontalBlock extends HorizontalBlock
{
    protected NormalHorizontalBlock(Properties properties, String name)
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

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        if (context.getPlayer() != null)
        {
            return getDefaultState().with(HORIZONTAL_FACING, context.getPlayer().getHorizontalFacing().getOpposite());
        }
        return getDefaultState();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING);
    }
}
