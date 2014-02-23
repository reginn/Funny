package rgn.mods.funny;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTrinityMob extends EntityCreature
{
	public EntityTrinityMob(World world)
	{
		super(world);
		this.setSize(1.0F, 0.5F);
	}

	@Override
	protected boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (!worldObj.isRemote && this.riddenByEntity == null)
		{
			par1EntityPlayer.mountEntity(this);
			return true;
		}
		return false;
	}
}
