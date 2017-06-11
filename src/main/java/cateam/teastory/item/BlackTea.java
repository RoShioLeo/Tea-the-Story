package cateam.teastory.item;

import java.util.List;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.ConfigLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.potion.PotionLoader;
import cateam.teastory.tileentity.TileEntityTeaDrink;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlackTea extends ItemTeaDrink
{
	public BlackTea()
	{
		super("black_tea");
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(StatCollector.translateToLocal("teastory.tooltip.black_tea"));
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
    		entityplayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, Math.max(0, ConfigLoader.TeaDrink_Time), 0)); 
    		if(world.rand.nextFloat() < 0.2F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionLifeDrain.id, Math.max(0, ConfigLoader.TeaDrink_Time) * 2, 0));
    		}
    		else if(world.rand.nextFloat() >= 0.8F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(Potion.healthBoost.id, Math.max(0, ConfigLoader.TeaDrink_Time) * 2, 0));
    		}
    	}
    	else
    	{
    		entityplayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, (int)(Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10), tier - 1)); 
    		if(world.rand.nextFloat() < 0.4F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionLifeDrain.id, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
    		}
    		else if(world.rand.nextFloat() >= 0.8F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(Potion.healthBoost.id, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
    		}
    	}
        if (entityplayer.getRNG().nextInt() > 0.5F)
        {
        	if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(ItemLoader.tea_residue, 1, 1)))
            {
                 world.spawnEntityInWorld(new EntityItem(world, entityplayer.posX + 0.5D, entityplayer.posY + 1.5D, entityplayer.posZ + 0.5D, 
                     new ItemStack(ItemLoader.tea_residue, 1, 1)));
            }
        	if (entityplayer instanceof EntityPlayerMP)
            {
                ((EntityPlayerMP)entityplayer).sendContainerToPlayer(entityplayer.inventoryContainer);
            }
	    }
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
		playerIn.triggerAchievement(AchievementLoader.blackTea);
        return super.onItemUseFinish(stack, worldIn, playerIn);
    }
	
	public Block getBlock(int meta)
	{
		switch(meta)
		{
		    case 1:
		    	return BlockLoader.blacktea_stone_cup;
		    case 2:
		    	return BlockLoader.blacktea_glass_cup;
		    case 3:
		    	return BlockLoader.blacktea_porcelain_cup;
		    default:
		    	return BlockLoader.blacktea_wood_cup;
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (playerIn.isSneaking())
		{
			IBlockState s = worldIn.getBlockState(pos);
			Block block = getBlock(stack.getItemDamage());
			BlockPos blockPos = pos.offset(side);
			if(s.getBlock().isReplaceable(worldIn, pos))
				blockPos = pos;
			if(!playerIn.canPlayerEdit(pos, side, null) || !block.canPlaceBlockAt(worldIn, blockPos) || !block.canPlaceBlockOnSide(worldIn, blockPos, side))
			    return false;
			IBlockState state = block.onBlockPlaced(worldIn, blockPos, side, 0, 0, 0, 0, playerIn);
			worldIn.setBlockState(blockPos, state);
			block.onBlockPlacedBy(worldIn, blockPos, state, playerIn, stack);			
		    stack.stackSize--;
			return true;
		}
		else return false;
	}
}
