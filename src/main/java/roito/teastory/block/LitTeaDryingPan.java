package roito.teastory.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.item.ItemRegister;
import roito.teastory.tileentity.TileEntityTeaDryingPan;

import java.util.Random;

public class LitTeaDryingPan extends BlockContainer
{
    protected static final AxisAlignedBB TEADRYINGPAN_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
    public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 6);
    public static final PropertyBool TYPE = PropertyBool.create("type");

    public LitTeaDryingPan()
    {
        super(Material.IRON);
        this.setHardness(3.0F);
        this.setSoundType(SoundType.METAL);
        this.setLightLevel(0.875F);
        this.setTranslationKey("lit_tea_drying_pan");
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "lit_tea_drying_pan"));
        this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0).withProperty(TYPE, false));
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
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityTeaDryingPan();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(BlockRegister.tea_drying_pan, 1));
        int meta = getMetaFromState(state);
        if ((meta >= 1) && (meta <= 4))
        {
            drops.add(new ItemStack(ItemRegister.tea_leaf, 8));
        } else if (meta == 5)
        {
            drops.add(new ItemStack(ItemRegister.dried_tea, 8));
        } else if ((meta >= 9) && (meta <= 12))
        {
            drops.add(new ItemStack(ItemRegister.half_dried_tea, 8));
        } else if (meta == 13)
        {
            drops.add(new ItemStack(ItemRegister.oolong_tea_leaf, 8));
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return TEADRYINGPAN_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        int meta = getMetaFromState(worldIn.getBlockState(pos));
        double d0 = pos.getX();
        double d1 = pos.getY();
        double d2 = pos.getZ();
        if (meta != 6 && meta != 14)
        {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.08D, 0.0D, new int[0]);
        } else worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + 0.5D, d1 + 1.0D, d2 + 0.5D, 0.0D, 0.1D, 0.0D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.01D, 0.0D, 0.0D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.01D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), 0.0D, 0.0D, -0.01D, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + worldIn.rand.nextDouble(), d1 + 0.2D, d2 + worldIn.rand.nextDouble(), -0.01D, 0.0D, 0.0D, new int[0]);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        int meta = getMetaFromState(state);
        TileEntity te = worldIn.getTileEntity(pos);
        int seconds = 60;
        if (te != null && te instanceof TileEntityTeaDryingPan)
        {
            seconds = ((TileEntityTeaDryingPan) te).getRemainingTime() / 20;
        }
        if (meta == 0)
        {
            if (!playerIn.getHeldItem(hand).isEmpty())
            {
                if (playerIn.getHeldItem(hand).getCount() >= 8)
                {
                    if (playerIn.getHeldItem(hand).getItem() == ItemRegister.tea_leaf)
                    {
                        worldIn.setBlockState(pos, this.getStateFromMeta(1));
                        if (!playerIn.capabilities.isCreativeMode)
                        {
                            playerIn.getHeldItem(hand).shrink(8);
                        }
                        return true;
                    } else if (playerIn.getHeldItem(hand).getItem() == ItemRegister.half_dried_tea)
                    {
                        worldIn.setBlockState(pos, this.getStateFromMeta(9));
                        if (!playerIn.capabilities.isCreativeMode)
                        {
                            playerIn.getHeldItem(hand).shrink(8);
                        }
                        return true;
                    }
                }
            }
            if (worldIn.isRemote)
            {
                if (!playerIn.getHeldItem(hand).isEmpty() && playerIn.getHeldItem(hand).getCount() < 8 && (playerIn.getHeldItem(hand).getItem() == ItemRegister.tea_leaf || playerIn.getHeldItem(hand).getItem() == ItemRegister.half_dried_tea))
                {
                    playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.notenough"));
                    return true;
                }
                playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.leaf"));
            }
            return true;
        } else if ((meta & 7) == 1)
        {
            if (worldIn.isRemote)
            {
                playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.time.1", seconds));
            }
            return true;
        } else if ((meta & 7) == 2)
        {
            worldIn.setBlockState(pos, this.getStateFromMeta(meta + 1));
            if (worldIn.isRemote)
            {
                playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.speedup", seconds));
            }
            return true;
        } else if ((meta & 7) == 3)
        {
            if (worldIn.isRemote)
            {
                playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.time.2", seconds));
            }
            return true;
        } else if ((meta & 7) == 4)
        {
            worldIn.setBlockState(pos, getStateFromMeta(meta + 1));
            if (worldIn.isRemote)
            {
                playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.dried"));
            }
            return true;
        } else if ((meta & 7) == 5)
        {
            if (!worldIn.isRemote)
            {
                if (!state.getValue(TYPE).booleanValue())
                {
                    ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegister.dried_tea, 8));
                } else
                {
                    ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegister.oolong_tea_leaf, 8));
                }
            }
            worldIn.setBlockState(pos, BlockRegister.tea_drying_pan.getDefaultState());
            worldIn.removeTileEntity(pos);
            return true;
        } else
        {
            if (worldIn.isRemote)
            {
                playerIn.sendMessage(new TextComponentTranslation("teastory.message.tea_drying_pan.failure"));
            }
            worldIn.setBlockState(pos, BlockRegister.tea_drying_pan.getDefaultState());
            worldIn.removeTileEntity(pos);
            return true;
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(BlockRegister.tea_drying_pan, 1));
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(BlockRegister.tea_drying_pan);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STEP, TYPE);
    }

    protected PropertyInteger getStepProperty()
    {
        return STEP;
    }

    @Override
    public IBlockState getStateFromMeta(int step)
    {
        int meta = step & 7;
        boolean type = (step & 8) == 8;
        return this.getDefaultState().withProperty(this.getStepProperty(), Integer.valueOf(meta)).withProperty(LitTeaDryingPan.TYPE, type);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int type = state.getValue(TYPE).booleanValue() ? 8 : 0;
        return state.getValue(this.getStepProperty()).intValue() | type;
    }

    public static void setState(int meta, World worldIn, BlockPos pos)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        worldIn.setBlockState(pos, BlockRegister.lit_tea_drying_pan.getStateFromMeta(meta));
        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }
}
