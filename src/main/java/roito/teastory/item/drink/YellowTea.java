package roito.teastory.item.drink;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.api.drink.DailyDrink;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.helper.DrinkingHelper;
import roito.teastory.item.ItemRegister;
import roito.teastory.potion.PotionRegister;

public class YellowTea extends ItemTeaDrink
{
    public YellowTea()
    {
        super("yellow_tea");
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        int tier = itemstack.getItemDamage() / 2;
        addPotion(tier, world, entityplayer);
    }

    public static void addPotion(int tier, World world, EntityPlayer entityplayer)
    {
        if (!world.isRemote)
        {
            DailyDrink dailyDrink = DrinkingHelper.getLevelAndTimeImprovement(world, entityplayer);
            tier = dailyDrink.getLevel();
            int time = ConfigMain.drink.yellowTeaDrink_Time;
            time *= 1.0F + dailyDrink.getTime() + tier * 0.2F;

            int addedTimePercent = (int) (dailyDrink.getTime() + tier * 0.2F * 100);
            entityplayer.sendMessage(new TextComponentTranslation("teastory.message.daily_drinking.time_only", dailyDrink.getInstantDay(), addedTimePercent + "%"));

            if (ConfigMain.general.useTeaResidueAsBoneMeal)
            {
                ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemRegister.tea_residue, 1, 2));
            }

            for (String potion : ConfigMain.drink.yellowTeaDrink_Effect)
            {
                if (Potion.getPotionFromResourceLocation(potion) != null)
                {
                    entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(potion), time, 0));
                }
                entityplayer.addPotionEffect(new PotionEffect(PotionRegister.PotionExcitement, time, 0));
            }
        }
    }

    @Override
    public Block getBlock(int meta)
    {
        switch (meta)
        {
            case 2:
                return BlockRegister.yellowtea_stone_cup;
            case 3:
                return BlockRegister.yellowtea_glass_cup;
            case 4:
                return BlockRegister.yellowtea_porcelain_cup;
            case 5:
                return BlockRegister.yellowtea_zisha_cup;
            default:
                return BlockRegister.yellowtea_wood_cup;
        }
    }
}