package rgn.mods.funny.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityTrinityRenderer extends TileEntitySpecialRenderer
{
	private IModelCustom trinityBlockModel;

	public TileEntityTrinityRenderer()
	{
		trinityBlockModel = AdvancedModelLoader.loadModel(TrinityResourceLocations.trinityModel);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTickTime)
	{
		this.doRender(tileEntity, x, y, z, partialTickTime);
	}

	private void doRender(TileEntity tileEntity, double x, double y, double z, float partialTickTime)
	{
		bindTexture(TrinityResourceLocations.trinityTexture);
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		trinityBlockModel.renderAll();
		GL11.glPopMatrix();
	}
}
