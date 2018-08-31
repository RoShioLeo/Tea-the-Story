package roito.teastory.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.potion.PotionRegister;

public class MatchaDrink extends ItemTeaDrink
{
    public MatchaDrink()
    {
        super("matcha_drink");
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (!world.isRemote)
        {
            int tier = itemstack.getItemDamage();
            addPotion(tier, world, entityplayer);
        }
    }

    public static void addPotion(int tier, World world, EntityPlayer entityplayer)
    {
        entityplayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, ConfigMain.drink.matchaDrink_Time / (tier + 1), tier));
        entityplayer.addPotionEffect(new PotionEffect(PotionRegister.PotionExcitement, ConfigMain.drink.matchaDrink_Time / (tier + 1), 0));
    }

    @Override
    public Block getBlock(int meta)
    {
        switch (meta)
        {
            case 2:
                return BlockRegister.matchadrink_stone_cup;
            case 3:
                return BlockRegister.matchadrink_glass_cup;
            case 4:
                return BlockRegister.matchadrink_porcelain_cup;
            case 5:
                return BlockRegister.matchadrink_zisha_cup;
            default:
                return BlockRegister.matchadrink_wood_cup;
        }
    }
}
