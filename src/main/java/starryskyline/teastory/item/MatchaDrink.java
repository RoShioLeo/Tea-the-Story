package starryskyline.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
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

public class MatchaDrink extends ItemTeaDrink
{
	public MatchaDrink()
	{
        super("matcha_drink");
    }
	
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(I18n.translateToLocal("teastory.tooltip.matcha_drink"));
    }

    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(!world.isRemote)
        {
        	entityplayer.addStat(AchievementLoader.matchaDrink);
        	int tier = itemstack.getItemDamage();
        	if (tier == 0)
        	{
        		entityplayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, Math.max(0, ConfigLoader.TeaDrink_Time), 0)); 
        		if(world.rand.nextFloat() < 0.5F)
        		{
        			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionPhotosynthesis, Math.max(0, ConfigLoader.TeaDrink_Time) * 2, 0));
        		}
        	}
        	else
        	{
        		entityplayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, (int)(Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10), tier - 1));
        		if(world.rand.nextFloat() < 0.5F)
        		{
        			entityplayer.addPotionEffect(new PotionEffect(PotionLoader.PotionPhotosynthesis, Math.max(0, ConfigLoader.TeaDrink_Time) * (10 + tier) / 10 * 2, tier - 1));
        		}
        	}
        }
    }
    
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	((EntityPlayer) entityLiving).addStat(AchievementLoader.matchaDrink);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
