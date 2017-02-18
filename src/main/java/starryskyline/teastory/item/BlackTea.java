package starryskyline.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import starryskyline.teastory.achievement.AchievementLoader;
import starryskyline.teastory.common.ConfigLoader;
import starryskyline.teastory.creativetab.CreativeTabsLoader;
import starryskyline.teastory.potion.PotionLoader;

public class BlackTea extends ItemTeaDrink
{
	public BlackTea()
	{
		super("black_tea");
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(I18n.translateToLocal("teastory.tooltip.black_tea"));
    }
	
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(!world.isRemote)
        {
        	int tier = itemstack.getItemDamage();
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
        		if(world.rand.nextFloat() < 0.4F)
        		{
        			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionLifeDrain, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
        		}
        		else if(world.rand.nextFloat() >= 0.8F)
        		{
        			entityplayer.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
        		}
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
	
	@Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	((EntityPlayer) entityLiving).addStat(AchievementLoader.blackTea);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
