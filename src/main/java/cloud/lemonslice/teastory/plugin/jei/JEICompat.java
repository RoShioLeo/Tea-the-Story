package cloud.lemonslice.teastory.plugin.jei;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.client.gui.BambooTrayGuiContainer;
import cloud.lemonslice.teastory.client.gui.DrinkMakerGuiContainer;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class JEICompat implements IModPlugin
{
    private static final String IN_RAIN = "bamboo_tray.mode.in_rain";
    private static final String OUTDOORS = "bamboo_tray.mode.outdoors";
    private static final String INDOORS = "bamboo_tray.mode.indoors";
    private static final String BAKE = "bamboo_tray.mode.bake";
    public static final String DRINK_MAKER = "drink_maker";
    public static final String FLOWER = "flower_hybridization";

    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(TeaStory.MODID, "recipe");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), IN_RAIN, new ResourceLocation(TeaStory.MODID, "textures/gui/jei/bamboo_tray.png"), 0),
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), OUTDOORS, new ResourceLocation(TeaStory.MODID, "textures/gui/jei/bamboo_tray.png"), 1),
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), INDOORS, new ResourceLocation(TeaStory.MODID, "textures/gui/jei/bamboo_tray.png"), 2),
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), BAKE, new ResourceLocation(TeaStory.MODID, "textures/gui/jei/bamboo_tray.png"), 3)
        );
        registry.addRecipeCategories(
                new DrinkMakerCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration)
    {
        registration.useNbtForSubtypes(BlockRegistry.CHRYSANTHEMUM_ITEM, BlockRegistry.HYACINTH_ITEM, BlockRegistry.ZINNIA_ITEM, ItemRegistry.BOTTLE_DRINK, ItemRegistry.PORCELAIN_CUP_DRINK, ItemRegistry.PORCELAIN_TEAPOT);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.BAMBOO_TRAY),
                new ResourceLocation(TeaStory.MODID, IN_RAIN),
                new ResourceLocation(TeaStory.MODID, OUTDOORS),
                new ResourceLocation(TeaStory.MODID, INDOORS),
                new ResourceLocation(TeaStory.MODID, BAKE)
        );
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.DRINK_MAKER), new ResourceLocation(TeaStory.MODID, DRINK_MAKER));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(BambooTrayGuiContainer.class, 77, 32, 22, 17,
                new ResourceLocation(TeaStory.MODID, IN_RAIN),
                new ResourceLocation(TeaStory.MODID, OUTDOORS),
                new ResourceLocation(TeaStory.MODID, INDOORS),
                new ResourceLocation(TeaStory.MODID, BAKE)
        );
        registration.addRecipeClickArea(DrinkMakerGuiContainer.class, 98, 37, 24, 17, new ResourceLocation(TeaStory.MODID, DRINK_MAKER));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_IN_RAIN), new ResourceLocation(TeaStory.MODID, IN_RAIN));
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_OUTDOORS), new ResourceLocation(TeaStory.MODID, OUTDOORS));
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_INDOORS), new ResourceLocation(TeaStory.MODID, INDOORS));
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_BAKE), new ResourceLocation(TeaStory.MODID, BAKE));
        registration.addRecipes(getRecipes(NormalRecipeTypes.DRINK_MAKER), new ResourceLocation(TeaStory.MODID, DRINK_MAKER));
    }

    private static List<IRecipe<?>> getRecipes(IRecipeType<?> type)
    {
        return TeaStory.proxy.getClientWorld()
                .getRecipeManager()
                .getRecipes()
                .stream()
                .filter(r -> r.getType() == type)
                .collect(Collectors.toList());
    }
}
