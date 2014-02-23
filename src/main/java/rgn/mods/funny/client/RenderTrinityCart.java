package rgn.mods.funny.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import rgn.mods.funny.EntityTrinityCart;

public class RenderTrinityCart extends Render
{
	protected IModelCustom trinityModel;

	public RenderTrinityCart()
	{
		this.shadowSize = 0.5F;
		this.trinityModel = AdvancedModelLoader.loadModel(TrinityResourceLocations.trinityModel);
	}

	public void doRender(EntityTrinityCart entityTrinityCart, double x, double y, double z, float par8, float par9)
	{
		GL11.glPushMatrix();

		long i = (long) entityTrinityCart.getEntityId() * 493286711L;
		i = i * i * 4392167121L + i * 98761L;
		float f2 = (((float) (i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f3 = (((float) (i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f4 = (((float) (i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		GL11.glTranslatef(f2, f3, f4);
		double d3 = entityTrinityCart.lastTickPosX + (entityTrinityCart.posX - entityTrinityCart.lastTickPosX) * (double) par9;
		double d4 = entityTrinityCart.lastTickPosY + (entityTrinityCart.posY - entityTrinityCart.lastTickPosY) * (double) par9;
		double d5 = entityTrinityCart.lastTickPosZ + (entityTrinityCart.posZ - entityTrinityCart.lastTickPosZ) * (double) par9;
		double d6 = 0.30000001192092896D;
		Vec3 vec3 = entityTrinityCart.func_70489_a(d3, d4, d5);
		float f5 = entityTrinityCart.prevRotationPitch + (entityTrinityCart.rotationPitch - entityTrinityCart.prevRotationPitch) * par9;

		if (vec3 != null)
		{
			Vec3 vec31 = entityTrinityCart.func_70495_a(d3, d4, d5, d6);
			Vec3 vec32 = entityTrinityCart.func_70495_a(d3, d4, d5, -d6);

			if (vec31 == null)
			{
				vec31 = vec3;
			}

			if (vec32 == null)
			{
				vec32 = vec3;
			}

			x += vec3.xCoord - d3;
			y += (vec31.yCoord + vec32.yCoord) / 2.0D - d4;
			z += vec3.zCoord - d5;
			Vec3 vec33 = vec32.addVector(-vec31.xCoord, -vec31.yCoord, -vec31.zCoord);

			if (vec33.lengthVector() != 0.0D)
			{
				vec33 = vec33.normalize();
				par8 = (float) (Math.atan2(vec33.zCoord, vec33.xCoord) * 180.0D / Math.PI);
				f5 = (float) (Math.atan(vec33.yCoord) * 73.0D);
			}
		}

		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-f5, 0.0F, 0.0F, 1.0F);
		float f7 = (float) entityTrinityCart.getRollingAmplitude() - par9;
		float f8 = entityTrinityCart.getDamage() - par9;

		if (f8 < 0.0F)
		{
			f8 = 0.0F;
		}

		if (f7 > 0.0F)
		{
			GL11.glRotatef(MathHelper.sin(f7) * f7 * f8 / 10.0F * (float) entityTrinityCart.getRollingDirection(), 1.0F, 0.0F, 0.0F);
		}

		GL11.glScalef(0.5F, 0.5F, 0.5F);
		this.bindEntityTexture(entityTrinityCart);
		this.trinityModel.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return TrinityResourceLocations.trinityTexture;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.doRender((EntityTrinityCart) par1Entity, par2, par4, par6, par8, par9);
	}
}
