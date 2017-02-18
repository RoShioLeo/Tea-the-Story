package starryskyline.teastory.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import starryskyline.teastory.TeaStory;

public class TileEntityLoader
{
	public TileEntityLoader(FMLPreInitializationEvent event)
    {
        registerTileEntity(TileEntityTeaStove.class, "TeaStove");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, TeaStory.MODID + ":" + id);
    }
}
