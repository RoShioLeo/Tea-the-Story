package roito.teastory.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

import javax.annotation.Nullable;
import java.util.List;

public class ShennongRuler extends ItemSword
{
    public static final Item.ToolMaterial SHENNONGTOOL = EnumHelper.addToolMaterial("SHENNONGTOOL", 3, 768, 8.0F, 1.0F, 10).setRepairItem(new ItemStack(ItemRegister.dried_tea, 1));

    public ShennongRuler()
    {
        super(SHENNONGTOOL);
        this.setCreativeTab(CreativeTabsRegister.tabTeaStory);
        this.setMaxStackSize(1);
        this.setUnlocalizedName("shennong_ruler");
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "shennong_ruler"));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.shennong_ruler"));
        } else
            tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
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
        if (player.isServerWorld() && entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
        }
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (!worldIn.isRemote)
        {
            playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
        }
        if (!playerIn.capabilities.isCreativeMode)
        {
            playerIn.getHeldItem(hand).setItemDamage(playerIn.getHeldItem(hand).getItemDamage() + 5);
            if (playerIn.getHeldItem(hand).getItemDamage() > 768)
            {
                playerIn.getHeldItem(hand).shrink(1);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
    }
}
