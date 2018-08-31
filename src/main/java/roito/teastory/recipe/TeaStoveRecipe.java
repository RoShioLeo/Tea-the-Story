package roito.teastory.recipe;

import net.minecraft.item.ItemStack;

public class TeaStoveRecipe implements ITeaStoveRecipe
{
    private final ItemStack input, output;
    private final boolean needWater;

    public TeaStoveRecipe(ItemStack input, ItemStack output, boolean needWater)
    {
        this.input = input;
        this.output = output;
        this.needWater = needWater;
    }

    @Override
    public ItemStack getInput()
    {
        return input.copy();
    }

    @Override
    public ItemStack getOutput()
    {
        return output.copy();
    }

    @Override
    public Boolean NeedWater()
    {
        return needWater;
    }

    @Override
    public boolean equals(Object r)
    {
        return r instanceof TeaStoveRecipe && ((TeaStoveRecipe) r).getInput().isItemEqual(this.input);
    }

}
