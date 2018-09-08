package roito.teastory.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class TeaTableRecipe implements ITeaTableRecipe
{
    private final ItemStack teaLeaf, cup, output;
    private final NonNullList<ItemStack> tool, sugar;

    public TeaTableRecipe(ItemStack teaLeaf, NonNullList<ItemStack> tool, ItemStack cup, NonNullList<ItemStack> sugar, ItemStack output)
    {
        this.teaLeaf = teaLeaf;
        this.tool = tool;
        this.cup = cup;
        this.sugar = sugar;
        this.output = output;
    }

    @Override
    public ItemStack getTeaLeafInput()
    {
        return teaLeaf;
    }

    @Override
    public NonNullList<ItemStack> getToolInput()
    {
        return tool;
    }

    @Override
    public NonNullList<ItemStack> getSugarInput()
    {
        return sugar;
    }

    @Override
    public ItemStack getCupInput()
    {
        return cup;
    }

    @Override
    public ItemStack getOutput()
    {
        return output;
    }

    @Override
    public boolean equals(Object r)
    {
        if (!(r instanceof TeaTableRecipe))
        {
            return false;
        }
        if (!((TeaTableRecipe) r).getTeaLeafInput().isItemEqual(this.teaLeaf))
        {
            return false;
        }
        if (!((TeaTableRecipe) r).getCupInput().isItemEqual(this.cup))
        {
            return false;
        }
        if (!OreDictionary.containsMatch(false, this.sugar, ((TeaTableRecipe) r).getSugarInput().get(0)))
        {
            return false;
        }
        if (!OreDictionary.containsMatch(false, this.tool, ((TeaTableRecipe) r).getToolInput().get(0)))
        {
            return false;
        }

        return true;
    }

}
