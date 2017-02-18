package starryskyline.teastory.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
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
import starryskyline.teastory.achievement.AchievementLoader;
import starryskyline.teastory.creativetab.CreativeTabsLoader;
import starryskyline.teastory.item.ItemLoader;

public class LitTeaDryingPan extends Block
{
	protected static final AxisAlignedBB TEADRYINGPAN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	private  final boolean isBurning;
	public LitTeaDryingPan()
	{
		super(Material.IRON);
		this.setHardness(3.0F);
		this.setTickRandomly(true);
        this.setSoundType(SoundType.METAL);
        this.setLightLevel(0.875F);
        this.setUnlocalizedName("lit_tea_drying_pan");
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.T1));
        this.isBurning = true;
	}

	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return TEADRYINGPAN_AABB;
    }
    
    @Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune)
	{
    	ArrayList drops = new ArrayList();
	    drops.add(new ItemStack(BlockLoader.tea_drying_pan, 1));
		return drops;
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if(!worldIn.isRemote)
		{
		    int meta = getMetaFromState(worldIn.getBlockState(pos));
		    if (((meta >= 2) && (meta <= 11)) && (worldIn.rand.nextFloat() < 0.5F))
		    {
			    if((meta != 5) && (meta != 9))
			    {
				    worldIn.setBlockState(pos, this.getStateFromMeta(meta + 1));
			    }
			    else worldIn.setBlockState(pos, this.getStateFromMeta(12));
	    	}
		}
    }
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		if(meta >=1)
		{
            double d0 = (double)pos.getX();
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ();
            if(meta != 12)
            {
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.08D, 0.0D, new int[0]);
            }
            else worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.1D, 0.0D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.01D, 0.0D, 0.0D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.01D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, -0.01D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), -0.01D, 0.0D, 0.0D, new int[0]);
		}
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		if (meta == 1)
		{
			if(heldItem != null)
			{
				if((heldItem.getItem() == ItemLoader.tea_leaf) && (heldItem.stackSize >=8))
				{
				    worldIn.setBlockState(pos, this.getStateFromMeta(2));
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
		else if(meta == 2)
		{
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.3"));
			}
			return true;
		}
		else if((meta >= 3) && (meta <= 5))
		{
			worldIn.setBlockState(pos, this.getStateFromMeta(6));
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.4"));
			}
			return true;
		}
		else if(meta == 6)
		{
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.5"));
			}
			return true;
		}
		else if((meta >= 7) && (meta <= 9))
		{
			worldIn.setBlockState(pos, this.getStateFromMeta(10));
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.6"));
			}
			return true;
		}
		else if((meta == 10) || (meta == 11))
		{
			if(!worldIn.isRemote)
			{
			    playerIn.worldObj.spawnEntityInWorld(new EntityItem(playerIn.worldObj, playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
            	        new ItemStack(ItemLoader.dried_tea, 8)));
			}
			worldIn.setBlockState(pos, BlockLoader.tea_drying_pan.getStateFromMeta(0));
			return true;
		}
		else if(meta == 12)
		{
			if(!worldIn.isRemote)
			{
				playerIn.addStat(AchievementLoader.burntLeaf);
			    playerIn.worldObj.spawnEntityInWorld(new EntityItem(playerIn.worldObj, playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
            	        new ItemStack(ItemLoader.burnt_tea, 8)));
			}
			else playerIn.addChatMessage(new TextComponentTranslation("teastory.tea_drying_pan.message.7"));
			worldIn.setBlockState(pos, BlockLoader.tea_drying_pan.getStateFromMeta(0));
			return true;
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
	    list.add(new ItemStack(BlockLoader.tea_drying_pan, 1));
	}
	
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, TYPE);
    }
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
		if ((meta>12) || (meta<=0)) meta = 1;
        EnumType t = EnumType.values()[meta];
        return this.getDefaultState().withProperty(TYPE, t);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int t = state.getValue(TYPE).ordinal();
        return t;
    }
	
	public static String getSpecialName(ItemStack stack)
    {
		return "." + String.valueOf(stack.getItemDamage());
    }
	
	public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", LitTeaDryingPan.EnumType.class);
	public enum EnumType implements IStringSerializable
    {
		T0("0"),
	    T1("1"),
		T2("2"),
		T3("3"),
		T4("4"),
		T5("5"),
		T6("6"),
		T7("7"),
		T8("8"),
		T9("9"),
		T10("10"),
		T11("11"),
		T12("12");

	    private String name;
	    
	    private EnumType(String name)
        {
	        this.name = name;
	    }
	    
	    @Override
	    public String getName()
        {
	        return name;
	    }
	    
	    @Override
	    public String toString()
        {
	        return getName();
	    }
	}
}
