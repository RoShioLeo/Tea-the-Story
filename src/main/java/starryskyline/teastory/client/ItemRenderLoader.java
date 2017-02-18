package starryskyline.teastory.client;

import net.minecraft.item.Item;
import starryskyline.teastory.block.BlockLoader;
import starryskyline.teastory.item.ItemLoader;

public class ItemRenderLoader
{

	public ItemRenderLoader()
    {
		ItemLoader.registerRenders();
		BlockLoader.registerRenders();
    }
}
