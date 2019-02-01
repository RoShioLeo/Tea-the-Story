package roito.teastory.common;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.TeaStory;
import roito.teastory.api.capability.IDailyDrink;
import roito.teastory.block.BlockRegister;
import roito.teastory.block.StrawBlanket;
import roito.teastory.capability.CapabilityDailyDrink;
import roito.teastory.config.ConfigMain;
import roito.teastory.helper.NonNullListHelper;
import roito.teastory.item.ItemRegister;
import roito.teastory.potion.PotionRegister;

import java.util.List;

public class EventRegister
{
	public static final EventBus EVENT_BUS = new EventBus();

	public EventRegister()
	{
		MinecraftForge.EVENT_BUS.register(this);
		EventRegister.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onDrops(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		if (!event.getWorld().isRemote)
		{
			Block theblock = event.getState().getBlock();
			if (theblock instanceof BlockOldLeaf && event.getState().getValue(BlockOldLeaf.VARIANT) == BlockPlanks.EnumType.OAK)
			{
				if (event.getState().getValue(PropertyBool.create("decayable")).booleanValue())
				{
					int rand = event.getWorld().rand.nextInt(1000);
					if (rand <= ConfigMain.general.teaSeedsDropChance - 1)
					{
						EntityItem entityitem = new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemRegister.tea_seeds, 1));
						event.getWorld().spawnEntity(entityitem);
					}
					int rand2 = event.getWorld().rand.nextInt(1000);
					if (rand2 <= ConfigMain.general.lemonDropChance - 1)
					{
						EntityItem entityitem = new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemRegister.lemon, 1));
						event.getWorld().spawnEntity(entityitem);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.getEntityLiving().isServerWorld())
		{
			PotionEffect effect1 = event.getEntityLiving().getActivePotionEffect(PotionRegister.PotionAgility);
			if (effect1 != null)
			{
				if (event.getEntityLiving().getRNG().nextFloat() < ((effect1.getAmplifier() + 1) * 0.15F + 0.05F))
				{
					event.setCanceled(true);
				}
			}
			PotionEffect effect2 = event.getEntityLiving().getActivePotionEffect(PotionRegister.PotionDefence);
			if (effect2 != null)
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event)
	{
		if (event.getEntityLiving().isServerWorld())
		{
			PotionEffect effect = event.getEntityPlayer().getActivePotionEffect(PotionRegister.PotionLifeDrain);
			if (effect != null)
			{
				int level = effect.getAmplifier() + 1;
				float r = event.getEntityPlayer().getRNG().nextFloat();
				float health = event.getEntityPlayer().getHealth();
				if (health < event.getEntityPlayer().getMaxHealth() && r <= level * 0.2F)
				{
					event.getEntityPlayer().heal(4.0F - 6.0F / (level + 1.0F));
				}
			}
			PotionEffect effect2 = event.getEntityPlayer().getActivePotionEffect(PotionRegister.PotionDefence);
			if (effect2 != null)
			{
				event.setCanceled(true);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerLogged(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (ConfigMain.general.info)
		{
			event.player.sendMessage(new TextComponentTranslation("teastory.info.welcome", "\u00a7a" + TeaStory.VERSION));
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(TeaStory.MODID))
		{
			ConfigManager.sync(TeaStory.MODID, Config.Type.INSTANCE);
		}
	}

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.world;
		List<EntityItem> itemList = world.getEntitiesWithinAABB(EntityItem.class, player.getEntityBoundingBox().expand(4.0F, 2.0F, 4.0F).expand(-4.0F, -2.0F, -4.0F));

		for (EntityItem entityItem : itemList)
		{
			if (OreDictionary.containsMatch(false, ConfigMain.general.useOreDictionaryWhenWashingRice ? OreDictionary.getOres("cropRice") : NonNullListHelper.createNonNullList(new ItemStack(ItemRegister.xian_rice)), entityItem.getItem()) && world.isMaterialInBB(entityItem.getEntityBoundingBox(), Material.WATER))
			{
				entityItem.setItem(new ItemStack(ItemRegister.washed_rice, entityItem.getItem().getCount()));
			}
		}
	}

	@SubscribeEvent
	public void onItemUse(RightClickBlock event)
	{
		boolean flag = false;
		if (!event.getItemStack().isEmpty())
		{
			if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockFarmland)
			{
				if (event.getItemStack().getItem() instanceof ItemSpade)
				{
					flag = true;
				}
				else
				{
					for (int i = 0; i < ConfigMain.general.spadeList.length; i++)
					{
						if (Item.getByNameOrId(ConfigMain.general.spadeList[i]).equals(event.getItemStack().getItem()))
						{
							flag = true;
							break;
						}
					}
				}
			}
			else if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockGrassPath)
			{
				if (event.getItemStack().getItem() instanceof ItemHoe)
				{
					flag = true;
				}
				else
				{
					for (int i = 0; i < ConfigMain.general.hoeList.length; i++)
					{
						if (Item.getByNameOrId(ConfigMain.general.hoeList[i]).equals(event.getItemStack().getItem()))
						{
							flag = true;
							break;
						}
					}
				}
			}
			if (flag)
			{
				event.getWorld().setBlockState(event.getPos(), BlockRegister.field.getDefaultState());
				event.getItemStack().damageItem(1, event.getEntityPlayer());
				event.getEntityPlayer().playSound(SoundEvents.ITEM_HOE_TILL, 1.0F, 1.0F);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerSetSpawn(PlayerSetSpawnEvent event)
	{
		World world = event.getEntityPlayer().getEntityWorld();

		if (event.getNewSpawn() != null)
		{
			Block block = world.getBlockState(event.getNewSpawn()).getBlock();

			if (!world.isRemote && (block instanceof StrawBlanket))
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event)
	{
		if (!event.getEntityPlayer().world.isRemote)
		{
			PotionEffect effect = event.getEntityPlayer().getActivePotionEffect(PotionRegister.PotionExcitement);
			if (effect != null)
			{
				event.setResult(SleepResult.OTHER_PROBLEM);
				event.getEntityPlayer().sendMessage(new TextComponentTranslation("teastory.message.bed.excited"));
			}
		}
	}

	@SubscribeEvent
	public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer)
		{
			ICapabilitySerializable<NBTTagCompound> provider = new CapabilityDailyDrink.ProviderPlayer();
			event.addCapability(new ResourceLocation(TeaStory.MODID, "daily_drink"), provider);
		}
	}

	@SubscribeEvent
	public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
	{
		Capability<IDailyDrink> capability = CapabilityRegister.dailyDrink;
		Capability.IStorage<IDailyDrink> storage = capability.getStorage();

		if (event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null))
		{
			NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
			storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
		}
	}
}
