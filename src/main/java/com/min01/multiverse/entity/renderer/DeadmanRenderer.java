package com.min01.multiverse.entity.renderer;

import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityDeadman;
import com.min01.multiverse.entity.model.ModelDeadman;
import com.min01.multiverse.entity.renderer.layer.DeadmanItemInHandLayer;
import com.min01.multiverse.event.ClientEventHandlerForge;
import com.min01.multiverse.vfx.Effect;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DeadmanRenderer extends MobRenderer<EntityDeadman, ModelDeadman>
{
	public DeadmanRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelDeadman(p_174304_.bakeLayer(ModelDeadman.LAYER_LOCATION)), 0.5F);
		this.addLayer(new DeadmanItemInHandLayer(this));
	}
	
	@Override
	public void render(EntityDeadman p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		for(Pair<Effect, UUID> pair : ClientEventHandlerForge.EFFECTS)
		{
    		if(!p_115455_.getUUID().equals(pair.getRight()))
    		{
    			continue;
    		}
    		pair.getLeft().renderTotal(p_115458_, p_115457_, p_115459_, p_115455_);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EntityDeadman p_115812_) 
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/deadman.png");
	}
}
