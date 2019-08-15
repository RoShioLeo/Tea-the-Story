package roito.teastory.item.drink;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.api.drink.DailyDrink;
import roito.teastory.block.BlockRegister;
import roito.teastory.config.ConfigMain;
import roito.teastory.helper.DrinkingHelper;
import roito.teastory.item.ItemRegister;
import roito.teastory.potion.PotionRegister;

public class LemonTea extends ItemTeaDrink
{
    public LemonTea()
    {
        super("lemon_tea");
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
            tier += dailyDrink.getLevel();
            int time = ConfigMain.drink.lemonTeaDrink_Time;
            time *= 1.0F + dailyDrink.getTime();

            int addedTimePercent = (int) (dailyDrink.getTime() * 100);
            entityplayer.sendMessage(new TextComponentTranslation("teastory.message.daily_drinking.normal", dailyDrink.getInstantDay(), addedTimePercent + "%", dailyDrink.getLevel()));

            if (ConfigMain.general.useTeaResidueAsBoneMeal)
            {
                ItemHandlerHelper.giveItemToPlayer(entityplayer, new ItemStack(ItemRegister.tea_residue, 1, 1));
            }
            if (entityplayer.isBurning())
            {
                entityplayer.extinguish();
            }
            for (String potion : ConfigMain.drink.lemonTeaDrink_Effect)
            {
                if (Potion.getPotionFromResourceLocation(potion) != null)
                {
                    entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(potion), time / (tier + 1), tier));
                }
            }
            entityplayer.addPotionEffect(new PotionEffect(PotionRegister.PotionExcitement, time / (tier + 1), 0));
            for (int x = -1 - tier; x <= 1 + tier; x++)
            {
                for (int y = 0; y <= 2 + 2 * tier; y++)
                {
                    for (int z = -1 - tier; z <= 1 + tier; z++)
                    {
                        if (entityplayer.canPlayerEdit(entityplayer.getPosition().add(x, y, z).down(), EnumFacing.UP, null))
                        {
                            if (world.getBlockState(entityplayer.getPosition().add(x, y, z)).getBlock() instanceof BlockFire)
                            {
                                world.setBlockToAir(entityplayer.getPosition().add(x, y, z));
                                world.playSound(null, entityplayer.getPosition().add(x, y, z), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Block getBlock(int meta)
    {
        switch (meta)
        {
            case 2:
                return BlockRegister.lemontea_stone_cup;
            case 3:
                return BlockRegister.lemontea_glass_cup;
            case 4:
                return BlockRegister.lemontea_porcelain_cup;
            case 5:
                return BlockRegister.lemontea_zisha_cup;
            default:
                return BlockRegister.lemontea_wood_cup;
        }
    }
}
