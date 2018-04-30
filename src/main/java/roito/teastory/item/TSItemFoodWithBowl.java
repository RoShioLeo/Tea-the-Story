package roito.teastory.item;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import roito.teastory.common.CreativeTabsLoader;

public class TSItemFoodWithBowl extends ItemFood
{
	public TSItemFoodWithBowl(String name, int amount, float saturation)
	{
		super(amount, saturation, false);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabsLoader.tabRice);
		this.setUnlocalizedName(name);
	}
	
	@Override
	@Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }
}
