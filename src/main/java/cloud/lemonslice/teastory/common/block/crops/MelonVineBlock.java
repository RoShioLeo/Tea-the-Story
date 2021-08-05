package cloud.lemonslice.teastory.common.block.crops;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MelonVineBlock extends BushBlock implements IGrowable
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
    private static final VoxelShape SHAPE = VoxelShapeHelper.createVoxelShape(0, 0, 0, 16, 5, 16);
    private static final VoxelShape SHAPE_MELON = VoxelShapeHelper.createVoxelShape(0, 0, 0, 16, 13, 16);
    private final Block melon;

    public MelonVineBlock(String id, Block melon)
    {
        super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP));
        this.melon = melon;
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
        this.setRegistryName(id);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return state.get(AGE) == 7 ? SHAPE_MELON : SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (player.getHeldItem(handIn).getItem() == Items.SHEARS && state.get(AGE) == 7)
        {
            player.getHeldItem(handIn).damageItem(1, player, e -> e.sendBreakAnimation(handIn));
            worldIn.setBlockState(pos, this.getDefaultState());
            if (!worldIn.isRemote)
            {
                Block.spawnAsEntity(worldIn, pos, new ItemStack(melon));
                worldIn.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ActionResultType.SUCCESS;
        }
        else return ActionResultType.PASS;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return state.isIn(Tags.Blocks.DIRT) || state.getBlock() instanceof FarmlandBlock || hasSolidSideOnTop(worldIn, pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isAreaLoaded(pos, 1))
        {
            return;
        }
        if (!canGrow(worldIn, pos, state, false))
        {
            return;
        }
        if (hasNearMelon(worldIn, pos, this))
        {
            return;
        }
        if (worldIn.getLightSubtracted(pos, 0) >= 9)
        {
            float f = getGrowthChance(this, worldIn, pos);
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0))
            {
                int i = state.get(AGE);
                if (i + 1 < 7)
                {
                    worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);

                    BlockPos blockPos = pos.offset(Direction.Plane.HORIZONTAL.random(random));
                    BlockState blockState = worldIn.getBlockState(blockPos);
                    if (blockState.isAir() && this.isValidGround(worldIn.getBlockState(blockPos.down()), worldIn, blockPos.down()))
                    {
                        worldIn.setBlockState(blockPos, this.getDefaultState());
                    }
                }
                else
                {
                    worldIn.setBlockState(pos, state.with(AGE, 7), 2);
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
            }
        }
    }

    public static boolean hasNearMelon(IBlockReader worldIn, BlockPos pos, Block melon)
    {
        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            BlockState state = worldIn.getBlockState(pos.offset(direction));
            if (state.matchesBlock(melon) && state.get(AGE) == 7)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return state.get(AGE) < 7 && worldIn.getBlockState(pos.down()).getBlock() instanceof FarmlandBlock;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        int i = Math.min(7, state.get(AGE) + MathHelper.nextInt(worldIn.rand, 2, 5));
        worldIn.setBlockState(pos, state.with(AGE, i), 2);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        return PlantType.CROP;
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
                if (blockstate.canSustainPlant(worldIn, blockpos.add(i, 0, j), Direction.UP, (IPlantable) blockIn))
                {
                    f1 = 1.0F;
                    if (blockstate.isFertile(worldIn, pos.add(i, 0, j)))
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
    @SuppressWarnings("deprecation")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
    {
        return state.get(AGE) == 7 ? Lists.newArrayList(new ItemStack(Blocks.MELON)) : Collections.emptyList();
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
    {
        return new ItemStack(Items.MELON_SEEDS);
    }
}
