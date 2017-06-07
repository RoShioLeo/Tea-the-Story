package cateam.teastory.item;

import java.util.List;

import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTeaDrink extends ItemFood
{
	public ItemTeaDrink(String name) {
		super(1, false);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
        this.setAlwaysEdible();
		this.setMaxStackSize(1);
        this.setHasSubtypes(true);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
	    subItems.add(new ItemStack(itemIn, 1, 0));
	    subItems.add(new ItemStack(itemIn, 1, 1));
	    subItems.add(new ItemStack(itemIn, 1, 2));
	    subItems.add(new ItemStack(itemIn, 1, 3));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		String name;
		switch(stack.getItemDamage())
		{
		    case 1:
		    	name = "stone";
		    	break;
		    case 2:
		    	name = "glass";
		    	break;
		    case 3:
		    	name = "porcelain";
		    	break;
		    default:
		    	name = "wood";
		}
	    return super.getUnlocalizedName() + "." + name;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        super.onItemUseFinish(stack, worldIn, playerIn);
        return new ItemStack(ItemLoader.cup, 1, stack.getItemDamage());
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemStackIn)
    {
        return EnumAction.DRINK;
    }
}
