package starryskyline.teastory.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import starryskyline.teastory.creativetab.CreativeTabsLoader;

public class TeaResidue extends TSItem
{
	public TeaResidue()
	{
		super("tea_residue", 64);
        this.setHasSubtypes(true);
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(StatCollector.translateToLocal("teastory.tooltip.tea_residue"));
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
	    return super.getUnlocalizedName() + "." + (stack.getItemDamage() == 0 ? "green" : "black");
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
	    subItems.add(new ItemStack(itemIn, 1, 0));
	    subItems.add(new ItemStack(itemIn, 1, 1));
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if (ItemDye.applyBonemeal(stack, worldIn, pos, playerIn))
        {
            if (!worldIn.isRemote)
            {
                worldIn.playAuxSFX(2005, pos, 0);
            }
            return true;
        }
		else return false;
    }
}
