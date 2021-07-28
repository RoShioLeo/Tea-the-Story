package cloud.lemonslice.teastory.common.recipe.stone_mill;

import cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import static cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry.STONE_ROLLER;

public class StoneRollerRecipe implements IRecipe<IInventory>
{
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient inputItem;
    protected final NonNullList<ItemStack> outputItems;
    protected final int workTime;

    public StoneRollerRecipe(ResourceLocation idIn, String groupIn, Ingredient inputItem, NonNullList<ItemStack> outputItems, int workTime)
    {
        this.id = idIn;
        this.group = groupIn;
        this.inputItem = inputItem;
        this.outputItems = outputItems;
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
        if (this.inputItem.test(inv.getStackInSlot(0)))
        {
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv)
    {
        return this.outputItems.get(0).copy();
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.from(Ingredient.EMPTY, getInputItem());
    }

    public Ingredient getInputItem()
    {
        return inputItem;
    }

    public NonNullList<ItemStack> getOutputItems()
    {
        return outputItems;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
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

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return STONE_ROLLER.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return NormalRecipeTypes.STONE_ROLLER;
    }
}
