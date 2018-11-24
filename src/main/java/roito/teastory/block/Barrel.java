package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.inventory.GuiElementRegister;
import roito.teastory.tileentity.TileEntityBarrel;

public class Barrel extends BlockContainer implements ITileEntityProvider
{
    public Barrel()
    {
        super(Material.WOOD);
        this.setHardness(0.5F);
        this.setSoundType(SoundType.WOOD);
        this.setTranslationKey("barrel");
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "barrel"));
        this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0));
        this.setCreativeTab(CreativeTabsRegister.tabTeaStory);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
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
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(BlockRegister.barrel, 1));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{STEP});
    }

    @Override
    public IBlockState getStateFromMeta(int step)
    {
        return this.getDefaultState().withProperty(this.getStepProperty(), Integer.valueOf(step));
    }

    protected PropertyInteger getStepProperty()
    {
        return STEP;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(this.getStepProperty()).intValue();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            int id = GuiElementRegister.GUI_BARREL;
            playerIn.openGui(TeaStory.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntityBarrel te = (TileEntityBarrel) worldIn.getTileEntity(pos);
        IItemHandler inventory = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);

        for (int i = inventory.getSlots() - 1; i >= 0; --i)
        {
            if (inventory.getStackInSlot(i) != ItemStack.EMPTY)
            {
                Block.spawnAsEntity(worldIn, pos, inventory.getStackInSlot(i));
                ((IItemHandlerModifiable) inventory).setStackInSlot(i, ItemStack.EMPTY);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    public static void setState(int step, World worldIn, BlockPos pos)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        worldIn.setBlockState(pos, BlockRegister.barrel.getStateFromMeta(step));

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityBarrel();
    }

    public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 4);
}