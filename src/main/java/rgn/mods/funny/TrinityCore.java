package rgn.mods.funny;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@Mod(modid = TrinityModInfo.MODID, name = TrinityModInfo.NAME, version = TrinityModInfo.VERSION)
public class TrinityCore
{

	@SidedProxy(clientSide = "rgn.mods.funny.client.ClientProxy", serverSide = "rgn.mods.funny.CommonProxy")
	public static CommonProxy proxy;

	public static Block blockTrinity;

	public static int trinityBlockRenderType;

	public static final CreativeTabs tabTrinity = new CreativeTabs("tabTrinity")
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(blockTrinity);
		}
	};

	@Mod.EventHandler

	public void preInit(FMLPreInitializationEvent event)
	{
		trinityBlockRenderType = proxy.getNewBlockRenderType();

		blockTrinity = (new BlockTrinity(Material.wood))
				.setBlockName("blockTrinity")
				.setBlockTextureName("planks_oak")
				.setCreativeTab(tabTrinity);

		GameRegistry.registerBlock(blockTrinity, "blockTrinity");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerBlockRenderingHandler();
		proxy.registerTileEntity();
	}
}
