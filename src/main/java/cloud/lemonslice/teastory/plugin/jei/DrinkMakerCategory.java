package cloud.lemonslice.teastory.plugin.jei;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.recipe.drink.DrinkRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.plugins.vanilla.ingredients.fluid.FluidStackRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.List;

import static cloud.lemonslice.teastory.plugin.jei.JEICompat.DRINK_MAKER;

public class DrinkMakerCategory implements IRecipeCategory<DrinkRecipe>
{
    private final IDrawable icon;
    private final IGuiHelper guiHelper;

    public DrinkMakerCategory(IGuiHelper guiHelper)
    {
        this.guiHelper = guiHelper;
        icon = guiHelper.createDrawableIngredient(BlockRegistry.DRINK_MAKER.asItem().getDefaultInstance());
    }

    @Override
    public ResourceLocation getUid()
    {
        return DRINK_MAKER;
    }

    @Override
    public Class<? extends DrinkRecipe> getRecipeClass()
    {
        return DrinkRecipe.class;
    }

    @Override
    public String getTitle()
    {
        return I18n.format("info.teastory.drink_maker");
    }

    @Override
    public IDrawable getBackground()
    {
        return guiHelper.createDrawable(new ResourceLocation(TeaStory.MODID, "textures/gui/jei/recipes.png"), 0, 0, 149, 75);
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setIngredients(DrinkRecipe recipe, IIngredients iIngredients)
    {
        FluidStack fluidOut = new FluidStack(recipe.getFluidResult(), recipe.getFluidAmount());
        List<FluidStack> fluidIn = Arrays.asList(recipe.getFluidIngredient().getMatchingStacks());
        iIngredients.setInputIngredients(recipe.getIngredients());
        iIngredients.setInputs(VanillaTypes.FLUID, fluidIn);
        iIngredients.setOutput(VanillaTypes.FLUID, fluidOut);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DrinkRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        int n = ingredients.getInputs(VanillaTypes.ITEM).size();
        for (int i = 0; i < n; i++)
        {
            guiItemStacks.init(i, true, 39 + i * 18, 29);
            guiItemStacks.set(i, ingredients.getInputs(VanillaTypes.ITEM).get(i));
        }

        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        fluidStackGroup.init(n, true, new FluidStackRenderer(1000, false, 16, 64, (IDrawable) null), 6, 6, 16, 64, 0, 0);
        fluidStackGroup.set(n, ingredients.getInputs(VanillaTypes.FLUID).get(0));

        fluidStackGroup.init(n + 1, false, new FluidStackRenderer(1000, false, 16, 64, (IDrawable) null), 127, 6, 16, 64, 0, 0);
        fluidStackGroup.set(n + 1, ingredients.getOutputs(VanillaTypes.FLUID).get(0));
    }
}
