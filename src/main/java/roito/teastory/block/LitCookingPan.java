package roito.teastory.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;
import roito.teastory.item.ItemRegister;
import roito.teastory.tileentity.TileEntityCookingPan;

public class LitCookingPan extends BlockContainer
{
	protected static final AxisAlignedBB TEADRYINGPAN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	
	protected LitCookingPan()
	{
		super(Material.IRON);
		this.setHardness(3.0F);
		this.setSoundType(SoundType.METAL);
		this.setLightLevel(0.875F);
		this.setUnlocalizedName("lit_cooking_pan");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "lit_cooking_pan"));
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
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCookingPan();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune)
	{
		ArrayList drops = new ArrayList();
		drops.add(new ItemStack(BlockRegister.tea_drying_pan, 1));
		drops.add(new ItemStack(ItemRegister.washed_rice, 16));
		return drops;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return TEADRYINGPAN_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		double d0 = pos.getX();
		double d1 = pos.getY();
		double d2 = pos.getZ();
		worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.08D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.01D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.01D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, -0.01D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), -0.01D, 0.0D, 0.0D, new int[0]);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote)
		{
			TileEntity te = worldIn.getTileEntity(pos);
			int seconds = 60;
			if(te != null && te instanceof TileEntityCookingPan)
			{
				seconds = ((TileEntityCookingPan) te).getRemainingTime() / 20;
			}
			playerIn.sendMessage(new TextComponentTranslation("teastory.message.cooking_pan.rice", seconds));
		}
		return true;
	}
}
