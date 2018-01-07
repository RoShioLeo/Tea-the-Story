package cateam.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cateam.teastory.common.ConfigLoader;
import cateam.teastory.creativetab.CreativeTabsLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ShennongRuler extends ItemSword
{
	public static final Item.ToolMaterial SHENNONGTOOL = EnumHelper.addToolMaterial("SHENNONGTOOL", 3, 768, 8.0F, 1.0F, 10).setRepairItem(new ItemStack(ItemLoader.dried_tea, 1));
	public ShennongRuler()
    {
		super(SHENNONGTOOL);
		this.setCreativeTab(CreativeTabsLoader.tabteastory);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("shennong_ruler");
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) 
        {
        	list.add(TextFormatting.WHITE +(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shennong_ruler")));
        }
        else
        	list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
    }
	
	@Override
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
		    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
        }
        return false;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(!worldIn.isRemote)
        {
        	playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0)); 
        }
		if(!playerIn.capabilities.isCreativeMode)
		{
		    itemStackIn.setItemDamage(itemStackIn.getItemDamage() + 5);
		    if (itemStackIn.getItemDamage() > 768)
		    {
		    	--itemStackIn.stackSize;
		    }
		}
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
}
