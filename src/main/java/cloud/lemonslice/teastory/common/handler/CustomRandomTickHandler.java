package cloud.lemonslice.teastory.common.handler;

import cloud.lemonslice.teastory.TeaStory;
import cloud.lemonslice.teastory.common.config.ServerConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ChunkHolder;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = TeaStory.MODID)
public final class CustomRandomTickHandler
{
    private static final CustomRandomTick SNOW_MELT = (state, world, pos) ->
    {
        BlockPos blockpos = world.getHeight(Heightmap.Type.MOTION_BLOCKING, world.getBlockRandomPos(pos.getX(), 0, pos.getZ(), 15));
        if (world.isAreaLoaded(blockpos, 1) && world.getBiome(blockpos).getTemperature(blockpos) >= 0.15F)
        {
            BlockState topState = world.getBlockState(blockpos);
            if (topState.getBlock().equals(Blocks.SNOW))
            {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
            }
            else
            {
                BlockState downState = world.getBlockState(blockpos.down());
                if (downState.getBlock().equals(Blocks.ICE))
                {
                    world.setBlockState(blockpos.down(), Blocks.WATER.getDefaultState());
                }
            }
        }
    };

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase.equals(TickEvent.Phase.END) && ServerConfig.Temperature.iceMelt.get() && !event.world.isRemote)
        {
            ServerWorld world = (ServerWorld) event.world;
            int randomTickSpeed = world.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED);
            if (randomTickSpeed > 0)
            {
                world.getChunkProvider().chunkManager.getLoadedChunksIterable().forEach(chunkHolder ->
                {
                    Optional<Chunk> optional = chunkHolder.getEntityTickingFuture().getNow(ChunkHolder.UNLOADED_CHUNK).left();
                    if (optional.isPresent())
                    {
                        Chunk chunk = optional.get();
                        for (ChunkSection chunksection : chunk.getSections())
                        {
                            if (chunksection != Chunk.EMPTY_SECTION && chunksection.needsRandomTickAny())
                            {
                                int x = chunk.getPos().getXStart();
                                int y = chunksection.getYLocation();
                                int z = chunk.getPos().getZStart();

                                for (int l = 0; l < randomTickSpeed; ++l)
                                {
                                    if (world.rand.nextInt(32) == 0)
                                    {
                                        doCustomRandomTick(world, x, y, z);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private static void doCustomRandomTick(ServerWorld world, int x, int y, int z)
    {
        if (ServerConfig.Temperature.iceMelt.get())
        {
            SNOW_MELT.tick(null, world, new BlockPos(x, y, z));
        }
    }
}
