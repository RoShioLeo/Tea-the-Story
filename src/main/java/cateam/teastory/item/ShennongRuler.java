package cateam.teastory.item;

import java.util.List;

import cateam.teastory.common.ConfigLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ShennongRuler extends ItemSword
{
	public static final Item.ToolMaterial SHENNONGTOOL = EnumHelper.addToolMaterial("SHENNONGTOOL", 3, 768, 8.0F, 1.0F, 10);
	public ShennongRuler()
    {
		super(SHENNONGTOOL);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("shennong_ruler");
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        list.add(StatCollector.translateToLocal("teastory.tooltip.shennong_ruler"));
    }
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		if(!player.worldObj.isRemote)
        {
		    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
        }
        return false;
    }
	
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
		if(!worldIn.isRemote)
        {
        	playerIn.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0)); 
        }
		if(!playerIn.capabilities.isCreativeMode)
		{
		    itemStackIn.setItemDamage(itemStackIn.getItemDamage() + 5);
		    if (itemStackIn.getItemDamage() > 768)
		    {
		    	--itemStackIn.stackSize;
		    }
		}
        playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }
}
