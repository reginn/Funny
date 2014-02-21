package rgn.mods.funny.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import rgn.mods.funny.TrinityCore;

public class TrinityBlockRenderingHandler implements ISimpleBlockRenderingHandler
{
	private static TileEntity dummyTileEntity = TrinityCore.blockTrinity.createTileEntity(null, 0);

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		TileEntityRendererDispatcher.instance.getSpecialRenderer(dummyTileEntity).renderTileEntityAt(dummyTileEntity, -0.5D, -0.5D, -0.5D, 0.0F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return TrinityCore.trinityBlockRenderType;
	}
}
