package cateam.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.common.ConfigLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.potion.PotionLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class BlackTea extends ItemTeaDrink
{
	public BlackTea()
	{
		super("black_tea");
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
    		entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, Math.max(0, ConfigLoader.TeaDrink_Time), 0)); 
    		if(world.rand.nextFloat() < 0.2F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionLifeDrain, Math.max(0, ConfigLoader.TeaDrink_Time) * 2, 0));
    		}
    		else if(world.rand.nextFloat() >= 0.8F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, Math.max(0, ConfigLoader.TeaDrink_Time) * 2, 0));
    		}
    	}
    	else
    	{
    		entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (int)(Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10), tier - 1)); 
    		if(world.rand.nextFloat() < 0.2F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionLifeDrain, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
    		}
    		else if(world.rand.nextFloat() >= 0.8F)
    		{
    			entityplayer.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
    		}
    	}
        if (entityplayer.getRNG().nextInt() > 0.5F)
        {
    	    if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(ItemLoader.tea_residue, 1, 1)))
            {
                world.spawnEntityInWorld(new EntityItem(world, entityplayer.posX + 0.5D, entityplayer.posY + 1.5D, entityplayer.posZ + 0.5D, 
            		    new ItemStack(ItemLoader.tea_residue, 1, 1)));
            }
        }
	}
	
	@Override
	@Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	((EntityPlayer) entityLiving).addStat(AchievementLoader.blackTea);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
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
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if (playerIn.isSneaking())
		{
		    Block drinkblock = getBlock(stack.getItemDamage());
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (!block.isReplaceable(worldIn, pos))
            {
                pos = pos.offset(facing);
            }

            if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.canBlockBePlaced(drinkblock, pos, false, facing, (Entity)null, stack))
            {
                int i = this.getMetadata(stack.getMetadata());
                IBlockState iblockstate1 = drinkblock.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, i, playerIn, stack);

                if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1))
                {
                    SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
                    worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    --stack.stackSize;
                }

                return EnumActionResult.SUCCESS;
            }
            else
            {
                return EnumActionResult.FAIL;
            }
		}
        else
        {
            return EnumActionResult.FAIL;
        }
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
