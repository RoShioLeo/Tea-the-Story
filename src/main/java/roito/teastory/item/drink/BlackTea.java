package roito.teastory.item.drink;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.item.ItemRegister;
import roito.teastory.potion.PotionRegister;

public class BlackTea extends ItemTeaDrink
{
    public BlackTea()
    {
        super("black_tea");
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
        if (Potion.getPotionFromResourceLocation(ConfigMain.drink.blackTeaDrink_Effect) != null)
        {
        	entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(ConfigMain.drink.blackTeaDrink_Effect), ConfigMain.drink.blackTeaDrink_Time / (tier + 1), tier));
        }
        entityplayer.addPotionEffect(new PotionEffect(PotionRegister.PotionExcitement, ConfigMain.drink.blackTeaDrink_Time / (tier + 1), 0));
    }

    @Override
    public Block getBlock(int meta)
    {
        switch (meta)
        {
            case 2:
                return BlockRegister.blacktea_stone_cup;
            case 3:
                return BlockRegister.blacktea_glass_cup;
            case 4:
                return BlockRegister.blacktea_porcelain_cup;
            case 5:
                return BlockRegister.blacktea_zisha_cup;
            default:
                return BlockRegister.blacktea_wood_cup;
        }
    }
}
