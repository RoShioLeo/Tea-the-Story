package cloud.lemonslice.teastory.common.recipe.stone_mill;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes;
import cloud.lemonslice.teastory.common.tileentity.StoneMillTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import static cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry.STONE_MILL;

public class StoneMillRecipe implements IRecipe<IInventory>
{
    protected final ResourceLocation id;
    protected final String group;
    protected final FluidIngredient inputFluid;
    protected final Ingredient inputItem;
    protected final NonNullList<ItemStack> outputItems;
    protected final FluidStack outputFluid;
    protected final int workTime;

    public StoneMillRecipe(ResourceLocation idIn, String groupIn, Ingredient inputItem, FluidIngredient inputFluid, NonNullList<ItemStack> outputItems, FluidStack outputFluid, int workTime)
    {
        this.id = idIn;
        this.group = groupIn;
        this.inputItem = inputItem;
        this.inputFluid = inputFluid;
        this.outputItems = outputItems;
        this.outputFluid = outputFluid;
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
            if (inv instanceof StoneMillTileEntity)
            {
                FluidStack fluidStack = ((StoneMillTileEntity) inv).getFluidTank().getFluidInTank(0).copy();
                return inputFluid.test(fluidStack);
            }
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

    public FluidStack getOutputFluid()
    {
        return outputFluid;
    }

    public FluidIngredient getInputFluid()
    {
        return inputFluid;
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
        return STONE_MILL.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return NormalRecipeTypes.STONE_MILL;
    }
}
