package cloud.lemonslice.teastory.common.block;

import cloud.lemonslice.teastory.common.item.ItemsRegistry;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
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
        return state.getBlock() == BlocksRegistry.PADDY_FIELD;
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
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        if (worldIn.getLightSubtracted(pos, 0) >= 9)
        {
            int i = this.getAge(state);
            if (i < this.getMaxAge())
            {
                if (i <= 3 && !worldIn.getBlockState(pos.down()).get(WATERLOGGED))
                {
                    return;
                }
                else if (i >= 6 && worldIn.getBlockState(pos.down()).get(WATERLOGGED))
                {
                    return;
                }
                float f = getGrowthChance(this, worldIn, pos) + getOneSideExtraGrowthChance(i, worldIn, pos.north()) + getOneSideExtraGrowthChance(i, worldIn, pos.south()) + getOneSideExtraGrowthChance(i, worldIn, pos.west()) + getOneSideExtraGrowthChance(i, worldIn, pos.east());
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                    doExtraRandomTick(worldIn.getBlockState(pos.north()), worldIn, pos.north(), rand);
                    doExtraRandomTick(worldIn.getBlockState(pos.south()), worldIn, pos.south(), rand);
                    doExtraRandomTick(worldIn.getBlockState(pos.west()), worldIn, pos.west(), rand);
                    doExtraRandomTick(worldIn.getBlockState(pos.east()), worldIn, pos.east(), rand);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void doExtraRandomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (state.getBlock() == this)
        {
            state.getBlock().randomTick(state, worldIn, pos, rand);
        }
    }

    protected float getOneSideExtraGrowthChance(int age, IBlockReader worldIn, BlockPos pos)
    {
        BlockState state = worldIn.getBlockState(pos);
        if (state.getBlock() == this && state.get(AGE) > age)
        {
            return state.get(AGE) - age;
        }
        return 0.0F;
    }

    @Override
    protected IItemProvider getSeedsItem()
    {
        return ItemsRegistry.RICE_SEEDS;
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
    {
        int age = state.get(AGE);
        if (age > 0)
        {
            return new ItemStack(ItemsRegistry.RICE_SEEDS);
        }
        else return new ItemStack(ItemsRegistry.RICE_SEEDLINGS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        List<ItemStack> list = Lists.newArrayList();
        if (getAge(state) < 7)
        {
            list.add(new ItemStack(ItemsRegistry.RICE_SEEDLINGS));
        }
        else
        {
            list.add(new ItemStack(ItemsRegistry.RICE_SEEDS, builder.getWorld().rand.nextInt(4) + 1));
        }
        return list;
    }
}
