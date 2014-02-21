package rgn.mods.funny.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import rgn.mods.funny.CommonProxy;
import rgn.mods.funny.TileEntityTrinityBlock;

public class ClientProxy extends CommonProxy
{
	@Override
	public int getNewBlockRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerBlockRenderingHandler()
	{
		RenderingRegistry.registerBlockHandler(new TrinityBlockRenderingHandler());
	}

	@Override
	public void registerTileEntity()
	{
		ClientRegistry.registerTileEntity(TileEntityTrinityBlock.class, "TileEntityTrinityBlock", new TileEntityTrinityRenderer());
	}
}
