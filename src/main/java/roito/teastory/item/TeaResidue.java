package roito.teastory.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.config.ConfigMain;

import javax.annotation.Nullable;
import java.util.List;

public class TeaResidue extends TSItem
{
    public TeaResidue()
    {
        super("tea_residue", 64, CreativeTabsRegister.tabDrink);
        this.setHasSubtypes(true);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (ConfigMain.general.tooltips)
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
            {
                tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.tea_residue"));
            }
            else
            {
                tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        switch (stack.getItemDamage())
        {
            case 1:
                return super.getTranslationKey() + "_" + "black";
            case 2:
                return super.getTranslationKey() + "_" + "yellow";
            case 3:
                return super.getTranslationKey() + "_" + "white";
            case 4:
                return super.getTranslationKey() + "_" + "oolong";
            case 5:
                return super.getTranslationKey() + "_" + "puer";
            default:
                return super.getTranslationKey() + "_" + "green";
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
            items.add(new ItemStack(this, 1, 2));
            items.add(new ItemStack(this, 1, 3));
            items.add(new ItemStack(this, 1, 4));
            items.add(new ItemStack(this, 1, 5));
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote && ConfigMain.general.useTeaResidueAsBoneMeal && ItemDye.applyBonemeal(playerIn.getHeldItem(hand), worldIn, pos))
        {
            worldIn.playEvent(2005, pos, 0);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
