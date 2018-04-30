package roito.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;

public class ItemSickle extends Item
{
	private final float speed;
    protected Item.ToolMaterial theToolMaterial = ToolMaterial.IRON;
    
	protected ItemSickle()
	{
        this.maxStackSize = 1;
        this.setMaxDamage(500);
        this.setCreativeTab(CreativeTabsLoader.tabRice);
        this.speed = theToolMaterial.getAttackDamage() + 0.5F;
        this.setUnlocalizedName("sickle");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "sickle"));
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(2, attacker);
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.sickle"));
		}
		else
			tooltip.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", theToolMaterial.getAttackDamage(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.speed - 4.0F, 0));
        }

        return multimap;
    }
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		return harvestCrops(playerIn.getHeldItem(hand), playerIn, worldIn, pos, 0);
    }
    
    public static EnumActionResult harvestCrops(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, int time)
    {
    	if (worldIn.getChunkFromBlockCoords(pos).isLoaded())
    	{
    		Block block = worldIn.getBlockState(pos).getBlock();
    		if (block instanceof BlockCrops)
    		{
    			if(((BlockCrops) block).isMaxAge(worldIn.getBlockState(pos)))
    			{
    				worldIn.destroyBlock(pos, true);
    				if (stack.getItemDamage() < stack.getMaxDamage() && time < 8)
    				{
    					stack.setItemDamage(stack.getItemDamage() + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.east(), time + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.north(), time + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.west(), time + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.south(), time + 1);
    				}
    				else stack.shrink(1);
    				return EnumActionResult.SUCCESS;
    			}
    		}
    	}
    	return EnumActionResult.FAIL;
    }
}
