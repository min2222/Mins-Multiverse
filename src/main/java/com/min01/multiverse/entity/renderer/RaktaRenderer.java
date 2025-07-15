package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityRakta;
import com.min01.multiverse.entity.model.ModelRakta;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RaktaRenderer extends MobRenderer<EntityRakta, ModelRakta>
{   
	public RaktaRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelRakta(p_174304_.bakeLayer(ModelRakta.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityRakta p_115812_)
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/rakta.png");
	}
}