package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import roito.teastory.TeaStory;
import roito.teastory.helper.EntironmentHelper;
import roito.teastory.item.ItemRegister;

import java.util.Random;

public class XianRicePlant extends BlockBush implements IGrowable
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    private static final AxisAlignedBB[] SEEDLING_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public XianRicePlant()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setCreativeTab((CreativeTabs) null);
        this.setTranslationKey("xian_rice_plant");
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "xian_rice_plant"));
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == BlockRegister.paddy_field;
    }

    protected PropertyInteger getAgeProperty()
    {
        return AGE;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{AGE});
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SEEDLING_AABB[state.getValue(this.getAgeProperty()).intValue()];
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Crop;
    }

    protected Item getSeed()
    {
        return ItemRegister.xian_rice_seedlings;
    }

    protected Item getCrop()
    {
        return ItemRegister.xian_rice_seeds;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.isMaxAge(state) ? this.getCrop() : this.getSeed();
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(getSeed());
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int age = state.getValue(AGE).intValue();
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        if (age >= 7)
        {
            drops.add(new ItemStack(this.getCrop(), 4));
            if (rand.nextBoolean())
            {
                drops.add(new ItemStack(ItemRegister.straw, 1));
            }
        } else
        {
            drops.add(new ItemStack(this.getSeed(), 1));
        }
    }

    public int getMaxAge()
    {
        return 7;
    }

    protected int getAge(IBlockState state)
    {
        return state.getValue(this.getAgeProperty()).intValue();
    }

    public IBlockState withAge(int age)
    {
        return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean isMaxAge(IBlockState state)
    {
        return state.getValue(this.getAgeProperty()).intValue() >= this.getMaxAge();
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.checkAndDropBlock(worldIn, pos, state);
        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge())
            {
                float f = getGrowthChance(this, worldIn, pos);

                if (f > 0.0F && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
        int i = this.getAge(state);
        if ((worldIn.getBlockState(pos.down()).getBlock() != BlockRegister.field) && (i >= 4))
        {
            worldIn.setBlockState(pos.down(), BlockRegister.field.getDefaultState());
        }
    }

    protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
        Biome biome = worldIn.getBiome(pos);
        float humidity = biome.getRainfall();
        float temperature = biome.getTemperature(pos);
        if (worldIn.isRainingAt(pos))
        {
            return 8 * EntironmentHelper.getRiceCropsGrowPercent(temperature, humidity) * 1.25F;
        }
        return 8 * EntironmentHelper.getRiceCropsGrowPercent(temperature, humidity);
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        Block blockDown = worldIn.getBlockState(pos.down()).getBlock();
        return blockDown == BlockRegister.paddy_field || blockDown == BlockRegister.field;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.withAge(meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return this.getAge(state);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return !this.isMaxAge(state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state);
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j)
        {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    protected int getBonemealAgeIncrease(World worldIn)
    {
        return MathHelper.getInt(worldIn.rand, 2, 5);
    }
}
