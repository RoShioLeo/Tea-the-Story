package cloud.lemonslice.teastory.common.recipe.drink;

import cloud.lemonslice.silveroak.common.recipe.FluidIngredient;
import cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry;
import cloud.lemonslice.teastory.common.recipe.type.NormalRecipeTypes;
import com.google.common.collect.Lists;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import java.util.List;

import static net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack.FLUID_NBT_KEY;

public class DrinkRecipe implements IRecipe<IInventory>
{
    protected final ResourceLocation id;
    protected final String group;
    protected final NonNullList<Ingredient> ingredients;
    protected final FluidIngredient fluidIngredient;
    protected final Fluid result;

    public DrinkRecipe(ResourceLocation idIn, String groupIn, NonNullList<Ingredient> ingredientIn, FluidIngredient fluidIn, Fluid resultIn)
    {
        this.id = idIn;
        this.group = groupIn;
        this.ingredients = ingredientIn;
        this.fluidIngredient = fluidIn;
        this.result = resultIn;
    }

    @Override
    public boolean isDynamic()
    {
        return true;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn)
    {
        List<ItemStack> inputs = Lists.newArrayList();
        return FluidUtil.getFluidHandler(inv.getStackInSlot(8).copy()).map(h ->
        {
            if (fluidIngredient.hasNoMatchingFluids())
            {
                return false;
            }
            else
            {
                boolean flag = false;
                for (FluidStack fluidStack : fluidIngredient.getMatchingStacks())
                {
                    if (h.getFluidInTank(0).containsFluid(fluidStack))
                    {
                        flag = true;
                        break;
                    }
                }
                if (!flag) return false;

                for (int j = 0; j < 4; ++j)
                {
                    ItemStack itemstack = inv.getStackInSlot(j).copy();
                    if (!itemstack.isEmpty())
                    {
                        inputs.add(itemstack);
                    }
                }

                return RecipeMatcher.findMatches(inputs, this.ingredients) != null;
            }
        }).orElse(false);
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv)
    {
        return FluidUtil.getFluidHandler(inv.getStackInSlot(8).copy()).map(h ->
        {
            ItemStack container = inv.getStackInSlot(8).copy();
            CompoundNBT fluidTag = new CompoundNBT();
            new FluidStack(result, h.getFluidInTank(0).getAmount()).writeToNBT(fluidTag);
            container.getOrCreateTag().put(FLUID_NBT_KEY, fluidTag);
            return container;
        }).orElse(ItemStack.EMPTY);
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public Fluid getFluidResult()
    {
        return result;
    }

    public FluidIngredient getFluidIngredient()
    {
        return fluidIngredient;
    }

    public int getFluidAmount()
    {
        return fluidIngredient.getMatchingStacks()[0].getAmount();
    }

    @Override
    public String getGroup()
    {
        return this.group;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return RecipeSerializerRegistry.DRINK_MAKER.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return NormalRecipeTypes.DRINK_MAKER;
    }
}
