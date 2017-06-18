package cateam.teastory.common;

import cateam.teastory.TeaStory;
import cateam.teastory.achievement.AchievementLoader;
import cateam.teastory.block.BlockLoader;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.potion.PotionLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionRange;
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
    public void onDrops(net.minecraftforge.event.world.BlockEvent.BreakEvent event)
    {
    	if(!event.world.isRemote)
    	{
            Block theblock = event.state.getBlock();
            if(theblock == Blocks.leaves || theblock == Blocks.leaves2 || theblock instanceof BlockLeavesBase)
            {
                BlockLeaves leaves = (BlockLeaves)theblock;
                if(((Boolean)event.state.getValue(PropertyBool.create("decayable"))).booleanValue())
                {
                	int rand = event.world.rand.nextInt(200);
                	if(rand == 0)
                	{
                        EntityItem entityitem = new EntityItem(event.world, (double)event.pos.getX(), (double)event.pos.getY(), (double)event.pos.getZ(), new ItemStack(ItemLoader.tea_seeds, 1));
                        event.world.spawnEntityInWorld(entityitem);
                	}
                	else if(rand >= 190)
                	{
                		EntityItem entityitem = new EntityItem(event.world, (double)event.pos.getX(), (double)event.pos.getY(), (double)event.pos.getZ(), new ItemStack(ItemLoader.broken_tea, 1));
                        event.world.spawnEntityInWorld(entityitem);
                	}
                }
            }
    	}
    	else return;
    }
    
    @SubscribeEvent
    public void onDrops2(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
    {
        if(!event.world.isRemote)
        {
            Block theblock = event.state.getBlock();
            if(theblock == Blocks.leaves || theblock == Blocks.leaves2 || theblock instanceof BlockLeavesBase)
            {
                BlockLeaves leaves = (BlockLeaves)theblock;
                if(((Boolean)event.state.getValue(PropertyBool.create("decayable"))).booleanValue())
                {
                	int rand = event.world.rand.nextInt(200);
                	if(rand == 0)
                	{
                        EntityItem entityitem = new EntityItem(event.world, (double)event.pos.getX(), (double)event.pos.getY(), (double)event.pos.getZ(), new ItemStack(ItemLoader.tea_seeds, 1));
                        event.world.spawnEntityInWorld(entityitem);
                	}
                	else if(rand >=196)
                	{
                		EntityItem entityitem = new EntityItem(event.world, (double)event.pos.getX(), (double)event.pos.getY(), (double)event.pos.getZ(), new ItemStack(ItemLoader.broken_tea, 1));
                        event.world.spawnEntityInWorld(entityitem);
                	}
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event)
    {
        if (!event.entityLiving.worldObj.isRemote)
        {
        	PotionEffect effect1 = event.entityLiving.getActivePotionEffect(PotionLoader.PotionAgility);
            if (effect1 != null)
            {
                if (event.entityLiving.getRNG().nextFloat() < ((effect1.getAmplifier() + 1) * 0.15F + 0.05F))
                {
                    event.ammount = 0;
                }
            }
            /*else
            {
            	PotionEffect effect2 = event.entityLiving.getActivePotionEffect(PotionLoader.PotionCalmness);
                if (effect2 != null)
                {
                    if (event.entityLiving.getRNG().nextFloat() < (effect2.getAmplifier() + 1) * 0.1F)
                    {
                        event.ammount /= 2;
                    }
                }
            }*/
        }
    }
    
    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event)
    {
    	if (!event.entityLiving.worldObj.isRemote)
    	{
        	PotionEffect effect = event.entityPlayer.getActivePotionEffect(PotionLoader.PotionLifeDrain);
        	if (effect != null)
            {
        		int level = effect.getAmplifier() + 1;
            	float r = event.entityPlayer.getRNG().nextFloat();
            	float health = event.entityPlayer.getHealth();
                if (health < event.entityPlayer.getMaxHealth() && r <= level*0.2F)
                {
                	event.entityPlayer.heal(4.0F - 6.0F/(level + 1.0F));
                }
            }
    	}
    }
    
    @SubscribeEvent
    public void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent event)
    {
        if (event.crafting.getItem() == ItemLoader.tea_leaf)
        {
            event.player.triggerAchievement(AchievementLoader.teaLeaf);
        }
        else if((event.crafting.getItem() == ItemLoader.cup) && event.crafting.getItemDamage() == 0)
        {
        	event.player.triggerAchievement(AchievementLoader.woodenCup);
        }
        else if((event.crafting.getItem() == ItemLoader.cup) && event.crafting.getItemDamage() == 1)
        {
        	event.player.triggerAchievement(AchievementLoader.stoneCup);
        }
        else if((event.crafting.getItem() == ItemLoader.cup) && event.crafting.getItemDamage() == 2)
        {
        	event.player.triggerAchievement(AchievementLoader.glassCup);
        }
    }
    
    @SubscribeEvent
    public void onPlayerItemSmelted(PlayerEvent.ItemSmeltedEvent event)
    {
        if(event.smelting.getItem() == ItemLoader.burnt_tea)
        {
            event.player.triggerAchievement(AchievementLoader.burntLeaf);
        }
        else if(event.smelting.getItem() == new ItemStack(BlockLoader.empty_kettle, 1, 0).getItem() && event.smelting.getItemDamage() == 0)
        {
            event.player.triggerAchievement(AchievementLoader.kettle);
        }
        else if((event.smelting.getItem() == ItemLoader.cup) && event.smelting.getItemDamage() == 3)
        {
        	event.player.triggerAchievement(AchievementLoader.porcelainCup);
        }
    }
    
    @SubscribeEvent
    public void onPlayerItemPickedup(PlayerEvent.ItemPickupEvent event)
    {
    	if(event.pickedUp.getEntityItem().getItem() == ItemLoader.tea_seeds)
    	{
    		event.player.triggerAchievement(AchievementLoader.teaSeeds);
    	}
    }
    
    @SubscribeEvent
    public void onPlayerLogged(PlayerEvent.PlayerLoggedInEvent event)
    {
    	if(ConfigLoader.info)
    	{
    	    event.player.addChatMessage(new ChatComponentTranslation("teastory.info.welcome.1", "\u00a7a" + TeaStory.VERSION));
    	}
    }
}
