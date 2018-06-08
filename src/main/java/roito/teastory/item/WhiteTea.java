package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockLoader;
import roito.teastory.common.AchievementLoader;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionLoader;

public class WhiteTea extends ItemTeaDrink
{
	public WhiteTea()
	{
		super("white_tea");
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(!world.isRemote)
		{
			int tier = itemstack.getItemDamage();
			addPotion(tier, world, entityplayer);
		}
	}

	public static void addPotion(int tier, World world, EntityPlayer entityplayer)
	{
		ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemLoader.tea_residue, 1, 3));
		entityplayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, ConfigMain.whiteTeaDrink_Time / (tier + 1), tier));
		entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionExcitement, ConfigMain.whiteTeaDrink_Time / (tier + 1), 0));
		entityplayer.addStat(AchievementLoader.whiteDrink);
	}

	public Block getBlock(int meta)
	{
		switch(meta)
		{
		case 2:
			return BlockLoader.whitetea_stone_cup;
		case 3:
			return BlockLoader.whitetea_glass_cup;
		case 4:
			return BlockLoader.whitetea_porcelain_cup;
		case 5:
			return BlockLoader.whitetea_zisha_cup;
		default:
			return BlockLoader.whitetea_wood_cup;
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
