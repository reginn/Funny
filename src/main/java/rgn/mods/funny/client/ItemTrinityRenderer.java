package rgn.mods.funny.client;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import rgn.mods.funny.ItemTrinityBoat;

public class ItemTrinityRenderer implements IItemRenderer
{
	IModelCustom modelTrinity;

	public ItemTrinityRenderer()
	{
		modelTrinity = AdvancedModelLoader.loadModel(TrinityResourceLocations.trinityModel);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return isRenderType(type);
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return isRenderType(type) && isUseHelperType(helper);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(TrinityResourceLocations.trinityTexture);

	}

	protected boolean isRenderType(ItemRenderType type)
	{
		return type == ItemRenderType.INVENTORY
			|| type == ItemRenderType.ENTITY;
	}

	protected boolean isUseHelperType(ItemRendererHelper helper)
	{
		return helper == ItemRendererHelper.INVENTORY_BLOCK
			|| helper == ItemRendererHelper.ENTITY_ROTATION
			|| helper == ItemRendererHelper.ENTITY_BOBBING;
	}
}
