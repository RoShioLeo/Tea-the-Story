package roito.teastory.client;

import roito.teastory.block.BlockRegister;
import roito.teastory.item.ItemRegister;

public class ItemRenderLoader
{

	public ItemRenderLoader()
	{
		ItemRegister.registerRenders();
		BlockRegister.registerRenders();
	}
}
