package cloud.lemonslice.teastory.plugin.jei;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.recipe.bamboo_tray.BambooTraySingleInRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.config.Constants;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

public class BambooTraySingleInCategory implements IRecipeCategory<BambooTraySingleInRecipe>
{
    private final String uid;
    private final IDrawable icon;
    private final IGuiHelper guiHelper;
    protected final IDrawable arrow;

    public BambooTraySingleInCategory(IGuiHelper guiHelper, ResourceLocation uid, int i)
    {
        this.guiHelper = guiHelper;
        this.uid = uid.getPath();
        icon = guiHelper.createDrawable(new ResourceLocation(TeaStory.MODID, "textures/gui/jei/bamboo_tray.png"), i * 20, 0, 20, 20);
        this.arrow = guiHelper.drawableBuilder(Constants.RECIPE_GUI_VANILLA, 82, 128, 24, 17).build();
    }

    @Override
    public ResourceLocation getUid()
    {
        return new ResourceLocation(TeaStory.MODID, uid);
    }

    @Override
    public Class<? extends BambooTraySingleInRecipe> getRecipeClass()
    {
        return BambooTraySingleInRecipe.class;
    }

    @Override
    public String getTitle()
    {
        return I18n.format("info.teastory." + uid);
    }

    @Override
    public IDrawable getBackground()
    {
        return guiHelper.createBlankDrawable(80, 20);
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setIngredients(BambooTraySingleInRecipe bambooTraySingleInRecipe, IIngredients iIngredients)
    {
        ItemStack itemOut = bambooTraySingleInRecipe.getRecipeOutput();
        iIngredients.setInputIngredients(Collections.singletonList(bambooTraySingleInRecipe.getIngredient()));
        iIngredients.setOutput(VanillaTypes.ITEM, itemOut);
    }

    @Override
    public void draw(BambooTraySingleInRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY)
    {
        this.arrow.draw(matrixStack, 28, 2);
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, BambooTraySingleInRecipe bambooTraySingleInRecipe, IIngredients iIngredients)
    {
        IGuiItemStackGroup guiItemStacks = iRecipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 5, 1);
        guiItemStacks.set(0, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
        guiItemStacks.setBackground(0, guiHelper.getSlotDrawable());
        guiItemStacks.init(1, false, 56, 1);
        guiItemStacks.set(1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
        guiItemStacks.setBackground(1, guiHelper.getSlotDrawable());
    }
}
