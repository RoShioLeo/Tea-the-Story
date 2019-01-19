package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.helper.NonNullListHelper;

import javax.annotation.Nonnull;

public class TeaMakingRecipe implements ITeaMakingRecipe
{
	public static final ITeaMakingRecipe EMPTY_RECIPE = new TeaMakingRecipe(NonNullListHelper.createNonNullList(ItemStack.EMPTY), ItemStack.EMPTY);
	private final NonNullList<ItemStack> inputs;
	private final ItemStack output;

	public TeaMakingRecipe(NonNullList<ItemStack> inputs, ItemStack output)
	{
		this.inputs = inputs;
		this.output = output;
	}

	@Override
	public NonNullList<ItemStack> getInputs()
	{
		return inputs;
	}

	@Override
	public ItemStack getOutput()
	{
		return output.copy();
	}

	public boolean isTheSameInput(@Nonnull ItemStack input)
	{
		return !this.output.isEmpty() && OreDictionary.containsMatch(false, inputs, input);
	}
}