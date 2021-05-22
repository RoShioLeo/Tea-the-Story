package cloud.lemonslice.teastory.common.tileentity;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class NormalContainerTileEntity extends TileEntity implements INamedContainerProvider
{
    private boolean prepareForRemove = false;

    public NormalContainerTileEntity(TileEntityType<?> tileEntityType)
    {
        super(tileEntityType);
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbtTag = this.write(new CompoundNBT());
        return new SUpdateTileEntityPacket(getPos(), 1, nbtTag);
    }

    public void refresh()
    {
        if (this.hasWorld() && !this.world.isRemote && !prepareForRemove)
        {
            SUpdateTileEntityPacket packet = this.getUpdatePacket();
            Stream<ServerPlayerEntity> playerEntity = ((ServerWorld) this.world).getChunkProvider().chunkManager.getTrackingPlayers(new ChunkPos(this.pos.getX() >> 4, this.pos.getZ() >> 4), false);
            for (ServerPlayerEntity player : playerEntity.collect(Collectors.toList()))
            {
                if (!isOpeningContainer(player.openContainer))
                {
                    player.connection.sendPacket(packet);
                }
            }
        }
    }

    public void prepareForRemove()
    {
        this.prepareForRemove = true;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent("container.teastory." + getType().getRegistryName().getPath());
    }

    protected abstract boolean isOpeningContainer(Container container);
}
