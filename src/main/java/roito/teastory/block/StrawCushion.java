package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.entity.EntityStrawCushion;

import java.util.List;

public class StrawCushion extends Block
{
    private static final AxisAlignedBB STRAWCUSHION_AABB = new AxisAlignedBB(0.125F, 0.0F, 0.125F, 0.875F, 0.125F, 0.875F);

    public StrawCushion()
    {
        super(Material.GRASS);
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.2F);
        this.setCreativeTab(CreativeTabsRegister.tabRice);
        this.setUnlocalizedName("straw_cushion");
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
        if (!playerIn.isRiding() && sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 0))
        {
            return true;
        }
        return false;
    }

    public static boolean sitOnBlock(World world, double x, double y, double z, EntityPlayer entityPlayer, double yOffset)
    {
        if (!checkForExistingEntity(world, x, y, z, entityPlayer))
        {
            EntityStrawCushion nemb = new EntityStrawCushion(world, x, y, z, yOffset);
            world.spawnEntity(nemb);
            entityPlayer.startRiding(nemb);
        }
        return true;
    }

    public static boolean checkForExistingEntity(World world, double x, double y, double z, EntityPlayer entityPlayer)
    {
        List<EntityStrawCushion> listEMB = world.getEntitiesWithinAABB(EntityStrawCushion.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
        for (EntityStrawCushion mount : listEMB)
        {
            if (mount.blockPosX == x && mount.blockPosY == y && mount.blockPosZ == z)
            {
                if (!mount.isBeingRidden())
                {
                    entityPlayer.startRiding(mount);
                }
                return true;
            }
        }
        return false;
    }
}
