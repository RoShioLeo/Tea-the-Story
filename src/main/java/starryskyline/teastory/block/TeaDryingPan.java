package starryskyline.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import starryskyline.teastory.achievement.AchievementLoader;
import starryskyline.teastory.block.LitTeaDryingPan.EnumType;
import starryskyline.teastory.creativetab.CreativeTabsLoader;

public class TeaDryingPan extends Block
{
	public TeaDryingPan()
	{
		super(Material.iron);
		this.setHardness(3.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(CreativeTabsLoader.tabteastory);
        this.setUnlocalizedName("tea_drying_pan");
	}
	
	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	public boolean isFullCube()
    {
        return false;
    }
	
	@Override
	public  boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(playerIn.getHeldItem() != null)
		{
			if(playerIn.getHeldItem().getItem() == Items.flint_and_steel)
			{
				if (!playerIn.capabilities.isCreativeMode)
                {
	    			playerIn.getHeldItem().setItemDamage(playerIn.getHeldItem().getItemDamage() + 1);
    		    }
			    worldIn.setBlockState(pos, BlockLoader.lit_tea_drying_pan.getStateFromMeta(1));
			    return true;
			}
		}
		if((worldIn.isRemote) && ((playerIn.getHeldItem() == null) || (Block.getBlockFromItem(playerIn.getHeldItem().getItem()) != BlockLoader.tea_drying_pan)))
		{
		    playerIn.addChatMessage(new ChatComponentTranslation("teastory.tea_drying_pan.message.1"));
		}
		return true;
	}
	
	@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		((EntityPlayer) placer).triggerAchievement(AchievementLoader.teaDryingPan);
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }
}
