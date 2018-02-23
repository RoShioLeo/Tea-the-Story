package cateam.teastory.block;

import java.util.ArrayList;

import javax.annotation.Nullable;

import cateam.teastory.item.ItemLoader;
import cateam.teastory.tileentity.TileEntityTeaDrink;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class TeaDrinkFull extends TeaDrink implements ITileEntityProvider
{
	public int drink;
	public Item teaDrink;

	public TeaDrinkFull(float hardness, String name, Material materialIn, SoundType soundType, int drink, int level)
	{
		super(hardness, name, materialIn, soundType, level);
		this.drink = drink;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTeaDrink();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		switch (drink)
		{
		case 1:
			return new ItemStack(ItemLoader.green_tea, 1, meta);
		case 2:
			return new ItemStack(ItemLoader.matcha_drink, 1, meta);
		case 3:
			return new ItemStack(ItemLoader.black_tea, 1, meta);
		}
		return new ItemStack(ItemLoader.green_tea, 1, meta);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		switch (drink)
		{
		case 1:
			this.teaDrink = ItemLoader.green_tea;
			break;
		case 2:
			this.teaDrink = ItemLoader.matcha_drink;
			break;
		case 3:
			this.teaDrink = ItemLoader.black_tea;
			break;
		}
		if (playerIn.isSneaking())
		{
			ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(teaDrink, 1, meta));
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			worldIn.removeTileEntity(pos);
			return true;
		}
		else
		{
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TileEntityTeaDrink)
			{
				if(((TileEntityTeaDrink)te).bite(playerIn, worldIn, drink, meta))
				{
					worldIn.removeTileEntity(pos);
				}
			}
			return true;
		}
	}

	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune)
	{
		ArrayList drops = new ArrayList();
		switch (drink)
		{
		case 1:
			drops.add(new ItemStack(ItemLoader.green_tea, 1, meta));
			break;
		case 2:
			drops.add(new ItemStack(ItemLoader.matcha_drink, 1, meta));
			break;
		case 3:
			drops.add(new ItemStack(ItemLoader.black_tea, 1, meta));
			break;
		}
		return drops;
	}
}
