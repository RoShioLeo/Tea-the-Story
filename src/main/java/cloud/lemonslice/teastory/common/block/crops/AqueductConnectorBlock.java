package cloud.lemonslice.teastory.common.block.crops;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AqueductConnectorBlock extends AqueductBlock
{
    public AqueductConnectorBlock(String name, Properties properties)
    {
        super(name, properties);
    }

    @Override
    public boolean canConnect(BlockState state)
    {
        return isAqueduct(state) || state.getFluidState().getFluid() == Fluids.WATER || state.getBlock() instanceof PaddyFieldBlock;
    }

    @Override
    public ActionResultType fillAqueduct(World worldIn, BlockPos pos, PlayerEntity player, Hand handIn)
    {
        if (player.getHeldItem(handIn).getItem() == Items.MOSSY_COBBLESTONE)
        {
            worldIn.setBlockState(pos, Blocks.MOSSY_COBBLESTONE.getDefaultState());
            return ActionResultType.SUCCESS;
        }
        else return ActionResultType.PASS;
    }
}
