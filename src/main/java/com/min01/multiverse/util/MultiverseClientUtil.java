package com.min01.multiverse.util;

import java.awt.Color;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector4f;

import com.min01.multiverse.obj.Face;
import com.min01.multiverse.obj.WavefrontObject;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MultiverseClientUtil 
{
	public static final Minecraft MC = Minecraft.getInstance();
	
    public static void drawBox(AABB boundingBox, PoseStack stack, MultiBufferSource bufferIn, Vec3 rgb, int light, int alpha, RenderType renderType) 
    {
        VertexConsumer vertexbuffer = bufferIn.getBuffer(renderType);
        Matrix4f matrix4f = stack.last().pose();
        float maxX = (float) boundingBox.maxX * 0.625F;
        float minX = (float) boundingBox.minX * 0.625F;
        float maxY = (float) boundingBox.maxY * 0.625F;
        float minY = (float) boundingBox.minY * 0.625F;
        float maxZ = (float) boundingBox.maxZ * 0.625F;
        float minZ = (float) boundingBox.minZ * 0.625F;

        float maxU = maxZ - minZ;
        float maxV = maxY - minY;
        float minU = minZ - maxZ;
        float minV = minY - maxY;
        // X+
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(1.0F, 0.0F, 0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(1.0F, 0.0F, 0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(1.0F, 0.0F, 0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(1.0F, 0.0F, 0F).endVertex();

        // X-
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(-1.0F, 0.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(-1.0F, 0.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(-1.0F, 0.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(-1.0F, 0.0F, 0.0F).endVertex();

        maxU = maxX - minX;
        maxV = maxY - minY;
        minU = minX - maxX;
        minV = minY - maxY;
        // Z-
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, -1.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, -1.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, -1.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, -1.0F).endVertex();

        // Z+
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, 1.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, 1.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, 1.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 0.0F, 1.0F).endVertex();


        maxU = maxZ - minZ;
        maxV = maxX - minX;
        minU = minZ - maxZ;
        minV = minX - maxX;
        // Y+
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.maxY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();

        // Y-
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, -1.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, -1.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.minZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, -1.0F, 0.0F).endVertex();
        vertexbuffer.vertex(matrix4f, (float) boundingBox.maxX, (float) boundingBox.minY, (float) boundingBox.maxZ).color((float)rgb.x, (float)rgb.y, (float)rgb.z, alpha).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0.0F, -1.0F, 0.0F).endVertex();
    }
    
	public static void drawRing(float innerRadius, float outerRadius, float innerHeight, float outerHeight, float centerRatio, int segments, PoseStack stack, MultiBufferSource buffer, Vector4f innerColor, Vector4f centerColor, Vector4f outerColor, int light, RenderType renderType, Vec3 center) 
	{
	    VertexConsumer vertexBuffer = buffer.getBuffer(renderType);
	    Matrix4f matrix = stack.last().pose();
	    float centerRadius = innerRadius + (outerRadius - innerRadius) * centerRatio;
	    float centerHeight = innerHeight + (outerHeight - innerHeight) * centerRatio;
	    
	    float minHeight = (float) (center.z - outerHeight);
	    float maxHeight = (float) (center.z - innerHeight);
	    float heightRange = maxHeight - minHeight;

	    for(int i = 0; i < segments; i++) 
	    {
	        float angle1 = (float)(2 * Math.PI * i / segments);
	        float angle2 = (float)(2 * Math.PI * (i + 1) / segments);

	        float u0 = (float)i / segments;
	        float u1 = (float)(i + 1) / segments;

	        Vec3 inner1 = new Vec3(center.x + innerRadius * Math.cos(angle1), center.y + innerRadius * Math.sin(angle1), center.z - innerHeight);
	        Vec3 inner2 = new Vec3(center.x + innerRadius * Math.cos(angle2), center.y + innerRadius * Math.sin(angle2), center.z - innerHeight);
	        Vec3 center1 = new Vec3(center.x + centerRadius * Math.cos(angle1), center.y + centerRadius * Math.sin(angle1), center.z - centerHeight);
	        Vec3 center2 = new Vec3(center.x + centerRadius * Math.cos(angle2), center.y + centerRadius * Math.sin(angle2), center.z - centerHeight);
	        Vec3 outer1 = new Vec3(center.x + outerRadius * Math.cos(angle1), center.y + outerRadius * Math.sin(angle1), center.z - outerHeight);
	        Vec3 outer2 = new Vec3(center.x + outerRadius * Math.cos(angle2), center.y + outerRadius * Math.sin(angle2), center.z - outerHeight);
	        
	        float vInner1 = computeV(inner1.z, minHeight, heightRange);
	        float vInner2 = computeV(inner2.z, minHeight, heightRange);
	        float vCenter1 = computeV(center1.z, minHeight, heightRange);
	        float vCenter2 = computeV(center2.z, minHeight, heightRange);
	        float vOuter1 = computeV(outer1.z, minHeight, heightRange);
	        float vOuter2 = computeV(outer2.z, minHeight, heightRange);
	        
	        addQuadWithUV(vertexBuffer, matrix, inner2, center2, center1, inner1, u1, u1, u0, u0, vInner2, vCenter2, vCenter1, vInner1, innerColor, centerColor, centerColor, innerColor, light);
	        addQuadWithUV(vertexBuffer, matrix, center2, outer2, outer1, center1, u1, u1, u0, u0, vCenter2, vOuter2, vOuter1, vCenter1, centerColor, outerColor, outerColor, centerColor, light);
	    }
	}
	
	public static float computeV(double z, double minHeight, double heightRange) 
	{
	    return (float) ((z - minHeight) / heightRange);
	}
	
	public static void addQuadWithUV(VertexConsumer buffer, Matrix4f matrix, Vec3 v0, Vec3 v1, Vec3 v2, Vec3 v3, float u0, float u1, float u2, float u3, float v0uv, float v1uv, float v2uv, float v3uv, Vector4f c0, Vector4f c1, Vector4f c2, Vector4f c3, int light)
	{
	    buffer.vertex(matrix, (float)v0.x, (float)v0.y, (float)v0.z).color(c0.x, c0.y, c0.z, c0.w).uv(u0, v0uv).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0, 1, 0).endVertex();
	    buffer.vertex(matrix, (float)v1.x, (float)v1.y, (float)v1.z).color(c1.x, c1.y, c1.z, c1.w).uv(u1, v1uv).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0, 1, 0).endVertex();
	    buffer.vertex(matrix, (float)v2.x, (float)v2.y, (float)v2.z).color(c2.x, c2.y, c2.z, c2.w).uv(u2, v2uv).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0, 1, 0).endVertex();
	    buffer.vertex(matrix, (float)v3.x, (float)v3.y, (float)v3.z).color(c3.x, c3.y, c3.z, c3.w).uv(u3, v3uv).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(0, 1, 0).endVertex();
	}
	
	public static void renderObj(PoseStack stack, MultiBufferSource source, WavefrontObject obj, RenderType renderType, int light)
	{
		stack.pushPose();
		Face.setUvOperator(1, 1, obj.xUV, obj.yUV);
		Face.setMatrix(stack);
		Face.setLightMap(light);
		Face.setColor(Color.BLACK);
		stack.translate(obj.x / 16.0F, obj.y / 16.0F, obj.z / 16.0F);
		if(obj.xRot != 0.0F || obj.yRot != 0.0F || obj.zRot != 0.0F) 
		{
			stack.mulPose((new Quaternionf()).rotationZYX(obj.zRot, obj.yRot, obj.xRot));
		}
		if(obj.xScale != 1.0F || obj.yScale != 1.0F || obj.zScale != 1.0F)
		{
			stack.scale(obj.xScale, obj.yScale, obj.zScale);
		}
		obj.tessellateAll(source.getBuffer(renderType));
		Face.resetUvOperator();
		Face.resetMatrix();
		Face.resetLightMap();
		Face.resetColor();
		stack.popPose();
	}
	
	public static void animateHead(ModelPart head, float netHeadYaw, float headPitch)
	{
		head.yRot += Math.toRadians(netHeadYaw);
		head.xRot += Math.toRadians(headPitch);
	}
}
