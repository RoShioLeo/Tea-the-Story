package cateam.teastory.item;

import java.util.List;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemCup extends TSItem
{
	public ItemCup()
	{
		super("cup", 64, CreativeTabsLoader.tabteastory);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name;
		switch(stack.getItemDamage())
		{
		case 1:
			name = "stone";
			break;
		case 2:
			name = "glass";
			break;
		case 3:
			name = "porcelain";
			break;
		default:
			name = "wood";
		}
		return super.getUnlocalizedName() + "." + name;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 1));
		subItems.add(new ItemStack(itemIn, 1, 2));
		subItems.add(new ItemStack(itemIn, 1, 3));
	}

	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 1:
			return BlockLoader.stone_cup;
		case 2:
			return BlockLoader.glass_cup;
		case 3:
			return BlockLoader.porcelain_cup;
		default:
			return BlockLoader.wood_cup;
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (playerIn.isSneaking())
		{
			Block drinkblock = getBlock(stack.getItemDamage());
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(worldIn, pos))
			{
				pos = pos.offset(facing);
			}

			if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.canBlockBePlaced(drinkblock, pos, false, facing, (Entity)null, stack))
			{
				int i = this.getMetadata(stack.getMetadata());
				IBlockState iblockstate1 = drinkblock.getDefaultState();

				if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1))
				{
					SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
					worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					--stack.stackSize;
				}

				return EnumActionResult.SUCCESS;
			}
			else
			{
				return EnumActionResult.FAIL;
			}
		}
		else
		{
			return EnumActionResult.PASS;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
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
						playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
						return new ActionResult(EnumActionResult.SUCCESS, this.turnCupIntoItem(itemStackIn, playerIn, new ItemStack(ItemLoader.cold_water, 1, itemStackIn.getItemDamage())));
					}
					else
					{
						return new ActionResult(EnumActionResult.FAIL, itemStackIn);
					}
				}
			}
		}
	}

	protected ItemStack turnCupIntoItem(ItemStack stackIn, EntityPlayer player, ItemStack stack)
	{
		if(!player.capabilities.isCreativeMode)
			--stackIn.stackSize;

		if (stackIn.stackSize <= 0)
		{
			return stack;
		}
		else
		{
			ItemHandlerHelper.giveItemToPlayer(player, stack);

			return stackIn;
		}
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
	{
		if (!world.setBlockState(pos, newState, 3)) return false;

		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == getBlock(stack.getItemDamage()))
		{
			ItemBlock.setTileEntityNBT(world, player, pos, stack);
			getBlock(stack.getItemDamage()).onBlockPlacedBy(world, pos, state, player, stack);
		}

		return true;
	}
}
