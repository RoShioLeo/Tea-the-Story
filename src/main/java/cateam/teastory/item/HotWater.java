package cateam.teastory.item;

import java.util.List;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.ConfigLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.tileentity.TileEntityTeaDrink;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class HotWater extends ItemFood
{
    public HotWater()
    {
        super(0, false);
        this.setCreativeTab(CreativeTabsLoader.tabteastory);
        this.setAlwaysEdible();
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("hot_water");
    }
    
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(StatCollector.translateToLocal("teastory.tooltip.hot_water"));
    }
    
    @Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
	    subItems.add(new ItemStack(itemIn, 1, 0));
	    subItems.add(new ItemStack(itemIn, 1, 1));
	    subItems.add(new ItemStack(itemIn, 1, 2));
	    subItems.add(new ItemStack(itemIn, 1, 3));
	}
    
    @Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		String name;
		switch(stack.getItemDamage())
		{
		    case 1:
		    	name = "stone";
		    	break;
		    case 2:
		    	name = "glass";
		    	break;
		    case 3:
		    	name = "porcelain";
		    	break;
		    default:
		    	name = "wood";
		}
	    return super.getUnlocalizedName() + "." + name;
	}

    @Override
    public void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 0));
    }
  
    @Override
    public EnumAction getItemUseAction(ItemStack itemStackIn)
    {
        return EnumAction.DRINK;
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        super.onItemUseFinish(stack, worldIn, playerIn);
        playerIn.triggerAchievement(AchievementLoader.hotWater);
        return new ItemStack(ItemLoader.cup, 1, stack.getItemDamage());
    }
    
    public Block getBlock(int meta)
	{
		switch(meta)
		{
		    case 1:
		    	return BlockLoader.hotwater_stone_cup;
		    case 2:
		    	return BlockLoader.hotwater_glass_cup;
		    case 3:
		    	return BlockLoader.hotwater_porcelain_cup;
		    default:
		    	return BlockLoader.hotwater_wood_cup;
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
