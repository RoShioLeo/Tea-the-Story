package cateam.teastory.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LitTeaDryingPan extends Block
{
	private  final boolean isBurning;
	public LitTeaDryingPan()
	{
		super(Material.iron);
		this.setHardness(3.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
		this.setTickRandomly(true);
        this.setStepSound(soundTypeMetal);
        this.setLightLevel(0.875F);
        this.setUnlocalizedName("lit_tea_drying_pan");
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.T1));
        this.isBurning = true;
	}

	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	public boolean isFullCube()
    {
        return false;
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
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
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
	
	public  boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		if (meta == 1)
		{
			if(playerIn.getHeldItem() != null)
			{
				if((playerIn.getHeldItem().getItem() == ItemLoader.tea_leaf) && (playerIn.getHeldItem().stackSize >=8))
				{
				    worldIn.setBlockState(pos, this.getStateFromMeta(2));
				    if (!playerIn.capabilities.isCreativeMode)
                    {
	            	    playerIn.getHeldItem().stackSize = playerIn.getHeldItem().stackSize - 8;
                    }
				    return true;
				}
			}
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new ChatComponentTranslation("teastory.tea_drying_pan.message.2"));
			}
			return true;
		}
		else if(meta == 2)
		{
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new ChatComponentTranslation("teastory.tea_drying_pan.message.3"));
			}
			return true;
		}
		else if((meta >= 3) && (meta <= 5))
		{
			worldIn.setBlockState(pos, this.getStateFromMeta(6));
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new ChatComponentTranslation("teastory.tea_drying_pan.message.4"));
			}
			return true;
		}
		else if(meta == 6)
		{
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new ChatComponentTranslation("teastory.tea_drying_pan.message.5"));
			}
			return true;
		}
		else if((meta >= 7) && (meta <= 9))
		{
			worldIn.setBlockState(pos, this.getStateFromMeta(10));
			if(worldIn.isRemote)
			{
			    playerIn.addChatMessage(new ChatComponentTranslation("teastory.tea_drying_pan.message.6"));
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
				playerIn.triggerAchievement(AchievementLoader.burntLeaf);
			    playerIn.worldObj.spawnEntityInWorld(new EntityItem(playerIn.worldObj, playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
            	        new ItemStack(ItemLoader.burnt_tea, 8)));
			}
			else playerIn.addChatMessage(new ChatComponentTranslation("teastory.tea_drying_pan.message.7"));
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
	
	@SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(BlockLoader.tea_drying_pan);
    }
	
	@Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, TYPE);
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
