package cateam.teastory.block;

import java.util.List;
import java.util.Random;

import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.item.ItemCup;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.item.ItemTeaDrink;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FullKettle extends Kettle
{
	private int drink;
	public FullKettle(String name, int drink)
    {
		super(name, Material.rock);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, EnumType.C1));
		this.drink = drink;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    	if (worldIn.isRemote)
        {
            return true;
        }
    	else
        {
    		if (playerIn.getHeldItem() != null)
    		{
    			if (playerIn.getHeldItem().getItem() instanceof ItemCup)
    			{
    				if (!playerIn.capabilities.isCreativeMode)
                    {
    					playerIn.getHeldItem().stackSize--;
        		    }
    				int meta = playerIn.getHeldItem().getItemDamage();
        	    	if (!playerIn.inventory.addItemStackToInventory(new ItemStack(this.getDrink(this), 1, meta)))
                    {
                        playerIn.getEntityWorld().spawnEntityInWorld(new EntityItem(playerIn.getEntityWorld(), playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
                            	new ItemStack(this.getDrink(this), 1, meta)));
                    }
                	else if (playerIn instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                    }
        	    	int meta2 = getMetaFromState(worldIn.getBlockState(pos));
    	    		if ((meta2 >> 2) == 3)
    	    		{
    	    			worldIn.setBlockState(pos, BlockLoader.empty_kettle.getStateFromMeta(meta2 & 3));
    	    		}
    	    		else 
    	    		{
    	    	    	worldIn.setBlockState(pos, this.getStateFromMeta(meta2 + 4));
    	    		}
        	    	return true;
    			}
    			else return false;
    		}
    		else return false;
        }
    }
	
	public ItemTeaDrink getDrink(FullKettle kettle)
	{
		switch(kettle.drink)
		{
		    case 1:
		    	return ItemLoader.green_tea;
		    case 2:
		    	return ItemLoader.matcha_drink;
		    case 3:
		    	return ItemLoader.black_tea;
		    default:
		    	return ItemLoader.burnt_green_tea;
		}
	}
	
	@Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, TYPE);
    }
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getHorizontal(meta & 3);
        EnumType cc = EnumType.values()[meta >> 2];
        return this.getDefaultState().withProperty(FACING, facing).withProperty(TYPE, cc);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
    	int facing = state.getValue(FACING).getHorizontalIndex();
        int cc = state.getValue(TYPE).ordinal() << 2;
        return facing | cc;
    }
    
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState origin = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
        return origin.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
    	Item.getItemFromBlock(this).setMaxDamage(16);
        return (state.getValue(TYPE).ordinal() << 2);
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		itemIn.setMaxDamage(16);
	    list.add(new ItemStack(itemIn, 1, 0));
	}
	
    public static String getSpecialName(ItemStack stack)
    {
    	switch(stack.getItemDamage() >> 2)
    	{
    	    case 0:
    		    return ".4";
    	    case 1:
    		    return ".3";
		    case 2:
		    	return ".2";
		    default:
		    	return ".1";
    	}
    }
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", FullKettle.EnumType.class);
	public enum EnumType implements IStringSerializable
    {
		C1("0"),
	    C2("1"),
		C3("2"),
		C4("3");

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

