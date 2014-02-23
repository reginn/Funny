package rgn.mods.funny.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import rgn.mods.funny.CommonProxy;
import rgn.mods.funny.EntityTrinityBoat;
import rgn.mods.funny.EntityTrinityCart;
import rgn.mods.funny.TileEntityTrinityBlock;

public class ClientProxy extends CommonProxy
{
	@Override
	public int getNewBlockRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerRenderingHandler()
	{
		RenderingRegistry.registerBlockHandler(new TrinityBlockRenderingHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityTrinityBoat.class, new RenderTrinityBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityTrinityCart.class, new RenderTrinityCart());
	}

	@Override
	public void registerTileEntity()
	{
		ClientRegistry.registerTileEntity(TileEntityTrinityBlock.class, "TileEntityTrinityBlock", new TileEntityTrinityRenderer());
	}
}
