package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;
import java.util.List;

public class FilterScreenBlock extends NormalBlock
{
    private static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            VoxelShapeHelper.createVoxelShape(6.5, 0, 0, 3, 16, 16),
            VoxelShapeHelper.createVoxelShape(0, 6.5, 0, 16, 3, 16),
            VoxelShapeHelper.createVoxelShape(0, 0, 6.5, 16, 16, 3)};

    public FilterScreenBlock()
    {
        super("filter_screen", Properties.create(Material.WEB).sound(SoundType.WOOD).hardnessAndResistance(0.2F).doesNotBlockMovement().notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE[state.get(AXIS).ordinal()];
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!worldIn.isRemote && entityIn instanceof ItemEntity)
        {
            List<Item> list = getPassableItem(worldIn, pos);
            if (list.contains(((ItemEntity) entityIn).getItem().getItem()))
            {
                return;
            }
            Vector3d motion = entityIn.getMotion();
            if (motion.x != 0 || motion.z != 0)
            {
                entityIn.setMotion(0, 0, 0);
            }
        }
    }

    public static List<Item> getPassableItem(World worldIn, BlockPos pos)
    {
        BlockPos blockPos = pos.up();
        while (worldIn.getBlockState(blockPos).getBlock() instanceof FilterScreenBlock)
        {
            blockPos = blockPos.up();
        }
        if (worldIn.getTileEntity(blockPos) != null)
        {
            return worldIn.getTileEntity(blockPos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(h ->
            {
                List<Item> list = Lists.newArrayList();
                for (int i = 0; i < h.getSlots(); i++)
                {
                    list.add(h.getStackInSlot(i).getItem());
                }
                return list;
            }).orElse(Lists.newArrayList());
        }
        return Lists.newArrayList();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (player.getHeldItem(handIn).isEmpty())
        {
            if (state.get(AXIS).equals(Direction.Axis.X))
            {
                worldIn.setBlockState(pos, state.with(AXIS, Direction.Axis.Z));
            }
            else
            {
                worldIn.setBlockState(pos, state.with(AXIS, Direction.Axis.X));
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        if (context.getPlayer() != null)
        {
            return getDefaultState().with(AXIS, context.getPlayer().getHorizontalFacing().getAxis());
        }
        return getDefaultState();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AXIS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return Lists.newArrayList(new ItemStack(this));
    }
}
