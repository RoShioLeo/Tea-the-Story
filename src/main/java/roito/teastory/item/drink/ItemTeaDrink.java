package roito.teastory.item.drink;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.ItemHandlerHelper;
import org.lwjgl.input.Keyboard;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.item.ItemRegister;
import toughasnails.api.stat.capability.ITemperature;
import toughasnails.api.stat.capability.IThirst;
import toughasnails.api.temperature.Temperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.IDrink;
import toughasnails.api.thirst.ThirstHelper;

import javax.annotation.Nullable;
import java.util.List;

@Optional.Interface(iface = "toughasnails.api.thirst.IDrink", modid = "toughasnails")
public class ItemTeaDrink extends ItemFood implements IDrink
{
	public ItemTeaDrink(String name)
	{
		super(1, false);
		this.setCreativeTab(CreativeTabsRegister.tabDrink);
		this.setAlwaysEdible();
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setTranslationKey(name);
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, name));
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (this.isInCreativeTab(tab))
		{
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.cup"));
		}
		else
		{
			tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
		}
	}


	@Override
	public String getTranslationKey(ItemStack stack)
	{
		String name;
		switch (stack.getItemDamage())
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
		return super.getTranslationKey() + "_" + name;
	}

	@Nullable
	public Block getBlock(int meta)
	{
		return null;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (player.isSneaking())
		{
			Block drinkblock = getBlock(player.getHeldItem(hand).getItemDamage());
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(worldIn, pos))
			{
				pos = pos.offset(facing);
			}

			if (player.getHeldItem(hand).getCount() != 0 && player.canPlayerEdit(pos, facing, player.getHeldItem(hand)) && worldIn.mayPlace(drinkblock, pos, false, facing, null))
			{
				IBlockState iblockstate1 = drinkblock.getDefaultState();

				if (placeBlockAt(player.getHeldItem(hand), player, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1))
				{
					SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
					worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					player.getHeldItem(hand).shrink(1);
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
		if (!world.setBlockState(pos, newState, 3))
		{
			return false;
		}

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
			ItemHandlerHelper.giveItemToPlayer((EntityPlayer) entityLiving, new ItemStack(ItemRegister.cup, 1, stack.getItemDamage()));

			if (Loader.isModLoaded("toughasnails"))
			{
				changeTemperature((EntityPlayer) entityLiving);
				drink((EntityPlayer) entityLiving);
			}
		}

		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}

	@Optional.Method(modid = "toughasnails")
	public void changeTemperature(EntityPlayer player)
	{
		ITemperature temperature = TemperatureHelper.getTemperatureData(player);

		if (temperature.getTemperature().getRawValue() <= 10)
		{
			temperature.setTemperature(new Temperature(temperature.getTemperature().getRawValue() + 1));
		}
		else if (temperature.getTemperature().getRawValue() >= 14)
		{
			temperature.setTemperature(new Temperature(temperature.getTemperature().getRawValue() - 1));
		}
	}

	@Optional.Method(modid = "toughasnails")
	public void drink(EntityPlayer player)
	{
		IThirst thirst = ThirstHelper.getThirstData(player);

		thirst.addStats(this.getThirst(), this.getHydration());
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStackIn)
	{
		return EnumAction.DRINK;
	}

	@Override
	@Optional.Method(modid = "toughasnails")
	public int getThirst()
	{
		return 8;
	}

	@Override
	@Optional.Method(modid = "toughasnails")
	public float getHydration()
	{
		return 0.6F;
	}

	@Override
	@Optional.Method(modid = "toughasnails")
	public float getPoisonChance()
	{
		return 0.0F;
	}
}
