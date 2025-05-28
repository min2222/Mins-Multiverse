package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityCreepeel;
import com.min01.multiverse.entity.model.ModelCreepeel;
import com.min01.multiverse.misc.WormChain.Worm;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CreepeelRenderer extends MobRenderer<EntityCreepeel, ModelCreepeel>
{
	public CreepeelRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelCreepeel(p_174304_.bakeLayer(ModelCreepeel.LAYER_LOCATION)), 0.0F);
	}
	
	@Override
	public void render(EntityCreepeel p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.front_body_huge, p_115455_, p_115455_.wormFrontHuge, p_115457_);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.back_body_huge, p_115455_, p_115455_.wormBackHuge, p_115457_);
		
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.front_body_large, p_115455_, p_115455_.wormFrontLarge, p_115457_);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.back_body_large, p_115455_, p_115455_.wormBackLarge, p_115457_);
		
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.front_body_medium, p_115455_, p_115455_.wormFrontMedium, p_115457_);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.back_body_medium, p_115455_, p_115455_.wormBackMedium, p_115457_);
		
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.front_body_small, p_115455_, p_115455_.wormFrontSmall, p_115457_);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.back_body_small, p_115455_, p_115455_.wormBackSmall, p_115457_);
	}
	
	public void renderSegment(PoseStack stack, MultiBufferSource source, int packedLight, ModelPart part, EntityCreepeel entity, Worm worm, float partialTicks)
	{
		stack.pushPose();
		Vec3 pos = worm.position(partialTicks);
		Vec2 rot = worm.getRot(partialTicks);
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(-pos.x, -pos.y, pos.z);
		MultiverseClientUtil.animateHead(part, rot.y + 180.0F, rot.x);
		part.render(stack, source.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity))), packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCreepeel p_115812_) 
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/creepeel.png");
	}
}
