package cloud.lemonslice.teastory.common.block.drink;

import cloud.lemonslice.silveroak.helper.VoxelShapeHelper;
import cloud.lemonslice.teastory.common.fluid.HotWaterFlowingFluidBlock;
import cloud.lemonslice.teastory.common.tileentity.TeapotTileEntity;
import cloud.lemonslice.teastory.common.tileentity.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class IronKettleBlock extends TeapotBlock
{
    private static final VoxelShape SHAPE = VoxelShapeHelper.createVoxelShape(2, 0, 2, 12, 11, 12);

    public IronKettleBlock(String name, Properties properties)
    {
        super(name, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TileEntityTypeRegistry.IRON_KETTLE.create();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TeapotTileEntity && ((TeapotTileEntity) tileentity).getFluid().getAttributes().getTemperature() >= 333)
        {
            double d0 = pos.getX() + 0.5D;
            double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = pos.getZ() + 0.5D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            worldIn.addParticle(ParticleTypes.CLOUD, false, d0 + d4, d1 + 0.5D, d2 + d4, 0.0D, 0.1D, 0.0D);
        }
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        entityIn.onLivingFall(fallDistance, 1.0F);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TeapotTileEntity && ((TeapotTileEntity) te).getFluid().getAttributes().getTemperature() >= 333)
            {
                entityIn.attackEntityFrom(HotWaterFlowingFluidBlock.BOILING, 1.0F);
            }
        }
    }
}
