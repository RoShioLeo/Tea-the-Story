package cateam.teastory.block;

import javax.annotation.Nullable;

import cateam.teastory.TeaStory;
import cateam.teastory.common.CreativeTabsLoader;
import cateam.teastory.inventory.GuiElementLoader;
import cateam.teastory.tileentity.TileEntityTeaTable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class TeaTable extends Block implements ITileEntityProvider
{
	protected static final AxisAlignedBB TEATABLE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D);
	private static boolean keepInventory = false;
	
	public TeaTable()
	{
		super(Material.WOOD);
		this.setHardness(3.5F);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName("tea_table");
		this.setCreativeTab(CreativeTabsLoader.tabDrink);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTeaTable();
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return TEATABLE_AABB;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
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
		if (!worldIn.isRemote)
		{
			int id = GuiElementLoader.GUI_TEATABLE;
			playerIn.openGui(TeaStory.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!keepInventory)
		{
			TileEntityTeaTable te = (TileEntityTeaTable) worldIn.getTileEntity(pos);
			IItemHandler inventory0 = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
			IItemHandler inventory1 = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
			IItemHandler inventory2 = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
			IItemHandler inventory3 = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.SOUTH);
			IItemHandler inventory4 = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.WEST);
			IItemHandler inventory5 = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);

			for (int i = inventory0.getSlots() - 1; i >= 0; --i)
			{
				if (inventory0.getStackInSlot(i) != null)
				{
					Block.spawnAsEntity(worldIn, pos, inventory0.getStackInSlot(i));
					((IItemHandlerModifiable) inventory0).setStackInSlot(i, null);
				}
			}

			for (int i = inventory1.getSlots() - 1; i >= 0; --i)
			{
				if (inventory1.getStackInSlot(i) != null)
				{
					Block.spawnAsEntity(worldIn, pos, inventory1.getStackInSlot(i));
					((IItemHandlerModifiable) inventory1).setStackInSlot(i, null);
				}
			}
			
			for (int i = inventory2.getSlots() - 1; i >= 0; --i)
			{
				if (inventory2.getStackInSlot(i) != null)
				{
					Block.spawnAsEntity(worldIn, pos, inventory2.getStackInSlot(i));
					((IItemHandlerModifiable) inventory2).setStackInSlot(i, null);
				}
			}

			for (int i = inventory3.getSlots() - 1; i >= 0; --i)
			{
				if (inventory3.getStackInSlot(i) != null)
				{
					Block.spawnAsEntity(worldIn, pos, inventory3.getStackInSlot(i));
					((IItemHandlerModifiable) inventory3).setStackInSlot(i, null);
				}
			}
			
			for (int i = inventory4.getSlots() - 1; i >= 0; --i)
			{
				if (inventory4.getStackInSlot(i) != null)
				{
					Block.spawnAsEntity(worldIn, pos, inventory4.getStackInSlot(i));
					((IItemHandlerModifiable) inventory4).setStackInSlot(i, null);
				}
			}
			
			for (int i = inventory5.getSlots() - 1; i >= 0; --i)
			{
				if (inventory5.getStackInSlot(i) != null)
				{
					Block.spawnAsEntity(worldIn, pos, inventory5.getStackInSlot(i));
					((IItemHandlerModifiable) inventory5).setStackInSlot(i, null);
				}
			}
		}
	}
}
