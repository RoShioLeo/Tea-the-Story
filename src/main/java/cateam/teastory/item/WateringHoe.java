package cateam.teastory.item;

import cateam.teastory.creativetab.CreativeTabsLoader;
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

public class WateringHoe extends ItemHoe
{
	public WateringHoe()
	{
		super(ShennongRuler.SHENNONGTOOL);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("watering_hoe");
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		EnumActionResult[] result = new EnumActionResult[5];
        for (int i = -2; i<=2; i++)
        {
        	result[i+2] = this.onHoeUse(stack, playerIn, worldIn, pos.offset(EnumFacing.getHorizontal((playerIn.getHorizontalFacing().getHorizontalIndex() + 1) % 4), i), hand, facing, hitX, hitY, hitZ);
        }
        for (int i = 0; i<=4; i++)
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
        	if (worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER)
        	{
        		if (((Integer)worldIn.getBlockState(pos.up()).getValue(BlockLiquid.LEVEL)).intValue() == 0)
        		{
        			isWater = true;
        		}
        		worldIn.setBlockToAir(pos.up());
        	}
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, playerIn, worldIn, pos);
            if (hook != 0) 
            {
            	if (hook>0)
            	{
            		pourWater(playerIn, worldIn, pos, isWater, true);
            		return EnumActionResult.SUCCESS;
            	}
            	else
            	{
            		pourWater(playerIn, worldIn, pos, isWater, false);
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
                    pourWater(playerIn, worldIn, pos, isWater, true);
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(stack, playerIn, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            pourWater(playerIn, worldIn, pos, isWater, true);
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(stack, playerIn, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            pourWater(playerIn, worldIn, pos, isWater, true);
                            return EnumActionResult.SUCCESS;
                    }
                }
            }
            pourWater(playerIn, worldIn, pos, isWater, false);
            return EnumActionResult.PASS;
        }
	}
	
	public static void pourWater(EntityPlayer playerIn, World worldIn, BlockPos pos, boolean flag, boolean flag2)
	{
		if (flag2)
		{
			for(int i=0; i<=9; i++)
			{
				if (playerIn.inventory.mainInventory[i] != null && playerIn.inventory.mainInventory[i].isItemEqual(new ItemStack(Items.WATER_BUCKET, 1)) && worldIn.isAirBlock(pos.up())) flag = true;
			}
		}
		if (flag) worldIn.setBlockState(pos.up(), Blocks.WATER.getDefaultState());
	}
}
