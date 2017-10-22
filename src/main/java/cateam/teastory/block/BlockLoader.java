package cateam.teastory.block;

import com.google.common.base.Function;

import cateam.teastory.TeaStory;
import cateam.teastory.creativetab.CreativeTabsLoader;
import cateam.teastory.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLoader
{
    public static Block teapan = new Teapan();
    public static Block tea_drying_pan = new TeaDryingPan();
    public static Block lit_tea_drying_pan = new LitTeaDryingPan();
    public static Block tea_stove = new TeaStove(0.0F, "tea_stove", false).setCreativeTab(CreativeTabsLoader.tabteastory);
    public static Block lit_tea_stove = new TeaStove(0.875F, "lit_tea_stove", true);
    public static Block barrel = new Barrel();
    public static Kettle clay_kettle = new ClayKettle();
    public static Kettle empty_kettle = new EmptyKettle();
    public static Kettle burntgreentea_kettle = new FullKettle("burntgreentea_kettle", 0);
    public static Kettle greentea_kettle = new FullKettle("greentea_kettle", 1);
    public static Kettle matcha_kettle = new FullKettle("matcha_kettle", 2);
    public static Kettle blacktea_kettle = new FullKettle("blacktea_kettle", 3);
    public static BlockCrops teaplant = new Teaplant();
    public static TeaDrinkEmpty wood_cup = new TeaDrinkEmpty(1.0F, "wood_cup", Material.WOOD, SoundType.WOOD, 0);
    public static TeaDrinkEmpty stone_cup = new TeaDrinkEmpty(1.3F, "stone_cup", Material.ROCK, SoundType.STONE, 1);
    public static TeaDrinkEmpty glass_cup = new TeaDrinkEmpty(0.8F, "glass_cup", Material.GLASS, SoundType.GLASS, 2);
    public static TeaDrinkEmpty porcelain_cup = new TeaDrinkEmpty(1.5F, "porcelain_cup", Material.ROCK, SoundType.STONE, 3);
    public static TeaDrinkFull burntgreentea_wood_cup = new TeaDrinkFull(1.0F, "burntgreentea_wood_cup", Material.WOOD, SoundType.WOOD, 0, 0);
    public static TeaDrinkFull greentea_wood_cup = new TeaDrinkFull(1.0F, "greentea_wood_cup", Material.WOOD, SoundType.WOOD, 1, 0);
    public static TeaDrinkFull matchadrink_wood_cup = new TeaDrinkFull(1.0F, "matchadrink_wood_cup", Material.WOOD, SoundType.WOOD, 2, 0);
    public static TeaDrinkFull blacktea_wood_cup = new TeaDrinkFull(1.0F, "blacktea_wood_cup", Material.WOOD, SoundType.WOOD, 3, 0);
    public static TeaDrinkFull hotwater_wood_cup = new TeaDrinkFull(1.0F, "hotwater_wood_cup", Material.WOOD, SoundType.WOOD, 4, 0);
    public static TeaDrinkFull burntgreentea_stone_cup = new TeaDrinkFull(1.3F, "burntgreentea_stone_cup", Material.ROCK, SoundType.STONE, 0, 1);
    public static TeaDrinkFull greentea_stone_cup = new TeaDrinkFull(1.3F, "greentea_stone_cup", Material.ROCK, SoundType.STONE, 1, 1);
    public static TeaDrinkFull matchadrink_stone_cup = new TeaDrinkFull(1.3F, "matchadrink_stone_cup", Material.ROCK, SoundType.STONE, 2, 1);
    public static TeaDrinkFull blacktea_stone_cup = new TeaDrinkFull(1.3F, "blacktea_stone_cup", Material.ROCK, SoundType.STONE, 3, 1);
    public static TeaDrinkFull hotwater_stone_cup = new TeaDrinkFull(1.3F, "hotwater_stone_cup", Material.ROCK, SoundType.STONE, 4, 1);
    public static TeaDrinkFull burntgreentea_glass_cup = new TeaDrinkFull(0.8F, "burntgreentea_glass_cup", Material.GLASS, SoundType.GLASS, 0, 2);
    public static TeaDrinkFull greentea_glass_cup = new TeaDrinkFull(0.8F, "greentea_glass_cup", Material.GLASS, SoundType.GLASS, 1, 2);
    public static TeaDrinkFull matchadrink_glass_cup = new TeaDrinkFull(0.8F, "matchadrink_glass_cup", Material.GLASS, SoundType.GLASS, 2, 2);
    public static TeaDrinkFull blacktea_glass_cup = new TeaDrinkFull(0.8F, "blacktea_glass_cup", Material.GLASS, SoundType.GLASS, 3, 2);
    public static TeaDrinkFull hotwater_glass_cup = new TeaDrinkFull(0.8F, "hotwater_glass_cup", Material.GLASS, SoundType.GLASS, 4, 2);
    public static TeaDrinkFull burntgreentea_porcelain_cup = new TeaDrinkFull(1.5F, "burntgreentea_porcelain_cup", Material.ROCK, SoundType.STONE, 0, 3);
    public static TeaDrinkFull greentea_porcelain_cup = new TeaDrinkFull(1.5F, "greentea_porcelain_cup", Material.ROCK, SoundType.STONE, 1, 3);
    public static TeaDrinkFull matchadrink_porcelain_cup = new TeaDrinkFull(1.5F, "matchadrink_porcelain_cup", Material.ROCK, SoundType.STONE, 2, 3);
    public static TeaDrinkFull blacktea_porcelain_cup = new TeaDrinkFull(1.5F, "blacktea_porcelain_cup", Material.ROCK, SoundType.STONE, 3, 3);
    public static TeaDrinkFull hotwater_porcelain_cup = new TeaDrinkFull(1.5F, "hotwater_porcelain_cup", Material.ROCK, SoundType.STONE, 4, 3);

    public BlockLoader(FMLPreInitializationEvent event)
    {
    	register(lit_tea_drying_pan, new ItemMultiTexture(lit_tea_drying_pan, lit_tea_drying_pan, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return LitTeaDryingPan.getSpecialName(input);
            }
        }), "lit_tea_drying_pan");
    	register(tea_drying_pan, "tea_drying_pan");
    	register(tea_stove, "tea_stove");
    	register(lit_tea_stove, "lit_tea_stove");
    	register(teapan, new ItemBlockName(teapan, teapan, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return "." + Teapan.getName(input.getMetadata());
            }
        }), "teapan");
    	register(barrel, new ItemBlockName(barrel, barrel, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return "." + Barrel.getName(input.getMetadata());
            }
        }), "barrel");
    	register(empty_kettle, new ItemBlockEmptyKettle(empty_kettle, empty_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return EmptyKettle.getSpecialName(input);
            }
        }), "empty_kettle");
    	register(burntgreentea_kettle, new ItemBlockFullKettle(burntgreentea_kettle, burntgreentea_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }, 0), "burntgreentea_kettle");
    	register(greentea_kettle, new ItemBlockFullKettle(greentea_kettle, greentea_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }, 1), "greentea_kettle");
    	register(matcha_kettle, new ItemBlockFullKettle(matcha_kettle, matcha_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }, 2), "matcha_kettle");
    	register(blacktea_kettle, new ItemBlockFullKettle(blacktea_kettle, blacktea_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }, 3), "blacktea_kettle");
    	register(clay_kettle, "clay_kettle");
        register(teaplant, "teaplant");
        register(wood_cup, "wood_cup");
    	register(stone_cup, "stone_cup");
    	register(glass_cup, "glass_cup");
    	register(porcelain_cup, "porcelain_cup");
    	register(burntgreentea_wood_cup, "burntgreentea_wood_cup");
    	register(greentea_wood_cup, "greentea_wood_cup");
    	register(matchadrink_wood_cup, "matchadrink_wood_cup");
    	register(blacktea_wood_cup, "blacktea_wood_cup");
    	register(burntgreentea_stone_cup, "burntgreentea_stone_cup");
    	register(greentea_stone_cup, "greentea_stone_cup");
    	register(matchadrink_stone_cup, "matchadrink_stone_cup");
    	register(blacktea_stone_cup, "blacktea_stone_cup");
    	register(burntgreentea_glass_cup, "burntgreentea_glass_cup");
    	register(greentea_glass_cup, "greentea_glass_cup");
    	register(matchadrink_glass_cup, "matchadrink_glass_cup");
    	register(blacktea_glass_cup, "blacktea_glass_cup");
    	register(burntgreentea_porcelain_cup, "burntgreentea_porcelain_cup");
    	register(greentea_porcelain_cup, "greentea_porcelain_cup");
    	register(matchadrink_porcelain_cup, "matchadrink_porcelain_cup");
    	register(blacktea_porcelain_cup, "blacktea_porcelain_cup");
    	register(hotwater_wood_cup, "hotwater_wood_cup");
    	register(hotwater_stone_cup, "hotwater_stone_cup");
    	register(hotwater_glass_cup, "hotwater_glass_cup");
    	register(hotwater_porcelain_cup, "hotwater_porcelain_cup");
    }
    
    public static void preInit()
    {
    	ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.teapan), 
    			new ResourceLocation(TeaStory.MODID, "teapan_empty"), new ResourceLocation(TeaStory.MODID, "teapan_full"), new ResourceLocation(TeaStory.MODID, "teapan_dried"), new ResourceLocation(TeaStory.MODID, "teapan_matcha"), new ResourceLocation(TeaStory.MODID, "teapan_wet"));
    	ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.barrel), 
    			new ResourceLocation(TeaStory.MODID, "barrel_empty"), new ResourceLocation(TeaStory.MODID, "barrel_full"), new ResourceLocation(TeaStory.MODID, "barrel_full2"), new ResourceLocation(TeaStory.MODID, "barrel_fermentation"), new ResourceLocation(TeaStory.MODID, "barrel_blacktea"));
    	ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.lit_tea_drying_pan), 
    			new ResourceLocation(TeaStory.MODID, "tea_drying_pan"), new ResourceLocation(TeaStory.MODID, "tea_drying_pan2"), new ResourceLocation(TeaStory.MODID, "tea_drying_pan3"), new ResourceLocation(TeaStory.MODID, "tea_drying_pan4"), new ResourceLocation(TeaStory.MODID, "tea_drying_pan5"), new ResourceLocation(TeaStory.MODID, "tea_drying_pan6"), new ResourceLocation(TeaStory.MODID, "tea_drying_pan7"));
    	ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockLoader.empty_kettle), 
    			new ResourceLocation(TeaStory.MODID, "empty_kettle"), new ResourceLocation(TeaStory.MODID, "water_kettle"));
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	registerRender(barrel, 0, "barrel_empty");
        registerRender(barrel, 1, "barrel_full");
        registerRender(barrel, 2, "barrel_full2");
        registerRender(barrel, 3, "barrel_fermentation");
        registerRender(barrel, 4, "barrel_blacktea");
        registerRender(teapan, 0, "teapan_empty");
        registerRender(teapan, 1, "teapan_full");
        registerRender(teapan, 2, "teapan_dried");
        registerRender(teapan, 3, "teapan_matcha");
        registerRender(teapan, 4, "teapan_wet");
        registerRender(clay_kettle, 0, "clay_kettle");
        registerRender(empty_kettle, 0, "empty_kettle");
        registerRender(empty_kettle, 4, "water_kettle");
        registerRender(empty_kettle, 12, "water_kettle");
        registerRender(burntgreentea_kettle, 0, "burntgreentea_kettle");
        registerRender(burntgreentea_kettle, 4, "burntgreentea_kettle");
        registerRender(burntgreentea_kettle, 8, "burntgreentea_kettle");
        registerRender(burntgreentea_kettle, 12, "burntgreentea_kettle");
        registerRender(greentea_kettle, 0, "greentea_kettle");
        registerRender(greentea_kettle, 4, "greentea_kettle");
        registerRender(greentea_kettle, 8, "greentea_kettle");
        registerRender(greentea_kettle, 12, "greentea_kettle");
        registerRender(matcha_kettle, 0, "matcha_kettle");
        registerRender(matcha_kettle, 4, "matcha_kettle");
        registerRender(matcha_kettle, 8, "matcha_kettle");
        registerRender(matcha_kettle, 12, "matcha_kettle");
        registerRender(blacktea_kettle, 0, "blacktea_kettle");
        registerRender(blacktea_kettle, 4, "blacktea_kettle");
        registerRender(blacktea_kettle, 8, "blacktea_kettle");
        registerRender(blacktea_kettle, 12, "blacktea_kettle");
        registerRender(tea_drying_pan, 0, "tea_drying_pan");
        registerRender(lit_tea_drying_pan, 1, "tea_drying_pan");
        registerRender(lit_tea_drying_pan, 2, "tea_drying_pan2");
        registerRender(lit_tea_drying_pan, 3, "tea_drying_pan3");
        registerRender(lit_tea_drying_pan, 4, "tea_drying_pan3");
        registerRender(lit_tea_drying_pan, 5, "tea_drying_pan3");
        registerRender(lit_tea_drying_pan, 6, "tea_drying_pan4");
        registerRender(lit_tea_drying_pan, 7, "tea_drying_pan5");
        registerRender(lit_tea_drying_pan, 8, "tea_drying_pan5");
        registerRender(lit_tea_drying_pan, 9, "tea_drying_pan5");
        registerRender(lit_tea_drying_pan, 10, "tea_drying_pan6");
        registerRender(lit_tea_drying_pan, 11, "tea_drying_pan6");
        registerRender(lit_tea_drying_pan, 12, "tea_drying_pan7");
        registerRender(tea_stove, "tea_stove");
        registerRender(lit_tea_stove, "lit_tea_stove");
        registerRender(teaplant, "teaplant");
        registerRender(wood_cup, "wood_cup");
    	registerRender(stone_cup, "stone_cup");
    	registerRender(glass_cup, "glass_cup");
    	registerRender(porcelain_cup, "porcelain_cup");
    	registerRender(burntgreentea_wood_cup, "burntgreentea_wood_cup");
    	registerRender(greentea_wood_cup, "greentea_wood_cup");
    	registerRender(matchadrink_wood_cup, "matchadrink_wood_cup");
    	registerRender(blacktea_wood_cup, "blacktea_wood_cup");
    	registerRender(burntgreentea_stone_cup, "burntgreentea_stone_cup");
    	registerRender(greentea_stone_cup, "greentea_stone_cup");
    	registerRender(matchadrink_stone_cup, "matchadrink_stone_cup");
    	registerRender(blacktea_stone_cup, "blacktea_stone_cup");
    	registerRender(burntgreentea_glass_cup, "burntgreentea_glass_cup");
    	registerRender(greentea_glass_cup, "greentea_glass_cup");
    	registerRender(matchadrink_glass_cup, "matchadrink_glass_cup");
    	registerRender(blacktea_glass_cup, "blacktea_glass_cup");
    	registerRender(burntgreentea_porcelain_cup, "burntgreentea_porcelain_cup");
    	registerRender(greentea_porcelain_cup, "greentea_porcelain_cup");
    	registerRender(matchadrink_porcelain_cup, "matchadrink_porcelain_cup");
    	registerRender(blacktea_porcelain_cup, "blacktea_porcelain_cup");
    	registerRender(hotwater_wood_cup, "hotwater_wood_cup");
    	registerRender(hotwater_stone_cup, "hotwater_stone_cup");
    	registerRender(hotwater_glass_cup, "hotwater_glass_cup");
    	registerRender(hotwater_porcelain_cup, "hotwater_porcelain_cup");
	}
    
    private static void register(Block block, String name)
    {
        GameRegistry.registerBlock(block, name);
    }
    
    private static void register(Block block, ItemBlock itemBlock, String name)
    {
        GameRegistry.registerBlock(block, null, name);
        GameRegistry.registerItem(itemBlock, name);
        GameData.getBlockItemMap().put(block, itemBlock);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block, String name)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(TeaStory.MODID + ":" + name, "inventory"));
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block, int meta, String name)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(TeaStory.MODID + ":" + name, "inventory"));
    }
}