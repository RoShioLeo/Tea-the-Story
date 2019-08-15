package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import roito.teastory.TeaStory;

import javax.annotation.Nullable;
import java.util.Random;

public class PaddyField extends Block
{
    protected static final AxisAlignedBB FIELD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);

    public PaddyField()
    {
        super(Material.WATER);
        this.setHardness(0.5F);
        this.setSoundType(SoundType.GROUND);
        this.setTranslationKey("paddy_field");
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "paddy_field"));
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(true)).withProperty(EAST, Boolean.valueOf(true)).withProperty(SOUTH, Boolean.valueOf(true)).withProperty(WEST, Boolean.valueOf(true)).withProperty(LEVEL, 0));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FIELD_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.DIRT);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(Blocks.DIRT));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, Boolean.valueOf(!isSameField(worldIn, pos.north()))).withProperty(EAST, Boolean.valueOf(!isSameField(worldIn, pos.east()))).withProperty(SOUTH, Boolean.valueOf(!isSameField(worldIn, pos.south()))).withProperty(WEST, Boolean.valueOf(!isSameField(worldIn, pos.west())));
    }

    public boolean isSameField(IBlockAccess worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock() == this;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!playerIn.getHeldItem(hand).isEmpty())
        {
            if (playerIn.getHeldItem(hand).getItem() == Items.BUCKET)
            {
                worldIn.setBlockState(pos, BlockRegister.field.getDefaultState());
                playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                return true;
            }
            if (playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET)
                return true;
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, NORTH, EAST, WEST, SOUTH, LEVEL);
    }
}
