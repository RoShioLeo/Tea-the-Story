package starryskyline.teastory.block;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starryskyline.teastory.TeaStory;
import starryskyline.teastory.creativetab.CreativeTabsLoader;
import starryskyline.teastory.item.ItemLoader;

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

    public BlockLoader(FMLPreInitializationEvent event)
    {
    	register(lit_tea_drying_pan, new ItemBlockMeta(lit_tea_drying_pan, lit_tea_drying_pan, new Function<ItemStack, String>()
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
    	register(teapan, new ItemBlockMeta(teapan, teapan, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return "." + Teapan.EnumType.values()[input.getMetadata()].getName();
            }
        }), "teapan");
    	register(barrel, new ItemBlockMeta(barrel, barrel, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return "." + Barrel.EnumType.values()[input.getMetadata()].getName();
            }
        }), "barrel");
    	register(clay_kettle, new ItemBlockMeta(clay_kettle, clay_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return "";
            }
        }), "clay_kettle");
    	register(empty_kettle, new ItemBlockMeta(empty_kettle, empty_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return EmptyKettle.getSpecialName(input);
            }
        }), "empty_kettle");
    	register(burntgreentea_kettle, new ItemBlockMeta(burntgreentea_kettle, burntgreentea_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }), "burntgreentea_kettle");
    	register(greentea_kettle, new ItemBlockMeta(greentea_kettle, greentea_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }), "greentea_kettle");
    	register(matcha_kettle, new ItemBlockMeta(matcha_kettle, matcha_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }), "matcha_kettle");
    	register(blacktea_kettle, new ItemBlockMeta(blacktea_kettle, blacktea_kettle, new Function<ItemStack, String>()
        {
            @Override
            public String apply(ItemStack input)
            {
                return FullKettle.getSpecialName(input);
            }
        }), "blacktea_kettle");
        register(teaplant, "teaplant");
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
        registerRender(teaplant, "teaplant");
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