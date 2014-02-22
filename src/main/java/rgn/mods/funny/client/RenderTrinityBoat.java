package rgn.mods.funny.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import rgn.mods.funny.EntityTrinityBoat;

@SideOnly(Side.CLIENT)
public class RenderTrinityBoat extends Render
{
	protected IModelCustom modelTrinityBoat;

	public RenderTrinityBoat()
	{
		this.shadowSize = 0.5F;
		this.modelTrinityBoat = AdvancedModelLoader.loadModel(TrinityResourceLocations.trinityModel);
	}

	public void doRender(EntityTrinityBoat entityTrinityBoat, double x, double y, double z, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 0.1F, (float) z);
		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		float f2 = (float) entityTrinityBoat.getTimeSinceHit() - par9;
		float f3 = entityTrinityBoat.getDamageTaken() - par9;

		if (f3 < 0.0F)
		{
			f3 = 0.0F;
		}

		if (f2 > 0.0F)
		{
			GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float) entityTrinityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}

		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
		this.bindEntityTexture(entityTrinityBoat);
		this.modelTrinityBoat.renderAll();
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
		this.doRender((EntityTrinityBoat) par1Entity, par2, par4, par6, par8, par9);
	}
}
