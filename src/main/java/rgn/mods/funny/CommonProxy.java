package rgn.mods.funny;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public int getNewBlockRenderType()
	{
		return 0;
	}

	public void registerRenderingHandler() {}

	public void registerTileEntity()
	{
		GameRegistry.registerTileEntity(TileEntityTrinityBlock.class, "TileEntityTrinityBlock");
	}
}
