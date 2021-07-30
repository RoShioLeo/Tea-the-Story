package cloud.lemonslice.teastory.plugin.jei;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.client.gui.BambooTrayGui;
import cloud.lemonslice.teastory.client.gui.DrinkMakerGui;
import cloud.lemonslice.teastory.client.gui.StoneMillGui;
import cloud.lemonslice.teastory.client.gui.StoneRollerGui;
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

import static cloud.lemonslice.teastory.TeaStory.MODID;

@JeiPlugin
public final class JEICompat implements IModPlugin
{
    private static final ResourceLocation IN_RAIN = new ResourceLocation(MODID, "bamboo_tray.mode.in_rain");
    private static final ResourceLocation OUTDOORS = new ResourceLocation(MODID, "bamboo_tray.mode.outdoors");
    private static final ResourceLocation INDOORS = new ResourceLocation(MODID, "bamboo_tray.mode.indoors");
    private static final ResourceLocation BAKE = new ResourceLocation(MODID, "bamboo_tray.mode.bake");
    public static final ResourceLocation DRINK_MAKER = new ResourceLocation(MODID, "drink_maker");
    public static final ResourceLocation STONE_MILL = new ResourceLocation(MODID, "stone_mill");
    public static final ResourceLocation STONE_ROLLER = new ResourceLocation(MODID, "stone_roller");

    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(MODID, "recipe");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), IN_RAIN, 0),
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), OUTDOORS, 1),
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), INDOORS, 2),
                new BambooTraySingleInCategory(registry.getJeiHelpers().getGuiHelper(), BAKE, 3));
        registry.addRecipeCategories(new DrinkMakerCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new StoneMillCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new StoneRollerCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration)
    {
        registration.useNbtForSubtypes(BlockRegistry.CHRYSANTHEMUM.asItem(), BlockRegistry.HYACINTH.asItem(), BlockRegistry.ZINNIA.asItem(), ItemRegistry.BOTTLE_DRINK, ItemRegistry.PORCELAIN_CUP_DRINK, ItemRegistry.PORCELAIN_TEAPOT);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.BAMBOO_TRAY), IN_RAIN, OUTDOORS, INDOORS, BAKE);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.DRINK_MAKER), DRINK_MAKER);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.STONE_MILL), STONE_MILL);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.STONE_ROLLER), STONE_ROLLER);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(BambooTrayGui.class, 77, 32, 22, 17, IN_RAIN, OUTDOORS, INDOORS, BAKE);
        registration.addRecipeClickArea(DrinkMakerGui.class, 98, 37, 24, 17, DRINK_MAKER);
        registration.addRecipeClickArea(StoneMillGui.class, 95, 37, 22, 17, STONE_MILL);
        registration.addRecipeClickArea(StoneRollerGui.class, 77, 37, 22, 17, STONE_ROLLER);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_IN_RAIN), IN_RAIN);
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_OUTDOORS), OUTDOORS);
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_INDOORS), INDOORS);
        registration.addRecipes(getRecipes(NormalRecipeTypes.BT_BAKE), BAKE);
        registration.addRecipes(getRecipes(NormalRecipeTypes.DRINK_MAKER), DRINK_MAKER);
        registration.addRecipes(getRecipes(NormalRecipeTypes.STONE_MILL), STONE_MILL);
        registration.addRecipes(getRecipes(NormalRecipeTypes.STONE_ROLLER), STONE_ROLLER);
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
