package roito.teastory.recipe;

import java.util.Collections;

import javax.annotation.Nonnull;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import roito.teastory.block.BlockLoader;
import roito.teastory.item.ItemLoader;

public class RecipeLoader
{
	@Nonnull
	public static IRecipeManager<ITeaStoveRecipe> managerTS;
	public static IRecipeManager<ITeaTableRecipe> managerTT;
	
	public RecipeLoader()
	{
		managerTS = new TeaStoveRecipeManger();
		managerTT = new TeaTableRecipeManager();
		
		addTeaStoveRecipe();
		addTeaTableRecipe();
	}

	private static void addTeaStoveRecipe()
	{
		managerTS.add(new TeaStoveRecipe(new ItemStack(ItemLoader.half_dried_tea), new ItemStack(ItemLoader.matcha_leaf), true));
		managerTS.add(new TeaStoveRecipe(new ItemStack(ItemLoader.tea_leaf), new ItemStack(ItemLoader.white_tea_leaf), false));
	}
	
	private static void addTeaTableRecipe()
	{
		for (int i = 0; i <= 5; i++)
		{
			if(i != 1)
			{
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.black_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.black_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.green_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.green_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.dried_tea, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.green_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.milk_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 8), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.milk_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag), OreDictionary.getOres("cropLemon"), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.lemon_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 8), OreDictionary.getOres("cropLemon"), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.lemon_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.yellow_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.yellow_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.white_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.white_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.oolong_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.oolong_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_bag), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.puer_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_leaf, 8), null, new ItemStack(ItemLoader.cup, 1, i), null, new ItemStack(ItemLoader.puer_tea, 1, i)));
				managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.matcha_powder, 8), Collections.singletonList(new ItemStack(ItemLoader.tea_whisk)), new ItemStack(ItemLoader.cup, 1, i), new ItemStack(Items.SUGAR, 3), new ItemStack(ItemLoader.matcha_drink, 1, i)));
			}
		}
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.black_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.black_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.green_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.green_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.dried_tea, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.green_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 4), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.milk_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 32), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.milk_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 4), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.lemon_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 32), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.lemon_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.yellow_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.yellow_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.white_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.white_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.oolong_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.oolong_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_bag, 4), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.puer_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_leaf, 32), null, new ItemStack(BlockLoader.empty_porcelain_kettle), null, new ItemStack(BlockLoader.puer_tea_porcelain_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.matcha_powder, 32), Collections.singletonList(new ItemStack(ItemLoader.tea_whisk)), new ItemStack(BlockLoader.empty_porcelain_kettle), new ItemStack(Items.SUGAR, 12), new ItemStack(BlockLoader.matcha_drink_porcelain_kettle)));
		
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.black_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.black_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.green_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.green_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.dried_tea, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.green_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 8), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.milk_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 64), Collections.singletonList(new ItemStack(Items.MILK_BUCKET)), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.milk_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_bag, 8), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.lemon_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.black_tea_leaf, 64), OreDictionary.getOres("cropLemon"), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.lemon_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.yellow_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.yellow_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.yellow_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.white_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.white_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.white_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.oolong_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.oolong_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.oolong_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_bag, 8), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.puer_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.puer_tea_leaf, 64), null, new ItemStack(BlockLoader.empty_zisha_kettle), null, new ItemStack(BlockLoader.puer_tea_zisha_kettle)));
		managerTT.add(new TeaTableRecipe(new ItemStack(ItemLoader.matcha_powder, 64), Collections.singletonList(new ItemStack(ItemLoader.tea_whisk)), new ItemStack(BlockLoader.empty_zisha_kettle), new ItemStack(Items.SUGAR, 24), new ItemStack(BlockLoader.matcha_drink_zisha_kettle)));
	}
}
