package roito.teastory.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import roito.teastory.block.BlockRegister;
import roito.teastory.common.CreativeTabsRegister;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRiceSeedling extends TSItem
{
	public ItemRiceSeedling()
	{
		super("item_xian_rice_seedling", 64, CreativeTabsRegister.tabRice);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.rice_seedling.temperature"));
			tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.rice_seedling.humidity"));
		}
		else
		{
			tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, true);

		if (movingobjectposition == null)
		{
			return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
		else
		{
			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				BlockPos blockpos = movingobjectposition.getBlockPos();

				if (!worldIn.isBlockModifiable(playerIn, blockpos))
				{
					return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
				}

				if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, playerIn.getHeldItem(handIn)))
				{
					return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
				}

				BlockPos blockpos1 = blockpos.up();
				IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
				IBlockState iblockstate2 = worldIn.getBlockState(blockpos1);

				if (iblockstate1.getBlock() == BlockRegister.paddy_field && iblockstate2.getBlock() != BlockRegister.xian_rice_plant)
				{
					net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);

					if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(playerIn, blocksnapshot, net.minecraft.util.EnumFacing.UP, handIn).isCanceled())
					{
						blocksnapshot.restore(true, false);
						return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
					}

					worldIn.setBlockState(blockpos1, BlockRegister.xian_rice_plant.getDefaultState());

					if (!playerIn.capabilities.isCreativeMode)
					{
						playerIn.getHeldItem(handIn).shrink(1);
					}
				}
			}
			return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
	}
}
