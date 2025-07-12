package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityCreepeel;
import com.min01.multiverse.entity.model.ModelCreepeel;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CreepeelRenderer extends MobRenderer<EntityCreepeel, ModelCreepeel>
{
	public CreepeelRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelCreepeel(p_174304_.bakeLayer(ModelCreepeel.LAYER_LOCATION)), 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCreepeel p_115812_) 
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/creepeel.png");
	}
}
