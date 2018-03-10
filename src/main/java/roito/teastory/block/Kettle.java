package roito.teastory.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import roito.teastory.common.CreativeTabsLoader;

public class Kettle extends BlockHorizontal
{
	protected static final AxisAlignedBB KETTLE_AABB = new AxisAlignedBB(0.1875F, 0F, 0.1875F, 0.8125F, 0.5125F, 0.8125F);
	public Kettle(String name, Material material)
	{
		super(material);
		this.setHardness(1.25F);
		this.setSoundType(SoundType.STONE);
		this.setUnlocalizedName(name);
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return KETTLE_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else
		{
			if (heldItem == null)
			{
				if (!playerIn.inventory.addItemStackToInventory(new ItemStack(state.getBlock(), 1, damageDropped(state))))
				{
					playerIn.getEntityWorld().spawnEntityInWorld(new EntityItem(playerIn.getEntityWorld(), playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D,
							new ItemStack(state.getBlock(), 1, damageDropped(state))));
				}
				else if (playerIn instanceof EntityPlayerMP)
				{
					((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
				}
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
			return true;
		}
	}
}
