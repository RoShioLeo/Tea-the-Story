package cateam.teastory.block;

import cateam.teastory.block.Barrel.EnumType;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.item.ItemCup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Kettle extends Block
{
	public Kettle(String name, Material material)
	{
		super(material);
		this.setHardness(2.0F);
		this.setBlockBounds(0.1875F, 0F, 0.1875F, 0.8125F, 0.5625F, 0.8125F);
        this.setStepSound(soundTypePiston);
        this.setUnlocalizedName(name);
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if (worldIn.isRemote)
        {
            return true;
        }
    	else
        {
    		if (playerIn.getHeldItem() == null)
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
    			worldIn.setBlockState(pos, Blocks.air.getDefaultState());
    		}
    		return true;
        }
    }
}
