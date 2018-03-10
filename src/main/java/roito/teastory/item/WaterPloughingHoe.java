package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import roito.teastory.common.CreativeTabsLoader;

public class WaterPloughingHoe extends ItemHoe
{
	public WaterPloughingHoe()
	{
		super(ShennongRuler.SHENNONGTOOL);
		this.setCreativeTab(CreativeTabsLoader.tabRice);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("waterploughing_hoe");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		EnumActionResult[] result = new EnumActionResult[3];
		for (int i = -1; i<=1; i++)
		{
			if (this.onHoeUse(stack, playerIn, worldIn, pos.offset(EnumFacing.getHorizontal((playerIn.getHorizontalFacing().getHorizontalIndex() + 1) % 4), i).offset(EnumFacing.getHorizontal((playerIn.getHorizontalFacing().getHorizontalIndex()) % 4), -1), hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS 
				| this.onHoeUse(stack, playerIn, worldIn, pos.offset(EnumFacing.getHorizontal((playerIn.getHorizontalFacing().getHorizontalIndex() + 1) % 4), i), hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS
				| this.onHoeUse(stack, playerIn, worldIn, pos.offset(EnumFacing.getHorizontal((playerIn.getHorizontalFacing().getHorizontalIndex() + 1) % 4), i).offset(EnumFacing.getHorizontal((playerIn.getHorizontalFacing().getHorizontalIndex()) % 4), 1), hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS)
			{
				result[i+1] = EnumActionResult.SUCCESS;
			}
			else result[i+1] = EnumActionResult.FAIL;
		}
		for (int i = 0; i<=2; i++)
		{
			if (result[i] == EnumActionResult.SUCCESS) return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	@SuppressWarnings("incomplete-switch")
	public EnumActionResult onHoeUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			boolean isWater = false;
			IBlockState water = Blocks.AIR.getDefaultState();
			if (worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER)
			{
				water = worldIn.getBlockState(pos.up());
				isWater = true;
				worldIn.setBlockToAir(pos.up());
			}
			int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, playerIn, worldIn, pos);
			if (hook != 0)
			{
				if (hook>0)
				{
					pourWater(playerIn, worldIn, pos, isWater, water);
					return EnumActionResult.SUCCESS;
				}
				else
				{
					pourWater(playerIn, worldIn, pos, isWater, water);
					return EnumActionResult.FAIL;
				}
			}

			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
			{
				if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
				{
					this.setBlock(stack, playerIn, worldIn, pos, Blocks.FARMLAND.getDefaultState());
					pourWater(playerIn, worldIn, pos, isWater, water);
					return EnumActionResult.SUCCESS;
				}

				if (block == Blocks.DIRT)
				{
					switch (iblockstate.getValue(BlockDirt.VARIANT))
					{
					case DIRT:
						this.setBlock(stack, playerIn, worldIn, pos, Blocks.FARMLAND.getDefaultState());
						pourWater(playerIn, worldIn, pos, isWater, water);
						return EnumActionResult.SUCCESS;
					case COARSE_DIRT:
						this.setBlock(stack, playerIn, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
						pourWater(playerIn, worldIn, pos, isWater, water);
						return EnumActionResult.SUCCESS;
					}
				}
			}
			pourWater(playerIn, worldIn, pos, isWater, water);
			return EnumActionResult.PASS;
		}
	}

	public static void pourWater(EntityPlayer playerIn, World worldIn, BlockPos pos, boolean IsWater, IBlockState water)
	{
		if (IsWater) worldIn.setBlockState(pos.up(), water);
	}
}
