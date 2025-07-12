package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityKingofSin;
import com.min01.multiverse.entity.model.ModelKingofSin;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KingofSinRenderer extends MobRenderer<EntityKingofSin, ModelKingofSin>
{   
	public KingofSinRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelKingofSin(p_174304_.bakeLayer(ModelKingofSin.LAYER_LOCATION)), 0.5F);
	}
	
	@Override
	protected RenderType getRenderType(EntityKingofSin p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) 
	{
		return RenderType.entityTranslucent(this.getTextureLocation(p_115322_));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityKingofSin p_115812_)
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/king_of_sin.png");
	}
}