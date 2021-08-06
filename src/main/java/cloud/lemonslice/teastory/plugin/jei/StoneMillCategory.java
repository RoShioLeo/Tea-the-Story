package cloud.lemonslice.teastory.plugin.jei;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneMillRecipe;
import com.google.common.collect.Lists;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static cloud.lemonslice.teastory.plugin.jei.JEICompat.STONE_MILL;

public class StoneMillCategory implements IRecipeCategory<StoneMillRecipe>
{
    private final IDrawable icon;
    private final IGuiHelper guiHelper;

    public StoneMillCategory(IGuiHelper guiHelper)
    {
        this.guiHelper = guiHelper;
        icon = guiHelper.createDrawableIngredient(BlockRegistry.STONE_MILL.asItem().getDefaultInstance());
    }

    @Override
    public ResourceLocation getUid()
    {
        return STONE_MILL;
    }

    @Override
    public Class<? extends StoneMillRecipe> getRecipeClass()
    {
        return StoneMillRecipe.class;
    }

    @Override
    public String getTitle()
    {
        return I18n.format("info.teastory.stone_mill");
    }

    @Override
    public IDrawable getBackground()
    {
        return guiHelper.createDrawable(new ResourceLocation(TeaStory.MODID, "textures/gui/jei/recipes.png"), 0, 105, 148, 60);
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setIngredients(StoneMillRecipe recipe, IIngredients iIngredients)
    {
        iIngredients.setInputIngredients(Collections.singletonList(recipe.getInputItem()));
        List<FluidStack> fluidIn = Arrays.asList(recipe.getInputFluid().getMatchingStacks());
        iIngredients.setInputs(VanillaTypes.FLUID, fluidIn);

        List<List<ItemStack>> outputs = Lists.newArrayList();
        for (ItemStack output : recipe.getOutputItems())
        {
            if (!output.isEmpty())
            {
                outputs.add(Lists.newArrayList(output));
            }
        }
        iIngredients.setOutputLists(VanillaTypes.ITEM, outputs);

        FluidStack fluidOut = recipe.getOutputFluid();
        if (!fluidOut.isEmpty())
        {
            iIngredients.setOutput(VanillaTypes.FLUID, fluidOut);
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, StoneMillRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        int slot = 0;

        guiItemStacks.init(slot, true, 40, 21);
        guiItemStacks.set(slot++, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        if (!ingredients.getInputs(VanillaTypes.FLUID).isEmpty())
        {
            fluidStackGroup.init(slot, true, new FluidStackRenderer(2000, true, 16, 48, null), 4, 6, 16, 48, 0, 0);
            fluidStackGroup.set(slot++, ingredients.getInputs(VanillaTypes.FLUID).get(0));
        }

        int n = ingredients.getOutputs(VanillaTypes.ITEM).size();
        for (int i = 0; i < n; i++)
        {
            guiItemStacks.init(slot, false, 90, 3 + i * 18);
            guiItemStacks.set(slot++, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
        }

        if (!ingredients.getOutputs(VanillaTypes.FLUID).isEmpty())
        {
            fluidStackGroup.init(slot, false, new FluidStackRenderer(2000, false, 16, 48, null), 128, 6, 16, 48, 0, 0);
            fluidStackGroup.set(slot, ingredients.getOutputs(VanillaTypes.FLUID).get(0));
        }
    }
}
