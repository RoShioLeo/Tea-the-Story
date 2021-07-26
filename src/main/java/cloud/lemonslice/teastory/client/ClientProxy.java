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
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
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
        registerCutoutType(BlockRegistry.WATERMELON_VINE);
        registerCutoutType(BlockRegistry.WET_HAYSTACK);
        registerCutoutType(BlockRegistry.DRY_HAYSTACK);
        FluidRegistry.FLUIDS.getEntries().forEach(e -> RenderTypeLookup.setRenderLayer(e.get(), RenderType.getTranslucent()));
        RenderTypeLookup.setRenderLayer(BlockRegistry.WOODEN_TRAY, RenderType.getTranslucent());
    }

    private static void registerCutoutType(Block... blocks)
    {
        Arrays.asList(blocks).forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutout()));
    }

    public static RecipeBookCategories getRecipeCategory(IRecipe<?> recipe)
    {
        IRecipeType<?> irecipetype = recipe.getType();
        if (irecipetype == IRecipeType.CRAFTING)
        {
            ItemStack itemstack = recipe.getRecipeOutput();
            ItemGroup itemgroup = itemstack.getItem().getGroup();
            if (itemgroup == ItemGroup.BUILDING_BLOCKS)
            {
                return RecipeBookCategories.CRAFTING_BUILDING_BLOCKS;
            }
            else if (itemgroup != ItemGroup.TOOLS && itemgroup != ItemGroup.COMBAT)
            {
                return itemgroup == ItemGroup.REDSTONE ? RecipeBookCategories.CRAFTING_REDSTONE : RecipeBookCategories.CRAFTING_MISC;
            }
            else
            {
                return RecipeBookCategories.CRAFTING_EQUIPMENT;
            }
        }
        else if (irecipetype == IRecipeType.SMELTING)
        {
            if (recipe.getRecipeOutput().getItem().isFood())
            {
                return RecipeBookCategories.FURNACE_FOOD;
            }
            else
            {
                return recipe.getRecipeOutput().getItem() instanceof BlockItem ? RecipeBookCategories.FURNACE_BLOCKS : RecipeBookCategories.FURNACE_MISC;
            }
        }
        else if (irecipetype == IRecipeType.BLASTING)
        {
            return recipe.getRecipeOutput().getItem() instanceof BlockItem ? RecipeBookCategories.BLAST_FURNACE_BLOCKS : RecipeBookCategories.BLAST_FURNACE_MISC;
        }
        else if (irecipetype == IRecipeType.SMOKING)
        {
            return RecipeBookCategories.SMOKER_FOOD;
        }
        else if (irecipetype == IRecipeType.STONECUTTING)
        {
            return RecipeBookCategories.STONECUTTER;
        }
        else if (irecipetype == IRecipeType.CAMPFIRE_COOKING)
        {
            return RecipeBookCategories.CAMPFIRE;
        }
        else if (irecipetype == IRecipeType.SMITHING)
        {
            return RecipeBookCategories.SMITHING;
        }
        else
        {
            return RecipeBookCategories.UNKNOWN;
        }
    }
}
