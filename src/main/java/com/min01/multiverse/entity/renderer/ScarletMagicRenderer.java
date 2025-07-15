package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.efkefc.EfkEfcLoader;
import com.min01.multiverse.efkefc.EfkEfcRenderer;
import com.min01.multiverse.entity.model.ModelHand;
import com.min01.multiverse.entity.projectile.EntityScarletMagic;
import com.min01.multiverse.entity.projectile.EntityScarletMagic.ScarletMagicType;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ScarletMagicRenderer extends EntityRenderer<EntityScarletMagic>
{
	public final ModelHand handModel;
	
	public ScarletMagicRenderer(Context p_174008_)
	{
		super(p_174008_);
		this.handModel = new ModelHand(p_174008_.bakeLayer(ModelHand.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityScarletMagic p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		super.render(p_114485_, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
		ScarletMagicType type = p_114485_.getScarletMagicType();
		float scale = type.getScale();
		Vec3 rot = p_114485_.getRotation();
		p_114488_.pushPose();
		p_114488_.scale(scale, scale, scale);
		if(type == ScarletMagicType.GATE)
		{
			p_114488_.pushPose();
			p_114488_.mulPose(Axis.YP.rotationDegrees((float) -rot.y));
			p_114488_.mulPose(Axis.XP.rotationDegrees((float) rot.x));
			EfkEfcRenderer gate = EfkEfcLoader.getEfkEfcRenderer(p_114485_, "scarlet_gate");
			if(gate != null)
			{
				gate.render(p_114488_, p_114489_, p_114485_.tickCount, p_114487_);
			}
			p_114488_.popPose();
		}
		else if(type == ScarletMagicType.HAND)
		{
			float dist = (float) p_114485_.position().distanceTo(p_114485_.getGatePos());
			AABB aabb = new AABB(-1.0F, -1.3F, 2.8125F, 1.0F, -0.55F, dist);
			p_114488_.pushPose();
			p_114488_.mulPose(Axis.YP.rotationDegrees((float) -rot.y));
			p_114488_.mulPose(Axis.XP.rotationDegrees((float) rot.x));
			MultiverseClientUtil.drawBox(aabb, p_114488_, p_114489_, Vec3.ZERO, p_114490_, 1, RenderType.entityCutoutNoCull(new ResourceLocation(MinsMultiverse.MODID, "textures/misc/white.png")));
			this.handModel.renderToBuffer(p_114488_, p_114489_.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(p_114485_))), p_114490_, OverlayTexture.NO_OVERLAY, 0, 0, 0, 0);
			p_114488_.popPose();
		}
		p_114488_.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityScarletMagic p_114482_) 
	{
		return new ResourceLocation(MinsMultiverse.MODID, "textures/entity/hand.png");
	}
}
