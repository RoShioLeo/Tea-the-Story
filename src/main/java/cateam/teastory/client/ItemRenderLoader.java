package cateam.teastory.client;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.item.ItemLoader;
import net.minecraft.item.Item;

public class ItemRenderLoader
{

	public ItemRenderLoader()
    {
		ItemLoader.registerRenders();
		BlockLoader.registerRenders();
    }
}
