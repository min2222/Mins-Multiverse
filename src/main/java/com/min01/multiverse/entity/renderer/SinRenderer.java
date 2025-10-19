package com.min01.multiverse.entity.renderer;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.efkefc.EfkEfcLoader;
import com.min01.multiverse.efkefc.EfkEfcRenderer;
import com.min01.multiverse.entity.projectile.EntitySin;
import com.min01.multiverse.entity.projectile.EntitySin.SinType;
import com.min01.multiverse.misc.MultiverseRenderType;
import com.min01.multiverse.obj.ObjModelManager;
import com.min01.multiverse.obj.WavefrontObject;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class SinRenderer extends EntityRenderer<EntitySin>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(MinsMultiverse.MODID, "textures/entity/sword.png");
    
    public static final WavefrontObject[] SWORDS = new WavefrontObject[24];
    
    static
    {
    	for(int i = 0; i < 24; i++)
    	{
    		SWORDS[i] = ObjModelManager.getInstance().getModel(new ResourceLocation(MinsMultiverse.MODID, "models/obj/sword" + (i + 1) + ".obj"));
    	}
    }
    
	public SinRenderer(Context p_174008_) 
	{
		super(p_174008_);
	}
	
	@Override
	public void render(EntitySin p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		if(p_114485_.getSwordType() == 0)
			return;
		WavefrontObject obj = SWORDS[p_114485_.getSwordType() - 1];
		SinType type = p_114485_.getSinType();
		float scale = type.getScale();
		Vec3 rot = p_114485_.getRotation();
		p_114488_.pushPose();
		p_114488_.scale(scale, scale, scale);
		if(type == SinType.GATE)
		{
			p_114488_.pushPose();
			p_114488_.mulPose(Axis.YP.rotationDegrees((float) -rot.y));
			p_114488_.mulPose(Axis.XP.rotationDegrees((float) rot.x));
			EfkEfcRenderer gate = EfkEfcLoader.getEfkEfcRenderer(p_114485_, "sin_gate", p_114487_);
			if(gate != null)
			{
				gate.render(p_114488_, p_114489_, p_114485_.tickCount, p_114487_);
			}
			p_114488_.popPose();
		}
		else if(type == SinType.GATE_SWORD)
		{
			p_114488_.pushPose();
			p_114488_.mulPose(Axis.YP.rotationDegrees(Mth.rotLerp(p_114487_, p_114485_.yRotO, p_114485_.getYRot()) + 180.0F));
			p_114488_.mulPose(Axis.XP.rotationDegrees(Mth.lerp(p_114487_, p_114485_.xRotO, p_114485_.getXRot()) + 180.0F));
			EfkEfcRenderer renderer = EfkEfcLoader.getEfkEfcRenderer(p_114485_, "sin_spear", p_114487_);
			if(renderer != null && !p_114485_.getDeltaMovement().equals(Vec3.ZERO))
			{
				renderer.render(p_114488_, p_114489_, p_114485_.tickCount, p_114487_);
			}
			p_114488_.popPose();

			p_114488_.pushPose();
			p_114488_.scale(5.0F, 5.0F, 5.0F);
			p_114488_.mulPose(Axis.YP.rotationDegrees(Mth.rotLerp(p_114487_, p_114485_.yRotO, p_114485_.getYRot()) + 180.0F));
			p_114488_.mulPose(Axis.XP.rotationDegrees(Mth.lerp(p_114487_, p_114485_.xRotO, p_114485_.getXRot()) + 180.0F));
			p_114488_.mulPose(Axis.XP.rotationDegrees(90.0F));
			p_114488_.translate(0.0F, -0.5F, 0.0F);
			MultiverseClientUtil.renderObj(p_114488_, p_114489_, obj, MultiverseRenderType.objBlend(TEXTURE), p_114490_);
			p_114488_.popPose();
		}
		else if(type == SinType.BLOCKING)
		{
			p_114488_.pushPose();
			p_114488_.mulPose(Axis.YP.rotationDegrees((float) -rot.y));
			p_114488_.mulPose(Axis.XP.rotationDegrees((float) rot.x));
			EfkEfcRenderer renderer = EfkEfcLoader.getEfkEfcRenderer(p_114485_, "sin_spear", p_114487_);
			if(renderer != null && !p_114485_.getDeltaMovement().equals(Vec3.ZERO))
			{
				renderer.render(p_114488_, p_114489_, p_114485_.tickCount, p_114487_);
			}
			p_114488_.popPose();

			p_114488_.pushPose();
			p_114488_.scale(5.0F, 5.0F, 5.0F);
			p_114488_.mulPose(Axis.YP.rotationDegrees((float) -rot.y));
			p_114488_.mulPose(Axis.XP.rotationDegrees((float) rot.x + 90.0F));
			p_114488_.translate(0.0F, -0.5F, 0.0F);
			MultiverseClientUtil.renderObj(p_114488_, p_114489_, obj, MultiverseRenderType.objBlend(TEXTURE), p_114490_);
			p_114488_.popPose();
		}
		p_114488_.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntitySin p_114482_) 
	{
		return null;
	}
}
