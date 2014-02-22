package rgn.mods.funny.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.MinecraftForgeClient;
import rgn.mods.funny.*;

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
		//MinecraftForgeClient.registerItemRenderer(TrinityCore.itemTrinityBoat, new ItemTrinityRenderer());
	}

	@Override
	public void registerTileEntity()
	{
		ClientRegistry.registerTileEntity(TileEntityTrinityBlock.class, "TileEntityTrinityBlock", new TileEntityTrinityRenderer());
	}
}
