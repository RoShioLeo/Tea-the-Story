package roito.teastory.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.common.AchievementLoader;
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
        this.speed = theToolMaterial.getDamageVsEntity() + 0.5F;
        this.setUnlocalizedName("sickle");
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
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.sickle"));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", theToolMaterial.getDamageVsEntity(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.speed - 4.0F, 0));
        }

        return multimap;
    }
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		return harvestCrops(stack, playerIn, worldIn, pos, 0);
    }
    
    public static EnumActionResult harvestCrops(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, int time)
    {
    	if (worldIn.getChunkFromBlockCoords(pos).isLoaded())
    	{
    		Block block = worldIn.getBlockState(pos).getBlock();
    		if (block instanceof IGrowable)
    		{
    			if(!((IGrowable) block).canGrow(worldIn, pos, worldIn.getBlockState(pos), worldIn.isRemote))
    			{
    				worldIn.destroyBlock(pos, true);
    				if (stack.getItemDamage() < stack.getMaxDamage())
    				{
    					if (time < 8)
    					{
    						stack.setItemDamage(stack.getItemDamage() + 1);
    						harvestCrops(stack, playerIn, worldIn, pos.east(), time + 1);
        					harvestCrops(stack, playerIn, worldIn, pos.north(), time + 1);
        					harvestCrops(stack, playerIn, worldIn, pos.west(), time + 1);
        					harvestCrops(stack, playerIn, worldIn, pos.south(), time + 1);
    					}
    				}
    				else stack.stackSize--;
    				playerIn.addStat(AchievementLoader.sickle);
    				return EnumActionResult.SUCCESS;
    			}
    		}
    	}
    	return EnumActionResult.FAIL;
    }
}
