package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MilkTea extends ItemTeaDrink
{
    public MilkTea()
    {
        super("milk_tea");
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (!world.isRemote)
        {
            int tier = itemstack.getItemDamage() / 2;
            addPotion(tier, world, entityplayer);
        }
    }

    public static void addPotion(int tier, World world, EntityPlayer entityplayer)
    {
        if (ConfigMain.general.useTeaResidueAsBoneMeal)
        {
            ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemRegister.tea_residue, 1, 1));
        }
        Collection<PotionEffect> effectList = entityplayer.getActivePotionEffects();
        List<PotionEffect> list = new ArrayList<>();
        for (PotionEffect effect : effectList)
        {
            if (effect.getPotion().isBadEffect())
            {
                list.add(effect);
            }
        }
        for (PotionEffect effect : list)
        {
            entityplayer.removePotionEffect(effect.getPotion());
        }

        entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(ConfigMain.drink.milkTeaDrink_Effect), ConfigMain.drink.milkTeaDrink_Time / (tier + 1), tier));
        entityplayer.addPotionEffect(new PotionEffect(PotionRegister.PotionExcitement, ConfigMain.drink.milkTeaDrink_Time / (tier + 1), 0));
    }

    @Override
    public Block getBlock(int meta)
    {
        switch (meta)
        {
            case 2:
                return BlockRegister.milktea_stone_cup;
            case 3:
                return BlockRegister.milktea_glass_cup;
            case 4:
                return BlockRegister.milktea_porcelain_cup;
            case 5:
                return BlockRegister.milktea_zisha_cup;
            default:
                return BlockRegister.milktea_wood_cup;
        }
    }
}
