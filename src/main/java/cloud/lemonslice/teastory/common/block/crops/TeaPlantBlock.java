package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.teastory.common.item.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

import java.util.List;
import java.util.Random;

public class TeaPlantBlock extends BushBlock implements IGrowable
{
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 11);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public TeaPlantBlock()
    {
        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP));
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), 0));
        this.setRegistryName("tea_plant");
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    public IntegerProperty getAgeProperty()
    {
        return AGE;
    }

    public int getMaxAge()
    {
        return 11;
    }

    protected int getAge(BlockState state)
    {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age)
    {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    public boolean isMaxAge(BlockState state)
    {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        List<ItemStack> list = super.getDrops(state, builder);
        list.add(new ItemStack(ItemRegistry.TEA_SEEDS));
        int age = state.get(AGE);
        if (age >= 2)
        {
            list.add(new ItemStack(Items.STICK, 2));
        }
        if (age >= 6 && age <= 10)
        {
            list.add(new ItemStack(ItemRegistry.TEA_LEAVES, 2));
        }
        else if (age >= 11)
        {
            list.add(new ItemStack(ItemRegistry.TEA_LEAVES, 1));
            list.add(new ItemStack(ItemRegistry.TEA_SEEDS, 4));
        }
        return list;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        super.tick(state, worldIn, pos, random);
        if (!worldIn.isAreaLoaded(pos, 1) || !worldIn.getDimensionType().hasSkyLight())
            return;
        if (worldIn.getLightSubtracted(pos, 0) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge() && i != 8)
            {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    protected int getBonemealAgeIncrease(World worldIn)
    {
        return 2;
    }

    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos)
    {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.add(i, 0, j), net.minecraft.util.Direction.UP, (net.minecraftforge.common.IPlantable) blockIn))
                {
                    f1 = 1.0F;
                    if (blockstate.isFertile(worldIn, blockpos.add(i, 0, j)))
                    {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        return PlantType.CROP;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote)
        {
            switch (this.getAge(state))
            {
                case 8:
                    worldIn.setBlockState(pos, this.getDefaultState().with(AGE, worldIn.rand.nextInt(3) + 4));
                    worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(ItemRegistry.TEA_LEAVES, worldIn.rand.nextInt(5) + 1)));
                    return ActionResultType.SUCCESS;
                case 11:
                    worldIn.setBlockState(pos, this.getDefaultState().with(AGE, worldIn.rand.nextInt(3) + 4));
                    worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(ItemRegistry.TEA_SEEDS, worldIn.rand.nextInt(5) + 1)));
                    worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, new ItemStack(ItemRegistry.TEA_LEAVES, 1)));
                    return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }
        else
        {
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return state.getBlock() instanceof FarmlandBlock;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof RavagerEntity && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn))
        {
            worldIn.destroyBlock(pos, true);
        }
        if (entityIn instanceof LivingEntity)
        {
            entityIn.setMotionMultiplier(state, new Vector3d(0.8F, 0.75D, 0.8F));
        }

        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    protected IItemProvider getSeedsItem()
    {
        return ItemRegistry.TEA_SEEDS;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
    {
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return !this.isMaxAge(state) && state.get(this.getAgeProperty()) != 8;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        if (!worldIn.getDimensionType().isNatural())
        {
            return;
        }
        int i = this.getAge(state);
        if (i == 6)
        {
            worldIn.setBlockState(pos, this.withAge(9), 2);
            return;
        }
        else
        {
            i += this.getBonemealAgeIncrease(worldIn);
        }
        i = Math.min(i, this.getMaxAge());

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }
}
