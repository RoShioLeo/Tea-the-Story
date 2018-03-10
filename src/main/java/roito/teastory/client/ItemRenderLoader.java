package roito.teastory.client;

import roito.teastory.block.BlockLoader;
import roito.teastory.item.ItemLoader;

public class ItemRenderLoader
{

	public ItemRenderLoader()
	{
		ItemLoader.registerRenders();
		BlockLoader.registerRenders();
	}
}
