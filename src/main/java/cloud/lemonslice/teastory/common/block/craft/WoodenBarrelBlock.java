package cloud.lemonslice.teastory.common.block.craft;

import cloud.lemonslice.silveroak.common.block.NormalBlock;
import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.item.ItemRegistry;
import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import cloud.lemonslice.teastory.common.tileentity.WoodenBarrelTileEntity;
import cloud.lemonslice.teastory.data.tag.NormalTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class WoodenBarrelBlock extends NormalBlock
{
    private static final VoxelShape SHAPE;

    public WoodenBarrelBlock()
    {
        super("wooden_barrel", Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.6F).notSolid());
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntityTypeRegistry.WOODEN_BARREL.create();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(player.getHeldItem(handIn), 1)).isPresent())
            {
                return te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getFace()).map(fluidTank ->
                {
                    FluidUtil.interactWithFluidHandler(player, handIn, fluidTank);
                    return ActionResultType.SUCCESS;
                }).orElse(ActionResultType.FAIL);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof WoodenBarrelTileEntity)
        {
            int i = ((WoodenBarrelTileEntity) te).getFluidAmount();
            float f = pos.getY() + 0.0625F + 0.875F * i / 2000;
            if (!worldIn.isRemote)
            {
                if (entityIn.isBurning())
                {
                    if (((WoodenBarrelTileEntity) te).getFluid().isIn(FluidTags.WATER) && i > 250 && entityIn.getPosY() <= f)
                    {
                        entityIn.extinguish();
                    }
                }
                else if (entityIn instanceof ItemEntity && ((WoodenBarrelTileEntity) te).getFluid() == Fluids.WATER)
                {
                    ItemStack item = ((ItemEntity) entityIn).getItem();
                    if (item.getItem().isIn(NormalTags.Items.CROPS_RICE))
                    {
                        ((ItemEntity) entityIn).setItem(new ItemStack(ItemRegistry.WASHED_RICE, item.getCount()));
                    }
                }
            }
        }
    }

    static
    {
        VoxelShape outer = VoxelShapeHelper.createVoxelShape(1, 0, 1, 14, 16, 14);
        VoxelShape inner = VoxelShapeHelper.createVoxelShape(2, 1, 2, 12, 15, 12);
        SHAPE = VoxelShapes.combineAndSimplify(outer, inner, IBooleanFunction.NOT_SAME);
    }
}
