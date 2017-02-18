package starryskyline.teastory.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import starryskyline.teastory.TeaStory;
import starryskyline.teastory.client.gui.GuiContainerTeaStove;

public class GuiElementLoader implements IGuiHandler
{
	public static final int GUI_TEASTOVE = 1;

    public GuiElementLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(TeaStory.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
        case GUI_TEASTOVE:
            return new ContainerTeaStove(player, world.getTileEntity(new BlockPos(x, y, z)));
        default:
            return null;
        }
    }


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
        case GUI_TEASTOVE:
            return new GuiContainerTeaStove(new ContainerTeaStove(player, world.getTileEntity(new BlockPos(x, y, z))));
        default:
            return null;
        }
    }


}
