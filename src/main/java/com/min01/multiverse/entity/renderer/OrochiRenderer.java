package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.efkefc.EfkEfcLoader;
import com.min01.multiverse.efkefc.EfkEfcRenderer;
import com.min01.multiverse.efkefc.EfkEfcRenderer.EfkEfcEmitter;
import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityOrochi.ChainType;
import com.min01.multiverse.entity.model.ModelOrochi;
import com.min01.multiverse.entity.model.ModelOrochiBody;
import com.min01.multiverse.entity.model.ModelOrochiHead;
import com.min01.multiverse.entity.model.ModelOrochiTail;
import com.min01.multiverse.misc.KinematicChain;
import com.min01.multiverse.misc.KinematicChain.ChainSegment;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class OrochiRenderer extends MobRenderer<EntityOrochi, ModelOrochi>
{
	public static final ResourceLocation HEAD_TEXTURE = new ResourceLocation(MinsMultiverse.MODID, "textures/entity/orochi_head.png");
	public static final ResourceLocation BODY_TEXTURE = new ResourceLocation(MinsMultiverse.MODID, "textures/entity/orochi_body.png");
	public static final ResourceLocation TAIL_TEXTURE = new ResourceLocation(MinsMultiverse.MODID, "textures/entity/orochi_tail.png");
	
	public final ModelOrochiHead headModel;
	public final ModelOrochiBody bodyModel;
	public final ModelOrochiTail tailModel;
	
	public OrochiRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelOrochi(p_174304_.bakeLayer(ModelOrochi.LAYER_LOCATION)), 0.5F);
		this.headModel = new ModelOrochiHead(p_174304_.bakeLayer(ModelOrochiHead.LAYER_LOCATION));
		this.bodyModel = new ModelOrochiBody(p_174304_.bakeLayer(ModelOrochiBody.LAYER_LOCATION));
		this.tailModel = new ModelOrochiTail(p_174304_.bakeLayer(ModelOrochiTail.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityOrochi p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		if(p_115455_.isSnake())
		{
			KinematicChain chain = p_115455_.chain;
			if(chain == null)
				return;
			for(int i = 1; i < chain.getSegments().length - 1; i++)
			{
		    	Vec3 camPos = MultiverseClientUtil.MC.gameRenderer.getMainCamera().getPosition();
		        double x = Mth.lerp((double)p_115457_, p_115455_.xOld, p_115455_.getX());
		        double y = Mth.lerp((double)p_115457_, p_115455_.yOld, p_115455_.getY());
		        double z = Mth.lerp((double)p_115457_, p_115455_.zOld, p_115455_.getZ());
				ChainSegment segment = chain.getSegments()[i];
				Vec3 pos = segment.position(p_115457_);
				Vec3 firstPos = chain.getSegments()[0].position(p_115457_);
				Vec2 rot = segment.getRot(p_115457_);
				if(firstPos.y < -128 && chain.getAnchorPos() == null)
				{
					continue;
				}
				if(i == chain.getSegments().length - 2 && p_115455_.getChainType() == ChainType.LASER && p_115455_.isAnim())
				{
					p_115458_.pushPose();
					p_115458_.scale(p_115455_.getScale(), p_115455_.getScale(), p_115455_.getScale());
					EfkEfcRenderer renderer = EfkEfcLoader.getEfkEfcRenderer(p_115455_, "blood_laser", p_115457_);
					if(renderer != null)
					{
						for(EfkEfcEmitter emitter : renderer.emitters)
						{
							if(emitter.node.name.equals("LaserParticle"))
							{
								p_115458_.pushPose();
								p_115458_.mulPose(Axis.YP.rotationDegrees(-rot.y));
								p_115458_.mulPose(Axis.XP.rotationDegrees(rot.x));
								p_115458_.mulPose(Axis.YP.rotationDegrees(90.0F));
								p_115458_.translate(0, 0.25F, 0);
								renderer.render(p_115458_, p_115459_, emitter, p_115455_.tickCount, p_115457_);
								p_115458_.popPose();
							}
							else if(emitter.node.name.equals("Laser"))
							{
								p_115458_.pushPose();
								p_115458_.scale(0.25F, 0.25F, 0.25F);
								p_115458_.mulPose(Axis.YP.rotationDegrees(-rot.y));
								p_115458_.mulPose(Axis.XP.rotationDegrees(rot.x));
								p_115458_.translate(0, 1.25F, 0);
								renderer.render(p_115458_, p_115459_, emitter, p_115455_.tickCount, p_115457_);
								p_115458_.popPose();
							}
							else if(emitter.node.name.equals("Particle"))
							{
								p_115458_.pushPose();
								p_115458_.mulPose(Axis.YP.rotationDegrees(-rot.y));
								p_115458_.mulPose(Axis.XP.rotationDegrees(rot.x));
								p_115458_.mulPose(Axis.YP.rotationDegrees(90.0F));
								p_115458_.translate(0, 0.25F, 0);
								renderer.render(p_115458_, p_115459_, emitter, p_115455_.tickCount, p_115457_);
								p_115458_.popPose();
							}
							else if(emitter.node.name.equals("LaserCore2"))
							{
								p_115458_.pushPose();
								p_115458_.mulPose(Axis.YP.rotationDegrees(-rot.y));
								p_115458_.mulPose(Axis.XP.rotationDegrees(rot.x));
								p_115458_.mulPose(Axis.YP.rotationDegrees(90.0F));
								p_115458_.translate(0, 0.25F, 0);
								renderer.render(p_115458_, p_115459_, emitter, p_115455_.tickCount, p_115457_);
								p_115458_.popPose();
							}
						}
					}
					p_115458_.popPose();
				}
				p_115458_.pushPose();
				this.transform(p_115458_, camPos, pos, rot, p_115455_.getScale(), x, y, z);
				if(i == chain.getSegments().length - 2)
				{
					this.headModel.setupAnimOrochi(p_115455_.jawOpenAnimationState, p_115455_, 0, 0, p_115455_.tickCount + p_115457_, 0, 0);
		            this.headModel.renderToBuffer(p_115458_, p_115459_.getBuffer(RenderType.entityCutoutNoCull(HEAD_TEXTURE)), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				}
				else if(i > 1)
				{
		            this.bodyModel.renderToBuffer(p_115458_, p_115459_.getBuffer(RenderType.entityCutoutNoCull(BODY_TEXTURE)), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				}
				else
				{
					this.tailModel.renderToBuffer(p_115458_, p_115459_.getBuffer(RenderType.entityCutoutNoCull(TAIL_TEXTURE)), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				}
				p_115458_.popPose();
			}
		}
		else
		{
			super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		}
	}
	
	public void transform(PoseStack stack, Vec3 camPos, Vec3 pos, Vec2 rot, float scale, double x, double y, double z)
	{
		stack.translate(-(x - camPos.x), -(y - camPos.y), -(z - camPos.z));
		stack.translate(pos.x - camPos.x, pos.y - camPos.y, pos.z - camPos.z);
		stack.mulPose(Axis.YP.rotationDegrees(-rot.y + 180.0F));
		stack.mulPose(Axis.XP.rotationDegrees(-rot.x));
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.scale(scale, scale, scale);
		stack.translate(0, -1.5F, 0);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityOrochi p_115812_) 
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/orochi.png");
	}
}
