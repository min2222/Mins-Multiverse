package com.min01.multiverse.vfx;

import org.joml.Matrix4f;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.util.MultiverseClientUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EffectSlash extends Effect
{
    public float yaw;
    public float pitch;
    public float slashAngle;
    public float size;
    
    public EffectSlash(int levelIn) 
    {
        super(levelIn);
        this.yaw = 0.0F;
        this.pitch = 0.0F;
        this.slashAngle = 0.0F;
    }
    
    public EffectSlash setSlashProperties(float yaw, float pitch, float angle)
    {
        this.yaw = yaw;
        this.pitch = pitch;
        this.slashAngle = angle;
        return this;
    }
    
    public EffectSlash setSlashSize(float size)
    {
    	this.size = size;
        return this;
    }
    
    @Override
    public void read(CompoundTag tag)
    {
        super.read(tag);
        this.yaw = tag.getFloat("yaw");
        this.pitch = tag.getFloat("pitch");
        this.slashAngle = tag.getFloat("slashAngle");
        this.size = tag.getFloat("size");
    }
    
    @Override
    public CompoundTag write() 
    {
        CompoundTag tag = super.write();
        tag.putFloat("yaw", this.yaw);
        tag.putFloat("pitch", this.pitch);
        tag.putFloat("slashAngle", this.slashAngle);
        tag.putFloat("size", this.size);
        return tag;
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(PoseStack stack, float partialTicks, MultiBufferSource buffer)
    {
    	Tesselator tess = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tess.getBuilder();
        stack.pushPose();
        RenderSystem.enableDepthTest();
		RenderSystem.setShaderTexture(0, new ResourceLocation(MinsMultiverse.MODID, "textures/vfx/beam.png"));
		RenderSystem.setShader(GameRenderer::getPositionTexLightmapColorShader);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_LIGHTMAP_COLOR);
        stack.translate(this.getInterpX(partialTicks), this.getInterpY(partialTicks), this.getInterpZ(partialTicks));
        stack.mulPose(Axis.XP.rotationDegrees(this.pitch));
        stack.mulPose(Axis.YP.rotationDegrees(-this.yaw));
        stack.mulPose(Axis.ZP.rotationDegrees(-this.slashAngle));
		PoseStack.Pose posestack$pose = stack.last();
		Matrix4f matrix4f = posestack$pose.pose();
		MultiverseClientUtil.drawSlash(matrix4f, bufferbuilder, -this.size, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, this.r, this.g, this.b, 0.0F, this.r, this.g, this.b, this.a * this.getLifeCoeff(partialTicks), 0.75F * this.getLifeCoeff(partialTicks), this.slashAngle);
        MultiverseClientUtil.drawSlash(matrix4f, bufferbuilder, 0.0F, 0.0F, 0.0F, this.size, 0.0F, 0.0F, this.r, this.g, this.b, this.a * this.getLifeCoeff(partialTicks), this.r, this.g, this.b, 0.0F, 0.75F * this.getLifeCoeff(partialTicks), this.slashAngle);
		BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableDepthTest();
		stack.popPose();
    }
}