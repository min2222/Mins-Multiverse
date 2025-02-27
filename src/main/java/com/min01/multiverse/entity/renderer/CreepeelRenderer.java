package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityCreepeel;
import com.min01.multiverse.entity.model.ModelCreepeel;
import com.min01.multiverse.misc.WormChain;
import com.min01.multiverse.misc.WormChain.Worm;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

public class CreepeelRenderer extends MobRenderer<EntityCreepeel, ModelCreepeel>
{
	public final Worm wormHead = new Worm();
	
	public final Worm wormFrontHuge = new Worm();
	public final Worm wormBackHuge = new Worm();
	
	public final Worm wormFrontLarge = new Worm();
	public final Worm wormBackLarge = new Worm();
	
	public final Worm wormFrontMedium = new Worm();
	public final Worm wormBackMedium = new Worm();
	
	public final Worm wormFrontSmall = new Worm();
	public final Worm wormBackSmall = new Worm();
	
	public CreepeelRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelCreepeel(p_174304_.bakeLayer(ModelCreepeel.LAYER_LOCATION)), 0.0F);
	}
	
	@Override
	public void render(EntityCreepeel p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		float distance = 0.5F;
		float speed = 0.35F;
		WormChain.tick(this.wormFrontHuge, p_115455_, distance, speed);
		WormChain.tick(this.wormBackHuge, this.wormFrontHuge, distance, speed);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.front_body_huge, p_115455_, this.wormFrontHuge, p_115457_);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.back_body_huge, p_115455_, this.wormBackHuge, p_115457_);
	}
	
	public void renderSegment(PoseStack stack, MultiBufferSource source, int packedLight, ModelPart part, EntityCreepeel entity, Worm worm, float partialTicks)
	{
		stack.pushPose();
		Vec2 rot = worm.getRot(partialTicks);
		stack.mulPose(Axis.YP.rotationDegrees(180.0F - worm.yRot(partialTicks)));
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(0.0F, -1.5F, 0.0F);
		MultiverseClientUtil.animateHead(part, rot.y, rot.x);
		part.render(stack, source.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity))), packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCreepeel p_115812_) 
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/creepeel.png");
	}
}
