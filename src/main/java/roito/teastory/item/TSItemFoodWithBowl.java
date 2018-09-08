package roito.teastory.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

import javax.annotation.Nullable;

public class TSItemFoodWithBowl extends ItemFood
{
    public TSItemFoodWithBowl(String name, int amount, float saturation)
    {
        super(amount, saturation, false);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabsRegister.tabRice);
        this.setUnlocalizedName(name);
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, name));
    }

    @Override
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }
}
