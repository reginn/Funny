package rgn.mods.funny;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTrinity extends BlockContainer
{
	public BlockTrinity(Material material)
	{
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2)
	{
		return new TileEntityTrinityBlock();
	}

	@Override
	public int getRenderType()
	{
		return TrinityCore.trinityBlockRenderType;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
