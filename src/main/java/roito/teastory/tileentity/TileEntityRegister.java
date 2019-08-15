package roito.teastory.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import roito.teastory.TeaStory;

public class TileEntityRegister
{
    public TileEntityRegister()
    {
        registerTileEntity(TileEntityTeaStove.class, "TeaStove");
        registerTileEntity(TileEntityTeaDrink.class, "TeaDrink");
        registerTileEntity(TileEntityTeaDryingPan.class, "TeaDryingPan");
        registerTileEntity(TileEntityCookingPan.class, "CookingPan");
        registerTileEntity(TileEntityTeaTable.class, "TeaTable");
        registerTileEntity(TileEntityTeapan.class, "Teapan");
        registerTileEntity(TileEntityKettle.class, "Kettle");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(TeaStory.MODID, id));
    }
}
