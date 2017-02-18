package starryskyline.teastory.item;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import starryskyline.teastory.achievement.AchievementLoader;
import starryskyline.teastory.common.ConfigLoader;
import starryskyline.teastory.creativetab.CreativeTabsLoader;
import starryskyline.teastory.potion.PotionLoader;

public class GreenTea extends ItemTeaDrink
{
    public GreenTea()
    {
        super("green_tea");
    }
    
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(I18n.translateToLocal("teastory.tooltip.green_tea"));
    }

    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(!world.isRemote)
        {
        	int tier = itemstack.getItemDamage();
        	if (tier == 0)
        	{
        		entityplayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, Math.max(0, ConfigLoader.TeaDrink_Time), 0));
        		if(world.rand.nextFloat() < 0.4F)
        		{
        			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionAgility, Math.max(0, ConfigLoader.TeaDrink_Time) * 2, 0));
        		}
        	}
        	else
        	{
        		entityplayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, (int)(Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10), tier - 1)); 
        		if(world.rand.nextFloat() < 0.4F)
        		{
        			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionAgility, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
        		}
        	}
        }
        if (entityplayer.getRNG().nextInt() > 0.5F)
        {
        	if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(ItemLoader.tea_residue, 1, 0)))
            {
                world.spawnEntityInWorld(new EntityItem(world, entityplayer.posX + 0.5D, entityplayer.posY + 1.5D, entityplayer.posZ + 0.5D, 
                		new ItemStack(ItemLoader.tea_residue, 1, 0)));
            }
        }
    }
    
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	((EntityPlayer) entityLiving).addStat(AchievementLoader.greenTea);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}