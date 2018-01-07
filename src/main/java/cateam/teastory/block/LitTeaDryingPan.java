package cateam.teastory.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.tileentity.TileEntityTeaDryingPan;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class LitTeaDryingPan extends BlockContainer
{
	protected static final AxisAlignedBB TEADRYINGPAN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 6);
	
	public LitTeaDryingPan()
	{
		super(Material.IRON);
		this.setHardness(3.0F);
        this.setSoundType(SoundType.METAL);
        this.setLightLevel(0.875F);
        this.setUnlocalizedName("lit_tea_drying_pan");
        this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0));
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
        return new TileEntityTeaDryingPan();
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
	    drops.add(new ItemStack(BlockLoader.tea_drying_pan, 1));
	    int meta = getMetaFromState(world.getBlockState(pos));
	    if((meta >= 1) && (meta <= 4))
		{
	    	drops.add(new ItemStack(ItemLoader.tea_leaf, 8));
		}
		else if(meta == 5)
		{
			drops.add(new ItemStack(ItemLoader.dried_tea, 8));
		}
		else if(meta == 6)
		{
			drops.add(new ItemStack(ItemLoader.burnt_tea, 8));
		}
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
		int meta = getMetaFromState(worldIn.getBlockState(pos));
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        if(meta != 6)
        {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.08D, 0.0D, new int[0]);
        }
        else worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.1D, 0.0D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.01D, 0.0D, 0.0D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.01D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, -0.01D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), -0.01D, 0.0D, 0.0D, new int[0]);
    }
	
    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int meta = getMetaFromState(state);
		TileEntity te = worldIn.getTileEntity(pos);
		int seconds = 60;
		if(te instanceof TileEntityTeaDryingPan)
	    {
	    	seconds = ((TileEntityTeaDryingPan) te).getRemainingTime() / 20;
	    }
		if (meta == 0)
		{
			if(heldItem != null)
			{
				if((heldItem.getItem() == ItemLoader.tea_leaf) && (heldItem.stackSize >=8))
				{
				    worldIn.setBlockState(pos, this.getStateFromMeta(1));
				    if (!playerIn.capabilities.isCreativeMode)
                    {
	            	    heldItem.stackSize = heldItem.stackSize - 8;
                    }
				    return true;
				}
			}
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.2"));
			}
			return true;
		}
		else if (meta == 1)
		{
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.3", seconds));
			}
			return true;
		}
		else if (meta == 2)
		{
			worldIn.setBlockState(pos, this.getStateFromMeta(3));
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.4", seconds));
			}
			return true;
		}
		else if (meta == 3)
		{
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.5", seconds));
			}
			return true;
		}
		else if (meta == 4)
		{
			worldIn.setBlockState(pos, getStateFromMeta(5));
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.6"));
			}
			return true;
		}
		else if (meta == 5)
		{
			if(!worldIn.isRemote)
			{
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.dried_tea, 8));
			}
			worldIn.setBlockState(pos, BlockLoader.tea_drying_pan.getStateFromMeta(0));
			worldIn.removeTileEntity(pos);
			return true;
		}
		else
		{
			if(!worldIn.isRemote)
			{
				playerIn.addStat(AchievementLoader.burntLeaf);
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.burnt_tea, 8));
			}
			else playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.7"));
			worldIn.setBlockState(pos, BlockLoader.tea_drying_pan.getStateFromMeta(0));
			worldIn.removeTileEntity(pos);
			return true;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
	    list.add(new ItemStack(BlockLoader.tea_drying_pan, 1));
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(BlockLoader.tea_drying_pan);
    }
	
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STEP);
    }
	
	protected PropertyInteger getStepProperty()
    {
        return STEP;
    }
	
	@Override
    public IBlockState getStateFromMeta(int step)
    {
    	return this.getDefaultState().withProperty(this.getStepProperty(), Integer.valueOf(step));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
    	return ((Integer)state.getValue(this.getStepProperty())).intValue();
    }
	
	public static String getSpecialName(ItemStack stack)
    {
		int meta = stack.getItemDamage();
		return "." + String.valueOf(meta);
    }
	
	public static void setState(int meta, World worldIn, BlockPos pos)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        worldIn.setBlockState(pos, BlockLoader.lit_tea_drying_pan.getStateFromMeta(meta));
        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
	}
}
