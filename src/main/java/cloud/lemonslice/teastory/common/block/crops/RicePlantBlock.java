package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class RicePlantBlock extends CropsBlock
{
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public RicePlantBlock(String name)
    {
        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP));
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
        this.setRegistryName(name);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return state.getBlock() == BlockRegistry.PADDY_FIELD;
    }

    protected boolean canPlantSeedlings(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return isValidGround(state, worldIn, pos) && state.get(WATERLOGGED);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.down();
        if (state.get(AGE) <= 0)
            return canPlantSeedlings(worldIn.getBlockState(blockpos), worldIn, blockpos);
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        if (worldIn.getLightSubtracted(pos, 0) >= 9)
        {
            int i = this.getAge(state);
            if (i < this.getMaxAge())
            {
                BlockState paddy = worldIn.getBlockState(pos.down());
                if (!(paddy.getBlock() instanceof PaddyFieldBlock))
                {
                    return;
                }
                if (i <= 2 && !paddy.get(WATERLOGGED))
                {
                    return;
                }
                else if (i >= 6 && paddy.get(WATERLOGGED))
                {
                    return;
                }
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (50.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);

                    if (!isNearbyTidy(i + 1, worldIn, pos))
                    {
                        growTogether(i + 1, worldIn, pos.north());
                        growTogether(i + 1, worldIn, pos.south());
                        growTogether(i + 1, worldIn, pos.east());
                        growTogether(i + 1, worldIn, pos.west());
                    }
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        tick(state, worldIn, pos, random);
    }

    private boolean isNearbyTidy(int age, ServerWorld worldIn, BlockPos pos)
    {
        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            BlockState state = worldIn.getBlockState(pos.offset(direction));
            if (state.getBlock() == this)
            {
                if (age - state.get(AGE) > 2)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void growTogether(int ageToGrow, ServerWorld worldIn, BlockPos pos)
    {
        BlockState state = worldIn.getBlockState(pos);
        if (state.getBlock() == this)
        {
            if (state.get(AGE) < ageToGrow - 2)
            {
                worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
                growTogether(ageToGrow, worldIn, pos.north());
                growTogether(ageToGrow, worldIn, pos.south());
                growTogether(ageToGrow, worldIn, pos.east());
                growTogether(ageToGrow, worldIn, pos.west());
            }
        }
    }

    @Override
    protected IItemProvider getSeedsItem()
    {
        return ItemRegistry.RICE_GRAINS;
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
    {
        int age = state.get(AGE);
        if (age > 0)
        {
            return new ItemStack(ItemRegistry.RICE_GRAINS);
        }
        else return new ItemStack(ItemRegistry.RICE_SEEDLINGS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        List<ItemStack> list = Lists.newArrayList();
        if (getAge(state) < 3)
        {
            list.add(new ItemStack(ItemRegistry.RICE_SEEDLINGS));
        }
        else if (getAge(state) < 7)
        {
            list.add(new ItemStack(ItemRegistry.WET_STRAW));
        }
        else
        {
            list.add(new ItemStack(ItemRegistry.WET_STRAW));
            list.add(new ItemStack(ItemRegistry.RICE_GRAINS, builder.getWorld().rand.nextInt(4) + 1));
        }
        return list;
    }
}
