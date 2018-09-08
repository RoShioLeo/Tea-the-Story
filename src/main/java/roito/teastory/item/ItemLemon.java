package roito.teastory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.config.ConfigMain;

public class ItemLemon extends ItemFood
{

    public ItemLemon()
    {
        super(1, false);
        this.setUnlocalizedName("lemon");
        this.setCreativeTab(CreativeTabsRegister.tabDrink);
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "lemon"));
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (!world.isRemote && ConfigMain.others.lemon)
        {
            entityplayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 160, 1));
        }
    }
}
