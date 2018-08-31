package roito.teastory.recipe;

import net.minecraft.item.ItemStack;

public class TeaMakingRecipe implements ITeaMakingRecipe
{
    private final ItemStack step1, step2, step3, step4, step5;

    public TeaMakingRecipe(ItemStack step1, ItemStack step2, ItemStack step3, ItemStack step4, ItemStack step5)
    {
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.step4 = step4;
        this.step5 = step5;
    }

    @Override
    public ItemStack getStep1()
    {
        return step1;
    }

    @Override
    public ItemStack getStep2()
    {
        return step2;
    }

    @Override
    public ItemStack getStep3()
    {
        return step3;
    }

    @Override
    public ItemStack getStep4()
    {
        return step4;
    }

    @Override
    public ItemStack getStep5()
    {
        return step5;
    }

}
