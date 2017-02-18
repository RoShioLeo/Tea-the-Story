package starryskyline.teastory.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import starryskyline.teastory.block.BlockLoader;
import starryskyline.teastory.creativetab.CreativeTabsLoader;

public class TeaSeeds extends ItemSeeds
{
	public TeaSeeds()
	{
		super((Block)BlockLoader.teaplant, Blocks.FARMLAND);
        this.setUnlocalizedName("tea_seeds");
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabsLoader.tabteastory);
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(I18n.translateToLocal("teastory.tooltip.tea_seeds"));
    }
}
