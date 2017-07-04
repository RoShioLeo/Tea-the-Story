package cateam.teastory.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemBlockEmptyKettle extends ItemMultiTexture
{
	public ItemBlockEmptyKettle(Block block, Block block2, Function<ItemStack, String> nameFunction)
    {
		super(block, block, nameFunction);
		this.setMaxStackSize(1);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + (String)this.nameFunction.apply(stack);
    }
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(I18n.translateToLocal("teastory.tooltip.kettle"));
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

        if (raytraceresult == null)
        {
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }
        else
        {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockpos = raytraceresult.getBlockPos();

                if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStackIn))
                {
                    return new ActionResult(EnumActionResult.PASS, itemStackIn);
                }

                if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER)
                {
                    worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    return new ActionResult(EnumActionResult.SUCCESS, new ItemStack(BlockLoader.empty_kettle, 1, 4));
                }
            }
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }
    }
	
	protected ItemStack turnKettleIntoItem(ItemStack stackIn, EntityPlayer player, ItemStack stack)
    {
		return stack;
    }
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	Block block = worldIn.getBlockState(pos).getBlock();
    	int meta = stack.getItemDamage();
    	if(meta == 0)
    	{
    		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

            if (raytraceresult == null)
            {
            	return EnumActionResult.PASS;
            }
            else
            {
                if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    BlockPos blockpos = raytraceresult.getBlockPos();

                    if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, stack))
                    {
                        return EnumActionResult.PASS;
                    }

                    if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER)
                    {
                        worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        stack.setItemDamage(4);
                    	stack.setItem(stack.getItem());
                    	if (playerIn instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                        }
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
    	}
    	else if (meta == 12)
    	{
    	    if (block == BlockLoader.wood_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_wood_cup.getStateFromMeta(1));
    	    	return EnumActionResult.SUCCESS;
    	    }
    	    else if(block == BlockLoader.stone_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_stone_cup.getStateFromMeta(1));
    	    	return EnumActionResult.SUCCESS;
    	    }
    	    else if(block == BlockLoader.glass_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_glass_cup.getStateFromMeta(1));
    	    	return EnumActionResult.SUCCESS;
    	    }
    	    else if(block == BlockLoader.porcelain_cup)
    	    {
    	    	worldIn.setBlockState(pos, BlockLoader.hotwater_porcelain_cup.getStateFromMeta(1));
    	    	return EnumActionResult.SUCCESS;
    	    }
    	}
		super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitZ, hitZ, hitZ);
    	return EnumActionResult.PASS;
    }
}
