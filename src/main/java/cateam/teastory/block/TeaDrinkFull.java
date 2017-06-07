package cateam.teastory.block;

import cateam.teastory.item.BlackTea;
import cateam.teastory.item.BurntGreenTea;
import cateam.teastory.item.GreenTea;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.item.ItemTeaDrink;
import cateam.teastory.item.MatchaDrink;
import net.minecraft.block.Block;
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
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TeaDrinkFull extends TeaDrink
{
	public int drink;
	public ItemTeaDrink teaDrink;
	
	public TeaDrinkFull(float hardness, String name, Material materialIn, SoundType soundType, int drink, int level)
	{
		super(hardness, name, materialIn, soundType, level);
        this.drink = drink;
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
	        default:
	        	this.teaDrink = ItemLoader.burnt_green_tea;
        }
		if (worldIn.isRemote)
        {
			return true;
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
	        return true;
		}
		else
		{
			switch (drink)
	        {
	            case 1:
	            	GreenTea.addPotion(meta, worldIn, playerIn);
	            	break;
		        case 2:
		        	MatchaDrink.addPotion(meta, worldIn, playerIn);
		        	break;
		        case 3:
		        	BlackTea.addPotion(meta, worldIn, playerIn);
		        	break;
		        default:
		        	BurntGreenTea.addPotion(meta, worldIn, playerIn);
	        }
			switch (meta)
	        {
	            case 1:
	            	worldIn.setBlockState(pos, BlockLoader.stone_cup.getDefaultState());
	            	break;
		        case 2:
		        	worldIn.setBlockState(pos, BlockLoader.glass_cup.getDefaultState());
		        	break;
		        case 3:
		        	worldIn.setBlockState(pos, BlockLoader.porcelain_cup.getDefaultState());
		        	break;
		        default:
		        	worldIn.setBlockState(pos, BlockLoader.wood_cup.getDefaultState());
	        }
			worldIn.playSoundAtEntity(playerIn, "random.burp", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			return true;
		}
    }
}
