package rgn.mods.funny;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTrinityCart extends Item
{
	public ItemTrinityCart()
	{
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if (BlockRailBase.func_150051_a(world.getBlock(x, y, z)))
		{
			if (!world.isRemote)
			{
				EntityTrinityCart entityTrinityCart = new EntityTrinityCart(world, (double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F));

				world.spawnEntityInWorld(entityTrinityCart);
			}

			--itemStack.stackSize;
			return true;
		}
		else
		{
			return false;
		}
	}
}
