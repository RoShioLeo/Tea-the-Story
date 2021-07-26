package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class StoneMillBlock extends NormalHorizontalBlock
{
    public StoneMillBlock()
    {
        super(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5F).sound(SoundType.STONE), "stone_mill");
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
}
