package cateam.teastory.block;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
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
		return super.getUnlocalizedName() + this.nameFunction.apply(stack);
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE +(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.kettle")));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		int meta = itemStackIn.getItemDamage();
		if (meta == 0)
		{
			RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
			ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, raytraceresult);
			if (ret != null) return ret;

			if (raytraceresult == null)
			{
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
			else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
			{
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
			else
			{
				BlockPos blockpos = raytraceresult.getBlockPos();

				if (!worldIn.isBlockModifiable(playerIn, blockpos))
				{
					return new ActionResult(EnumActionResult.FAIL, itemStackIn);
				}
				else
				{
					if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStackIn))
					{
						return new ActionResult(EnumActionResult.FAIL, itemStackIn);
					}
					else
					{
						IBlockState iblockstate = worldIn.getBlockState(blockpos);
						Material material = iblockstate.getMaterial();

						if (material == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL).intValue() == 0)
						{
							worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
							playerIn.addStat(StatList.getObjectUseStats(this));
							playerIn.playSound(SoundEvents.ITEM_BOTTLE_FILL, 1.0F, 1.0F);
							return new ActionResult(EnumActionResult.SUCCESS, new ItemStack(BlockLoader.empty_kettle, 1, 4));
						}
						else
						{
							return new ActionResult(EnumActionResult.FAIL, itemStackIn);
						}
					}
				}
			}
		}
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		Block block = worldIn.getBlockState(pos).getBlock();
		int meta = stack.getItemDamage();
		if (meta == 12)
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
		BlockPos posC = pos;
		if (worldIn.getBlockState(posC.offset(facing)).getMaterial() != Material.WATER)
		{
			return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitZ, hitZ, hitZ);
		}
		return EnumActionResult.PASS;
	}
}
