package roito.teastory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.item.ItemRegister;

import javax.annotation.Nullable;
import java.util.Random;

public class StrawBlanket extends BlockHorizontal
{
	public static final PropertyEnum<StrawBlanket.EnumPartType> PART = PropertyEnum.<StrawBlanket.EnumPartType>create("part", StrawBlanket.EnumPartType.class);
	public static final PropertyBool OCCUPIED = PropertyBool.create("occupied");
	protected static final AxisAlignedBB STRAWBLANKET_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

	public StrawBlanket()
	{
		super(Material.GRASS);
		this.setSoundType(SoundType.PLANT);
		this.setHardness(0.2F);
		this.disableStats();
		this.setTranslationKey("straw_blanket");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "straw_blanket"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(PART, StrawBlanket.EnumPartType.FOOT).withProperty(OCCUPIED, Boolean.valueOf(false)));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else
		{
			if (state.getValue(PART) != StrawBlanket.EnumPartType.HEAD)
			{
				pos = pos.offset(state.getValue(FACING));
				state = worldIn.getBlockState(pos);

				if (state.getBlock() != this)
				{
					return true;
				}
			}

			if (worldIn.provider.canRespawnHere() && worldIn.getBiome(pos) != Biomes.HELL)
			{
				if (state.getValue(OCCUPIED).booleanValue())
				{
					EntityPlayer entityplayer = this.getPlayerInBlanket(worldIn, pos);

					if (entityplayer != null)
					{
						playerIn.sendMessage(new TextComponentTranslation("tile.bed.occupied", new Object[0]));
						return true;
					}

					state = state.withProperty(OCCUPIED, Boolean.valueOf(false));
					worldIn.setBlockState(pos, state, 4);
				}

				EntityPlayer.SleepResult entityplayer$sleepresult = playerIn.trySleep(pos);

				if (entityplayer$sleepresult == EntityPlayer.SleepResult.OK)
				{
					state = state.withProperty(OCCUPIED, Boolean.valueOf(true));
					worldIn.setBlockState(pos, state, 4);
					return true;
				}
				else
				{
					if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW)
					{
						playerIn.sendMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]));
					}
					else if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_SAFE)
					{
						playerIn.sendMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]));
					}

					return true;
				}
			}
			else
			{
				worldIn.setBlockToAir(pos);
				BlockPos blockpos = pos.offset(state.getValue(FACING).getOpposite());

				if (worldIn.getBlockState(blockpos).getBlock() == this)
				{
					worldIn.setBlockToAir(blockpos);
				}

				worldIn.newExplosion((Entity) null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 5.0F, true, true);
				return true;
			}
		}
	}

	@Nullable
	private EntityPlayer getPlayerInBlanket(World worldIn, BlockPos pos)
	{
		for (EntityPlayer entityplayer : worldIn.playerEntities)
		{
			if (entityplayer.isPlayerSleeping() && entityplayer.bedLocation.equals(pos))
			{
				return entityplayer;
			}
		}

		return null;
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
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		EnumFacing enumfacing = state.getValue(FACING);

		if (state.getValue(PART) == StrawBlanket.EnumPartType.HEAD)
		{
			if (worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getBlock() != this)
			{
				worldIn.setBlockToAir(pos);
			}
		}
		else if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this)
		{
			worldIn.setBlockToAir(pos);

			if (!worldIn.isRemote)
			{
				this.dropBlockAsItem(worldIn, pos, state, 0);
			}
		}
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return state.getValue(PART) == StrawBlanket.EnumPartType.HEAD ? null : ItemRegister.straw_blanket;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return STRAWBLANKET_AABB;
	}

	@Nullable
	public static BlockPos getSafeExitLocation(World worldIn, BlockPos pos, int tries)
	{
		EnumFacing enumfacing = worldIn.getBlockState(pos).getValue(FACING);
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		for (int l = 0; l <= 1; ++l)
		{
			int i1 = i - enumfacing.getXOffset() * l - 1;
			int j1 = k - enumfacing.getZOffset() * l - 1;
			int k1 = i1 + 2;
			int l1 = j1 + 2;

			for (int i2 = i1; i2 <= k1; ++i2)
			{
				for (int j2 = j1; j2 <= l1; ++j2)
				{
					BlockPos blockpos = new BlockPos(i2, j, j2);

					if (hasRoomForPlayer(worldIn, blockpos))
					{
						if (tries <= 0)
						{
							return blockpos;
						}

						--tries;
					}
				}
			}
		}

		return null;
	}

	protected static boolean hasRoomForPlayer(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos.down()).isTopSolid() && !worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.getBlockState(pos.up()).getMaterial().isSolid();
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		if (state.getValue(PART) == StrawBlanket.EnumPartType.FOOT)
		{
			super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
		}
	}

	@Override
	public EnumPushReaction getPushReaction(IBlockState state)
	{
		return EnumPushReaction.DESTROY;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(ItemRegister.straw_blanket);
	}

	@Override
	public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity player)
	{
		return true;
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (player.capabilities.isCreativeMode && state.getValue(PART) == StrawBlanket.EnumPartType.HEAD)
		{
			BlockPos blockpos = pos.offset(state.getValue(FACING).getOpposite());

			if (worldIn.getBlockState(blockpos).getBlock() == this)
			{
				worldIn.setBlockToAir(blockpos);
			}
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.byHorizontalIndex(meta);
		return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, StrawBlanket.EnumPartType.HEAD).withProperty(FACING, enumfacing).withProperty(OCCUPIED, Boolean.valueOf((meta & 4) > 0)) : this.getDefaultState().withProperty(PART, StrawBlanket.EnumPartType.FOOT).withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		if (state.getValue(PART) == StrawBlanket.EnumPartType.FOOT)
		{
			IBlockState iblockstate = worldIn.getBlockState(pos.offset(state.getValue(FACING)));

			if (iblockstate.getBlock() == this)
			{
				state = state.withProperty(OCCUPIED, iblockstate.getValue(OCCUPIED));
			}
		}

		return state;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | state.getValue(FACING).getHorizontalIndex();

		if (state.getValue(PART) == StrawBlanket.EnumPartType.HEAD)
		{
			i |= 8;

			if (state.getValue(OCCUPIED).booleanValue())
			{
				i |= 4;
			}
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]{FACING, PART, OCCUPIED});
	}

	public static enum EnumPartType implements IStringSerializable
	{
		HEAD("head"),
		FOOT("foot");

		private final String name;

		private EnumPartType(String name)
		{
			this.name = name;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}
}
