package cateam.teastory.item;

import java.util.List;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.ConfigLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.potion.PotionLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class MatchaDrink extends ItemTeaDrink
{
	public MatchaDrink()
	{
        super("matcha_drink");
    }

	@Override
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(!world.isRemote)
        {
        	int tier = itemstack.getItemDamage();
        	addPotion(tier, world, entityplayer);
        }
    }
    
    public static void addPotion(int tier, World world, EntityPlayer entityplayer)
	{
    	if (tier == 0)
    	{
    		entityplayer.addPotionEffect(new PotionEffect(Potion.absorption.id, Math.max(0, ConfigLoader.TeaDrink_Time), 0)); 
    		if(world.rand.nextFloat() < 0.5F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionPhotosynthesis.id, Math.max(0, ConfigLoader.TeaDrink_Time) * 2, 0));
    		}
    	}
    	else
    	{
    		entityplayer.addPotionEffect(new PotionEffect(Potion.absorption.id, (int)(Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10), tier - 1));
    		if(world.rand.nextFloat() < 0.5F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionPhotosynthesis.id, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
    		}
    	}
	}
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
		playerIn.triggerAchievement(AchievementLoader.matchaDrink);
        return super.onItemUseFinish(stack, worldIn, playerIn);
    }
    
    public Block getBlock(int meta)
	{
		switch(meta)
		{
		    case 1:
		    	return BlockLoader.matchadrink_stone_cup;
		    case 2:
		    	return BlockLoader.matchadrink_glass_cup;
		    case 3:
		    	return BlockLoader.matchadrink_porcelain_cup;
		    default:
		    	return BlockLoader.matchadrink_wood_cup;
		}
	}
	
    @Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (playerIn.isSneaking())
		{
			Block drinkblock = getBlock(stack.getItemDamage());
			IBlockState iblockstate = worldIn.getBlockState(pos);
	        Block block = iblockstate.getBlock();

	        if (!block.isReplaceable(worldIn, pos))
	        {
	            pos = pos.offset(side);
	        }

	        if (stack.stackSize == 0)
	        {
	            return false;
	        }
	        else if (!playerIn.canPlayerEdit(pos, side, stack))
	        {
	            return false;
	        }
	        else if (worldIn.canBlockBePlaced(drinkblock, pos, false, side, (Entity)null, stack))
	        {
	            int i = this.getMetadata(stack.getMetadata());
	            IBlockState iblockstate1 = drinkblock.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, i, playerIn);

	            if (placeBlockAt(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ, iblockstate1))
	            {
	                worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), drinkblock.stepSound.getPlaceSound(), (drinkblock.stepSound.getVolume() + 1.0F) / 2.0F, drinkblock.stepSound.getFrequency() * 0.8F);
	                --stack.stackSize;
	            }

	            return true;
	        }
	        else
	        {
	            return false;
	        }
		}
		else return false;
	}
	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState, 3)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == getBlock(stack.getItemDamage()))
        {
            ItemBlock.setTileEntityNBT(world, player, pos, stack);
            getBlock(stack.getItemDamage()).onBlockPlacedBy(world, pos, state, player, stack);
        }

        return true;
    }
}
