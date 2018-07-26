package roito.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import roito.teastory.block.BlockLoader;
import roito.teastory.common.AchievementLoader;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.config.ConfigMain;

public class ItemRiceSeedling extends ItemSeeds
{
	public ItemRiceSeedling()
	{
		super(BlockLoader.xian_rice_plant, Blocks.WATER);
		this.setUnlocalizedName("item_xian_rice_seedling");
		this.setCreativeTab(CreativeTabsLoader.tabRice);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.rice_seedling.temperature"));
			list.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.rice_seedling.humidity"));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, true);

		if (movingobjectposition == null)
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
		}
		else
		{
			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				BlockPos blockpos = movingobjectposition.getBlockPos();

				if (!worldIn.isBlockModifiable(playerIn, blockpos))
				{
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
				}

				if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStackIn))
				{
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
				}

				BlockPos blockpos1 = blockpos.up();
				BlockPos blockpos2 = blockpos.down();
				IBlockState iblockstate = worldIn.getBlockState(blockpos);
				IBlockState iblockstate2 = worldIn.getBlockState(blockpos2);
				
				boolean canPlant = ConfigMain.isRiceLimited ? worldIn.getBiome(blockpos1).getTemperature() >= 0.5F && worldIn.getBiome(blockpos1).getRainfall() >= 0.5F : true;

				if (iblockstate.getMaterial() == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL).intValue() == 0 && worldIn.isAirBlock(blockpos1) && iblockstate2.getBlock() instanceof BlockFarmland && canPlant)
				{
					net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);

					if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(playerIn, blocksnapshot, net.minecraft.util.EnumFacing.UP).isCanceled())
					{
						blocksnapshot.restore(true, false);
						return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
					}
					
					worldIn.setBlockState(blockpos1, BlockLoader.xian_rice_plant.getDefaultState());

					if (!playerIn.capabilities.isCreativeMode)
					{
						--itemStackIn.stackSize;
					}
					playerIn.addStat(AchievementLoader.transplanting);
				}
				else if (worldIn.isRemote && !canPlant)
				{
					playerIn.addChatMessage(new TextComponentTranslation("teastory.message.rice_seedling"));
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		return EnumActionResult.PASS;
	}
}
