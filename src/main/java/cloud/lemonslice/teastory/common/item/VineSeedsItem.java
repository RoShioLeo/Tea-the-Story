package cloud.lemonslice.teastory.common.item;

import cloud.lemonslice.teastory.common.block.crops.TrellisBlock;
import cloud.lemonslice.teastory.common.block.crops.TrellisWithVineBlock;
import cloud.lemonslice.teastory.common.block.crops.VineType;
import cloud.lemonslice.teastory.common.environment.crop.CropInfoManager;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import static cloud.lemonslice.teastory.common.item.ItemRegistry.getNormalItemProperties;

public class VineSeedsItem extends BlockNamedItem
{
    private final VineType type;

    public VineSeedsItem(String name, VineType type, Food food)
    {
        super(type.getFruit(), getNormalItemProperties().food(food));
        this.setRegistryName(name);
        this.type = type;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof TrellisBlock && !(state.getBlock() instanceof TrellisWithVineBlock))
        {
            if (world.getBlockState(pos.down()).getBlock().isIn(Tags.Blocks.DIRT))
            {
                world.setBlockState(pos, CropInfoManager.getVineTrellis(type, (TrellisBlock) state.getBlock()).getRelevantState(state));
                context.getItem().shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType();
    }
}
