package cateam.teastory.item;

import java.util.List;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;

public class TeaSeeds extends ItemSeeds
{
	public TeaSeeds()
	{
		super((Block)BlockLoader.teaplant, Blocks.farmland);
        this.setUnlocalizedName("tea_seeds");
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabsLoader.tabteastory);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(StatCollector.translateToLocal("teastory.tooltip.tea_seeds"));
    }
}
