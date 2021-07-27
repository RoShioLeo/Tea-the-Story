package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalHorizontalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class StoneCampfireBlock extends NormalHorizontalBlock
{
    public StoneCampfireBlock()
    {
        super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F), "stone_campfire");
    }
}
