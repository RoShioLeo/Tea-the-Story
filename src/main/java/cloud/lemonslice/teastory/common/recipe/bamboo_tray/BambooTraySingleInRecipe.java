package cloud.lemonslice.teastory.common.recipe.bamboo_tray;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class BambooTraySingleInRecipe implements IRecipe<IInventory>
{
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final int workTime;

    public BambooTraySingleInRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, int workTime)
    {
        this.id = idIn;
        this.group = groupIn;
        this.ingredient = ingredientIn;
        this.result = resultIn;
        this.workTime = workTime;
    }

    @Override
    public boolean isDynamic()
    {
        return true;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn)
    {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv)
    {
        return this.result.copy();
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.from(getIngredient());
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return this.result.copy();
    }

    public int getWorkTime()
    {
        return this.workTime;
    }

    @Override
    public String getGroup()
    {
        return this.group;
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }
}
