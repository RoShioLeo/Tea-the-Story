package roito.teastory.client;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import roito.teastory.TeaStory;
import roito.teastory.block.BlockRegister;
import roito.teastory.common.CommonProxy;
import roito.teastory.item.ItemRegister;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        loadExtraResourceLocation();

        ItemRegister.initModels();
        BlockRegister.initModels();
    }

    public static void loadExtraResourceLocation()
    {
        ModelBakery.registerItemVariants(ItemRegister.tea_residue, new ResourceLocation(TeaStory.MODID, "tea_residue_green"), new ResourceLocation(TeaStory.MODID, "tea_residue_black"), new ResourceLocation(TeaStory.MODID, "tea_residue_yellow"), new ResourceLocation(TeaStory.MODID, "tea_residue_white"), new ResourceLocation(TeaStory.MODID, "tea_residue_oolong"), new ResourceLocation(TeaStory.MODID, "tea_residue_puer"));
        ModelBakery.registerItemVariants(ItemRegister.cup, new ResourceLocation(TeaStory.MODID, "cup_wood"), new ResourceLocation(TeaStory.MODID, "cup_stone"), new ResourceLocation(TeaStory.MODID, "cup_glass"), new ResourceLocation(TeaStory.MODID, "cup_porcelain"), new ResourceLocation(TeaStory.MODID, "cup_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.green_tea, new ResourceLocation(TeaStory.MODID, "green_tea_wood"), new ResourceLocation(TeaStory.MODID, "green_tea_stone"), new ResourceLocation(TeaStory.MODID, "green_tea_glass"), new ResourceLocation(TeaStory.MODID, "green_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "green_tea_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.black_tea, new ResourceLocation(TeaStory.MODID, "black_tea_wood"), new ResourceLocation(TeaStory.MODID, "black_tea_stone"), new ResourceLocation(TeaStory.MODID, "black_tea_glass"), new ResourceLocation(TeaStory.MODID, "black_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "black_tea_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.matcha_drink, new ResourceLocation(TeaStory.MODID, "matcha_drink_wood"), new ResourceLocation(TeaStory.MODID, "matcha_drink_stone"), new ResourceLocation(TeaStory.MODID, "matcha_drink_glass"), new ResourceLocation(TeaStory.MODID, "matcha_drink_porcelain"), new ResourceLocation(TeaStory.MODID, "matcha_drink_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.lemon_tea, new ResourceLocation(TeaStory.MODID, "lemon_tea_wood"), new ResourceLocation(TeaStory.MODID, "lemon_tea_stone"), new ResourceLocation(TeaStory.MODID, "lemon_tea_glass"), new ResourceLocation(TeaStory.MODID, "lemon_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "lemon_tea_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.milk_tea, new ResourceLocation(TeaStory.MODID, "milk_tea_wood"), new ResourceLocation(TeaStory.MODID, "milk_tea_stone"), new ResourceLocation(TeaStory.MODID, "milk_tea_glass"), new ResourceLocation(TeaStory.MODID, "milk_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "milk_tea_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.yellow_tea, new ResourceLocation(TeaStory.MODID, "yellow_tea_wood"), new ResourceLocation(TeaStory.MODID, "yellow_tea_stone"), new ResourceLocation(TeaStory.MODID, "yellow_tea_glass"), new ResourceLocation(TeaStory.MODID, "yellow_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "yellow_tea_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.white_tea, new ResourceLocation(TeaStory.MODID, "white_tea_wood"), new ResourceLocation(TeaStory.MODID, "white_tea_stone"), new ResourceLocation(TeaStory.MODID, "white_tea_glass"), new ResourceLocation(TeaStory.MODID, "white_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "white_tea_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.oolong_tea, new ResourceLocation(TeaStory.MODID, "oolong_tea_wood"), new ResourceLocation(TeaStory.MODID, "oolong_tea_stone"), new ResourceLocation(TeaStory.MODID, "oolong_tea_glass"), new ResourceLocation(TeaStory.MODID, "oolong_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "oolong_tea_zisha"));
        ModelBakery.registerItemVariants(ItemRegister.puer_tea, new ResourceLocation(TeaStory.MODID, "puer_tea_wood"), new ResourceLocation(TeaStory.MODID, "puer_tea_stone"), new ResourceLocation(TeaStory.MODID, "puer_tea_glass"), new ResourceLocation(TeaStory.MODID, "puer_tea_porcelain"), new ResourceLocation(TeaStory.MODID, "puer_tea_zisha"));

        ModelBakery.registerItemVariants(Item.getItemFromBlock(BlockRegister.lit_tea_drying_pan), new ResourceLocation(TeaStory.MODID, "tea_drying_pan"));
    }
}
