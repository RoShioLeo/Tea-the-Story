package cateam.teastory.block;

import java.util.ArrayList;

import cateam.teastory.item.BlackTea;
import cateam.teastory.item.BurntGreenTea;
import cateam.teastory.item.GreenTea;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.item.ItemTeaDrink;
import cateam.teastory.item.MatchaDrink;
import cateam.teastory.tileentity.TileEntityTeaDrink;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    public Item getItem(World worldIn, BlockPos pos)
    {
		switch (drink)
        {
            case 1:
            	return ItemLoader.green_tea;
            case 2:
	        	return ItemLoader.matcha_drink;
	        case 3:
	        	return ItemLoader.black_tea;
	        case 4:
	            return ItemLoader.hot_water;
	        default:
	        	return ItemLoader.burnt_green_tea;
        }
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
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
	        case 4:
	        	this.teaDrink = ItemLoader.hot_water;
	        	break;
	        default:
	        	this.teaDrink = ItemLoader.burnt_green_tea;
        }
		if (playerIn.isSneaking())
		{
	    	if (!playerIn.inventory.addItemStackToInventory(new ItemStack(teaDrink, 1, meta)))
            {
                playerIn.getEntityWorld().spawnEntityInWorld(new EntityItem(playerIn.getEntityWorld(), playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
                    	new ItemStack(teaDrink, 1, meta)));
            }
            else if (playerIn instanceof EntityPlayerMP)
            {
                ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
            }
	        worldIn.setBlockState(pos, Blocks.air.getDefaultState());
	        worldIn.removeTileEntity(pos);
	        return true;
		}
		else
		{
			if((playerIn.getHeldItem() != null) && (this.teaDrink == ItemLoader.hot_water))
			{
				if((playerIn.getHeldItem().getItem() == ItemLoader.matcha) && playerIn.getHeldItem().stackSize >= 3)
				{
					switch (meta)
					{
					    case 1:
					    	worldIn.setBlockState(pos, BlockLoader.matchadrink_stone_cup.getDefaultState());
					    	break;
				        case 2:
				        	worldIn.setBlockState(pos, BlockLoader.matchadrink_glass_cup.getDefaultState());
				        	break;
				        case 3:
				        	worldIn.setBlockState(pos, BlockLoader.matchadrink_porcelain_cup.getDefaultState());
				        	break;
				        default:
				        	worldIn.setBlockState(pos, BlockLoader.matchadrink_wood_cup.getDefaultState());
				        	break;
					}
	                if (!playerIn.capabilities.isCreativeMode)
	                {
	                	playerIn.getHeldItem().stackSize = playerIn.getHeldItem().stackSize - 3;
	                }
	                if (playerIn instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                    }
	                return true;
				}
				else if (playerIn.getHeldItem().getItem() == ItemLoader.tea_bag)
				{
					if (playerIn.getHeldItem().getItemDamage() == 0)
					{
						switch (meta)
						{
						    case 1:
						    	worldIn.setBlockState(pos, BlockLoader.greentea_stone_cup.getDefaultState());
						    	break;
					        case 2:
					        	worldIn.setBlockState(pos, BlockLoader.greentea_glass_cup.getDefaultState());
					        	break;
					        case 3:
					        	worldIn.setBlockState(pos, BlockLoader.greentea_porcelain_cup.getDefaultState());
					        	break;
					        default:
					        	worldIn.setBlockState(pos, BlockLoader.greentea_wood_cup.getDefaultState());
					        	break;
						}
						if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ItemLoader.tea_residue, 1, 0)))
			            {
			                 worldIn.spawnEntityInWorld(new EntityItem(worldIn, playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
			                     new ItemStack(ItemLoader.tea_residue, 1, 0)));
			            }
						if (!playerIn.capabilities.isCreativeMode)
		                {
		                	playerIn.getHeldItem().stackSize--;
		                }
						if (playerIn instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                        }
						
					}
					else
					{
						switch (meta)
						{
						    case 1:
						    	worldIn.setBlockState(pos, BlockLoader.blacktea_stone_cup.getDefaultState());
						    	break;
					        case 2:
					        	worldIn.setBlockState(pos, BlockLoader.blacktea_glass_cup.getDefaultState());
					        	break;
					        case 3:
					        	worldIn.setBlockState(pos, BlockLoader.blacktea_porcelain_cup.getDefaultState());
					        	break;
					        default:
					        	worldIn.setBlockState(pos, BlockLoader.blacktea_wood_cup.getDefaultState());
					        	break;
						}
						if (!playerIn.capabilities.isCreativeMode)
		                {
		                	playerIn.getHeldItem().stackSize--;
		                }
						if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ItemLoader.tea_residue, 1, 1)))
			            {
			                 worldIn.spawnEntityInWorld(new EntityItem(worldIn, playerIn.posX + 0.5D, playerIn.posY + 1.5D, playerIn.posZ + 0.5D, 
			                     new ItemStack(ItemLoader.tea_residue, 1, 1)));
			            }
					}
	                return true;
				}
			}
			if(worldIn.isRemote)
			{
				return true;
			}
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
            case 4:
            	drops.add(new ItemStack(ItemLoader.hot_water, 1, meta));
            	break;
            default:
            	drops.add(new ItemStack(ItemLoader.burnt_green_tea, 1, meta));
        }
	    return drops;
	}
}
