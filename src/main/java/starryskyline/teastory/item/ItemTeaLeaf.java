package starryskyline.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starryskyline.teastory.block.BlockLoader;
import starryskyline.teastory.block.Kettle;

public class ItemTeaLeaf extends TSItem
{
	private Kettle kettle;
	public ItemTeaLeaf(String name, int maxstack, Kettle kettle) {
		super(name, maxstack);
		this.kettle = kettle;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else if ((worldIn.getBlockState(pos).getBlock() == BlockLoader.empty_kettle) && (stack.stackSize >=8))
		{
			int meta = BlockLoader.empty_kettle.getMetaFromState(worldIn.getBlockState(pos));
			if ((meta & 12) == 12)
			{
				worldIn.setBlockState(pos, kettle.getStateFromMeta(meta & 3));
                if (!playerIn.capabilities.isCreativeMode)
                {
            	    stack.stackSize = stack.stackSize - 8;
                }
                return true;
			}
			else return false;
		}
		else return false;
	}
}
