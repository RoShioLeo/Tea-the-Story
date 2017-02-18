package starryskyline.teastory.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starryskyline.teastory.creativetab.CreativeTabsLoader;
import starryskyline.teastory.item.ItemCup;
import starryskyline.teastory.item.ItemLoader;

public class EmptyKettle extends Kettle
{
	public EmptyKettle()
    {
		super("empty_kettle", Material.rock);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WATER, Boolean.FALSE).withProperty(BOILED, Boolean.FALSE));
	} 
	
	@Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, WATER, BOILED);
    }
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getHorizontal(meta & 3);
        Boolean water = Boolean.valueOf((meta & 4) != 0);
        Boolean boiled = false;
        if (water)
        {
            boiled = Boolean.valueOf((meta >> 3) != 0);
        }
        return this.getDefaultState().withProperty(FACING, facing).withProperty(WATER, water).withProperty(BOILED, boiled);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
    	int facing = state.getValue(FACING).getHorizontalIndex();
        int water = state.getValue(WATER).booleanValue() ? 4 : 0;
        int boiled = state.getValue(BOILED).booleanValue() ? 8 : 0;
        return facing | water | boiled;
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
    		int meta = getMetaFromState(state);
    	    if ((meta & 12) == 0)
    		{
    			if (playerIn.getHeldItem() != null)
    			{
    				if (playerIn.getHeldItem().getItem() == Items.water_bucket)
    				{
    					if (!playerIn.capabilities.isCreativeMode)
                        {
                            playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(Items.bucket));
                        }
    					worldIn.setBlockState(pos, BlockLoader.empty_kettle.getStateFromMeta(meta | 4));
    					return true;
    				}
    				else return false;
    			}
    			else return false;
    		}
    		else if((meta & 12) == 12)
    		{
    			if (playerIn.getHeldItem() != null)
    			{
    				if (playerIn.getHeldItem().getItem() instanceof ItemCup)
    				{
    					if (!playerIn.capabilities.isCreativeMode)
                        {
    						playerIn.getHeldItem().stackSize--;
        		    	}
    					int meta2 = playerIn.getHeldItem().getItemDamage();
        	    		if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ItemLoader.hot_water, 1, meta2)))
                        {
                            playerIn.getEntityWorld().spawnEntityInWorld(new EntityItem(playerIn.getEntityWorld(), playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
                            		new ItemStack(ItemLoader.hot_water, 1, meta2)));
                        }
                		else if (playerIn instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                        }
        	    		return true;
    				}
    				else return false;
    			}
    			else return false;
    		}
    		else return false;
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        list.add(new ItemStack(itemIn, 1, 0));
        list.add(new ItemStack(itemIn, 1, 4));
        list.add(new ItemStack(itemIn, 1, 12));
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
    	if (state.getValue(WATER).booleanValue())
    	{
    		if (state.getValue(BOILED).booleanValue())
    		{
    			return 12;
    		}
    		else return 4;
    	}
    	else return 0;
    }
    
    public static String getSpecialName(ItemStack stack)
    {
    	switch(stack.getItemDamage() >> 2)
    	{
    	    case 1:
    		    return ".water";
		    case 3:
		    	return ".boiled";
		    default:
		    	return "";
    	}
    }
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool WATER = PropertyBool.create("water");
	public static final PropertyBool BOILED = PropertyBool.create("boiled");
}
