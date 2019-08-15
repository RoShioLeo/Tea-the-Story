package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.entity.ISeat;

import java.util.List;

public class StrawCushion extends Block implements ISeat
{
    private static final AxisAlignedBB STRAWCUSHION_AABB = new AxisAlignedBB(0.125F, 0.0F, 0.125F, 0.875F, 0.125F, 0.875F);

    public StrawCushion()
    {
        super(Material.GRASS);
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.2F);
        this.setCreativeTab(CreativeTabsRegister.tabRice);
        this.setTranslationKey("straw_cushion");
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "straw_cushion"));
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return STRAWCUSHION_AABB;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn instanceof FakePlayer || playerIn.getRidingEntity() != null)
        {
            return false;
        }

        Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

        double maxDist = 2;
        if ((vec.x - playerIn.posX) * (vec.x - playerIn.posX) + (vec.y - playerIn.posY) * (vec.y - playerIn.posY) + (vec.z - playerIn.posZ) * (vec.z - playerIn.posZ) > maxDist * maxDist)
        {
            return false;
        }

        ItemStack stack1 = playerIn.getHeldItemMainhand();
        ItemStack stack2 = playerIn.getHeldItemOffhand();
        if (!stack1.isEmpty() || !stack2.isEmpty())
        {
            return false;
        }

        if (state.getBlock() instanceof ISeat)
        {
            List<Seat> seats = worldIn.getEntitiesWithinAABB(Seat.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));

            if (seats.isEmpty())
            {
                Vec3d v = ((ISeat) state.getBlock()).getSeat(state);
                Seat seat = new Seat(worldIn, v.add(pos.getX(), pos.getY(), pos.getZ()));
                worldIn.spawnEntity(seat);
                playerIn.startRiding(seat);
                return true;
            }
        }
        return false;
    }

    @Override
    public Vec3d getSeat(IBlockState state)
    {
        return new Vec3d(0.5, 0.0625, 0.5);
    }
}
