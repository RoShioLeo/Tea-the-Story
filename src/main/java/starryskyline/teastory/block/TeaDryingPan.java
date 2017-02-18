package starryskyline.teastory.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import starryskyline.teastory.achievement.AchievementLoader;
import starryskyline.teastory.block.LitTeaDryingPan.EnumType;
import starryskyline.teastory.creativetab.CreativeTabsLoader;

public class TeaDryingPan extends Block
{
	protected static final AxisAlignedBB TEADRYINGPAN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	public TeaDryingPan()
	{
		super(Material.IRON);
		this.setHardness(3.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(CreativeTabsLoader.tabteastory);
        this.setUnlocalizedName("tea_drying_pan");
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return TEADRYINGPAN_AABB;
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(heldItem != null)
		{
			if(heldItem.getItem() == Items.FLINT_AND_STEEL)
			{
				if (!playerIn.capabilities.isCreativeMode)
                {
	    			heldItem.setItemDamage(heldItem.getItemDamage() + 1);
    		    }
			    worldIn.setBlockState(pos, BlockLoader.lit_tea_drying_pan.getStateFromMeta(1));
			    return true;
			}
		}
		if((worldIn.isRemote) && ((heldItem == null) || (Block.getBlockFromItem(heldItem.getItem()) != BlockLoader.tea_drying_pan)))
		{
		    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.1"));
		}
		return true;
	}
	
	@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		((EntityPlayer) placer).addStat(AchievementLoader.teaDryingPan);
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }
}
