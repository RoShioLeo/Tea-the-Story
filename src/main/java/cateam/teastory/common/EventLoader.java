package cateam.teastory.common;

import java.util.List;

import cateam.teastory.TeaStory;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.block.StrawBlanket;
import cateam.teastory.config.ConfigMain;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.potion.PotionLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class EventLoader
{
	public static final EventBus EVENT_BUS = new EventBus();

	public EventLoader()
	{
		MinecraftForge.EVENT_BUS.register(this);
		EventLoader.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onDrops(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
	{
		if(!event.getWorld().isRemote)
		{
			Block theblock = event.getState().getBlock();
			if(theblock instanceof BlockLeaves && event.getState().getValue(BlockOldLeaf.VARIANT) == BlockPlanks.EnumType.OAK)
			{
				if(event.getState().getValue(PropertyBool.create("decayable")).booleanValue())
				{
					int rand = event.getWorld().rand.nextInt(250);
					if(rand == 0)
					{
						EntityItem entityitem = new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemLoader.tea_seeds, 1));
						event.getWorld().spawnEntityInWorld(entityitem);
					}
					else if(rand >= 248)
					{
						EntityItem entityitem = new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemLoader.lemon, 1));
						event.getWorld().spawnEntityInWorld(entityitem);
					}
				}
			}
			else if(event.getHarvester() != null && theblock == BlockLoader.half_dried_leaf_block && theblock.getMetaFromState(event.getState()) == 8)
			{
				event.getHarvester().addStat(AchievementLoader.puerTea);
			}
			else if(event.getHarvester() != null && theblock == BlockLoader.rice_plant && theblock.getMetaFromState(event.getState()) == 7)
			{
				event.getHarvester().addStat(AchievementLoader.harvest);
			}
		}
	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (!event.getEntityLiving().worldObj.isRemote)
		{
			PotionEffect effect1 = event.getEntityLiving().getActivePotionEffect(PotionLoader.PotionAgility);
			if (effect1 != null)
			{
				if (event.getEntityLiving().getRNG().nextFloat() < ((effect1.getAmplifier() + 1) * 0.15F + 0.05F))
				{
					event.setCanceled(true);
				}
			}
			PotionEffect effect2 = event.getEntityLiving().getActivePotionEffect(PotionLoader.PotionDefence);
			if (effect2 != null)
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event)
	{
		if (!event.getEntityLiving().worldObj.isRemote)
		{
			PotionEffect effect = event.getEntityPlayer().getActivePotionEffect(PotionLoader.PotionLifeDrain);
			if (effect != null)
			{
				int level = effect.getAmplifier() + 1;
				float r = event.getEntityPlayer().getRNG().nextFloat();
				float health = event.getEntityPlayer().getHealth();
				if (health < event.getEntityPlayer().getMaxHealth() && r <= level*0.2F)
				{
					event.getEntityPlayer().heal(4.0F - 6.0F/(level + 1.0F));
				}
			}
			PotionEffect effect2 = event.getEntityPlayer().getActivePotionEffect(PotionLoader.PotionDefence);
			if (effect2 != null)
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent event)
	{
		if(event.crafting.getItem() == ItemLoader.cup)
		{
			if(event.crafting.getItemDamage() == 0)
			{
				event.player.addStat(AchievementLoader.woodenCup);
			}
			else if(event.crafting.getItemDamage() == 2)
			{
				event.player.addStat(AchievementLoader.stoneCup);
			}
			else if(event.crafting.getItemDamage() == 3)
			{
				event.player.addStat(AchievementLoader.glassCup);
			}
		}
		else if(event.crafting.getItem() == ItemLoader.empty_tea_bag)
		{
			event.player.addStat(AchievementLoader.emptyBag);
		}
		else if(event.crafting.getItem() == ItemLoader.green_tea_bag
				|| event.crafting.getItem() == ItemLoader.black_tea_bag
				|| event.crafting.getItem() == ItemLoader.yellow_tea_bag
				|| event.crafting.getItem() == ItemLoader.white_tea_bag
				|| event.crafting.getItem() == ItemLoader.oolong_tea_bag
				|| event.crafting.getItem() == ItemLoader.puer_tea_bag)
		{
			event.player.addStat(AchievementLoader.teaBag);
		}
	}

	@SubscribeEvent
	public void onPlayerItemSmelted(PlayerEvent.ItemSmeltedEvent event)
	{
		if(event.smelting.getItem() == ItemLoader.cup)
		{
			if(event.smelting.getItemDamage() == 4)
			{
				event.player.addStat(AchievementLoader.porcelainCup);
			}
			else if(event.smelting.getItemDamage() == 5)
			{
				event.player.addStat(AchievementLoader.zishaCup);
			}
		}
		else if(event.smelting.getItem() == ItemLoader.hw_pot_stone
				|| event.smelting.getItem() == ItemLoader.hw_pot_iron
				|| event.smelting.getItem() == ItemLoader.hw_pot_porcelain
				|| event.smelting.getItem() == ItemLoader.hw_pot_zisha)
		{
			event.player.addStat(AchievementLoader.hotWater);
		}
		else if(event.smelting.getItem() == Item.getItemFromBlock(BlockLoader.empty_porcelain_kettle))
		{
			event.player.addStat(AchievementLoader.kettle);
		}
	}

	@SubscribeEvent
	public void onPlayerItemPickedup(PlayerEvent.ItemPickupEvent event)
	{
		if(event.pickedUp.getEntityItem().getItem() == ItemLoader.tea_seeds)
		{
			event.player.addStat(AchievementLoader.teaSeeds);
		}
		else if(event.pickedUp.getEntityItem().getItem() == ItemLoader.rice_seeds)
		{
			event.player.addStat(AchievementLoader.riceSeeds);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerLogged(PlayerEvent.PlayerLoggedInEvent event)
	{
		if(ConfigMain.info)
		{
			event.player.addChatComponentMessage(new TextComponentTranslation("teastory.info.welcome.1", "\u00a7a" + TeaStory.VERSION));
			event.player.addChatComponentMessage(new TextComponentTranslation("teastory.info.welcome.2"));
			ConfigMain.info = false;
		}
	}
	
	@SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
        if (event.getConfigID().equalsIgnoreCase(TeaStory.MODID))
        {
            Configuration config = ConfigMain.config;
            if (config.hasChanged())
            {
                config.save();
            }
            ConfigMain.registerConfig();
        }
    }
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.worldObj;
		List<EntityItem> itemList = world.getEntitiesWithinAABB(EntityItem.class, player.getEntityBoundingBox().expand(4.0F, 4.0F, 4.0F));
		
		for (EntityItem entityItem : itemList)
		{
			if (OreDictionary.containsMatch(false, OreDictionary.getOres("cropRice"), entityItem.getEntityItem()) && world.isMaterialInBB(entityItem.getEntityBoundingBox(), Material.WATER))
			{
				entityItem.setEntityItemStack(new ItemStack(ItemLoader.washed_rice, entityItem.getEntityItem().stackSize));
			}
		}
	}
	
	@SubscribeEvent
    public void onPlayerWakeUp(PlayerWakeUpEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
        World world = player.worldObj;
        BlockPos pos = player.bedLocation;
        IBlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof StrawBlanket)
		{
            ItemStack stack = state.getBlock().getItem(world, pos, state);

            BlockPos pos1 = pos.offset(state.getValue(StrawBlanket.FACING).getOpposite());
            world.setBlockToAir(pos1);
            
            player.addStat(AchievementLoader.strawBlanket);
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
		if (!event.getEntityPlayer().worldObj.isRemote)
		{
			PotionEffect effect = event.getEntityPlayer().getActivePotionEffect(PotionLoader.PotionExcitement);
			if (effect != null)
			{
				event.setResult(SleepResult.OTHER_PROBLEM);
				event.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("teastory.message.bed.excited"));
				event.getEntityPlayer().addStat(AchievementLoader.excitement);
			}
		}
	}
}
