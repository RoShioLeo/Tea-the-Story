package cloud.lemonslice.teastory.common.recipe.special;

import cloud.lemonslice.teastory.common.environment.flower.FlowerColor;
import cloud.lemonslice.teastory.common.item.HybridizableFlowerBlockItem;
import cloud.lemonslice.teastory.common.recipe.serializer.RecipeSerializerRegistry;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FlowerDyeRecipe extends SpecialRecipe
{

    public FlowerDyeRecipe(ResourceLocation idIn)
    {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn)
    {
        ItemStack itemstack = ItemStack.EMPTY;

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = inv.getStackInSlot(i);
            if (!itemstack1.isEmpty())
            {
                if (itemstack1.getItem() instanceof HybridizableFlowerBlockItem)
                {
                    if (!itemstack.isEmpty())
                    {
                        return false;
                    }

                    itemstack = itemstack1;
                }
            }
        }

        return !itemstack.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv)
    {
        ItemStack itemstack = ItemStack.EMPTY;

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = inv.getStackInSlot(i);
            if (!itemstack1.isEmpty())
            {
                if (itemstack1.getItem() instanceof HybridizableFlowerBlockItem)
                {
                    if (!itemstack.isEmpty())
                    {
                        return ItemStack.EMPTY;
                    }

                    itemstack = itemstack1;
                }
            }
        }
        FlowerColor color = FlowerColor.getFlowerColor(itemstack.getOrCreateTag().getString("color"));
        if (color.getDye() != null)
        {
            return new ItemStack(color.getDye(), 2);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return true;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return RecipeSerializerRegistry.CRAFTING_SPECIAL_FLOWERDYE.get();
    }
}
