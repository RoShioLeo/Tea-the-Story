package cloud.lemonslice.teastory.common.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class NormalFlowingFluidBlock extends FlowingFluidBlock
{
    public NormalFlowingFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties)
    {
        super(supplier, properties);
    }

    public static Block.Properties getProperties()
    {
        return Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops();
    }
}
