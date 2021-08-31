package cloud.lemonslice.teastory.client;

import cloud.lemonslice.teastory.client.color.season.BiomeColorsHandler;
import cloud.lemonslice.teastory.client.render.SeatEntityRenderer;
import cloud.lemonslice.teastory.common.CommonProxy;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.entity.EntityTypeRegistry;
import cloud.lemonslice.teastory.common.fluid.FluidRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.Arrays;

public class ClientProxy extends CommonProxy
{
    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer()
    {
        return Minecraft.getInstance().player;
    }

    public static void registerProperties()
    {
        ItemModelsProperties.registerProperty(BlockRegistry.SAUCEPAN.asItem(), new ResourceLocation("lid"), (itemStack, world, entity) ->
        {
            if (itemStack.getOrCreateTag().contains("lid"))
            {
                return itemStack.getOrCreateTag().getBoolean("lid") ? 1 : 0;
            }
            return 1;
        });
    }

    public static void initBiomeColors()
    {
        BiomeColors.GRASS_COLOR = BiomeColorsHandler.GRASS_COLOR;
        BiomeColors.FOLIAGE_COLOR = BiomeColorsHandler.FOLIAGE_COLOR;
    }

    public static void registerEntityRenderer()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.SEAT_ENTITY, SeatEntityRenderer::new);
    }

    public static void registerRenderType()
    {
        registerCutoutType(BlockRegistry.CHRYSANTHEMUM);
        registerCutoutType(BlockRegistry.ZINNIA);
        registerCutoutType(BlockRegistry.HYACINTH);
        registerCutoutType(BlockRegistry.TEA_PLANT);
        registerCutoutType(BlockRegistry.RICE_SEEDLING);
        registerCutoutType(BlockRegistry.WILD_TEA_PLANT);
        registerCutoutType(BlockRegistry.GRASS_BLOCK_WITH_HOLE);
        registerCutoutType(BlockRegistry.BAMBOO_GLASS_DOOR);
        registerCutoutType(BlockRegistry.DRINK_MAKER);
        registerCutoutType(BlockRegistry.RICE_PLANT);
        registerCutoutType(BlockRegistry.TRELLIS_BLOCKS.toArray(new Block[0]));
        registerCutoutType(BlockRegistry.WILD_GRAPE);
        registerCutoutType(BlockRegistry.GRAPE);
        registerCutoutType(BlockRegistry.CUCUMBER);
        registerCutoutType(BlockRegistry.BITTER_GOURD);
        registerCutoutType(BlockRegistry.WATERMELON_VINE);
        registerCutoutType(BlockRegistry.WET_HAYSTACK);
        registerCutoutType(BlockRegistry.DRY_HAYSTACK);
        registerCutoutType(BlockRegistry.CHILI_PLANT);
        registerCutoutType(BlockRegistry.CHINESE_CABBAGE_PLANT);
        registerCutoutType(BlockRegistry.WOODEN_BOWL);
        FluidRegistry.FLUIDS.getEntries().forEach(e -> RenderTypeLookup.setRenderLayer(e.get(), RenderType.getTranslucent()));
        RenderTypeLookup.setRenderLayer(BlockRegistry.WOODEN_TRAY, RenderType.getTranslucent());
//        RenderTypeLookup.setRenderLayer(BlockRegistry.SAUCEPAN, RenderType.getTranslucent());
    }

    private static void registerCutoutType(Block... blocks)
    {
        Arrays.asList(blocks).forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutout()));
    }
}
