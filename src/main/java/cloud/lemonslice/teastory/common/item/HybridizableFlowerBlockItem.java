package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.silveroak.common.item.NormalBlockItem;
import cloud.lemonslice.teastory.common.environment.flower.FlowerColor;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class HybridizableFlowerBlockItem extends NormalBlockItem
{
    public HybridizableFlowerBlockItem(Block blockIn)
    {
        super(blockIn, ItemRegistry.getNormalItemProperties());
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items)
    {
        if (group == ItemGroup.DECORATIONS)
        {
            for (FlowerColor c : FlowerColor.values())
            {
                ItemStack stack = new ItemStack(this.getItem());
                CompoundNBT nbt = new CompoundNBT();
                nbt.putString("color", c.getString());
                stack.setTag(nbt);
                items.add(stack);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        if (stack.getOrCreateTag().contains("color"))
        {
            tooltip.add(new StringTextComponent(FlowerColor.getFlowerColor(stack.getOrCreateTag().getString("color")).getTranslation()).mergeStyle(TextFormatting.ITALIC).mergeStyle(TextFormatting.GRAY));
        }
    }

}
