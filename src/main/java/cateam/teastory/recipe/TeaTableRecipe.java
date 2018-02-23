package cateam.teastory.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public class TeaTableRecipe implements ITeaTableRecipe
{
	private final ItemStack teaLeaf, cup, output, sugar;
	private final List<ItemStack> tool;
	
	public TeaTableRecipe(ItemStack teaLeaf, List<ItemStack> tool, ItemStack cup, ItemStack sugar, ItemStack output)
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
	public List<ItemStack> getToolInput()
	{
		return tool;
	}

	@Override
	public ItemStack getSugarInput()
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
		boolean flag = r instanceof TeaTableRecipe;
		flag = flag && ((TeaTableRecipe)r).getCupInput().isItemEqual(this.cup);
		flag = flag && ((TeaTableRecipe)r).getTeaLeafInput().isItemEqual(this.teaLeaf);
		if (this.sugar != null)
		{
			flag = flag && ((TeaTableRecipe)r).getSugarInput().isItemEqual(this.sugar);
		}
		if (this.tool == null)
		{
			return flag;
		}
		if (this.tool != null && ((TeaTableRecipe)r).getToolInput() != null)
		{
			boolean flag2 = false;
			for(ItemStack stack : this.tool)
			{
				flag2 = flag2 || ((TeaTableRecipe)r).getToolInput().contains(stack);
			}
			flag = flag2 && flag;
		}
		return flag;
	}

}
