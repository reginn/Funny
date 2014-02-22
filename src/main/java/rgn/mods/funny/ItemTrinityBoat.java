package rgn.mods.funny;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class ItemTrinityBoat extends Item
{
	public ItemTrinityBoat()
	{
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		float f = 1.0F;
		float f1 = entityPlayer.prevRotationPitch + (entityPlayer.rotationPitch - entityPlayer.prevRotationPitch) * f;
		float f2 = entityPlayer.prevRotationYaw + (entityPlayer.rotationYaw - entityPlayer.prevRotationYaw) * f;
		double dx = entityPlayer.prevPosX + (entityPlayer.posX - entityPlayer.prevPosX) * (double) f;
		double dy = entityPlayer.prevPosY + (entityPlayer.posY - entityPlayer.prevPosY) * (double) f + 1.62D - (double) entityPlayer.yOffset;
		double dz = entityPlayer.prevPosZ + (entityPlayer.posZ - entityPlayer.prevPosZ) * (double) f;
		Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(dx, dy, dz);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, true);

		if (movingobjectposition == null)
		{
			return itemStack;
		}
		else
		{
			Vec3 vec32 = entityPlayer.getLook(f);
			boolean flag = false;
			float f9 = 1.0F;
			List list = world.getEntitiesWithinAABBExcludingEntity(entityPlayer, entityPlayer.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand((double) f9, (double) f9, (double) f9));
			int i;

			for (i = 0; i < list.size(); ++i)
			{
				Entity entity = (Entity) list.get(i);

				if (entity.canBeCollidedWith())
				{
					float f10 = entity.getCollisionBorderSize();
					AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double) f10, (double) f10, (double) f10);

					if (axisalignedbb.isVecInside(vec3))
					{
						flag = true;
					}
				}
			}

			if (flag)
			{
				return itemStack;
			}
			else
			{
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
				{
					i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;

					if (world.getBlock(i, j, k) == Blocks.snow_layer)
					{
						--j;
					}

					EntityTrinityBoat entityTrinityBoat = new EntityTrinityBoat(world, (double) ((float) i + 0.5F), (double) ((float) j + 1.0F), (double) ((float) k + 0.5F));
					entityTrinityBoat.rotationYaw = (float) (((MathHelper.floor_double((double) (entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);

					if (!world.getCollidingBoundingBoxes(entityTrinityBoat, entityTrinityBoat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
					{
						return itemStack;
					}

					if (!world.isRemote)
					{
						world.spawnEntityInWorld(entityTrinityBoat);
					}

					if (!entityPlayer.capabilities.isCreativeMode)
					{
						--itemStack.stackSize;
					}
				}

				return itemStack;
			}
		}
	}
}
