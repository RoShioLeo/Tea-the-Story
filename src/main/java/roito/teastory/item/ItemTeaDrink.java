package roito.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.common.CreativeTabsLoader;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.IDrink;
import toughasnails.api.thirst.ThirstHelper;

@Optional.Interface(iface = "toughasnails.api.thirst.IDrink", modid = "toughasnails")
public class ItemTeaDrink extends ItemFood implements IDrink
{
	public ItemTeaDrink(String name) {
		super(1, false);
		this.setCreativeTab(CreativeTabsLoader.tabDrink);
		this.setAlwaysEdible();
		this.setMaxStackSize(4);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(name);
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 2));
		subItems.add(new ItemStack(itemIn, 1, 3));
		subItems.add(new ItemStack(itemIn, 1, 4));
		subItems.add(new ItemStack(itemIn, 1, 5));
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean b)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.cup"));
		}
		else
			list.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}


	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name;
		switch(stack.getItemDamage())
		{
		case 2:
			name = "stone";
			break;
		case 3:
			name = "glass";
			break;
		case 4:
			name = "porcelain";
			break;
		case 5:
			name = "zisha";
			break;
		default:
			name = "wood";
		}
		return super.getUnlocalizedName() + "." + name;
	}
	
	@Nullable
	public Block getBlock(int meta)
	{
		return null;
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (playerIn.isSneaking())
		{
			Block drinkblock = getBlock(stack.getItemDamage());
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(worldIn, pos))
			{
				pos = pos.offset(facing);
			}

			if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.canBlockBePlaced(drinkblock, pos, false, facing, (Entity)null, stack))
			{
				int i = this.getMetadata(stack.getMetadata());
				IBlockState iblockstate1 = drinkblock.getDefaultState();

				if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1))
				{
					SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
					worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					--stack.stackSize;
				}

				return EnumActionResult.SUCCESS;
			}
			else
			{
				return EnumActionResult.FAIL;
			}
		}
		else
		{
			return EnumActionResult.PASS;
		}
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
	{
		if (!world.setBlockState(pos, newState, 3)) return false;

		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == getBlock(stack.getItemDamage()))
		{
			ItemBlock.setTileEntityNBT(world, player, pos, stack);
			getBlock(stack.getItemDamage()).onBlockPlacedBy(world, pos, state, player, stack);
		}

		return true;
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
            
            if(!worldIn.isRemote && Loader.isModLoaded("ToughAsNails"))
            {
            	drink(entityLiving);
            	changeTemperature(entityLiving);
            }
        }
        
		if (stack.stackSize > 1)
		{
			ItemHandlerHelper.giveItemToPlayer((EntityPlayer) entityLiving, new ItemStack(ItemLoader.cup, 1, stack.getItemDamage()));
			--stack.stackSize;
			return stack;
		}
		return new ItemStack(ItemLoader.cup, 1, stack.getItemDamage());
	}
	
	@Optional.Method(modid = "ToughAsNails")
	public void changeTemperature(EntityLivingBase entity)
	{
		EntityPlayer player = (EntityPlayer)entity;
		ITemperature temperature = TemperatureHelper.getTemperatureData(player);
		
		if (temperature.getTemperature().getRawValue() <= 10)
		{
			temperature.setTemperature(new Temperature(temperature.getTemperature().getRawValue()+1));
		}
		else if (temperature.getTemperature().getRawValue() >= 14)
		{
			temperature.setTemperature(new Temperature(temperature.getTemperature().getRawValue()-1));
		}
	}
	
	@Optional.Method(modid = "ToughAsNails")
	public void drink(EntityLivingBase entity)
	{
		EntityPlayer player = (EntityPlayer)entity;
        IThirst thirst = ThirstHelper.getThirstData(player);
        
        thirst.addStats(this.getThirst(), this.getHydration());
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStackIn)
	{
		return EnumAction.DRINK;
	}
	
	@Override
	@Optional.Method(modid = "ToughAsNails")
	public int getThirst()
	{
		return 8;
	}

	@Override
	@Optional.Method(modid = "ToughAsNails")
	public float getHydration()
	{
		return 0.6F;
	}

	@Override
	@Optional.Method(modid = "ToughAsNails")
	public float getPoisonChance()
	{
		return 0.0F;
	}
}
