package roito.teastory.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import roito.teastory.helper.CraftTweakerHelper;

import javax.annotation.Nonnull;

public class TeaMakingRecipe implements ITeaMakingRecipe
{
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
		return !this.output.isEmpty() && CraftTweakerHelper.containsMatch(false, inputs, input);
	}

	public String toString()
	{
		return inputs + "@" + output;
	}
}