package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class WoodenBarrelBlock extends NormalBlock
{
    public WoodenBarrelBlock()
    {
        super("wooden_barrel", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    }
}
