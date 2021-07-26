package cloud.lemonslice.teastory.common.block.decorations;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.List;


public class TableBlock extends NormalBlock
{
    public static final VoxelShape SHAPE;

    public TableBlock(String name, Properties properties)
    {
        super(name, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
        return 60;
    }

    @Override
    @SuppressWarnings("deprecation")
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return 1.0F;
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(this));
    }

    static
    {
        VoxelShape one = VoxelShapeHelper.createVoxelShape(1, 0, 1, 2, 14, 2);
        VoxelShape two = VoxelShapeHelper.createVoxelShape(13, 0, 1, 2, 14, 2);
        VoxelShape three = VoxelShapeHelper.createVoxelShape(1, 0, 13, 2, 14, 2);
        VoxelShape four = VoxelShapeHelper.createVoxelShape(13, 0, 13, 2, 14, 2);
        VoxelShape table = VoxelShapeHelper.createVoxelShape(0, 14, 0, 16, 2, 16);
        SHAPE = VoxelShapes.or(one, two, three, four, table);
    }
}
