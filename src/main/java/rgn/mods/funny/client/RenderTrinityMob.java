package rgn.mods.funny.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import rgn.mods.funny.EntityTrinityMob;

public class RenderTrinityMob extends Render
{
	IModelCustom trinityModel;

	public RenderTrinityMob()
	{
		trinityModel = AdvancedModelLoader.loadModel(TrinityResourceLocations.trinityModel);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float var8, float var9)
	{
		this.doRender((EntityTrinityMob)entity, x, y, z, var8, var9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return TrinityResourceLocations.trinityTexture;
	}

	public void doRender(EntityTrinityMob entityTrinityMob, double x, double y, double z, float f, float f1)
	{
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x, (float) y + 0.5F, (float) z);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		this.bindEntityTexture(entityTrinityMob);

		trinityModel.renderAll();
		GL11.glPopMatrix();
	}
}
