package cloud.lemonslice.teastory.plugin.jei;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.recipe.stone_mill.StoneRollerRecipe;
import com.google.common.collect.Lists;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

import static cloud.lemonslice.teastory.plugin.jei.JEICompat.STONE_ROLLER;

public class StoneRollerCategory implements IRecipeCategory<StoneRollerRecipe>
{
    private final IDrawable icon;
    private final IGuiHelper guiHelper;

    public StoneRollerCategory(IGuiHelper guiHelper)
    {
        this.guiHelper = guiHelper;
        icon = guiHelper.createDrawableIngredient(BlockRegistry.STONE_ROLLER.asItem().getDefaultInstance());
    }

    @Override
    public ResourceLocation getUid()
    {
        return STONE_ROLLER;
    }

    @Override
    public Class<? extends StoneRollerRecipe> getRecipeClass()
    {
        return StoneRollerRecipe.class;
    }

    @Override
    public String getTitle()
    {
        return I18n.format("info.teastory.stone_mill");
    }

    @Override
    public IDrawable getBackground()
    {
        return guiHelper.createDrawable(new ResourceLocation(TeaStory.MODID, "textures/gui/jei/recipes.png"), 37, 105, 74, 60);
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setIngredients(StoneRollerRecipe recipe, IIngredients iIngredients)
    {
        iIngredients.setInputIngredients(Collections.singletonList(recipe.getInputItem()));
        List<List<ItemStack>> outputs = Lists.newArrayList();
        for (ItemStack output : recipe.getOutputItems())
        {
            outputs.add(Lists.newArrayList(output));
        }
        iIngredients.setOutputLists(VanillaTypes.ITEM, outputs);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, StoneRollerRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        int slot = 0;

        guiItemStacks.init(slot, true, 3, 21);
        guiItemStacks.set(slot++, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        int n = ingredients.getOutputs(VanillaTypes.ITEM).size();
        for (int i = 0; i < n; i++)
        {
            guiItemStacks.init(slot, false, 53, 3 + i * 18);
            guiItemStacks.set(slot++, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
        }
    }
}
