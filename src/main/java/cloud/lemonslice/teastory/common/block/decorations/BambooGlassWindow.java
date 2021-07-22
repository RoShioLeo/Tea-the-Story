package cloud.lemonslice.teastory.common.block.decorations;

import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BambooGlassWindow extends PaneBlock
{
    public BambooGlassWindow()
    {
        super(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS));
        this.setRegistryName("bamboo_glass_window");
    }
}
