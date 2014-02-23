package rgn.mods.funny;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

@Mod(modid = TrinityModInfo.MODID, name = TrinityModInfo.NAME, version = TrinityModInfo.VERSION)
public class TrinityCore
{

	@SidedProxy(clientSide = "rgn.mods.funny.client.ClientProxy", serverSide = "rgn.mods.funny.CommonProxy")
	public static CommonProxy proxy;

	public static Block blockTrinity;

	public static Item itemTrinityBoat;
	public static Item itemTrinityCart;

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

		itemTrinityBoat = (new ItemTrinityBoat())
				.setUnlocalizedName("itemTrinityBoat")
				.setTextureName("boat")
				.setCreativeTab(tabTrinity);

		GameRegistry.registerItem(itemTrinityBoat, "itemTrinityBoat");

		itemTrinityCart = (new ItemTrinityCart())
				.setUnlocalizedName("itemTrinityCart")
				.setTextureName("minecart_normal")
				.setCreativeTab(tabTrinity);

		GameRegistry.registerItem(itemTrinityCart, "itemTrinityCart");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderingHandler();
		proxy.registerTileEntity();

		EntityRegistry.registerModEntity(EntityTrinityBoat.class, "entityTrinityBoat", 0, this, 250, 1, true);
		EntityRegistry.registerModEntity(EntityTrinityCart.class, "entityTrinityCart", 1, this, 250, 1, true);
		EntityRegistry.registerModEntity(EntityTrinityMob.class,  "entityTrinityMob",  2, this, 250, 1, true);

		//EntityRegistry.addSpawn(EntityTrinityMob.class, 50, 1, 4, EnumCreatureType.creature, new BiomeGenBase[] {BiomeGenBase.plains});

		BlockDispenser.dispenseBehaviorRegistry.putObject(itemTrinityBoat, new BehaviorDefaultDispenseItem()
		{
			private final BehaviorDefaultDispenseItem field_150842_b = new BehaviorDefaultDispenseItem();

			public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack itemStack)
			{
				EnumFacing enumfacing = BlockDispenser.func_149937_b(iBlockSource.getBlockMetadata());
				World world = iBlockSource.getWorld();
				double dx = iBlockSource.getX() + (double) ((float) enumfacing.getFrontOffsetX() * 1.125F);
				double dy = iBlockSource.getY() + (double) ((float) enumfacing.getFrontOffsetY() * 1.125F);
				double dz = iBlockSource.getZ() + (double) ((float) enumfacing.getFrontOffsetZ() * 1.125F);
				int x = iBlockSource.getXInt() + enumfacing.getFrontOffsetX();
				int y = iBlockSource.getYInt() + enumfacing.getFrontOffsetY();
				int z = iBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
				Material material = world.getBlock(x, y, z).getMaterial();
				double yBias;

				if (Material.water.equals(material))
				{
					yBias = 1.0D;
				}
				else
				{
					if (!Material.air.equals(material) || !Material.water.equals(world.getBlock(x, y - 1, z).getMaterial()))
					{
						return this.field_150842_b.dispense(iBlockSource, itemStack);
					}

					yBias = 0.0D;
				}

				EntityTrinityBoat entityTrinityBoat = new EntityTrinityBoat(world, dx, dy + yBias, dz);
				world.spawnEntityInWorld(entityTrinityBoat);
				itemStack.splitStack(1);
				return itemStack;
			}

			/**
			 * Play the dispense sound from the specified block.
			 */
			protected void playDispenseSound(IBlockSource par1IBlockSource)
			{
				par1IBlockSource.getWorld().playAuxSFX(1000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
			}
		});

		BlockDispenser.dispenseBehaviorRegistry.putObject(itemTrinityCart, new BehaviorDefaultDispenseItem()
		{
			private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();

			/**
			 * Dispense the specified stack, play the dispense sound and spawn particles.
			 */
			public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack itemStack)
			{
				EnumFacing enumfacing = BlockDispenser.func_149937_b(iBlockSource.getBlockMetadata());
				World world = iBlockSource.getWorld();
				double dx = iBlockSource.getX() + (double) ((float) enumfacing.getFrontOffsetX() * 1.125F);
				double dy = iBlockSource.getY() + (double) ((float) enumfacing.getFrontOffsetY() * 1.125F);
				double dz = iBlockSource.getZ() + (double) ((float) enumfacing.getFrontOffsetZ() * 1.125F);
				int x = iBlockSource.getXInt() + enumfacing.getFrontOffsetX();
				int y = iBlockSource.getYInt() + enumfacing.getFrontOffsetY();
				int z = iBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
				Block block = world.getBlock(x, y, z);
				double yBias;

				if (BlockRailBase.func_150051_a(block))
				{
					yBias = 0.0D;
				}
				else
				{
					if (block.getMaterial() != Material.air || !BlockRailBase.func_150051_a(world.getBlock(x, y - 1, z)))
					{
						return this.behaviourDefaultDispenseItem.dispense(iBlockSource, itemStack);
					}

					yBias = -1.0D;
				}

				EntityTrinityCart entityTrinityCart = new EntityTrinityCart(world, dx, dy + yBias, dz);

				if (itemStack.hasDisplayName())
				{
					entityTrinityCart.setMinecartName(itemStack.getDisplayName());
				}

				world.spawnEntityInWorld(entityTrinityCart);
				itemStack.splitStack(1);
				return itemStack;
			}

			/**
			 * Play the dispense sound from the specified block.
			 */
			protected void playDispenseSound(IBlockSource par1IBlockSource)
			{
				par1IBlockSource.getWorld().playAuxSFX(1000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
			}
		});
	}
}
