package cateam.teastory.item;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.block.StrawBlanket;
import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemStrawBlanket extends TSItem
{
	public ItemStrawBlanket()
	{
		super("item_straw_blanket", 64, CreativeTabsLoader.tabrice);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (facing != EnumFacing.UP)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            boolean flag = block.isReplaceable(worldIn, pos);

            if (!flag)
            {
                pos = pos.up();
            }

            int i = MathHelper.floor_double((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            EnumFacing enumfacing = EnumFacing.getHorizontal(i);
            BlockPos blockpos = pos.offset(enumfacing);

            if (playerIn.canPlayerEdit(pos, facing, stack) && playerIn.canPlayerEdit(blockpos, facing, stack))
            {
                boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
                boolean flag2 = flag || worldIn.isAirBlock(pos);
                boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

                if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isFullyOpaque() && worldIn.getBlockState(blockpos.down()).isFullyOpaque())
                {
                    IBlockState iblockstate1 = BlockLoader.straw_blanket.getDefaultState().withProperty(StrawBlanket.OCCUPIED, Boolean.valueOf(false)).withProperty(StrawBlanket.FACING, enumfacing).withProperty(StrawBlanket.PART, StrawBlanket.EnumPartType.FOOT);

                    if (worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        IBlockState iblockstate2 = iblockstate1.withProperty(StrawBlanket.PART, StrawBlanket.EnumPartType.HEAD);
                        worldIn.setBlockState(blockpos, iblockstate2, 11);
                    }

                    SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, playerIn);
                    worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    --stack.stackSize;
                    return EnumActionResult.SUCCESS;
                }
                else
                {
                    return EnumActionResult.FAIL;
                }
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
    }
}
