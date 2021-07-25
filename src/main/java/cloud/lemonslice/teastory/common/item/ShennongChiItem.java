package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.teastory.TeaStory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ShennongChiItem extends SwordItem
{
    public static final IItemTier HEAVENLY_WEAPON = new HeavenlyItemTier();

    public ShennongChiItem()
    {
        super(HEAVENLY_WEAPON, 3, -1.0F, new Properties().group(TeaStory.GROUP_CORE).isImmuneToFire());
        this.setRegistryName("shennong_chi");
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if (!worldIn.isRemote)
        {
            playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 0));
        }
        playerIn.getHeldItem(handIn).damageItem(1, playerIn, player -> player.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
    }

    public static class HeavenlyItemTier implements IItemTier
    {

        @Override
        public int getMaxUses()
        {
            return 2048;
        }

        @Override
        public float getEfficiency()
        {
            return 10.0F;
        }

        @Override
        public float getAttackDamage()
        {
            return 2.0F;
        }

        @Override
        public int getHarvestLevel()
        {
            return 0;
        }

        @Override
        public int getEnchantability()
        {
            return 20;
        }

        @Override
        public Ingredient getRepairMaterial()
        {
            return Ingredient.EMPTY;
        }
    }
}
